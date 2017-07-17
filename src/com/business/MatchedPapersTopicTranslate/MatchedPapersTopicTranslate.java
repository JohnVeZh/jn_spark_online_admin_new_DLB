package com.business.MatchedPapersTopicTranslate; 

public class MatchedPapersTopicTranslate extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String mpId;
    private String tTest;
    private String id;
    private int isDel;
    private String tContent;
    private String code;
    private String analysis;

    
    public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
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
    
    public String gettTest() {
		return tTest;
	}
	public void settTest(String tTest) {
		this.tTest = tTest;
	}
	public String gettContent() {
		return tContent;
	}
	public void settContent(String tContent) {
		this.tContent = tContent;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }

/** default constructor */
	public MatchedPapersTopicTranslate() {
	}
	public MatchedPapersTopicTranslate(String analysis,String code,String mpId, String tTest, String id,
		int isDel, String tContent) {
	super();
	this.mpId = mpId;
	this.tTest = tTest;
	this.id = id;
	this.isDel = isDel;
	this.tContent = tContent;
	this.code = code;
	this.analysis = analysis;
}

}
