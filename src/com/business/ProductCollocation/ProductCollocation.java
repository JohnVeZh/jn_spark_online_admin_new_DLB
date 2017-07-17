package com.business.ProductCollocation; 

public class ProductCollocation extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int sort;
    private String productId;
    private String createtime;
    private String id;
    private String productFid;
    private int productFtype;
    private int productType;
    private int isDel;
    private double money;

    
    public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }

public String getProductFid() {
		return productFid;
	}
	public void setProductFid(String productFid) {
		this.productFid = productFid;
	}
	public int getProductFtype() {
		return productFtype;
	}
	public void setProductFtype(int productFtype) {
		this.productFtype = productFtype;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
/** default constructor */
	public ProductCollocation() {
	}
public ProductCollocation(double money,int isDel,int sort, String productId, String createtime,
		String id, String productFid, int productFtype, int productType) {
	super();
	this.money = money;
	this.isDel = isDel;
	this.sort = sort;
	this.productId = productId;
	this.createtime = createtime;
	this.id = id;
	this.productFid = productFid;
	this.productFtype = productFtype;
	this.productType = productType;
}

}
