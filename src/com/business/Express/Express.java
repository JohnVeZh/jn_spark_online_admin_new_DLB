package com.business.Express; 

public class Express extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String eUrl;
    private String id;
    private String eName;
    private String eCode;
    private String eState;
    private String eZtState;
    private String eOrder;
    private String eLetter;


public String geteUrl() {
		return eUrl;
	}

	public void seteUrl(String eUrl) {
		this.eUrl = eUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteCode() {
		return eCode;
	}

	public void seteCode(String eCode) {
		this.eCode = eCode;
	}

	public String geteState() {
		return eState;
	}

	public void seteState(String eState) {
		this.eState = eState;
	}

	public String geteZtState() {
		return eZtState;
	}

	public void seteZtState(String eZtState) {
		this.eZtState = eZtState;
	}

	public String geteOrder() {
		return eOrder;
	}

	public void seteOrder(String eOrder) {
		this.eOrder = eOrder;
	}

	public String geteLetter() {
		return eLetter;
	}

	public void seteLetter(String eLetter) {
		this.eLetter = eLetter;
	}

/** default constructor */
	public Express() {
	}

    public Express (String eUrl,String id,String eName,String eCode,String eState,String eZtState,String eOrder,String eLetter) {
      this.eUrl = eUrl;
      this.id = id;
      this.eName = eName;
      this.eCode = eCode;
      this.eState = eState;
      this.eZtState = eZtState;
      this.eOrder = eOrder;
      this.eLetter = eLetter;
    }
}
