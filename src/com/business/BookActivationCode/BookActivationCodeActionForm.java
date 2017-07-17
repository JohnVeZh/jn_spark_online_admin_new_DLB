package com.business.BookActivationCode; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class BookActivationCodeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int useNum;
    private String userId;
    private String code;
    private int isExport;
    private String sysUserId;
    private int isUse;
    private String useRegion;
    private String createtime;
    private String id;
    private String useTime;
    private int isDel;
    private String productId;
    
    private FormFile formfile;
    

    public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public void clear() {
    	formfile = null;
    	 }
	public int getUseNum(){
      return useNum;
    }
    public void setUseNum(int useNum){
      this.useNum = useNum;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public int getIsExport(){
      return isExport;
    }
    public void setIsExport(int isExport){
      this.isExport = isExport;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public int getIsUse(){
      return isUse;
    }
    public void setIsUse(int isUse){
      this.isUse = isUse;
    }
    public String getUseRegion(){
      return useRegion;
    }
    public void setUseRegion(String useRegion){
      this.useRegion = useRegion;
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
    public String getUseTime(){
      return useTime;
    }
    public void setUseTime(String useTime){
      this.useTime = useTime;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }
}

