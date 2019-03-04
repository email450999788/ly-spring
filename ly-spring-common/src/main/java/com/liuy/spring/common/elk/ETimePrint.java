package com.liuy.spring.common.elk;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 打印耗时信息
 * @author 刘洋
 *
 */
public @interface ETimePrint {
	
}
