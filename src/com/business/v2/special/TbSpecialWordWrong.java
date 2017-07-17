package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialWordWrong entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialWordWrong extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String userId;
	private String wordId;
	private String isDel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialWordWrong() {
	}

	/** minimal constructor */
	public TbSpecialWordWrong(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialWordWrong(String id, String section, String userId,
			String wordId, String isDel, Date createTime) {
		this.id = id;
		this.section = section;
		this.userId = userId;
		this.wordId = wordId;
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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWordId() {
		return this.wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;
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