package com.liuy.spring.cloud.app.webfile.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="${webweather-appname}",fallbackFactory=WebWeatherFallbackFactory.class)
public interface WebWeather {
	
	@RequestMapping(method = RequestMethod.GET, value = "/base/weather")
	String weather(@RequestParam("city") String city) ;
	
}
