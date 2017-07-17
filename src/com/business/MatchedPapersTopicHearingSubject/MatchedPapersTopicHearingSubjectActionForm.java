package com.business.MatchedPapersTopicHearingSubject; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersTopicHearingSubjectActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String cAnalysis;
    private String mpthtId;
    private String id;
    private int isDel;
    private int sNumber;
    private int score;
    private String url;
    private String content;
    private String code;
    
    
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getcAnalysis() {
		return cAnalysis;
	}
	public void setcAnalysis(String cAnalysis) {
		this.cAnalysis = cAnalysis;
	}
	public String getMpthtId() {
		return mpthtId;
	}
	public void setMpthtId(String mpthtId) {
		this.mpthtId = mpthtId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getsNumber() {
		return sNumber;
	}
	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

