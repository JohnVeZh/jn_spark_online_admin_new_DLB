package com.business.NetworkCourseComment;

import com.easecom.common.framework.struts.BaseForm;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by yangye on 2017/4/19.
 */
@Entity
@Table(name = "network_course_comment", schema = "jn_spark_online", catalog = "")
public class NetworkCourseComment extends BaseForm  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String ncId;
    private String userId;
    private String content;
    private int voteUp;
    private int voteDown;
    private int isHide;
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNcId() {
        return ncId;
    }

    public void setNcId(String ncId) {
        this.ncId = ncId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(int voteUp) {
        this.voteUp = voteUp;
    }

    public int getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(int voteDown) {
        this.voteDown = voteDown;
    }

    public int getIsHide() {
        return isHide;
    }

    public void setIsHide(int isHide) {
        this.isHide = isHide;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "NetworkCourseCourse{" +
                "id='" + id + '\'' +
                ", ncId='" + ncId + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", voteUp=" + voteUp +
                ", voteDown=" + voteDown +
                ", isHide=" + isHide +
                ", createTime=" + createTime +
                '}';
    }
}
