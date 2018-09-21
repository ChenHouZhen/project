package com.chenhz.tpos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages={"com.chenhz.tpos"})
@ComponentScan(basePackages={"com.chenhz.tpos"})
public class TposApplication {

	public static void main(String[] args) {
		SpringApplication.run(TposApplication.class, args);
	}
}
