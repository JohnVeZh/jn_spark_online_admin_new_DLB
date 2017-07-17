package com.business.JQrMpthsc; 

public class JQrMpthsc extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private int isDel;
    private int cIsAnswer;
    private String qrmtphcId;
    private int cSort;
    private String cContent;
    private String cName;
    private String id;


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

	public String getQrmtphcId() {
		return qrmtphcId;
	}

	public void setQrmtphcId(String qrmtphcId) {
		this.qrmtphcId = qrmtphcId;
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

/** default constructor */
	public JQrMpthsc() {
	}

    public JQrMpthsc (String code,int isDel,int cIsAnswer,String qrmtphcId,int cSort,String cContent,String cName,String id) {
      this.code = code;
      this.isDel = isDel;
      this.cIsAnswer = cIsAnswer;
      this.qrmtphcId = qrmtphcId;
      this.cSort = cSort;
      this.cContent = cContent;
      this.cName = cName;
      this.id = id;
    }
}
