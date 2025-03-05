package com.mupl.engagement_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EngagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngagementServiceApplication.class, args);
	}

}
