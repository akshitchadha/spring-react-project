package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootCourseApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCourseApiGatwayApplication.class, args);
	}

}
