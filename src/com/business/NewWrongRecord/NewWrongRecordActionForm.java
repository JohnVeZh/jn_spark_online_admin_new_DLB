package com.business.NewWrongRecord; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NewWrongRecordActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int wrong;
    private int count;
    private String nqptId;
    private int correct;
    private String id;
    private String nqtpId;

    public int getWrong(){
      return wrong;
    }
    public void setWrong(int wrong){
      this.wrong = wrong;
    }
    public int getCount(){
      return count;
    }
    public void setCount(int count){
      this.count = count;
    }
    public String getNqptId(){
      return nqptId;
    }
    public void setNqptId(String nqptId){
      this.nqptId = nqptId;
    }
    public int getCorrect(){
      return correct;
    }
    public void setCorrect(int correct){
      this.correct = correct;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getNqtpId(){
      return nqtpId;
    }
    public void setNqtpId(String nqtpId){
      this.nqtpId = nqtpId;
    }
}

