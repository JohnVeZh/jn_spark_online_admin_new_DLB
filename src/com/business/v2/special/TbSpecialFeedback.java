package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialFeedback entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialFeedback extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String trainingType;
	private String trainingId;
	private String problemType;
	private String content;
	private String userId;
	private String isSolved;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialFeedback() {
	}

	/** minimal constructor */
	public TbSpecialFeedback(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialFeedback(String id, String section, String trainingType,
			String trainingId, String problemType, String content,
			String userId, String isSolved, Date createTime) {
		this.id = id;
		this.section = section;
		this.trainingType = trainingType;
		this.trainingId = trainingId;
		this.problemType = problemType;
		this.content = content;
		this.userId = userId;
		this.isSolved = isSolved;
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

	public String getProblemType() {
		return this.problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsSolved() {
		return this.isSolved;
	}

	public void setIsSolved(String isSolved) {
		this.isSolved = isSolved;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}