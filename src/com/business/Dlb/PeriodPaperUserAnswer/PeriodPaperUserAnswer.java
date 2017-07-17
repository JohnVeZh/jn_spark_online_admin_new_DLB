package com.business.Dlb.PeriodPaperUserAnswer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.easecom.common.framework.struts.BaseForm;

public class PeriodPaperUserAnswer extends BaseForm implements Serializable{

	private String id;
	private String reportId;
	private String paperId;
	private String userId;
	private String section;
	private int period;
	private int questionType;
	private int questionNo;
	private String rightAnswer;
	private int isTeacherEvaluate ;
	private String userAnswer;
	private BigDecimal score;
	private String replyContent;
	private String replyUserId;
	private Timestamp replyDate;
	private Timestamp createDate;
	//用户手机号
	private String mobile;
	//批改人
	private String replyName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public int getIsTeacherEvaluate() {
		return isTeacherEvaluate;
	}
	public void setIsTeacherEvaluate(int isTeacherEvaluate) {
		this.isTeacherEvaluate = isTeacherEvaluate;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	@Override
	public String toString() {
		return "PeriodPaperUserAnswer [id=" + id + ", reportId=" + reportId
				+ ", paperId=" + paperId + ", userId=" + userId + ", section="
				+ section + ", period=" + period + ", questionType="
				+ questionType + ", questionNo=" + questionNo
				+ ", rightAnswer=" + rightAnswer + ", isTeacherEvaluate="
				+ isTeacherEvaluate + ", userAnswer=" + userAnswer + ", score="
				+ score + ", replyContent=" + replyContent + ", replyUserId="
				+ replyUserId + ", replyDate=" + replyDate + ", createDate="
				+ createDate + ", mobile=" + mobile + ", replyName="
				+ replyName + "]";
	}
}
