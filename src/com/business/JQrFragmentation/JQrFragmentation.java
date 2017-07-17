package com.business.JQrFragmentation; 

public class JQrFragmentation extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String qrCode;
    private String id;
    private String name;
    private String hearUrl;

    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public String getHearUrl(){
      return hearUrl;
    }
    public void setHearUrl(String hearUrl){
      this.hearUrl = hearUrl;
    }

/** default constructor */
	public JQrFragmentation() {
	}

    public JQrFragmentation (String qrCode,String id,String name,String hearUrl) {
      this.qrCode = qrCode;
      this.id = id;
      this.name = name;
      this.hearUrl = hearUrl;
    }
}
