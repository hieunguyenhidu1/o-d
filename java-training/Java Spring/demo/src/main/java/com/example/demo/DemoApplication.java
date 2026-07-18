package com.example.demo;

import com.example.demo.constant.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Server is on: http://localhost:8080");
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User user = User.builder()
						.username("admin")
						.password(encoder.encode("123456"))
						.roles(List.of(Role.ROLE_ADMIN))
						.enabled(true)
						.build();
				userRepository.save(user);
				System.out.println("✅ Admin user created: admin / 123456");
			}
		};
	}

}
