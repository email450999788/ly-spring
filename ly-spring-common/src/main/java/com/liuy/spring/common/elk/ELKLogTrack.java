package com.liuy.spring.common.elk;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ELKLogTrack {
	/**
	 * 交易名称
	 * @return
	 */
	String tranName() default "";
	/**
	 * 项目编号
	 * @return
	 */
	String projNo() default "";
	/**
	 * 项目名称
	 * @return
	 */
	String projName() default "";
	/**
	 * 业务号来源
	 * @return
	 */
	String[] busIdFrom() default "";
}
