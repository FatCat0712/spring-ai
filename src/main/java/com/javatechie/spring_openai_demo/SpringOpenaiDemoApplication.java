package com.javatechie.spring_openai_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class SpringOpenaiDemoApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(
				TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
		);
		SpringApplication.run(SpringOpenaiDemoApplication.class, args);
	}

}
