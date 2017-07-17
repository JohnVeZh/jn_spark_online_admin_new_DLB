package com.business.v2.paper;

/**
 * TbPaperPart entity. @author MyEclipse Persistence Tools
 */

public class TbPaperPart extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String paperId;
	private String partType;
	private String title;
	private String name;
	private String times;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbPaperPart() {
	}

	/** minimal constructor */
	public TbPaperPart(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbPaperPart(String id, String paperId, String partType,
			String title, String name, String times, Integer sortOrder) {
		this.id = id;
		this.paperId = paperId;
		this.partType = partType;
		this.title = title;
		this.name = name;
		this.times = times;
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

	public String getPartType() {
		return this.partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimes() {
		return this.times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}