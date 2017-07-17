package com.business.CommonType; 

public class CommonType extends com.easecom.common.framework.hibernate.BaseModel
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
    private String parentId;

    public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public CommonType() {
	}

    public CommonType (String typeName,String sort,String id,String createtime) {
      this.typeName = typeName;
      this.sort = sort;
      this.id = id;
      this.createtime = createtime;
    }
}
