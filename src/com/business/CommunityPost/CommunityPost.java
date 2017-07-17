package com.business.CommunityPost; 

public class CommunityPost extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int seeNum;
    private String createtime;
    private String countent;
    private int isDel;
    private String id;
    private String recommendTime;
    private String topTime;
    private int isRecommend;
    private String sysUserId;
    private String brief;
    private int likeNum;
    private String viewImgpath;
    private int isTop;
    private String title;
    private int differNum;
    private int isVary;
    private int commentNum;
    private String imgpathList;
    private int concernNum;
    private  int isShow;


    public int getIsShow() { return isShow;}
    public void setIsShow(int isShow) {this.isShow = isShow;}
    public int getConcernNum() {
		return concernNum;
	}
	public void setConcernNum(int concernNum) {
		this.concernNum = concernNum;
	}
	public int getSeeNum(){
      return seeNum;
    }
    public void setSeeNum(int seeNum){
      this.seeNum = seeNum;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getCountent(){
      return countent;
    }
    public void setCountent(String countent){
      this.countent = countent;
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
    public String getRecommendTime(){
      return recommendTime;
    }
    public void setRecommendTime(String recommendTime){
      this.recommendTime = recommendTime;
    }
    public String getTopTime(){
      return topTime;
    }
    public void setTopTime(String topTime){
      this.topTime = topTime;
    }
    public int getIsRecommend(){
      return isRecommend;
    }
    public void setIsRecommend(int isRecommend){
      this.isRecommend = isRecommend;
    }
    public String getSysUserId(){
      return sysUserId;
    }
    public void setSysUserId(String sysUserId){
      this.sysUserId = sysUserId;
    }
    public String getBrief(){
      return brief;
    }
    public void setBrief(String brief){
      this.brief = brief;
    }
    public int getLikeNum(){
      return likeNum;
    }
    public void setLikeNum(int likeNum){
      this.likeNum = likeNum;
    }
    public String getViewImgpath(){
      return viewImgpath;
    }
    public void setViewImgpath(String viewImgpath){
      this.viewImgpath = viewImgpath;
    }
    public int getIsTop(){
      return isTop;
    }
    public void setIsTop(int isTop){
      this.isTop = isTop;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
      this.title = title;
    }
    public int getDifferNum(){
      return differNum;
    }
    public void setDifferNum(int differNum){
      this.differNum = differNum;
    }
    public int getIsVary(){
      return isVary;
    }
    public void setIsVary(int isVary){
      this.isVary = isVary;
    }
    public int getCommentNum(){
      return commentNum;
    }
    public void setCommentNum(int commentNum){
      this.commentNum = commentNum;
    }
    public String getImgpathList(){
      return imgpathList;
    }
    public void setImgpathList(String imgpathList){
      this.imgpathList = imgpathList;
    }

/** default constructor */
	public CommunityPost() {
	}

    public CommunityPost (int seeNum,String createtime,String countent,int isDel,String id,String recommendTime,String topTime,int isRecommend,String sysUserId,String brief,int likeNum,String viewImgpath,int isTop,String title,int differNum,int isVary,int commentNum,String imgpathList) {
      this.seeNum = seeNum;
      this.createtime = createtime;
      this.countent = countent;
      this.isDel = isDel;
      this.id = id;
      this.recommendTime = recommendTime;
      this.topTime = topTime;
      this.isRecommend = isRecommend;
      this.sysUserId = sysUserId;
      this.brief = brief;
      this.likeNum = likeNum;
      this.viewImgpath = viewImgpath;
      this.isTop = isTop;
      this.title = title;
      this.differNum = differNum;
      this.isVary = isVary;
      this.commentNum = commentNum;
      this.imgpathList = imgpathList;
    }
}
