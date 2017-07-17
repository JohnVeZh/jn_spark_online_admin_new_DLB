package com.business.Dlb.Homework;

/**
 * Created by sprke on 2017/7/8.
 */
public class HomeworkQuestion {

    private String id;
    private String homeworkId; //作业Id
    private String questionId; //题目Id
    private String questionName; //题目名称
    private Integer sort; //题目排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "HomeworkQuestion{" +
                "id='" + id + '\'' +
                ", homeworkId='" + homeworkId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", questionName='" + questionName + '\'' +
                ", sort=" + sort +
                '}';
    }
}
