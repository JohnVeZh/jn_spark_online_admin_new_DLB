package com.business.JQrMptht; 

public class JQrMptht extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String url;
    private int isDel;
    private String id;
    private String content;
    private String qrmpthId;
    private int sort;
    private String name;

    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getUrl(){
      return url;
    }
    public void setUrl(String url){
      this.url = url;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
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
    public String getQrmpthId(){
      return qrmpthId;
    }
    public void setQrmpthId(String qrmpthId){
      this.qrmpthId = qrmpthId;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }

/** default constructor */
	public JQrMptht() {
	}

    public JQrMptht (String code,String url,int isDel,String id,String content,String qrmpthId,int sort,String name) {
      this.code = code;
      this.url = url;
      this.isDel = isDel;
      this.id = id;
      this.content = content;
      this.qrmpthId = qrmpthId;
      this.sort = sort;
      this.name = name;
    }
}
