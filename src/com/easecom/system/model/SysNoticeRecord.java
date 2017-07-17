package com.easecom.system.model; 

public class SysNoticeRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String state;
    private String sysUserId;
    private String sysNoticeId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getState(){
      return state;
    }
    public void setState(String state){
      this.state = state;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getSysNoticeId(){
      return sysNoticeId;
    }
    public void setSysNoticeId(String sysNoticeId){
      this.sysNoticeId = sysNoticeId;
    }

/** default constructor */
	public SysNoticeRecord() {
	}

    public SysNoticeRecord (String id,String state,String sysUserId,String sysNoticeId) {
      this.id = id;
      this.state = state;
      this.sysUserId = sysUserId;
      this.sysNoticeId = sysNoticeId;
    }
}
