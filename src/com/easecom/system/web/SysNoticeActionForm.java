package com.easecom.system.web; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class SysNoticeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String createtime;
    private String sysUserId;
    private String content;
    private String title;
    private String type;
    private String shopIds;

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
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShopIds() {
		return shopIds;
	}
	public void setShopIds(String shopIds) {
		this.shopIds = shopIds;
	}
    
}

