package com.business.Users; 

public class Users extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
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
    private int isDisable;
    private String alipayId;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String address;
    private String zipcode;
    private int userType;
    private int mobileState;
    private String qqOpenId;
    private int fActivation;
    private int sActivation;
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
	public String getQqOpenId() {
		return qqOpenId;
	}
	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public int getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(int isDisable) {
		this.isDisable = isDisable;
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

    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
/** default constructor */
	public Users() {
	}
public Users(int mobileState,int userType,String zipcode,int sex, String qq, String id, String wx, int score,
		String realname, String icon, String createTime, String email,
		String loginTime, double balance, String mobile, double mapx,
		String password, double mapy, int growing, String username,
		String iosOff, String imel, String account, String imsi, String token,
		String code, int isDisable, String alipayId, String provinceId,
		String cityId, String areaId,String address) {
	super();
	this.sex = sex;
	this.qq = qq;
	this.id = id;
	this.wx = wx;
	this.score = score;
	this.realname = realname;
	this.icon = icon;
	this.createTime = createTime;
	this.email = email;
	this.loginTime = loginTime;
	this.balance = balance;
	this.mobile = mobile;
	this.mapx = mapx;
	this.password = password;
	this.mapy = mapy;
	this.growing = growing;
	this.username = username;
	this.iosOff = iosOff;
	this.imel = imel;
	this.account = account;
	this.imsi = imsi;
	this.token = token;
	this.code = code;
	this.isDisable = isDisable;
	this.alipayId = alipayId;
	this.provinceId = provinceId;
	this.cityId = cityId;
	this.areaId = areaId;
	this.address = address;
	this.zipcode = zipcode;
	this.userType = userType;
	this.mobileState = mobileState;
}

}
