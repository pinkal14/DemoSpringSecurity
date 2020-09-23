package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class DemoSpringSecurityApplication {

	public static void main(String[] args) {
		System.out.println("Rahul Is Evwrywhere");
		SpringApplication.run(DemoSpringSecurityApplication.class, args);
		System.out.println("Huula lala la al a");
		
	}

}
