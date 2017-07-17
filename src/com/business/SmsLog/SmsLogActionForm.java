package com.business.SmsLog; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class SmsLogActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String logIp;
    private String logMsg;
    private String logCaptcha;
    private int addTime;
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
    public int getAddTime(){
      return addTime;
    }
    public void setAddTime(int addTime){
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
}

