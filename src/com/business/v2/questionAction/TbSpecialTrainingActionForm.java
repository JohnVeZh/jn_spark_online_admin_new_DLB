package com.business.v2.questionAction;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by john on 2017/4/21.
 */
public class TbSpecialTrainingActionForm extends BaseForm implements Serializable {

    // Fields

    private String id;
    private String section;
    private String catalogId;
    private String trainingType;
    private String trainingId;
    private Integer sortOrder;

    // Constructors

    /** default constructor */
    public TbSpecialTrainingActionForm() {
    }

    /** minimal constructor */
    public TbSpecialTrainingActionForm(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbSpecialTrainingActionForm(String id, String section, String catalogId,
                             String trainingType, String trainingId, Integer sortOrder) {
        this.id = id;
        this.section = section;
        this.catalogId = catalogId;
        this.trainingType = trainingType;
        this.trainingId = trainingId;
        this.sortOrder = sortOrder;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getTrainingType() {
        return this.trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getTrainingId() {
        return this.trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
