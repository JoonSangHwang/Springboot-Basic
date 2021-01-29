package com.joonsang.example.SpringbootBasic;

import com.joonsang.example.SpringbootBasic.init.JoonsangProperties;
import com.joonsang.example.SpringbootBasic.listener.SampleListener;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(JoonsangProperties.class)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})	// 생략 가능
public class SpringbootBasicApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(SpringbootBasicApplication.class);
		springApplication.addListeners(new SampleListener());
		springApplication.run(args);

//		SpringApplication.run(SpringbootBasicApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
