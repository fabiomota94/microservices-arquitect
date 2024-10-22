package com.example.microservico_entry_point.web;

import com.example.microservico_entry_point.model.request.MessageRequest;
import com.example.microservico_entry_point.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    SendService sendService;

    // Define o endpoint POST
    @PostMapping
    public ResponseEntity<String> createRequest(@RequestBody MessageRequest messageRequest) {
        logger.info("Pedido recebido {}",messageRequest);
        String result = sendService.sendRequest(messageRequest);
        return new ResponseEntity<>("Request created successfully!", HttpStatus.CREATED);
    }
}
