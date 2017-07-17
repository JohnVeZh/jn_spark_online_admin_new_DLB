package com.easecom.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

public class JsonUtils {
	private static final Log log = LogFactory.getLog(JsonUtils.class);
	
	public JsonUtils() {
		
	}
	
	/**
	 * 向客户端输出JSON
	 */
	public static void outputJson(HttpServletResponse response, JSONObject json)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
	}
	
    
    /**
     * 输出 html 信息
     * @param response
     * @param responseContent
     */
    public static void flushResponseToHtml(HttpServletResponse response,String responseContent){
	    response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(responseContent);
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
    }

	/** 
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 */

	public static Object toBean(String jsonString, Class beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Object object = JSONObject.toBean(jsonObject, beanClass);
		return object;
	}
	
	public static Object toBean(String jsonString, Class beanClass,Map map) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Object object = JSONObject.toBean(jsonObject, beanClass,map);
		return object;
	}

	/** 
	 * 从json对象集合表达式中得到一个java对象列表
	 * @param jsonString:数组字符串[{key1:value1,key2:value2},{bean2},{bean3},...]
	 * @param pojoClass
	 * @return
	 */
	public static List toList(String jsonString, Class beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Object object = JSONObject.toBean(jsonObject, beanClass);
			list.add(object);
		}
		return list;
	}
	public static List toList(String jsonString, Class beanClass,Map map) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Object object = JSONObject.toBean(jsonObject, beanClass,map);
			list.add(object);
		}
		return list;
	}

	/** 
	 * 从json字符串中获取一个map，该map支持嵌套功能
	 * @param jsonString
	 * @return
	 */
	public static Map toMap(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator it = jsonObject.keys();
		Map map = new HashMap();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = jsonObject.get(key);
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * 从json数组中得到相应java数组
	 * @param jsonString
	 * @return
	 */
	public static Object[] toObjectArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/** 
	 * 从json解析出java字符串数组
	 * @param jsonString
	 * @return
	 */
	public static String[] toStringArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}

	/** 
	 * 从json解析出javaLong型对象数组
	 * @param jsonString
	 * @return
	 */
	public static Long[] toLongArray(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Long[] longArray = new Long[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			longArray[i] = jsonArray.getLong(i);
		}
		return longArray;
	}

	/**
	 * 从json解析出java Integer型对象数组
	 * @param jsonString:[1,2,3,4]
	 * @return
	 */
	public static Integer[] toIntegerArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Integer[] integerArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			integerArray[i] = jsonArray.getInt(i);
		}
		return integerArray;
	}

	/** 
	 * 从json解析出java Date 型对象数组
	 * @param jsonString
	 * @return
	 */
	public static Date[] toDateArray(String jsonString, String DataFormat) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Date[] dateArray = new Date[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			String dateString = jsonArray.getString(i);
			Date date = DateUtils.format(dateString, DataFormat);
			dateArray[i] = date;
		}
		return dateArray;
	}

	/**
	 * 从json中解析出java Double型对象数组
	 * @param jsonString
	 * @return
	 */
	public static Double[] toDoubleArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Double[] doubleArray = new Double[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			doubleArray[i] = jsonArray.getDouble(i);
		}
		return doubleArray;
	}

	/**
	 * 将java对象转换成json字符串
	 * @param javaObj
	 * @return
	 */
	public static String toJsonString(Object object) {
		JSONObject json = JSONObject.fromObject(object);
		return json.toString();
	}

	/*
	 * 将java对象转化为json数组字符串
	 * [{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]
	 */
	public static String toJsonArrayString(List<Object> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	private String dateFormat = "yyyy-MM-dd";

    public void setDateFormat(String dateFormat){
        this.dateFormat = dateFormat;
    }

    /**
     * 格式化日期字符串
     */
    public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(false));
    }
   
    @SuppressWarnings("unchecked")
    public static void outputJsonResponse(HttpServletResponse response,boolean result){
    	JSONObject json = new JSONObject();
        json.put("result", result);
        String content=json.toString();
        flushResponse(response,content);
    }
    /*
     * {"result":true,"msg":"abc"}
     */
    @SuppressWarnings("unchecked")
    public static void outputJsonResponse(HttpServletResponse response,boolean result,String message){
    	JSONObject json = new JSONObject();
        json.put("result", result);
        json.put("message", message);
        String content=json.toString();
        flushResponse(response,content);
    }
    @SuppressWarnings("unchecked")
    public static void outputJsonResponse(HttpServletResponse response,boolean result,String message,String userData){
    	JSONObject json = new JSONObject();
        json.put("result", result);
        json.put("message", message);
        json.put("userData", userData);
        String content=json.toString();
        flushResponse(response,content);
    }
    /*
     * {"result":true,"data":{"key1":"value1","key2":"value2"}}
     */
    @SuppressWarnings("unchecked")
    public static void outputJsonResponse(HttpServletResponse response,boolean result,String message,Map userData){
    	JSONObject json = new JSONObject();
        json.put("result", result);
        json.put("message", message);
        if(userData!=null && userData.size()>0)
            json.put("userData", userData);
        String content=json.toString();
        flushResponse(response,content);
    }
    
    /*
     * 输出json数据
     * 输出格式为：{result:true,data:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
     */
    public static void outputJsonResponse(HttpServletResponse response,boolean result,List list) {
    	JSONObject json = new JSONObject();
    	json.put("result", result);
        if (list!=null && list.size()>0){
            JSONArray jsonArray = JSONArray.fromObject(list);
            json.put("data", jsonArray);
        }
        String content=json.toString();
        flushResponse(response,content);
    }
    /*
     * 输出json数据
     * 输出格式为：{result:"000",data:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
     */
    public static void outputJsonResponse(HttpServletResponse response,String result,List list) {
    	JSONObject json = new JSONObject();
    	json.put("result", result);
        if (list!=null && list.size()>0){
            JSONArray jsonArray = JSONArray.fromObject(list);
            json.put("data", jsonArray);
        }
        String content=json.toString();
        flushResponse(response,content);
    }  
    /*
     * 输出ext列表的json数据
     * 输出格式为：{totalProperty:12,root:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
     */
    public static void outputJsonDataForExt(HttpServletResponse response,int totalProperty,List list) {
    	JSONObject json = new JSONObject();
        json.put("totalProperty", totalProperty);
        if (list!=null && list.size()>0){
            JSONArray jsonArray = JSONArray.fromObject(list);
            json.put("root", jsonArray);
        }
        String content=json.toString();
        flushResponse(response,content);
    }
    /**
     * Method to flush a String as response.
     * @param response
     * @param responseContent
     * @throws IOException
     */
    public static void flushResponse(HttpServletResponse response,String responseContent){
	    response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			response.setHeader("Content-Type", "application/json");
			writer.write(responseContent);
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
    }
    /**
     * @param response
     */
    public static void outputXML(HttpServletResponse response, String xml) throws Exception {
        response.setContentType("text/xml; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(xml);
        out.flush();
    }
    
    /**
     * 属性拷贝
     */
    public void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception ex) {
			log.error("Copy property error: " + ex.toString());
		}
	}
    /**
     * 
     * @Description:转为json字符串并输入到手机端
     * @param response
     * @param obj  
     * @author: 窦恩虎
     * @date 2011-12-12 上午10:17:07
     */
    public static void outputJsonResponse(HttpServletResponse response,Object obj){
    	
    	String content = toJsonString(obj);
    	 flushResponse(response,content);
    }
	/**
	 *
	 * @Description:转为json字符串并输入到手机端
	 * @param response
	 * @param obj
	 * @author: 窦恩虎
	 * @date 2011-12-12 上午10:17:07
	 */
	public static void outputJsonArrayResponse(HttpServletResponse response,Object obj){
		String content = JSONArray.fromObject(obj).toString();
		flushResponse(response,content);
	}
    /**
     * 
     * @Description:对只有一个变量的的key-->value 形式输入到手机端  
     * @param response
     * @param varName  变量的名字
     * @param varValue  变量的值
     * @author: 窦恩虎
     * @date 2011-12-12 上午10:42:48
     */
    public static void outputJsonResponse(HttpServletResponse response,String varName,String varValue){
    	JSONObject json = new JSONObject();
        json.put(varName, varValue);
        String content=json.toString();
        flushResponse(response,content);
    }
    /**
     * 
     * @Description:  
     * @param response
     * @param result 操作是否成功
     * @param map   业务数据
     * @author: 窦恩虎
     * @date Dec 12, 2011 10:22:40 AM
     */
    public static void outputJsonResponse(HttpServletResponse response,String succeed,Map map) {
    	JSONObject json = new JSONObject();
        json.put("succeed", succeed);
        if(map!=null && map.size()>0)
            json.put("userData", map);
        String content=json.toString();
        flushResponse(response,content);
    }
    
   
}
