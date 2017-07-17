package com.business.NetworkVideoCatalog; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NetworkVideoCatalogActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String nvId;
    private String parentId;
    private String cName;
    private String id;
    private int sort;
	public String getNvId() {
		return nvId;
	}
	public void setNvId(String nvId) {
		this.nvId = nvId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

}

