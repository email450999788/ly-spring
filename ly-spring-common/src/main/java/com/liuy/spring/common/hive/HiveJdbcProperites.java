package com.liuy.spring.common.hive;

import org.apache.hive.jdbc.HiveDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConditionalOnClass(HiveDriver.class)
@Configuration
@ConfigurationProperties(prefix="spring.datasource.hive")
public class HiveJdbcProperites {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private int initialSize;
	private int minIdle;
	private int maxActive;
	private int maxWait;
	private int timeBetweenEvictionRunsMillis;
	private int minEvictableIdleTimeMillis;
	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;
	private String filters;
}
