package com.business.JQrSubtitleListening; 

public class JQrSubtitleListening extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String id;
    private String hearUrl;
    private String mpName;
    private String createtime;
    private int sort;
    private int isDel;
    private String qrCode;
    private String levelType;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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
    public String getHearUrl(){
      return hearUrl;
    }
    public void setHearUrl(String hearUrl){
      this.hearUrl = hearUrl;
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
	public JQrSubtitleListening() {
	}

    public JQrSubtitleListening (String levelType,String qrCode,String code,String id,String hearUrl,String mpName,String createtime,int sort,int isDel) {
      this.code = code;
      this.qrCode = qrCode;
      this.id = id;
      this.hearUrl = hearUrl;
      this.mpName = mpName;
      this.createtime = createtime;
      this.sort = sort;
      this.isDel = isDel;
      this.levelType = levelType;
    }
}
