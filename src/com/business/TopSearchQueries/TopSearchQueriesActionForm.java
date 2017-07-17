package com.business.TopSearchQueries; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class TopSearchQueriesActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int countnum;
    private String id;
    private int sort;
    private String title;

    public int getCountnum(){
      return countnum;
    }
    public void setCountnum(int countnum){
      this.countnum = countnum;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
}

