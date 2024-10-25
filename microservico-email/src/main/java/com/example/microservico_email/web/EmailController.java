package com.example.microservico_email.web;

import com.example.microservico_email.model.request.MessageRequest;
import com.example.microservico_email.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    MailService mailService;

    private EmailController() {
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody MessageRequest messageRequest) {
        logger.info("Message received {}", messageRequest);
        mailService.sendEmail(messageRequest);
        return ResponseEntity.ok("");
    }
}
