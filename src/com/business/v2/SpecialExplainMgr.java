package com.business.v2;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.v2.special.TbSpecialExplain;
import com.business.v2.vo.TbSpecialExplainForm;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;
import com.easecom.system.exception.SystemException;

@SuppressWarnings("all")
public class SpecialExplainMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SpecialExplainMgr.class);

	public ListContainer list(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from TbSpecialExplain where 1=1"+ QueryHelper.toAndQuery(queryConds)+ " order by createTime desc";
			Query qCount = ses.createQuery("select count(id) from TbSpecialExplain where 1=1 " + QueryHelper.toAndQuery(queryConds));

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
			
			List<TbSpecialExplain> tmpList = query.list();
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
	
	public void add(TbSpecialExplainForm model) throws SystemException {
		Transaction tx = null;

		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			
			TbSpecialExplain po = new TbSpecialExplain();
			po.setId(model.getId());
			po.setSection(model.getSection());
			po.setTrainingType(model.getTrainingType());
			po.setTitle(model.getTitle());
			po.setContentType(model.getContentType());
			po.setContent(model.getContent());
			po.setContentId(model.getContentId());
			po.setVisitNum(model.getVisitNum());
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
	
	public TbSpecialExplainForm view(String id) throws SystemException {
		try {
			Session ses = HibernateSessionFactory.openSession();
			TbSpecialExplain tb = (TbSpecialExplain) ses.load(TbSpecialExplain.class, id);
			TbSpecialExplainForm form = new TbSpecialExplainForm();
			form.setId(tb.getId());
			form.setSection(tb.getSection());
			form.setTrainingType(tb.getTrainingType());
			form.setTitle(tb.getTitle());
			form.setContentType(tb.getContentType());
			form.setContent(tb.getContent());
			form.setContentId(tb.getContentId());
			form.setVisitNum(tb.getVisitNum());
			form.setCreateTime(tb.getCreateTime());
			if("11".equals(form.getContentType())) {	// 资讯
				String contentName = Tool.getValue("select title from news where id='" + form.getContentId() + "'");
				form.setContentName(contentName);
			} else if("21".equals(form.getContentType())) {	// 图书
				String contentName = Tool.getValue("select t.p_name from product t where t.id='" + form.getContentId() + "'");
				form.setContentName(contentName);
			} else if("22".equals(form.getContentType())) {	// 网课
				String contentName = Tool.getValue("select t.nc_name from network_course t where t.id='" + form.getContentId() + "'");
				form.setContentName(contentName);
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
	
	public void update(TbSpecialExplainForm model) throws SystemException {
		Transaction tx = null;

		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			
			TbSpecialExplain po = (TbSpecialExplain) session.load(TbSpecialExplain.class, model.getId());
			po.setSection(model.getSection());
			po.setTrainingType(model.getTrainingType());
			po.setTitle(model.getTitle());
			po.setContentType(model.getContentType());
			po.setContent(model.getContent());
			po.setContentId(model.getContentId());
			po.setVisitNum(model.getVisitNum());
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
