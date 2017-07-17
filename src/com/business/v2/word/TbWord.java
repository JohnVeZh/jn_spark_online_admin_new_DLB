package com.business.v2.word;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.easecom.common.framework.hibernate.BaseModel;
/**
 * TbWord entity. @author MyEclipse Persistence Tools
 */

public class TbWord extends BaseModel
		implements java.io.Serializable {

	// Fields

	private String id;
	private String word;
	private String phoneticSymbol;
	private String pronunciationUrl;
	private String paraphrase;
	private String exampleSentence;
	private String exampleReference;
	private String exampleUrl;
	private Date createTime;

	// Constructors
	private String sortOrder;
	
	/** default constructor */
	public TbWord() {
	}

	/** minimal constructor */
	public TbWord(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbWord(String id, String word, String phoneticSymbol,
			String pronunciationUrl, String paraphrase, String exampleSentence,
			String exampleReference, String exampleUrl, Date createTime) {
		this.id = id;
		this.word = word;
		this.phoneticSymbol = phoneticSymbol;
		this.pronunciationUrl = pronunciationUrl;
		this.paraphrase = paraphrase;
		this.exampleSentence = exampleSentence;
		this.exampleReference = exampleReference;
		this.exampleUrl = exampleUrl;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPhoneticSymbol() {
		return this.phoneticSymbol;
	}

	public void setPhoneticSymbol(String phoneticSymbol) {
		this.phoneticSymbol = phoneticSymbol;
	}

	public String getPronunciationUrl() {
		return this.pronunciationUrl;
	}

	public void setPronunciationUrl(String pronunciationUrl) {
		this.pronunciationUrl = pronunciationUrl;
	}

	public String getParaphrase() {
		return this.paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getExampleSentence() {
		return this.exampleSentence;
	}

	public void setExampleSentence(String exampleSentence) {
		this.exampleSentence = exampleSentence;
	}

	public String getExampleReference() {
		return this.exampleReference;
	}

	public void setExampleReference(String exampleReference) {
		this.exampleReference = exampleReference;
	}

	public String getExampleUrl() {
		return this.exampleUrl;
	}

	public void setExampleUrl(String exampleUrl) {
		this.exampleUrl = exampleUrl;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}