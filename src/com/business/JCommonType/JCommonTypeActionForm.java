package com.business.JCommonType; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JCommonTypeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String typeName;
    private String sort;
    private String id;
    private String createtime;
    private String qrCode;

    
    public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }
    public String getSort(){
      return sort;
    }
    public void setSort(String sort){
      this.sort = sort;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
}

