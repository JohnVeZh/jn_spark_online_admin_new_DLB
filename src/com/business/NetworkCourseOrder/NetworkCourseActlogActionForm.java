package com.business.NetworkCourseOrder;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by john on 2017/5/9.
 */
public class NetworkCourseActlogActionForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userId; //用户Id
    private String ncId;  //网课Id
    private Integer actType; //活动类型 0: 预约, 1: 免费领取, 2: 限时领取, 3: 已下单, 4: 已付款, 5: 已退款'
    private String createTime; //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNcId() {
        return ncId;
    }

    public void setNcId(String ncId) {
        this.ncId = ncId;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "NetworkCourseActlog{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", ncId='" + ncId + '\'' +
                ", actType=" + actType +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
