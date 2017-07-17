package com.business.JQrStudyMaterialsWriting; 

public class JQrStudyMaterialsWriting extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private int type;
    private String title;
    private String qrCode;
    private String contemt;
    private int sort;
    private String levelType;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getType(){
      return type;
    }
    public void setType(int type){
      this.type = type;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getContemt(){
      return contemt;
    }
    public void setContemt(String contemt){
      this.contemt = contemt;
    }

/** default constructor */
	public JQrStudyMaterialsWriting() {
	}

    public JQrStudyMaterialsWriting (String levelType,int sort,String id,int type,String title,String qrCode,String contemt) {
      this.id = id;
      this.type = type;
      this.title = title;
      this.qrCode = qrCode;
      this.contemt = contemt;
      this.sort = sort;
      this.levelType = levelType;
    }
}
