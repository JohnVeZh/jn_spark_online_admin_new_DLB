package com.business.UserFeedback; 

import com.easecom.common.framework.struts.BaseForm;

public class UserFeedbackActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String createtime;
    private String content;
    private int isHandle;
    private String handleResults;
    private String telephone;
    private String userId;
    private String userName;
    private String email;
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
}

