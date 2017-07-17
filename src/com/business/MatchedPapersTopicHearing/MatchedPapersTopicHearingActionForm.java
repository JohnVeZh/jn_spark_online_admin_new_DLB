package com.business.MatchedPapersTopicHearing; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersTopicHearingActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String hName;
    private String mpId;
    private String hContent;
    private String id;
    private int sort;
    private String subjectType;
    private int isDel;
    private String code;
    
    
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
    public String getSubjectType(){
      return subjectType;
    }
    public void setSubjectType(String subjectType){
      this.subjectType = subjectType;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
	public String gethName() {
		return hName;
	}
	public void sethName(String hName) {
		this.hName = hName;
	}
	public String gethContent() {
		return hContent;
	}
	public void sethContent(String hContent) {
		this.hContent = hContent;
	}
}

