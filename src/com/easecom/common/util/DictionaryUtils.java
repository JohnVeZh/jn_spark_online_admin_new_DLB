package com.easecom.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easecom.system.business.SysConfigMgr;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysConfig;
import com.easecom.system.model.SystemProvinceCity;

public class DictionaryUtils {
	/**服务端路径*/
	public static final String WEB_PATH = "http://localhost:8080/";
//	public static final String WEB_PATH = "http://192.168.0.211:8080/wf_planea/";
	public static final String WEB_PHONE_PATH = "http://114.55.87.86:8080/";
	public static final String WEB_INTEFACE_PATH = "http://114.55.87.86:8988/";//接口路径

	
 
	/**省份城市 list */
	public static ArrayList<Map<String, Object>> AREA_PROVINCE_CITY_LIST = new ArrayList<Map<String, Object>>();
	public static final String AVATOR_FILEPATH = "avator/";
	
	/**会员头像路径*/
	public static final String USER_ICON_PATH = "uploadFile/userImg/userIcon";
	
	/**新闻Phone图片*/
	public static final String NEWS_ICON_PATH_PHONEIMG = "uploadFile/newsImg/phoneImg";
	public static final String POST_PATH_PHONEIMG = "uploadFile/post/phoneImg";
	
	
	/**试卷Phone图片*/
	public static final String MP_ICON_PATH_PHONEIMG = "uploadFile/mpImg/phoneImg";
	
	/**产品类型Phone图片*/
	public static final String PRODUCT_TYPE_ICON_PATH_PHONEIMG = "uploadFile/productTypeIcon/phoneImg";
	
	
	/**商铺图片*/
	public static final String SHOP_ICON_PATH = "uploadFile/ShopIcon";
	
	/**商铺图片*/
	public static final String SYSCONFIG_ICON_PATH = "uploadFile/sysFile";
	
	/**商品属性pad*/
	public static final String ATTRBUTE_ICON_PATH_PAD = "uploadFile/attributeImg/padImg";
	/**商品属性Phone*/
	public static final String ATTRBUTE_ICON_PATH_PHONE = "uploadFile/attributeImg/phoneImg";
	
	/**网课视频*/
	public static final String NETWORK_PATH_PHONE = "uploadFile/network/phoneImg";
	/**网课讲师*/
	public static final String NETWORK_TEACHER_PATH_PHONE = "uploadFile/network_teacher/phoneImg";
	
	/**前.手机端**/
	public static final String STYLE_FRONT_IMG_PHONE = "uploadFile/styleFront/phoneImg";
	/**前.Pad端**/
	public static final String STYLE_FRONT_IMG_PAD = "uploadFile/styleFront/padImg";
	/**后.手机端**/
	public static final String STYLE_BEHIND_IMG_PHONE = "uploadFile/styleBehind/phoneImg";
	/**前.Pad端**/
	public static final String STYLE_BEHIND_IMG_PAD = "uploadFile/styleBehind/padImg";
	
	
	/**商品类型pad*/
	public static final String STYLECLASS_ICON_PATH_PAD = "uploadFile/styleClass/padImg";
	/**商品类型phone*/
	public static final String STYLECLASS_ICON_PATH_PHONE = "uploadFile/styleClass/phoneImg";
	
	/**字体padPath*/
	public static final String FONT_ICON_PATH_PAD = "uploadFile/font/padImg";
	/**字体phonePath*/
	public static final String FONT_ICON_PATH_PHONE = "uploadFile/font/phoneImg";

	/**列表phonePath*/
	public static final String PRODUCT_ICON_PATH_PHONE = "uploadFile/product/phoneImg";
	/**订购大图片maxphonePath*/
	public static final String PRODUCT_ICON_PATH_MAXPHONE = "uploadFile/product/maxphoneImg";
	/**订单小图片minphonePath*/
	public static final String PRODUCT_ICON_PATH_MINPHONE = "uploadFile/product/minphoneImg";
	/**轮播phonePath*/
	public static final String PRODUCT_ICON_PATH_FILESPHONE = "uploadFile/product/filesPhoneImg";
	/**分享图片路径*/
	public static final String SHAREINFO_IMG = "uploadFile/shareInfo/filesImg";

