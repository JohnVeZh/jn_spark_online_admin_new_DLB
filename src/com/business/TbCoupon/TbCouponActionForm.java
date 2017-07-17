package com.business.TbCoupon;

import com.easecom.common.framework.struts.BaseForm;
import java.sql.Timestamp;

/**
 * Created by liubaibing on 2016/11/4.
 */
public class TbCouponActionForm extends BaseForm {
    private String id;
    private String title;
    private byte type;
    private byte status;
    private int maxUseNum;
    private String startTime;
    private String endTime;
    private Integer minMoney;
    private Integer discountMoney;
    private Double discountRate;
    private String createTime;
    private byte relationType;

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


    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }


    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getMaxUseNum() {
        return maxUseNum;
    }

    public void setMaxUseNum(int maxUseNum) {
        this.maxUseNum = maxUseNum;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }


    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }


    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public byte getRelationType() {
        return relationType;
    }

    public void setRelationType(byte relationType) {
        this.relationType = relationType;
    }

}
