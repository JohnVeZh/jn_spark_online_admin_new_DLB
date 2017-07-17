package com.business.OnlineUser; 

public class OnlineUser extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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

/** default constructor */
	public OnlineUser() {
	}

    public OnlineUser (String type,String ipaddress,String ip,String userId,String userName,String imei,String loginPhone,String id,String loginTime,String lastTime) {
      this.type = type;
      this.ipaddress = ipaddress;
      this.ip = ip;
      this.userId = userId;
      this.userName = userName;
      this.imei = imei;
      this.loginPhone = loginPhone;
      this.id = id;
      this.loginTime = loginTime;
      this.lastTime = lastTime;
    }
}
