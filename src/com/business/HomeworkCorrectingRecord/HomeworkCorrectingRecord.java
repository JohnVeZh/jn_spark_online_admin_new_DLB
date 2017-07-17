package com.business.HomeworkCorrectingRecord; 

public class HomeworkCorrectingRecord extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String cTest;
    private String cContent;
    private String id;
    private String hcId;

    
   
   
	public String getcTest() {
		return cTest;
	}
	public void setcTest(String cTest) {
		this.cTest = cTest;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getHcId(){
      return hcId;
    }
    public void setHcId(String hcId){
      this.hcId = hcId;
    }

/** default constructor */
	public HomeworkCorrectingRecord() {
	}

    public HomeworkCorrectingRecord (String cTest,String cContent,String id,String hcId) {
      this.cTest = cTest;
      this.cContent = cContent;
      this.id = id;
      this.hcId = hcId;
    }
}