	/**=============订单状态======================*/
	public static final String ORDER_STATE_NOT_PAID="not_paid";//未支付
	public static final String ORDER_STATE_NOT_DELIVER="not_deliver";//待发货
	public static final String ORDER_STATE_NOT_RECEIVED="not_received";//待收货
	public static final String ORDER_STATE_NOT_COMMENT="not_comment";//待评论
	public static final String ORDER_STATE_BEEN_CANCELED="been_canceled";//已取消
	public static final String ORDER_STATE_NOT_COMPLETED="completed";//已完成

	/**极光推送相关信息*/
//	public static final String APPKEY="698be8ff956a7e8f2ea10073";//手机端
//	public static final String MASTERSECRET="1463e5a29830ecf347216391";//手机端
	public static final String APPKEY = "aa3c5140aed1a25041edeb18";			// 测试
	public static final String MASTERSECRET = "cd3fb4ff9cddcc5deb894a41";	// 测试

	/**支付宝相关信息*/
	public static final String PARTNER="2088121043178191";//支付宝PID
	// 商户的私钥
	public static final String PRIVATE_KEY="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM5ZerHfZHIol8ekDe+qawZvAdhzWt/6edmEjWyvr2snXLHbnoWIWq13M/OftzwwtnAVDYLZ3O4pFUJUqypPs64NhmIw94G2yWsMwUYbqCtRgqXKdZP8j2cqIwBTZITn+YsQKa0lhRl50V7vWVk2pc/TZ3tPERL575Uc8b9MI0kJAgMBAAECgYEAnbaSKmUddotEBpiC9k0AY/hwJql0KYBdpTzEgu98WZ/KHz5GWLUw6SgUkv3dpKlupxfQLdSqIXVRBFOGR16Hav8NZ6/cacSfaABmwbZ5XcduxOL23Cg6J81gwArUOmW7cZQHoor8s7MW+VNTAukmjKV9PnXUP0ae4FDjn6wvPEkCQQDquQ+0Q6xh3ZvgTiW4ZORLsYA3YTu4Q0HBhAJKiUQqWQ9FcHRrHH5DC4moUmhftvpfr0Y0Ls7jOevYKZet+I5nAkEA4Q39kd7zLwisAIipsC9f6yAl3LJdcY6d9vGd0iDtYj8gV4e0igs6fO4vlXINPp0IBz+c/ewlBVUFepvu6MznDwJAWEYm8M1unBOi1rWtG+7dqNXleW5Y7lBk/2ktD2kWOLLrulqzfcDmf0Jsj+AGHtEK0kdxqJhd+DjFSLNDtwq+SwJBAMyxxOhgOB+Xa5owsuYksMFSxB0vvf4DEXoB2OG+QSsS73+tVnAYNmJa4byzt4gzm5G8MjZiCmoHIqyaF3p2o9ECQQCFAtHWOFInoAsU/foBn6GanJGaipklIOAr4ivhn2+FBwOfWHdA4cPd5LvHQklpB+qXzm2lpTtp5gxjkAgKhbrH";
	//支付宝的公钥，无需修改该值
	public static final String ALI_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	/**微信相关信息*/
	public static final String APP_ID="wx2b44ad8f047231a2";//微信开发平台应用id
	public static final String APP_SECRET="1f954fa4051f2b2e9910a01e7ee9a46c";//应用对应的凭证
	public static final String APP_KEY="";//
	public static final String CFT_PARTNER="1298355101";//财付通商户号
	public static final String PARTNER_KEY="planeao2o88888888888888888888888";//商户号对应的密钥
	
	
	/**融云*/
	public static final String RONG_APPKEY="p5tvi9dstz5h4";
	public static final String RONG_APPSECRET="mm6yDvxvytHrx";
	
	static{
		try {
			flushCache();
		} catch (SystemException e) {
		}
	}
	
	/**
	 * 刷新缓存信息
	 */
	public static void flushCache() throws SystemException{
		try {
			flushProvinceCityCache();
		} catch (SystemException e) {
			throw e;
		}
	}
	
	
	
