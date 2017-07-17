package com.business.SystemFeedback; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class SystemFeedbackActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int isSuc;
    private String createtime;
    private String remark;
    private String id;
    private String content;
    private String userId;

    public int getIsSuc(){
      return isSuc;
    }
    public void setIsSuc(int isSuc){
      this.isSuc = isSuc;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
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
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
}

