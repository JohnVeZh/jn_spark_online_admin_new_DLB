/*
 * GetStringTableKey.java   2011-8-15
 * Copyright:  Copyright (c) 2011
 * Company:山东益信通科贸有限公司
 */

package com.easecom.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @Description: 获得主键值
 * @author: YuPeng
 * @version 1.0
 */
public class GetStringTableKey {
	//个数初始化为1
	private  static int  COUNT = 1;
	
	/**
	 * @Description:  生成主键值
	 * @return  
	 * @author: YuPeng
	 * @date Aug 19, 2011 11:28:55 AM
	 */
	public static synchronized String getStringTableKey() {
		COUNT ++;
		SecureRandom rand = new SecureRandom();
		List arrayList = new ArrayList();
		String id = "001";
		while(arrayList.size()<10){
			arrayList.add(rand.nextInt(100000)+"");
			id = rand.nextInt(10000000)+"";
		}
		try {
			//Thread.currentThread().sleep(2);
			//Thread.currentThread().sleep(3);
			id +=""+COUNT+new Random().nextInt(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return System.currentTimeMillis()+""+id;
	}
}