	/**
	 * 刷新地区下所有城市信息缓存
	 */
	public static void flushAreaProvinceCityCache() throws SystemException{
		/** 刷新区域省份城市缓存  start */
		AREA_PROVINCE_CITY_LIST.clear();
		
		try {
			// 获取所有地区列表
			List areaQueryConds = new ArrayList();
			areaQueryConds.add(new QueryCond("sc.type", "String", "=", "areas"));
			List areaList = new SysConfigMgr().list(areaQueryConds, 0, null);
			
			// 获取所有省份城市
			List provinceCityList = SysConfigMgr.provinceCityList();
			//如果地区不为空
			if(null!=areaList && areaList.size()>0){
				for(int areaIndex = 0; areaIndex<areaList.size(); areaIndex++){
					SysConfig areaPo = (SysConfig)areaList.get(areaIndex);
					Map<String, Object> areaMap = new HashMap<String, Object>();
					areaMap.put("id", areaPo.getId());
					areaMap.put("name", areaPo.getName());
					
					List provinceList = new ArrayList();
					
					if(null!=provinceCityList && provinceCityList.size()>0){
						// 循环遍历，取出所有省份
						for(int i=0; i<provinceCityList.size(); i++){
							SystemProvinceCity province = (SystemProvinceCity)provinceCityList.get(i);
							if("FFFFFF".equals(province.getParentid()) && areaPo.getId().equals(province.getAreaid())){
								Map<String, Object> provinceMap = new HashMap<String, Object>();
								provinceMap.put("id", province.getId());
								provinceMap.put("name", province.getName());
								
								List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
								// 循环遍历，取出当前省份下的所有城市
								for(int j=0; j<provinceCityList.size(); j++){
									SystemProvinceCity city = (SystemProvinceCity)provinceCityList.get(j);
									if(province.getId().equals(city.getParentid())){
										Map<String, Object> cityMap = new HashMap<String, Object>();
										cityMap.put("id", city.getId());
										cityMap.put("name", city.getName());
										
										List<Map<String, Object>> countyList = new ArrayList<Map<String, Object>>();
										for(int z=0; z<provinceCityList.size(); z++){
											SystemProvinceCity county = (SystemProvinceCity)provinceCityList.get(z);
											if(county.getParentid().equals(city.getId())){
												Map<String, Object> countyMap = new HashMap<String, Object>();
												countyMap.put("id", county.getId());
												countyMap.put("name", county.getName());
												
												List<Map<String, Object>> streetList = new ArrayList<Map<String, Object>>();
												for(int k=0; k<provinceCityList.size(); k++){
													SystemProvinceCity street = (SystemProvinceCity)provinceCityList.get(k);
													if(street.getParentid().equals(county.getId())){
														Map<String, Object> streetMap = new HashMap<String, Object>();
														streetMap.put("id", street.getId());
														streetMap.put("name", street.getName());
														streetList.add(streetMap);
													}
												}
												countyList.add(countyMap);
												countyMap.put("fifth",streetList);
											}
										}
										cityMap.put("fourth", countyList);
										cityList.add(cityMap);
									}
								}
								
								provinceMap.put("third", cityList);
								provinceList.add(provinceMap);
							}
						}
					}
					areaMap.put("second", provinceList);
					AREA_PROVINCE_CITY_LIST.add(areaMap);
				}
			}
		} catch (Exception e) {
			throw new SystemException("刷新省份城市缓存时发生错误", e);
		}
		/** 刷新所有省份城市缓存  end */
	}
	
