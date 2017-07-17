package com.business.MatchedWrongRecord; 

public class MatchedWrongRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int wrong;
    private int count;
    private int correct;
    private String id;
    private int type;
    private String nqptId;
    private String nqtpId;
    private int wrongRate;
    
    public int getWrongRate() {
		return wrongRate;
	}
	public void setWrongRate(int wrongRate) {
		this.wrongRate = wrongRate;
	}
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
    public int getType(){
      return type;
    }
    public void setType(int type){
      this.type = type;
    }
    public String getNqptId(){
      return nqptId;
    }
    public void setNqptId(String nqptId){
      this.nqptId = nqptId;
    }
    public String getNqtpId(){
      return nqtpId;
    }
    public void setNqtpId(String nqtpId){
      this.nqtpId = nqtpId;
    }

/** default constructor */
	public MatchedWrongRecord() {
	}

    public MatchedWrongRecord (int wrong,int count,int correct,String id,int type,String nqptId,String nqtpId) {
      this.wrong = wrong;
      this.count = count;
      this.correct = correct;
      this.id = id;
      this.type = type;
      this.nqptId = nqptId;
      this.nqtpId = nqtpId;
    }
}
