package com.business.CommunityPostFollowRecord; 

public class CommunityPostFollowRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String communityPostId;
    private String createtime;
    private String id;
    private String userId;

    public String getCommunityPostId(){
      return communityPostId;
    }
    public void setCommunityPostId(String communityPostId){
      this.communityPostId = communityPostId;
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

/** default constructor */
	public CommunityPostFollowRecord() {
	}

    public CommunityPostFollowRecord (String communityPostId,String createtime,String id,String userId) {
      this.communityPostId = communityPostId;
      this.createtime = createtime;
      this.id = id;
      this.userId = userId;
    }
}
