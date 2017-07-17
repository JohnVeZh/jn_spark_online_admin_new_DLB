package com.business.ProductOrderDetailsCollocation; 

public class ProductOrderDetailsCollocation extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String podId;
    private String id;
    private int productType;
    private String productId;
    private String pcId;
    private Double money;
    private String status;

    
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getPodId(){
      return podId;
    }
    public void setPodId(String podId){
      this.podId = podId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getProductType(){
      return productType;
    }
    public void setProductType(int productType){
      this.productType = productType;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }
    public String getPcId(){
      return pcId;
    }
    public void setPcId(String pcId){
      this.pcId = pcId;
    }

/** default constructor */
	public ProductOrderDetailsCollocation() {
	}

    public ProductOrderDetailsCollocation (String status,String podId,String id,int productType,String productId,String pcId,double money) {
      this.podId = podId;
      this.id = id;
      this.productType = productType;
      this.productId = productId;
      this.pcId = pcId;
      this.money = money;
      this.status = status;
    }
}
