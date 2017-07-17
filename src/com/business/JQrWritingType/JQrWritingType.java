package com.business.JQrWritingType; 

public class JQrWritingType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String qrCode;
    private String typeName;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }

/** default constructor */
	public JQrWritingType() {
	}

    public JQrWritingType (String id,String qrCode,String typeName) {
      this.id = id;
      this.qrCode = qrCode;
      this.typeName = typeName;
    }
}
