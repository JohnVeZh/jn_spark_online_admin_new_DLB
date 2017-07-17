package com.business.PreferentialCodeUse; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class PreferentialCodeUseActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String pcgId;
    private String createtime;
    private String userId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getPcgId(){
      return pcgId;
    }
    public void setPcgId(String pcgId){
      this.pcgId = pcgId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
}

