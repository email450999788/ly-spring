package com.liuy.spring.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:application.properties")
@Data
public class Conf {
	private String amqName ;
}
