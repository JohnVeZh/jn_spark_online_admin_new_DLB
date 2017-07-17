package com.business.Product; 

public class Product extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private double pPostage;
    private double pOriginalPrice;
    private double pPresentPrice;
    private String pName;
    private int pIsPostage;
    private int pIsDel;
    private String pTypeId;
    private String pContent;
    private String pCreatetime;
    private String id;
    private String pCoverImg;
    private String pGradeId;
    private String press;
    private int sales;
    private int evaluateNum;
    private String brief;
    private String specialBrief;
    private String levelType;
    private int isPromotion;
    private String pListImg;
    private String sysUserId;
    private String viewImgs;
	private String batchTime;
	private  String isShow;
	private String marketTime;

	public String getBatchTime() {
		return batchTime;
	}
	public void setBatchTime(String batchTime) {
		this.batchTime = batchTime;
	}
	public String getViewImgs() {
		return viewImgs;
	}
	public void setViewImgs(String viewImgs) {
		this.viewImgs = viewImgs;
	}
	public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public double getpPostage() {
		return pPostage;
	}
	public void setpPostage(double pPostage) {
		this.pPostage = pPostage;
	}
	public double getpOriginalPrice() {
		return pOriginalPrice;
	}
	public void setpOriginalPrice(double pOriginalPrice) {
		this.pOriginalPrice = pOriginalPrice;
	}
	public double getpPresentPrice() {
		return pPresentPrice;
	}
	public void setpPresentPrice(double pPresentPrice) {
		this.pPresentPrice = pPresentPrice;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpIsPostage() {
		return pIsPostage;
	}
	public void setpIsPostage(int pIsPostage) {
		this.pIsPostage = pIsPostage;
	}
	public int getpIsDel() {
		return pIsDel;
	}
	public void setpIsDel(int pIsDel) {
		this.pIsDel = pIsDel;
	}
	public String getpTypeId() {
		return pTypeId;
	}
	public void setpTypeId(String pTypeId) {
		this.pTypeId = pTypeId;
	}
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getpCreatetime() {
		return pCreatetime;
	}
	public void setpCreatetime(String pCreatetime) {
		this.pCreatetime = pCreatetime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpCoverImg() {
		return pCoverImg;
	}
	public void setpCoverImg(String pCoverImg) {
		this.pCoverImg = pCoverImg;
	}
	public String getpGradeId() {
		return pGradeId;
	}
	public void setpGradeId(String pGradeId) {
		this.pGradeId = pGradeId;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getEvaluateNum() {
		return evaluateNum;
	}
	public void setEvaluateNum(int evaluateNum) {
		this.evaluateNum = evaluateNum;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getSpecialBrief() {
		return specialBrief;
	}
	public void setSpecialBrief(String specialBrief) {
		this.specialBrief = specialBrief;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public int getIsPromotion() {
		return isPromotion;
	}
	public void setIsPromotion(int isPromotion) {
		this.isPromotion = isPromotion;
	}
	public String getpListImg() {
		return pListImg;
	}
	public void setpListImg(String pListImg) {
		this.pListImg = pListImg;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getMarketTime() {
		return marketTime;
	}

	public void setMarketTime(String marketTime) {
		this.marketTime = marketTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Product() {
		super();
	}
	public Product(double pPostage, double pOriginalPrice,
			double pPresentPrice, String pName, int pIsPostage, int pIsDel,
			String pTypeId, String pContent, String pCreatetime, String id,
			String pCoverImg, String pGradeId, String press, int sales,
			int evaluateNum, String brief, String specialBrief,
			String levelType, int isPromotion, String pListImg, String sysUserId,String viewImgs,String batchTime) {
		super();
		this.pPostage = pPostage;
		this.pOriginalPrice = pOriginalPrice;
		this.pPresentPrice = pPresentPrice;
		this.pName = pName;
		this.pIsPostage = pIsPostage;
		this.pIsDel = pIsDel;
		this.pTypeId = pTypeId;
		this.pContent = pContent;
		this.pCreatetime = pCreatetime;
		this.id = id;
		this.pCoverImg = pCoverImg;
		this.pGradeId = pGradeId;
		this.press = press;
		this.sales = sales;
		this.evaluateNum = evaluateNum;
		this.brief = brief;
		this.specialBrief = specialBrief;
		this.levelType = levelType;
		this.isPromotion = isPromotion;
		this.pListImg = pListImg;
		this.sysUserId = sysUserId;
		this.viewImgs = viewImgs;
		this.batchTime = batchTime;
	}
    
    

}
