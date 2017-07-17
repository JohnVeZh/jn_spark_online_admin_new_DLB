package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialReport entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialReport extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String catalogId;
	private String userId;
	private String trainingType;
	private String trainingId;
	private Integer durationSeconds;
	private Integer totalNum;
	private Integer replyNum;
	private Integer correctNum;
	private Integer wrongNum;
	private String difficultyLevel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialReport() {
	}

	/** minimal constructor */
	public TbSpecialReport(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialReport(String id, String section, String catalogId,
			String userId, String trainingType, String trainingId,
			Integer durationSeconds, Integer totalNum, Integer replyNum,
			Integer correctNum, Integer wrongNum, String difficultyLevel,
			Date createTime) {
		this.id = id;
		this.section = section;
		this.catalogId = catalogId;
		this.userId = userId;
		this.trainingType = trainingType;
		this.trainingId = trainingId;
		this.durationSeconds = durationSeconds;
		this.totalNum = totalNum;
		this.replyNum = replyNum;
		this.correctNum = correctNum;
		this.wrongNum = wrongNum;
		this.difficultyLevel = difficultyLevel;
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

	public Integer getDurationSeconds() {
		return this.durationSeconds;
	}

	public void setDurationSeconds(Integer durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getReplyNum() {
		return this.replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Integer getCorrectNum() {
		return this.correctNum;
	}

	public void setCorrectNum(Integer correctNum) {
		this.correctNum = correctNum;
	}

	public Integer getWrongNum() {
		return this.wrongNum;
	}

	public void setWrongNum(Integer wrongNum) {
		this.wrongNum = wrongNum;
	}

	public String getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}