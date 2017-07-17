package com.business.MatchedPapersTopicTranslate; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersTopicTranslateActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String mpId;
    private String tTest;
    private String id;
    private int isDel;
    private String tContent;
    private String code;
    private String analysis;

    
    
    public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMpId(){
      return mpId;
    }
    public void setMpId(String mpId){
      this.mpId = mpId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
	public String gettTest() {
		return tTest;
	}
	public void settTest(String tTest) {
		this.tTest = tTest;
	}
	public String gettContent() {
		return tContent;
	}
	public void settContent(String tContent) {
		this.tContent = tContent;
	}
    
}

