package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialCollection entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialCollection extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String catalogId;
	private String userId;
	private String trainingType;
	private String trainingId;
	private Integer collectNum;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialCollection() {
	}

	/** minimal constructor */
	public TbSpecialCollection(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialCollection(String id, String section, String catalogId,
			String userId, String trainingType, String trainingId,
			Integer collectNum, Date createTime) {
		this.id = id;
		this.section = section;
		this.catalogId = catalogId;
		this.userId = userId;
		this.trainingType = trainingType;
		this.trainingId = trainingId;
		this.collectNum = collectNum;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTrainingType() {
		return this.trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getTrainingId() {
		return this.trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public Integer getCollectNum() {
		return this.collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}