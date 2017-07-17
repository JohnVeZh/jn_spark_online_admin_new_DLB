package com.business.WebConfig; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class WebConfigActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String opetime;
    private String configContent;
    private String id;
    private String type;
    private String configName;

    public String getOpetime(){
      return opetime;
    }
    public void setOpetime(String opetime){
      this.opetime = opetime;
    }
    public String getConfigContent(){
      return configContent;
    }
    public void setConfigContent(String configContent){
      this.configContent = configContent;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getType(){
      return type;
    }
    public void setType(String type){
      this.type = type;
    }
    public String getConfigName(){
      return configName;
    }
    public void setConfigName(String configName){
      this.configName = configName;
    }
}

