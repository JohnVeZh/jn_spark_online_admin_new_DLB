package com.business.v2.question;

import java.util.Date;

/**
 * TbQuestionListening entity. @author MyEclipse Persistence Tools
 */

public class TbQuestionListening extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String audioUrl;
	private Integer questionQuantity;
	private String tapescripts;
	private String translation;
	private String target;
	private String isDel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbQuestionListening() {
	}

	/** minimal constructor */
	public TbQuestionListening(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbQuestionListening(String id, String title, String audioUrl,
			Integer questionQuantity, String tapescripts, String translation,
			String target, String isDel, Date createTime) {
		this.id = id;
		this.title = title;
		this.audioUrl = audioUrl;
		this.questionQuantity = questionQuantity;
		this.tapescripts = tapescripts;
		this.translation = translation;
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

	public String getAudioUrl() {
		return this.audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public Integer getQuestionQuantity() {
		return this.questionQuantity;
	}

	public void setQuestionQuantity(Integer questionQuantity) {
		this.questionQuantity = questionQuantity;
	}

	public String getTapescripts() {
		return this.tapescripts;
	}

	public void setTapescripts(String tapescripts) {
		this.tapescripts = tapescripts;
	}

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
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

}