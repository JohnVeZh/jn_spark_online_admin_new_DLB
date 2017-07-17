package com.business.JQrMp; 

public class JQrMp extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String id;
    private String qrtId;
    private String mpName;
    private String createtime;
    private int sort;
    private int isDel;
    private String url;
    private String content;
    private String levelType;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getQrtId(){
      return qrtId;
    }
    public void setQrtId(String qrtId){
      this.qrtId = qrtId;
    }
    public String getMpName(){
      return mpName;
    }
    public void setMpName(String mpName){
      this.mpName = mpName;
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
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }

/** default constructor */
	public JQrMp() {
	}

    public JQrMp (String levelType,String url,String content,String code,String id,String qrtId,String mpName,String createtime,int sort,int isDel) {
      this.code = code;
      this.id = id;
      this.qrtId = qrtId;
      this.mpName = mpName;
      this.createtime = createtime;
      this.sort = sort;
      this.isDel = isDel;
      this.url = url;
      this.content = content;
      this.levelType = levelType;
    }
}