	/**
	 * 获取省份下面所有 城市
	 * @throws SystemException
	 * @author 李来源
	 * Jul 22, 20131:56:12 PM
	 */
	public static void flushProvinceCityCache() throws SystemException{
		/** 刷新区域省份城市缓存  start */
		AREA_PROVINCE_CITY_LIST.clear();
		try {
			// 获取所有地区列表
			List provinceList = new ArrayList();
			// 获取所有省份城市
			List provinceCityList = SysConfigMgr.provinceCityList();
			//如果省份不为空
			if(null!=provinceCityList && provinceCityList.size()>0){
				// 循环遍历，取出所有省份
				for(int i=0; i<provinceCityList.size(); i++){
					SystemProvinceCity province = (SystemProvinceCity)provinceCityList.get(i);
					if("FFFFFF".equals(province.getParentid())){
						Map<String, Object> provinceMap = new HashMap<String, Object>();
						provinceMap.put("id", province.getId());
						provinceMap.put("name", province.getName());
						List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
						// 循环遍历，取出当前省份下的所有城市
						for(int j=0; j<provinceCityList.size(); j++){
							SystemProvinceCity city = (SystemProvinceCity)provinceCityList.get(j);
							if(province.getId().equals(city.getParentid())){
								Map<String, Object> cityMap = new HashMap<String, Object>();
								cityMap.put("id", city.getId());
								cityMap.put("name", city.getName());
								
								//取得城市下面所有区县
								List<Map<String, Object>> countyList = new ArrayList<Map<String, Object>>();
								for(int z=0; z<provinceCityList.size(); z++){
									SystemProvinceCity county = (SystemProvinceCity)provinceCityList.get(z);
									if(county.getParentid().equals(city.getId())){
										Map<String, Object> countyMap = new HashMap<String, Object>();
										countyMap.put("id", county.getId());
										countyMap.put("name", county.getName());
										
//										List<Map<String, Object>> streetList = new ArrayList<Map<String, Object>>();
//										for(int k=0; k<provinceCityList.size(); k++){
//											SystemProvinceCity street = (SystemProvinceCity)provinceCityList.get(k);
//											if(street.getParentid().equals(county.getId())){
//												Map<String, Object> streetMap = new HashMap<String, Object>();
//												streetMap.put("id", street.getId());
//												streetMap.put("name", street.getName());
//												streetList.add(streetMap);
//											}
//										}
										countyList.add(countyMap);
//										countyMap.put("fourth",streetList);
									}
								}
								cityMap.put("countrys", countyList);
								cityList.add(cityMap);
							}
						}
						provinceMap.put("citys", cityList);
						AREA_PROVINCE_CITY_LIST.add(provinceMap);
					}
				}
				
			}
		}catch (Exception e) {
			throw new SystemException("刷新省份城市缓存时发生错误", e);
		}
	
	}
	

