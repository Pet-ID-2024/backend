package com.petid.domain.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailSenderAddr;

    private final JavaMailSender mailSender;

    public void sendEmail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailSenderAddr);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
