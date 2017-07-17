package com.business.ProductOrderDetailsCollocation; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class ProductOrderDetailsCollocationActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String podId;
    private String id;
    private int productType;
    private String productId;
    private String pcId;
    private double money;
    private String status;
    
    
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
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
}

