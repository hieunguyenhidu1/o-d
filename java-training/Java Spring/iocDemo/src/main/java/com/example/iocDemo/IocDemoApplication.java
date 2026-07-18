package com.example.iocDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IocDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(IocDemoApplication.class, args);
//		EmailService emailService = new EmailService();
//		Client client = new Client(emailService);
//		client.processService("Hello world");
		EmailService emailService = context.getBean(EmailService.class);
		emailService.sendMessage("ABC");

		Client client = context.getBean(Client.class);
		client.processService("ABC");
	}

}
