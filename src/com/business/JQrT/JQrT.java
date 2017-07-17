package com.business.JQrT; 

public class JQrT extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String qrCode;
    private String parentId;
    private String id;
    private String createtime;
    private int sort;
    private String textTypeName;
    private int isDel;

    
    public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getParentId(){
      return parentId;
    }
    public void setParentId(String parentId){
      this.parentId = parentId;
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
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getTextTypeName(){
      return textTypeName;
    }
    public void setTextTypeName(String textTypeName){
      this.textTypeName = textTypeName;
    }

/** default constructor */
	public JQrT() {
	}

    public JQrT (int isDel,String code,String qrCode,String parentId,String id,String createtime,int sort,String textTypeName) {
      this.code = code;
      this.isDel = isDel;
      this.qrCode = qrCode;
      this.parentId = parentId;
      this.id = id;
      this.createtime = createtime;
      this.sort = sort;
      this.textTypeName = textTypeName;
    }
}
