package com.easecom.system.web;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

@SuppressWarnings("serial")
public class SysConfigForm extends BaseForm {

	private String id;
	private String alias;
	private String name;
	private String value;
	private String jumpType;
	
	private String valueId;
	private String valueName;
	
	public String getJumpType() {
		return jumpType;
	}
	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}
	private String type;
	private String url;
	private Integer sort;
	
	private FormFile file;
	
	
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
}
