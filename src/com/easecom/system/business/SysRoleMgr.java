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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.StrUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeNode;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysFunction;
import com.easecom.system.model.SysOrg;
import com.easecom.system.model.SysRole;
import com.easecom.system.model.SysRoleFunright;
import com.easecom.system.model.SysUserRole;
import com.easecom.system.web.SysRoleForm;

/**
 * @author Administrator
 * 
 */
public class SysRoleMgr extends AbstractHibernateDAO {

	private static LogUtils logger = LogUtils.getLogger(SysRoleMgr.class);

	/**
	 * 生成组织树
	 * 
	 * @param parentId
	 * @param orgId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getOrgtree(String parentId, String orgId) throws SystemException,
			Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Session ses = HibernateSessionFactory.openSession();
			SysOrg org1 = (SysOrg) ses.load(SysOrg.class, orgId);
			String queryString = "from SysOrg as org where org.id not in ('"
					+ parentId + "','" + org1.getId() + "') and org.code like '"
					+ org1.getCode() + "%' order by org.code ";
			Query query = ses.createQuery(queryString);
			if (query == null) {
				list = new ArrayList();
			} else {
				list = query.list();
			}
			Iterator tempArray = list.iterator();
			SysOrg org = null;
			TreeNode treeNode = null;
			if (!org1.getId().equalsIgnoreCase("FFFFFF")) {
				treeNode = new TreeNode(org1.getId(), org1.getName(), "");
				treeNode.setTreeNodeURL("sysrole.do?act=list&orgId="
						+ org1.getId());
				treeNodes.add(treeNode);
				if (!org1.getSysOrg().getId().equalsIgnoreCase("FFFFFF")) {
					parentId = org1.getSysOrg().getId();
				}
			}
			while (tempArray.hasNext()) {
				org = (SysOrg) tempArray.next();
				treeNode = new TreeNode(org.getId(), org.getName(), org.getSysOrg()
						.getId().equals(parentId) ? "" : org.getSysOrg().getId());
				treeNode.setTreeNodeURL("sysrole.do?act=list&orgId="
						+ org.getId());
				treeNodes.add(treeNode);
			}
			return treeNodes;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 判断角色名称是否已经存在
	 * 
	 * @param typeid
	 * @param roleName
	 * @return
	 */
	public boolean isRoleExist(String typeid, String roleName) {
		int count = Tool.getIntValue("select count(id) from sys_role where name='"
				+ roleName + "'");
		if (count > 0)
			return true;
		else
			return false;
	}

	/**
	 * 改变角色其他内容,在不改变角色名称时
	 * 
	 * @param typeid
	 * @param roleName
	 * @return
	 */
	public boolean isRoleExistupdate(String typeid, String roleName) {
		int count = Tool.getIntValue("select count(id) from sys_role where name='"
				+ roleName + "'");
		if (count > 1)
			return true;
		else
			return false;
	}

