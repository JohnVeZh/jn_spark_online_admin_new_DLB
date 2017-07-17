package com.business.apktrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;

public class ApktrackMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(ApktrackMgr.class);

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> apktrackData(String startTime, String endTime, List<String> areaList) {
		logger.debug("APP流量统计...");
		Set<String> areas = new HashSet<String>();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        
        // 日期查询
        StringBuffer condition = new StringBuffer();
        if(startTime!=null && Pattern.matches("\\d{4}-\\d{2}-\\d{2}", startTime)) {
        	condition.append(" t.time>='").append(startTime).append(" 00:00:00").append("' and ");
        }
        if(endTime!=null && Pattern.matches("\\d{4}-\\d{2}-\\d{2}", endTime)) {
        	condition.append(" t.time<='").append(endTime).append(" 23:59:59").append("' and ");
        }
        
        // 渠道列表
        List<Map<String, String>> channelList = Tool.query("select t.`name`, t.`value` from sys_config t where t.type='APK_DOWNLOAD_CHANNEL' order by t.sort");
        for(Map<String, String> c : channelList) {
        	String channelId = c.get("VALUE");
            Map<String, Object> channel = new HashMap<String, Object>();
            channel.put("code", channelId);
            channel.put("name", c.get("NAME"));
            // 总访问量
            List<Map<String, Object>> list1 = Tool.query("select COUNT(t.ip) pv, COUNT(DISTINCT t.ip) uv from apk_download_track t where " + condition + " t.channel_id='" + channelId + "'");
            channel.put("PV", list1.get(0).get("PV"));
            channel.put("UV", list1.get(0).get("UV"));
            // 渠道引流
            List<Map<String, Object>> list2 = Tool.query("select t.os_type, COUNT(t.os_type) num from apk_download_track t where " + condition + " t.channel_id='" + channelId + "' group by t.os_type");
            for(Map<String, Object> e : list2) {
            	String os = (String) e.get("OS_TYPE");
            	long count = (Long) e.get("NUM");
            	if("1".equals(os)) {
            		channel.put("OS_1", count);	// Android
            	} else if("2".equals(os)) {
            		channel.put("OS_2", count);	// iOS
            	} else if("3".equals(os)) {
            		channel.put("OS_3", count);	// PC
            	} else {
            		if(channel.containsKey("OS_")) {
            			count += (Long) channel.get("OS_");
            		}
            		channel.put("OS_", count);	// 其他
            	}
            }
            // 地区引流
            List<Map<String, Object>> list3 = Tool.query("select t.ip_province, COUNT(t.ip) pv, COUNT(DISTINCT t.ip) uv from apk_download_track t where " + condition + " t.channel_id='" + channelId + "' group by t.ip_province");
            for(Map<String, Object> e : list3) {
            	String area = (String) e.get("IP_PROVINCE");
            	long pv = (long) e.get("PV");
            	long uv = (long) e.get("UV");
            	if(StringUtils.isNotBlank(area)) {
            		areas.add(area);
                    channel.put("PV_" + area, pv);	// 北京pv
                    channel.put("UV_" + area, uv);	// 北京uv
            	} else {
            		if(channel.containsKey("PV_")) {
            			pv += (Long) channel.get("PV_");
            		}
                    channel.put("PV_", pv);	// 其他pv
            		if(channel.containsKey("UV_")) {
            			uv += (Long) channel.get("UV_");
            		}
                    channel.put("UV_", uv);	// 其他uv
            	}
            }
            dataList.add(channel);
        }
        
        // 地区列表
        areaList.addAll(areas);
        Collections.sort(areaList);
        
        return dataList;
	}
}
