package com.business.ProductNetworkVideo; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class ProductNetworkVideoActionForm  extends BaseForm {
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
}

