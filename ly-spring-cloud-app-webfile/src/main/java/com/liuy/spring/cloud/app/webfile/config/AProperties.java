package com.liuy.spring.cloud.app.webfile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="app-webfile.test")
@RefreshScope
public class AProperties {
	private String a;
	private String b;
	private String c;
}	
