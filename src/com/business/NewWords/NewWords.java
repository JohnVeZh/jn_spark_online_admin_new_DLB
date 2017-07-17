package com.business.NewWords; 

public class NewWords extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String userId;
    private String knowledgePointEnId;
    private String createtime;

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
    public String getKnowledgePointEnId(){
      return knowledgePointEnId;
    }
    public void setKnowledgePointEnId(String knowledgePointEnId){
      this.knowledgePointEnId = knowledgePointEnId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }

/** default constructor */
	public NewWords() {
	}

    public NewWords (String id,String userId,String knowledgePointEnId,String createtime) {
      this.id = id;
      this.userId = userId;
      this.knowledgePointEnId = knowledgePointEnId;
      this.createtime = createtime;
    }
}
