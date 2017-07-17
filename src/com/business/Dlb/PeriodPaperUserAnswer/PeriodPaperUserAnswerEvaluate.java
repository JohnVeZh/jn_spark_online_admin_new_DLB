package com.business.Dlb.PeriodPaperUserAnswer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.struts.BaseForm;

/**
 * 试卷批改规则详情记录表
 * @author sparke
 *
 */
public class PeriodPaperUserAnswerEvaluate extends BaseModel implements Serializable{

	private String id;
	private String userAnswerId;
	private String ruleId;
	private String ruleDetailId;
	private BigDecimal score;
	private Timestamp createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserAnswerId() {
		return userAnswerId;
	}
	public void setUserAnswerId(String userAnswerId) {
		this.userAnswerId = userAnswerId;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleDetailId() {
		return ruleDetailId;
	}
	public void setRuleDetailId(String ruleDetailId) {
		this.ruleDetailId = ruleDetailId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
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
		return "PeriodPaperUserAnswerEvaluate [id=" + id + ", userAnswerId="
				+ userAnswerId + ", ruleId=" + ruleId + ", ruleDetailId="
				+ ruleDetailId + ", score=" + score + ", createDate="
				+ createDate + "]";
	}
}
