package com.easecom.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 字典管理
 * @author Administrator
 *
 */
public class DictionaryManage {
	
	
	/***************各种返回值****************************/
	
	public static final String CODE501 = "501";//客户端没有传数据或者是所传数据为空
	public static final String CODE502 = "502";//登录密码不正确
	public static final String CODE505 = "505";//支付密码密码不正确
	
	public static final String CODE000 = "000";//操作成功
	public static final String CODE001 = "001";//操作失败
	public static final String CODE002 = "002";//无数据
	public static final String CODE003 = "003";//异常
	public static final String CODE004 = "004";//请求参数不正确
	public static final String CODE005 = "005";//用户余额不足
	public static final String CODE006 = "006";//使用不存在的支付方式
	//
	public static final String CODE202 = "202";//订单发票信息不全
	
	//
	public static final String CODE503 = "503";//用户不存在
	public static final String CODE203 = "203";//用户已被禁用
	public static final String CODE204 = "204";//用户收货地址不正确
	public static final String CODE205 = "205";//社区不存在
	public static final String CODE206 = "206";//商品不存在或已被删除
	public static final String CODE207 = "207";//商品已下架
	
	public static final String CODE210 = "210";// 订单中部分商品已无货，请返回购物车重新选择
	public static final String CODE211 = "211";// 订单中部分商品已下架或删除，请返回购物车重新选择
	
	public static final String CODE601 = "601";// 所选小区商品已无货
	public static final String CODE602 = "602";// 该小区配送员已下班，所订商品配送员上班后统一发货
	
	public static final String CODE208 = "208";//支付订单不存在
	public static final String CODE209 = "209";//支付订单已被处理
	public static final String CODE300 = "300";//订单未支付
	public static final String CODE301 = "301";//此订单状态不支持处理
	public static final String CODE302 = "302";//订单已关闭
	public static final String CODE303 = "303";//订单被取消
	public static final String CODE304 = "304";//订单已确认收货
	
	public static final String CODE701 = "701";// 退货商品数量有误
	public static final String CODE702 = "702";// 退货商品订单不存在
	public static final String CODE703 = "703";// 商品订单完成时间过长，请联系管理员退货
	
	
	
	public static int INFOS = 0;
	
	//支付失败
	public static final String FAILURE = "FAILURE";
	public static final String FAILURE_NAME = "支付失败";
	
	//未支付
	public static final String UNPAY = "UNPAY";
	public static final String UNPAY_NAME = "未付款";
	//已支付
	public static final String PAY = "PAY";
	public static final String PAY_NAME = "已付款";
	//已接单/待出库
	public static final String UNSEND = "UNSEND";
	public static final String UNSEND_NAME = "待出库";
	
	//已出库
	public static final String SEND = "SEND";
	public static final String SEND_NAME = "已出库";
	
	//已送达
	public static final String RECEIVE = "RECEIVE";
	public static final String RECEIVE_NAME = "已送达";
	//已完成
	public static final String SUCCEED = "SUCCEED";
	public static final String SUCCEED_NAME = "已完成";
	//取消申请中..
	public static final String CANALE = "CANALE";
	public static final String CANALE_NAME = "已取消";
	
	//已取消
	public static final String CANCELED = "CANCELED";
	public static final String CANCELED_NAME = "已取消";
	
	
	//关闭订单
	public static final String CLOSE = "CLOSE";	
	public static final String CLOSE_NAME = "已关闭";
	
	//未送达
	public static final String NO_SENT = "NO_SENT";	
	public static final String NO_SENT_NAME = "未送达";
	
	//已送达
	public static final String SENT = "SENT";	
	public static final String SENT_NAME = "已送达";
	
	// 结束送货
	public static final String FINISHED_DELIVERY = "FINISHED_DELIVERY";
	public static final String FINISHED_DELIVERY_NAME = "已结束送货";
	
	//验货完成
	public static final String INSPECTION_SUC = "INSPECTION_SUC";	
	public static final String INSPECTION_SUC_NAME = "验货成功";
	
	//验货失败
	public static final String INSPECTION_FAILED = "INSPECTION_FAILED";	
	public static final String INSPECTION_FAILED_NAME = "验货失败";
	
	//入库
	public static final String PUT_IN_STORAGE = "PUT_IN_STORAGE";	
	public static final String PUT_IN_STORAGE_NAME = "入库";
	
