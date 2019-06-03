package com.liuy.spring.common.util;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 数字工具类
 * @author 刘洋
 *
 */
public class NumberUtil {
	
	
	/**
	 *获取 最大连续次数
	 * @param arrays
	 * @return
	 */
	public static int maxSerialCount(int[] arrays) {
		Arrays.sort(arrays);
		int maxSerialCnt = 1;
		int tmp = 1;
		for(int i=1,l=arrays.length ; i<l  ;i++) {
			if(arrays[i-1]+1 == arrays[i]) {
				tmp ++;
			}else {
				tmp = 1;
			}
			maxSerialCnt = tmp>maxSerialCnt ? tmp : maxSerialCnt;
		}
		return maxSerialCnt;
	}
	
	/**
            * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(BigDecimal value, int digit) {
        BigDecimal result = value.multiply(new BigDecimal(100));
        return getRoundUp(result, digit);
    }
    
    /**
     * 获取百分比（乘100）
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getPercentValue(double value, int digit) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, digit);
    }

    /**
     * 获取百分比（乘100,保留两位小数）
     *
     * @param value 数值
     * @return
     */
    public static String getPercentValue(double value) {
        BigDecimal result = new BigDecimal(value);
        return getPercentValue(result, 2);
    }
    
    
    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(String value, int digit) {
        BigDecimal result = new BigDecimal(Double.parseDouble(value));
        return result.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(double value, int digit) {
        BigDecimal result = new BigDecimal(value);
        return result.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * 四舍五入
     *
     * @param value 数值
     * @param digit 保留小数位
     * @return
     */
    public static String getRoundUp(BigDecimal value, int digit) {
        return value.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
    }
}
