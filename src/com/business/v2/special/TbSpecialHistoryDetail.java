package com.business.v2.special;

/**
 * TbSpecialHistoryDetail entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialHistoryDetail extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String historyId;
	private String userId;
	private String questionId;
	private String optAnswer;
	private String optAnswerPrefix;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbSpecialHistoryDetail() {
	}

	/** minimal constructor */
	public TbSpecialHistoryDetail(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialHistoryDetail(String id, String historyId, String userId,
			String questionId, String optAnswer, String optAnswerPrefix,
			Integer sortOrder) {
		this.id = id;
		this.historyId = historyId;
		this.userId = userId;
		this.questionId = questionId;
		this.optAnswer = optAnswer;
		this.optAnswerPrefix = optAnswerPrefix;
		this.sortOrder = sortOrder;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
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

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}