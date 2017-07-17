package com.easecom.common.util;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.easecom.common.util.DictionaryUtils;

public class CommUtil {
	/**
	 * @Title: isObject
	 * @author:tuoyan_sxy
	 * @Description: 判断对象是否为空
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isObject(Object obj) {
		boolean flag = false;
		if (!"".equals(obj) && null != obj) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @Title: isString
	 * @author:tuoyan_sxy
	 * @Description: 判断字符串如果为null则返回空字符串
	 * @return String 返回类型
	 * @throws
	 */
	public static String isString(String str) {
		String s = "";
		if (!"".equals(str) && null != str && !"null".equals(str)) {
			s = str;
		}
		return s;
	}

	/**
	 * @Title: isObject
	 * @author:tuoyan_sxy
	 * @Description: 判断对象是否为空和size是否大于0
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isObject(List obj) {
		boolean flag = false;
		if (!"".equals(obj) && null != obj && obj.size() > 0) {
			flag = true;
		}
		return flag;
	}


	/**
	 * @Title: isImgString
	 * @author:tuoyan_sxy
	 * @Description: 判断图片字段是否为空，如果不为空则添加系统路径
	 * @return String 返回类型
	 * @throws
	 */
	public static String isPhoneString(String str) {
		String s = "";
		if (!"".equals(str) && null != str) {
			s = DictionaryUtils.WEB_PATH + str;
		}
		return s;
	}

	/**
	 * @Description: 字符串转换
	 * @return String
	 * @author sdty_sunxiaoyu
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 * @date 2016-1-22
	 */
	public static String getContent(Blob content)
			throws UnsupportedEncodingException, SQLException {
		String blobString = "";
		if (!"".equals(content) && null != content && content.length() > 0) {
			blobString = new String(
					content.getBytes(1, (int) content.length()), "UTF-8");
		}
		return blobString;
	}

	/**
	 * @Description: 字符串获取blob
	 * @return Blob
	 * @author sdty_sunxiaoyu
	 * @date 2016-1-23
	 */
	public static Blob getBlob(String content) throws SerialException,
			UnsupportedEncodingException, SQLException {
		Blob b = null;
		if (!"".equals(content) && null != content) {
			// String 转 blob
			b = new SerialBlob(content.getBytes("UTF-8"));
		}
		return b;
	}

	/**
	 * @Title: toInt
	 * @author:tuoyan_sxy
	 * @Description: 字符串转INT
	 * @return String 返回类型
	 * @throws
	 */
	public static int toInt(String str) {
		int s = 0;
		if (!"".equals(str) && null != str) {
			s = Integer.valueOf(str);
		}
		return s;
	}
	
	/**
	 * @param 判断一个字符串的首字符是否为字母
	 * @return
	 */
	public static boolean isEnglish(String s) {
		char c = s.charAt(0);
		int i = (int) c;
		if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 验证时间是否在某时间内
	 * @param currentTime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean between(long currentTime, long  startTime, long endTime) {

		if(currentTime >= startTime && currentTime <= endTime){
			return true;
		}else {
			return false;
		}

	}
}
