package com.business.Dlb.PeriodPaperQrcode;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

/**
 * 大礼包三套题(学前、学中、学末 )对应的二维码
 * @author sparke
 *
 */
/**
 * @author sparke
 *
 */
/**
 * @author sparke
 *
 */
public class PeriodPaperQrcode extends BaseForm implements Serializable{

	private String id;
	private String section;
	private int period;
	private String name;
	private String qrcodeContent;
	private String qrcodeUrl;
	private Timestamp createDate;
	private String userId;
	private String username;
	private int delFlag;
	//图片
	private FormFile file;
	
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
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "PeriodPaperQrcode [id=" + id + ", section=" + section
				+ ", period=" + period + ", name=" + name + ", qrcodeContent="
				+ qrcodeContent + ", qrcodeUrl=" + qrcodeUrl + ", createDate="
				+ createDate + ", userId=" + userId + ", username=" + username
				+ ", delFlag=" + delFlag + "]";
	}
}
