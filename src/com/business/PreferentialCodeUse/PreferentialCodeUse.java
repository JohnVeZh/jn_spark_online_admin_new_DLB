package com.business.PreferentialCodeUse; 

public class PreferentialCodeUse extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String pcgId;
    private String createtime;
    private String userId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getPcgId(){
      return pcgId;
    }
    public void setPcgId(String pcgId){
      this.pcgId = pcgId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }

/** default constructor */
	public PreferentialCodeUse() {
	}

    public PreferentialCodeUse (String id,String pcgId,String createtime,String userId) {
      this.id = id;
      this.pcgId = pcgId;
      this.createtime = createtime;
      this.userId = userId;
    }
}
