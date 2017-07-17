package com.business.MatchedPapersTopicHearingType; 

public class MatchedPapersTopicHearingType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String url;
    private int isDel;
    private String id;
    private String mpthId;
    private String content;
    private int sort;
    private String name;
    private String code;
    
    
    
   	public String getCode() {
   		return code;
   	}
   	public void setCode(String code) {
   		this.code = code;
   	}

    public String getUrl(){
      return url;
    }
    public void setUrl(String url){
      this.url = url;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getMpthId(){
      return mpthId;
    }
    public void setMpthId(String mpthId){
      this.mpthId = mpthId;
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
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }

/** default constructor */
	public MatchedPapersTopicHearingType() {
	}

    public MatchedPapersTopicHearingType (String code,String url,int isDel,String id,String mpthId,String content,int sort,String name) {
      this.url = url;
      this.isDel = isDel;
      this.id = id;
      this.mpthId = mpthId;
      this.content = content;
      this.sort = sort;
      this.name = name;
      this.code = code;
    }
}
