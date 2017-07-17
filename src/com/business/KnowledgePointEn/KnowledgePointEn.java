package com.business.KnowledgePointEn; 

public class KnowledgePointEn extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String kpeSubjectDirectoryId;
    private String kpeCreatetime;
    private String kpeName;
    private int kpeSort;
    private String kpeContent;
    private String kpeAudioPath;
    private int kpeTestFrequency;
    private String kpeIntro;
    private String id;
    private String kpePhonogram;

    public String getKpeSubjectDirectoryId(){
      return kpeSubjectDirectoryId;
    }
    public void setKpeSubjectDirectoryId(String kpeSubjectDirectoryId){
      this.kpeSubjectDirectoryId = kpeSubjectDirectoryId;
    }
    public String getKpeCreatetime(){
      return kpeCreatetime;
    }
    public void setKpeCreatetime(String kpeCreatetime){
      this.kpeCreatetime = kpeCreatetime;
    }
    public String getKpeName(){
      return kpeName;
    }
    public void setKpeName(String kpeName){
      this.kpeName = kpeName;
    }
    public int getKpeSort(){
      return kpeSort;
    }
    public void setKpeSort(int kpeSort){
      this.kpeSort = kpeSort;
    }
    public String getKpeContent(){
      return kpeContent;
    }
    public void setKpeContent(String kpeContent){
      this.kpeContent = kpeContent;
    }
    public String getKpeAudioPath(){
      return kpeAudioPath;
    }
    public void setKpeAudioPath(String kpeAudioPath){
      this.kpeAudioPath = kpeAudioPath;
    }
    public int getKpeTestFrequency(){
      return kpeTestFrequency;
    }
    public void setKpeTestFrequency(int kpeTestFrequency){
      this.kpeTestFrequency = kpeTestFrequency;
    }
    public String getKpeIntro(){
      return kpeIntro;
    }
    public void setKpeIntro(String kpeIntro){
      this.kpeIntro = kpeIntro;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getKpePhonogram(){
      return kpePhonogram;
    }
    public void setKpePhonogram(String kpePhonogram){
      this.kpePhonogram = kpePhonogram;
    }

/** default constructor */
	public KnowledgePointEn() {
	}

    public KnowledgePointEn (String kpeSubjectDirectoryId,String kpeCreatetime,String kpeName,int kpeSort,String kpeContent,String kpeAudioPath,int kpeTestFrequency,String kpeIntro,String id,String kpePhonogram) {
      this.kpeSubjectDirectoryId = kpeSubjectDirectoryId;
      this.kpeCreatetime = kpeCreatetime;
      this.kpeName = kpeName;
      this.kpeSort = kpeSort;
      this.kpeContent = kpeContent;
      this.kpeAudioPath = kpeAudioPath;
      this.kpeTestFrequency = kpeTestFrequency;
      this.kpeIntro = kpeIntro;
      this.id = id;
      this.kpePhonogram = kpePhonogram;
    }
}
