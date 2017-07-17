package com.business.PreferentialCodeGenerate; 

public class PreferentialCodeGenerate extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int pcgNum;
    private String id;
    private String pcId;
    private String pcgCode;
    private String createtime;

    public int getPcgNum(){
      return pcgNum;
    }
    public void setPcgNum(int pcgNum){
      this.pcgNum = pcgNum;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getPcId(){
      return pcId;
    }
    public void setPcId(String pcId){
      this.pcId = pcId;
    }
    public String getPcgCode(){
      return pcgCode;
    }
    public void setPcgCode(String pcgCode){
      this.pcgCode = pcgCode;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }

/** default constructor */
	public PreferentialCodeGenerate() {
	}

    public PreferentialCodeGenerate (int pcgNum,String id,String pcId,String pcgCode,String createtime) {
      this.pcgNum = pcgNum;
      this.id = id;
      this.pcId = pcId;
      this.pcgCode = pcgCode;
      this.createtime = createtime;
    }
}
