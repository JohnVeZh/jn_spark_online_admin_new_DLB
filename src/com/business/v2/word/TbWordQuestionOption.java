package com.business.v2.word;

/**
 * TbWordQuestionOption entity. @author MyEclipse Persistence Tools
 */

public class TbWordQuestionOption extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String questionId;
	private String prefix;
	private String content;
	private String isAnswer;

	// Constructors

	/** default constructor */
	public TbWordQuestionOption() {
	}

	/** minimal constructor */
	public TbWordQuestionOption(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbWordQuestionOption(String id, String questionId, String prefix,
			String content, String isAnswer) {
		this.id = id;
		this.questionId = questionId;
		this.prefix = prefix;
		this.content = content;
		this.isAnswer = isAnswer;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsAnswer() {
		return this.isAnswer;
	}

	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}

}