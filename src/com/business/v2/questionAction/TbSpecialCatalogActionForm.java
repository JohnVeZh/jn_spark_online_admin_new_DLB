package com.business.v2.questionAction;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by john on 2017/4/13.
 */
public class TbSpecialCatalogActionForm extends BaseForm implements Serializable {

    // Fields

    private String id;
    private String PId;
    private String name;
    private String iconUrl;
    private Integer sortOrder;
    private String remarks;
    private String isDel;
    private Date createTime;

    // Constructors

    /** default constructor */
    public TbSpecialCatalogActionForm() {
    }

    /** minimal constructor */
    public TbSpecialCatalogActionForm(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbSpecialCatalogActionForm(String id, String PId, String name, String iconUrl,
                                      Integer sortOrder, String remarks, String isDel,
                                      Date createTime) {
        this.id = id;
        this.PId = PId;
        this.name = name;
        this.iconUrl = iconUrl;
        this.sortOrder = sortOrder;
        this.remarks = remarks;
        this.isDel = isDel;
        this.createTime = createTime;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return this.PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsDel() {
        return this.isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
