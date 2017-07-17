package com.business.CommonWords; 

public class CommonWords extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int testFrequency;
    private String ctId;
    private String createtime;
    private String audioPath;
    private String phonogram;
    private String id;
    private String word;
    private String content;
    private int sort;
    private String intro;

    
    public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getTestFrequency(){
      return testFrequency;
    }
    public void setTestFrequency(int testFrequency){
      this.testFrequency = testFrequency;
    }
    public String getCtId(){
      return ctId;
    }
    public void setCtId(String ctId){
      this.ctId = ctId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getAudioPath(){
      return audioPath;
    }
    public void setAudioPath(String audioPath){
      this.audioPath = audioPath;
    }
    public String getPhonogram(){
      return phonogram;
    }
    public void setPhonogram(String phonogram){
      this.phonogram = phonogram;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getWord(){
      return word;
    }
    public void setWord(String word){
      this.word = word;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }

/** default constructor */
	public CommonWords() {
	}

    public CommonWords (String intro,int testFrequency,String ctId,String createtime,String audioPath,String phonogram,String id,String word,String content,int sort) {
      this.testFrequency = testFrequency;
      this.ctId = ctId;
      this.intro = intro;
      this.createtime = createtime;
      this.audioPath = audioPath;
      this.phonogram = phonogram;
      this.id = id;
      this.word = word;
      this.content = content;
      this.sort = sort;
    }
}
