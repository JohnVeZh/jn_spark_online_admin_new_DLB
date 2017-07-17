package com.business.UserFeedback; 

public class UserFeedback extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String createtime;
    private String content;
    private int isHandle;
    private String handleResults;
    private String telephone;
    private String email;
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private String userId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public int getIsHandle(){
      return isHandle;
    }
    public void setIsHandle(int isHandle){
      this.isHandle = isHandle;
    }
    public String getHandleResults(){
      return handleResults;
    }
    public void setHandleResults(String handleResults){
      this.handleResults = handleResults;
    }
    public String getTelephone(){
      return telephone;
    }
    public void setTelephone(String telephone){
      this.telephone = telephone;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }

/** default constructor */
	public UserFeedback() {
	}

    public UserFeedback (String id,String createtime,String content,int isHandle,String handleResults,String telephone,String userId) {
      this.id = id;
      this.createtime = createtime;
      this.content = content;
      this.isHandle = isHandle;
      this.handleResults = handleResults;
      this.telephone = telephone;
      this.userId = userId;
    }
}
