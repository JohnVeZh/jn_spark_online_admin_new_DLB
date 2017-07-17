package com.business.SystemCodeTable; 

public class SystemCodeTable extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String remark;
    private String characterEn;
    private String id;
    private String code;
    private String updatetime;
    private String characterCn;

    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
    }
    public String getCharacterEn(){
      return characterEn;
    }
    public void setCharacterEn(String characterEn){
      this.characterEn = characterEn;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCode(){
      return code;
    }
    public void setCode(String code){
      this.code = code;
    }
    public String getUpdatetime(){
      return updatetime;
    }
    public void setUpdatetime(String updatetime){
      this.updatetime = updatetime;
    }
    public String getCharacterCn(){
      return characterCn;
    }
    public void setCharacterCn(String characterCn){
      this.characterCn = characterCn;
    }

/** default constructor */
	public SystemCodeTable() {
	}

    public SystemCodeTable (String remark,String characterEn,String id,String code,String updatetime,String characterCn) {
      this.remark = remark;
      this.characterEn = characterEn;
      this.id = id;
      this.code = code;
      this.updatetime = updatetime;
      this.characterCn = characterCn;
    }
}
