package com.business.JQrSubtitleListeningLyric; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrSubtitleListeningLyricActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String qrslId;
    private String id;
    private double statrTime;
    private String lyricText;
    private int sort;

    public String getQrslId(){
      return qrslId;
    }
    public void setQrslId(String qrslId){
      this.qrslId = qrslId;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public double getStatrTime(){
      return statrTime;
    }
    public void setStatrTime(double statrTime){
      this.statrTime = statrTime;
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

