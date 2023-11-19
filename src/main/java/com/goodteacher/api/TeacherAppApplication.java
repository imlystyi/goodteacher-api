package com.goodteacher.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// todo: security configuration
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TeacherAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(TeacherAppApplication.class, args);
	}
}
