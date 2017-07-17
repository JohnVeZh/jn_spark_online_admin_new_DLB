package com.business.PushLog; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class PushLogActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String editcontent;
    private String title;
    private String id;
    private String content;
    private String sysUserId;
    private String createtime;
    private int ismap;

    public String getEditcontent(){
      return editcontent;
    }
    public void setEditcontent(String editcontent){
      this.editcontent = editcontent;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public int getIsmap(){
      return ismap;
    }
    public void setIsmap(int ismap){
      this.ismap = ismap;
    }
}

