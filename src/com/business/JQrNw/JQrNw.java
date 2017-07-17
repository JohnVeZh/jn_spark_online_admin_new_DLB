package com.business.JQrNw; 

public class JQrNw extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String networkLiveLink;
    private String qrCode;
    private String imgPath;
    private String id;
    private String qrnwtId;
    private int networkType;
    private String networkRecordLink;
    private String networkLiveTime;
    private String name;
    private int sort;
    private String levelType;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getNetworkLiveLink(){
      return networkLiveLink;
    }
    public void setNetworkLiveLink(String networkLiveLink){
      this.networkLiveLink = networkLiveLink;
    }
    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getImgPath(){
      return imgPath;
    }
    public void setImgPath(String imgPath){
      this.imgPath = imgPath;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getQrnwtId(){
      return qrnwtId;
    }
    public void setQrnwtId(String qrnwtId){
      this.qrnwtId = qrnwtId;
    }
    public int getNetworkType(){
      return networkType;
    }
    public void setNetworkType(int networkType){
      this.networkType = networkType;
    }
    public String getNetworkRecordLink(){
      return networkRecordLink;
    }
    public void setNetworkRecordLink(String networkRecordLink){
      this.networkRecordLink = networkRecordLink;
    }
    public String getNetworkLiveTime(){
      return networkLiveTime;
    }
    public void setNetworkLiveTime(String networkLiveTime){
      this.networkLiveTime = networkLiveTime;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }

/** default constructor */
	public JQrNw() {
	}

    public JQrNw (String levelType,int sort,String networkLiveLink,String qrCode,String imgPath,String id,String qrnwtId,int networkType,String networkRecordLink,String networkLiveTime,String name) {
      this.networkLiveLink = networkLiveLink;
      this.qrCode = qrCode;
      this.imgPath = imgPath;
      this.id = id;
      this.qrnwtId = qrnwtId;
      this.networkType = networkType;
      this.networkRecordLink = networkRecordLink;
      this.networkLiveTime = networkLiveTime;
      this.name = name;
      this.sort = sort;
      this.levelType = levelType;
    }
}
