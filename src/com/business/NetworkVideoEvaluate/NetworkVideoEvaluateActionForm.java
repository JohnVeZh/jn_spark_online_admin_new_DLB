package com.business.NetworkVideoEvaluate; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NetworkVideoEvaluateActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String content;
    private String networkVideoCatalogId;
    private String createtime;
    private String id;
    private String userId;

    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getNetworkVideoCatalogId(){
      return networkVideoCatalogId;
    }
    public void setNetworkVideoCatalogId(String networkVideoCatalogId){
      this.networkVideoCatalogId = networkVideoCatalogId;
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
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
}

