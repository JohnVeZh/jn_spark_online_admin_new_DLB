package com.business.v2.question;

/**
 * TbQuestionListeningQuestion entity. @author MyEclipse Persistence Tools
 */

public class TbQuestionListeningQuestion extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String listeningId;
	private String title;
	private String analysis;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbQuestionListeningQuestion() {
	}

	/** minimal constructor */
	public TbQuestionListeningQuestion(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbQuestionListeningQuestion(String id, String listeningId,
			String title, String analysis, Integer sortOrder) {
		this.id = id;
		this.listeningId = listeningId;
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

	public String getListeningId() {
		return this.listeningId;
	}

	public void setListeningId(String listeningId) {
		this.listeningId = listeningId;
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