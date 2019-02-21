package com.liuy.spring.cloud.app.webfile.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;

/**
 * ribbon负载均衡策略
 * @return
 */
@Configuration
public class DefaultRibbonConfig {
	
	public DefaultRibbonConfig() {}

	@Bean
	public IRule ribbonRule() {
		return new BestAvailableRule();
	}


}