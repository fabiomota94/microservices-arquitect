package com.example.microservico_entry_point.service;

import com.example.microservico_entry_point.model.request.MessageRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SendService {

    private static final Logger logger = LoggerFactory.getLogger(SendService.class);
    private final EmailClient emailClient;

    public SendService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    @CircuitBreaker(name = "emailService", fallbackMethod = "fallbackSendEmail")
    public String sendRequest(MessageRequest request) {
        logger.info("Sending request from {} to {}", request.getRequester(), request.getReceiver());
        try {
            return emailClient.sendEmail(request);
        } catch (Exception e) {
            logger.error("Error sending request: {}", e.getMessage());
            throw e; // Let the Circuit Breaker handle the error and call the fallback
        }
    }

    public String fallbackSendEmail(MessageRequest messageRequest, Throwable x) {
        logger.info("Message was not sent, service is unavailable");
        return "Service is down";
    }
}
