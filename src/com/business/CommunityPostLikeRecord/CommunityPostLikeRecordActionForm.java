package com.business.CommunityPostLikeRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class CommunityPostLikeRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String createtime;
    private int type;
    private String communityPostId;
    private String id;
    private String userId;

    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public int getType(){
      return type;
    }
    public void setType(int type){
      this.type = type;
    }
    public String getCommunityPostId(){
      return communityPostId;
    }
    public void setCommunityPostId(String communityPostId){
      this.communityPostId = communityPostId;
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

