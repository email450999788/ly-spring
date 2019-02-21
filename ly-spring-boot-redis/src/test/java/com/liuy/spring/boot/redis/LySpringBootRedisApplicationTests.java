package com.liuy.spring.boot.redis;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LySpringBootRedisApplicationTests {

	@Autowired
    StringRedisTemplate redisTemplate;
    
    ValueOperations<String, String> stringRedis;
    
    @PostConstruct
    public void init(){
    	System.out.println(redisTemplate.getStringSerializer());
        stringRedis=redisTemplate.opsForValue();
    }
    
    
    @Test
    public void setname (){
        stringRedis.set("name", "My name is 刘 x");
    }
    
    @Test
    public void seta1000 (){
    	for(int i=1;i<=1000;i++)
    		stringRedis.set("name"+i, "name"+i+": My name is 刘 x");
    }
    
    @Test
    public void getname (){
        System.out.println(stringRedis.get("name"));
    }
    
    @Test
    public void geta1000 (){
    	for(int i=1;i<=1000;i++) {
    		System.out.println("name"+i+"="+stringRedis.get("name"+i));
    	}
    		
    }
    
    @Test
    public void deleteName() {
    	redisTemplate.delete("name") ;
    }
    
    @Test
    public void deletea1000 (){
    	for(int i=1;i<=1000;i++) {
    		redisTemplate.delete("name"+i) ;
    	}
    		
    }
    
    @Test
    public void geta(){
        System.out.println(stringRedis.get("a"));
    }

}
