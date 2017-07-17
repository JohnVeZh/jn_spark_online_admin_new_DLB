package com.business.v2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.v2.special.TbSpecialSuggestion;
import com.business.v2.vo.TbSpecialSuggestionForm;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

@SuppressWarnings("all")
public class SpecialSuggestionMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SpecialSuggestionMgr.class);

	public ListContainer list(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from TbSpecialSuggestion where 1=1"+ QueryHelper.toAndQuery(queryConds)+ " order by section, trainingType, start";
			Query qCount = ses.createQuery("select count(id) from TbSpecialSuggestion where 1=1 " + QueryHelper.toAndQuery(queryConds));

			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString() : "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			ses = HibernateSessionFactory.openSession();
			Query query = ses.createQuery(hq.toString());
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			
			List<TbSpecialSuggestion> tmpList = query.list();
			lc.getList().addAll(tmpList);// 装填指定页的列表数据到列表容器
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
	
	public void delete(String[] ids) {
		Transaction tx = null;
		Statement stmt = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Connection conn = session.connection();
			stmt = conn.createStatement();
			for(String id : ids) {
				stmt.addBatch("update tb_special_suggestion set is_del='1' where id='" + id + "'");
			}
			stmt.executeBatch();
			tx.commit();
			session.flush();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	public void add(TbSpecialSuggestionForm model) throws SystemException {
		Transaction tx = null;

		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			
			TbSpecialSuggestion po = new TbSpecialSuggestion();
			po.setId(model.getId());
			po.setSection(model.getSection());
			po.setTrainingType(model.getTrainingType());
			po.setStart(model.getStart());
			po.setEnd(model.getEnd());
			po.setAnalysis(model.getAnalysis());
			po.setSuggestion(model.getSuggestion());
			po.setIsDel(model.getIsDel());
			po.setCreateTime(model.getCreateTime());
			
			ses.save(po);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(ex);
			}
			throw new SystemException("新增出错!请检查输入数据。", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}
	
	public TbSpecialSuggestionForm view(String id) throws SystemException {
		try {
			Session ses = HibernateSessionFactory.openSession();
			TbSpecialSuggestion tb = (TbSpecialSuggestion) ses.load(TbSpecialSuggestion.class, id);
			TbSpecialSuggestionForm form = new TbSpecialSuggestionForm();
			form.setId(tb.getId());
			form.setSection(tb.getSection());
			form.setTrainingType(tb.getTrainingType());
			form.setStart(tb.getStart());
			form.setEnd(tb.getEnd());
			form.setAnalysis(tb.getAnalysis());
			form.setSuggestion(tb.getSuggestion());
			form.setIsDel(tb.getIsDel());
			form.setCreateTime(tb.getCreateTime());
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new SystemException("获取详情信息出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	public void update(TbSpecialSuggestionForm model) throws SystemException {
		Transaction tx = null;

		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			
			TbSpecialSuggestion po = (TbSpecialSuggestion) session.load(TbSpecialSuggestion.class, model.getId());
			po.setSection(model.getSection());
			po.setTrainingType(model.getTrainingType());
			po.setStart(model.getStart());
			po.setEnd(model.getEnd());
			po.setAnalysis(model.getAnalysis());
			po.setSuggestion(model.getSuggestion());
			session.update(po);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(ex);
			}
			throw new SystemException("更新出错!请检查输入数据。", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}
}
