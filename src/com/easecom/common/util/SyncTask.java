package com.easecom.common.util;
import java.text.SimpleDateFormat;
import java.util.*;

import com.business.ProductOrder.ProductOrderMgr;
import org.apache.log4j.Logger;

public class SyncTask extends TimerTask {

	public static Logger logger = Logger.getLogger(SyncTask.class);
	private static boolean isRunning = false;
	ProductOrderMgr mgr = new ProductOrderMgr();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void run() {
		if (!isRunning){
				System.out.println("14天自动确认收货定时任务执行开始...");
				isRunning = true;
			// update product_order set product_order.order_state ='not_comment' where product_order.order_state ='not_received' and product_order.auto_reward_time <'2016-12-10'
			//因为需要添加日志采用循环修改
				try {
					 //这里执行定时任务，某个mgr的某个方法。
					List list = mgr.SQLQuery("SELECT order_code,consignee,telephone," +
							" auto_reward_time FROM product_order where order_state ='not_received'and auto_reward_time <='"+sdf.format(new Date())+"' and auto_reward_time is NOT NULL");
					for(int i=0;i<list.size();i++){
						Map map = (Map) list.get(i);
						String orderCode = "";
						String consignee = "";
						String telephone = "";
						try{
							orderCode = map.get("order_code").toString();
							consignee = map.get("consignee").toString();
							telephone = map.get("telephone").toString();
							int result = mgr.SQLExecute("update product_order set order_state='not_comment' where order_code = '"+orderCode+"' ");
							Tool.AddLog("定时自动收货", orderCode,
										"修改订单状态:订单号："+orderCode+"；收货人："+consignee+";联系电话:"+telephone+"; 的订单，由状态《待收货》修改为《待评论》", "2", "127.0.0.1");
						}catch (Exception ex){
							logger.error(ex.getMessage());
						}
					}
					System.out.println("定时任务执行结束...");

				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					isRunning = false;
				}
		}
	}
}
