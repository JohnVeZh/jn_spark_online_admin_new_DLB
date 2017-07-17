package com.business.v2.question;

/**
 * TbQuestionReadingQuestion entity. @author MyEclipse Persistence Tools
 */

public class TbQuestionReadingQuestion extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String readingId;
	private String title;
	private String analysis;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbQuestionReadingQuestion() {
	}

	/** minimal constructor */
	public TbQuestionReadingQuestion(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbQuestionReadingQuestion(String id, String readingId, String title,
			String analysis, Integer sortOrder) {
		this.id = id;
		this.readingId = readingId;
		this.title = title;
		this.analysis = analysis;
		this.sortOrder = sortOrder;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReadingId() {
		return this.readingId;
	}

	public void setReadingId(String readingId) {
		this.readingId = readingId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}