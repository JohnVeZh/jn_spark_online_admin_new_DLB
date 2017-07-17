package com.business.Dlb.Message;

import java.sql.Timestamp;

/**
 * Created by qiu on 2017/7/7.
 */
public class TbMessageUser {
    private String id;
    private String userId;
    private String msgId;
    private Byte type;
    private Timestamp createDate;
    private Timestamp updteDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdteDate() {
        return updteDate;
    }

    public void setUpdteDate(Timestamp updteDate) {
        this.updteDate = updteDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbMessageUser that = (TbMessageUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (msgId != null ? !msgId.equals(that.msgId) : that.msgId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updteDate != null ? !updteDate.equals(that.updteDate) : that.updteDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (msgId != null ? msgId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updteDate != null ? updteDate.hashCode() : 0);
        return result;
    }
}
