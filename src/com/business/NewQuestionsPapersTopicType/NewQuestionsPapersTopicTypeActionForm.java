package com.business.NewQuestionsPapersTopicType; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NewQuestionsPapersTopicTypeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String nqtpId;
    private String tUrl;
    private String id;
    private int sort;
    private String subjectType;
    private int isDel;
    private String tName;
    private String code;
    private FormFile file;
    
    
   	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public void clear() {
    	file = null;
    }
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNqtpId() {
		return nqtpId;
	}
	public void setNqtpId(String nqtpId) {
		this.nqtpId = nqtpId;
	}
	public String gettUrl() {
		return tUrl;
	}
	public void settUrl(String tUrl) {
		this.tUrl = tUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}

   
}

