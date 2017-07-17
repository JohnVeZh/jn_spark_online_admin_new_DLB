package com.business.MatchedPapersUserRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersUserRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int mpurType;
    private String mpuId;
    private String mptsId;
    private String id;
    private int isCorrect;
    private String answer;

    public int getMpurType(){
      return mpurType;
    }
    public void setMpurType(int mpurType){
      this.mpurType = mpurType;
    }
    public String getMpuId(){
      return mpuId;
    }
    public void setMpuId(String mpuId){
      this.mpuId = mpuId;
    }
    public String getMptsId(){
      return mptsId;
    }
    public void setMptsId(String mptsId){
      this.mptsId = mptsId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getIsCorrect(){
      return isCorrect;
    }
    public void setIsCorrect(int isCorrect){
      this.isCorrect = isCorrect;
    }
    public String getAnswer(){
      return answer;
    }
    public void setAnswer(String answer){
      this.answer = answer;
    }
}

