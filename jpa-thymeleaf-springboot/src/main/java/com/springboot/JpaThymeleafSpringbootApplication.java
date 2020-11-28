package com.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaThymeleafSpringbootApplication {

	private static Logger logger = LoggerFactory.getLogger(JpaThymeleafSpringbootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaThymeleafSpringbootApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		logger.debug("application is running....!");
		return null;
	}
}
