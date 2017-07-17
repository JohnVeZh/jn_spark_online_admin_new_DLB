package com.business.v2.vo;

import java.util.Date;

import com.easecom.common.framework.struts.BaseForm;

/**
 * TbSpecialSuggestion entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialSuggestionForm extends BaseForm {

	// Fields

	private String id;
	private String section;
	private String trainingType;
	private Float start;
	private Float end;
	private String analysis;
	private String suggestion;
	private String isDel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialSuggestionForm() {
	}

	/** minimal constructor */
	public TbSpecialSuggestionForm(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialSuggestionForm(String id, String section, String trainingType,
			Float start, Float end, String analysis, String suggestion,
			String isDel, Date createTime) {
		this.id = id;
		this.section = section;
		this.trainingType = trainingType;
		this.start = start;
		this.end = end;
		this.analysis = analysis;
		this.suggestion = suggestion;
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

	public Float getStart() {
		return this.start;
	}

	public void setStart(Float start) {
		this.start = start;
	}

	public Float getEnd() {
		return this.end;
	}

	public void setEnd(Float end) {
		this.end = end;
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
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

}