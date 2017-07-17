package com.easecom.common.util;

import java.util.Random;

public class RandomUtil {

	/**
	 * 随机数
	 * Feb 11, 201410:45:03 AM
	 * @param digCount
	 * @return
	 */
	public static String getRandomNumber(int num) {
		Random rnd = new Random();
	    StringBuilder sb = new StringBuilder(num);  
	    for(int i=0; i < num; i++)  
	        sb.append((char)('0' + rnd.nextInt(10)));  
	    return sb.toString();  
	}
	
	/**
	 * 
	  * getRandomNumber 方法
	  * <p>方法说明:</p>
	  * @param num
	  * @return
	  * @return String
	  * @author Lly
	  * @date 2014年8月20日 下午8:39:08
	 */
	public static String getRandomNumber(String code,int num) {
		Random rnd = new Random();
	    StringBuilder sb = new StringBuilder(num);  
	    sb.append(code);
	    for(int i=0; i < num; i++)  
	        sb.append((char)('0' + rnd.nextInt(10)));  
	    return sb.toString();  
	}
	
	
}
