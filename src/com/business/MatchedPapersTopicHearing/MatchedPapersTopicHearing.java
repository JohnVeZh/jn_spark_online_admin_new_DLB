package com.business.MatchedPapersTopicHearing; 

public class MatchedPapersTopicHearing extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String hName;
    private String mpId;
    private String hContent;
    private String id;
    private int sort;
    private String subjectType;
    private int isDel;
    private String code;
    
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMpId(){
      return mpId;
    }
    public void setMpId(String mpId){
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
    public String getSubjectType(){
      return subjectType;
    }
    public void setSubjectType(String subjectType){
      this.subjectType = subjectType;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }

    public String gethName() {
		return hName;
	}
	public void sethName(String hName) {
		this.hName = hName;
	}
	public String gethContent() {
		return hContent;
	}
	public void sethContent(String hContent) {
		this.hContent = hContent;
	}
/** default constructor */
	public MatchedPapersTopicHearing() {
	}

    public MatchedPapersTopicHearing (String code,String hName,String mpId,String hContent,String id,int sort,String subjectType,int isDel) {
      this.hName = hName;
      this.mpId = mpId;
      this.hContent = hContent;
      this.id = id;
      this.sort = sort;
      this.subjectType = subjectType;
      this.isDel = isDel;
      this.code = code;
    }
}
