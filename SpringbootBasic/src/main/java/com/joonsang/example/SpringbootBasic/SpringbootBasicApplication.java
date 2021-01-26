package com.joonsang.example.SpringbootBasic;

import com.joonsang.example.SpringbootBasic.listener.SampleListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class SpringbootBasicApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(SpringbootBasicApplication.class);
		springApplication.addListeners(new SampleListener());
		springApplication.run(args);

//		SpringApplication.run(SpringbootBasicApplication.class, args);
	}

}
