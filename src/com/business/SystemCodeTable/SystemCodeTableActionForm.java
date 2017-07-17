package com.business.SystemCodeTable; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class SystemCodeTableActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String remark;
    private String characterEn;
    private String id;
    private String code;
    private String updatetime;
    private String characterCn;

    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
    }
    public String getCharacterEn(){
      return characterEn;
    }
    public void setCharacterEn(String characterEn){
      this.characterEn = characterEn;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getUpdatetime(){
      return updatetime;
    }
    public void setUpdatetime(String updatetime){
      this.updatetime = updatetime;
    }
    public String getCharacterCn(){
      return characterCn;
    }
    public void setCharacterCn(String characterCn){
      this.characterCn = characterCn;
    }
}

