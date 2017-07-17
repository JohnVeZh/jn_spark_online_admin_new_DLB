package com.business.UserAddress; 

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.easecom.common.framework.struts.BaseForm;

public class UserAddressActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String userId;
    private String phone;
    private String realname;
    private String provinceId;
    private String areaId;
    private String isView;
    private String zipcode;
    private String detailAddress;
    private String cityId;
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
    
}

