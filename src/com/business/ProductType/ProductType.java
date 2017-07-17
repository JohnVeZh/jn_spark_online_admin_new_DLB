package com.business.ProductType; 

public class ProductType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String typeName;
    private String imgPhone;
    private String imgPad;
    private int sort;
    private String parentId;
    private String sysUserId;
    private String createtime;
    
    public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }

    public String getImgPhone() {
		return imgPhone;
	}
	public void setImgPhone(String imgPhone) {
		this.imgPhone = imgPhone;
	}
	public String getImgPad() {
		return imgPad;
	}
	public void setImgPad(String imgPad) {
		this.imgPad = imgPad;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
/** default constructor */
	public ProductType() {
	}

    public ProductType (String parentId,String id,String typeName,String imgPhone,int sort) {
      this.id = id;
      this.typeName = typeName;
      this.imgPhone = imgPhone;
      this.sort = sort;
      this.parentId = parentId;
    }
}
