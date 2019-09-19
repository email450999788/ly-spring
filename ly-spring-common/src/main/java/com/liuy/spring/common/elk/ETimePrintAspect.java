package com.liuy.spring.common.elk;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 打印耗时信息
 * @author hzhx
 *
 */
@Component
@Order(3)
@Aspect
@Slf4j
public class ETimePrintAspect {
	
	private static ThreadLocal<Long> currentTimeMillis = new ThreadLocal<>() ;
	
	@Pointcut("@annotation(eTimePrint)")
	public void pointCut(ETimePrint eTimePrint) {} 
	
	/**
	 * @param pjp
	 * @throws Throwable
	 */
	@Before(value="pointCut(eTimePrint)", argNames="eTimePrint")
	public void before(JoinPoint jp, ETimePrint eTimePrint) throws Throwable{
		long cur = System.currentTimeMillis() ;
		log.info("交易处理开始:{}", cur);
		currentTimeMillis.set(cur);
	}
	
	/**
	 * @param pjp
	 * @throws Throwable
	 */
	@After(value="pointCut(eTimePrint)", argNames="eTimePrint")
	public void after(JoinPoint jp, ETimePrint eTimePrint) throws Throwable{
		long cur = System.currentTimeMillis() ;
        log.info("交易处理结束:{},处理总耗时:{}ms", cur, cur-currentTimeMillis.get());
	}
}
