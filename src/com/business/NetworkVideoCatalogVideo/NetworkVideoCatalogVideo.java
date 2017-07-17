package com.business.NetworkVideoCatalogVideo; 

public class NetworkVideoCatalogVideo extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String nvcId;
    private String networkLiveLink;
    private String startTime;
    private String id;
    private String createtime;
    private String networkRecordLinkId;
    private String catalogName;
    private String longTime;
    private int sort;
    private String nvId;
    private int isDel;
    private int networkType;
    private String teacherId;
    
    
    public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getNetworkType() {
		return networkType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getNvId() {
		return nvId;
	}
	public void setNvId(String nvId) {
		this.nvId = nvId;
	}
	public String getNvcId(){
      return nvcId;
    }
    public void setNvcId(String nvcId){
      this.nvcId = nvcId;
    }
    public String getNetworkLiveLink(){
      return networkLiveLink;
    }
    public void setNetworkLiveLink(String networkLiveLink){
      this.networkLiveLink = networkLiveLink;
    }
    public String getStartTime(){
      return startTime;
    }
    public void setStartTime(String startTime){
      this.startTime = startTime;
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
    public String getNetworkRecordLinkId(){
      return networkRecordLinkId;
    }
    public void setNetworkRecordLinkId(String networkRecordLinkId){
      this.networkRecordLinkId = networkRecordLinkId;
    }
    public String getCatalogName(){
      return catalogName;
    }
    public void setCatalogName(String catalogName){
      this.catalogName = catalogName;
    }
    public String getLongTime(){
      return longTime;
    }
    public void setLongTime(String longTime){
      this.longTime = longTime;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }

/** default constructor */
	public NetworkVideoCatalogVideo() {
	}

    public NetworkVideoCatalogVideo (String teacherId,int networkType,int isDel,String nvId,String nvcId,String networkLiveLink,String startTime,String id,String createtime,String networkRecordLinkId,String catalogName,String longTime,int sort) {
      this.nvcId = nvcId;
      this.nvId = nvId;
      this.isDel = isDel;
      this.networkLiveLink = networkLiveLink;
      this.startTime = startTime;
      this.id = id;
      this.createtime = createtime;
      this.networkRecordLinkId = networkRecordLinkId;
      this.catalogName = catalogName;
      this.longTime = longTime;
      this.sort = sort;
      this.networkType = networkType;
      this.teacherId = teacherId;
    }
}
