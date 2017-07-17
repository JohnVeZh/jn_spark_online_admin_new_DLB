package com.business.JQrSubtitleListeningLyric; 

public class JQrSubtitleListeningLyric extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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

/** default constructor */
	public JQrSubtitleListeningLyric() {
	}

    public JQrSubtitleListeningLyric (String qrslId,String id,double statrTime,String lyricText,int sort) {
      this.qrslId = qrslId;
      this.id = id;
      this.statrTime = statrTime;
      this.lyricText = lyricText;
      this.sort = sort;
    }
}
