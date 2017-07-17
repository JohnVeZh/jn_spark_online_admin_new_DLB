package com.business.JQrWriting; 

public class JQrWriting extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String hearUrl;
    private String title;
    private String jqrwtId;
    private String levelType;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getHearUrl(){
      return hearUrl;
    }
    public void setHearUrl(String hearUrl){
      this.hearUrl = hearUrl;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
    public String getJqrwtId(){
      return jqrwtId;
    }
    public void setJqrwtId(String jqrwtId){
      this.jqrwtId = jqrwtId;
    }

/** default constructor */
	public JQrWriting() {
	}

    public JQrWriting (String levelType,String id,String hearUrl,String title,String jqrwtId) {
      this.id = id;
      this.hearUrl = hearUrl;
      this.title = title;
      this.jqrwtId = jqrwtId;
      this.levelType = levelType;
    }
}
