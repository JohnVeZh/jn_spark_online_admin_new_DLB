package com.business.v2.special;

/**
 * TbSpecialReportDetail entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialReportDetail extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String reportId;
	private String userId;
	private String questionId;
	private String optAnswer;
	private String optAnswerPrefix;
	private String optCorrect;
	private String optCorrectPrefix;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbSpecialReportDetail() {
	}

	/** minimal constructor */
	public TbSpecialReportDetail(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialReportDetail(String id, String reportId, String userId,
			String questionId, String optAnswer, String optAnswerPrefix,
			String optCorrect, String optCorrectPrefix, Integer sortOrder) {
		this.id = id;
		this.reportId = reportId;
		this.userId = userId;
		this.questionId = questionId;
		this.optAnswer = optAnswer;
		this.optAnswerPrefix = optAnswerPrefix;
		this.optCorrect = optCorrect;
		this.optCorrectPrefix = optCorrectPrefix;
		this.sortOrder = sortOrder;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
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

	public String getOptCorrect() {
		return this.optCorrect;
	}

	public void setOptCorrect(String optCorrect) {
		this.optCorrect = optCorrect;
	}

	public String getOptCorrectPrefix() {
		return this.optCorrectPrefix;
	}

	public void setOptCorrectPrefix(String optCorrectPrefix) {
		this.optCorrectPrefix = optCorrectPrefix;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}