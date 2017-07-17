package com.business.MatchedPapersTopicRead; 

public class MatchedPapersTopicRead extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String mpId;
    private String id;
    private String rName;
    private String rContent;
    private int sort;
    private int isDel;
    private String code;
    private String subjectType;
    
    public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMpId() {
		return mpId;
	}
	public void setMpId(String mpId) {
		this.mpId = mpId;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }

/** default constructor */
	public MatchedPapersTopicRead() {
	}

    public String getrName() {
    	return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public MatchedPapersTopicRead (String code,String mpId,String id,String rName,String rContent,int sort,int isDel) {
      this.mpId = mpId;
      this.id = id;
      this.rName = rName;
      this.rContent = rContent;
      this.sort = sort;
      this.isDel = isDel;
      this.code = code;
    }
}
