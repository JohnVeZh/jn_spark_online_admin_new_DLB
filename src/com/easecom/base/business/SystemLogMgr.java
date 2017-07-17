package com.easecom.base.business;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.DateTimeUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;

public class SystemLogMgr extends AbstractHibernateDAO {
	
	/**
	 * 根据查询条件，获得系统日志列表
	 * @param queryConds		查询条件集合
	 * @param currentPageInt	当前页数
	 * @param itemsInPage		每页显示数目
	 * @param action			操作类型
	 * @param jumpPage			跳页的页数
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public ListContainer list(Collection queryConds , int currentPageInt , int itemsInPage
								, String action , int jumpPage)
								throws Exception , Exception{
		ListContainer lc = null;
		
		// 查询指定条件的所有系统日志信息HQL
		String itemsHQL = "from SystemLog as SystemLog where 1=1"
							+ QueryHelper.toAndQuery(queryConds)
							+ " order by SystemLog.id desc";
		// 查询指定条件的所有系统日志数量HQL
		String totalCountHQL = "select count(id) from SystemLog as SystemLog where 1=1" +
								QueryHelper.toAndQuery(queryConds);
		
//		System.out.println("Log:"+itemsHQL);
		try {
			// 获取session对象
			Session session = HibernateSessionFactory.openSession();
			// 按指定条件查询所有系统日志数量
			List countList = session.createQuery(totalCountHQL).list();
			int totalItems = 0;
			if(countList.iterator().hasNext())
			{
				totalItems = (Integer)countList.iterator().next();
			}
			// 创建ListContainer对象
			lc = new ListContainer();
			// 设置每页显示记录数
			lc.setItemsInPage(itemsInPage);
			// 设置总记录数
			lc.setTotalItem(totalItems);
			// 设置当前页
			lc.setCurrentPage(currentPageInt);
			// 设置翻页行为
			lc.setPageAction(PageAction.fromString(action));
			// 设置跳页的页数
			lc.setJumpIndex(jumpPage);
			
			// 获取指定条件指定记录数量的系统日志信息
			List itemsList = session.createQuery(itemsHQL)
											.setFirstResult(lc.calculateNextPageIndex())
											.setMaxResults(itemsInPage)
											.list();
			// 将系统日志信息添加到lc中
			lc.setList(itemsList);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return lc;
	}
	
	/**
	 * 删除周、月、全部的系统日志
	 * @param action 指定是删除周日志、月日志、全部日志
	 * @return 成功返回true，失败false
	 */
	public boolean deleteWeekMonthALL(String action)
	{
		// 删除结果（成功true、失败false）
		boolean isDelete = false;
		// 删除SQL语句
		String deleteSQL = "delete from system_log ";
		// 如果删除标志是deleteWeek，代表要删除的日志为本周的全部日志
//		if("deleteWeek".equals(action)){
//			// 获取本周开始的日期时间
//			String weekStartDate = DateTimeUtils.getCurrentWeekStartDate();
//			// 获取本周结束的日期时间
//			String weekEndDate = DateTimeUtils.getCurrentWeekEndDate();
//			
//			// 组成删除本周日志的SQL语句
//			deleteSQL += " where (s.opetime >= '" + weekStartDate + "' and s.opetime <= '" + weekEndDate + "')";
//		}else if("deleteMonth".equals(action)){ 	// 如果删除标志位deleteMonth，则代表删除的日志为本月的全部日志
//			// 获取本月开始的日期时间
//			String monthStartDate = DateTimeUtils.getCurrentMonthStartDate();
//			// 获取本月结束的日期时间
//			String monthEndDate = DateTimeUtils.getCurrentMonthEndDate();
//
//			// 组成删除本月日志的SQL语句
//			deleteSQL += " where (s.opetime >= '"+ monthStartDate + "' and s.opetime <= '" + monthEndDate + "')";
//		}
		if("deleteWeek".equals(action)){
			// 获取本周开始的日期时间
			String weekStartDate = DateTimeUtils.getCurrentWeekStartDate();
			// 获取本周结束的日期时间
			String weekEndDate = DateTimeUtils.getCurrentWeekEndDate();
			
			// 组成删除本周日志的SQL语句
		//	deleteSQL += " where (to_date(s.opetime, 'yyyy-mm-dd hh24:mi:ss') >= to_date('" +weekStartDate + "', 'yyyy-mm-dd hh24:mi:ss') and to_date(s.opetime, 'yyyy-mm-dd hh24:mi:ss') <= to_date('" +weekEndDate + "', 'yyyy-mm-dd hh24:mi:ss'))";
			deleteSQL +=" where  unix_timestamp(opetime) between unix_timestamp('"+weekStartDate+"') and unix_timestamp('"+weekEndDate+"')";
		}else if("deleteMonth".equals(action)){ 	// 如果删除标志位deleteMonth，则代表删除的日志为本月的全部日志
			// 获取本月开始的日期时间
			String monthStartDate = DateTimeUtils.getCurrentMonthStartDate();
			// 获取本月结束的日期时间
			String monthEndDate = DateTimeUtils.getCurrentMonthEndDate();

			// 组成删除本月日志的SQL语句
			//deleteSQL += " where (to_date(s.opetime, 'yyyy-mm-dd hh24:mi:ss') >=   to_date('" +monthStartDate + "', 'yyyy-mm-dd hh24:mi:ss') and to_date(s.opetime, 'yyyy-mm-dd hh24:mi:ss') <= to_date('" +monthEndDate + "', 'yyyy-mm-dd hh24:mi:ss'))";
			deleteSQL +=" where  unix_timestamp(opetime) between unix_timestamp('"+monthStartDate+"') and unix_timestamp('"+monthEndDate+"')";
		}
		System.out.println(deleteSQL);
		try {
			// 通过Tool工具类执行SQL语句
			isDelete = Tool.execute(deleteSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isDelete;
	}
}