	/**
	 * 根据参数编号获取，地区、省份、城市、县区编号
	 * @param cityId
	 * @return
	 * <b>areaId  地区编号</b>
	 * <b>provinceId  省份编号</b>
	 * <b>cityId  城市编号</b>
	 * <b>countyId  县区编号</b>
	 */
	public static Map<String, String> getAreaProvinceidByCityid(String id){
		Map<String, String> areaProvinceCity = new HashMap<String, String>();
		// 如果地区省份城市列表不为空
		if(AREA_PROVINCE_CITY_LIST!=null && AREA_PROVINCE_CITY_LIST.size()>0){
			// 循环地区列表
			for(int i=0; i<AREA_PROVINCE_CITY_LIST.size(); i++){
				Map areaProvinceCityMap = (Map)AREA_PROVINCE_CITY_LIST.get(i);
				
				String areaId = areaProvinceCityMap.get("id")+"";
				if(areaId.equals(id)){
					areaProvinceCity.put("areaId", areaId);
					return areaProvinceCity;
				}else{
					// 获取当前地区的省份列表
					List secondsList = (List)(areaProvinceCityMap.get("second")!=null?areaProvinceCityMap.get("second"):new ArrayList());
					if(null!=secondsList && secondsList.size()>0){
						// 如果省份列表不为空，则遍历所有省份，如果和参数指定的省份编号相同，则返回此省份与地区的编号，如果不相同则继续遍历城市信息
						for(int j=0; j<secondsList.size(); j++){
							Map provinceMap = (Map)secondsList.get(j);
							String provinceId = (String)provinceMap.get("id");
							if(provinceId.equals(id)){
								areaProvinceCity.put("areaId", areaId);
								areaProvinceCity.put("provinceId", provinceId);

								return areaProvinceCity;
							}else{
								List thirdList = (List)provinceMap.get("third");
								// 如果城市列表不为空，则循环城市
								if(null!=thirdList && thirdList.size()>0){
									for(int z=0; z<thirdList.size(); z++){
										Map cityMap = (Map)thirdList.get(z);
										String cityId = (String)cityMap.get("id");
										if(cityId.equals(id)){
											areaProvinceCity.put("areaId", areaId);
											areaProvinceCity.put("provinceId", provinceId);
											areaProvinceCity.put("cityId", cityId);
										}else{
											List fourthList = (List)(cityMap.get("fourth")==null?new ArrayList():cityMap.get("fourth"));
											if(null!=fourthList && fourthList.size()>0){
												for(int x=0; x<fourthList.size(); x++){
													Map countyMap = (Map)fourthList.get(x);
													String countyId = (String)countyMap.get("id");
													if(countyId.equals(id)){
														areaProvinceCity.put("areaId", areaId);
														areaProvinceCity.put("provinceId", provinceId);
														areaProvinceCity.put("cityId", cityId);
														areaProvinceCity.put("countyId", countyId);
													}else{
														List fifthList = (List)(countyMap.get("fifth")==null?new ArrayList():countyMap.get("fifth"));
														if(null!=fifthList && fifthList.size()>0){
															for(int k=0; k<fifthList.size(); k++){
																Map streetMap = (Map)fifthList.get(k);
																String streetId = (String)streetMap.get("id");
																if(streetId.equals(id)){
																	areaProvinceCity.put("areaId", areaId);
																	areaProvinceCity.put("provinceId", provinceId);
																	areaProvinceCity.put("cityId", cityId);
																	areaProvinceCity.put("countyId", countyId);
																	areaProvinceCity.put("streetId", streetId);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return areaProvinceCity;
	}
	
	/**
	 * 根据名称获取地区、省份、城市编号
	 * @param name
	 * @return
	 */
	public static Map<String, String> getAreaProvinceCityidByName(String name){
		Map<String, String> areaProvinceCity = new HashMap<String, String>();
		
		// 如果地区省份城市列表不为空
		if(AREA_PROVINCE_CITY_LIST!=null && AREA_PROVINCE_CITY_LIST.size()>0){
			// 循环地区列表
			for(int i=0; i<AREA_PROVINCE_CITY_LIST.size(); i++){
				Map areaProvinceCityMap = (Map)AREA_PROVINCE_CITY_LIST.get(i);
				String areaId = areaProvinceCityMap.get("id")+"";
				String areaName = areaProvinceCityMap.get("name")+"";
				if(cityNameEquals(name, areaName)){
					areaProvinceCity.put("areaId", areaId);
					
					return areaProvinceCity;
				}else{
					// 获取当前地区的省份列表
					List secondsList = (List)(areaProvinceCityMap.get("second")!=null?areaProvinceCityMap.get("second"):new ArrayList());
					if(null!=secondsList && secondsList.size()>0){
						// 如果省份列表不为空，则遍历所有省份，如果和参数指定的省份编号相同，则返回此省份与地区的编号，如果不相同则继续遍历城市信息
						for(int j=0; j<secondsList.size(); j++){
							Map provinceMap = (Map)secondsList.get(j);
							String provinceId = (String)provinceMap.get("id");
							String provinceName = (String)provinceMap.get("name");
							if( cityNameEquals(name, provinceName)){
								areaProvinceCity.put("areaId", areaId);
								areaProvinceCity.put("provinceId", provinceId);

								return areaProvinceCity;
							}else{
								List thirdList = (List)provinceMap.get("third");
								// 如果城市列表不为空，则循环城市
								if(null!=thirdList && thirdList.size()>0){
									for(int z=0; z<thirdList.size(); z++){
										Map cityMap = (Map)thirdList.get(z);
										String cityId = (String)cityMap.get("id");
										String cityName = (String)cityMap.get("name");
										if(cityNameEquals(name, cityName)){
											areaProvinceCity.put("areaId", areaId);
											areaProvinceCity.put("provinceId", provinceId);
											areaProvinceCity.put("cityId", cityId);
										}else{
											List fourthList = (List)(cityMap.get("fourth")==null?new ArrayList():cityMap.get("fourth"));
											if(null!=fourthList && fourthList.size()>0){
												for(int x=0; x<fourthList.size(); x++){
													Map countyMap = (Map)fourthList.get(x);
													String countyId = (String)countyMap.get("id");
													String countyName = (String)countyMap.get("name");
													if(cityNameEquals(name, countyName)){
														areaProvinceCity.put("areaId", areaId);
														areaProvinceCity.put("provinceId", provinceId);
														areaProvinceCity.put("cityId", cityId);
														areaProvinceCity.put("countyId", countyId);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return areaProvinceCity;
	}
	
	/**
	 * 根据名称获取地区、省份、城市编号
	 * @param name
	 * @return
	 */
	public static Map<String, String> getAreaProvinceCityidByNames(String name){
		Map<String, String> areaProvinceCity = new HashMap<String, String>();
		
		// 如果地区省份城市列表不为空
		if(AREA_PROVINCE_CITY_LIST!=null && AREA_PROVINCE_CITY_LIST.size()>0){
			// 循环地区列表
			for(int i=0; i<AREA_PROVINCE_CITY_LIST.size(); i++){
				Map areaProvinceCityMap = (Map)AREA_PROVINCE_CITY_LIST.get(i);
				String areaId = areaProvinceCityMap.get("id")+"";
				String areaName = areaProvinceCityMap.get("name")+"";
				if(cityNameEquals(name, areaName)){
					areaProvinceCity.put("provinceId", areaId);
					
					return areaProvinceCity;
				}else{
					// 获取当前地区的省份列表
					List secondsList = (List)(areaProvinceCityMap.get("second")!=null?areaProvinceCityMap.get("second"):new ArrayList());
					if(null!=secondsList && secondsList.size()>0){
						// 如果省份列表不为空，则遍历所有省份，如果和参数指定的省份编号相同，则返回此省份与地区的编号，如果不相同则继续遍历城市信息
						for(int j=0; j<secondsList.size(); j++){
							Map provinceMap = (Map)secondsList.get(j);
							String provinceId = (String)provinceMap.get("id");
							String provinceName = (String)provinceMap.get("name");
							if( cityNameEquals(name, provinceName)){
								areaProvinceCity.put("provinceId", areaId);
								areaProvinceCity.put("cityId", provinceId);

								return areaProvinceCity;
							}else{
								List thirdList = (List)provinceMap.get("third");
								// 如果城市列表不为空，则循环城市
								if(null!=thirdList && thirdList.size()>0){
									for(int z=0; z<thirdList.size(); z++){
										Map cityMap = (Map)thirdList.get(z);
										String cityId = (String)cityMap.get("id");
										String cityName = (String)cityMap.get("name");
										if(cityNameEquals(name, cityName)){
											areaProvinceCity.put("provinceId", areaId);
											areaProvinceCity.put("cityId", provinceId);
											areaProvinceCity.put("countyId", cityId);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return areaProvinceCity;
	}
	
	// 比较城市名称
	public static boolean cityNameEquals(String source, String target){
		boolean isSuc = false;
		
		if(null!=source && source.length()>0 && target!=null){
			if(source.substring(source.length()-1).equals("%")){
				if(target.length()>=source.length()-1){
					if(target.substring(0, source.length()-1).equals(source.substring(0, source.length()-1)))
						isSuc =  true;
				}
			}else{
				if(source.equals(target))
					isSuc = true;
			}
		}
		
		return isSuc;
	}
	
	/**
	 * 根据区域编号获取子区域编号
	 * @param districtId
	 * @param type 1 连同上一层数据 0无上一层数据
	 * @return
	 * @author 李来源
	 * Jul 1, 20133:42:33 PM
	 */
	public static List getSubDistrictList(String districtId,int type,int state){
		List districtList = new ArrayList();
		Map<String,Object> parentMap = new HashMap<String,Object>();
		List list = new ArrayList();
		Map<String, String> districtMap = DictionaryUtils.getAreaProvinceidByCityid(districtId);
		String disAreaId = districtMap.get("areaId");
		String disProvinceId = districtMap.get("provinceId");
		String disCityId = districtMap.get("cityId");
		String disCountyId = districtMap.get("countyId");
		String disStreetId = districtMap.get("streetId");
		//获取上一级数据
		if(type==1){
			parentMap = getParentMapById(districtId);
		}
		List temp_list = (List)AREA_PROVINCE_CITY_LIST.clone();
		if(temp_list!=null && temp_list.size()>0){
			// 循环地区列表
			for(int i=0; i<temp_list.size(); i++){
				Map areaProvinceCityMap = (Map)temp_list.get(i);
				String areaId = areaProvinceCityMap.get("id")+"";
				//如果地区为空返回所有地区
				if(null==disAreaId || "".equals(disAreaId)){
					Map temp_areaProvinceCityMap = new HashMap();
					temp_areaProvinceCityMap.put("id", areaProvinceCityMap.get("id"));
					temp_areaProvinceCityMap.put("name", areaProvinceCityMap.get("name"));
					districtList.add(temp_areaProvinceCityMap);
					//如果省份不为空
				}else if((null==disProvinceId || "".equals(disProvinceId))){
					//返回此地区下所有省份
					if(areaId.equals(disAreaId)){
						List temp_districtList = (List)areaProvinceCityMap.get("second");
						if(null!=temp_districtList && temp_districtList.size()>0){
							for(int mm = 0; mm<temp_districtList.size(); mm++){
								Map provinceMap = (Map)temp_districtList.get(mm);
								Map temp_provinceMap = new HashMap();
								temp_provinceMap.put("id", provinceMap.get("id"));
								temp_provinceMap.put("name", provinceMap.get("name"));
								districtList.add(temp_provinceMap);
							}
						}
						if(type==1){
							parentMap.put("id","");
							parentMap.put("name","返回上一层");
						}
						districtList.add(0,parentMap);
						return districtList;
					}
					//得到所有省份
				}else{
					// 获取当前地区的省份列表
					List secondsList = (List)(areaProvinceCityMap.get("second")!=null?areaProvinceCityMap.get("second"):new ArrayList());
					if(null!=secondsList && secondsList.size()>0){
						// 如果省份列表不为空，则遍历所有省份，如果和参数指定的省份编号相同，则返回此省份与地区的编号，如果不相同则继续遍历城市信息
						for(int j=0; j<secondsList.size(); j++){
							Map provinceMap = (Map)secondsList.get(j);
							String provinceId = (String)provinceMap.get("id");
							String provinceName = (String)provinceMap.get("name");
							Map temp_cityParMap = new HashMap();
							temp_cityParMap.put("id",provinceId);
							temp_cityParMap.put("name",provinceName);
							//如果市编号为空，得到此省份下所有市
							if((null==disCityId || "".equals(disCityId))){
								if( provinceId.equals(disProvinceId)){
									List temp_districtList = (List)provinceMap.get("third");
									if(null!=temp_districtList && temp_districtList.size()>0){
										for(int z=0; z<temp_districtList.size(); z++){
											Map cityMap = (Map)temp_districtList.get(z);
											Map temp_cityMap = new HashMap();
											temp_cityMap.put("id", cityMap.get("id"));
											temp_cityMap.put("name", cityMap.get("name"));
											districtList.add(temp_cityMap);
										}
									}
									if(type==1)
										districtList.add(0,parentMap);
									return districtList;
								}
								//市编号不为空
							}else{
								//得到所有市
								List thirdList = (List)provinceMap.get("third");
								// 如果城市列表不为空，则循环城市
								if(null!=thirdList && thirdList.size()>0){
									for(int z=0; z<thirdList.size(); z++){
										Map cityMap = (Map)thirdList.get(z);
										String cityId = (String)cityMap.get("id");
										//如果县编号为空
										if((null==disCountyId || "".equals(disCountyId))){
											if(cityId.equals(districtId)){
												List temp_districtList = (List)cityMap.get("fourth");
												if(null!=temp_districtList && temp_districtList.size()>0){
													for(int m=0; m<temp_districtList.size(); m++){
														Map countyMap = (Map)temp_districtList.get(m);
														Map temp_countyMap = new HashMap();
														temp_countyMap.put("id", countyMap.get("id"));
														temp_countyMap.put("name", countyMap.get("name"));
														districtList.add(temp_countyMap);
													}
												}
												if(type==1)
													districtList.add(0,parentMap);
												return districtList;
											}
										}else{
										//得到所有县
											List fourthList = (List)cityMap.get("fourth");
											if(fourthList.size()>0&&null!=fourthList){
												for(int n=0; n<fourthList.size(); n++){
													Map countyMap = (Map)fourthList.get(n);
													String countyId = (String)countyMap.get("id");
													//得到此县下面的所有街道
													if((null==disStreetId||"".equals(disStreetId))&& countyId.equals(disCountyId)){
														List fifthList = (List)countyMap.get("fifth");
														if(fifthList!=null&&fifthList.size()>0){
															Map temp_streetMap = new HashMap();
															temp_streetMap.put("id", countyMap.get("id"));
															temp_streetMap.put("name", countyMap.get("name"));
															if(type==1)
																districtList.add(0,parentMap);
															districtList.addAll(fifthList);
															return districtList;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(state==0){
			if(type==1){
				parentMap.put("id","");
				parentMap.put("name","");
				districtList.add(0,parentMap);
			}
		}
		return districtList;
	}


	/**
	 * 根据id获取父类信息
	 * @param districtId
	 * @param parentMap
	 * @author 李来源
	 * Jul 1, 20134:19:35 PM
	 */
	public static Map<String,Object> getParentMapById(String districtId ) {
		List list = new ArrayList();
		Map<String, Object> parentMap = new HashMap<String,Object>();
		list = Tool.query("select parentId,name,areaid from system_province_city where ID = '"+districtId+"'");
		if(list.size()>0&&list!=null){
			Map map = (Map)list.get(0);
			String parentId = map.get("PARENTID")+"";
			
			if("FFFFFF".equals(parentId)||"FFFFFF"==parentId){
				parentId = map.get("AREAID")+"";
			}
			parentMap.put("id",parentId);
			parentMap.put("name","返回上一层");
		}
		return parentMap;
	}
	/**
	 * 根据城市编号，获取名称
	 * @param id
	 * @return
	 */
	public static String getNameBySiteid(String id){
		String name = "";
		// 如果地区省份城市列表不为空
		if(AREA_PROVINCE_CITY_LIST!=null && AREA_PROVINCE_CITY_LIST.size()>0){
			// 循环地区列表
			for(int i=0; i<AREA_PROVINCE_CITY_LIST.size(); i++){
				Map areaProvinceCityMap = (Map)AREA_PROVINCE_CITY_LIST.get(i);
				
				String areaId = areaProvinceCityMap.get("id")+"";
				if(areaId.equals(id)){
					name = areaProvinceCityMap.get("name")+"";
				}else{
					// 获取当前地区的省份列表
					List secondsList = (List)(areaProvinceCityMap.get("second")!=null?areaProvinceCityMap.get("second"):new ArrayList());
					if(null!=secondsList && secondsList.size()>0){
						// 如果省份列表不为空，则遍历所有省份，如果和参数指定的省份编号相同，则返回此省份与地区的编号，如果不相同则继续遍历城市信息
						for(int j=0; j<secondsList.size(); j++){
							Map provinceMap = (Map)secondsList.get(j);
							String provinceId = (String)provinceMap.get("id");
							if(provinceId.equals(id)){
								name = provinceMap.get("name")+"";
							}else{
								List thirdList = (List)provinceMap.get("third");
								// 如果城市列表不为空，则循环城市
								if(null!=thirdList && thirdList.size()>0){
									for(int z=0; z<thirdList.size(); z++){
										Map cityMap = (Map)thirdList.get(z);
										String cityId = (String)cityMap.get("id");
										if(cityId.equals(id)){
											name = cityMap.get("name")+"";
										}else{
											List fourthList = (List)(cityMap.get("fourth")==null?new ArrayList():cityMap.get("fourth"));
											if(null!=fourthList && fourthList.size()>0){
												for(int x=0; x<fourthList.size(); x++){
													Map countyMap = (Map)fourthList.get(x);
													String countyId = (String)countyMap.get("id");
													if(countyId.equals(id)){
														name = countyMap.get("name")+"";
													}else{
														List fifthList = (List)(countyMap.get("fifth")==null?new ArrayList():countyMap.get("fifth"));
														if(null!=fifthList && fifthList.size()>0){
															for(int f=0; f<fifthList.size(); f++){
																Map streetMap = (Map)fifthList.get(f);
																String streetId = (String)streetMap.get("id");
																if(streetId.equals(id)){
																	name = streetMap.get("name")+"";
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return name;
	}
	/**
	 * 根据区域编号获取省份编号的sql字符串
	 * @param id
	 * @return
	 */
	public static String getProvinceidByAreaid(String id){
		String ids = "";
		
		if(null==id || "".equals(id))
			return "";
		
		if(null!=AREA_PROVINCE_CITY_LIST && AREA_PROVINCE_CITY_LIST.size()>0){
			for(int i=0; i<AREA_PROVINCE_CITY_LIST.size() ; i++){
				Map<String, Object> areaMap = AREA_PROVINCE_CITY_LIST.get(i);
				String areaId = (String)areaMap.get("id");
				if(areaId.equals(id)){
					List provinceList = (List)areaMap.get("second");
					if(null!=provinceList && provinceList.size()>0){
						String split = "";
						for(int j=0; j<provinceList.size(); j++){
							Map provinceMap = (Map)provinceList.get(j);
							ids += split + "'"+provinceMap.get("id")+"'";
							split = ",";
						}
					}
				}
			}
		}
		
		return ids;
	}
	
	/**
	 * 七牛
	 */
	public interface QiNiu {
		String ACCESS_KEY = "NEg0gY6JM0N-UUeGrfEz3wvX6NJFRiAXwQ-SHdjS";
		String SECRET_KEY = "00pJ0KbemdYzpfpETC-c8wSjYgWUPZbiOY6_Gzo8";
		String BUCKET = "app-pic";
		long EXPIRES = 3600;
	}
}
