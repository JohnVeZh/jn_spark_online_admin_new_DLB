package com.business.ProductOrderLogistics; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class ProductOrderLogisticsActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String orderHistory;
    private String logisticscode;
    private String createtime;
    private String id;
    private String eName;
    private String eCode;

    public String getOrderHistory(){
      return orderHistory;
    }
    public void setOrderHistory(String orderHistory){
      this.orderHistory = orderHistory;
    }
    public String getLogisticscode(){
      return logisticscode;
    }
    public void setLogisticscode(String logisticscode){
      this.logisticscode = logisticscode;
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
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteCode() {
		return eCode;
	}
	public void seteCode(String eCode) {
		this.eCode = eCode;
	}
    
}

