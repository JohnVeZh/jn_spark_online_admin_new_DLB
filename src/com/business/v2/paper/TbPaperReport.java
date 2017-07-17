package com.business.v2.paper;

import java.util.Date;

/**
 * TbPaperReport entity. @author MyEclipse Persistence Tools
 */

public class TbPaperReport extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String paperId;
	private String section;
	private String userId;
	private Double score;
	private Integer durationSeconds;
	private Integer totalNum;
	private Integer replyNum;
	private Integer correctNum;
	private Integer wrongNum;
	private String answerSheetJson;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbPaperReport() {
	}

	/** minimal constructor */
	public TbPaperReport(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbPaperReport(String id, String paperId, String section,
			String userId, Double score, Integer durationSeconds,
			Integer totalNum, Integer replyNum, Integer correctNum,
			Integer wrongNum, String answerSheetJson, String content,
			Date createTime) {
		this.id = id;
		this.paperId = paperId;
		this.section = section;
		this.userId = userId;
		this.score = score;
		this.durationSeconds = durationSeconds;
		this.totalNum = totalNum;
		this.replyNum = replyNum;
		this.correctNum = correctNum;
		this.wrongNum = wrongNum;
		this.answerSheetJson = answerSheetJson;
		this.content = content;
		this.createTime = createTime;
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

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getDurationSeconds() {
		return this.durationSeconds;
	}

	public void setDurationSeconds(Integer durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getReplyNum() {
		return this.replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Integer getCorrectNum() {
		return this.correctNum;
	}

	public void setCorrectNum(Integer correctNum) {
		this.correctNum = correctNum;
	}

	public Integer getWrongNum() {
		return this.wrongNum;
	}

	public void setWrongNum(Integer wrongNum) {
		this.wrongNum = wrongNum;
	}

	public String getAnswerSheetJson() {
		return this.answerSheetJson;
	}

	public void setAnswerSheetJson(String answerSheetJson) {
		this.answerSheetJson = answerSheetJson;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}