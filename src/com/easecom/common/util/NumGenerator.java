package com.easecom.common.util;

import java.util.Date;
import java.util.Random;

/**
 * 号码产生器，用来生成各种号码，如订单号码、产品编码
 * @author 周志君
 * @created 2014-9-9 下午4:24:19
 */
public class NumGenerator {
	
	/**
	 * 生成产品编号
	 * 
	 * @auth 周志君
	 * @created 2014-9-9 下午4:25:20
	 */
	public static String productNumGenerate() {
		return new Date().getTime()+"";
	}
	
	/**
	 * 生成订单编号
	 * @return
	 * @auth 周志君
	 * @created 2014-11-17 下午8:49:42
	 */
	public static String orderFormNumGenerate() {
		return new Date().getTime()+""+new Random().nextInt(10000)+"";
	}
	
	public static void main(String [] args) {
		System.out.println(productNumGenerate());
	}

}
