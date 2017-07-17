package com.business.v2.questionAction;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by john on 2017/4/24.
 */
public class TbQuestionReadingQuestionActionForm extends BaseForm implements Serializable {

    // Fields

    private String id;
    private String readingId;
    private String title;
    private String analysis;
    private Integer sortOrder;

    // Constructors

    /** default constructor */
    public TbQuestionReadingQuestionActionForm() {
    }

    /** minimal constructor */
    public TbQuestionReadingQuestionActionForm(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbQuestionReadingQuestionActionForm(String id, String readingId, String title,
                                     String analysis, Integer sortOrder) {
        this.id = id;
        this.readingId = readingId;
        this.title = title;
        this.analysis = analysis;
        this.sortOrder = sortOrder;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadingId() {
        return this.readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnalysis() {
        return this.analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
