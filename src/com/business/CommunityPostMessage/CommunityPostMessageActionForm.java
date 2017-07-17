package com.business.CommunityPostMessage; 

import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class CommunityPostMessageActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String commentsId;
    private int likeNum;
    private String createtime;
    private String imgpths;
    private String id;
    private String communityPostId;
    private Blob content;
    private String userId;
    private String firstmesId;
	private int isTop;
	private String topTime;


	public int getIsTop() {return isTop;}
	public void setIsTop(int isTop) {this.isTop = isTop;}
	public String getTopTime() {return topTime;}
	public void setTopTime(String topTime) {this.topTime = topTime;}
	public String getFirstmesId() {
		return firstmesId;
	}
	public void setFirstmesId(String firstmesId) {
		this.firstmesId = firstmesId;
	}
	public String getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(String commentsId) {
		this.commentsId = commentsId;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getImgpths() {
		return imgpths;
	}
	public void setImgpths(String imgpths) {
		this.imgpths = imgpths;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommunityPostId() {
		return communityPostId;
	}
	public void setCommunityPostId(String communityPostId) {
		this.communityPostId = communityPostId;
	}
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}

