package com.business.NetworkVideoCatalog; 

public class NetworkVideoCatalog extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String nvId;
    private String parentId;
    private String cName;
    private String id;
    private int sort;
    

/** default constructor */

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
	public NetworkVideoCatalog(String nvId, String parentId, String cName,
			String id, int sort) {
		super();
		this.nvId = nvId;
		this.parentId = parentId;
		this.cName = cName;
		this.id = id;
		this.sort = sort;
	}

	public NetworkVideoCatalog() {
		super();
	}
	
}
