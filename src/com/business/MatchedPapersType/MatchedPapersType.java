package com.business.MatchedPapersType; 

public class MatchedPapersType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String parentId;
    private String id;
    private String createtime;
    private int sort;
    private String textTypeName;
    private String scId;
    private String code;

    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	public String getParentId(){
      return parentId;
    }
    public void setParentId(String parentId){
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
	public MatchedPapersType() {
	}

    public MatchedPapersType (String code,String scId,String parentId,String id,String createtime,int sort,String textTypeName) {
      this.parentId = parentId;
      this.id = id;
      this.createtime = createtime;
      this.sort = sort;
      this.textTypeName = textTypeName;
      this.scId = scId;
      this.code = code;
    }
}
