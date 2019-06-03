package com.liuy.spring.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableRabbit
@ComponentScan("com.liuy")
@Slf4j
public class LySpringRabbitmqApplication {
	
	
	@Autowired ApplicationContext ctx ;

	public static void main(String[] args) {
		SpringApplication.run(LySpringRabbitmqApplication.class, args);
	}
	
	
	@Bean
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer  configurer,ConnectionFactory connectionFactory) {
      SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
      factory.setConnectionFactory(connectionFactory);
      factory.setMaxConcurrentConsumers(5);
      factory.setConcurrentConsumers(5);
      configurer.configure(factory, connectionFactory);
      return factory;
    }
	
	@RabbitListener(containerFactory="myRabbitListenerContainerFactory", queues="${amqName}")
//	@RabbitListener(containerFactory="myRabbitListenerContainerFactory", queues="#{conf.amqName}")
    public void process(String msg) {
		log.info("thread_id={}, thread_nm={}, msg={}", Thread.currentThread().getId(), Thread.currentThread().getName(), msg);
    }


}
