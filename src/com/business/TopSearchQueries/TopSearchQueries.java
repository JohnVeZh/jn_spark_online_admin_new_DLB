package com.business.TopSearchQueries; 

public class TopSearchQueries extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int countnum;
    private String id;
    private int sort;
    private String title;

    public int getCountnum(){
      return countnum;
    }
    public void setCountnum(int countnum){
      this.countnum = countnum;
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
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }

/** default constructor */
	public TopSearchQueries() {
	}

    public TopSearchQueries (int countnum,String id,int sort,String title) {
      this.countnum = countnum;
      this.id = id;
      this.sort = sort;
      this.title = title;
    }
}
