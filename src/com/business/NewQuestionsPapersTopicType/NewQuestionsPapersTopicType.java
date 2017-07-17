package com.business.NewQuestionsPapersTopicType; 

public class NewQuestionsPapersTopicType extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String nqtpId;
    private String tUrl;
    private String id;
    private int sort;
    private String subjectType;
    private int isDel;
    private String tName;
    private String code;
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNqtpId(){
      return nqtpId;
    }
    public void setNqtpId(String nqtpId){
      this.nqtpId = nqtpId;
    }
   
    public String gettUrl() {
		return tUrl;
	}
	public void settUrl(String tUrl) {
		this.tUrl = tUrl;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
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
    

/** default constructor */
	public NewQuestionsPapersTopicType() {
	}

    public NewQuestionsPapersTopicType (String nqtpId,String tUrl,String id,int sort,String subjectType,int isDel,String tName) {
      this.nqtpId = nqtpId;
      this.tUrl = tUrl;
      this.id = id;
      this.sort = sort;
      this.subjectType = subjectType;
      this.isDel = isDel;
      this.tName = tName;
    }
}
