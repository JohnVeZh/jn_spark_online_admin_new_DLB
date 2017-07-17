/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.system.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.UploadCmd;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.StrUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeNode;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysFunction;
import com.easecom.system.model.SysOrg;
import com.easecom.system.model.SysRole;
import com.easecom.system.model.SysUser;
import com.easecom.system.model.SysUserRole;
import com.easecom.system.web.SysUserForm;

public class SysUserMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(SysUserMgr.class);

	public SysUserMgr() {
		super();
	}

	/**
	 * 添加
	 * 
	 * @prama vo 要添加的对象，类型 根据具体的来定义
	 * @throws Exception
	 *             要抛出的异常 可定义多个
	 */
	public void add(SysUserForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
			if (isUserExist(vo.getLoginName()))
				throw new Exception("登陆名称为" + vo.getLoginName() + "的用户已经存在!");
			if(isUserNameExist(vo.getName()))
				throw new Exception("用户名称为" + vo.getName() + "用户已存在");
			SysUser po = new SysUser();
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			super.copyProperties(po, vo);
			po.setName(vo.getName());
			po.setLoginName(vo.getLoginName());
			SysOrg org = (SysOrg) ses.load(SysOrg.class, vo.getOrgId());
			po.setSysOrg(org);
			po.setOrgName(org.getName());
			po.setPassword(MD5.getInstance().getMD5ofStr(vo.getPassword()).toLowerCase());
			po.setEmail(vo.getEmail());
//			po.setPhoneCornet(vo.getPhoneCornet());
			po.setWorkPhone(vo.getWorkPhone());
			po.setType(vo.getType());
			po.setShopId(vo.getShopId());
			po.setIsvalid("1");// 标示用户是启用状态
//			if ("0".equals(vo.getType())) {
//				po.setEmailPassword(vo.getEmailPassword().trim());
//			}
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
	 * 判断该名称用户是否已存在
	 * 
	 * @param loginName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isUserExist(String loginName) {
		Session session = HibernateSessionFactory.openSession();
		String hql = "from SysUser as user where user.loginName = '"
				+ loginName + "' and user.isvalid=1 and user.sysOrg.isvalid=1";
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
	 * 判断用户名是否已存在
	 * @param loginName
	 * @return
	 */
	public boolean isUserNameExist(String name) {
		Session session = HibernateSessionFactory.openSession();
		String hql = "from SysUser as user where user.name = '"
				+ name + "' and user.isvalid=1 and user.sysOrg.isvalid=1";
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
	

//	/**
//	 * @prama vo 要更新的数据
//	 * @throws Exception
//	 *             抛出的异常，可定义多个
//	 */
//	public void update(SysUserForm vo) throws SystemException, Exception {
//		Transaction tx = null;
//		try {
//			if (!view(vo.getId()).getLoginName().equalsIgnoreCase(
//					vo.getLoginName())) {
//				if (isUserExist(vo.getLoginName()))
//					throw new Exception("登陆名称为" + vo.getLoginName()
//							+ "的用户已经存在!");
//			}
//			
//			if (!view(vo.getId()).getName().equalsIgnoreCase(
//					vo.getName())) {
//				if(isUserNameExist(vo.getName()))
//					throw new Exception("用户名称为" + vo.getName() + "用户已存在");
//			}			
//
//			Session ses = HibernateSessionFactory.openSession();
//			tx = ses.beginTransaction();
//			SysUser po = (SysUser) ses.load(SysUser.class, vo.getId());
//			po.setRemark(vo.getRemark());
//			po.setName(vo.getName());
//			po.setLoginName(vo.getLoginName());
//			if(vo.getPasswordnew() != null && !"".equals(vo.getPasswordnew())){
//				po.setPassword(MD5.getInstance().getMD5ofStr(vo.getPasswordnew()).toLowerCase());
//			}
//			po.setAge(vo.getAge());
//			po.setDuty(vo.getDuty());
////			po.setEducation(vo.getEducation());
////			po.setBirthdate(vo.getBirthdate());
//			po.setSex(vo.getSex());
////			po.setWorkdate(vo.getWorkdate());
//			po.setEmail(vo.getEmail());
////			po.setPhoneCornet(vo.getPhoneCornet());
//			po.setWorkPhone(vo.getWorkPhone());
//			SysOrg org = (SysOrg) ses.load(SysOrg.class, vo.getOrgId());
//			po.setSysOrg(org);
//			po.setOrgName(org.getName());
//			po.setType(vo.getType());
////			if ("0".equals(vo.getType())) {
////				po.setEmailPassword(vo.getEmailPassword().trim());
////			}
//			ses.update(po);
//			tx.commit();
//		} catch (Exception e) {
//			logger.error(e);
//			throw new SystemException(e.getMessage(), e);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
	/**
	 * @prama vo 要更新的数据
	 * @throws Exception
	 *             抛出的异常，可定义多个
	 */
	public void update(SysUserForm vo) throws SystemException, Exception {
		Transaction tx = null;
		try {
			if (!view(vo.getId()).getLoginName().equalsIgnoreCase(
					vo.getLoginName())) {
				if (isUserExist(vo.getLoginName()))
					throw new Exception("登陆名称为" + vo.getLoginName()
							+ "的用户已经存在!");
			}

			if (!view(vo.getId()).getName().equalsIgnoreCase(vo.getName())) {
				if (isUserNameExist(vo.getName()))
					throw new Exception("用户名称为" + vo.getName() + "用户已存在");
			}

			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			SysUser po = (SysUser) ses.load(SysUser.class, vo.getId());
			po.setRemark(vo.getRemark());
			po.setName(vo.getName());
			po.setLoginName(vo.getLoginName());
			if (vo.getPasswordnew() != null && !"".equals(vo.getPasswordnew())) {
				MD5 md5=MD5.getInstance();
				po.setPassword(md5.getMD5ofStr(vo.getPasswordnew()).toLowerCase());
			}
			po.setAge(vo.getAge());
			po.setDuty(vo.getDuty());
//			po.setEducation(vo.getEducation());
//			po.setBirthdate(vo.getBirthdate());
			po.setSex(vo.getSex());
//			po.setWorkdate(vo.getWorkdate());
			po.setEmail(vo.getEmail());
//			po.setPhoneCornet(vo.getPhoneCornet());
			po.setWorkPhone(vo.getWorkPhone());
			po.setShopId(vo.getShopId());
//			po.setWorknum(vo.getWorknum());
			SysOrg org = (SysOrg) ses.load(SysOrg.class, vo.getOrgId());
			po.setSysOrg(org);
			po.setOrgName(org.getName());
			po.setType(vo.getType());
//			po.setOpeningBank(vo.getOpeningBank());
//			po.setOpeningBankAccount(vo.getOpeningBankAccount());
//			if ("0".equals(vo.getType())) {
//				po.setEmailPassword(vo.getEmailPassword().trim());
//			}
//			if(!"".equals(vo.getEarnestMoney())&&null!=vo.getEarnestMoney()){
//				po.setEarnestMoney(Integer.parseInt(vo.getEarnestMoney()));
//			}
			ses.update(po);
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		} finally {
			HibernateSessionFactory.closeSession();
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
			SysUser po = null;
			for (int i = 0; i < IDs.length; i++) {
				if(!"FFFFFF".equals(IDs[i])){
					String opeflag = "";
					po = (SysUser) ses.load(SysUser.class, IDs[i]);
					opeflag = "0".equals(po.getIsvalid()) ? "1" : "0";
					po.setIsvalid(opeflag);// 把用户的可以状态置为0 或者 1 ，
					ses.update(po);
				}
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 查看用户详细信息
	 * 
	 * @prama ID
	 * @throws SystemException
	 * @throws Exception
	 */
	public SysUserForm view(String ID) throws SystemException, Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			SysUser po = (SysUser) ses.load(SysUser.class, ID);
			SysUserForm vo = new SysUserForm();
			super.copyProperties(vo, po);
			vo.setOrgId(po.getSysOrg().getId());
			vo.setOrgCode(po.getSysOrg().getCode());
			SysOrg org = (SysOrg) ses
					.load(SysOrg.class, po.getSysOrg().getId());
			vo.setOrgName(org.getName());
			//加密
			vo.setPassword(po.getPassword());
			vo.setEmail(po.getEmail());
			vo.setWorkPhone(po.getWorkPhone());
//			vo.setEmailPassword(po.getEmailPassword());
//			vo.setPhoneCornet(po.getPhoneCornet());
			return vo;
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获取用户信息出错！", e);
		} finally {
			HibernateSessionFactory.closeSession();
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
			int itemsInPage, String action, int jumpPage)
			throws SystemException, Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			String hq = "from SysUser as user where user.loginName<>'admin' "
					+ QueryHelper.toAndQuery(queryConds)
					+ "  order by user.name"; // and user.isvalid=1
			Query qCount = ses
					.createQuery("select count(user) from SysUser as user where user.loginName<>'admin' " // and
																										// user.isvalid=1
							+ QueryHelper.toAndQuery(queryConds));
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt);
			lc.setItemsInPage(itemsInPage);
			lc.setPageAction(PageAction.fromString(action));
			lc.setJumpIndex(jumpPage);
			List l = qCount.list();
			int totalItems = Integer.parseInt(l.size() > 0 ? l.get(0)
					.toString() : "0");
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
	 * 根据组织ID获取组织人员
	 * 
	 * @param orgId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getSysUserByOrgId(String orgId) throws SystemException,
			Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			String hq = "from SysUser as user where user.sysOrg.id='" + orgId
					+ "' order by user.name";
			Query query = ses.createQuery(hq.toString());
			return query.list();
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
	 * 生成组织树
	 * 
	 * @param parentId
	 * @param orgId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getOrgtree(String parentId, String orgId)
			throws SystemException, Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Session ses = HibernateSessionFactory.openSession();
			SysOrg org1 = (SysOrg) ses.load(SysOrg.class, orgId);
			String queryString = "from SysOrg as org where org.id not in ('"
					+ parentId + "','" + org1.getId()
					+ "') and org.code like '" + org1.getCode()
					+ "%' and org.isvalid=1  order by org.code asc ";
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
				treeNode.setTreeNodeURL("sysUser.do?act=list&orgId="
						+ org1.getId());
				treeNodes.add(treeNode);
				if (!org1.getSysOrg().getId().equalsIgnoreCase("FFFFFF")) {
					parentId = org1.getSysOrg().getId();
				}
			}
			while (tempArray.hasNext()) {
				org = (SysOrg) tempArray.next();
				treeNode = new TreeNode(org.getId(), org.getName(), org
						.getSysOrg().getId().equals(parentId) ? "" : org
						.getSysOrg().getId());
				treeNode.setTreeNodeURL("sysUser.do?act=list&orgId="
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
	 * 生成设置组织树
	 * 
	 * @param parentId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getCheckCfgOrgtree(String parentId) throws SystemException,
			Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "from SysOrg as org where org.id not in ('"
					+ parentId + "') order by org.code,org.sort ";
			Query query = ses.createQuery(queryString);
			if (query == null) {
				list = new ArrayList();
			} else {
				list = query.list();
			}
			Iterator tempArray = list.iterator();
			SysOrg org = null;
			TreeNode treeNode = null;
			while (tempArray.hasNext()) {
				org = (SysOrg) tempArray.next();
				treeNode = new TreeNode(org.getId(), org.getName(), org
						.getSysOrg().getId().equals(parentId) ? "" : org
						.getSysOrg().getId());
				treeNode.setTreeNodeURL("sysUser.do?act=checkCfgList&orgId="
						+ org.getId());
				treeNodes.add(treeNode);
			}
			return treeNodes;
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
	 * 获取用户已分配角色ID
	 * 
	 * @param userid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	public String getUserRoleId(String userid) throws SystemException,
			Exception {
		String hq = "select role_id from sys_user_role where user_id='"
				+ userid + "'";
		return Tool.getValue(hq);
	}

	/**
	 * 保存给用户配置的角色 userid:用户id roleid:角色ID
	 * 
	 * @param userid
	 * @param roleid
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveRoleCfg(String userid, String roleid)
			throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			// 先删除已经分配的角色权限
			SysUser sysUser = (SysUser) session.load(SysUser.class, userid);
			SysRole sysRole = null;
			if (roleid.equalsIgnoreCase("")) {
				Iterator tempArray = sysUser.getSysUserRoles().iterator();
				while (tempArray.hasNext()) {
					session.delete((SysUserRole) tempArray.next());
				}
				tx.commit();
			} else {
				Iterator tempArray = sysUser.getSysUserRoles().iterator();
				while (tempArray.hasNext()) {
					session.delete((SysUserRole) tempArray.next());
				}
				sysRole = (SysRole) session.load(SysRole.class, roleid);
				tx.commit();
				tx = session.beginTransaction();
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setSysRole(sysRole);
				sysUserRole.setRoleName(sysRole.getName());
				sysUserRole.setSysUser(sysUser);
				sysUserRole.setUserName(sysUser.getName());
				session.save(sysUserRole);
				tx.commit();
			}
		} catch (Exception e) {
			logger.error(e);
			try {
				if (tx != null)
					tx.rollback();
			} catch (Exception ex) {
				logger.error(e);
			}
			throw new SystemException("保存角色分配出错!", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

	/**
	 * 根据用户id,得到他的全部的功能列表
	 * 
	 * @param userid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFunction(String userid) throws SystemException, Exception {
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			String hq = "from SysFunction as fun where  fun.id<>'FFFFFF'  and fun.isview = '1' order by  fun.sort";
			System.out.println(hq);
			Query query = session.createQuery(hq);
			List l = query.list();
			if (userid.equalsIgnoreCase("FFFFFF")) { // sys（id="FFFFFF"）帐号具有所有的权限
				Iterator it = l.iterator();
				while (it.hasNext()) {
					SysFunction f = (SysFunction) it.next();
					list.add(f.getId());
				}
			} else {
				Iterator it1 = l.iterator();
				while (it1.hasNext()) {
					SysFunction fun = (SysFunction) it1.next();
					if (isCfgFun(userid, fun.getId()))
						list.add(fun.getId());
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获得分配的功能列表出错！", e);
		}
		return list;
	}
	/**
	 * 根据用户id，parentid得到子功能列表
	 */
	@SuppressWarnings("unchecked")
	public List getSonFunction(String userid, String parentid) throws SystemException, Exception {
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			String hq = "from SysFunction as fun where  fun.id<>'FFFFFF' and fun.sysFunction.id='"+parentid+"' order by  fun.sort";
			System.out.println(hq);
			Query query = session.createQuery(hq);
			List l = query.list();
			if (userid.equalsIgnoreCase("FFFFFF")) { // sys（id="FFFFFF"）帐号具有所有的权限
				Iterator it = l.iterator();
				while (it.hasNext()) {
					SysFunction f = (SysFunction) it.next();
					if("1".equals(f.getIsview())){
						list.add(f);
					}
				}
			} else {
				Iterator it1 = l.iterator();
				while (it1.hasNext()) {
					SysFunction fun = (SysFunction) it1.next();
					if (isCfgFun(userid, fun.getId()))
						if("1".equals(fun.getIsview())){
							list.add(fun);
						}
						
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获得分配的功能列表出错！", e);
		}
		return list;
	}
	/**
	 * 根据用户id,得到他的全部的功能的code列表
	 * 
	 * @param userid
	 *            用户id
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFunctionCode(String userid) throws SystemException,
			Exception {
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			String hq = "from SysFunction as fun where  fun.id<>'FFFFFF' order by  fun.sort";
			System.out.println(hq);
			Query query = session.createQuery(hq);
			List l = query.list();
			if (userid.equalsIgnoreCase("FFFFFF")) { // sys（id="FFFFFF"）帐号具有所有的权限
				Iterator it = l.iterator();
				while (it.hasNext()) {
					SysFunction f = (SysFunction) it.next();
					list.add(f.getCode());
				}
			} else {
				Iterator it1 = l.iterator();
				while (it1.hasNext()) {
					SysFunction fun = (SysFunction) it1.next();
					if (isCfgFun(userid, fun.getId()))
						list.add(fun.getCode());
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获得分配的功能列表出错！", e);
		}
		return list;
	}

	/**
	 * 判断该功能是否已经被该用户拥有
	 * 
	 * @param userid
	 * @param funid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	private boolean isCfgFun(String userid, String funid)
			throws SystemException, Exception {
		boolean flag = false;

		// 从用户-角色-功能路径中判断该功能是否已经分配
//		String temp1 = "select role_id from sys_user_role where user_id='"
//				+ userid + "')";
//		String temp2 = "select count(*) from sys_role_funright where fun_id='"
//				+ funid + "' and role_id in (";
//		String hql = temp2 + temp1;
//		if (Tool.getIntValue(hql) > 0)
//			flag = true;
//		return flag;
		String hql = "select count(ID) from sys_role_funright sf  left join sys_user_role sr on  sf.role_id = sr.role_id where   sr.user_id ='"+userid+"' and sf.fun_id = '"+funid+"'";
		if (Tool.getIntValue(hql) > 0){
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询出企业用户表中那些用户是没有分配的
	 * 
	 * @param queryConds
	 * @param currentPageInt
	 * @param itemsInPage
	 * @param action
	 * @param jumpPage
	 * @return
	 * @throws SystemException
	 */
	@SuppressWarnings("unchecked")
	public ListContainer assignList(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage)
			throws SystemException {
		try {
			Session session = HibernateSessionFactory.openSession();
			String hq = "from SysCompUser as user where 1=1 and user.sysUser.id=null  "
					+ QueryHelper.toAndQuery(queryConds);
			Query qCount = session
					.createQuery("select count(user) from SysCompUser as user where 1=1 and user.sysUser.id=null  "
							+ QueryHelper.toAndQuery(queryConds));

			// 新建并设置列表容器
			ListContainer lc = new ListContainer();

			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码

			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0)
					.toString() : "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = session.createQuery(hq.toString());
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());

			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获得未分配企业用户列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}

	}

	 

	/**
	 * 查看管理员用户所属的企业用户
	 * 
	 * @param queryConds
	 * @param currentPageInt
	 * @param itemsInPage
	 * @param action
	 * @param jumpPage
	 * @param userid
	 * @param orgId
	 * @return
	 * @throws SystemException
	 */
	@SuppressWarnings("unchecked")
	public ListContainer selectlist(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage, String userid,
			String orgId) throws SystemException {
		try {
			Session session = HibernateSessionFactory.openSession();
			String hq = "";
			Query qCount = null;

			// "0"代表没有选中管理员而直接查看有多少企业用户
			if ("0".equals(userid)) {
				hq = "from SysCompUser as user where  user.sysOrg.id='" + orgId
						+ "' and user.sysUser.id=null  "
						+ QueryHelper.toAndQuery(queryConds);
				qCount = session
						.createQuery("select count(user) from SysCompUser as user where 1=1 and user.sysUser.id=null and user.sysOrg.id='"
								+ orgId
								+ "' "
								+ QueryHelper.toAndQuery(queryConds));
			} else {
				hq = "from SysCompUser as user where  1=1 and user.sysUser.id='"
						+ userid
						+ "' and user.sysOrg.id='"
						+ orgId
						+ "' "
						+ QueryHelper.toAndQuery(queryConds);
				qCount = session
						.createQuery("select count(user) from SysCompUser as user where 1=1 and user.sysUser.id='"
								+ userid
								+ "' and user.sysOrg.id='"
								+ orgId
								+ "' " + QueryHelper.toAndQuery(queryConds));
			}

			// 新建并设置列表容器
			ListContainer lc = new ListContainer();

			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码

			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0)
					.toString() : "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = session.createQuery(hq.toString());
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());

			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("查看管理员用户所属企业用户列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}

	 
	 

	/**
	 * 主页面修改用户信息
	 * 
	 * @param vo
	 * @param typeUser
	 * @throws SystemException
	 * @throws Exception
	 */
	public void userManagerUpdate(SysUserForm vo, String typeUser)
			throws SystemException, Exception {
		Transaction tx = null;
		try {
			Session ses = HibernateSessionFactory.openSession();
			tx = ses.beginTransaction();
			if ("gs".equals(typeUser)) {
				SysUser po = (SysUser) ses.load(SysUser.class, vo.getId());
				po.setRemark(vo.getRemark());
				po.setName(vo.getName());
				po.setLoginName(vo.getLoginName());
				po.setPassword(StrUtils.encrypt(vo.getPasswordnew()));
				po.setAge(vo.getAge());
				po.setDuty(vo.getDuty());
//				po.setEducation(vo.getEducation());
//				po.setBirthdate(vo.getBirthdate());
				po.setSex(vo.getSex());
//				po.setWorkdate(vo.getWorkdate());
				po.setEmail(vo.getEmail());
//				po.setPhoneCornet(vo.getPhoneCornet());
				po.setWorkPhone(vo.getWorkPhone());
				po.setType(vo.getType());
				ses.save(po);
			}

			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 单个文件上传
	 * 
	 * @param ff
	 *            文件的名字
	 * @param folderPath
	 *            上传路径
	 * @return 包含上传路径和上传文件名字的对象UploadCmd
	 * @throws Exception
	 */
	public UploadCmd logoupload(FormFile ff, String folderPath)
			throws Exception {
		UploadCmd uploadCmd = new UploadCmd();
		try {
			File file = new File(folderPath);

			// 判断目录是否存在
			if (!file.exists()) { // 不存在创建
				file.mkdirs();
			}
			InputStream io = null;
			OutputStream os = null;
			String filename = ff.getFileName();
			if (!filename.equalsIgnoreCase("")) {
				io = ff.getInputStream();

				String filenameExt = filename.substring(
						filename.lastIndexOf(".")).toLowerCase();

				// 防止文件名子过长
				if (filename.subSequence(0, filename.lastIndexOf(".")).length() > 80) {
					filename = filename.substring(filename.length() - 80,
							filename.length());
				}
				String newFilename = filename.substring(0, filename
						.lastIndexOf("."))
						+ "_" + System.currentTimeMillis() + filenameExt;
				uploadCmd.setTitle(newFilename);
				uploadCmd.setFullPath(folderPath + newFilename);
				os = new FileOutputStream(folderPath + newFilename);
				int readByte = 0;
				byte buf[] = new byte[8129];
				while ((readByte = io.read(buf, 0, 8129)) != -1) {
					os.write(buf, 0, readByte);
				}
			}
			os.close();
			io.close();
			ff.destroy();
		} catch (Exception e) {
			logger.error(e.toString());

		}
		return uploadCmd;
	}

	 
	public String getValue(String s, Connection conn) {
		String s1 = "";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(s);
			if (resultset.next())
				s1 = resultset.getString(1) == null ? "" : resultset
						.getString(1);
			else
				s1 = "";
			resultset.close();
			statement.close();
		} catch (Exception exception) {
			logger.error(exception.toString());
		}
		return s1;
	}

	 

	 

	 

	/**
	 * @Description:导入Excel
	 * @param orgId 组织的Id
	 * @author: 窦恩虎
	 * @throws SystemException
	 * @date Oct 12, 2011 10:47:10 AM
	 */
	public List<String[]> importExcel(InputStream stream, String orgId, String[] titles)
			throws SystemException {
		List<String[]> list = new ArrayList<String[]>();
		String table[] = null;
		
		Workbook wb = null;
		try {
			wb = Workbook.getWorkbook(stream);
			Sheet rs = wb.getSheet(0);
			boolean mark = true;
			Session session = HibernateSessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			SysUser po = null;
			
			if(rs!=null && rs.getRows()>2){
				for(int i=0; i<titles.length; i++){
					String value = rs.getCell(i, 1).getContents();
					if(!titles[i].equals(value))
						throw new SystemException("导入工号信息时，数据格式不对");
				}
			}
			
			for (int i = 2; i < rs.getRows(); i++) {

				String loginName = rs.getCell(0, i).getContents();
				String name = rs.getCell(0, i).getContents();
				String password = rs.getCell(1, i).getContents();
				String sex = rs.getCell(2, i).getContents();
				String education = rs.getCell(3, i).getContents();
				String workdate = rs.getCell(4, i).getContents();
				String birthdate = rs.getCell(5, i).getContents();
				String age = rs.getCell(6, i).getContents();
				String duty = rs.getCell(7, i).getContents();
				String workPhone = rs.getCell(8, i).getContents();
				String email = rs.getCell(9, i).getContents();
				String homeAddress = rs.getCell(10, i).getContents();
				String remark = rs.getCell(11, i).getContents();

				// 判断用户名、真实姓名、密码是否为空
				if ("".equals(loginName) || "".equals(name)
						|| "".equals(password)) {
					mark = false;
				} else if (loginName.length() > 100 || name.length() > 100
						|| password.length() > 100 || sex.length() > 32
						|| education.length() > 32 || workdate.length() > 32
						|| birthdate.length() > 32 || age.length() > 32
						|| duty.length() > 32 || workPhone.length() > 32
						|| email.length() > 100 || remark.length() > 200
						|| homeAddress.length() > 200) {// 判断字段的长度是否符合条件
					mark = false;
				} else {
					// 判断输入的用户名是否存在
					String hql = "from SysUser as user where user.loginName = '"
						+ loginName + "' and user.isvalid=1 and user.sysOrg.isvalid=1";
					Query query = session.createQuery(hql);
					List list1 = query.list();
					if (list1 != null && list1.size() > 0) {
						mark = false;
					}else{
						mark = true;
					}
					
				} 
				
				if (mark) {
					
					po = new SysUser();
					SysOrg org = (SysOrg) session.load(SysOrg.class, orgId);
					po.setSysOrg(org);
					po.setOrgName(org.getName());
					po.setLoginName(loginName);
					po.setName(name);
					po.setPassword(StrUtils.encrypt(password));
					po.setSex(sex);
//					po.setEducation(education);
//					po.setWorkdate(workdate);
//					po.setBirthdate(birthdate);
					po.setAge(age);
					po.setDuty(duty);
					po.setWorkPhone(workPhone);
					po.setEmail(email);
					po.setRemark(remark);
					po.setIsvalid("1");
					session.save(po);

				} else {
					table = new String [12];
					table[0] = loginName ;
					table[1] = password;
					table[2] = sex;
					table[3] = education;
					table[4] = workdate;
					table[5] = birthdate;
					table[6] = age;
					table[7] = duty;
					table[8] = workPhone;
					table[9] = email;
					table[10] = homeAddress;
					table[11] = remark;
					list.add(table);
				}
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException(e.getMessage(), e);
		} finally {
			if(wb!=null)
				wb.close();
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	/**
	 * 获取部门
	 * 
	 * @return
	 * @throws SystemException
	 */
	@SuppressWarnings("unchecked")
	public List getOrg(String roleid, String parentid) throws SystemException,
			Exception {
		try {
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "from SysOrg as fun where fun.isvalid='1' and fun.id not in ('"
					+ parentid + "') order by fun.code,fun.sort ";
			List list = ses.createQuery(queryString).list();
			return list;
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
}
