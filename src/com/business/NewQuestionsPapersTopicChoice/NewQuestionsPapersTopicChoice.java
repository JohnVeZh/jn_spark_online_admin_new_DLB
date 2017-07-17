package com.business.NewQuestionsPapersTopicChoice; 

public class NewQuestionsPapersTopicChoice extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int cIsAnswer;
    private String nqptId;
    private int cSort;
    private String cContent;
    private String cName;
    private String id;
    private int isDel;

    

public int getcIsAnswer() {
		return cIsAnswer;
	}

	public void setcIsAnswer(int cIsAnswer) {
		this.cIsAnswer = cIsAnswer;
	}

	public String getNqptId() {
		return nqptId;
	}

	public void setNqptId(String nqptId) {
		this.nqptId = nqptId;
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

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

/** default constructor */
	public NewQuestionsPapersTopicChoice() {
	}

    public NewQuestionsPapersTopicChoice (int cIsAnswer,String nqptId,int cSort,String cContent,String cName,String id,int isDel) {
      this.cIsAnswer = cIsAnswer;
      this.nqptId = nqptId;
      this.cSort = cSort;
      this.cContent = cContent;
      this.cName = cName;
      this.id = id;
      this.isDel = isDel;
    }
}
