package com.business.MatchedPapersUser; 

public class MatchedPapersUser extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String mptsId;
    private int state;
    private String mpId;
    private String id;
    private String userId;
    private String createtime;
    private int type;
    private String completeTime;

    public String getMptsId(){
      return mptsId;
    }
    public void setMptsId(String mptsId){
      this.mptsId = mptsId;
    }
    public int getState(){
      return state;
    }
    public void setState(int state){
      this.state = state;
    }
    public String getMpId(){
      return mpId;
    }
    public void setMpId(String mpId){
      this.mpId = mpId;
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
    public String getCompleteTime(){
      return completeTime;
    }
    public void setCompleteTime(String completeTime){
      this.completeTime = completeTime;
    }

/** default constructor */
	public MatchedPapersUser() {
	}

    public MatchedPapersUser (String mptsId,int state,String mpId,String id,String userId,String createtime,int type,String completeTime) {
      this.mptsId = mptsId;
      this.state = state;
      this.mpId = mpId;
      this.id = id;
      this.userId = userId;
      this.createtime = createtime;
      this.type = type;
      this.completeTime = completeTime;
    }
}
