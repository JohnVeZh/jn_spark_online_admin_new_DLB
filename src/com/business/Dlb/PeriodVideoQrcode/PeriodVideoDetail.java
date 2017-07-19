package com.business.Dlb.PeriodVideoQrcode;

import java.io.Serializable;

import com.easecom.common.framework.struts.BaseForm;

public class PeriodVideoDetail extends BaseForm implements Serializable{

	private String id;
	private String videoQrcodeId;
	private String title;
	private int sort;
	private String detail;
	private String videoCcid;
	private String createDate;
	private String createBy;
	private String username;
	private int delFlag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVideoQrcodeId() {
		return videoQrcodeId;
	}
	public void setVideoQrcodeId(String videoQrcodeId) {
		this.videoQrcodeId = videoQrcodeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getVideoCcid() {
		return videoCcid;
	}
	public void setVideoCcid(String videoCcid) {
		this.videoCcid = videoCcid;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	@Override
	public String toString() {
		return "PeriodVideoDetail [id=" + id + ", videoQrcodeId="
				+ videoQrcodeId + ", title=" + title + ", sort=" + sort
				+ ", detail=" + detail + ", videoCcid=" + videoCcid
				+ ", createDate=" + createDate + ", createBy=" + createBy
				+ ", username=" + username + ", delFlag=" + delFlag + "]";
	}

}
