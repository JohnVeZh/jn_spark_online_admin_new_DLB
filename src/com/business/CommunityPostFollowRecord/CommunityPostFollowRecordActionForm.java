package com.business.CommunityPostFollowRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class CommunityPostFollowRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String communityPostId;
    private String createtime;
    private String id;
    private String userId;

    public String getCommunityPostId(){
      return communityPostId;
    }
    public void setCommunityPostId(String communityPostId){
      this.communityPostId = communityPostId;
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

