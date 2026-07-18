package com.example.beanDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeanDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BeanDemoApplication.class, args);
		Boy boy = context.getBean(Boy.class);
		System.out.println("Boy: " + boy);
		boy.useObjectMapper();
//
//		//Scope
//		Boy boy2 = context.getBean(Boy.class);
//		System.out.println("Boy2: " + boy2);
//		Boy boy3 = context.getBean(Boy.class);
//		System.out.println("Boy3: " + boy3);
	}

}
