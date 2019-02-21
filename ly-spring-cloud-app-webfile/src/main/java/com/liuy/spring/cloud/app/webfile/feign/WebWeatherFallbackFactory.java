package com.liuy.spring.cloud.app.webfile.feign;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * 断路器
 * @author ifre
 *
 */
@Component
public class WebWeatherFallbackFactory implements FallbackFactory<WebWeather>  {

	@Override
	public WebWeather create(Throwable cause) {
		return new WebWeather() {
			public String weather(String city) {
				return "调用改接口出现了错误："+cause.getMessage();
			}
		};
	}

}
