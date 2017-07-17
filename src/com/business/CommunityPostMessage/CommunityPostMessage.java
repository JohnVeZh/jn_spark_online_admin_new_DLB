package com.business.CommunityPostMessage; 

import java.sql.Blob;

public class CommunityPostMessage extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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

/** default constructor */
	public CommunityPostMessage() {
	}

    public CommunityPostMessage (String firstmesId,String commentsId,int likeNum,String createtime,String imgpths,String id,String communityPostId,Blob content,String userId) {
      this.commentsId = commentsId;
      this.likeNum = likeNum;
      this.createtime = createtime;
      this.imgpths = imgpths;
      this.id = id;
      this.communityPostId = communityPostId;
      this.content = content;
      this.userId = userId;
      this.firstmesId = firstmesId;
    }
}
