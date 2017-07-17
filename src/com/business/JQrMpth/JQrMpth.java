package com.business.JQrMpth; 

public class JQrMpth extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String code;
    private String subjectType;
    private String id;
    private String hContent;
    private String qrmpId;
    private int sort;
    private String hName;
    private int isDel;


public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gethContent() {
		return hContent;
	}

	public void sethContent(String hContent) {
		this.hContent = hContent;
	}

	public String getQrmpId() {
		return qrmpId;
	}

	public void setQrmpId(String qrmpId) {
		this.qrmpId = qrmpId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

/** default constructor */
	public JQrMpth() {
	}

    public JQrMpth (String code,String subjectType,String id,String hContent,String qrmpId,int sort,String hName,int isDel) {
      this.code = code;
      this.subjectType = subjectType;
      this.id = id;
      this.hContent = hContent;
      this.qrmpId = qrmpId;
      this.sort = sort;
      this.hName = hName;
      this.isDel = isDel;
    }
}
