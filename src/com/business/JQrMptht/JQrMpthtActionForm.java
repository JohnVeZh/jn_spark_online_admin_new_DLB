package com.business.JQrMptht; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class JQrMpthtActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String code;
    private String url;
    private int isDel;
    private String id;
    private String content;
    private String qrmpthId;
    private int sort;
    private String name;

    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getUrl(){
      return url;
    }
    public void setUrl(String url){
      this.url = url;
    }
    public int getIsDel(){
      return isDel;
    }
    public void setIsDel(int isDel){
      this.isDel = isDel;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public String getQrmpthId(){
      return qrmpthId;
    }
    public void setQrmpthId(String qrmpthId){
      this.qrmpthId = qrmpthId;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
}

