package com.business.NewsMessage; 

import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class NewsMessageActionForm  extends BaseForm {
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
    public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
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
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
}