	/**
	 * 添加
	 * 
	 * @param vo
	 *          要添加的对象，类型要根据具体来定义
	 * @throws Exception
	 *           要抛出的异常，可定义多个
	 */
	public void add(SysRoleForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
			if (isRoleExist("", vo.getName()))
				throw new Exception("名称为" + vo.getName() + "的角色已经存在!");
			try {
				Session session = HibernateSessionFactory.openSession();
				tx = session.beginTransaction();
				SysRole po = new SysRole();
				po.setName(vo.getName());
				po.setRemark(vo.getRemark());
				po.setRoleurl(vo.getRoleurl());
				po.setShopId(vo.getShopId());
				session.save(po);
				tx.commit();
			} catch (Exception e) {
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
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param vo
	 *          要添加的对象，类型要根据具体来定义
	 * @throws Exception
	 *           要抛出的异常，可定义多个
	 */
	public void update(SysRoleForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
			if (isRoleExistupdate("", vo.getName()))
				throw new Exception("名称为" + vo.getName() + "的角色已经存在!");
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			SysRole po = (SysRole) session.load(SysRole.class, vo.getId());
			this.updateRoleName(vo, session);
			po.setName(vo.getName());
			po.setRemark(vo.getRemark());
			po.setRoleurl(vo.getRoleurl());
			po.setShopId(vo.getShopId());
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
	 * @param IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void delete(String[] ids) throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			SysRole po = null;
			System.out.println(ids.length+"==========================");
			// 删除对象
			for (int i = 0; i < ids.length; i++) {
				po = (SysRole) session.load(SysRole.class, ids[i]);
				List list1 = session.createFilter(po.getSysRoleFunrights(), "").list();
				List list2 = session.createFilter(po.getSysUserRoles(), "").list();
				
				if(null!=list1&&list1.size()>0){
					for(int j=0; j<list1.size(); j++)
						session.delete(list1.get(j));
				}
				if(null!=list2&&list2.size()>0){
					for(int j=0; j<list2.size(); j++)
						session.delete(list2.get(j));
				}
				session.delete(po);
			}
			tx.commit();
		} catch (Exception e) {
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
	 * 查看详细信息
	 * 
	 * @param IDs
	 * @throws SystemException
	 * @throws Exception
	 */
	public SysRoleForm view(String id) throws SystemException, Exception {
		try {
			Session session = HibernateSessionFactory.openSession();
			SysRole po = (SysRole) session.load(SysRole.class, id);
			SysRoleForm form = new SysRoleForm();
			form.setId(po.getId());
			form.setName(po.getName());
			form.setRemark(po.getRemark());
			form.setRoleurl(po.getRoleurl());
			return form;
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获取角色信息出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 列表
	 * 
	 * @param queryConds
	 *          查询条件
	 * @param currentPageInt
	 *          当前页码
	 * @param itemsInPage
	 *          每页的记录数
	 * @param action
	 *          页面行为
	 * @param jumpPage
	 *          跳页的目标页码
	 * @return 返回符合条件的列表对象
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage) throws SystemException,
			Exception {
		try {

			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 保存对象
			String hq = "from SysRole as role where 1=1 "+QueryHelper.toAndQuery(queryConds)+"   order by role.name";
			Query qCount = ses
					.createQuery("select count(role) from SysRole as role  where 1 = 1    " + QueryHelper.toAndQuery(queryConds));

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
			throw new SystemException("角色列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.debug(ex);
			}
		}
	}

	// /////////////////////////////////////////////
	//
	// 以下是给角色分配功能操作权限
	//
	// /////////////////////////////////////////////

	/**
	 * 得到功能分配树
	 * 
	 * @param roleid
	 * @param parentid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFuntree(String roleid, String parentid)
			throws SystemException, Exception {
		List nodes = new ArrayList();
		try {
				Collection queryConds = new ArrayList();
			
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "from SysFunction as fun where 1 = 1 "+QueryHelper.toAndQuery(queryConds)+" and fun.id not in ('"
					+ parentid + "') order by fun.code,fun.sort ";
			List list = ses.createQuery(queryString).list();
			Iterator tempArray = list.iterator();
			SysFunction fun = null;
			TreeNode treeNode = null;
			while (tempArray.hasNext()) {
				fun = (SysFunction) tempArray.next();
				treeNode = new TreeNode(fun.getId(), fun.getName(), fun
						.getSysFunction().getId().equals("FFFFFF") ? "" : fun
						.getSysFunction().getId(), "c");

				// 判断该功能是否已经分配给该角色
				if (isCfgfun(roleid, fun.getId()))
					treeNode.setSelected(true);
				nodes.add(treeNode);
			}
			return nodes;
		} catch (Exception e) {
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
	 * 
	 * 方法功能说明：得到功能菜单树，但只能得到自己用的功能菜单  
	 * 创建：2015年12月22日 by Zzc   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 * @参数： @param roleid
	 * @参数： @param parentid
	 * @参数： @param userId
	 * @参数： @return
	 * @参数： @throws SystemException
	 * @参数： @throws Exception      
	 * @return List     
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List getFuntree(String roleid, String parentid,String userId)
			throws SystemException, Exception {
		List nodes = new ArrayList();
		try {
				Collection queryConds = new ArrayList();
				String roleId = Tool.getValue("select role_id from sys_user_role where user_id='"+userId+"'");
				String funIds = "";
				if(!roleId.equals("")){
					List<Map> funIdList =  Tool.query("select fun_id from sys_role_funright where role_id='"+roleId+"'");
					if(funIdList.size()>0){
						Map map = new HashMap();
						funIds = "'"+funIdList.get(0).get("FUN_ID").toString()+"'";
						for (int i = 1; i < funIdList.size(); i++) {
							funIds = funIds+","+"'"+funIdList.get(i).get("FUN_ID").toString()+"'";
						}
					}
				}
				if(!funIds.equals("")){
					queryConds.add(new QueryCond("fun.id", "String", "in", funIds));
				}else{
					queryConds.add(new QueryCond("fun.id", "String", "in", "null"));
				}
			
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "from SysFunction as fun where 1 = 1 "+QueryHelper.toAndQuery(queryConds)+" and fun.id not in ('"
					+ parentid + "') order by fun.code,fun.sort ";
			List list = ses.createQuery(queryString).list();
			Iterator tempArray = list.iterator();
			SysFunction fun = null;
			TreeNode treeNode = null;
			while (tempArray.hasNext()) {
				fun = (SysFunction) tempArray.next();
				treeNode = new TreeNode(fun.getId(), fun.getName(), fun
						.getSysFunction().getId().equals("FFFFFF") ? "" : fun
						.getSysFunction().getId(), "c");

				// 判断该功能是否已经分配给该角色
				if (isCfgfun(roleid, fun.getId()))
					treeNode.setSelected(true);
				nodes.add(treeNode);
			}
			return nodes;
		} catch (Exception e) {
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
	 * 保存分配的功能操作权限
	 * 
	 * @param roleid
	 * @param funids
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveFun(String roleid, String funids) throws SystemException,
			Exception {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			SysRole role = (SysRole) session.load(SysRole.class, roleid);
			Iterator it = role.getSysRoleFunrights().iterator();
			while (it.hasNext()) {
				session.delete((SysRoleFunright) it.next());
			}
			tx.commit();

			tx = session.beginTransaction();
			String[] funid = null;
			if (funids != null && !funids.equals("")) {
				funid = StrUtils.split(funids, ',');
				for (int i = 0; funid != null && i < funid.length; i++) {
					SysFunction fun = (SysFunction) session.load(SysFunction.class,
							funid[i]);
					SysRoleFunright po = new SysRoleFunright();
					po.setSysFunction(fun);
					po.setFunName(fun.getName());
					po.setSysRole(role);
					po.setRoleName(role.getName());
					session.save(po);
				}
			}
			tx.commit();

		} catch (Exception e) {
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(e);
			}
			throw new SystemException("功能保存出错!", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 判断是否菜单
	 * 
	 * @param funid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean isFun(String funid) throws SystemException, Exception {
		boolean flag = false;
		String hq = "select count(id) from sys_function where id='" + funid + "'";
		if (Tool.getIntValue(hq) > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断该功能是否已经分配给角色
	 * 
	 * @param roleid
	 * @param funid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	private boolean isCfgfun(String roleid, String funid) throws SystemException,
			Exception {
		boolean flag = false;
		String hq = "select count(id) from sys_role_funright where role_id='"
				+ roleid + "' and fun_id='" + funid + "'";
		if (Tool.getIntValue(hq) > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断该功能该操作是否已经分配给角色
	 * 
	 * @param roleid
	 * @param funoperid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean isCfgFunoper(String roleid, String funoperid)
			throws SystemException, Exception {
		boolean flag = false;
		String hq = "select count(1) from sys_role_func_oper where func_oper_id='"
				+ funoperid + "' and role_id='" + roleid + "'";
		if (Tool.getIntValue(hq) > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 修改角色名称时，同时修改对应人员和对应功能下角色名称
	 * 
	 * @param vo
	 * @param ses
	 */
	@SuppressWarnings("unchecked")
	private void updateRoleName(SysRoleForm vo, Session ses) {

		String voname = vo.getName();
		SysRole po = (SysRole) ses.load(SysRole.class, vo.getId());

		List urlist = null;
		List rflist = null;
		if (!("".equals(vo.getName())) && null != voname
				&& !(voname.equals(po.getName()))) {

			// 查询SysUserRole和SysRoleFunright---找出角色为po的UserRole与RoleFunright
			String urhql = " from SysUserRole where 1=1 and sysRole.id=" + "'"
					+ vo.getId() + "'";
			String rfhql = " from SysRoleFunright where 1=1 and sysRole.id=" + "'"
					+ vo.getId() + "'";
			try {
				Query uquery = ses.createQuery(urhql);
				Query rquery = ses.createQuery(rfhql);
				urlist = (List) (uquery.list());
				rflist = (List) (rquery.list());

				for (int i = 0; i < urlist.size(); i++) {
					SysUserRole userrole = (SysUserRole) urlist.get(i);
					userrole.setRoleName(vo.getName());
					ses.update(userrole);
				}
				for (int i = 0; i < rflist.size(); i++) {
					SysRoleFunright rfrole = (SysRoleFunright) rflist.get(i);
					rfrole.setRoleName(vo.getName());
					ses.update(rfrole);
				}
			} catch (HibernateException he) {
				throw he;
			}
		}
	}
}
