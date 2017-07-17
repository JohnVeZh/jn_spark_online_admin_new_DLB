package com.business.JQrCode; 

public class JQrCode extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String type;
    private String id;
    private int targetType;

    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getType(){
      return type;
    }
    public void setType(String type){
      this.type = type;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getTargetType(){
      return targetType;
    }
    public void setTargetType(int targetType){
      this.targetType = targetType;
    }

/** default constructor */
	public JQrCode() {
	}

    public JQrCode (String code,String type,String id,int targetType) {
      this.code = code;
      this.type = type;
      this.id = id;
      this.targetType = targetType;
    }
}
