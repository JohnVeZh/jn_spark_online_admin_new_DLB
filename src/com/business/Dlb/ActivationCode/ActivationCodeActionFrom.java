package com.business.Dlb.ActivationCode;

import java.io.Serializable;
import java.sql.Timestamp;

import com.easecom.common.framework.struts.BaseForm;
/**
 * 大礼包激活码
 * @author xss
 *
 */
public class ActivationCodeActionFrom extends BaseForm implements Serializable{

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
	private int activateSort;
	//创建时间
	private Timestamp createDate;
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
	public int getActivateSort() {
		return activateSort;
	}
	public void setActivateSort(int activateSort) {
		this.activateSort = activateSort;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ActivationCodeActionFrom [id=" + id + ", code=" + code
				+ ", section=" + section + ", isActivated=" + isActivated
				+ ", activateTime=" + activateTime + ", activateUserId="
				+ activateUserId + ", address=" + address + ", activateSort="
				+ activateSort + ", createDate=" + createDate + "]";
	}
	
}
