package com.business.OnlineUser; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class OnlineUserActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String type;
    private String ipaddress;
    private String ip;
    private String userId;
    private String userName;
    private String imei;
    private String loginPhone;
    private String id;
    private String loginTime;
    private String lastTime;

    public String getType(){
      return type;
    }
    public void setType(String type){
      this.type = type;
    }
    public String getIpaddress(){
      return ipaddress;
    }
    public void setIpaddress(String ipaddress){
      this.ipaddress = ipaddress;
    }
    public String getIp(){
      return ip;
    }
    public void setIp(String ip){
      this.ip = ip;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public String getUserName(){
      return userName;
    }
    public void setUserName(String userName){
      this.userName = userName;
    }
    public String getImei(){
      return imei;
    }
    public void setImei(String imei){
      this.imei = imei;
    }
    public String getLoginPhone(){
      return loginPhone;
    }
    public void setLoginPhone(String loginPhone){
      this.loginPhone = loginPhone;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getLoginTime(){
      return loginTime;
    }
    public void setLoginTime(String loginTime){
      this.loginTime = loginTime;
    }
    public String getLastTime(){
      return lastTime;
    }
    public void setLastTime(String lastTime){
      this.lastTime = lastTime;
    }
}

