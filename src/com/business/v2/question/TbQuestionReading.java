package com.business.v2.question;

import java.util.Date;

/**
 * TbQuestionReading entity. @author MyEclipse Persistence Tools
 */

public class TbQuestionReading extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String content;
	private Integer questionQuantity;
	private String translation;
	private String target;
	private String isDel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbQuestionReading() {
	}

	/** minimal constructor */
	public TbQuestionReading(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbQuestionReading(String id, String title, String content,
			Integer questionQuantity, String translation, String target,
			String isDel, Date createTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.questionQuantity = questionQuantity;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getQuestionQuantity() {
		return this.questionQuantity;
	}

	public void setQuestionQuantity(Integer questionQuantity) {
		this.questionQuantity = questionQuantity;
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