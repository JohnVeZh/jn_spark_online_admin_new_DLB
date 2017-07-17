/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

/*
 * Created by Shaohui.Liu on 2005-5-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.easecom.system.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

@SuppressWarnings("serial")
public class SysUserForm extends BaseForm {

	private String id;

	private String orgId;
	private String orgCode;

	private String orgName;

	private String name;
	
	private String loginName;

	private String password;

	private String passwordnew;

	private String passwordold;
	private String emailPassword;
	private String passwordnew2;
	private String email;
	private String type;

	private String remark;
	private String sex;
	private String education;
	private String workdate;
	private String birthdate;
	private String age;
	private String duty;
	private String userid;
	private String phoneCornet;
	private String workPhone;
	private String address;
	private FormFile file;
	private String homeAddress;
	private String shopId;
	
	
	private String worknum;
	
	//通知公告数量
	private int cou_notice ;
	//短信发送数量
	private int cou_sms ;
	//email发送数量
	private int cou_email ;
	
	
	//合计数量
	private int cou_all ;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getPhoneCornet() {
		return phoneCornet;
	}

	public void setPhoneCornet(String phoneCornet) {
		this.phoneCornet = phoneCornet;
	}

	public String getUserid() {
		return userid;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWorknum() {
		return worknum;
	}

	public void setWorknum(String worknum) {
		this.worknum = worknum;
	}

	public String getPasswordnew() {
		return passwordnew;
	}

	public void setPasswordnew(String passwordnew) {
		this.passwordnew = passwordnew;
	}

	public String getPasswordold() {
		return passwordold;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setPasswordold(String passwordold) {
		this.passwordold = passwordold;
	}

	public String getPasswordnew2() {
		return passwordnew2;
	}

	public void setPasswordnew2(String passwordnew2) {
		this.passwordnew2 = passwordnew2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public int getCou_all() {
		return cou_all;
	}

	public void setCou_all(int cou_all) {
		this.cou_all = cou_all;
	}

	public int getCou_notice() {
		return cou_notice;
	}

	public void setCou_notice(int cou_notice) {
		this.cou_notice = cou_notice;
	}

	public int getCou_sms() {
		return cou_sms;
	}

	public void setCou_sms(int cou_sms) {
		this.cou_sms = cou_sms;
	}

	public int getCou_email() {
		return cou_email;
	}

	public void setCou_email(int cou_email) {
		this.cou_email = cou_email;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
