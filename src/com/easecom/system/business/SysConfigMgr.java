package com.easecom.system.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.DateTimeUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysConfig;
import com.easecom.system.web.SysConfigForm;

/**
 * 系统配置表
 * @author 周志君
 * 2013-6-21 上午09:16:37
 */
public class SysConfigMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SysConfigMgr.class);
	
	private static List<SysConfig> sysConfigList = new ArrayList<SysConfig>();
	
	static{
		String hql = "from SysConsfig sc where 1=1";
		try {
			Session session = HibernateSessionFactory.openSession();
			sysConfigList = session.createQuery(hql).list();
		} catch (HibernateException e) {
			LogErrorMgr.addLog(e.getMessage(), null, null, null, DateTimeUtils.getCurrTime(), "3", null);
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 根据别名获取某一个配置参数
	 * @param alias
	 * @return
	 * @author 周志君
	 * 2013-6-21 下午02:30:39
	 */
	public static SysConfig get(String alias){
		SysConfig sc = null;
		
		if(null==alias || "".equals(alias))
			return null;
		
		if(null!=sysConfigList && sysConfigList.size()>0){
			for(int i=0; i<sysConfigList.size(); i++){
				SysConfig po = sysConfigList.get(i);
				if(alias.equals(po.getAlias())){
					return po;
				}
			}
		}
		
		return sc;
	}
	
	/**
	 * 根据type获取列表，如果type为空，则获取全部列表
	 * @param type
	 * @return
	 * @author 周志君
	 * 2013-6-21 下午02:38:17
	 */
	public static List<SysConfig> getList(String type){
		List<SysConfig> list = new ArrayList<SysConfig>();
		
		if(null==type || "".equals(type)){
			return sysConfigList;
		}
		
		if(null!=sysConfigList && sysConfigList.size()>0){
			for(int i=0; i<sysConfigList.size(); i++){
				SysConfig po = sysConfigList.get(i);
				if(type.equals(po.getType())){
					list.add(po);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 获取配置参数列表
	 * @return
	 * @throws SystemException
	 */
	public List<SysConfig> list(List<QueryCond> queryConds, int maxCount,  String orderby) throws SystemException{
		if(null==orderby || "".equals(orderby)){
			orderby = " order by sc.sort desc";
		}
		String hql = "from SysConfig sc where 1=1 " + QueryHelper.toAndQuery(queryConds) + orderby;
		System.out.println(hql);
		
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			
			if(maxCount>0)
				list = session.createQuery(hql).setFirstResult(0).setMaxResults(maxCount).list();
			else
				list = session.createQuery(hql).list();
			
		} catch (HibernateException e) {
			logger.error("查询配置信息失败", e);
			throw new SystemException("查询配置信息失败", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return list;
	}
	
	public List<SysConfig> list(String type) throws SystemException{
		String hql = "from SysConfig sc where 1=1 " ;
			   hql = hql+" and sc.alias like '%"+type+"%'";
		       hql = hql +" order by sc.sort asc ";
		       
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
				list = session.createQuery(hql).list();
		} catch (HibernateException e) {
			logger.error("查询配置信息失败", e);
			throw new SystemException("查询配置信息失败", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = "from SysConfig sc where 1=1 "
					+ QueryHelper.toAndQuery(queryConds)
					+ " order by sc.sort asc"	;
			
			Query qCount = ses
					.createQuery("select count(id) from SysConfig as sc where 1=1 "
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
	public ListContainer list(Collection queryConds, int currentPageInt,
							  int itemsInPage, String action, int jumpPage,String orderby) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = "from SysConfig sc where 1=1 "
					+ QueryHelper.toAndQuery(queryConds)
					+ " "+orderby	;

			Query qCount = ses
					.createQuery("select count(id) from SysConfig as sc where 1=1 "
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
	 * 获取所有的省份城市列表
	 * @return
	 */
	public static List provinceCityList(){
		Session session = HibernateSessionFactory.openSession();
		try {
			String queryString = "from SystemProvinceCity";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			logger.error("获取省份城市列表发生错误", re);
			throw re;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public SysConfigForm view(String id) throws SystemException, Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			SysConfig po = (SysConfig) ses.load(SysConfig.class, id);
			SysConfigForm form = new SysConfigForm();
			form.setId(po.getId());
			form.setAlias(po.getAlias());
			form.setName(po.getName());
			form.setValue(po.getValue());
			form.setType(po.getType());
			form.setUrl(po.getUrl());
			form.setSort(po.getSort());
			form.setJumpType(po.getJumpType());
			
			if(StringUtils.isNotBlank(po.getJumpType())) {
				String jt = po.getJumpType();
				String valueName = null;
				if("11".equals(jt) || "12".equals(jt) || "13".equals(jt) || "14".equals(jt)) {
					String valueId = po.getValue();
					switch (jt) {
					case "11":	// 资讯
						valueName = Tool.getValue("select t.title from news t where t.id='"+valueId+"'");
						break;
					case "12":	// 活动
						valueName = Tool.getValue("select t.title from community_post t where t.id='"+valueId+"'");
						break;
					case "13":	// 图书
						valueName = Tool.getValue("select t.p_name from product t where t.id='"+valueId+"'");
						break;
					case "14":	// 网课
						valueName = Tool.getValue("select t.nc_name from network_course t where t.id='"+valueId+"'");
						break;
					default:
						break;
					}
					form.setValueId(valueId);
					form.setValueName(valueName);
				}
			}
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new SystemException("获取用户信息出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	 /**
		*更新
		*/
		public void update(SysConfigForm vo) throws SystemException, Exception {
			Transaction tx = null;
			try {
			
				Session session = HibernateSessionFactory.openSession();
				tx = session.beginTransaction();
				SysConfig po = (SysConfig) session.load(SysConfig.class, vo.getId());
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
	
}
