package com.business.Problem; 

public class Problem extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int isSatisfied;
    private String sysUserId;
    private int isDel;
    private String replyContent;
    private int isReply;
    private String id;
    private String content;
    private String userId;

    public int getIsSatisfied(){
      return isSatisfied;
    }
    public void setIsSatisfied(int isSatisfied){
      this.isSatisfied = isSatisfied;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public String getReplyContent(){
      return replyContent;
    }
    public void setReplyContent(String replyContent){
      this.replyContent = replyContent;
    }
    public int getIsReply(){
      return isReply;
    }
    public void setIsReply(int isReply){
      this.isReply = isReply;
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
	public Problem() {
	}

    public Problem (int isSatisfied,String sysUserId,int isDel,String replyContent,int isReply,String id,String content,String userId) {
      this.isSatisfied = isSatisfied;
      this.sysUserId = sysUserId;
      this.isDel = isDel;
      this.replyContent = replyContent;
      this.isReply = isReply;
      this.id = id;
      this.content = content;
      this.userId = userId;
    }
}
