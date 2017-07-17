package com.business.MatchedPapersTopicReadType; 

public class MatchedPapersTopicReadType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String typeName;
    private String mptrId;
    private int sort;
    private String content;
    private String code;
    private String translateContent;

    
    public String getTranslateContent() {
		return translateContent;
	}
	public void setTranslateContent(String translateContent) {
		this.translateContent = translateContent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getTypeName(){
      return typeName;
    }
    public void setTypeName(String typeName){
      this.typeName = typeName;
    }
    public String getMptrId(){
      return mptrId;
    }
    public void setMptrId(String mptrId){
      this.mptrId = mptrId;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }

/** default constructor */
	public MatchedPapersTopicReadType() {
	}

    public MatchedPapersTopicReadType (String translateContent,String code,String content,String id,String typeName,String mptrId,int sort) {
      this.id = id;
      this.typeName = typeName;
      this.mptrId = mptrId;
      this.sort = sort;
      this.content = content;
      this.code = code;
      this.translateContent = translateContent;
    }
}
