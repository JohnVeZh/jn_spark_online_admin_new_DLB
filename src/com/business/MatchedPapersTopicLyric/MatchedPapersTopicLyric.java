package com.business.MatchedPapersTopicLyric; 

public class MatchedPapersTopicLyric extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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

/** default constructor */
	public MatchedPapersTopicLyric() {
	}

    public MatchedPapersTopicLyric (double endTime,String mpthtId,double statrTime,String id,String lyricText,int sort) {
      this.endTime = endTime;
      this.mpthtId = mpthtId;
      this.statrTime = statrTime;
      this.id = id;
      this.lyricText = lyricText;
      this.sort = sort;
    }
}
