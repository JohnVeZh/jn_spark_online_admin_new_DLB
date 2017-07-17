package com.business.v2.question;

import java.util.Date;

/**
 * TbQuestionTranslation entity. @author MyEclipse Persistence Tools
 */

public class TbQuestionTranslation extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String content;
	private String reference;
	private String analysis;
	private String analysisUrl;
	private String target;
	private String isDel;
	private Date createTime;

	private String name;
	private String sortOrder;
	private String catalogId;
	// Constructors

	/** default constructor */
	public TbQuestionTranslation() {
	}

	/** minimal constructor */
	public TbQuestionTranslation(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbQuestionTranslation(String id, String title, String content,
			String reference, String analysis, String analysisUrl,
			String target, String isDel, Date createTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.reference = reference;
		this.analysis = analysis;
		this.analysisUrl = analysisUrl;
		this.target = target;
		this.isDel = isDel;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getAnalysisUrl() {
		return this.analysisUrl;
	}

	public void setAnalysisUrl(String analysisUrl) {
		this.analysisUrl = analysisUrl;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	
}