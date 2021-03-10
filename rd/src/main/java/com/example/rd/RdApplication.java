package com.example.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RdApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdApplication.class, args);
	}

}
