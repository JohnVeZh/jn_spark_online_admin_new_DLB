package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialWordHistory entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialWordHistory extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String userId;
	private String groupId;
	private String content;
	private Integer totalNum;
	private Integer replyNum;
	private Integer correctNum;
	private Integer wrongNum;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialWordHistory() {
	}

	/** minimal constructor */
	public TbSpecialWordHistory(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialWordHistory(String id, String section, String userId,
			String groupId, String content, Integer totalNum, Integer replyNum,
			Integer correctNum, Integer wrongNum, Date createTime) {
		this.id = id;
		this.section = section;
		this.userId = userId;
		this.groupId = groupId;
		this.content = content;
		this.totalNum = totalNum;
		this.replyNum = replyNum;
		this.correctNum = correctNum;
		this.wrongNum = wrongNum;
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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}