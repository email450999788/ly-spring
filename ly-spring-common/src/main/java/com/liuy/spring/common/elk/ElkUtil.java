package com.liuy.spring.common.elk;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class ElkUtil{
    public static String xmlEle(String context, String eleName) {
    	if(StringUtils.isNotBlank(context)) {
    		int startIdx = context.indexOf("<"+eleName+">") ;
    		int endIdx = context.indexOf("</"+eleName+">") ;
    		if(startIdx != -1 && endIdx != -1 ) {
    			return context.substring(startIdx+eleName.length()+2, endIdx) ;
    		}
    	}
    	return "" ;
    }
    
    public static String jsonEle(String context, String eleName) {
    	if(StringUtils.isNotBlank(context)) {
    		return JSON.parseObject(context).getString(eleName) ;
    	}
    	return "" ;
    }
    
}
