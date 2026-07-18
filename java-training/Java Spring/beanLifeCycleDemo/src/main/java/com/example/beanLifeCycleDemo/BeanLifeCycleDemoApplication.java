package com.example.beanLifeCycleDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeanLifeCycleDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BeanLifeCycleDemoApplication.class, args);

		System.out.println("Sau khi run app");

		Girl girl = context.getBean(Girl.class);

		System.out.println("girl: "+ girl);

		girl.useBean();

	}

}
