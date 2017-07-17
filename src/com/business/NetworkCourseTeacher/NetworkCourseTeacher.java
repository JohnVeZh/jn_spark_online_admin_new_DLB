package com.business.NetworkCourseTeacher;

import java.io.Serializable;

import javax.persistence.Table;

import com.easecom.common.framework.hibernate.BaseModel;
@Table(name = "network_course_teacher", schema = "jn_spark_online", catalog = "")
public class NetworkCourseTeacher extends BaseModel implements Serializable{

	/**
	 *
	 */
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
	private Integer sex;
	//老师介绍
	private String introduce;
	//是否删除
	private Integer isDelete;
	//排序
	private Integer sort;
	//创建时间
	private String tags;
	private String createTime;

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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "NetworkCourseTeacher{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", moblie='" + moblie + '\'' +
				", email='" + email + '\'' +
				", logo='" + logo + '\'' +
				", sex=" + sex +
				", introduce='" + introduce + '\'' +
				", isDelete=" + isDelete +
				", sort=" + sort +
				", tags='" + tags + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
