package com.business.JQrMpths; 

public class JQrMpths extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String cAnalysis;
    private String url;
    private String id;
    private int isDel;
    private int sNumber;
    private String content;
    private int score;
    private String qrmpthtId;


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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getQrmpthtId() {
		return qrmpthtId;
	}


	public void setQrmpthtId(String qrmpthtId) {
		this.qrmpthtId = qrmpthtId;
	}


/** default constructor */
	public JQrMpths() {
	}


	public JQrMpths(String code, String cAnalysis, String url, String id,
		int isDel, int sNumber, String content, int score, String qrmpthtId) {
		super();
		this.code = code;
		this.cAnalysis = cAnalysis;
		this.url = url;
		this.id = id;
		this.isDel = isDel;
		this.sNumber = sNumber;
		this.content = content;
		this.score = score;
		this.qrmpthtId = qrmpthtId;
	}

}
