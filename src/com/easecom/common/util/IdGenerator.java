/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */


package com.easecom.common.util;

import java.util.UUID;
import com.easecom.common.util.StrUtils;


public class IdGenerator {
	
    //生成UUID字符串作为ID	
	public static String GenerateUUID(){
		String id="";
		String temp=UUID.randomUUID().toString();
		String[] var=StrUtils.split(temp,'-');
		for(int i=0;i<var.length;i++){
			id+=var[i];
		}
		return id;
	}
}
