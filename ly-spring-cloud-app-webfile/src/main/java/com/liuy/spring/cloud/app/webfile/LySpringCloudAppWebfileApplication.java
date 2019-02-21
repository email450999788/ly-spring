package com.liuy.spring.cloud.app.webfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.liuy.spring.cloud.app.webfile.config.DefaultRibbonConfig;

@SpringBootApplication
@EnableDiscoveryClient	
@EnableEurekaClient
@EnableFeignClients
@RibbonClients(defaultConfiguration=DefaultRibbonConfig.class)
@ComponentScan("com.liuy.spring.cloud.app.webfile")
public class LySpringCloudAppWebfileApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LySpringCloudAppWebfileApplication.class, args);
	}
	
}
