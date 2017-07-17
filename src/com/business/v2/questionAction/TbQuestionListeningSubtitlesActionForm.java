package com.business.v2.questionAction;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by john on 2017/4/21.
 */
public class TbQuestionListeningSubtitlesActionForm extends BaseForm implements Serializable {

    // Fields

    private String id;
    private String listeningId;
    private Integer startTime;
    private String subtitle;

    // Constructors

    /** default constructor */
    public TbQuestionListeningSubtitlesActionForm() {
    }

    /** minimal constructor */
    public TbQuestionListeningSubtitlesActionForm(String id) {
        this.id = id;
    }

    /** full constructor */
    public TbQuestionListeningSubtitlesActionForm(String id, String listeningId,
                                        Integer startTime, String subtitle) {
        this.id = id;
        this.listeningId = listeningId;
        this.startTime = startTime;
        this.subtitle = subtitle;
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

    public Integer getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

}
