package com.liuy.spring.common.hive;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.hive.jdbc.HiveDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConditionalOnClass(HiveDriver.class)
@Configuration
public class HiveJdbcTemplateConfiguration {
	
	@Autowired HiveJdbcProperites hiveJdbcP ;
	
	@Bean(name = "hiveDataSource")
	public DataSource hiveDataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName(hiveJdbcP.getDriverClassName());
		datasource.setUrl(hiveJdbcP.getUrl());
		datasource.setUsername(hiveJdbcP.getUsername());
		datasource.setPassword(hiveJdbcP.getPassword());
		datasource.setInitialSize(hiveJdbcP.getInitialSize());
		datasource.setMinIdle(hiveJdbcP.getMinIdle());
		datasource.setMaxActive(hiveJdbcP.getMaxActive());
		datasource.setMaxWait(hiveJdbcP.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(hiveJdbcP.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(hiveJdbcP.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(hiveJdbcP.getValidationQuery());
		datasource.setTestWhileIdle(hiveJdbcP.isTestWhileIdle());
		datasource.setTestOnBorrow(hiveJdbcP.isTestOnBorrow());
		datasource.setTestOnReturn(hiveJdbcP.isTestOnReturn());
		datasource.setPoolPreparedStatements(hiveJdbcP.isPoolPreparedStatements());
		datasource.setMaxPoolPreparedStatementPerConnectionSize(hiveJdbcP.getMaxPoolPreparedStatementPerConnectionSize());
		try {
			datasource.setFilters(hiveJdbcP.getFilters());
		} catch (SQLException e) {
			log.error("druid configuration initialization filter", e);
		}
		return datasource;
	}
	
	@Bean(name = "hiveJdbcTemplate")
	public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource) ;
	}
}
