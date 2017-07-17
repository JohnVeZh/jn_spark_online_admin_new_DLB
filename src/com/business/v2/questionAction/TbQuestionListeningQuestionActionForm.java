package com.business.v2.questionAction;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by john on 2017/4/21.
 */
public class TbQuestionListeningQuestionActionForm extends BaseForm implements Serializable {

    // Fields

    private String id;
    private String listeningId;
    private String title;
    private String analysis;
    private Integer sortOrder;

    // Constructors

    /** default constructor */
    public TbQuestionListeningQuestionActionForm() {
    }

    /** minimal constructor */
    public TbQuestionListeningQuestionActionForm(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbQuestionListeningQuestionActionForm(String id, String listeningId,
                                       String title, String analysis, Integer sortOrder) {
        this.id = id;
        this.listeningId = listeningId;
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

    public String getListeningId() {
        return this.listeningId;
    }

    public void setListeningId(String listeningId) {
        this.listeningId = listeningId;
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
