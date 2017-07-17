package com.business.NetworkVideo;

import com.easecom.common.framework.struts.BaseForm;
import org.apache.struts.upload.FormFile;

public class NetworkVideoActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String limitEndTime;
    private int isLimitFree;
    private String networkLiveLink;
    private String id;
    private String networkIntroduce;
    private String networkRecordLink;
    private String networkName;
    private String networkImgpath;
    private String brief;
    private String teacherIntroduce;
    private String catalogIntroduce;
    private int saleNum;
    private double networkMoney;
    private int networkType;
    private String createtime;
    private int sort;
    private String limitStartTime;
    private int isFree;
    private String teacherId;
    private int state;
    private String networkLiveTime;
    private String levelType;
    private int catalogNum;
    private double originalPrice;
    private int isDel;
    private int isIndex;
    private int isCatalog;
    private int isTeacher;
    private int isBook;
    private int isPublic;
    private Integer evaluateNum;
    private FormFile file;
    private Double bookPrice;
    private int limitCount;
    private String enrolEndTime;
    private String teachStartTime;
    private String teachEndTime;
    private String qrcode;


    public int getIsPublic() {return isPublic;}
    public void setIsPublic(int isPublic) {this.isPublic = isPublic;}
    public int getIsBook() {
        return isBook;
    }
    public void setIsBook(int isBook) {
        this.isBook = isBook;
    }
    public Integer getEvaluateNum() {
        return evaluateNum;
    }
    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }
    public int getIsTeacher() {
		return isTeacher;
	}
	public void setIsTeacher(int isTeacher) {
		this.isTeacher = isTeacher;
	}
	public int getIsCatalog() {
		return isCatalog;
	}
	public void setIsCatalog(int isCatalog) {
		this.isCatalog = isCatalog;
	}
	public String getCatalogIntroduce() {
		return catalogIntroduce;
	}
	public void setCatalogIntroduce(String catalogIntroduce) {
		this.catalogIntroduce = catalogIntroduce;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getNetworkLiveTime() {
		return networkLiveTime;
	}
	public void setNetworkLiveTime(String networkLiveTime) {
		this.networkLiveTime = networkLiveTime;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public int getCatalogNum() {
		return catalogNum;
	}
	public void setCatalogNum(int catalogNum) {
		this.catalogNum = catalogNum;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getLimitEndTime(){
      return limitEndTime;
    }
    public void setLimitEndTime(String limitEndTime){
      this.limitEndTime = limitEndTime;
    }
    public int getIsLimitFree(){
      return isLimitFree;
    }
    public void setIsLimitFree(int isLimitFree){
      this.isLimitFree = isLimitFree;
    }
    public String getNetworkLiveLink(){
      return networkLiveLink;
    }
    public void setNetworkLiveLink(String networkLiveLink){
      this.networkLiveLink = networkLiveLink;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getNetworkIntroduce(){
      return networkIntroduce;
    }
    public void setNetworkIntroduce(String networkIntroduce){
      this.networkIntroduce = networkIntroduce;
    }
    public String getNetworkRecordLink(){
      return networkRecordLink;
    }
    public void setNetworkRecordLink(String networkRecordLink){
      this.networkRecordLink = networkRecordLink;
    }
    public String getNetworkName(){
      return networkName;
    }
    public void setNetworkName(String networkName){
      this.networkName = networkName;
    }
    public String getNetworkImgpath(){
      return networkImgpath;
    }
    public void setNetworkImgpath(String networkImgpath){
      this.networkImgpath = networkImgpath;
    }
    public String getBrief(){
      return brief;
    }
    public void setBrief(String brief){
      this.brief = brief;
    }
    public String getTeacherIntroduce(){
      return teacherIntroduce;
    }
    public void setTeacherIntroduce(String teacherIntroduce){
      this.teacherIntroduce = teacherIntroduce;
    }
    public int getSaleNum(){
      return saleNum;
    }
    public void setSaleNum(int saleNum){
      this.saleNum = saleNum;
    }
    public double getNetworkMoney(){
      return networkMoney;
    }
    public void setNetworkMoney(double networkMoney){
      this.networkMoney = networkMoney;
    }
    public int getNetworkType(){
      return networkType;
    }
    public void setNetworkType(int networkType){
      this.networkType = networkType;
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
    public String getLimitStartTime(){
      return limitStartTime;
    }
    public void setLimitStartTime(String limitStartTime){
      this.limitStartTime = limitStartTime;
    }
    public int getIsFree(){
      return isFree;
    }
    public void setIsFree(int isFree){
      this.isFree = isFree;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public String getEnrolEndTime() {
        return enrolEndTime;
    }

    public void setEnrolEndTime(String enrolEndTime) {
        this.enrolEndTime = enrolEndTime;
    }

    public String getTeachStartTime() {
        return teachStartTime;
    }

    public void setTeachStartTime(String teachStartTime) {
        this.teachStartTime = teachStartTime;
    }

    public String getTeachEndTime() {
        return teachEndTime;
    }

    public void setTeachEndTime(String teachEndTime) {
        this.teachEndTime = teachEndTime;
    }
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
    
}

