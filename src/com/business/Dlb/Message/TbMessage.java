package com.business.Dlb.Message;

import java.util.Date;

/**
 * Created by qiu on 2017/7/7.
 */
public class TbMessage {
    private String id;
    private String title;
    private String img;
    private String intro;//简介
    private String content;//富文本
    private int targetType;//目标类型(1.个人 2.全体)
    private int jumpType;//跳转类型(1.富文本 2.外部链接 3.活动 4.资讯 5.图书，6网课 )
    private String contentId;// 活动资讯图书网课等的id
    private Date pushTime;//推送时间
    private int pushStatus;//推送状态（0.未推送 1.已推送）
    private Date createDate;//创建时间
    private String createBy;//创建人
    private Date updateDate;//修改时间
    private String updateBy;//修改人
    private int sort;//排序
    private int delFlag;//删除标记 0未删除 1删除

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
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

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(int pushStatus) {
        this.pushStatus = pushStatus;
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
        return "TbMessage{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", intro='" + intro + '\'' +
                ", content='" + content + '\'' +
                ", targetType='" + targetType + '\'' +
                ", jumpType='" + jumpType + '\'' +
                ", contentId='" + contentId + '\'' +
                ", pushTime=" + pushTime +
                ", pushStatus=" + pushStatus +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", updateDate=" + updateDate +
                ", updateBy='" + updateBy + '\'' +
                ", sort=" + sort +
                ", delFlag=" + delFlag +
                '}';
    }
}
