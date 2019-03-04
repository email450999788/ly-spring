package com.liuy.spring.common.elk;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	
	@Pointcut("@annotation(eTimePrint)")
	public void pointCut(ETimePrint eTimePrint) {} 
	
	/**
	 * @param pjp
	 * @throws Throwable
	 */
	@Before(value="pointCut(eTimePrint)", argNames="eTimePrint")
	public void before(JoinPoint jp, ETimePrint eTimePrint) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest() ;
		log.info("请求路径:"+request.getRequestURL());
		long curTime = System.currentTimeMillis() ;
		request.setAttribute("begin_nao_time", curTime); 
		log.info("请求开始时间:"+DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(Calendar.getInstance()));
	}
	
	/**
	 * @param pjp
	 * @throws Throwable
	 */
	@After(value="pointCut(eTimePrint)", argNames="eTimePrint")
	public void after(JoinPoint jp, ETimePrint eTimePrint) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest() ;
		Long begin_nao_time =  (Long) request.getAttribute("begin_nao_time"); 
		long curTime = System.currentTimeMillis() ;
		log.info("请求结束时间:"+DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(Calendar.getInstance()));
        log.info("请求耗时:"+(curTime-begin_nao_time)+"ms");
	}
}
