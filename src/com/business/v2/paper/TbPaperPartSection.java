package com.business.v2.paper;

/**
 * TbPaperPartSection entity. @author MyEclipse Persistence Tools
 */

public class TbPaperPartSection extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String paperId;
	private String partId;
	private String sectionType;
	private String title;
	private String directions;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbPaperPartSection() {
	}

	/** minimal constructor */
	public TbPaperPartSection(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbPaperPartSection(String id, String paperId, String partId,
			String sectionType, String title, String directions,
			Integer sortOrder) {
		this.id = id;
		this.paperId = paperId;
		this.partId = partId;
		this.sectionType = sectionType;
		this.title = title;
		this.directions = directions;
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

	public String getSectionType() {
		return this.sectionType;
	}

	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirections() {
		return this.directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}