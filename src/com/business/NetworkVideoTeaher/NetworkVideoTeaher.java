package com.business.NetworkVideoTeaher; 

public class NetworkVideoTeaher extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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

/** default constructor */
	public NetworkVideoTeaher() {
	}

    public NetworkVideoTeaher (String imgpath,String name,int isDel,String introduce,String id,String createtiem,int sex,String moblie) {
      this.imgpath = imgpath;
      this.name = name;
      this.isDel = isDel;
      this.introduce = introduce;
      this.id = id;
      this.createtiem = createtiem;
      this.sex = sex;
      this.moblie = moblie;
    }
}
