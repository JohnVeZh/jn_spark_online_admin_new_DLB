package com.business.SystemFeedback; 

public class SystemFeedback extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int isSuc;
    private String createtime;
    private String remark;
    private String id;
    private String content;
    private String userId;

    public int getIsSuc(){
      return isSuc;
    }
    public void setIsSuc(int isSuc){
      this.isSuc = isSuc;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }

/** default constructor */
	public SystemFeedback() {
	}

    public SystemFeedback (int isSuc,String createtime,String remark,String id,String content,String userId) {
      this.isSuc = isSuc;
      this.createtime = createtime;
      this.remark = remark;
      this.id = id;
      this.content = content;
      this.userId = userId;
    }
}
