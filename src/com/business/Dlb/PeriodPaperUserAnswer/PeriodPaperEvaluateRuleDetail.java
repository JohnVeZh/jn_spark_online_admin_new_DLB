package com.business.Dlb.PeriodPaperUserAnswer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.easecom.common.framework.struts.BaseForm;

public class PeriodPaperEvaluateRuleDetail extends BaseForm implements Serializable{

	private String id;
	private String ruleId;
	private int level;
	private String content;
	private BigDecimal score;
	private Timestamp createDate;
	private String name;
	private String levelName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	@Override
	public String toString() {
		return "PeriodPaperEvaluateRule [id=" + id + ", ruleId=" + ruleId
				+ ", level=" + level + ", content=" + content + ", score="
				+ score + ", createDate=" + createDate + ", name=" + name + "]";
	}
}
