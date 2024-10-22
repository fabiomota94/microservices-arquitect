package com.example.microservico_entry_point.service;

import com.example.microservico_entry_point.model.request.MessageRequest;
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

    public String sendRequest(MessageRequest messageRequest){
        logger.info("Tentando Enviar message {}",messageRequest);
        return emailClient.sendEmail(messageRequest);
    }
}
