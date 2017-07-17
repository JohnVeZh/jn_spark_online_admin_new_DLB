package com.business.NewLables; 

public class NewLables extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int sort;
    private String id;
    private String createtime;
    private String lablename;
    private int isDel;
    private String sysUserId;
    
	public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getLablename(){
      return lablename;
    }
    public void setLablename(String lablename){
      this.lablename = lablename;
    }
    

/** default constructor */
	public NewLables() {
	}

    public NewLables (int isDel,int sort,String id,String createtime,String lablename) {
      this.sort = sort;
      this.id = id;
      this.createtime = createtime;
      this.lablename = lablename;
      this.isDel = isDel;
    }
}
