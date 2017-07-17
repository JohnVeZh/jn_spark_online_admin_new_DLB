package com.business.BookActivationCode; 

public class BookActivationCode extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int useNum;
    private String userId;
    private String code;
    private int isExport;
    private String sysUserId;
    private int isUse;
    private String useRegion;
    private String createtime;
    private String id;
    private String useTime;
    private int isDel;
    private String productId;

    public int getUseNum(){
      return useNum;
    }
    public void setUseNum(int useNum){
      this.useNum = useNum;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public int getIsExport(){
      return isExport;
    }
    public void setIsExport(int isExport){
      this.isExport = isExport;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public int getIsUse(){
      return isUse;
    }
    public void setIsUse(int isUse){
      this.isUse = isUse;
    }
    public String getUseRegion(){
      return useRegion;
    }
    public void setUseRegion(String useRegion){
      this.useRegion = useRegion;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getUseTime(){
      return useTime;
    }
    public void setUseTime(String useTime){
      this.useTime = useTime;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }

/** default constructor */
	public BookActivationCode() {
	}

    public BookActivationCode (int useNum,String userId,String code,int isExport,String sysUserId,int isUse,String useRegion,String createtime,String id,String useTime,int isDel,String productId) {
      this.useNum = useNum;
      this.userId = userId;
      this.code = code;
      this.isExport = isExport;
      this.sysUserId = sysUserId;
      this.isUse = isUse;
      this.useRegion = useRegion;
      this.createtime = createtime;
      this.id = id;
      this.useTime = useTime;
      this.isDel = isDel;
      this.productId = productId;
    }
}
