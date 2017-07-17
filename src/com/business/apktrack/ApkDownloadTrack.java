package com.business.apktrack;

import java.util.Date;

/**
 * ApkDownloadTrack entity. @author MyEclipse Persistence Tools
 */

public class ApkDownloadTrack extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String operType;
	private String channelId;
	private String osType;
	private String ip;
	private String ipProvince;
	private String ipCity;
	private Date time;

	// Constructors

	/** default constructor */
	public ApkDownloadTrack() {
	}

	/** full constructor */
	public ApkDownloadTrack(String operType, String channelId, String osType,
			String ip, String ipProvince, String ipCity, Date time) {
		this.operType = operType;
		this.channelId = channelId;
		this.osType = osType;
		this.ip = ip;
		this.ipProvince = ipProvince;
		this.ipCity = ipCity;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOsType() {
		return this.osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpProvince() {
		return this.ipProvince;
	}

	public void setIpProvince(String ipProvince) {
		this.ipProvince = ipProvince;
	}

	public String getIpCity() {
		return this.ipCity;
	}

	public void setIpCity(String ipCity) {
		this.ipCity = ipCity;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}