package com.business.WebConfig; 

public class WebConfig extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String opetime;
    private String configContent;
    private String id;
    private String type;
    private String configName;

    public String getOpetime(){
      return opetime;
    }
    public void setOpetime(String opetime){
      this.opetime = opetime;
    }
    public String getConfigContent(){
      return configContent;
    }
    public void setConfigContent(String configContent){
      this.configContent = configContent;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getType(){
      return type;
    }
    public void setType(String type){
      this.type = type;
    }
    public String getConfigName(){
      return configName;
    }
    public void setConfigName(String configName){
      this.configName = configName;
    }

/** default constructor */
	public WebConfig() {
	}

    public WebConfig (String opetime,String configContent,String id,String type,String configName) {
      this.opetime = opetime;
      this.configContent = configContent;
      this.id = id;
      this.type = type;
      this.configName = configName;
    }
}
