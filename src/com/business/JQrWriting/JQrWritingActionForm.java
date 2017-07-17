package com.business.JQrWriting; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrWritingActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String hearUrl;
    private String title;
    private String jqrwtId;
    private String levelType;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getHearUrl(){
      return hearUrl;
    }
    public void setHearUrl(String hearUrl){
      this.hearUrl = hearUrl;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
    public String getJqrwtId(){
      return jqrwtId;
    }
    public void setJqrwtId(String jqrwtId){
      this.jqrwtId = jqrwtId;
    }
}

