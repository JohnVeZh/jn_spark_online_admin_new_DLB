package com.easecom.common.iospush;
import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class IosPush {
	
	/**
	 * 
	 * @param deviceToken 手机串号
	 * @param certificatePath 证书路径
	 * @param certificatePassword 证书密码
	 * @param content 推送内容
	 * @return
	 * @author 李来源
	 * Oct 12, 20133:30:11 PM
	 */
	public static boolean push(String deviceToken,String certificatePath,String certificatePassword,String content){
		try {
			  PayLoad payLoad = new PayLoad();
			  payLoad.addAlert(content);
			  payLoad.addBadge(4);
			  payLoad.addSound("default");
						
			  PushNotificationManager pushManager = PushNotificationManager.getInstance();
			  pushManager.addDevice("iPhone", deviceToken);
			  //Connect to APNs
			  String host= "gateway.push.apple.com";
			  int port = 2195;
			  pushManager.initializeConnection(host,port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			  //Send Push
			  Device client = pushManager.getDevice("iPhone");
			  pushManager.sendNotification(client, payLoad);
			  pushManager.stopConnection();
			  pushManager.removeDevice("iPhone");
			  System.out.println("推送成功....");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) throws Exception {
	//	push("4d6c39e5402eac6617e899854fe6a7166efa318e2158910ccd19b0ffc003f5e8","d:/liangshanjiadianhuiyuanPUSH.p12","123","推送内容.....");
		push("4028819641abc7830141abc7b5ac0001", "d:/liangshanjiadiandianpuPUSH.p12", "123", "店铺推送...");
	}
}
