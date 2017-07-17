package com.business.NewQuestionsTypePapersUserRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NewQuestionsTypePapersUserRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String nqptId;
    private String id;
    private String userId;
    private String createtime;
    private String answer;
    private String sujectType;
    private String nqtpId;
    private int isCorrect;

    public String getNqptId(){
      return nqptId;
    }
    public void setNqptId(String nqptId){
      this.nqptId = nqptId;
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
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getAnswer(){
      return answer;
    }
    public void setAnswer(String answer){
      this.answer = answer;
    }
    public String getSujectType(){
      return sujectType;
    }
    public void setSujectType(String sujectType){
      this.sujectType = sujectType;
    }
    public String getNqtpId(){
      return nqtpId;
    }
    public void setNqtpId(String nqtpId){
      this.nqtpId = nqtpId;
    }
    public int getIsCorrect(){
      return isCorrect;
    }
    public void setIsCorrect(int isCorrect){
      this.isCorrect = isCorrect;
    }
}

