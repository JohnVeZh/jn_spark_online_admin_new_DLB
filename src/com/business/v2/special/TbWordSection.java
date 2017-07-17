package com.business.v2.special;

/**
 * TbWordSection entity. @author MyEclipse Persistence Tools
 */

public class TbWordSection extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String wordId;
	private String section;
	private String catalogId;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbWordSection() {
	}

	/** minimal constructor */
	public TbWordSection(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbWordSection(String id, String wordId, String section,
			String catalogId, Integer sortOrder) {
		this.id = id;
		this.wordId = wordId;
		this.section = section;
		this.catalogId = catalogId;
		this.sortOrder = sortOrder;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWordId() {
		return this.wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;
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

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}