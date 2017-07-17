package com.business.Answer; 

public class Answer extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String problemType;
    private String id;
    private String problemContent;
    private String createtime;
    private String problemName;
    private int sort;

    public String getProblemType(){
      return problemType;
    }
    public void setProblemType(String problemType){
      this.problemType = problemType;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getProblemContent(){
      return problemContent;
    }
    public void setProblemContent(String problemContent){
      this.problemContent = problemContent;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getProblemName(){
      return problemName;
    }
    public void setProblemName(String problemName){
      this.problemName = problemName;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }

/** default constructor */
	public Answer() {
	}

    public Answer (String problemType,String id,String problemContent,String createtime,String problemName,int sort) {
      this.problemType = problemType;
      this.id = id;
      this.problemContent = problemContent;
      this.createtime = createtime;
      this.problemName = problemName;
      this.sort = sort;
    }
}
