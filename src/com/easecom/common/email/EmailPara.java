package com.easecom.common.email;

/**
 * EmailPara entity. @author MyEclipse Persistence Tools
 */

public class EmailPara implements java.io.Serializable {

	// Fields

	private String oid;
	private String tfrom;
	private String smtpHost;
	private String pop3Host;
	private String needAuth;
	private String authclassname;
	private String isDebug;
	private String tprotocol;
	private String username;
	private String password;
	private String agentip;
	private String agentport;
	private String pprotocol;
	private String tport;
	private String pport;

	// Constructors

	/** default constructor */
	public EmailPara() {
	}

	/** full constructor */
	public EmailPara(String tfrom, String smtpHost, String pop3Host,
			String needAuth, String authclassname, String isDebug,
			String tprotocol, String username, String password, String agentip,
			String agentport,String pprotocol,String tport,String pport) {
		this.tfrom = tfrom;
		this.smtpHost = smtpHost;
		this.pop3Host = pop3Host;
		this.needAuth = needAuth;
		this.authclassname = authclassname;
		this.isDebug = isDebug;
		this.tprotocol = tprotocol;
		this.username = username;
		this.password = password;
		this.agentip = agentip;
		this.agentport = agentport;
		this.pprotocol = pprotocol;
		this.tport = tport;
		this.pport = pport;
	}

	// Property accessors

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTfrom() {
		return this.tfrom;
	}

	public void setTfrom(String tfrom) {
		this.tfrom = tfrom;
	}

	public String getSmtpHost() {
		return this.smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getPop3Host() {
		return this.pop3Host;
	}

	public void setPop3Host(String pop3Host) {
		this.pop3Host = pop3Host;
	}

	public String getNeedAuth() {
		return this.needAuth;
	}

	public void setNeedAuth(String needAuth) {
		this.needAuth = needAuth;
	}

	public String getAuthclassname() {
		return this.authclassname;
	}

	public void setAuthclassname(String authclassname) {
		this.authclassname = authclassname;
	}



	public String getTprotocol() {
		return this.tprotocol;
	}

	public void setTprotocol(String tprotocol) {
		this.tprotocol = tprotocol;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgentip() {
		return this.agentip;
	}

	public void setAgentip(String agentip) {
		this.agentip = agentip;
	}

	public String getAgentport() {
		return this.agentport;
	}

	public void setAgentport(String agentport) {
		this.agentport = agentport;
	}

	public String getPprotocol() {
		return pprotocol;
	}

	public void setPprotocol(String pprotocol) {
		this.pprotocol = pprotocol;
	}

	public String getTport() {
		return tport;
	}

	public void setTport(String tport) {
		this.tport = tport;
	}

	public String getPport() {
		return pport;
	}

	public void setPport(String pport) {
		this.pport = pport;
	}

	public String getIsDebug() {
		return isDebug;
	}

	public void setIsDebug(String isDebug) {
		this.isDebug = isDebug;
	}
	
	
}