package com.business.networkplay;

import java.util.Date;

/**
 * NetworkPlayCount entity. @author MyEclipse Persistence Tools
 */

public class NetworkPlayCount extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private String networkId;
	private String networkVideoId;
	private String networkRecordLinkId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public NetworkPlayCount() {
	}

	/** full constructor */
	public NetworkPlayCount(String userId, String networkId,
			String networkVideoId, String networkRecordLinkId,
			Date createTime) {
		this.userId = userId;
		this.networkId = networkId;
		this.networkVideoId = networkVideoId;
		this.networkRecordLinkId = networkRecordLinkId;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getNetworkVideoId() {
		return this.networkVideoId;
	}

	public void setNetworkVideoId(String networkVideoId) {
		this.networkVideoId = networkVideoId;
	}

	public String getNetworkRecordLinkId() {
		return this.networkRecordLinkId;
	}

	public void setNetworkRecordLinkId(String networkRecordLinkId) {
		this.networkRecordLinkId = networkRecordLinkId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}