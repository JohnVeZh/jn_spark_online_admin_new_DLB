package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialCollectionDetail entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialCollectionDetail extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String collectionId;
	private String userId;
	private String questionId;
	private String optAnswer;
	private String optAnswerPrefix;
	private String isDel;
	private Integer sortOrder;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialCollectionDetail() {
	}

	/** minimal constructor */
	public TbSpecialCollectionDetail(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialCollectionDetail(String id, String collectionId,
			String userId, String questionId, String optAnswer,
			String optAnswerPrefix, String isDel, Integer sortOrder,
			Date createTime) {
		this.id = id;
		this.collectionId = collectionId;
		this.userId = userId;
		this.questionId = questionId;
		this.optAnswer = optAnswer;
		this.optAnswerPrefix = optAnswerPrefix;
		this.isDel = isDel;
		this.sortOrder = sortOrder;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getOptAnswer() {
		return this.optAnswer;
	}

	public void setOptAnswer(String optAnswer) {
		this.optAnswer = optAnswer;
	}

	public String getOptAnswerPrefix() {
		return this.optAnswerPrefix;
	}

	public void setOptAnswerPrefix(String optAnswerPrefix) {
		this.optAnswerPrefix = optAnswerPrefix;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}