package com.business.MatchedPapersTopicHearingSubjectChoice; 

public class MatchedPapersTopicHearingSubjectChoice extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int isDel;
    private int cIsAnswer;
    private int cSort;
    private String cContent;
    private String cName;
    private String mtphcId;
    private String id;
    private String code;


    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getMtphcId() {
		return mtphcId;
	}

	public void setMtphcId(String mtphcId) {
		this.mtphcId = mtphcId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

/** default constructor */
	public MatchedPapersTopicHearingSubjectChoice() {
	}

    public MatchedPapersTopicHearingSubjectChoice (String code,int isDel,int cIsAnswer,int cSort,String cContent,String cName,String mtphcId,String id) {
      this.isDel = isDel;
      this.cIsAnswer = cIsAnswer;
      this.cSort = cSort;
      this.cContent = cContent;
      this.cName = cName;
      this.mtphcId = mtphcId;
      this.id = id;
      this.code = code;
    }
}
