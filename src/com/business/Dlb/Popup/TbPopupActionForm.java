package com.business.Dlb.Popup;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by qiu on 2017/7/10.
 */
public class TbPopupActionForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String img;
    private int jumpType;//跳转类型(1.富文本 2.外部链接 3.活动 4.资讯 5.图书 6.网课 )
    private String contentId;
    private String startTimeStr;
    private String endTimeStr;
    private int module;//展示的模块，1首页，2社区，3商城，4我的(同一个模块同一个时间段只能有一个弹框)
    private int status;//1有效，2下线，3删除（1的时候判断时间，未生效、有效、已失效统称有效）

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
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
}
