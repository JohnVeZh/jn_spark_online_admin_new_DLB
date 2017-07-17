package com.business.MatchedPapersTopicReadSubjectChoice; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersTopicReadSubjectChoiceActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int isDel;
    private int cIsAnswer;
    private int cSort;
    private String cContent;
    private String cName;
    private String id;
    private String mptrsId;
    private String code;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMptrsId(String mptrsId) {
		this.mptrsId = mptrsId;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getcIsAnswer() {
		return cIsAnswer;
	}
	public void setcIsAnswer(int cIsAnswer) {
		this.cIsAnswer = cIsAnswer;
	}
	public int getcSort() {
		return cSort;
	}
	public void setcSort(int cSort) {
		this.cSort = cSort;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMptrsId() {
		return mptrsId;
	}
	public void setMptrId(String mptrsId) {
		this.mptrsId = mptrsId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

