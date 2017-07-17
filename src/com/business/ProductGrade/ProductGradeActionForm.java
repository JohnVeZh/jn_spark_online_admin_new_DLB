package com.business.ProductGrade; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class ProductGradeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String gName;
    private int sort;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getgName(){
      return gName;
    }
    public void setgName(String gName){
      this.gName = gName;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
}

