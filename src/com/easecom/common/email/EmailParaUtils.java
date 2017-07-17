/**
 * EmailParaUtils.java   2012-2-27 下午06:03:28
 * Copyright:  Copyright (c) 2011
 * Company:山东益信通科贸有限公司
 */

package com.easecom.common.email;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Description 邮件参数助手类
 *
 * @author Administrator
 * @date 2012-2-27 下午06:03:28
 */

public class EmailParaUtils {
	public static Map cacheMap = new HashMap();
	
	public static String getValue(String name){
		if(cacheMap.isEmpty()){
			getFromDb();
		}
		if(cacheMap.containsKey(name)){
			return (String)cacheMap.get(name);
		}
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public static void getFromDb(){
		EmailParaMgr spMgr = new EmailParaMgr();
		EmailPara EmailPara = spMgr.getEmailPara();
		if(EmailPara.getOid()!=null&&!"".equals(EmailPara.getOid())){
			cacheMap.put("tfrom", EmailPara.getTfrom());
			cacheMap.put("smtpHost", EmailPara.getSmtpHost());
			cacheMap.put("smtpPort", EmailPara.getTport());
			cacheMap.put("pop3Host", EmailPara.getPop3Host());
			cacheMap.put("pop3Port", EmailPara.getPport());
			cacheMap.put("needAuth", EmailPara.getNeedAuth());
			cacheMap.put("isDebug", EmailPara.getIsDebug());
			cacheMap.put("tprotocol", EmailPara.getTprotocol());
			cacheMap.put("pprotocol", EmailPara.getPprotocol());
			cacheMap.put("username", EmailPara.getUsername());
			cacheMap.put("password", EmailPara.getPassword());
			cacheMap.put("agentip", EmailPara.getAgentip());
			cacheMap.put("agentport", EmailPara.getAgentport());
		}		
	}
}
