package com.business.MatchedPapersTopicReadSubject; 

public class MatchedPapersTopicReadSubject extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String cAnalysis;
    private String mptrtId;
    private String cTitle;
    private String id;
    private int isDel;
    private int sNumber;
    private int score;
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


	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
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

	public String getMptrtId() {
		return mptrtId;
	}

	public void setMptrtId(String mptrtId) {
		this.mptrtId = mptrtId;
	}

/** default constructor */
	public MatchedPapersTopicReadSubject() {
	}

    public MatchedPapersTopicReadSubject (String code,String cAnalysis,String mptrtId,String cTitle,String id,int isDel,int sNumber,int score) {
      this.cAnalysis = cAnalysis;
      this.mptrtId = mptrtId;
      this.cTitle = cTitle;
      this.id = id;
      this.isDel = isDel;
      this.sNumber = sNumber;
      this.score = score;
      this.code = code;
    }
}
