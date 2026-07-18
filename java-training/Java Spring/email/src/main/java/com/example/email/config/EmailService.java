package com.example.email.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hieu2003fca2@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("Mail sent successfully!");
    }

    public void sendMimeMessage(
            String toEmail,
            String subject,
            String body,
            String attachmentPath) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("hieu2003fca2@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true); // true = gửi HTML

        // Thêm file đính kèm nếu có
        if (attachmentPath != null && !attachmentPath.isEmpty()) {
            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            helper.addAttachment(file.getFilename(), file);
        }

        mailSender.send(mimeMessage);
        System.out.println("MimeMessage sent successfully!");
    }

    public void sendEmailWithThymeleaf(String to, String subject, String name, String content) throws MessagingException {
        // 1️⃣ Tạo context Thymeleaf
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("content", content);

        // 2️⃣ Render HTML
        String htmlContent = templateEngine.process("email-template", context);

        // 3️⃣ Gửi mail với MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("hieu2003fca2@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true = HTML

        mailSender.send(message);
    }
}
