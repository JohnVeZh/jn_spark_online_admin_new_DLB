package com.business.v2.special;

/**
 * TbSpecialWordReport entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialWordReport extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String userId;
	private String groupId;
	private Integer totalNum;
	private Integer correctNum;
	private Integer wrongNum;

	// Constructors

	/** default constructor */
	public TbSpecialWordReport() {
	}

	/** minimal constructor */
	public TbSpecialWordReport(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialWordReport(String id, String section, String userId,
			String groupId, Integer totalNum, Integer correctNum,
			Integer wrongNum) {
		this.id = id;
		this.section = section;
		this.userId = userId;
		this.groupId = groupId;
		this.totalNum = totalNum;
		this.correctNum = correctNum;
		this.wrongNum = wrongNum;
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

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
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

}