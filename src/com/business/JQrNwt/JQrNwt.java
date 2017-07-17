package com.business.JQrNwt; 

public class JQrNwt extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String qrCode;
    private String id;
    private String name;

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

/** default constructor */
	public JQrNwt() {
	}

    public JQrNwt (String qrCode,String id,String name) {
      this.qrCode = qrCode;
      this.id = id;
      this.name = name;
    }
}
