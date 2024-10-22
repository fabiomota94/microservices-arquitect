package com.example.microservico_entry_point.service;

import com.example.microservico_entry_point.model.request.MessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-email")
public interface EmailClient {

    @PostMapping("/email/send")
    String sendEmail(@RequestBody MessageRequest request);
}
