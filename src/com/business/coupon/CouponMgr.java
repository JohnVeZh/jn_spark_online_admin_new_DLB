package com.business.coupon;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.coupon.dao.CouponCode;
import com.business.coupon.dao.CouponTemplate;
import com.business.coupon.vo.CouponCodeForm;
import com.business.coupon.vo.CouponTemplateForm;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;
import com.easecom.system.exception.SystemException;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class CouponMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(CouponMgr.class);

	public ListContainer templateList(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from CouponTemplate where 1=1"+ QueryHelper.toAndQuery(queryConds)+ " order by createTime desc";
			Query qCount = ses.createQuery("select count(id) from CouponTemplate where 1=1 " + QueryHelper.toAndQuery(queryConds));

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
			
			List<CouponTemplate> tmpList = query.list();
			List<CouponTemplateForm> list = new ArrayList<CouponTemplateForm>();
			for(CouponTemplate tmp : tmpList) {
				CouponTemplateForm form = new CouponTemplateForm();
				form.setId(tmp.getId());
				form.setTitle(tmp.getTitle());
				form.setType(tmp.getType());
				form.setStatus(tmp.getStatus());
				form.setEffectTime(tmp.getEffectTime());
				form.setInvalidTime(tmp.getInvalidTime());
				form.setCreateTime(tmp.getCreateTime());
				form.setEffectPeriod(tmp.getEffectPeriod());
//				form.setEffectPeriodStart(tmp.getEffectPeriodStart());
//				form.setEffectPeriodEnd(tmp.getEffectPeriodEnd());
				form.setDiscountType(tmp.getDiscountType());
				form.setMinMoney(tmp.getMinMoney());
				form.setDiscountMoney(tmp.getDiscountMoney());
				form.setDiscountRate(tmp.getDiscountRate());
				form.setProductType(tmp.getProductType());
				form.setProductId(tmp.getProductId());
				form.setGainNum(Tool.getLongValue("select count(1) from coupon_gain_record t where t.template_id='" + tmp.getId() + "'"));
				form.setUseNum(Tool.getLongValue("select count(1) from coupon a, coupon_gain_record b where a.`status`='3' and a.id=b.coupon_id and b.template_id='" + tmp.getId() + "'"));
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

	public ListContainer codeList(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from CouponCode where 1=1"+ QueryHelper.toAndQuery(queryConds)+ " order by createTime desc";
			Query qCount = ses.createQuery("select count(id) from CouponCode where 1=1 " + QueryHelper.toAndQuery(queryConds));

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
	public void delete(String ID) throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			Connection conn = ses.connection();
			Statement stmt = conn.createStatement();
			stmt.execute("delete from coupon_template_relation where content_id = '"+ID+"' and type='4'");
			CouponCode couponCode = null;
			couponCode = (CouponCode) ses.load(CouponCode.class, ID);
			ses.delete(couponCode);
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
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	public CouponCodeForm view(String id) throws SystemException{
		try {
			Session ses = HibernateSessionFactory.openSession();
			CouponCode couponCode = (CouponCode) ses.load(CouponCode.class, id);
			CouponCodeForm form = new CouponCodeForm();
			form.setId(couponCode.getId());
			form.setCode(couponCode.getCode());
			form.setStatus(couponCode.getStatus());
			form.setEffectTime(couponCode.getEffectTime());
			form.setInvalidTime(couponCode.getInvalidTime());
			form.setCreateTime(couponCode.getCreateTime());
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

	public void codeUpdate(CouponCodeForm couponCodeForm)throws SystemException {

		Transaction tx = null;
		try {

			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			CouponCode couponCode = (CouponCode) session.load(CouponCode.class, couponCodeForm.getId());
			couponCode.setId(couponCodeForm.getId());
			couponCode.setStatus(couponCodeForm.getStatus());
			couponCode.setEffectTime(couponCodeForm.getEffectTime());
			couponCode.setInvalidTime(couponCodeForm.getInvalidTime());
			couponCode.setCreateTime(couponCodeForm.getCreateTime());
			session.update(couponCode);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(e);
			}
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}
	public List getCouponTemplateNoPage(String titile,Collection queryConds) throws SystemException{
		try {
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from CouponTemplate where 1=1 "+ QueryHelper.toAndQuery(queryConds) + " order by createTime desc";
			List lc = new ArrayList();
			Query query = ses.createQuery(hq.toString());
			return query.list();
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

	public List viewTemplateByContentId(String id) throws SystemException{
		try {
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from CouponTemplateRelation where 1=1 and content_id='"+id+ "'";
			List lc = new ArrayList();
			Query query = ses.createQuery(hq.toString());
			return query.list();
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
	
	public CouponTemplateForm templateView(String id) throws SystemException {
		try {
			Session ses = HibernateSessionFactory.openSession();
			CouponTemplate tb = (CouponTemplate) ses.load(CouponTemplate.class, id);
			CouponTemplateForm form = new CouponTemplateForm();
			form.setId(tb.getId());
			form.setTitle(tb.getTitle());
			form.setType(tb.getType());
			form.setStatus(tb.getStatus());
			form.setEffectTime(tb.getEffectTime());
			form.setInvalidTime(tb.getInvalidTime());
			form.setCreateTime(tb.getCreateTime());
			form.setEffectPeriod(tb.getEffectPeriod());
//			form.setEffectPeriodStart(tb.getEffectPeriodStart());
//			form.setEffectPeriodEnd(tb.getEffectPeriodEnd());
			form.setDiscountType(tb.getDiscountType());
			form.setMinMoney(tb.getMinMoney());
			form.setDiscountMoney(tb.getDiscountMoney());
			form.setDiscountRate(tb.getDiscountRate());
			form.setProductType(tb.getProductType());
			form.setProductId(tb.getProductId());
			if("2".equals(form.getType())) {
				String productName = null;
				if("1".equals(form.getProductType())) {
					productName = Tool.getValue("select p_name from product where id='" + form.getProductId() + "'");
				} else if("2".equals(form.getProductType())) {
					productName = Tool.getValue("select network_name from network_video where id='" + form.getProductId() + "'");
				}
				form.setProductName(productName);
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
	
	public void templateAdd(CouponTemplateForm tb) throws SystemException {
		Transaction tx = null;

		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			
			CouponTemplate po = new CouponTemplate();
			po.setId(tb.getId());
			po.setTitle(tb.getTitle());
			po.setType(tb.getType());
			po.setStatus(tb.getStatus());
			po.setEffectTime(tb.getEffectTime());
			po.setInvalidTime(tb.getInvalidTime());
			po.setCreateTime(tb.getCreateTime());
			po.setEffectPeriod(tb.getEffectPeriod());
//			po.setEffectPeriodStart(tb.getEffectPeriodStart());
//			po.setEffectPeriodEnd(tb.getEffectPeriodEnd());
			po.setDiscountType(tb.getDiscountType());
			po.setMinMoney(tb.getMinMoney());
			po.setDiscountMoney(tb.getDiscountMoney());
			po.setDiscountRate(tb.getDiscountRate());
			po.setProductType(tb.getProductType());
			po.setProductId(tb.getProductId());
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

	public void templateUpdate(CouponTemplateForm vo) throws SystemException {
		Transaction tx = null;

		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			
			CouponTemplate po = (CouponTemplate) session.load(CouponTemplate.class, vo.getId());
			po.setTitle(vo.getTitle());
			po.setType(vo.getType());
			po.setStatus(vo.getStatus());
			po.setEffectTime(vo.getEffectTime());
			po.setInvalidTime(vo.getInvalidTime());
			po.setEffectPeriod(vo.getEffectPeriod());
//			po.setEffectPeriodStart(vo.getEffectPeriodStart());
//			po.setEffectPeriodEnd(vo.getEffectPeriodEnd());
			po.setDiscountType(vo.getDiscountType());
			po.setMinMoney(vo.getMinMoney());
			po.setDiscountMoney(vo.getDiscountMoney());
			po.setDiscountRate(vo.getDiscountRate());
			po.setProductType(vo.getProductType());
			po.setProductId(vo.getProductId());
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

	public List couponList(String hql) {
		Session session = HibernateSessionFactory.openSession();

		try {
			Query query = session.createQuery(hql);
			if (query == null){
				return null;
			}else{
				return query.list();
			}
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public ListContainer templateRelationList(Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "from CouponTemplateRelation where 1=1"+ QueryHelper.toAndQuery(queryConds)+ " order by createTime desc";
			Query qCount = ses.createQuery("select count(id) from CouponTemplateRelation where 1=1 " + QueryHelper.toAndQuery(queryConds));

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

	public ListContainer bindResourceList(String relationType, Collection queryConds, int currentPageInt, int itemsInPage, String action, int jumpPage) throws SystemException {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			// 查询对象
			String hq = "";
			Query qCount = null;
			if("1".equals(relationType)) {
				hq = "from CommunityPost where 1=1" + QueryHelper.toAndQuery(queryConds)+ " order by createtime desc";
				qCount = ses.createQuery("select count(id) from CommunityPost where 1=1 " + QueryHelper.toAndQuery(queryConds));
			} else if("2".equals(relationType)) {
				hq = "from Product where 1=1" + QueryHelper.toAndQuery(queryConds)+ " order by pCreatetime desc";
				qCount = ses.createQuery("select count(id) from Product where 1=1 " + QueryHelper.toAndQuery(queryConds));
			} else if("3".equals(relationType)) {
                hq ="from NetworkVideo where isDel=0 and isDel=0 and isFree=0 and isTeacher=0 and (levelType=01 or levelType=02 or levelType=03)"
                        +QueryHelper.toAndQuery(queryConds);
                qCount = ses
                        .createQuery("select count(id) from NetworkVideo where 1=1 and is_del=0 and is_free=0 and is_teacher=0 and (level_type=01 or level_type=02 or level_type=03)"
                                + QueryHelper.toAndQuery(queryConds));
			} else if("5".equals(relationType) || "6".equals(relationType) || "7".equals(relationType)) {
				String scType = "5".equals(relationType) ? "INDEX_CAROUSEL" : "6".equals(relationType) ? "INDEX_CAROUSEL2" : "INDEX_CAROUSEL3";
                hq = "from SysConfig where type='" + scType + "' "+ QueryHelper.toAndQuery(queryConds)+" order by sort asc ";;
                qCount = ses.createQuery("select count(id) from SysConfig where type='" + scType + "' "+ QueryHelper.toAndQuery(queryConds));
			}

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
	
	public void bindResource(String templateId, String relationType, List<String> contentIdList) {
		StringBuffer deleteId = new StringBuffer();
		StringBuffer insertSql = new StringBuffer("insert into coupon_template_relation(id, type, template_id, content_id, create_time) values ");
		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		for(int i=0; i<contentIdList.size(); i++) {
			String id = UUID.randomUUID().toString().replaceAll("-","");
			insertSql.append(i==0?"('":",('").append(id).append("','").append(relationType).append("','").append(templateId).append("','").append(contentIdList.get(i)).append("','").append(datetime).append("')");
			deleteId.append(i==0?"'":",'").append(contentIdList.get(i)).append("'");
		}
		String deleteSql = "delete from coupon_template_relation where template_id = '" + templateId + "' and content_id in (" + deleteId.toString() + ")";
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			Connection conn = session.connection();
			Statement statement = conn.createStatement();
			statement.execute(deleteSql.toString());
			statement.execute(insertSql.toString());
			statement.close();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
}
