package com.example.microservico_email.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class MessageRequest {
    @NonNull
    private String subject;
    private String receiver;
    private String message;
    private String title;
    private String requester;
}
