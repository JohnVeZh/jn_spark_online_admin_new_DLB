package com.business.SmsLog; 

public class SmsLog extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String logIp;
    private String logMsg;
    private String logCaptcha;
    private String addTime;
    private String logPhone;
    private String id;
    private String memberName;
    private String memberId;
    private String logType;

    public String getLogIp(){
      return logIp;
    }
    public void setLogIp(String logIp){
      this.logIp = logIp;
    }
    public String getLogMsg(){
      return logMsg;
    }
    public void setLogMsg(String logMsg){
      this.logMsg = logMsg;
    }
    public String getLogCaptcha(){
      return logCaptcha;
    }
    public void setLogCaptcha(String logCaptcha){
      this.logCaptcha = logCaptcha;
    }
    public String getAddTime(){
      return addTime;
    }
    public void setAddTime(String addTime){
      this.addTime = addTime;
    }
    public String getLogPhone(){
      return logPhone;
    }
    public void setLogPhone(String logPhone){
      this.logPhone = logPhone;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getMemberName(){
      return memberName;
    }
    public void setMemberName(String memberName){
      this.memberName = memberName;
    }
    public String getMemberId(){
      return memberId;
    }
    public void setMemberId(String memberId){
      this.memberId = memberId;
    }
    public String getLogType(){
      return logType;
    }
    public void setLogType(String logType){
      this.logType = logType;
    }

/** default constructor */
	public SmsLog() {
	}

    public SmsLog (String logIp,String logMsg,String logCaptcha,String addTime,String logPhone,String id,String memberName,String memberId,String logType) {
      this.logIp = logIp;
      this.logMsg = logMsg;
      this.logCaptcha = logCaptcha;
      this.addTime = addTime;
      this.logPhone = logPhone;
      this.id = id;
      this.memberName = memberName;
      this.memberId = memberId;
      this.logType = logType;
    }
}
