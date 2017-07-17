package com.business.Dlb.Homework;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sprke on 2017/7/5.
 */
public class HomeworkActionForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;  //id
    private String section; //学段，四级01，六级02
    private Integer questionType; //作业题型 1听力，2阅读，3翻译，4写作
    private String courseId;  //网课ID
    private String courseCatalogId; //课时
    private String Title;  //作业名字
    private String displayDate;  //作业展示的时间点：根据课时安排录入时间点
    private Integer sort;  //排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseCatalogId() {
        return courseCatalogId;
    }

    public void setCourseCatalogId(String courseCatalogId) {
        this.courseCatalogId = courseCatalogId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id='" + id + '\'' +
                ", section='" + section + '\'' +
                ", questionType=" + questionType +
                ", courseId='" + courseId + '\'' +
                ", courseCatalogId='" + courseCatalogId + '\'' +
                ", Title='" + Title + '\'' +
                ", displayDate=" + displayDate +
                ", sort=" + sort +
                '}';
    }
}
