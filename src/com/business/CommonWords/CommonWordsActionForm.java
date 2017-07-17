package com.business.CommonWords; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class CommonWordsActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int testFrequency;
    private String ctId;
    private String createtime;
    private String audioPath;
    private String phonogram;
    private String id;
    private String word;
    private String content;
    private String intro;
    private int sort;
    private FormFile file;

    
    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getTestFrequency(){
      return testFrequency;
    }
    public void setTestFrequency(int testFrequency){
      this.testFrequency = testFrequency;
    }
    public String getCtId(){
      return ctId;
    }
    public void setCtId(String ctId){
      this.ctId = ctId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getAudioPath(){
      return audioPath;
    }
    public void setAudioPath(String audioPath){
      this.audioPath = audioPath;
    }
    public String getPhonogram(){
      return phonogram;
    }
    public void setPhonogram(String phonogram){
      this.phonogram = phonogram;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getWord(){
      return word;
    }
    public void setWord(String word){
      this.word = word;
    }
    public String getContent(){
      return content;
    }
    public void setContent(String content){
      this.content = content;
    }
    public int getSort(){
      return sort;
    }
    public void setSort(int sort){
      this.sort = sort;
    }
    public void clear() {
    	file = null;
    }
}

