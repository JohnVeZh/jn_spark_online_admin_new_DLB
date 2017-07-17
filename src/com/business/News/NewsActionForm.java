package com.business.News; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;




import com.easecom.common.framework.struts.BaseForm;

public class NewsActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String content;
    private String author;
    private String ptime;
    private String source;
    private String subtitle;
    private int count;
    private String phoneImg;
    private String title;
    private String id;
    private String padImg;
    private String sysUserId;
    private int browseVolume;
    private int isDel;
    private int likeNum;
    private int differNum;
    private int isVary;
    private int isTop;
    private String topTime;
    private int isRecommend;
    private String recommendTime;
    private int commentNum;
    private String createtime;
	private int isShow;
    private FormFile file;

	public int getIsShow() {return isShow;}
	public void setIsShow(int isShow) {this.isShow = isShow;}
    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}

	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getDifferNum() {
		return differNum;
	}
	public void setDifferNum(int differNum) {
		this.differNum = differNum;
	}
	public int getIsVary() {
		return isVary;
	}
	public void setIsVary(int isVary) {
		this.isVary = isVary;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public String getTopTime() {
		return topTime;
	}
	public void setTopTime(String topTime) {
		this.topTime = topTime;
	}
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getRecommendTime() {
		return recommendTime;
	}
	public void setRecommendTime(String recommendTime) {
		this.recommendTime = recommendTime;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public int getBrowseVolume() {
		return browseVolume;
	}
	public void setBrowseVolume(int browseVolume) {
		this.browseVolume = browseVolume;
	}
	public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getAuthor(){
      return author;
    }
    public void setAuthor(String author){
      this.author = author;
    }
    public String getPtime(){
      return ptime;
    }
    public void setPtime(String ptime){
      this.ptime = ptime;
    }
    public String getSource(){
      return source;
    }
    public void setSource(String source){
      this.source = source;
    }
    public String getSubtitle(){
      return subtitle;
    }
    public void setSubtitle(String subtitle){
      this.subtitle = subtitle;
    }
    public int getCount(){
      return count;
    }
    public void setCount(int count){
      this.count = count;
    }
   
    public String getPhoneImg() {
		return phoneImg;
	}
	public void setPhoneImg(String phoneImg) {
		this.phoneImg = phoneImg;
	}
	public String getPadImg() {
		return padImg;
	}
	public void setPadImg(String padImg) {
		this.padImg = padImg;
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
}

