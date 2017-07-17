package com.business.Dlb.ActivationCode;

import java.sql.Timestamp;

/**
 * @author xss
 *
 */
public class UserObjectiveSubmitCount {

	private String id;
	private String section;
	private String userId;
	private String codeId;
	private String code;
	private Integer  preTranslateTotal;
	private Integer  preTranslateUse;
	private Integer  preWriteTotal;
	private Integer  preWriteUse;
	private Integer  midTranslateTotal;
	private Integer  midTranslateUse;
	private Integer  midWriteTotal;
	private Integer  midWriteUse;
	private Integer  postTranslateTotal;
	private Integer  postTranslateUse;
	private Integer  postWriteTotal;
	private Integer  postWriteUse;
	private Timestamp createDate;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPreTranslateTotal() {
		return preTranslateTotal;
	}
	public void setPreTranslateTotal(Integer preTranslateTotal) {
		this.preTranslateTotal = preTranslateTotal;
	}
	public Integer getPreTranslateUse() {
		return preTranslateUse;
	}
	public void setPreTranslateUse(Integer preTranslateUse) {
		this.preTranslateUse = preTranslateUse;
	}
	public Integer getPreWriteTotal() {
		return preWriteTotal;
	}
	public void setPreWriteTotal(Integer preWriteTotal) {
		this.preWriteTotal = preWriteTotal;
	}
	public Integer getPreWriteUse() {
		return preWriteUse;
	}
	public void setPreWriteUse(Integer preWriteUse) {
		this.preWriteUse = preWriteUse;
	}
	public Integer getMidTranslateTotal() {
		return midTranslateTotal;
	}
	public void setMidTranslateTotal(Integer midTranslateTotal) {
		this.midTranslateTotal = midTranslateTotal;
	}
	public Integer getMidTranslateUse() {
		return midTranslateUse;
	}
	public void setMidTranslateUse(Integer midTranslateUse) {
		this.midTranslateUse = midTranslateUse;
	}
	public Integer getMidWriteTotal() {
		return midWriteTotal;
	}
	public void setMidWriteTotal(Integer midWriteTotal) {
		this.midWriteTotal = midWriteTotal;
	}
	public Integer getMidWriteUse() {
		return midWriteUse;
	}
	public void setMidWriteUse(Integer midWriteUse) {
		this.midWriteUse = midWriteUse;
	}
	public Integer getPostTranslateTotal() {
		return postTranslateTotal;
	}
	public void setPostTranslateTotal(Integer postTranslateTotal) {
		this.postTranslateTotal = postTranslateTotal;
	}
	public Integer getPostTranslateUse() {
		return postTranslateUse;
	}
	public void setPostTranslateUse(Integer postTranslateUse) {
		this.postTranslateUse = postTranslateUse;
	}
	public Integer getPostWriteTotal() {
		return postWriteTotal;
	}
	public void setPostWriteTotal(Integer postWriteTotal) {
		this.postWriteTotal = postWriteTotal;
	}
	public Integer getPostWriteUse() {
		return postWriteUse;
	}
	public void setPostWriteUse(Integer postWriteUse) {
		this.postWriteUse = postWriteUse;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "UserObjectiveSubmitCount [id=" + id + ", section=" + section
				+ ", userId=" + userId + ", codeId=" + codeId + ", code="
				+ code + ", preTranslateTotal=" + preTranslateTotal
				+ ", preTranslateUse=" + preTranslateUse + ", preWriteTotal="
				+ preWriteTotal + ", preWriteUse=" + preWriteUse
				+ ", midTranslateTotal=" + midTranslateTotal
				+ ", midTranslateUse=" + midTranslateUse + ", midWriteTotal="
				+ midWriteTotal + ", midWriteUse=" + midWriteUse
				+ ", postTranslateTotal=" + postTranslateTotal
				+ ", postTranslateUse=" + postTranslateUse
				+ ", postWriteTotal=" + postWriteTotal + ", postWriteUse="
				+ postWriteUse + ", createDate=" + createDate + "]";
	}
}
