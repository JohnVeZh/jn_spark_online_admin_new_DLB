package com.business.NetworkVideo;

import com.easecom.common.framework.hibernate.BaseModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "network_video", schema = "jn_spark_online", catalog = "")
public class NetworkVideo extends BaseModel
		implements Serializable {
   /**
	 * 
	 */
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
    private Integer evaluateNum;
    private int isPublic;
    private int isBook;
	private Double bookPrice;
	private int limitCount;
	private String enrolEndTime;
	private String teachStartTime;
	private String teachEndTime;
	private String qrcode;

/** default constructor */
	public NetworkVideo() {
	}

    public NetworkVideo(String limitEndTime, int isLimitFree, String networkLiveLink, String id, String networkIntroduce, String networkRecordLink, String networkName, String networkImgpath, String brief, String teacherIntroduce, String catalogIntroduce, int saleNum, double networkMoney, int networkType, String createtime, int sort, String limitStartTime, int isFree, String teacherId, int state, String networkLiveTime, String levelType, int catalogNum, double originalPrice, int isDel, int isIndex, int isCatalog, int isTeacher, Integer evaluateNum, int isPublic, int isBook, Double bookPrice, String enrolEndTime, int limitCount, String teachStartTime, String teachEndTime, String qrcode) {
        this.limitEndTime = limitEndTime;
        this.isLimitFree = isLimitFree;
        this.networkLiveLink = networkLiveLink;
        this.id = id;
        this.networkIntroduce = networkIntroduce;
        this.networkRecordLink = networkRecordLink;
        this.networkName = networkName;
        this.networkImgpath = networkImgpath;
        this.brief = brief;
        this.teacherIntroduce = teacherIntroduce;
        this.catalogIntroduce = catalogIntroduce;
        this.saleNum = saleNum;
        this.networkMoney = networkMoney;
        this.networkType = networkType;
        this.createtime = createtime;
        this.sort = sort;
        this.limitStartTime = limitStartTime;
        this.isFree = isFree;
        this.teacherId = teacherId;
        this.state = state;
        this.networkLiveTime = networkLiveTime;
        this.levelType = levelType;
        this.catalogNum = catalogNum;
        this.originalPrice = originalPrice;
        this.isDel = isDel;
        this.isIndex = isIndex;
        this.isCatalog = isCatalog;
        this.isTeacher = isTeacher;
        this.evaluateNum = evaluateNum;
        this.isPublic = isPublic;
        this.isBook = isBook;
        this.bookPrice = bookPrice;
        this.enrolEndTime = enrolEndTime;
        this.limitCount = limitCount;
        this.teachStartTime = teachStartTime;
        this.teachEndTime = teachEndTime;
        this.qrcode = qrcode;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }
    public int getIsBook() {
        return isBook;
    }

    public void setIsBook(int isBook) {
        this.isBook = isBook;
    }

    public void setIsLimitFree(Integer isLimitFree) {
        this.isLimitFree = isLimitFree;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public void setNetworkMoney(Double networkMoney) {
        this.networkMoney = networkMoney;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setCatalogNum(Integer catalogNum) {
        this.catalogNum = catalogNum;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
    }

    public void setIsCatalog(Integer isCatalog) {
        this.isCatalog = isCatalog;
    }

    public void setIsTeacher(Integer isTeacher) {
        this.isTeacher = isTeacher;
    }

    @Basic
    @Column(name = "is_teacher", nullable = true)
    public int getIsTeacher() {
		return isTeacher;
	}

	public void setIsTeacher(int isTeacher) {
		this.isTeacher = isTeacher;
	}

	@Basic
    @Column(name = "is_catalog", nullable = true)
    public int getIsCatalog() {
		return isCatalog;
	}

	public void setIsCatalog(int isCatalog) {
		this.isCatalog = isCatalog;
	}

	@Basic
    @Column(name = "catalog_introduce", nullable = true, length = -1)
    public String getCatalogIntroduce() {
		return catalogIntroduce;
	}

	public void setCatalogIntroduce(String catalogIntroduce) {
		this.catalogIntroduce = catalogIntroduce;
	}

	@Basic
    @Column(name = "is_index", nullable = true)
    public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	@Basic
    @Column(name = "is_del", nullable = true)
    public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	@Basic
    @Column(name = "network_live_time", nullable = true, length = 200)
    public String getNetworkLiveTime() {
		return networkLiveTime;
	}

	public void setNetworkLiveTime(String networkLiveTime) {
		this.networkLiveTime = networkLiveTime;
	}

	@Basic
    @Column(name = "level_type", nullable = true, length = 32)
    public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	@Basic
    @Column(name = "catalog_num", nullable = true)
    public int getCatalogNum() {
		return catalogNum;
	}

	public void setCatalogNum(int catalogNum) {
		this.catalogNum = catalogNum;
	}

	@Basic
    @Column(name = "original_price", nullable = true, precision = 0)
    public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Basic
    @Column(name = "state", nullable = true)
    public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Basic
    @Column(name = "teacher_id", nullable = true, length = 32)
    public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@Basic
    @Column(name = "limit_end_time", nullable = true, length = 50)
    public String getLimitEndTime(){
      return limitEndTime;
    }

    public void setLimitEndTime(String limitEndTime){
      this.limitEndTime = limitEndTime;
    }

    @Basic
    @Column(name = "is_limit_free", nullable = true)
    public int getIsLimitFree(){
      return isLimitFree;
    }

    public void setIsLimitFree(int isLimitFree){
      this.isLimitFree = isLimitFree;
    }

    @Basic
    @Column(name = "network_live_link", nullable = true, length = 200)
    public String getNetworkLiveLink(){
      return networkLiveLink;
    }

    public void setNetworkLiveLink(String networkLiveLink){
      this.networkLiveLink = networkLiveLink;
    }

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId(){
      return id;
    }

    public void setId(String id){
      this.id = id;
    }

    @Basic
    @Column(name = "network_introduce", nullable = true, length = -1)
    public String getNetworkIntroduce(){
      return networkIntroduce;
    }

    public void setNetworkIntroduce(String networkIntroduce){
      this.networkIntroduce = networkIntroduce;
    }

    @Basic
    @Column(name = "network_record_link", nullable = true, length = 200)
    public String getNetworkRecordLink(){
      return networkRecordLink;
    }

    public void setNetworkRecordLink(String networkRecordLink){
      this.networkRecordLink = networkRecordLink;
    }

    @Basic
    @Column(name = "network_name", nullable = true, length = 100)
    public String getNetworkName(){
      return networkName;
    }

    public void setNetworkName(String networkName){
      this.networkName = networkName;
    }

    @Basic
    @Column(name = "network_imgpath", nullable = true, length = 200)
    public String getNetworkImgpath(){
      return networkImgpath;
    }

    public void setNetworkImgpath(String networkImgpath){
      this.networkImgpath = networkImgpath;
    }

    @Basic
    @Column(name = "brief", nullable = true, length = 500)
    public String getBrief(){
      return brief;
    }

    public void setBrief(String brief){
      this.brief = brief;
    }

    @Basic
    @Column(name = "teacher_introduce", nullable = true, length = -1)
    public String getTeacherIntroduce(){
      return teacherIntroduce;
    }

    public void setTeacherIntroduce(String teacherIntroduce){
      this.teacherIntroduce = teacherIntroduce;
    }

    @Basic
    @Column(name = "sale_num", nullable = true)
    public int getSaleNum(){
      return saleNum;
    }

    public void setSaleNum(int saleNum){
      this.saleNum = saleNum;
    }

    @Basic
    @Column(name = "network_money", nullable = true, precision = 0)
    public double getNetworkMoney(){
      return networkMoney;
    }

    public void setNetworkMoney(double networkMoney){
      this.networkMoney = networkMoney;
    }

    @Basic
    @Column(name = "network_type", nullable = true)
    public int getNetworkType(){
      return networkType;
    }

    public void setNetworkType(int networkType){
      this.networkType = networkType;
    }

    @Basic
    @Column(name = "createtime", nullable = true, length = 50)
    public String getCreatetime(){
      return createtime;
    }

    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }

    @Basic
    @Column(name = "sort", nullable = true)
    public int getSort(){
      return sort;
    }

    public void setSort(int sort){
      this.sort = sort;
    }

    @Basic
    @Column(name = "limit_start_time", nullable = true, length = 50)
    public String getLimitStartTime(){
      return limitStartTime;
    }

    public void setLimitStartTime(String limitStartTime){
      this.limitStartTime = limitStartTime;
    }

    @Basic
    @Column(name = "is_free", nullable = true)
    public int getIsFree(){
      return isFree;
    }

    public void setIsFree(int isFree){
      this.isFree = isFree;
    }

    @Basic
    @Column(name = "evaluate_num", nullable = true)
    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    @Basic
    @Column(name = "book_price", nullable = true, length = 50)
    public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

    @Basic
    @Column(name = "enrol_end_time", nullable = true)
	public String getEnrolEndTime() {
		return enrolEndTime;
	}

	public void setEnrolEndTime(String enrolEndTime) {
		this.enrolEndTime = enrolEndTime;
	}

    @Basic
    @Column(name = "teach_start_time", nullable = true)
	public String getTeachStartTime() {
		return teachStartTime;
	}

	public void setTeachStartTime(String teachStartTime) {
		this.teachStartTime = teachStartTime;
	}

    @Basic
    @Column(name = "teach_end_time", nullable = true)
	public String getTeachEndTime() {
		return teachEndTime;
	}

	public void setTeachEndTime(String teachEndTime) {
		this.teachEndTime = teachEndTime;
	}

    @Basic
    @Column(name = "limit_count", nullable = true)
	public int getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	@Basic
    @Column(name = "qrcode", nullable = true)
	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkVideo that = (NetworkVideo) o;

        if (isLimitFree != that.isLimitFree) return false;
        if (saleNum != that.saleNum) return false;
        if (Double.compare(that.networkMoney, networkMoney) != 0) return false;
        if (networkType != that.networkType) return false;
        if (sort != that.sort) return false;
        if (isFree != that.isFree) return false;
        if (state != that.state) return false;
        if (catalogNum != that.catalogNum) return false;
        if (Double.compare(that.originalPrice, originalPrice) != 0) return false;
        if (isDel != that.isDel) return false;
        if (isIndex != that.isIndex) return false;
        if (isCatalog != that.isCatalog) return false;
        if (isTeacher != that.isTeacher) return false;
        if (limitEndTime != null ? !limitEndTime.equals(that.limitEndTime) : that.limitEndTime != null) return false;
        if (networkLiveLink != null ? !networkLiveLink.equals(that.networkLiveLink) : that.networkLiveLink != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (networkIntroduce != null ? !networkIntroduce.equals(that.networkIntroduce) : that.networkIntroduce != null)
            return false;
        if (networkRecordLink != null ? !networkRecordLink.equals(that.networkRecordLink) : that.networkRecordLink != null)
            return false;
        if (networkName != null ? !networkName.equals(that.networkName) : that.networkName != null) return false;
        if (networkImgpath != null ? !networkImgpath.equals(that.networkImgpath) : that.networkImgpath != null)
            return false;
        if (brief != null ? !brief.equals(that.brief) : that.brief != null) return false;
        if (teacherIntroduce != null ? !teacherIntroduce.equals(that.teacherIntroduce) : that.teacherIntroduce != null)
            return false;
        if (catalogIntroduce != null ? !catalogIntroduce.equals(that.catalogIntroduce) : that.catalogIntroduce != null)
            return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (limitStartTime != null ? !limitStartTime.equals(that.limitStartTime) : that.limitStartTime != null)
            return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (networkLiveTime != null ? !networkLiveTime.equals(that.networkLiveTime) : that.networkLiveTime != null)
            return false;
        if (levelType != null ? !levelType.equals(that.levelType) : that.levelType != null) return false;
        if (evaluateNum != null ? !evaluateNum.equals(that.evaluateNum) : that.evaluateNum != null) return false;
        if (qrcode != null?!qrcode.equals(that.qrcode):that.qrcode!=null)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (networkName != null ? networkName.hashCode() : 0);
        result = 31 * result + (brief != null ? brief.hashCode() : 0);
        result = 31 * result + (networkIntroduce != null ? networkIntroduce.hashCode() : 0);
        result = 31 * result + (teacherIntroduce != null ? teacherIntroduce.hashCode() : 0);
        result = 31 * result + (catalogIntroduce != null ? catalogIntroduce.hashCode() : 0);
        result = 31 * result + (networkImgpath != null ? networkImgpath.hashCode() : 0);
        temp = Double.doubleToLongBits(originalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(networkMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + networkType;
        result = 31 * result + (networkLiveLink != null ? networkLiveLink.hashCode() : 0);
        result = 31 * result + (networkLiveTime != null ? networkLiveTime.hashCode() : 0);
        result = 31 * result + (networkRecordLink != null ? networkRecordLink.hashCode() : 0);
        result = 31 * result + saleNum;
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + sort;
        result = 31 * result + isFree;
        result = 31 * result + isLimitFree;
        result = 31 * result + (limitStartTime != null ? limitStartTime.hashCode() : 0);
        result = 31 * result + (limitEndTime != null ? limitEndTime.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (levelType != null ? levelType.hashCode() : 0);
        result = 31 * result + catalogNum;
        result = 31 * result + isIndex;
        result = 31 * result + isDel;
        result = 31 * result + (evaluateNum != null ? evaluateNum.hashCode() : 0);
        result = 31 * result + isTeacher;
        result = 31 * result + isCatalog;
        result = 31 * result + (qrcode!=null?qrcode.hashCode():0);
        return result;
    }
}