	//线下支付
	public static final String UNLINE  = "UNLINE";
	public static final String UNLINE_NAME  = "线下支付";
	//线上支付
	public static final String ONLINE  = "ONLINE";	
	public static final String ONLINE_NAME  = "线上支付";	
	//余额支付
	public static final String MONEY  = "MONEY";	
	public static final String MONEY_NAME  = "余额支付";
	
	
	/**已退货*/
	public static final String RETURNED  = "RETURNED";	
	public static final String RETURNED_NAME  = "已退货";
	
	
	/***************各种返回值****************************/
	
	
	/** 发票邮寄费用 */
	public static final double BILL_POST_MONEY = 10.00;
	
	
	public static String getPayState(String code){
		if (UNPAY.equals(code)) {
			return UNPAY_NAME;
		} else if (PAY.equals(code)) {
			return PAY_NAME;
		} else if (UNSEND.equals(code)) {
			return UNSEND_NAME;
		} else if (SEND.equals(code)) {
			return SEND_NAME;
		} else if (RECEIVE.equals(code)) {
			return RECEIVE_NAME;
		}else if (CLOSE.equals(code)) {
			return CLOSE_NAME;
		}else if (CANALE.equals(code)) {
			return CANALE_NAME;
		}else if (SUCCEED.equals(code)) {
			return SUCCEED_NAME;
		}else if(CANCELED.equals(code)){
			return CANCELED_NAME;
		}else if(FINISHED_DELIVERY.equals(code)){
			return FINISHED_DELIVERY_NAME;
		}else if(RETURNED.equals(code)){
			return RETURNED_NAME;
		}
		return "";
	}
	
	/**
	  * getReturnOrderState 方法
	  * <p>方法说明:</p>
	  * @param code
	  * @return
	  * @return String
	  * @author Lly
	  * @date 2015-1-28 下午3:16:25
	 */
	public static String getReturnOrderState(String code){
		if(INSPECTION_SUC.equals(code)){
			return "处理完成";
		}else if(PUT_IN_STORAGE.equals(code)){
			return "处理完成";
		}else{
			return "处理中";
		}
	}
	
	
	/**
	 * 公告类型：广播类型
	 */
	public static String BROADCAST = "1";
	/**
	 * 公告类型：点对点类型
	 */
	public static String POINTTOPOINT = "2";
	/**
	 * 获取公告类型字符串
	 */
	public static String getNoticeType(String id){
		if(DictionaryManage.BROADCAST.equals(id)){
			return "广播类型";
		}else{
			return "点对点类型";
		}
	}
	
	/**
	 * 用于保存公告访问记录时用到：公告类型 1
	 */
	public static String NOTICE="1";
	/**
	 * 用于保存资讯访问记录时用到：资讯类型 2
	 */
	public static String INFORMATION="2";
	
	
	
	/**
	 * 操作类型字典
	 */
	public static Map<Integer , String> OPETYPE = new HashMap<Integer , String>();
	
	

	/** 推送操作类型--key */
	public static final String PUSH_OPERATE_TYPE_KEY = "OPERATE_TYPE";
	/** 推送操作类型--配送端有新订单 */
	public static final Map<String, String> PUSH_OPERATE_TYPE_DISPATCH_NEW_ORDER = new HashMap<String, String>();
	/** 推送操作类型--客户端有新的已送达订单  **/
	public static final Map<String, String> PUSH_OPERATE_TYPE_NEW_SENT_GOODS = new HashMap<String, String>();
	static{
		OPETYPE.put(0, "系统日志");
		OPETYPE.put(1, "操作日志");
		OPETYPE.put(2, "自动收货日志");
		
		PUSH_OPERATE_TYPE_DISPATCH_NEW_ORDER.put(PUSH_OPERATE_TYPE_KEY, "DISPATCH_NEW_ORDER");
		PUSH_OPERATE_TYPE_DISPATCH_NEW_ORDER.put("PUSH_TITLE", "爱跑腿");
		PUSH_OPERATE_TYPE_DISPATCH_NEW_ORDER.put("PUSH_DESC", "您有一个新订单");
		
		PUSH_OPERATE_TYPE_NEW_SENT_GOODS.put(PUSH_OPERATE_TYPE_KEY, "NEW_SENT_GOODS");
		PUSH_OPERATE_TYPE_NEW_SENT_GOODS.put("PUSH_TITLE", "爱跑腿");
		PUSH_OPERATE_TYPE_NEW_SENT_GOODS.put("PUSH_DESC", "您有一个已完成订单赶紧去看看吧.");
		
	}
	
	/**
	 * 通过操作类别数字获取操作类别字符串
	 * @param key
	 * @return
	 */
	public static String getOpeType(Integer key){
		return OPETYPE.get(key);
	}
	/**
	 * 通过操作类别字符串获取操作类别数字
	 * @param value
	 * @return
	 */
	public static Integer getOpeType(String value){
		Set<Integer> set = OPETYPE.keySet();
		for(Integer key : set){
			if(OPETYPE.get(key).equals(value)){
				return key;
			}
		}
		return null;
	}
	/**
	 * 获取所有的值
	 */
	public static Collection<String> getOpeTypeValues(){
		return OPETYPE.values();
	}
	public static Map<Integer, String> getOPETYPE() {
		return OPETYPE;
	}
}
