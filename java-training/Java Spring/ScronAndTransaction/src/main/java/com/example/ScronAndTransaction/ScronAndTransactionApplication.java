package com.example.ScronAndTransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScronAndTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScronAndTransactionApplication.class, args);
	}

}
