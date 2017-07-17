package com.business.NetworkCourseVideo;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

/**
 * @author xs
 *
 */

public class NetworkCourseVideo extends BaseForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String nvId;
	private String catalogId;
	private String ncvName;
	private Integer ncvType;
	private Timestamp ncvStartTime;
	private String ncvDuration;
	private String ncvImg;
	private String ncvPreviewLink;
	private String ncvLiveLink;
	private String ncvRecordLinkId;
	private String teacherId;
	private int isDelete;
	private Integer sort;
	private Timestamp createTime;
	private String ncvLiveRoomId;
	
	/*展现*/
	//老师名字
	private String teacherName;
	//课程状态
	private String videoState;
	//批量上传
	private FormFile file;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNvId() {
		return nvId;
	}
	public void setNvId(String nvId) {
		this.nvId = nvId;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getNcvName() {
		return ncvName;
	}
	public void setNcvName(String ncvName) {
		this.ncvName = ncvName;
	}
	public Integer getNcvType() {
		return ncvType;
	}
	public void setNcvType(Integer ncvType) {
		this.ncvType = ncvType;
	}
	public Timestamp getNcvStartTime() {
		return ncvStartTime;
	}
	public void setNcvStartTime(Timestamp ncvStartTime) {
		this.ncvStartTime = ncvStartTime;
	}
	public String getNcvDuration() {
		return ncvDuration;
	}
	public void setNcvDuration(String ncvDuration) {
		this.ncvDuration = ncvDuration;
	}
	public String getNcvImg() {
		return ncvImg;
	}
	public void setNcvImg(String ncvImg) {
		this.ncvImg = ncvImg;
	}
	public String getNcvPreviewLink() {
		return ncvPreviewLink;
	}
	public void setNcvPreviewLink(String ncvPreviewLink) {
		this.ncvPreviewLink = ncvPreviewLink;
	}
	public String getNcvLiveLink() {
		return ncvLiveLink;
	}
	public void setNcvLiveLink(String ncvLiveLink) {
		this.ncvLiveLink = ncvLiveLink;
	}
	public String getNcvRecordLinkId() {
		return ncvRecordLinkId;
	}
	public void setNcvRecordLinkId(String ncvRecordLinkId) {
		this.ncvRecordLinkId = ncvRecordLinkId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getVideoState() {
		return videoState;
	}
	public void setVideoState(String videoState) {
		this.videoState = videoState;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	public String getNcvLiveRoomId() {
		return ncvLiveRoomId;
	}
	public void setNcvLiveRoomId(String ncvLiveRoomId) {
		this.ncvLiveRoomId = ncvLiveRoomId;
	}
	
	@Override
	public String toString() {
		return "NetworkCourseVideo [id=" + id + ", nvId=" + nvId
				+ ", catalogId=" + catalogId + ", ncvName=" + ncvName
				+ ", ncvType=" + ncvType + ", ncvStartTime=" + ncvStartTime
				+ ", ncvDuration=" + ncvDuration + ", ncvImg=" + ncvImg
				+ ", ncvPreviewLink=" + ncvPreviewLink + ", ncvLiveLink="
				+ ncvLiveLink + ", ncvRecordLinkId=" + ncvRecordLinkId
				+ ", teacherId=" + teacherId + ", isDelete=" + isDelete
				+ ", sort=" + sort + ", createTime=" + createTime
				+ ", ncvLiveRoomId=" + ncvLiveRoomId + ", teacherName="
				+ teacherName + ", videoState=" + videoState + ", file=" + file
				+ "]";
	}
	public void clear() {
    	file = null;
    }
}
