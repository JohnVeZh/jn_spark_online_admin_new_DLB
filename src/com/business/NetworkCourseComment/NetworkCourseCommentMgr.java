package com.business.NetworkCourseComment;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.Collection;
import java.util.List;

public class NetworkCourseCommentMgr extends AbstractHibernateDAO{

	private static LogUtils logger = LogUtils.getLogger(NetworkCourseComment.class);
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException, Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			String sql = "SELECT ncc.id, ncc.nc_id AS ncId, ncc.user_id AS userId, CAST(ncc.content AS CHAR(10000) CHARACTER SET utf8) as content, ncc.vote_up AS voteUp, ncc.vote_down AS voteDown, ncc.is_hide AS isHide, ncc.create_time AS createTime, nc.nc_name AS ncName, us.mobile AS mobile FROM network_course_comment AS ncc LEFT JOIN network_course AS nc on nc.id = ncc.nc_id LEFT JOIN users  AS us ON ncc.user_id = us.id WHERE ncc.is_hide = 0" + QueryHelper.toAndQuery(queryConds) + " order by ncc.create_time desc";

			String sqlCount = "SELECT count(1) FROM network_course_comment AS ncc LEFT JOIN network_course AS nc on nc.id = ncc.nc_id LEFT JOIN users  AS us ON ncc.user_id = us.id WHERE ncc.is_hide = 0" + QueryHelper.toAndQuery(queryConds) + " order by ncc.create_time desc";
			System.err.println(sql);
			Query qCount = ses.createSQLQuery(sqlCount);
			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString() : "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = ses.createSQLQuery(sql);
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
