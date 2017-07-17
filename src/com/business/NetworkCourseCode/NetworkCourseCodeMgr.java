package com.business.NetworkCourseCode;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.business.NetworkVideo.NetworkVideo;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class NetworkCourseCodeMgr extends AbstractHibernateDAO{

	private static LogUtils logger = LogUtils.getLogger(NetworkCourseCode.class);
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds,Collection queryCond, int currentPageInt,
			int itemsInPage, String action, int jumpPage,String type) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			String types="";
			if(type==null || type.equals("") || type.equals("0")){
				type="and networkCourseCode.user_id is null";
				types="and NetworkCourseCode.userId is null";
			}else if(type.equals("1")){
				type="and networkCourseCode.user_id is not null";
				types="and NetworkCourseCode.userId is not null";
			}else if(type.equals("all")){
				type="";
				types="";
			}
			// 查询对象
			String sql = "select networkCourseCode.*,networkCourse.nc_name  ncName,networkCourse.nc_level  ncLevel,networkCourse.nc_level_type  ncLevelType,"
					+ "networkCourse.nc_type  ncType from network_course_code as networkCourseCode , network_course as networkCourse where 1=1"
					+ QueryHelper.toAndQuery(queryConds) + " "+type+" and networkCourseCode.nv_id = networkCourse.id order by networkCourseCode.create_time desc  ";
			Query qCount = ses .createQuery("select count(NetworkCourseCode.id) from NetworkCourseCode as NetworkCourseCode,NetworkCourse as NetworkCourse where 1=1 and NetworkCourseCode.nvId=NetworkCourse.id "+ QueryHelper.toAndQuery(queryCond)+" "+types+"");
			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
					: "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = ses.createSQLQuery(sql).addEntity(NetworkCourseCode.class);
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("查询数据列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
}
