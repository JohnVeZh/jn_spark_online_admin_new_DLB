package com.business.Dlb.PeriodPaperQrcode;

import java.io.Serializable;
import java.sql.Timestamp;

import com.easecom.common.framework.hibernate.BaseModel;

/**
 * 扫码录题的答案
 * @author sparke
 *
 */
public class PeriodPaperAnswer extends BaseModel implements Serializable{

	private String id;
	private String paperQrcodeId;
	private String section;
	private int period;
	private int questionType;
	private int questionNo;
	private String answer;
	private Double score;
	private Timestamp createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPaperQrcodeId() {
		return paperQrcodeId;
	}
	public void setPaperQrcodeId(String paperQrcodeId) {
		this.paperQrcodeId = paperQrcodeId;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "PeriodPaperAnswer [id=" + id + ", paperQrcodeId="
				+ paperQrcodeId + ", section=" + section + ", period=" + period
				+ ", questionType=" + questionType + ", questionNo="
				+ questionNo + ", answer=" + answer + ", score=" + score
				+ ", createDate=" + createDate + "]";
	}
	
}
