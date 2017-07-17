package com.business.ProductGrade; 

public class ProductGrade extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String gName;
    private int sort;

    public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }

/** default constructor */
	public ProductGrade() {
	}

    public ProductGrade (String id,String gName,int sort) {
      this.id = id;
      this.gName = gName;
      this.sort = sort;
    }
}
