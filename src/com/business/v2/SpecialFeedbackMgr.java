package com.business.v2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbSpecialFeedback;
import com.business.v2.vo.TbSpecialFeedbackForm;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;

@SuppressWarnings("all")
public class SpecialFeedbackMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SpecialFeedbackMgr.class);

	public ListContainer list(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from TbSpecialFeedback where 1=1 "+ QueryHelper.toAndQuery(queryConds)+ " order by isSolved, createTime desc";
			Query qCount = ses.createQuery("select count(id) from TbSpecialFeedback where 1=1 " + QueryHelper.toAndQuery(queryConds));

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
			
			List<TbSpecialFeedback> tmpList = query.list();
			List<TbSpecialFeedbackForm> list = new ArrayList<TbSpecialFeedbackForm>();
			for(TbSpecialFeedback item : tmpList) {
				TbSpecialFeedbackForm form = new TbSpecialFeedbackForm();
				form.setId(item.getId());
				form.setSection(item.getSection());
				form.setTrainingType(item.getTrainingType());
				form.setTrainingId(item.getTrainingId());
				form.setProblemType(item.getProblemType());
				form.setContent(item.getContent());
				form.setUserId(item.getUserId());
				form.setIsSolved(item.getIsSolved());
				form.setCreateTime(item.getCreateTime());
				TbSpecialCatalog catalog = (TbSpecialCatalog) super.getObjectByHql("select a from TbSpecialCatalog a, TbSpecialTraining b where a.id=b.catalogId and b.trainingId='" + item.getTrainingId() + "'");
				if(catalog != null) {
					form.setTrainingCatalogName(catalog.getName());
				}
				list.add(form);
			}
			lc.getList().addAll(list);// 装填指定页的列表数据到列表容器
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
				stmt.addBatch("delete from tb_special_feedback where id='" + id + "'");
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
	
	public TbSpecialFeedbackForm view(String id) throws SystemException {
		try {
			Session ses = HibernateSessionFactory.openSession();
			TbSpecialFeedback tb = (TbSpecialFeedback) ses.load(TbSpecialFeedback.class, id);
			TbSpecialFeedbackForm form = new TbSpecialFeedbackForm();
			form.setId(tb.getId());
			form.setSection(tb.getSection());
			form.setTrainingType(tb.getTrainingType());
			form.setTrainingId(tb.getTrainingId());
			form.setProblemType(tb.getProblemType());
			form.setContent(tb.getContent());
			form.setUserId(tb.getUserId());
			form.setIsSolved(tb.getIsSolved());
			form.setCreateTime(tb.getCreateTime());
			TbSpecialCatalog catalog = (TbSpecialCatalog) super.getObjectByHql("select a from TbSpecialCatalog a, TbSpecialTraining b where a.id=b.catalogId and b.trainingId='" + tb.getTrainingId() + "'");
			if(catalog != null) {
				form.setTrainingCatalogName(catalog.getName());
				TbSpecialCatalog parentCatalog = (TbSpecialCatalog) super.getObjectByHql("from TbSpecialCatalog where id='" + catalog.getPId() + "'");
				if(parentCatalog != null) {
					form.setTrainingParentCatalogName(parentCatalog.getName());
				}
			}
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
	
	public void update(TbSpecialFeedbackForm model) throws SystemException {
		Transaction tx = null;

		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			
			TbSpecialFeedback po = (TbSpecialFeedback) session.load(TbSpecialFeedback.class, model.getId());
			po.setIsSolved("1");
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
