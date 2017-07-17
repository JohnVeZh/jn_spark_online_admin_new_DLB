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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.MD5;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.Tool;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysConfig;
import com.easecom.system.model.SysFunction;
import com.easecom.system.model.SysUser;

public class LoginMgr extends AbstractHibernateDAO {
	private static LogUtils logger = LogUtils.getLogger(LoginMgr.class);

	/**
	 * 
	 * 
	 * @param loginName
	 * @param password
	 * @return  login result ， and  functions 
	 */
	public String isValidUser(String loginName, String password,
			HttpServletRequest request, SessionContainer sessionContainer,
			HttpSession session,int state) {
		try {
			String result_login = "0";
			Session ses = HibernateSessionFactory.openSession();
			//用户所在部门为：有效；用户本身的状态为：有效； 则允许登录
			String userHq = "from SysUser as user where user.sysOrg.isvalid=1 and user.isvalid=1 and user.loginName  = binary('"
					+ loginName + "')";
			logger.info(userHq);
			
			SysUser user = (SysUser) ses.createQuery(userHq).uniqueResult();
			if (null != user) {
				MD5 md5 = MD5.getInstance();
				logger.info(user.getPassword()+"==="+md5.getInstance().getMD5ofStr(password).toLowerCase()+"===="+password);
				if (user.getPassword().equals(md5.getMD5ofStr(password).toLowerCase())) {
					logger.warn(user.getName() + "已经登录...");	
					sessionContainer.setUserId(user.getId());
					sessionContainer.setUserName(user.getName());
					sessionContainer.setLoginName(user.getLoginName());
					String orgId = null;
					if (user.getSysOrg() != null) {
						sessionContainer.setOrgId(user.getSysOrg().getId());
						sessionContainer.setOrgName(user.getSysOrg().getFullname());//user.getSysOrg().getName()
						// 获取直属组织id
						orgId = user.getSysOrg().getId();
						String parentOrgId = this.getTopOrgId(orgId);
						if (!"".equals(parentOrgId)) {
							sessionContainer.setTopOrgId(parentOrgId);
						} else {
							sessionContainer.setTopOrgId("");
						}
					// no  org  
					} else {
						sessionContainer.setOrgId(orgId);
						sessionContainer.setOrgName("ERROR ORG");
					}
				 
					sessionContainer.setRoleId("");
					sessionContainer.setRoleName("");
					sessionContainer.setLoadMark(user.getType());
					sessionContainer.setStyleName(user.getEmail());
					sessionContainer.setStyleUrl("normal");
					sessionContainer.setFunIds(getFunction(user.getId(),state));// 装载功能列表
					sessionContainer.setType(user.getType());
					sessionContainer.setUrltype(state+"");
			/*		//获取系统开关
					String switchHql="from SysConfig where type='switch' and 1=1";
					List<SysConfig> switchList=mgr.getList(switchHql);
					Map map=new HashMap();
					if(!"".equals(switchList)&&null!=switchList&&switchList.size()>0){
						for(int i=0;i<switchList.size();i++){
							map.put(switchList.get(i).getAlias(),switchList.get(i).getValue());
						}
					}
					sessionContainer.setMap(map);*/
					session.setAttribute("SessionContainer", sessionContainer);
					request.setAttribute("denglu", "1"); 
					result_login = "1";
				} 
				return result_login;
			}else{
				return result_login;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return "0";
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	 

	/**  ff80808140b135db0140b1ee74190004
	 * 根据用户id,得到他的功能列表
	 * 
	 * @param userid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFunction(String userid,int state) throws SystemException, Exception {
		List list = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			
			Collection queryConds = new ArrayList();
//			queryConds.add(new QueryCond("fun.state","String","=",(state+"")));
			String hq = "from SysFunction as fun where fun.id<>'FFFFFF' and fun.isview = '1'   "+QueryHelper.toAndQuery(queryConds)+"  order by fun.sort asc "; // fun.code,
			Query query = session.createQuery(hq);
			List l = query.list();
			if (userid.equalsIgnoreCase("FFFFFF")) { // sys（id="FFFFFF"）帐号具有所有的权限
				Iterator it = l.iterator();
				while (it.hasNext()) {
					SysFunction f = (SysFunction) it.next();
					list.add(f);
				}
			} else {
				Iterator it1 = l.iterator();
				while (it1.hasNext()) {
					SysFunction fun = (SysFunction) it1.next();
					if (isCfgFun(userid, fun.getId()))
						list.add(fun);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SystemException("获得分配的功能列表出错！", e);
		}
		return list;
	}

	/**
	 * 判断该功能是否已经被该用户所拥有
	 * 
	 * @param userid
	 * @param funid
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	private boolean isCfgFun(String userid, String funid) throws SystemException,
			Exception {
		boolean flag = false;

		// 从用户-角色-功能路径中判断该功能是否已经分配
		String temp1 = "select role_id from sys_user_role where  user_id='"
				+ userid + "')";
		String temp2 = "select count(id) from sys_role_funright where fun_id='"
				+ funid + "' and role_id in (";
		String hql = temp2 + temp1;
		if (Tool.getIntValue(hql) > 0)
			flag = true;
		return flag;
	}

	public String getTopOrgId(String orgId) {

		String topOrgId = "";
		String sql = "  select org.id,org.code from sys_org as org where "
				+ "org.CODE=left((select sysorg.code from sys_org  as sysorg where id='"
				+ orgId + "'),8)";
		topOrgId = Tool.getValue(sql);
		return topOrgId;

	}

	public String getTopOrgName(String orgId) {

		String topOrgName = "";
		String sql = "  select org.name from sys_org as org where "
				+ "org.CODE=left((select sysorg.code from sys_org  as sysorg where id='"
				+ orgId + "'),8)";
		topOrgName = Tool.getValue(sql);
		return topOrgName;

	}

	public String TopOrgId22(String orgId) {
		String topOrgId = "";
		String sql = " select org.id from sys_org as org where org.CODE=left((select sysorg.code from sys_org  as sysorg where sysorg.id='"
				+ orgId
				+ "'),(select LENGTH(sorg.code) from sys_org  as sorg where sorg.id='"
				+ orgId + "')-4)";
		topOrgId = Tool.getValue(sql);
		return topOrgId;
	}
	/**
	 * 
	 * 
	 * @param loginName
	 * @param password
	 * @return  
	 */
	public String isASValidUser(String sysId,
			HttpServletRequest request, SessionContainer sessionContainer,
			HttpSession session,int state) {
		try {
			String result_login = "0";
			Session ses = HibernateSessionFactory.openSession();
			//用户所在部门为：有效；用户本身的状态为：有效； 则允许登录
			String userHq = "from SysUser as user where user.sysOrg.isvalid=1 and user.isvalid=1 and user.id='"+sysId+"'";
			SysUser user = (SysUser) ses.createQuery(userHq).uniqueResult();
			if (null != user) {
				MD5 md5 = MD5.getInstance();
				logger.warn(user.getName() + "已经登录...");	
				sessionContainer.setUserId(user.getId());
				sessionContainer.setUserName(user.getName());
				sessionContainer.setLoginName(user.getLoginName());
				String orgId = null;
				if (user.getSysOrg() != null) {
					sessionContainer.setOrgId(user.getSysOrg().getId());
					sessionContainer.setOrgName(user.getSysOrg().getFullname());//user.getSysOrg().getName()
					// 获取直属组织id
					orgId = user.getSysOrg().getId();
					String parentOrgId = this.getTopOrgId(orgId);
					if (!"".equals(parentOrgId)) {
						sessionContainer.setTopOrgId(parentOrgId);
					} else {
						sessionContainer.setTopOrgId("");
					}
				} else {
					sessionContainer.setOrgId(orgId);
					sessionContainer.setOrgName("");
				}
				 
				sessionContainer.setRoleId("");
				sessionContainer.setRoleName("");
				//sessionContainer.setStyleUser("gs");
				sessionContainer.setLoadMark(user.getType());
//				sessionContainer.setStyleId(user.getPhoneCornet());
				sessionContainer.setStyleName(user.getEmail());
				sessionContainer.setStyleUrl("normal");
				sessionContainer.setFunIds(getFunction(user.getId(),state));// 装在功能列表
				sessionContainer.setType(user.getType());
				sessionContainer.setUrltype(state+"");
	/*			//获取系统开关
				String switchHql="from SysConfig where type='switch' and 1=1";
				List<SysConfig> switchList=mgr.getList(switchHql);
				Map map=new HashMap();
				if(!"".equals(switchList)&&null!=switchList&&switchList.size()>0){
					for(int i=0;i<switchList.size();i++){
						map.put(switchList.get(i).getAlias(),switchList.get(i).getValue());
					}
				}
				sessionContainer.setMap(map);*/
				session.setAttribute("SessionContainer", sessionContainer);
				request.setAttribute("denglu", "1"); 
				result_login = "1";
				return result_login;
			}else{
				return result_login;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return "0";
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
