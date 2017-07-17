package com.business.ProductType; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;




import com.easecom.common.framework.struts.BaseForm;

public class ProductTypeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String typeName;
    private String imgPhone;
    private String imgPad;
    private int sort;
    private String parentId;
    private String sysUserId;
    private String createtime;
    
    private FormFile file;

    
    public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
    
    public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
    
    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
	public String getImgPhone() {
		return imgPhone;
	}
	public void setImgPhone(String imgPhone) {
		this.imgPhone = imgPhone;
	}
	public String getImgPad() {
		return imgPad;
	}
	public void setImgPad(String imgPad) {
		this.imgPad = imgPad;
	}
    
}

