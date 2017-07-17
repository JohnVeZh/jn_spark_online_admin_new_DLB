package com.business.NewQuestionsType; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NewQuestionsTypeActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String createtime;
    private String levelType;
    private int sort;
    private String textTypeName;
    private String parentId;
    private String code;
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
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
    public String getLevelType(){
      return levelType;
    }
    public void setLevelType(String levelType){
      this.levelType = levelType;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getTextTypeName(){
      return textTypeName;
    }
    public void setTextTypeName(String textTypeName){
      this.textTypeName = textTypeName;
    }
}

