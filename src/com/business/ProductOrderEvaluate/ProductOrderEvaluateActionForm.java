package com.business.ProductOrderEvaluate; 

import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class ProductOrderEvaluateActionForm  extends BaseForm {
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
}

