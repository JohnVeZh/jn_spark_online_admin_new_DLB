package com.business.JQrFragmentation; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrFragmentationActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String qrCode;
    private String id;
    private String name;
    private String hearUrl;

    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public String getHearUrl(){
      return hearUrl;
    }
    public void setHearUrl(String hearUrl){
      this.hearUrl = hearUrl;
    }
}

