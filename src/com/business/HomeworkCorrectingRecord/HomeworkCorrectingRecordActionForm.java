package com.business.HomeworkCorrectingRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class HomeworkCorrectingRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String cTest;
    private String cContent;
    private String id;
    private String hcId;

   
    public String getcTest() {
		return cTest;
	}
	public void setcTest(String cTest) {
		this.cTest = cTest;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getHcId(){
      return hcId;
    }
    public void setHcId(String hcId){
      this.hcId = hcId;
    }
}

