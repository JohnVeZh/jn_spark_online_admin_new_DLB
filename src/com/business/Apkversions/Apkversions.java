package com.business.Apkversions;

import java.io.Serializable;

public class Apkversions extends com.easecom.common.framework.hibernate.BaseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private String apkname;
    private String apkversion;
    private String apkurl;
    private String isnew;
    private String filesize;
    private String remark;
    private String updatetime;
    private String clienttype;
    private String type;
    private String twoCode;
    private String versionName;
    private String downloadUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApkname() {
        return apkname;
    }

    public void setApkname(String apkname) {
        this.apkname = apkname;
    }

    public String getApkversion() {
        return apkversion;
    }

    public void setApkversion(String apkversion) {
        this.apkversion = apkversion;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getClienttype() {
        return clienttype;
    }

    public void setClienttype(String clienttype) {
        this.clienttype = clienttype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTwoCode() {
        return twoCode;
    }

    public void setTwoCode(String twoCode) {
        this.twoCode = twoCode;
    }

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
