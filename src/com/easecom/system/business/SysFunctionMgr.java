/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.system.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeNode;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysFunction;
import com.easecom.system.web.SysFunctionForm;

/**
 * @title: 功能类
 * @description:
 * @author: wanghw
 * @version: 1.0
 * @create Date:2007-1-10
 */
public class SysFunctionMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SysUserMgr.class);

	public SysFunctionMgr() {
		super();
	}

	@SuppressWarnings("unchecked")
	public boolean isFunExist(String parentid, String funName, String funCode) {
		Session session = HibernateSessionFactory.openSession();
		String hql = "from SysFunction as fun where fun.sysFunction.id = '"
				+ parentid + "'  and (fun.name = '" + funName + "' or fun.code = '"
				+ funCode + "') ";
		try {
			Query query = session.createQuery(hql);
			List list = query.list();
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return false;
	}

	/**
	 * 添加
	 * 
	 * @prama vo 要添加的对象，类型 根据具体的来定义
	 * @throws Exception
	 *           要抛出的异常 可定义多个
	 */
	public void add(SysFunctionForm vo) throws SystemException, Exception {
		Transaction tx = null;
		if (isFunExist(vo.getParentid(), vo.getName(), vo.getCode()))
			throw new Exception("名称为" + vo.getName() + "或编码为" + vo.getCode()
					+ "的功能已经存在!");
		String path = getFunPathName(vo.getParentid()) + vo.getName();
		try {
			SysFunction po = new SysFunction();
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			SysFunction parent = (SysFunction) ses.load(SysFunction.class, vo
					.getParentid());
			po.setSysFunction(parent);
			po.setName(vo.getName());
			po.setCode(vo.getCode());
			po.setFullname(path);
			po.setUrl(vo.getUrl());
			po.setSort(vo.getSort());
			po.setIsdir(vo.getIsdir());
			po.setIsview(vo.getIsview());
			po.setRemark(vo.getRemark());
			po.setIsvalid(vo.getIsvalid());
			po.setIcon(vo.getIcon());
//			po.setState(vo.getState());
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
	 * @prama vo 要更新的数据
	 * @throws Exception
	 *           抛出的异常，可定义多个
	 */
	public void update(SysFunctionForm vo) throws SystemException, Exception {
		Transaction tx = null;
		String path = getFunPathName(vo.getParentid()) + vo.getName();
		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			SysFunction po = (SysFunction) ses.load(SysFunction.class, vo.getId());
			po.setName(vo.getName());
			po.setFullname(path);
			po.setUrl(vo.getUrl());
			po.setSort(vo.getSort());
			po.setIsdir(vo.getIsdir());
			po.setIsview(vo.getIsview());
			po.setRemark(vo.getRemark());
			po.setIsvalid(vo.getIsvalid());
			po.setIcon(vo.getIcon());
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
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 删除
	 * 
	 * @prama IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void delete(String[] IDs) throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			SysFunction po = null;
			for (int i = 0; i < IDs.length; i++) {
				po = (SysFunction) ses.load(SysFunction.class, IDs[i]);
				List list = ses.createFilter(po.getSysFunctions(), "").list();
				if (list.size() < 1 && po.getSysRoleFunrights().size() < 1)// 没有引用
					ses.delete(po);
				else
					throw new Exception("功能:" + po.getName() + "已经使用或存在下级功能或操作，不能删除!");
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
	 * @prama ID
	 * @throws SystemException
	 * @throws Exception
	 */
	public SysFunctionForm view(String ID) throws SystemException, Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			SysFunction po = (SysFunction) ses.load(SysFunction.class, ID);
			SysFunctionForm form = new SysFunctionForm();
			form.setId(po.getId());
			form.setCode(po.getCode());
			form.setFullname(po.getFullname());
			form.setIsdir(po.getIsdir());
			form.setIsview(po.getIsview());
			form.setName(po.getName());
			form.setParentcode(po.getSysFunction().getCode());
			form.setParentid(po.getSysFunction().getId());
			form.setRemark(po.getRemark());
			form.setSort(po.getSort());
			form.setUrl(po.getUrl());
			form.setIsvalid(po.getIsvalid());
			form.setIcon(po.getIcon());
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
	 * 列表
	 * 
	 * @prama queryConds 查询条件
	 * @prama currentPageInt 当前页码
	 * @prama itemsInPage 每页的记录数
	 * @prama action 页面行为
	 * @prama jumpPage 跳页的目标页码
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			String hq = "from SysFunction as function where 1=1 "
					+ QueryHelper.toAndQuery(queryConds)
					+ " order by function.sort, function.code ";
			Query qCount = ses
					.createQuery("select count(function) from SysFunction as function where 1=1 "
							+ QueryHelper.toAndQuery(queryConds));

			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt);
			lc.setItemsInPage(itemsInPage);
			lc.setPageAction(PageAction.fromString(action));
			lc.setJumpIndex(jumpPage);
			List l = qCount.list();
			int totalItems = Integer.parseInt(l.size() > 0 ? l.get(0).toString()
					: "0");
			lc.setTotalItem(totalItems);
			Query query = ses.createQuery(hq.toString());
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			lc.getList().addAll(query.list());
			return lc;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("查询数据出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}

	/**
	 * 生成功能树
	 * 
	 * @param parentId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFuntree(String parentId) throws SystemException, Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Collection queryConds = new ArrayList();
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "from SysFunction as function where function.id not in ('"
					+ parentId + "') "+QueryHelper.toAndQuery(queryConds)+" order by function.code ";
			Query query = ses.createQuery(queryString);
			if (query == null) {
				list = new ArrayList();
			} else {
				list = query.list();
			}
			Iterator tempArray = list.iterator();
			SysFunction function = null;
			TreeNode treeNode = null;
			while (tempArray.hasNext()) {
				function = (SysFunction) tempArray.next();
				treeNode = new TreeNode(function.getId(), function.getName(), function
						.getSysFunction().getId().equals(parentId) ? "" : function
						.getSysFunction().getId());
				treeNode
						.setTreeNodeURL("sysFunction.do?act=list&parentid="
								+ function.getId());//+"&state="+function.getState()
				treeNodes.add(treeNode);
			}
			return treeNodes;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 得到主界面功能权限树
	 * 
	 * @param userId
	 * @param parentId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFunTree(String userId, String parentId)
			throws SystemException, Exception {
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			List funIds = new SysUserMgr().getFunction(userId);// 功能ID列表
			Iterator it = funIds.iterator();
			while (it.hasNext()) {
				String funId = (String) it.next();
				SysFunction function = (SysFunction) session.load(SysFunction.class,
						funId);
				TreeNode treeNode = new TreeNode(function.getId(), function.getName(),
						function.getSysFunction().getId().equals(parentId) ? "" : function
								.getSysFunction().getId());
				String url = function.getUrl();
				if (url != null && !url.equalsIgnoreCase("")) {
					if (url.indexOf("?") > 0)
						url = url + "&funid=" + function.getId();
					else
						url = url + "?funid=" + function.getId();
					treeNode.setTreeNodeURL(url);
				}
				list.add(treeNode);
			}
			return list;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 根据ID得到其编码
	 * 
	 * @param id
	 * @return
	 */
	public String getCodeById(String id) {
		return Tool.getValue("select code from sys_function where id='" + id + "'");
	}

	/**
	 * 根据父ID生成编码
	 * 
	 * @param parentid
	 * @return
	 */
	public String getNewCodeByparentid(String parentid) {
		String code = getCodeById(parentid);
		String temp = Tool
				.getValue("select max(code) from sys_function where parent_id='"
						+ parentid + "'");
		if (temp.equalsIgnoreCase("") || temp == null)
			temp = "0000";
		temp = temp.substring(temp.length() - 4);
		int i = Integer.parseInt(temp) + 1;
		temp = String.valueOf(i);
		if (temp.length() == 1)
			code += "000" + temp;
		else {
			if (temp.length() == 2)
				code += "00" + temp;
			else {
				if (temp.length() == 3)
					code += "0" + temp;
				else
					code = temp;
			}

		}
		return code;
	}

	/**
	 * 根据父ID生成排序值
	 * 
	 * @param parentid
	 * @return
	 */
	public int getNewSortByParentid(String parentid) {
		return Tool
				.getIntValue("select max(sort) from sys_function where parent_id='"
						+ parentid + "'") + 1;
	}

	/**
	 * 得到功能的全名
	 * 
	 * @param parentid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public String getFunPathName(String parentid) throws SystemException,
			Exception {
		String name = "";
		try {
			Session session = HibernateSessionFactory.openSession();
			String idx = parentid;
			while (!idx.equalsIgnoreCase("FFFFFF")) {
				SysFunction po = (SysFunction) session.load(SysFunction.class, idx);
				name = po.getName() + "-" + name;
				idx = po.getSysFunction().getId();
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("得到功能全名出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
		return name;
	}
}
