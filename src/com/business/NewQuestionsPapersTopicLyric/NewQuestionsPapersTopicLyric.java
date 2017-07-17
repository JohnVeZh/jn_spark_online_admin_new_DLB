package com.business.NewQuestionsPapersTopicLyric; 

public class NewQuestionsPapersTopicLyric extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private double statrTime;
    private double endTime;
    private String id;
    private String nqpttId;
    private String lyricText;
    private int sort;

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

/** default constructor */
	public NewQuestionsPapersTopicLyric() {
	}

    public NewQuestionsPapersTopicLyric (double endTime,double statrTime,String id,String nqpttId,String lyricText,int sort) {
      this.statrTime = statrTime;
      this.endTime = endTime;
      this.id = id;
      this.lyricText = lyricText;
      this.sort = sort;
      this.nqpttId = nqpttId;
    }
}
