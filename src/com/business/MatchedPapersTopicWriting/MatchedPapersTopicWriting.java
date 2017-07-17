package com.business.MatchedPapersTopicWriting; 

public class MatchedPapersTopicWriting extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String mpId;
    private String wTest;
    private String id;
    private int isDel;
    private String wContent;
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

	public String getMpId() {
		return mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
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


	public String getwTest() {
		return wTest;
	}

	public void setwTest(String wTest) {
		this.wTest = wTest;
	}

	public String getwContent() {
		return wContent;
	}

	public void setwContent(String wContent) {
		this.wContent = wContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

/** default constructor */
	public MatchedPapersTopicWriting() {
	}

	public MatchedPapersTopicWriting(String analysis,String code,String mpId, String wTest, String id,
		int isDel, String wContent) {
	super();
	this.mpId = mpId;
	this.wTest = wTest;
	this.id = id;
	this.isDel = isDel;
	this.wContent = wContent;
	this.code = code;
	this.analysis = analysis;
}

}
