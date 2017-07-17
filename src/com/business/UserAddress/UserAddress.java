package com.business.UserAddress; 

public class UserAddress extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String userId;
    private String phone;
    private String realname;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String isView;
    private String zipcode;
    private String detailAddress;
    private String createtime;
    private String districtCn;
    private String id;
    private String isDef;

    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }
    public String getPhone(){
      return phone;
    }
    public void setPhone(String phone){
      this.phone = phone;
    }
    public String getRealname(){
      return realname;
    }
    public void setRealname(String realname){
      this.realname = realname;
    }
    public String getProvinceId(){
      return provinceId;
    }
    public void setProvinceId(String provinceId){
      this.provinceId = provinceId;
    }
    public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getIsView(){
      return isView;
    }
    public void setIsView(String isView){
      this.isView = isView;
    }
    public String getZipcode(){
      return zipcode;
    }
    public void setZipcode(String zipcode){
      this.zipcode = zipcode;
    }
    public String getDetailAddress(){
      return detailAddress;
    }
    public void setDetailAddress(String detailAddress){
      this.detailAddress = detailAddress;
    }
    public String getCityId(){
      return cityId;
    }
    public void setCityId(String cityId){
      this.cityId = cityId;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getDistrictCn(){
      return districtCn;
    }
    public void setDistrictCn(String districtCn){
      this.districtCn = districtCn;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }

    
    public String getIsDef() {
		return isDef;
	}
	public void setIsDef(String isDef) {
		this.isDef = isDef;
	}
/** default constructor */
	public UserAddress() {
	}
public UserAddress(String userId, String phone, String realname,
		String provinceId, String areaId, String isView, String zipcode,
		String detailAddress, String cityId, String createtime,
		String districtCn, String id, String isDef) {
	super();
	this.userId = userId;
	this.phone = phone;
	this.realname = realname;
	this.provinceId = provinceId;
	this.areaId = areaId;
	this.isView = isView;
	this.zipcode = zipcode;
	this.detailAddress = detailAddress;
	this.cityId = cityId;
	this.createtime = createtime;
	this.districtCn = districtCn;
	this.id = id;
	this.isDef = isDef;
}

}
