package com.business.Dlb.Message;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourseVideo.NetworkCourseVideo;
import com.easecom.common.framework.struts.BaseForm;
import com.easecom.common.util.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

/**
 * Created by sprke on 2017/7/5.
 */
public class TbMessageActionForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String img;
    private int jumpType;
    private String contentId;
    private String intro;//简介
    private String pushTimeStr;
    private String content;//富文本

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getJumpType() {
        return jumpType;
    }

    public void setJumpType(int jumpType) {
        this.jumpType = jumpType;
    }

    public String getPushTimeStr() {
        return pushTimeStr;
    }

    public void setPushTimeStr(String pushTimeStr) {
        this.pushTimeStr = pushTimeStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
