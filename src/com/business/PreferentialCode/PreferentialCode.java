package com.business.PreferentialCode; 

public class PreferentialCode extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int productType;
    private double deductionMoney;
    private double fullMoney;
    private double rate;
    private String id;
    private String sort;
    private String endTime;
    private String createtime;
    private String startTime;
    private String content;
    private int discountType;
    private String title;

    public int getProductType(){
      return productType;
    }
    public void setProductType(int productType){
      this.productType = productType;
    }
    public double getDeductionMoney(){
      return deductionMoney;
    }
    public void setDeductionMoney(double deductionMoney){
      this.deductionMoney = deductionMoney;
    }
    public double getFullMoney(){
      return fullMoney;
    }
    public void setFullMoney(double fullMoney){
      this.fullMoney = fullMoney;
    }
    public double getRate(){
      return rate;
    }
    public void setRate(double rate){
      this.rate = rate;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getSort(){
      return sort;
    }
    public void setSort(String sort){
      this.sort = sort;
    }
    public String getEndTime(){
      return endTime;
    }
    public void setEndTime(String endTime){
      this.endTime = endTime;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getStartTime(){
      return startTime;
    }
    public void setStartTime(String startTime){
      this.startTime = startTime;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public int getDiscountType(){
      return discountType;
    }
    public void setDiscountType(int discountType){
      this.discountType = discountType;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }

/** default constructor */
	public PreferentialCode() {
	}

    public PreferentialCode (int productType,double deductionMoney,double fullMoney,double rate,String id,String sort,String endTime,String createtime,String startTime,String content,int discountType,String title) {
      this.productType = productType;
      this.deductionMoney = deductionMoney;
      this.fullMoney = fullMoney;
      this.rate = rate;
      this.id = id;
      this.sort = sort;
      this.endTime = endTime;
      this.createtime = createtime;
      this.startTime = startTime;
      this.content = content;
      this.discountType = discountType;
      this.title = title;
    }
}
