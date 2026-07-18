package com.example.springEntity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringEntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEntityApplication.class, args);
	}

}
