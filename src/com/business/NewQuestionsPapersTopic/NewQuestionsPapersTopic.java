package com.business.NewQuestionsPapersTopic; 

public class NewQuestionsPapersTopic extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String tSubject;
    private String tAnalysis;
    private int isDel;
    private String tText;
    private String id;
    private int tNum;
    private String nqpttId;
    private String tUrl;
    private int score;
    private int sort;

    

public String gettSubject() {
		return tSubject;
	}

	public void settSubject(String tSubject) {
		this.tSubject = tSubject;
	}

	public String gettAnalysis() {
		return tAnalysis;
	}

	public void settAnalysis(String tAnalysis) {
		this.tAnalysis = tAnalysis;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String gettText() {
		return tText;
	}

	public void settText(String tText) {
		this.tText = tText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int gettNum() {
		return tNum;
	}

	public void settNum(int tNum) {
		this.tNum = tNum;
	}

	public String getNqpttId() {
		return nqpttId;
	}

	public void setNqpttId(String nqpttId) {
		this.nqpttId = nqpttId;
	}

	public String gettUrl() {
		return tUrl;
	}

	public void settUrl(String tUrl) {
		this.tUrl = tUrl;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

/** default constructor */
	public NewQuestionsPapersTopic() {
	}

    public NewQuestionsPapersTopic (String tSubject,String tAnalysis,int isDel,String tText,String id,int tNum,String nqpttId,String tUrl,int score,int sort) {
      this.tSubject = tSubject;
      this.tAnalysis = tAnalysis;
      this.isDel = isDel;
      this.tText = tText;
      this.id = id;
      this.tNum = tNum;
      this.nqpttId = nqpttId;
      this.tUrl = tUrl;
      this.score = score;
      this.sort = sort;
    }
}
