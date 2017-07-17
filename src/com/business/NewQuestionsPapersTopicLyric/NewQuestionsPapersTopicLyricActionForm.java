package com.business.NewQuestionsPapersTopicLyric; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NewQuestionsPapersTopicLyricActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private double statrTime;
    private String id;
    private String nqpttId;
    private String lyricText;
    private int sort;
    private double endTime;

    
    public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	public double getStatrTime(){
      return statrTime;
    }
    public void setStatrTime(double statrTime){
      this.statrTime = statrTime;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
	public String getNqpttId() {
		return nqpttId;
	}
	public void setNqpttId(String nqpttId) {
		this.nqpttId = nqpttId;
	}
	public String getLyricText(){
      return lyricText;
    }
    public void setLyricText(String lyricText){
      this.lyricText = lyricText;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
}

