package com.liuy.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan("com.liuy.spring")
public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@EnableWebSecurity
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().ignoringAntMatchers("/eureka/**");
	        super.configure(http);
	    }
	}

}

