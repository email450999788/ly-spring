package com.liuy.spring.cloud.app.webfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liuy.spring.cloud.app.webfile.config.AProperties;
import com.liuy.spring.cloud.app.webfile.feign.WebWeather;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/base")
@Slf4j
public class WebFileRestController {
	
	@Autowired private WebWeather weather ;
	@Autowired AProperties a ;
	
	@RequestMapping(path="/w", method=RequestMethod.GET)
	public String w() {
		String str = weather.weather("杭州") ;
		log.info(str);
		return weather.weather("杭州") ;
	}
	
	@RequestMapping("/home")
	public String home() {
		return a.toString() ;
	}
	
	@RequestMapping("/sleep")
	public String sleep(@RequestParam("second") Integer second) {
		try {
			log.info("我要开始午睡了");
			Thread.sleep(second * 1000);
			log.info("午睡结束：一共午睡了{}秒",second);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "我睡醒了" ;
	}
}
