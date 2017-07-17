package com.easecom.common.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class JPushClient {
	private static String HEADER_AUTHOR;
	private static String URL = "https://api.jpush.cn/v3/push";
	private static String APPKEY = DictionaryUtils.APPKEY;
	private static String MASTERSECRET = DictionaryUtils.MASTERSECRET;
	private static int ANDROID_BUILDERID = 1;							// 通知栏样式ID		Android自定义
	private static String IOS_SOUND = "WindowsLogonSound.wav";			// 通知提示声音
	private static boolean IOS_PRODUCTION = true;						// true表示推送生产环境
	
	public static void main(String[] args) {
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("id", "xxx");
		extras.put("type", "1");
		System.out.println(push("测试", "hello world!", extras));
	}
	
	public static String push(String title, String alert) {
		return push(null, null, title, alert, null);
	}
	
	public static String push(String title, String alert, Map<String, Object> extras) {
		return push(null, null, title, alert,  extras);
	}
	
	public static String push(List<String> tag, List<String> alias, String title, String alert, Map<String, Object> extras) {
		if(HEADER_AUTHOR == null) {
			String secret = APPKEY + ":" + MASTERSECRET;
			HEADER_AUTHOR = "Basic " + Base64.getEncoder().encodeToString(secret.getBytes());
		}
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", HEADER_AUTHOR);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("platform", "all");
		
		Map<String, List<String>> audience = new HashMap<String, List<String>>();
		if(tag!=null && !tag.isEmpty()) {
			audience.put("tag", tag);
		}
		if(alias!=null && !alias.isEmpty()) {
			audience.put("alias", alias);
		}
		body.put("audience", audience.isEmpty() ? "all" : audience);
		
		Map<String, Object> notification = new HashMap<String, Object>();
		// Android
		Map<String, Object> android = new HashMap<String, Object>();
		android.put("title", title);
		android.put("alert", alert);
		android.put("builder_id", ANDROID_BUILDERID);
		if(extras!=null && !extras.isEmpty()) {
			android.put("extras", extras);
		}
		notification.put("android", android);
		// iOS
		Map<String, Object> ios = new HashMap<String, Object>();
		ios.put("alert", alert);
		ios.put("sound", IOS_SOUND);
		if(extras!=null && !extras.isEmpty()) {
			ios.put("extras", extras);
		}
		notification.put("ios", ios);
		// winphone
		Map<String, Object> winphone = new HashMap<String, Object>();
		winphone.put("title", title);
		winphone.put("alert", alert);
		if(extras!=null && !extras.isEmpty()) {
			winphone.put("extras", extras);
		}
		notification.put("winphone", winphone);
		body.put("notification", notification);
		// options
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("apns_production", IOS_PRODUCTION);
		body.put("options", options);
		
		return doPost(URL, headers, JsonUtils.toJsonString(body), "utf-8");
	}
	
	private static String doPost(String url, Map<String, String> headers, String body, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			if(headers!=null && !headers.isEmpty()) {
				for(Map.Entry<String, String> head : headers.entrySet()) {
					httpPost.addHeader(head.getKey(), head.getValue());
				}
			}
			if (StringUtils.isNotBlank(body)) {
				httpPost.setEntity(new StringEntity(body, charset));
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static class SSLClient extends DefaultHttpClient {
		public SSLClient() throws Exception {
			super();
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = this.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
		}
	}
}
