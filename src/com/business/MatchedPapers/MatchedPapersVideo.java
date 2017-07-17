package com.business.MatchedPapers;

/**
 * MatchedPapersVideo entity. @author MyEclipse Persistence Tools
 */

public class MatchedPapersVideo extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String mpId;
	private String videoTitle;
	private String videoId;
	private String videoUrl;
	private String isDel;

	// Constructors

	/** default constructor */
	public MatchedPapersVideo() {
	}

	/** minimal constructor */
	public MatchedPapersVideo(String id) {
		this.id = id;
	}

	/** full constructor */
	public MatchedPapersVideo(String id, String mpId, String videoTitle,
			String videoId, String videoUrl, String isDel) {
		this.id = id;
		this.mpId = mpId;
		this.videoTitle = videoTitle;
		this.videoId = videoId;
		this.videoUrl = videoUrl;
		this.isDel = isDel;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMpId() {
		return this.mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
	}

	public String getVideoTitle() {
		return this.videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoId() {
		return this.videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}