package com.business.NetworkVideoEvaluate; 

public class NetworkVideoEvaluate extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String content;
    private String networkVideoCatalogId;
    private String createtime;
    private String id;
    private String userId;

    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getNetworkVideoCatalogId(){
      return networkVideoCatalogId;
    }
    public void setNetworkVideoCatalogId(String networkVideoCatalogId){
      this.networkVideoCatalogId = networkVideoCatalogId;
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
	public NetworkVideoEvaluate() {
	}

    public NetworkVideoEvaluate (String content,String networkVideoCatalogId,String createtime,String id,String userId) {
      this.content = content;
      this.networkVideoCatalogId = networkVideoCatalogId;
      this.createtime = createtime;
      this.id = id;
      this.userId = userId;
    }
}
