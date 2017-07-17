package com.business.ProductNetworkVideo; 

public class ProductNetworkVideo extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String nvId;
    private String id;
    private String productId;

    public String getNvId(){
      return nvId;
    }
    public void setNvId(String nvId){
      this.nvId = nvId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }

/** default constructor */
	public ProductNetworkVideo() {
	}

    public ProductNetworkVideo (String nvId,String id,String productId) {
      this.nvId = nvId;
      this.id = id;
      this.productId = productId;
    }
}
