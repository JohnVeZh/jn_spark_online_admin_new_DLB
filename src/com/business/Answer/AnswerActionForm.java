package com.business.Answer; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class AnswerActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String problemType;
    private String id;
    private String problemContent;
    private String createtime;
    private String problemName;
    private int sort;

    public String getProblemType(){
      return problemType;
    }
    public void setProblemType(String problemType){
      this.problemType = problemType;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getProblemContent(){
      return problemContent;
    }
    public void setProblemContent(String problemContent){
      this.problemContent = problemContent;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getProblemName(){
      return problemName;
    }
    public void setProblemName(String problemName){
      this.problemName = problemName;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
}

