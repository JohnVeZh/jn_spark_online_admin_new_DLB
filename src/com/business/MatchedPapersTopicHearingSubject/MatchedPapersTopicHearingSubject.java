package com.business.MatchedPapersTopicHearingSubject; 

public class MatchedPapersTopicHearingSubject extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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

/** default constructor */
	public MatchedPapersTopicHearingSubject() {
	}

	public MatchedPapersTopicHearingSubject(String code,String cAnalysis, String mpthId,
		String id, int isDel, int sNumber, int score, String url, String content) {
	super();
	this.cAnalysis = cAnalysis;
	this.mpthtId = mpthtId;
	this.id = id;
	this.isDel = isDel;
	this.sNumber = sNumber;
	this.score = score;
	this.url = url;
	this.content = content;
	this.code = code;
}

}
