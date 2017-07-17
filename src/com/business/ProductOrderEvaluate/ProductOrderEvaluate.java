package com.business.ProductOrderEvaluate; 

import java.sql.Blob;

public class ProductOrderEvaluate extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int averageScore;
    private int isDel;
    private Blob content;
    private String createtime;
    private String productId;
    private String podId;
    private int thinkingScore;
    private String id;
    private String userId;
    private int curriculumScore;
    private int styleScore;

    public int getAverageScore(){
      return averageScore;
    }
    public void setAverageScore(int averageScore){
      this.averageScore = averageScore;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }
    public String getPodId(){
      return podId;
    }
    public void setPodId(String podId){
      this.podId = podId;
    }
    public int getThinkingScore(){
      return thinkingScore;
    }
    public void setThinkingScore(int thinkingScore){
      this.thinkingScore = thinkingScore;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public int getCurriculumScore(){
      return curriculumScore;
    }
    public void setCurriculumScore(int curriculumScore){
      this.curriculumScore = curriculumScore;
    }
    public int getStyleScore(){
      return styleScore;
    }
    public void setStyleScore(int styleScore){
      this.styleScore = styleScore;
    }

/** default constructor */
	public ProductOrderEvaluate() {
	}

    public ProductOrderEvaluate (int averageScore,int isDel,Blob content,String createtime,String productId,String podId,int thinkingScore,String id,String userId,int curriculumScore,int styleScore) {
      this.averageScore = averageScore;
      this.isDel = isDel;
      this.content = content;
      this.createtime = createtime;
      this.productId = productId;
      this.podId = podId;
      this.thinkingScore = thinkingScore;
      this.id = id;
      this.userId = userId;
      this.curriculumScore = curriculumScore;
      this.styleScore = styleScore;
    }
}
