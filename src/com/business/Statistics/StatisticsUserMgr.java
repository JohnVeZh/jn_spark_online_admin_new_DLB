package com.business.Statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.Tool;
import com.easecom.system.exception.SystemException;

public class StatisticsUserMgr extends AbstractHibernateDAO{
	private static LogUtils logger = LogUtils.getLogger(StatisticsUserMgr.class);
	
	
	//会员交易总额
	@SuppressWarnings("unchecked")
	public List list(String id) throws SystemException,
			Exception {
		try {
			List list=new ArrayList();
			String month = "";
			String days=DateUtils.getFirstDay(month);
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			Date dates=sf.parse(days);
			int day=DateUtils.getMonthDay(month);
			String str="";
			if(!"".equals(id)){
				str=" and ID in ("+id+")";
			}
			for(int i=0;i<day;i++){
				Map map=new HashMap();
				String time=DateUtils.getNextDate(dates,i);
				map.put("KEY", time);
				map.put("VALUE",Tool.getIntValue("SELECT sum(price) FROM product_order WHERE  order_state='complete_order' or order_state='not_paid' AND user_del=0  AND admin_del=0  and createtime>='"+time+" 00:00:00' and createtime<='"+time+" 23:59:59'  "));
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("users列表出错！", e);
		}
	}
	
	
		//会员交易总额
		@SuppressWarnings("unchecked")
		public List getuserEr(String startTime,String endTime) throws SystemException,
				Exception {
			try {
				List list=new ArrayList();
				if(!"".equals(startTime)&&null!=startTime&&!"".equals(endTime)&&null!=endTime){
					int day=this.daysBetween(startTime,endTime);
					
//					String str="";
//					if(!"".equals(id)){
//						str=" and ID in ("+id+")";
//					}
					for(int i=0;i<day;i++){
						Map map=new HashMap();
						String time=DateUtils.getNextDate(startTime,i);
						map.put("KEY", time);
						map.put("VALUE",Tool.getIntValue("select count(id) from users where  user_type='0' and createtime>='"+time+" 00:00:00' and createtime<='"+time+" 23:59:59' "));
						list.add(map);
					}
				}else{
					String month = "";
					String days=DateUtils.getFirstDay(month);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					Date dates=sf.parse(days);
					int day=DateUtils.getMonthDay(month);
					
//					String str="";
//					if(!"".equals(id)){
//						str=" and ID in ("+id+")";
//					}
					for(int i=0;i<day;i++){
						Map map=new HashMap();
						String time=DateUtils.getNextDate(dates,i);
						map.put("KEY", time);
						map.put("VALUE",Tool.getIntValue("select count(id) from users where  user_type='0' and createtime>='"+time+" 00:00:00' and createtime<='"+time+" 23:59:59' "));
						list.add(map);
					}
				}
				return list;
			} catch (Exception e) {
				logger.debug(e);
				throw new SystemException("users列表出错！", e);
			}
		}
		
		
				//
				@SuppressWarnings("unchecked")
				public List getuserbook(String startTime,String endTime) throws SystemException,
						Exception {
					try {
						List list=new ArrayList();
						if(!"".equals(startTime)&&null!=startTime&&!"".equals(endTime)&&null!=endTime){
							int day=this.daysBetween(startTime,endTime);
							
//							String str="";
//							if(!"".equals(id)){
//								str=" and ID in ("+id+")";
//							}
							for(int i=0;i<day;i++){
								Map map=new HashMap();
								String time=DateUtils.getNextDate(startTime,i);
								map.put("KEY", time);
								map.put("VALUE",Tool.getIntValue("select count(id) from book_activation_code where   is_use='1'  and is_del='0' and use_time>='"+time+" 00:00:00' and use_time<='"+time+" 23:59:59' "));
								list.add(map);
							}
						}else{
							String month = "";
							String days=DateUtils.getFirstDay(month);
							SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
							Date dates=sf.parse(days);
							int day=DateUtils.getMonthDay(month);
//							String str="";
//							if(!"".equals(id)){
//								str=" and ID in ("+id+")";
//							}
							for(int i=0;i<day;i++){
								Map map=new HashMap();
								String time=DateUtils.getNextDate(dates,i);
								map.put("KEY", time);
								map.put("VALUE",Tool.getIntValue("select count(id) from book_activation_code where   is_use='1'  and is_del='0' and use_time>='"+time+" 00:00:00' and use_time<='"+time+" 23:59:59' "));
								list.add(map);
							}
						}
						return list;
					} catch (Exception e) {
						logger.debug(e);
						throw new SystemException("users列表出错！", e);
					}
				}
				
				@SuppressWarnings("unchecked")
				public List getInterfaceAnalyselist() throws SystemException,
						Exception {
					try {
						List list=new ArrayList();
						list=Tool.query("SELECT interface_name_cn name,click num FROM interface_log  ORDER BY click desc");
						return list;
					} catch (Exception e) {
						logger.debug(e);
						throw new SystemException("partner列表出错！", e);
					}
				}
				/**  
			     * 计算两个日期之间相差的天数  
			     * @param smdate 较小的时间 
			     * @param bdate  较大的时间 
			     * @return 相差天数 
			     * @throws ParseException  
			     */    
			    public static int daysBetween(String startTime,String endTime) throws Exception    
			    {   
			        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			        Date smdate=sdf.parse(startTime);  
			        Date bdate=sdf.parse(endTime);  
			        Calendar cal = Calendar.getInstance();    
			        cal.setTime(smdate);    
			        long time1 = cal.getTimeInMillis();                 
			        cal.setTime(bdate);    
			        long time2 = cal.getTimeInMillis();         
			        long between_days=(time2-time1)/(1000*3600*24);  
			            
			       return Integer.parseInt(String.valueOf(between_days));           
			    } 
}
