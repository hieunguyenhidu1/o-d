package com.example.email.controller;
import com.example.email.config.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

        emailService.sendSimpleEmail(to, subject, body);
        return "Email sent to " + to;
    }

    @GetMapping("/send-mime-email")
    public String sendMimeEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam(required = false) String attachmentPath) {

        try {
            emailService.sendMimeMessage(to, subject, body, attachmentPath);
            return "MimeMessage sent to " + to;
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending MimeMessage: " + e.getMessage();
        }
    }

    @GetMapping("/send-thymeleaf-email")
    public String sendThymeleafEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String name,
            @RequestParam String content) {

        try {
            emailService.sendEmailWithThymeleaf(to, subject, name, content);
            return "Gửi email bằng Thymeleaf thành công!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Lỗi: " + e.getMessage();
        }
    }
}
