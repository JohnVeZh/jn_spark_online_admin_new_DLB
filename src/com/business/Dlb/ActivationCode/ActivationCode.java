package com.business.Dlb.ActivationCode;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.struts.BaseForm;
/**
 * 大礼包激活码
 * @author xss
 *
 */
public class ActivationCode extends BaseForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//激活码
	private String code;
	//学段
	private String section;
	//是否激活
	private int isActivated;
	//激活时间
	private Timestamp activateTime;
	//激活用户ID
	private String activateUserId;
	//激活地址
	private String address;
	//激活次序
	private Integer activateSort;
	//创建时间
	private Timestamp createDate;
	//激活用户
	private String username;
	//激活用户手机号
	private String mobile;
	//批改次数
	private Integer  preTranslateTotal;
	private Integer  preTranslateUse;
	private Integer  preWriteTotal;
	private Integer  preWriteUse;
	private Integer  midTranslateTotal;
	private Integer  midTranslateUse;
	private Integer  midWriteTotal;
	private Integer  midWriteUse;
	private Integer  postTranslateTotal;
	private Integer  postTranslateUse;
	private Integer  postWriteTotal;
	private Integer  postWriteUse;
	//批量上传
	private FormFile file;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(int isActivated) {
		this.isActivated = isActivated;
	}

	public Timestamp getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(Timestamp activateTime) {
		this.activateTime = activateTime;
	}

	public String getActivateUserId() {
		return activateUserId;
	}

	public void setActivateUserId(String activateUserId) {
		this.activateUserId = activateUserId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getActivateSort() {
		return activateSort;
	}

	public void setActivateSort(Integer activateSort) {
		this.activateSort = activateSort;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getPreTranslateTotal() {
		return preTranslateTotal;
	}

	public void setPreTranslateTotal(Integer preTranslateTotal) {
		this.preTranslateTotal = preTranslateTotal;
	}

	public Integer getPreTranslateUse() {
		return preTranslateUse;
	}

	public void setPreTranslateUse(Integer preTranslateUse) {
		this.preTranslateUse = preTranslateUse;
	}

	public Integer getPreWriteTotal() {
		return preWriteTotal;
	}

	public void setPreWriteTotal(Integer preWriteTotal) {
		this.preWriteTotal = preWriteTotal;
	}

	public Integer getPreWriteUse() {
		return preWriteUse;
	}

	public void setPreWriteUse(Integer preWriteUse) {
		this.preWriteUse = preWriteUse;
	}

	public Integer getMidTranslateTotal() {
		return midTranslateTotal;
	}

	public void setMidTranslateTotal(Integer midTranslateTotal) {
		this.midTranslateTotal = midTranslateTotal;
	}

	public Integer getMidTranslateUse() {
		return midTranslateUse;
	}

	public void setMidTranslateUse(Integer midTranslateUse) {
		this.midTranslateUse = midTranslateUse;
	}

	public Integer getMidWriteTotal() {
		return midWriteTotal;
	}

	public void setMidWriteTotal(Integer midWriteTotal) {
		this.midWriteTotal = midWriteTotal;
	}

	public Integer getMidWriteUse() {
		return midWriteUse;
	}

	public void setMidWriteUse(Integer midWriteUse) {
		this.midWriteUse = midWriteUse;
	}

	public Integer getPostTranslateTotal() {
		return postTranslateTotal;
	}

	public void setPostTranslateTotal(Integer postTranslateTotal) {
		this.postTranslateTotal = postTranslateTotal;
	}

	public Integer getPostTranslateUse() {
		return postTranslateUse;
	}

	public void setPostTranslateUse(Integer postTranslateUse) {
		this.postTranslateUse = postTranslateUse;
	}

	public Integer getPostWriteTotal() {
		return postWriteTotal;
	}

	public void setPostWriteTotal(Integer postWriteTotal) {
		this.postWriteTotal = postWriteTotal;
	}

	public Integer getPostWriteUse() {
		return postWriteUse;
	}

	public void setPostWriteUse(Integer postWriteUse) {
		this.postWriteUse = postWriteUse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "ActivationCode [id=" + id + ", code=" + code + ", section="
				+ section + ", isActivated=" + isActivated + ", activateTime="
				+ activateTime + ", activateUserId=" + activateUserId
				+ ", address=" + address + ", activateSort=" + activateSort
				+ ", createDate=" + createDate + ", username=" + username
				+ ", mobile=" + mobile + ", preTranslateTotal="
				+ preTranslateTotal + ", preTranslateUse=" + preTranslateUse
				+ ", preWriteTotal=" + preWriteTotal + ", preWriteUse="
				+ preWriteUse + ", midTranslateTotal=" + midTranslateTotal
				+ ", midTranslateUse=" + midTranslateUse + ", midWriteTotal="
				+ midWriteTotal + ", midWriteUse=" + midWriteUse
				+ ", postTranslateTotal=" + postTranslateTotal
				+ ", postTranslateUse=" + postTranslateUse
				+ ", postWriteTotal=" + postWriteTotal + ", postWriteUse="
				+ postWriteUse + "]";
	}
	
}
