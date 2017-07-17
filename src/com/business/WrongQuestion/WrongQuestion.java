package com.business.WrongQuestion; 

public class WrongQuestion extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String mpthsId;
    private String id;
    private String leveltype;
    private String userId;
    private int wqType;
    private int count;
    private String createtime;
    private String mpId;

    public String getMpthsId(){
      return mpthsId;
    }
    public void setMpthsId(String mpthsId){
      this.mpthsId = mpthsId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getLeveltype(){
      return leveltype;
    }
    public void setLeveltype(String leveltype){
      this.leveltype = leveltype;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public int getWqType(){
      return wqType;
    }
    public void setWqType(int wqType){
      this.wqType = wqType;
    }
    public int getCount(){
      return count;
    }
    public void setCount(int count){
      this.count = count;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getMpId(){
      return mpId;
    }
    public void setMpId(String mpId){
      this.mpId = mpId;
    }

/** default constructor */
	public WrongQuestion() {
	}

    public WrongQuestion (String mpthsId,String id,String leveltype,String userId,int wqType,int count,String createtime,String mpId) {
      this.mpthsId = mpthsId;
      this.id = id;
      this.leveltype = leveltype;
      this.userId = userId;
      this.wqType = wqType;
      this.count = count;
      this.createtime = createtime;
      this.mpId = mpId;
    }
}
