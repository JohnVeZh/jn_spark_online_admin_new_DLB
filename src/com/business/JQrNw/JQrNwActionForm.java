package com.business.JQrNw; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrNwActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String networkLiveLink;
    private String qrCode;
    private String imgPath;
    private String id;
    private String qrnwtId;
    private int networkType;
    private String networkRecordLink;
    private String networkLiveTime;
    private String name;
    private String sort;
    private String levelType;
    
    private FormFile file;

    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getNetworkLiveLink(){
      return networkLiveLink;
    }
    public void setNetworkLiveLink(String networkLiveLink){
      this.networkLiveLink = networkLiveLink;
    }
    public String getQrCode(){
      return qrCode;
    }
    public void setQrCode(String qrCode){
      this.qrCode = qrCode;
    }
    public String getImgPath(){
      return imgPath;
    }
    public void setImgPath(String imgPath){
      this.imgPath = imgPath;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getQrnwtId(){
      return qrnwtId;
    }
    public void setQrnwtId(String qrnwtId){
      this.qrnwtId = qrnwtId;
    }
    public int getNetworkType(){
      return networkType;
    }
    public void setNetworkType(int networkType){
      this.networkType = networkType;
    }
    public String getNetworkRecordLink(){
      return networkRecordLink;
    }
    public void setNetworkRecordLink(String networkRecordLink){
      this.networkRecordLink = networkRecordLink;
    }
    public String getNetworkLiveTime(){
      return networkLiveTime;
    }
    public void setNetworkLiveTime(String networkLiveTime){
      this.networkLiveTime = networkLiveTime;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
}

