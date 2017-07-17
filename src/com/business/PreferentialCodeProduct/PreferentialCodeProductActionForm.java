package com.business.PreferentialCodeProduct; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class PreferentialCodeProductActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int type;
    private String pcId;
    private int isDel;
    private String id;
    private String createtime;
    private String productId;

    public int getType(){
      return type;
    }
    public void setType(int type){
      this.type = type;
    }
    public String getPcId(){
      return pcId;
    }
    public void setPcId(String pcId){
      this.pcId = pcId;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
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
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }
}

