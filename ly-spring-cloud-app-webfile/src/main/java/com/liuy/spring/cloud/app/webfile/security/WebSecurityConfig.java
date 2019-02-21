package com.liuy.spring.cloud.app.webfile.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/base/**").permitAll()
        		.antMatchers("/page/**").permitAll()
        		.antMatchers("/actuator/info").permitAll()
        		.antMatchers("/actuator/health").permitAll()
        		.anyRequest().authenticated()
        	.and().csrf().ignoringAntMatchers("/actuator/**");
        super.configure(http);
    }
}
