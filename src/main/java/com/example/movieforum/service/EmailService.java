package com.example.movieforum.service;

import com.example.movieforum.entity.AppUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendRegistrationEmail(AppUser user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eh.test.email10@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Welcome to Movie Forum");
        helper.setText("Dear " + user.getUsername() + ",\n\n" +
                "Thank you for registering with Movie Forum.");

        mailSender.send(message);
    }
}
