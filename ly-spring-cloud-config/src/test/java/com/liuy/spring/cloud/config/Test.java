package com.liuy.spring.cloud.config;

import java.util.Arrays;

public class Test {
	@org.junit.Test
	public void test() {
		int[] arrays = {4,6,7,8,10,12,15,16} ;
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
		System.out.println(maxSerialCnt);
	}
	
}
