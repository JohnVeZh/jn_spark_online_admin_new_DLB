package com.easecom.system.model;

/**
 * SystemLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SystemLog extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String opetime;
	private String content;
	private String operid;
	private String oper;
	private String type;
	private String ipaddress;

	// Constructors

	/** default constructor */
	public SystemLog() {
	}

	/** full constructor */
	public SystemLog(String opetime, String content, String operid,
			String oper, String type, String ipaddress) {
		this.opetime = opetime;
		this.content = content;
		this.operid = operid;
		this.oper = oper;
		this.type = type;
		this.ipaddress = ipaddress;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpetime() {
		return this.opetime;
	}

	public void setOpetime(String opetime) {
		this.opetime = opetime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperid() {
		return this.operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	public String getOper() {
		return this.oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

}