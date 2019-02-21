package com.liuy.spring.cloud.app.webweather;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableDiscoveryClient	
@EnableEurekaClient
@RestController
@RequestMapping("/base")
public class LySpringCloudAppWebweatherApplication {
	
	@RequestMapping(path="/weather", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String weather(@RequestParam String city) {
		String result = "" ;
		JSONObject resultJson = new JSONObject() ;
		String uri = String.format("https://www.tianqiapi.com/api/?version=v1&city=%s",city) ;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri) ;
		CloseableHttpResponse response = null ;
		try {
			response = httpclient.execute(httpGet);
		    System.out.println(response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity, "UTF-8") ;
		    System.out.println(result);
		    resultJson = JSON.parseObject(result) ;
		    System.out.println(resultJson);
		    ObjectMapper mapper = new ObjectMapper();
		    Object obj = mapper.readValue(result, Object.class);
		    result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		    EntityUtils.consume(entity);
		}catch(Exception e) {
			
		} finally {
			try {
				response.close();
			} catch (IOException e) {
			}
		}
		return result ;
		
    }


	public static void main(String[] args) {
		SpringApplication.run(LySpringCloudAppWebweatherApplication.class, args);
	}
	
	@EnableWebSecurity
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        	.authorizeRequests()
	        		.antMatchers("/base/**").permitAll()
	        		.antMatchers("/actuator/**").permitAll()
	        		.anyRequest().authenticated() ;
	        super.configure(http);
	    }
	}


}

