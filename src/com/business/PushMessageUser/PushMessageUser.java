package com.business.PushMessageUser; 

public class PushMessageUser extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int isLook;
    private int id;
    private String messageId;
    private String userId;
    private String createtime;
    
    public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getIsLook(){
      return isLook;
    }
    public void setIsLook(int isLook){
      this.isLook = isLook;
    }
    public int getId(){
      return id;
    }
    public void setId(int id){
      this.id = id;
    }
    public String getMessageId(){
      return messageId;
    }
    public void setMessageId(String messageId){
      this.messageId = messageId;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }

/** default constructor */
	public PushMessageUser() {
	}

    public PushMessageUser (int isLook,int id,String messageId,String userId) {
      this.isLook = isLook;
      this.id = id;
      this.messageId = messageId;
      this.userId = userId;
    }
}
