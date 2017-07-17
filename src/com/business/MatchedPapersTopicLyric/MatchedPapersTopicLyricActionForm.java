package com.business.MatchedPapersTopicLyric; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersTopicLyricActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private double endTime;
    private String mpthtId;
    private double statrTime;
    private String id;
    private String lyricText;
    private int sort;

    public double getEndTime(){
      return endTime;
    }
    public void setEndTime(double endTime){
      this.endTime = endTime;
    }
    public String getMpthtId(){
      return mpthtId;
    }
    public void setMpthtId(String mpthtId){
      this.mpthtId = mpthtId;
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

