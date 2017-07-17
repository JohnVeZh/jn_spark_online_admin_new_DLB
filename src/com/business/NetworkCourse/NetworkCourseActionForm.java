package com.business.NetworkCourse;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.struts.BaseForm;
/**
 * @author xs
 *
 */
@Entity
@Table(name = "network_course", schema = "jn_spark_online", catalog = "")
public class NetworkCourseActionForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String ncName;
	private String ncBrief;
	private String ncIntroduce;
	private String teacherId;
	private String ncTeacher;
	private String teacherIntroduce;
	private String catalogIntroduce;
	private int catalogNumber;
	private String ncLogo;
	private String ncImg;
	private Double originalPrice;
	private Double currentPrice;
	private int ncType;
	private String ncLiveLink;
	private Timestamp ncLiveTime;
	private Timestamp ncEndTime;
	private String ncPreviewLink;
	private String ncRecordLink;
	private Timestamp reserveTime; 
	private Timestamp saleTime; 
	private Timestamp saleEndTime;
	private int limitNumber;
	private int reserveNumber;
	private int saleNumber;
	private int ncState;
	private String ncLevel;
	private String ncLevelType;
	private int sort;
	private int isFree;
	private int isLimitFree;
	private Timestamp limitStartTime;
	private Timestamp limitEndTime;
	private int isIndex;
	private int isDelete;
	private int evaluateCount;
	private int isTeacher;
	private int isCatalog;
	private int isPublic;
	private int isGiftBook;
	private Double giftBookPrice;
	private Timestamp createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNcName() {
		return ncName;
	}
	public void setNcName(String ncName) {
		this.ncName = ncName;
	}
	public String getNcBrief() {
		return ncBrief;
	}
	public void setNcBrief(String ncBrief) {
		this.ncBrief = ncBrief;
	}
	public String getNcIntroduce() {
		return ncIntroduce;
	}
	public void setNcIntroduce(String ncIntroduce) {
		this.ncIntroduce = ncIntroduce;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getNcTeacher() {
		return ncTeacher;
	}
	public void setNcTeacher(String ncTeacher) {
		this.ncTeacher = ncTeacher;
	}
	public String getTeacherIntroduce() {
		return teacherIntroduce;
	}
	public void setTeacherIntroduce(String teacherIntroduce) {
		this.teacherIntroduce = teacherIntroduce;
	}
	public String getCatalogIntroduce() {
		return catalogIntroduce;
	}
	public void setCatalogIntroduce(String catalogIntroduce) {
		this.catalogIntroduce = catalogIntroduce;
	}
	public int getCatalogNumber() {
		return catalogNumber;
	}
	public void setCatalogNumber(int catalogNumber) {
		this.catalogNumber = catalogNumber;
	}
	public String getNcLogo() {
		return ncLogo;
	}
	public void setNcLogo(String ncLogo) {
		this.ncLogo = ncLogo;
	}
	public String getNcImg() {
		return ncImg;
	}
	public void setNcImg(String ncImg) {
		this.ncImg = ncImg;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getNcType() {
		return ncType;
	}
	public void setNcType(int ncType) {
		this.ncType = ncType;
	}
	public String getNcLiveLink() {
		return ncLiveLink;
	}
	public void setNcLiveLink(String ncLiveLink) {
		this.ncLiveLink = ncLiveLink;
	}
	public Timestamp getNcLiveTime() {
		return ncLiveTime;
	}
	public void setNcLiveTime(Timestamp ncLiveTime) {
		this.ncLiveTime = ncLiveTime;
	}
	public Timestamp getNcEndTime() {
		return ncEndTime;
	}
	public void setNcEndTime(Timestamp ncEndTime) {
		this.ncEndTime = ncEndTime;
	}
	public String getNcPreviewLink() {
		return ncPreviewLink;
	}
	public void setNcPreviewLink(String ncPreviewLink) {
		this.ncPreviewLink = ncPreviewLink;
	}
	public String getNcRecordLink() {
		return ncRecordLink;
	}
	public void setNcRecordLink(String ncRecordLink) {
		this.ncRecordLink = ncRecordLink;
	}
	public Timestamp getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}
	public Timestamp getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(Timestamp saleTime) {
		this.saleTime = saleTime;
	}
	public Timestamp getSaleEndTime() {
		return saleEndTime;
	}
	public void setSaleEndTime(Timestamp saleEndTime) {
		this.saleEndTime = saleEndTime;
	}
	public int getLimitNumber() {
		return limitNumber;
	}
	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}
	public int getReserveNumber() {
		return reserveNumber;
	}
	public void setReserveNumber(int reserveNumber) {
		this.reserveNumber = reserveNumber;
	}
	public int getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}
	public int getNcState() {
		return ncState;
	}
	public void setNcState(int ncState) {
		this.ncState = ncState;
	}
	public String getNcLevel() {
		return ncLevel;
	}
	public void setNcLevel(String ncLevel) {
		this.ncLevel = ncLevel;
	}
	public String getNcLevelType() {
		return ncLevelType;
	}
	public void setNcLevelType(String ncLevelType) {
		this.ncLevelType = ncLevelType;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	public int getIsLimitFree() {
		return isLimitFree;
	}
	public void setIsLimitFree(int isLimitFree) {
		this.isLimitFree = isLimitFree;
	}
	public Timestamp getLimitStartTime() {
		return limitStartTime;
	}
	public void setLimitStartTime(Timestamp limitStartTime) {
		this.limitStartTime = limitStartTime;
	}
	public Timestamp getLimitEndTime() {
		return limitEndTime;
	}
	public void setLimitEndTime(Timestamp limitEndTime) {
		this.limitEndTime = limitEndTime;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getEvaluateCount() {
		return evaluateCount;
	}
	public void setEvaluateCount(int evaluateCount) {
		this.evaluateCount = evaluateCount;
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
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public int getIsGiftBook() {
		return isGiftBook;
	}
	public void setIsGiftBook(int isGiftBook) {
		this.isGiftBook = isGiftBook;
	}
	public Double getGiftBookPrice() {
		return giftBookPrice;
	}
	public void setGiftBookPrice(Double giftBookPrice) {
		this.giftBookPrice = giftBookPrice;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "NetworkCourse [id=" + id + ", ncName=" + ncName + ", ncBrief="
				+ ncBrief + ", ncIntroduce=" + ncIntroduce + ", teacherId="
				+ teacherId + ", ncTeacher=" + ncTeacher
				+ ", teacherIntroduce=" + teacherIntroduce
				+ ", catalogIntroduce=" + catalogIntroduce + ", catalogNumber="
				+ catalogNumber + ", ncImg=" + ncImg + ", originalPrice="
				+ originalPrice + ", currentPrice=" + currentPrice
				+ ", ncType=" + ncType + ", ncLiveLink=" + ncLiveLink
				+ ", ncLiveTime=" + ncLiveTime + ", ncEndTime=" + ncEndTime
				+ ", ncPreviewLink=" + ncPreviewLink + ", ncRecordLink="
				+ ncRecordLink + ", reserveTime=" + reserveTime + ", saleTime="
				+ saleTime + ", saleEndTime=" + saleEndTime + ", limitNumber="
				+ limitNumber + ", reserveNumber=" + reserveNumber
				+ ", saleNumber=" + saleNumber + ", ncState=" + ncState
				+ ", ncLevel=" + ncLevel + ", ncLevelType=" + ncLevelType
				+ ", sort=" + sort + ", isFree=" + isFree + ", isLimitFree="
				+ isLimitFree + ", limitStartTime=" + limitStartTime
				+ ", limitEndTime=" + limitEndTime + ", isIndex=" + isIndex
				+ ", isDelete=" + isDelete + ", evaluateCount=" + evaluateCount
				+ ", isTeacher=" + isTeacher + ", isCatalog=" + isCatalog
				+ ", isPublic=" + isPublic + ", isGiftBook=" + isGiftBook
				+ ", giftBookPrice=" + giftBookPrice + ", createTime="
				+ createTime + "]";
	}

}
