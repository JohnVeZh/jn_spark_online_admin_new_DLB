package com.business.MatchedPapers; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class MatchedPapersActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private String mpListImgpath;
    private String mpName;
    private String createtime;
    private String mpViewImgpath;
    private String mptId;
    private int sort;
    private int isDel;
    private String code;
    private String qrCode;
    
    private FormFile file;
    
    public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getMpListImgpath(){
      return mpListImgpath;
    }
    public void setMpListImgpath(String mpListImgpath){
      this.mpListImgpath = mpListImgpath;
    }
    public String getMpName(){
      return mpName;
    }
    public void setMpName(String mpName){
      this.mpName = mpName;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getMpViewImgpath(){
      return mpViewImgpath;
    }
    public void setMpViewImgpath(String mpViewImgpath){
      this.mpViewImgpath = mpViewImgpath;
    }
    public String getMptId(){
      return mptId;
    }
    public void setMptId(String mptId){
      this.mptId = mptId;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public void clear() {
    	file = null;
    }
}

