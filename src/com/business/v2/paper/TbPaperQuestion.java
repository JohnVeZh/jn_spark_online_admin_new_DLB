package com.business.v2.paper;

/**
 * TbPaperQuestion entity. @author MyEclipse Persistence Tools
 */

public class TbPaperQuestion extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String paperId;
	private String partId;
	private String partType;
	private String sectionId;
	private String trainingId;
	private String title;
	private Double score;
	private Integer startIndex;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbPaperQuestion() {
	}

	/** minimal constructor */
	public TbPaperQuestion(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbPaperQuestion(String id, String paperId, String partId,
			String partType, String sectionId, String trainingId, String title,
			Double score, Integer startIndex, Integer sortOrder) {
		this.id = id;
		this.paperId = paperId;
		this.partId = partId;
		this.partType = partType;
		this.sectionId = sectionId;
		this.trainingId = trainingId;
		this.title = title;
		this.score = score;
		this.startIndex = startIndex;
		this.sortOrder = sortOrder;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaperId() {
		return this.paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPartId() {
		return this.partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartType() {
		return this.partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getTrainingId() {
		return this.trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getStartIndex() {
		return this.startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}