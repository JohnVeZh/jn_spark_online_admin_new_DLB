package com.business.NewWrongRecord; 

public class NewWrongRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int wrong;
    private int count;
    private String nqptId;
    private int correct;
    private String id;
    private String nqtpId;

    public int getWrong(){
      return wrong;
    }
    public void setWrong(int wrong){
      this.wrong = wrong;
    }
    public int getCount(){
      return count;
    }
    public void setCount(int count){
      this.count = count;
    }
    public String getNqptId(){
      return nqptId;
    }
    public void setNqptId(String nqptId){
      this.nqptId = nqptId;
    }
    public int getCorrect(){
      return correct;
    }
    public void setCorrect(int correct){
      this.correct = correct;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getNqtpId(){
      return nqtpId;
    }
    public void setNqtpId(String nqtpId){
      this.nqtpId = nqtpId;
    }

/** default constructor */
	public NewWrongRecord() {
	}

    public NewWrongRecord (int wrong,int count,String nqptId,int correct,String id,String nqtpId) {
      this.wrong = wrong;
      this.count = count;
      this.nqptId = nqptId;
      this.correct = correct;
      this.id = id;
      this.nqtpId = nqtpId;
    }
}
