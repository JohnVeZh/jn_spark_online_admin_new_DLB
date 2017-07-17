package com.business.PreferentialCodeGenerate; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class PreferentialCodeGenerateActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int pcgNum;
    private String id;
    private String pcId;
    private String pcgCode;
    private String createtime;

    public int getPcgNum(){
      return pcgNum;
    }
    public void setPcgNum(int pcgNum){
      this.pcgNum = pcgNum;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getPcId(){
      return pcId;
    }
    public void setPcId(String pcId){
      this.pcId = pcId;
    }
    public String getPcgCode(){
      return pcgCode;
    }
    public void setPcgCode(String pcgCode){
      this.pcgCode = pcgCode;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
}

