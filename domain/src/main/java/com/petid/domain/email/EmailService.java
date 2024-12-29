package com.petid.domain.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailSenderAddr;

    private final JavaMailSender mailSender;

    public void sendEmail(String subject, String text) {
        /*
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailSenderAddr);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
 */
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(text, true); // true indicates HTML content            
            helper.setTo(emailSenderAddr);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
        
    }

    
}
