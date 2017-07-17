package com.business.NewQuestionsTypePapersUserRecord; 

public class NewQuestionsTypePapersUserRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String nqptId;
    private String id;
    private String userId;
    private String createtime;
    private String answer;
    private String sujectType;
    private String nqtpId;
    private int isCorrect;

    public String getNqptId(){
      return nqptId;
    }
    public void setNqptId(String nqptId){
      this.nqptId = nqptId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getAnswer(){
      return answer;
    }
    public void setAnswer(String answer){
      this.answer = answer;
    }
    public String getSujectType(){
      return sujectType;
    }
    public void setSujectType(String sujectType){
      this.sujectType = sujectType;
    }
    public String getNqtpId(){
      return nqtpId;
    }
    public void setNqtpId(String nqtpId){
      this.nqtpId = nqtpId;
    }
    public int getIsCorrect(){
      return isCorrect;
    }
    public void setIsCorrect(int isCorrect){
      this.isCorrect = isCorrect;
    }

/** default constructor */
	public NewQuestionsTypePapersUserRecord() {
	}

    public NewQuestionsTypePapersUserRecord (String nqptId,String id,String userId,String createtime,String answer,String sujectType,String nqtpId,int isCorrect) {
      this.nqptId = nqptId;
      this.id = id;
      this.userId = userId;
      this.createtime = createtime;
      this.answer = answer;
      this.sujectType = sujectType;
      this.nqtpId = nqtpId;
      this.isCorrect = isCorrect;
    }
}
