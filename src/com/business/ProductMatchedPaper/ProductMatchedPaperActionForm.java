package com.business.ProductMatchedPaper; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class ProductMatchedPaperActionForm  extends BaseForm {
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
}

