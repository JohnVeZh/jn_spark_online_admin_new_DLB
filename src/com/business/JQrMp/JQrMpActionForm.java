package com.business.JQrMp; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class JQrMpActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String code;
    private String id;
    private String qrtId;
    private String mpName;
    private String createtime;
    private int sort;
    private int isDel;
    private String url;
    private String content;
    private String levelType;

    private FormFile file;
    
    
    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getQrtId(){
      return qrtId;
    }
    public void setQrtId(String qrtId){
      this.qrtId = qrtId;
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

