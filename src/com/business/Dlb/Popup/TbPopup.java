package com.business.Dlb.Popup;

import com.easecom.common.framework.hibernate.BaseModel;

import java.util.Date;

/**
 * Created by qiu on 2017/7/10.
 */
public class TbPopup extends BaseModel {
    private String id;
    private String title;
    private String img;
    private int jumpType;//跳转类型(1.富文本 2.外部链接 3.活动 4.资讯 5.图书 6.网课 )
    private String contentId;
    private Date startTime;
    private Date endTime;
    private int module;//展示的模块，1首页，2社区，3商城，4我的  5网课(同一个模块同一个时间段只能有一个弹框)
    private int status;//1有效，2下线，3删除（1的时候判断时间，未生效、有效、已失效统称有效）
    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
    private int sort;
    private int delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getJumpType() {
        return jumpType;
    }

    public void setJumpType(int jumpType) {
        this.jumpType = jumpType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "TbPopup{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", jumpType=" + jumpType +
                ", contentId='" + contentId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", module=" + module +
                ", status=" + status +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", updateDate=" + updateDate +
                ", updateBy='" + updateBy + '\'' +
                ", sort=" + sort +
                ", delFlag=" + delFlag +
                '}';
    }
}
