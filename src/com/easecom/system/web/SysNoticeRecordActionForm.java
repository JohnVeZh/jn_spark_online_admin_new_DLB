package com.easecom.system.web; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class SysNoticeRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String state;
    private String sysUserId;
    private String sysNoticeId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getState(){
      return state;
    }
    public void setState(String state){
      this.state = state;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getSysNoticeId(){
      return sysNoticeId;
    }
    public void setSysNoticeId(String sysNoticeId){
      this.sysNoticeId = sysNoticeId;
    }
}

