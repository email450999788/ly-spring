package com.liuy.spring.zipkin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan("com.liuy")
@RestController
@Slf4j
public class LySpringZipkinClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LySpringZipkinClientApplication.class, args);
	}
	
	@RequestMapping(path="say", method=RequestMethod.GET)
	public String say(@RequestParam("v") String v) {
		log.info("you say：{}",v);
		return "you say："+v ;
	}
	
	@RequestMapping(path="random", method=RequestMethod.GET)
	public String random() {
		double d = Math.random() ;
		log.info("random：{}",d);
		return "random："+d ;
	}

}
