package com.liuy.spring.common.elk;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP
 * @author 刘洋
 *
 */
@Component
@Order(2)
@Aspect
public class ELKLogTrackAspect {

	static ElkUtil elkUtil = new ElkUtil();
	
	/**
	 * ELK日志配置拦截
	 * @param pjp
	 * @throws Throwable
	 */
	@Before(value="@annotation(log)", argNames="log")
	public void aopLogTrack(JoinPoint jp, ELKLogTrack logTrack) throws Throwable{
		String projNo = "" ;
		String projName = "" ;
		String tranName = "" ;
		String busId = "" ;
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest() ;
		tranName = logTrack.tranName() ;
		projNo = logTrack.projNo() ;
		projName = logTrack.projName() ;
		
		if(logTrack.busIdFrom()!=null && logTrack.busIdFrom().length>0) {
			//设置业务号
			StringBuffer busIdBuffer = new StringBuffer() ;
			ExpressionParser parser = new SpelExpressionParser() ;
			StandardEvaluationContext context = new StandardEvaluationContext() ;
			context.setVariable("req", request);
			context.setVariable("elkUtil", elkUtil);
			if(jp.getArgs() !=null ) {
				MethodSignature signature = (MethodSignature) jp.getSignature() ;
				String[] parameterNames = signature.getParameterNames() ;
				for(int i=0,l=parameterNames.length ; i<l ;i++) {
					context.setVariable(parameterNames[i], jp.getArgs()[i]);
				}
			}
			for(String fromSpel : logTrack.busIdFrom()) {
				busIdBuffer.append(parser.parseExpression(fromSpel).getValue(context)).append("_") ;
			}
			busId = busIdBuffer.deleteCharAt(busIdBuffer.length()-1).toString() ;
		}
		
		MDC.put("serverName", request.getServletPath().substring(1));
		MDC.put("busId", busId.toString());
		MDC.put("tranName", tranName);
		MDC.put("projNo", projNo);
		MDC.put("projName", projName);
	}
	
}
