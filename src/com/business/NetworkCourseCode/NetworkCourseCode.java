package com.business.NetworkCourseCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easecom.common.framework.struts.BaseForm;

/**
 * @author xs
 *
 */
@Entity
@Table(name = "network_course_code", schema = "jn_spark_online", catalog = "")
public class NetworkCourseCode extends BaseForm  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nvId;
	private BigDecimal amount;
	private Timestamp startTime;
	private Timestamp endTime;
	private String userId;
	private int isExport;
	private int isEnable;
	private Timestamp createTime;
	
	//展现网课部分信息
	private String ncName;
	private int ncType;
	private String ncLevelType;
	private String ncLevel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNvId() {
		return nvId;
	}
	public void setNvId(String nvId) {
		this.nvId = nvId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getIsExport() {
		return isExport;
	}
	public void setIsExport(int isExport) {
		this.isExport = isExport;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getNcName() {
		return ncName;
	}
	public void setNcName(String ncName) {
		this.ncName = ncName;
	}
	public int getNcType() {
		return ncType;
	}
	public void setNcType(int ncType) {
		this.ncType = ncType;
	}
	public String getNcLevelType() {
		return ncLevelType;
	}
	public void setNcLevelType(String ncLevelType) {
		this.ncLevelType = ncLevelType;
	}
	public String getNcLevel() {
		return ncLevel;
	}
	public void setNcLevel(String ncLevel) {
		this.ncLevel = ncLevel;
	}
	@Override
	public String toString() {
		return "NetworkCourseCode [id=" + id + ", nvId=" + nvId + ", amount="
				+ amount + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", userId=" + userId + ", isExport=" + isExport
				+ ", isEnable=" + isEnable + ", createTime=" + createTime
				+ ", ncName=" + ncName + ", ncType=" + ncType
				+ ", ncLevelType=" + ncLevelType + ", ncLevel=" + ncLevel + "]";
	}
	
}
