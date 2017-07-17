package com.business.JQrCode; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrCodeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String code;
    private String type;
    private String id;
    private int targetType;

    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getType(){
      return type;
    }
    public void setType(String type){
      this.type = type;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getTargetType(){
      return targetType;
    }
    public void setTargetType(int targetType){
      this.targetType = targetType;
    }
}

