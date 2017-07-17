package com.business.MatchedPapersTopicRead; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersTopicReadActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String mpId;
    private String id;
    private String rName;
    private String rContent;
    private int sort;
    private int isDel;
    private String code;
    private String subjectType;

    
    public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
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
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
    
}

