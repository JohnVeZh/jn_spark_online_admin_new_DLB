package com.business.CommunityPostLikeRecord; 

public class CommunityPostLikeRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String createtime;
    private int type;
    private String communityPostId;
    private String id;
    private String userId;

    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public int getType(){
      return type;
    }
    public void setType(int type){
      this.type = type;
    }
    public String getCommunityPostId(){
      return communityPostId;
    }
    public void setCommunityPostId(String communityPostId){
      this.communityPostId = communityPostId;
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
	public CommunityPostLikeRecord() {
	}

    public CommunityPostLikeRecord (String createtime,int type,String communityPostId,String id,String userId) {
      this.createtime = createtime;
      this.type = type;
      this.communityPostId = communityPostId;
      this.id = id;
      this.userId = userId;
    }
}
