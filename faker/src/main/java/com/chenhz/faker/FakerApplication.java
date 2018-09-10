package com.chenhz.faker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chenhz.faker.dao.mapper"})
public class FakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakerApplication.class, args);
	}
}
