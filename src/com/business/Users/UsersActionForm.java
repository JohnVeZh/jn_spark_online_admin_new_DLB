package com.business.Users; 

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



import com.easecom.common.framework.struts.BaseForm;

public class UsersActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int sex;
    private String qq;
    private String id;
    private String wx;
    private int score;
    private String realname;
    private String icon;
    private String createTime;
    private String email;
    private String loginTime;
    private double balance;
    private String mobile;
    private double mapx;
    private String password;
    private double mapy;
    private int growing;
    private String username;
    private String iosOff;
    private String imel;
    private String account;
    private String imsi;
    private String token;
    private String code;
    private int IsDisable;
    private String alipayId;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String address;
    private String zipcode;
    private int userType;
    private int mobileState;
    private int fActivation;
    private int sActivation;
    private String wbOpenId;
    private FormFile fileImage;
    private FormFile file;
    private int fromType;
    
    
    public int getfActivation() {
		return fActivation;
	}
	public void setfActivation(int fActivation) {
		this.fActivation = fActivation;
	}
	public int getsActivation() {
		return sActivation;
	}
	public void setsActivation(int sActivation) {
		this.sActivation = sActivation;
	}
	public String getWbOpenId() {
		return wbOpenId;
	}
	public void setWbOpenId(String wbOpenId) {
		this.wbOpenId = wbOpenId;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public int getMobileState() {
		return mobileState;
	}
	public void setMobileState(int mobileState) {
		this.mobileState = mobileState;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
    public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public FormFile getFileImage() {
		return fileImage;
	}
	public void setFileImage(FormFile fileImage) {
		this.fileImage = fileImage;
	}
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public int getIsDisable() {
		return IsDisable;
	}
	public void setIsDisable(int isDisable) {
		IsDisable = isDisable;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
    public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getQq(){
      return qq;
    }
    public void setQq(String qq){
      this.qq = qq;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getWx(){
      return wx;
    }
    public void setWx(String wx){
      this.wx = wx;
    }
    public int getScore(){
      return score;
    }
    public void setScore(int score){
      this.score = score;
    }
    public String getRealname(){
      return realname;
    }
    public void setRealname(String realname){
      this.realname = realname;
    }
    public String getIcon(){
      return icon;
    }
    public void setIcon(String icon){
      this.icon = icon;
    }
    public String getCreateTime(){
      return createTime;
    }
    public void setCreateTime(String createTime){
      this.createTime = createTime;
    }
    public String getEmail(){
      return email;
    }
    public void setEmail(String email){
      this.email = email;
    }
    public String getLoginTime(){
      return loginTime;
    }
    public void setLoginTime(String loginTime){
      this.loginTime = loginTime;
    }
    public double getBalance(){
      return balance;
    }
    public void setBalance(double balance){
      this.balance = balance;
    }
    public String getMobile(){
      return mobile;
    }
    public void setMobile(String mobile){
      this.mobile = mobile;
    }
    public double getMapx(){
      return mapx;
    }
    public void setMapx(double mapx){
      this.mapx = mapx;
    }
    public String getPassword(){
      return password;
    }
    public void setPassword(String password){
      this.password = password;
    }
    public double getMapy(){
      return mapy;
    }
    public void setMapy(double mapy){
      this.mapy = mapy;
    }
    public int getGrowing(){
      return growing;
    }
    public void setGrowing(int growing){
      this.growing = growing;
    }
    public String getUsername(){
      return username;
    }
    public void setUsername(String username){
      this.username = username;
    }
    public String getIosOff(){
      return iosOff;
    }
    public void setIosOff(String iosOff){
      this.iosOff = iosOff;
    }
    public String getImel(){
      return imel;
    }
    public void setImel(String imel){
      this.imel = imel;
    }
    public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getImsi(){
      return imsi;
    }
    public void setImsi(String imsi){
      this.imsi = imsi;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public void clear() {
    	file = null;
    	 }
}

