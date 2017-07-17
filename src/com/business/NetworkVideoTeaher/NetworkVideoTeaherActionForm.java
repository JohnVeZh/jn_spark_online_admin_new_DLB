package com.business.NetworkVideoTeaher; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class NetworkVideoTeaherActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String imgpath;
    private String name;
    private int isDel;
    private String introduce;
    private String id;
    private String createtiem;
    private int sex;
    private String moblie;
    
    private FormFile file;
    
    
    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getImgpath(){
      return imgpath;
    }
    public void setImgpath(String imgpath){
      this.imgpath = imgpath;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public String getIntroduce(){
      return introduce;
    }
    public void setIntroduce(String introduce){
      this.introduce = introduce;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCreatetiem(){
      return createtiem;
    }
    public void setCreatetiem(String createtiem){
      this.createtiem = createtiem;
    }
    public int getSex(){
      return sex;
    }
    public void setSex(int sex){
      this.sex = sex;
    }
    public String getMoblie(){
      return moblie;
    }
    public void setMoblie(String moblie){
      this.moblie = moblie;
    }
}

