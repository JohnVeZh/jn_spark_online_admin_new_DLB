package com.business.v2.word.WordAction;
import java.util.Date;

import javax.persistence.Entity;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

/**
 * TbWord entity. @author MyEclipse Persistence Tools
 */

/**
 * @author xs
 *
 */
@Entity
public class TbWordActionForm extends BaseForm
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
	private String type;
	private String types;
	private String sortOrder;
	private FormFile file;
	private String cet;
	/** default constructor */
	public TbWordActionForm() {
	}

	/** minimal constructor */
	public TbWordActionForm(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbWordActionForm(String id, String word, String phoneticSymbol,
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getCet() {
		return cet;
	}

	public void setCet(String cet) {
		this.cet = cet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}