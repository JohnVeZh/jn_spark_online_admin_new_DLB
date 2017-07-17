package com.business.NewQuestionsType; 

public class NewQuestionsType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String createtime;
    private String levelType;
    private int sort;
    private String textTypeName;
    private String parentId;
    private String code;
    
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getLevelType(){
      return levelType;
    }
    public void setLevelType(String levelType){
      this.levelType = levelType;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getTextTypeName(){
      return textTypeName;
    }
    public void setTextTypeName(String textTypeName){
      this.textTypeName = textTypeName;
    }

/** default constructor */
	public NewQuestionsType() {
	}

    public NewQuestionsType (String id,String createtime,String levelType,int sort,String textTypeName) {
      this.id = id;
      this.createtime = createtime;
      this.levelType = levelType;
      this.sort = sort;
      this.textTypeName = textTypeName;
    }
}
