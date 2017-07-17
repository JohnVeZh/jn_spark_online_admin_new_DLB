package com.easecom.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 获取省、市、县区名称
 * @author 孙晓宇
 *Jul 9, 201311:50:15 AM
 */
public class GetAddressComponent {
	/**
	 * 根据经纬度获取省，市，县区的名称
	 * @author 孙晓宇
	 * Jul 9, 201311:51:17 AM
	 * @param ak
	 * @param latitude 
	 * @param longitude
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> getAddressComponent(String ak,String latitude,String longitude) throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		String url = String.format(
				"http://api.map.baidu.com/geocoder/v2/?ak=%s&callback=renderReverse&location=%s,%s&output=json&pois=1"
				,ak,latitude, longitude);
		String province="";
		String city="";
		String county="";
		try {
			HttpClient c = new HttpClient();
			GetMethod getMethod = new GetMethod(url);
			c.executeMethod(getMethod);
			String jsonString = getMethod.getResponseBodyAsString();
			jsonString=jsonString.substring(29, jsonString.length()-1);
			JSONObject ob=JSONObject.fromObject(jsonString);
			int status=Integer.parseInt(ob.get("status").toString());
			//如果返回正确
			if(0==status){
				JSONObject result=ob.getJSONObject("result");
				JSONObject obs=result.getJSONObject("addressComponent");
				//获取市
				city=obs.get("city").toString();
				//县区
				county=obs.get("district").toString();
				//省份
				province=obs.get("province").toString();
			}
			map.put("province", province);
			map.put("city",city);
			map.put("county", county);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}
}
