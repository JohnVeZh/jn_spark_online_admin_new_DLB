package com.business.MatchedPapers; 

public class MatchedPapers extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String id;
    private String mpListImgpath;
    private String mpName;
    private String createtime;
    private String mpViewImgpath;
    private String mptId;
    private int sort;
    private int isDel;
    private String code;
    private String qrCode;
    
    
    public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getMpListImgpath(){
      return mpListImgpath;
    }
    public void setMpListImgpath(String mpListImgpath){
      this.mpListImgpath = mpListImgpath;
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
    public String getMpViewImgpath(){
      return mpViewImgpath;
    }
    public void setMpViewImgpath(String mpViewImgpath){
      this.mpViewImgpath = mpViewImgpath;
    }
    public String getMptId(){
      return mptId;
    }
    public void setMptId(String mptId){
      this.mptId = mptId;
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
	public MatchedPapers() {
	}

    public MatchedPapers (String qrCode,String code,String id,String mpListImgpath,String mpName,String createtime,String mpViewImgpath,String mptId,int sort,int isDel) {
      this.id = id;
      this.mpListImgpath = mpListImgpath;
      this.mpName = mpName;
      this.createtime = createtime;
      this.mpViewImgpath = mpViewImgpath;
      this.mptId = mptId;
      this.sort = sort;
      this.isDel = isDel;
      this.code = code;
      this.qrCode = qrCode;
    }
}
