package com.business.Dlb.PeriodPaperUserAnswer;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.business.Dlb.PeriodPaperQrcode.PeriodPaperQrcode;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

public class PeriodPaperUserAnswerActionMgr extends AbstractHibernateDAO{
	
	private static LogUtils logger = LogUtils.getLogger(PeriodPaperUserAnswer.class);

	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage, String state) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			String state_sql="";
			if("1".equals(state)){
				state_sql="AND a.reply_user_id is NOT NULL";
			}
			if("2".equals(state)){
				state_sql="AND a.reply_user_id is NULL";
			}
			// 查询对象
			String hq="select a.*,b.mobile mobile,c.username replyName from dlb_period_paper_user_answer a LEFT JOIN users b ON a.user_id=b.id  LEFT JOIN users c ON c.id= a.reply_user_id WHERE 1=1 "+state_sql+" AND a.is_teacher_evaluate=1 AND  a.question_type in( 3,4)"+ QueryHelper.toAndQuery(queryConds)+"  ORDER BY a.create_date DESC  ";
			System.err.println(hq);
			Query qCount = ses .createSQLQuery("select count(1) from dlb_period_paper_user_answer a LEFT JOIN users b ON a.user_id=b.id WHERE 1=1 "+state_sql+" and a.is_teacher_evaluate=1 "+ QueryHelper.toAndQuery(queryConds)+"  ORDER BY a.create_date DESC  ");

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
			Query query = ses.createSQLQuery(hq.toString()).addEntity(PeriodPaperUserAnswer.class);
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
	
	/**
	 * 用户提交答案详情
	 * @param sql
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public static List findObjectListBySql (String sql) throws SystemException, Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 保存对象
			Query query = ses.createSQLQuery(sql.toString()).addEntity(PeriodPaperUserAnswer.class);;
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
	/**
	 * 老师批改规则
	 * @param sql
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public static List findEvaluateRuleListBySql (String sql) throws SystemException, Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 保存对象
			Query query = ses.createSQLQuery(sql.toString()).addEntity(PeriodPaperEvaluateRuleDetail.class);;
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
