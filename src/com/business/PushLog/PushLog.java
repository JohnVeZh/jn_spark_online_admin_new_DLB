package com.business.PushLog; 

public class PushLog extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String editcontent;
    private String title;
    private String id;
    private String content;
    private String sysUserId;
    private String createtime;
    private int ismap;

    public String getEditcontent(){
      return editcontent;
    }
    public void setEditcontent(String editcontent){
      this.editcontent = editcontent;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public int getIsmap(){
      return ismap;
    }
    public void setIsmap(int ismap){
      this.ismap = ismap;
    }

/** default constructor */
	public PushLog() {
	}

    public PushLog (String editcontent,String title,String id,String content,String sysUserId,String createtime,int ismap) {
      this.editcontent = editcontent;
      this.title = title;
      this.id = id;
      this.content = content;
      this.sysUserId = sysUserId;
      this.createtime = createtime;
      this.ismap = ismap;
    }
}
