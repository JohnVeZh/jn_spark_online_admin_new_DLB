package com.business.v2.word;

/**
 * TbWordQuestion entity. @author MyEclipse Persistence Tools
 */

public class TbWordQuestion extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String wordId;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public TbWordQuestion() {
	}

	/** minimal constructor */
	public TbWordQuestion(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbWordQuestion(String id, String wordId, Integer sortOrder) {
		this.id = id;
		this.wordId = wordId;
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

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}