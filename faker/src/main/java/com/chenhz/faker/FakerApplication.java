package com.chenhz.faker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages={"com.chenhz.faker.dao","com.chenhz.common.dao"})
@ComponentScan(basePackages={"com.chenhz.faker","com.chenhz.common"})
public class FakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakerApplication.class, args);
	}
}
