package com.liuy.spring.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LySpringZipkinClient1ApplicationTests {
	
	@Autowired AmqpTemplate templdate ;

	@Test
	public void t1() {
		for(int i=0; i<1; i++) {
			templdate.convertAndSend("amq.topic", "liuy.#.read", String.format("liuy.#.read#%d", i));
		}
	}
	
	@Test
	public void t2() {
		
	}
	
	
	@Test
	public void get() {
		
	}
}
