package com.business.v2.question;

/**
 * TbQuestionListeningSubtitles entity. @author MyEclipse Persistence Tools
 */

public class TbQuestionListeningSubtitles extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String listeningId;
	private Integer startTime;
	private String subtitle;

	// Constructors

	/** default constructor */
	public TbQuestionListeningSubtitles() {
	}

	/** minimal constructor */
	public TbQuestionListeningSubtitles(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbQuestionListeningSubtitles(String id, String listeningId,
			Integer startTime, String subtitle) {
		this.id = id;
		this.listeningId = listeningId;
		this.startTime = startTime;
		this.subtitle = subtitle;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListeningId() {
		return this.listeningId;
	}

	public void setListeningId(String listeningId) {
		this.listeningId = listeningId;
	}

	public Integer getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

}