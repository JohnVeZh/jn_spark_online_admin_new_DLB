package com.business.ProductMatchedPaper; 

public class ProductMatchedPaper extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String mptId;
    private String id;
    private String mpId;
    private String productId;

    public String getMptId(){
      return mptId;
    }
    public void setMptId(String mptId){
      this.mptId = mptId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getMpId(){
      return mpId;
    }
    public void setMpId(String mpId){
      this.mpId = mpId;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }

/** default constructor */
	public ProductMatchedPaper() {
	}

    public ProductMatchedPaper (String mptId,String id,String mpId,String productId) {
      this.mptId = mptId;
      this.id = id;
      this.mpId = mpId;
      this.productId = productId;
    }
}
