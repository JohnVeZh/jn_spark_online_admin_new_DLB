package com.business.JCommonType; 

public class JCommonType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String typeName;
    private String sort;
    private String id;
    private String createtime;
    private String qrCode;

    
    public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }
    public String getSort(){
      return sort;
    }
    public void setSort(String sort){
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

/** default constructor */
	public JCommonType() {
	}

    public JCommonType (String qrCode,String typeName,String sort,String id,String createtime) {
      this.typeName = typeName;
      this.sort = sort;
      this.id = id;
      this.createtime = createtime;
      this.qrCode = qrCode;
    }
}
