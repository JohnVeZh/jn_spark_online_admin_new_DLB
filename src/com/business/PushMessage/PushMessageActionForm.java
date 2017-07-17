package com.business.PushMessage; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class PushMessageActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int pmIsPush;
    private String pmTitle;
    private String pmCreatetime;
    private String pmContent;
    private String id;
    private String pmImg;
    private String levelType; 
    
    public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	private FormFile fileImage;
    private FormFile file;
    
    public FormFile getFileImage() {
		return fileImage;
	}
	public void setFileImage(FormFile fileImage) {
		this.fileImage = fileImage;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public int getPmIsPush(){
      return pmIsPush;
    }
    public void setPmIsPush(int pmIsPush){
      this.pmIsPush = pmIsPush;
    }
    public String getPmTitle(){
      return pmTitle;
    }
    public void setPmTitle(String pmTitle){
      this.pmTitle = pmTitle;
    }
    public String getPmCreatetime(){
      return pmCreatetime;
    }
    public void setPmCreatetime(String pmCreatetime){
      this.pmCreatetime = pmCreatetime;
    }
    public String getPmContent(){
      return pmContent;
    }
    public void setPmContent(String pmContent){
      this.pmContent = pmContent;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getPmImg(){
      return pmImg;
    }
    public void setPmImg(String pmImg){
      this.pmImg = pmImg;
    }
}

