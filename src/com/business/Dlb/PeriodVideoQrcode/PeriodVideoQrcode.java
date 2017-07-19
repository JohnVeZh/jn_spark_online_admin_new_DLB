package com.business.Dlb.PeriodVideoQrcode;

import java.io.Serializable;
import java.sql.Timestamp;

import com.easecom.common.framework.struts.BaseForm;

public class PeriodVideoQrcode extends BaseForm implements Serializable{

	private String id;
	private String section;
	private String title;
	private String qrcodeContent;
	private String qrcodeUrl;
	private String createBy;
	private Timestamp createDate;
	private String username;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQrcodeContent() {
		return qrcodeContent;
	}
	public void setQrcodeContent(String qrcodeContent) {
		this.qrcodeContent = qrcodeContent;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "PeriodVideoQrcode [id=" + id + ", section=" + section
				+ ", title=" + title + ", qrcodeContent=" + qrcodeContent
				+ ", qrcodeUrl=" + qrcodeUrl + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", username=" + username + "]";
	}
	
}
