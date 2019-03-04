package com.liuy.spring.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LySpringCommonApplicationTests {
	
	@Autowired JdbcTemplate jdbcTemplate ;

	@Test
	public void selecta() {
		MDC.put("projName", "查询A表记录");
		MDC.put("projNo", "alipay");
		log.info(jdbcTemplate.queryForList("select * from a")+"") ;
		MDC.put("projNo", "huabei");
		log.info(jdbcTemplate.queryForList("select * from a")+"") ;
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
