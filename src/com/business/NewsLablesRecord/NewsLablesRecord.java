package com.business.NewsLablesRecord; 

public class NewsLablesRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String newsId;
    private String newLablesId;

    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getNewsId(){
      return newsId;
    }
    public void setNewsId(String newsId){
      this.newsId = newsId;
    }
    public String getNewLablesId(){
      return newLablesId;
    }
    public void setNewLablesId(String newLablesId){
      this.newLablesId = newLablesId;
    }

/** default constructor */
	public NewsLablesRecord() {
	}

    public NewsLablesRecord (String id,String newsId,String newLablesId) {
      this.id = id;
      this.newsId = newsId;
      this.newLablesId = newLablesId;
    }
}
