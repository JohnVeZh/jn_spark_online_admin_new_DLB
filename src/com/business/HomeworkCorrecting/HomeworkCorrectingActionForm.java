package com.business.HomeworkCorrecting; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class HomeworkCorrectingActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int isUrgent;
    private String sysUserId;
    private String content;
    private int isCorrecting;
    private String userId;
    private String id;
    private String reviewed;
    private String hcValue;

    
    public String getHcValue() {
		return hcValue;
	}
	public void setHcValue(String hcValue) {
		this.hcValue = hcValue;
	}
	public int getIsUrgent(){
      return isUrgent;
    }
    public void setIsUrgent(int isUrgent){
      this.isUrgent = isUrgent;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public int getIsCorrecting(){
      return isCorrecting;
    }
    public void setIsCorrecting(int isCorrecting){
      this.isCorrecting = isCorrecting;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getReviewed(){
      return reviewed;
    }
    public void setReviewed(String reviewed){
      this.reviewed = reviewed;
    }
}

