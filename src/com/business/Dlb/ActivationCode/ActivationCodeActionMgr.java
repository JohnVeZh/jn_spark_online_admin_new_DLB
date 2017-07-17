package com.business.Dlb.ActivationCode;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class ActivationCodeActionMgr extends AbstractHibernateDAO{
	
	private static LogUtils logger = LogUtils.getLogger(ActivationCode.class);
	
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq="SELECT a.*, c.username, c.mobile, b.pre_translate_total preTranslateTotal,"
					+"b.pre_translate_use preTranslateUse, b.pre_write_total preWriteTotal, b.pre_write_use preWriteUse, b.mid_translate_total midTranslateTotal,"
					+"b.mid_translate_use midTranslateUse, b.mid_write_total midWriteTotal, b.mid_write_use midWriteUse, b.post_translate_total postTranslateTotal,"
					+"b.post_translate_use postTranslateUse, b.post_write_total postWriteTotal, b.post_write_use postWriteUse "
					+"FROM dlb_activation_code a LEFT JOIN dlb_user_objective_submit_count b ON b.code = a.code "
					+"LEFT JOIN users c ON b.user_id = c.id WHERE 1 = 1 "+ QueryHelper.toAndQuery(queryConds)+" ORDER BY a.activate_sort DESC";
			
			Query qCount = ses .createSQLQuery("SELECT COUNT(1) FROM dlb_activation_code a LEFT JOIN dlb_user_objective_submit_count b ON b.code = a.code "
					+"LEFT JOIN users c ON b.user_id = c.id WHERE 1 = 1 "+ QueryHelper.toAndQuery(queryConds));

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
			Query query = ses.createSQLQuery(hq.toString()).addEntity(ActivationCode.class);
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
	
}
