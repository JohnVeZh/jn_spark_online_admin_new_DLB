package com.business.PushMessageUser; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class PushMessageUserActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int isLook;
    private int id;
    private String messageId;
    private String userId;
    private String createtime;
    

    public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getIsLook(){
      return isLook;
    }
    public void setIsLook(int isLook){
      this.isLook = isLook;
    }
    public int getId(){
      return id;
    }
    public void setId(int id){
      this.id = id;
    }
    public String getMessageId(){
      return messageId;
    }
    public void setMessageId(String messageId){
      this.messageId = messageId;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
}

