package com.business.Dlb.PeriodVideoQrcode;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.business.Dlb.PeriodPaperUserAnswer.PeriodPaperUserAnswer;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class PeriodVideoQrcodeActionMgr extends AbstractHibernateDAO{
	private static LogUtils logger = LogUtils.getLogger(PeriodVideoQrcode.class);
	
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq="SELECT a.*,b.username username FROM dlb_period_video_qrcode a LEFT JOIN users b ON a.create_by=b.id WHERE 1=1 "+ QueryHelper.toAndQuery(queryConds)+"  ORDER BY a.create_date DESC  ";
			System.err.println(hq);
			Query qCount = ses .createSQLQuery("SELECT count(1) FROM dlb_period_video_qrcode a LEFT JOIN users b ON a.create_by=b.id WHERE 1=1 "+ QueryHelper.toAndQuery(queryConds)+"  ORDER BY a.create_date DESC  ");

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
			Query query = ses.createSQLQuery(hq.toString()).addEntity(PeriodVideoQrcode.class);
			//Query query = ses.createSQLQuery(hq);
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
	public static List findObjectListBySql (String sql) throws SystemException, Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 保存对象
			Query query = ses.createSQLQuery(sql.toString()).addEntity(PeriodVideoQrcode.class);;
			return query.list();
			
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("获取对象出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
	}
}
