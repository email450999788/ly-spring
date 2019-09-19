package com.liuy.spring.common.elk;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * AOP
 * @author 刘洋
 *
 */
@Component
@Order(2)
@Aspect
@Slf4j
public class ELKLogTrackAspect {
	
	
	static ElkUtil elkUtil = new ElkUtil();
	
	private Object parseSpel(ExpressionParser parser, StandardEvaluationContext context, String spel, String warnMsg) {
		try {
			if(StringUtils.isNotBlank(spel)) {
				if(spel.indexOf("#") > -1) {
					return parser.parseExpression(spel).getValue(context) ;
				}
				return spel ;
			}
		}catch(ParseException e) {
			log.warn(warnMsg, e);
		}
		return "" ;
	}
		
	
	/**
	 * ELK日志配置拦截
	 * @param pjp
	 * @throws Throwable
	 */
	@Before(value="@annotation(log)", argNames="log")
	public void aopLogTrack(JoinPoint jp, ELKLogTrack logTrack){
		
		ExpressionParser parser = new SpelExpressionParser() ;
		StandardEvaluationContext context = new StandardEvaluationContext() ;
		context.setVariable("elkUtil", elkUtil);
		if(jp.getArgs() !=null ) {
			MethodSignature signature = (MethodSignature) jp.getSignature() ;
			String[] parameterNames = signature.getParameterNames() ;
			for(int i=0,l=parameterNames.length ; i<l ;i++) {
				context.setVariable(parameterNames[i], jp.getArgs()[i]);
			}
		}

		//设置业务号
		String busId = "" ;
		if(logTrack.busIdFrom()!=null && logTrack.busIdFrom().length>0) {
			StringBuilder stringBuilder = new StringBuilder() ;
			for(String fromSpel : logTrack.busIdFrom()) {
				stringBuilder.append(parseSpel(parser, context, fromSpel, "解析业务号来源busIdFrom出错")).append("_") ;
			}
			busId = stringBuilder.deleteCharAt(stringBuilder.length()-1).toString() ;
		}
		
		MDC.put("busId", busId);
		MDC.put("tranName", parseSpel(parser, context, logTrack.tranName(), "解析交易名称tranName出错").toString());
		MDC.put("projNo", parseSpel(parser, context, logTrack.projNo(), "解析项目编号projNo出错").toString());
		MDC.put("projName", parseSpel(parser, context, logTrack.projName(), "解析项目名称projName出错").toString());
	}
	
}
