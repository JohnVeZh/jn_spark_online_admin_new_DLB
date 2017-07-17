package com.business.v2.vo;

import java.util.Date;

import com.easecom.common.framework.struts.BaseForm;

/**
 * TbSpecialExplain entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialExplainForm extends BaseForm {
	private static final long serialVersionUID = -1023353925495412716L;
	
	// Fields
	private String id;
	private String section;
	private String trainingType;
	private String title;
	private String contentType;
	private String content;
	private String contentId;
	private Integer visitNum;
	private Date createTime;
	private String contentName;

	// Constructors

	/** default constructor */
	public TbSpecialExplainForm() {
	}

	/** minimal constructor */
	public TbSpecialExplainForm(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialExplainForm(String id, String section, String trainingType,
			String title, String contentType, String content, String contentId,
			Integer visitNum, Date createTime) {
		this.id = id;
		this.section = section;
		this.trainingType = trainingType;
		this.title = title;
		this.contentType = contentType;
		this.content = content;
		this.contentId = contentId;
		this.visitNum = visitNum;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTrainingType() {
		return this.trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Integer getVisitNum() {
		return this.visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
}