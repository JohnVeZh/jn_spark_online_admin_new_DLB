package com.business.NewsMessage; 

import java.sql.Blob;

public class NewsMessage extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String newsId;
    private String commentsId;
    private String createtime;
    private String id;
    private Blob content;
    private String userId;
    private int isDel;
    private int isTop;
    private String topTime;

    public int getIsTop() {return isTop;}
    public void setIsTop(int isTop) {this.isTop = isTop;}
    public String getTopTime() {return topTime;}
    public void setTopTime(String topTime) {this.topTime = topTime;}
    public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getNewsId(){
      return newsId;
    }
    public void setNewsId(String newsId){
      this.newsId = newsId;
    }
    public String getCommentsId(){
      return commentsId;
    }
    public void setCommentsId(String commentsId){
      this.commentsId = commentsId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }

/** default constructor */
	public NewsMessage() {
	}

    public NewsMessage (String newsId,String commentsId,String createtime,String id,Blob content,String userId) {
      this.newsId = newsId;
      this.commentsId = commentsId;
      this.createtime = createtime;
      this.id = id;
      this.content = content;
      this.userId = userId;
    }
}
