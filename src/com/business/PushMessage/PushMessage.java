package com.business.PushMessage; 

public class PushMessage extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int pmIsPush;
    private String pmTitle;
    private String pmCreatetime;
    private String pmContent;
    private String id;
    private String pmImg;
    private String levelType;
    private int type;
    private int jumpType;
    private String contentId;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getJumpType() {
    return jumpType;
  }

  public void setJumpType(int jumpType) {
    this.jumpType = jumpType;
  }

  public String getContentId() {
    return contentId;
  }

  public void setContentId(String contentId) {
    this.contentId = contentId;
  }

  public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public int getPmIsPush(){
      return pmIsPush;
    }
    public void setPmIsPush(int pmIsPush){
      this.pmIsPush = pmIsPush;
    }
    public String getPmTitle(){
      return pmTitle;
    }
    public void setPmTitle(String pmTitle){
      this.pmTitle = pmTitle;
    }
    public String getPmCreatetime(){
      return pmCreatetime;
    }
    public void setPmCreatetime(String pmCreatetime){
      this.pmCreatetime = pmCreatetime;
    }
    public String getPmContent(){
      return pmContent;
    }
    public void setPmContent(String pmContent){
      this.pmContent = pmContent;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getPmImg(){
      return pmImg;
    }
    public void setPmImg(String pmImg){
      this.pmImg = pmImg;
    }

/** default constructor */
	public PushMessage() {
	}

    public PushMessage (int pmIsPush,String pmTitle,String pmCreatetime,String pmContent,String id,String pmImg) {
      this.pmIsPush = pmIsPush;
      this.pmTitle = pmTitle;
      this.pmCreatetime = pmCreatetime;
      this.pmContent = pmContent;
      this.id = id;
      this.pmImg = pmImg;
    }
}
