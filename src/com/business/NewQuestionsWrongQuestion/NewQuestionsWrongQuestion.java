package com.business.NewQuestionsWrongQuestion; 

public class NewQuestionsWrongQuestion extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String subjectType;
    private String nqptId;
    private String nqtpId;
    private String id;
    private String userId;
    private int count;
    private String createtime;

    public String getSubjectType(){
      return subjectType;
    }
    public void setSubjectType(String subjectType){
      this.subjectType = subjectType;
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
    public int getCount(){
      return count;
    }
    public void setCount(int count){
      this.count = count;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }

/** default constructor */
	public NewQuestionsWrongQuestion() {
	}

    public NewQuestionsWrongQuestion (String subjectType,String nqptId,String nqtpId,String id,String userId,int count,String createtime) {
      this.subjectType = subjectType;
      this.nqptId = nqptId;
      this.nqtpId = nqtpId;
      this.id = id;
      this.userId = userId;
      this.count = count;
      this.createtime = createtime;
    }
}
