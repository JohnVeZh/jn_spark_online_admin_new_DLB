package com.business.NetworkCourseTeacher;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

public class NetworkCourseTeacherActionForm extends BaseForm {

	private static final long serialVersionUID = 1L;
	//老师Id
	private String id;
	//老师名称
	private String name;
	//电话
	private String moblie;
	//邮箱
	private String email;
	//教师logo路径
	private String logo;
	//性别
	private int sex;
	//老师介绍
	private String introduce;
	//是否删除
	private int isDelete;
	//排序
	private int sort;
	//创建时间
	private String createTime;

	private String tags;


	private FormFile file;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	
	
}
