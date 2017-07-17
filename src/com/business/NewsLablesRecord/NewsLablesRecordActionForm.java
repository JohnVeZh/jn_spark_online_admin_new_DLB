package com.business.NewsLablesRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NewsLablesRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String newsId;
    private String newLablesId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getNewsId(){
      return newsId;
    }
    public void setNewsId(String newsId){
      this.newsId = newsId;
    }
    public String getNewLablesId(){
      return newLablesId;
    }
    public void setNewLablesId(String newLablesId){
      this.newLablesId = newLablesId;
    }
}

