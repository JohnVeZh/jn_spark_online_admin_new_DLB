package com.business.ProductOrder; 


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.Express.Express;
import com.business.ProductOrderLogistics.ProductOrderLogistics;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.mobile.MobilePage;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.system.exception.SystemException;


public class ProductOrderMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(ProductOrder.class);


	/**
	 * 添加
	 *
	 * @throws Exception 要抛出的异常 可定义多个
	 * @prama vo 要添加的对象，类型 根据具体的来定义
	 */
	public void add(ProductOrderActionForm vo) throws SystemException, Exception {
		Transaction tx = null;

		try {
			ProductOrder po = new ProductOrder();
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			super.copyProperties(po, vo);
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

	/**
	 * 更新
	 */
	public void update(ProductOrderActionForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {

			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			ProductOrder po = (ProductOrder) session.load(ProductOrder.class, vo.getId());
			super.copyProperties(po, vo);
			session.update(po);
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


	/**
	 * 删除
	 *
	 * @throws SystemException
	 * @throws Exception
	 * @prama IDs
	 */
	@SuppressWarnings("unchecked")
	public void delete(String[] IDs) throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			ProductOrder po = null;
			for (int i = 0; i < IDs.length; i++) {
				po = (ProductOrder) ses.load(ProductOrder.class, IDs[i]);
				//List list = ses.createFilter(po.getProductOrder(), "").list();
				//if (list.size() < 1 && po.getSysRoleFunrights().size() < 1)// 没有引用
				ses.delete(po);
				//else
				//	throw new Exception("功能:" + po.getName() + "已经使用或存在下级功能或操作，不能删除!");
			}
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

	/**
	 * 查看详细信息
	 *
	 * @throws SystemException
	 * @throws Exception
	 * @prama ID
	 */
	public ProductOrderActionForm view(String ID) throws SystemException, Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			ProductOrder po = (ProductOrder) ses.load(ProductOrder.class, ID);
			ProductOrderActionForm form = new ProductOrderActionForm();
			super.copyProperties(form, po);
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

	/**
	 * 列表
	 *
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 * @prama queryConds 查询条件
	 * @prama currentPageInt 当前页码
	 * @prama itemsInPage 每页的记录数
	 * @prama action 页面行为
	 * @prama jumpPage 跳页的目标页码
	 */
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
							  int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = "from ProductOrder as ProductOrder where 1=1 "
					+ QueryHelper.toAndQuery(queryConds)
					+ " order by ProductOrder.createtime  desc";
			Query qCount = ses
					.createQuery("select count(id) from ProductOrder as ProductOrder where 1=1 "
							+ QueryHelper.toAndQuery(queryConds));
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


	/**
	 * 接口：列表
	 *
	 * @param queryConds
	 * @param mp
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @created 2015-1-19 下午5:43:06
	 */
	public List<ProductOrder> mlist(Collection queryConds, MobilePage mp) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 保存对象
			String hq = "from ProductOrder as ProductOrder where 1=1 "
					+ QueryHelper.toAndQuery(queryConds) + " order by ProductOrder.createtime desc";
			Query qCount = ses
					.createQuery("select count(id) from ProductOrder as ProductOrder where 1=1 "
							+ QueryHelper.toAndQuery(queryConds));

			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
					: "0");
			mp.setTotalCount(totalItems); // 设置记录总数
			Query query = ses.createQuery(hq.toString());
			query.setFirstResult(mp.getStartRecord());
			query.setMaxResults(mp.getRowCountPerPage());
			return query.list();
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("接口列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
	}

	/**
	 * 添加获取图书订单导出数据
	 *
	 * @param queryConds
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public List getBookOrderList(Collection queryConds) throws SystemException, Exception {
		try {
			String sql = " SELECT pro.p_name, pod.pcount, pod.money, u.username," +
					" p.pname,c.city,a.area,po.pay_price,po.consignee,po.telephone,po.zipcode," +
					" po.address,po.createtime,po.remark,po.order_code,po.postage FROM" +
					" product_order po LEFT JOIN product_order_details pod ON po.id = pod.product_order_id" +
					" LEFT JOIN product pro ON pod.product_id = pro.id" +
					" LEFT JOIN users u ON po.user_id = u.id" +
					" LEFT JOIN area a ON po.area_id = a.areaID" +
					" LEFT JOIN city c ON po.city_id = c.cityID" +
					" LEFT JOIN province p ON po.province_id = p.provinceID" +
					" where 1=1 " + QueryHelper.toAndQuery(queryConds);


			List list = super.SQLQuery(sql);
			return list;
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
	 * 添加获取网课订单导出数据
	 *
	 * @param queryConds
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public List getNetWorkOrderList(Collection queryConds) throws SystemException, Exception {
		try {
			String sql = " SELECT case prd.check_status  when 'check_ing' then '退款审核中' when  'pay_finished' then '已完成退款' ELSE '正常' END as check_status,"+
					" CASE po.payType WHEN '0' THEN '支付宝'WHEN '1' THEN '微信'WHEN '3' THEN '兑换码'END AS payType,nv.network_name as p_name, pod.pcount, pod.money, u.username," +
					" p.pname,c.city,a.area,po.price,po.consignee,po.telephone,po.zipcode," +
					" po.address,po.createtime,po.remark,po.order_code,po.postage FROM" +
					" product_order po LEFT JOIN product_order_details pod ON po.id = pod.product_order_id" +
					" LEFT JOIN product_order_refund prd ON prd.product_order_details_id = pod.id" +
					" LEFT JOIN network_video nv ON pod.product_id = nv.id" +
					" LEFT JOIN users u ON po.user_id = u.id" +
					" LEFT JOIN area a ON po.area_id = a.areaID" +
					" LEFT JOIN city c ON po.city_id = c.cityID" +
					" LEFT JOIN province p ON po.province_id = p.provinceID" +
					"  where 1=1 " + QueryHelper.toAndQuery(queryConds);

			List list = super.SQLQuery(sql);
			return list;
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
	 * 列表
	 *
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 * @prama queryConds 查询条件
	 * @prama currentPageInt 当前页码
	 * @prama itemsInPage 每页的记录数
	 * @prama action 页面行为
	 * @prama jumpPage 跳页的目标页码
	 */
	@SuppressWarnings("unchecked")
	public ListContainer bookList(String whereStr, int currentPageInt,
								  int itemsInPage, String action, int jumpPage, String searchType) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			String sql = "SELECT  p.pname,cast(c.city as char(50)),cast(a.area as char(50)),pro.p_name,po.id," +
					" po.price,po.zipcode,po.consignee,po.telephone,po.address," +
					" po.createtime,po.order_state,po.remark,po.order_code,po.postage," +
					" po.pay_price,po.from_type,pod.id details_id,pod.pcount,pod.`status`," +
					" pod.money,u.username,po.logisticsCode,u.mobile FROM product pro LEFT JOIN product_order_details pod" +
					" ON pod.product_id = pro.id LEFT JOIN product_order po ON pod.product_order_id = po.id" +
					" LEFT JOIN users u ON po.user_id = u.id" +
					" LEFT JOIN area a ON po.area_id = a.areaID" +
					" LEFT JOIN city c ON po.city_id = c.cityID" +
					" LEFT JOIN province p ON po.province_id = p.provinceID" +
					" where 1=1 " + whereStr +
					" order by po.createtime desc";
			String sqlCount = "SELECT count(1) count FROM product pro LEFT JOIN product_order_details pod" +
					" ON pod.product_id = pro.id LEFT JOIN product_order po ON pod.product_order_id = po.id" +
					" LEFT JOIN users u ON po.user_id = u.id" +
					" LEFT JOIN area a ON po.area_id = a.areaID" +
					" LEFT JOIN city c ON po.city_id = c.cityID" +
					" LEFT JOIN province p ON po.province_id = p.provinceID" +
					" where 1=1 " + whereStr +
					" order by po.createtime desc";
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
			Query query = ses.createSQLQuery(sql.toString());
			if (!"all".equals(searchType)) {
				query.setMaxResults(lc.getItemsInPage());
				query.setFirstResult(lc.calculateNextPageIndex());
			}
			List dataList = query.list();
			lc.getList().addAll(dataList);// 装填指定页的列表数据到列表容器
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
	 * 列表
	 *
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 * @prama queryConds 查询条件
	 * @prama currentPageInt 当前页码
	 * @prama itemsInPage 每页的记录数
	 * @prama action 页面行为
	 * @prama jumpPage 跳页的目标页码
	 */
	@SuppressWarnings("unchecked")
	public ListContainer networkList(Collection queryConds, int currentPageInt,
									 int itemsInPage, String action, int jumpPage,String searchType) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			String sql = "SELECT   p.pname,cast(c.city as char(50)),cast(a.area as char(50)),nv.network_name,po.id," +
					" po.price,po.zipcode,po.consignee,po.telephone,po.address," +
					" po.createtime,po.order_state,po.remark,po.order_code,po.postage," +
					" po.pay_price,po.from_type,pod.id details_id,pod.pcount,pod.`status`," +
					" pod.money,u.username,po.logisticsCode,u.mobile FROM network_video nv LEFT JOIN product_order_details pod" +
					" ON pod.product_id = nv.id LEFT JOIN product_order po ON pod.product_order_id = po.id" +
					" LEFT JOIN users u ON po.user_id = u.id" +
					" LEFT JOIN area a ON po.area_id = a.areaID" +
					" LEFT JOIN city c ON po.city_id = c.cityID" +
					" LEFT JOIN province p ON po.province_id = p.provinceID" +
					" where 1=1 " + QueryHelper.toAndQuery(queryConds) +
					" order by po.createtime desc";
			String sqlCount = "SELECT count(1) count FROM network_video nv LEFT JOIN product_order_details pod" +
					" ON pod.product_id = nv.id LEFT JOIN product_order po ON pod.product_order_id = po.id" +
					" LEFT JOIN users u ON po.user_id = u.id" +
					" LEFT JOIN area a ON po.area_id = a.areaID" +
					" LEFT JOIN city c ON po.city_id = c.cityID" +
					" LEFT JOIN province p ON po.province_id = p.provinceID" +
					" where 1=1 " + QueryHelper.toAndQuery(queryConds) +
					" order by po.createtime desc";
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
			Query query = ses.createSQLQuery(sql.toString());
			if (!"all".equals(searchType)) {
				query.setMaxResults(lc.getItemsInPage());
				query.setFirstResult(lc.calculateNextPageIndex());
			}
			List dataList = query.list();
			lc.getList().addAll(dataList);// 装填指定页的列表数据到列表容器
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
	 * 发货 - 导入
	 * @param orderCode		订单号
	 * @param expressName	物流商名称
	 * @param logistics		物流编号
	 */
	public boolean orderExpress(String orderCode, String expressName, String logistics) {
		Session session = HibernateSessionFactory.openSession();
		ProductOrder order = (ProductOrder) session.createQuery("from ProductOrder where orderCode='" + orderCode + "'").uniqueResult();
		if(order == null) {
			return false;
		}
		Express express = (Express) session.createQuery("from Express where eName='" + expressName + "'").uniqueResult();
		if(express == null) {
			return false;
		}
		
		Transaction tx = session.beginTransaction();
		try {
			order.setOrderState(DictionaryUtils.ORDER_STATE_NOT_RECEIVED);
			order.setLogisticscode(logistics);
			order.setAutoRewardTime(DateUtils.format(new Date(new Date().getTime()+14*24*60*60*1000),"yyyy-MM-dd HH:mm:ss"));
			session.update(order);
			
			ProductOrderLogistics orderLogistics = (ProductOrderLogistics) session.createQuery("from ProductOrderLogistics where orderHistory='" + order.getId() + "'").uniqueResult();
			if(orderLogistics != null) {
				orderLogistics.seteCode(express.geteCode());
				orderLogistics.setLogisticscode(logistics);
				orderLogistics.seteName(express.geteName());
				session.update(orderLogistics);
			} else {
				orderLogistics = new ProductOrderLogistics();
				orderLogistics.seteCode(express.geteCode());
				orderLogistics.seteName(express.geteName());
				orderLogistics.setCreatetime(DateUtils.getCurrDateTimeStr());
				orderLogistics.setOrderHistory(order.getId());
				orderLogistics.setLogisticscode(logistics);
				session.save(orderLogistics);
			}
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
		} finally {
//			HibernateSessionFactory.closeSession();
		}
		
		return true;
	}
}