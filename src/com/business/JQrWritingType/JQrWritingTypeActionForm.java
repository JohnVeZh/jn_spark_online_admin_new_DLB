package com.business.JQrWritingType; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrWritingTypeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String qrCode;
    private String typeName;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }
}

