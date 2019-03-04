package com.liuy.spring.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.liuy.spring.common")
public class LySpringCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(LySpringCommonApplication.class, args);
	}

}
