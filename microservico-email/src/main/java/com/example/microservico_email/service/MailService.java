package com.example.microservico_email.service;

import com.example.microservico_email.model.request.MessageRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.sendgrid.api-key:}")
    private String apiKey;

    @Value("${application.requester.email:}")
    private String fromEmail;

    public static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public String sendEmail(MessageRequest request) {
        Email from = new Email(fromEmail, request.getRequester()); // Sender email
        String subject = "Important Message";
        Email to = new Email(request.getReceiver()); // Email recipient
        Content content = new Content("text/plain", request.getMessage());

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request sgRequest = new Request();

        try {
            sgRequest.setMethod(Method.POST);
            sgRequest.setEndpoint("mail/send");
            sgRequest.setBody(mail.build());
            com.sendgrid.Response response = sg.api(sgRequest);

            logger.info("SendGrid Response: Status Code: {}, Body: {}, Headers: {}",
                    response.getStatusCode(), response.getBody(), response.getHeaders());

            return "Email sent successfully!";
        } catch (Exception ex) {
            logger.error("Error sending email: {}", ex.getMessage(), ex);
            return "Error sending email";
        }
    }
}
