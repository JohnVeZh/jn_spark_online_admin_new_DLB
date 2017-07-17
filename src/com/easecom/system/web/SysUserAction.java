/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.system.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.business.SysRoleMgr;
import com.easecom.system.business.SysUserMgr;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysUser;

public class SysUserAction extends BaseAction {

	SysUserMgr mgr = new SysUserMgr();
	SysRoleMgr roleMgr = new SysRoleMgr();
	private static String FFFFFF = "FFFFFF";
	
	/**
	 * 生成组织树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward treelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");

			String orgId = sessionContainer.getOrgId();
			String rootid = "FFFFFF";

			List treelist = null;
			treelist = mgr.getOrgtree(rootid, orgId);

			request.setAttribute("treelist", treelist);
			request.setAttribute("rootid", rootid);
			request.setAttribute("orgid", orgId);

			return mapping.findForward("tree");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取组织树时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 生成组织考核设置树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward checkCfgTreelist(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String rootid = "FFFFFF";

			List treelist = null;
			treelist = mgr.getCheckCfgOrgtree(rootid);

			request.setAttribute("treelist", treelist);
			request.setAttribute("rootid", rootid);

			return mapping.findForward("checkcfgtree");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取组织树时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 组织部门考核设置列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward checkCfgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String orgId = ParamUtils.getParameter(request, "orgId", true);
			String cfgDate = ParamUtils.getParameter(request, "cfgDate", true);

			request.setAttribute("orgId", orgId);
			request.setAttribute("cfgDate", cfgDate);

			return mapping.findForward("checkcfglist");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 得到用户列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request,
					"currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request,
					"totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils
					.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
						15);
			}
			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			String orgId = ParamUtils.getParameter(request, "orgId", true);
			// 接收传值
			String name = ParamUtils.getParameter(request, "name", false);
			String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			queryConds.add(new QueryCond("user.sysOrg.id", "String", "=", orgId));
			queryConds.add(new QueryCond("user.name", "String", "like", name));
			queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			String fieldname = ParamUtils.getParameter(request, "fieldname",
					true);
			String op = ParamUtils.getParameter(request, "op", true);
			String fieldvalue = ParamUtils.getParameter(request, "fieldvalue",
					true);
			queryConds.add(new QueryCond(fieldname, "String", op, fieldvalue));
			
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("orgId", orgId);
			request.setAttribute("name", name);
			request.setAttribute("loginName", loginName);
			request.setAttribute("lc", lc);
			 
			return mapping.findForward("list");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 
	 * 方法功能说明：  设计师模块的查询显示
	 * 创建：2015年12月17日 by Zzc   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 * @参数： @param mapping
	 * @参数： @param form
	 * @参数： @param request
	 * @参数： @param response
	 * @参数： @return      
	 * @return ActionForward     
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ActionForward designerlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request,
					"currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request,
					"totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils
					.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
						15);
			}
			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			// 接收传值
			String name = ParamUtils.getParameter(request, "name", false);
			String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
		 
			queryConds.add(new QueryCond("user.name", "String", "like", name));
			queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			
			//当前登录人员权限条件
			String shopId = Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'");
			if(!shopId.equals("FFFFFF")){
				queryConds.add(new QueryCond("user.shopId", "String", "=", shopId));
			}
			
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("name", name);
			request.setAttribute("loginName", loginName);
			request.setAttribute("lc", lc);
			 
			return mapping.findForward("designerlist");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 得到用户详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String ID = ParamUtils.getParameter(request, "id", true);

			request.setAttribute("SysUserForm", mgr.view(ID));

			return mapping.findForward("view");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	 

	/**
	 * 修改用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SysUserForm vo = (SysUserForm) form;
		try {
			mgr.update(vo);

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 加密
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author 李来源
	 * Aug 10, 201310:46:44 AM
	 */
	public ActionForward MD5ToStr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String pwd = ParamUtils.getParameter(request, "pwd", true);
			String md5 = MD5.getInstance().getMD5ofStr(pwd);
			if(md5!=null)
				md5 = md5.toLowerCase();
			Map map = new HashMap();
			map.put("md5",md5);
			JsonUtils.outputJsonResponse(response, map);
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	
//	/**
//	 * 修改用户信息
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public ActionForward mainUpdate(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		SysUserForm vo = (SysUserForm) form;
//
//		try {
//
//			mgr.update(vo);
//
//			request.setAttribute("successFlag", "1");
//
//			return mapping.findForward("mainupdate");
//		} catch (Exception ex) {
//
//			log.error(ex.getMessage(), ex);
//			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
//					"返回", "javascript:window.history.back()");
//			request.setAttribute("DialogBox", dialog);
//			return mapping.findForward("DialogBox");
//		}
//	}
	
	/**
	 * 修改所有用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward mainUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SysUserForm vo = (SysUserForm) form;
		String a="1";
		try {
			//根据ID获取用户，判断原密码是否正确
			String id=vo.getId();
			SysUser sysUser=(SysUser)mgr.loadObject(SysUser.class, id);
			MD5 md5=MD5.getInstance();
			if(md5.getMD5ofStr(vo.getPasswordold()).toLowerCase().equals(sysUser.getPassword())){
				//密码正确
				sysUser.setPassword(md5.getMD5ofStr(vo.getPasswordnew()).toLowerCase());
				mgr.update(sysUser);
			}else{
				//密码不正确
				a="0";
			}
			request.setAttribute("successFlag",a);
			return mapping.findForward("mainupdate");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 预修改用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);

			SysUserForm vo = mgr.view(ID);
			vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("SysUserForm", vo);
			
			String shopId = Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'");
			 String shopOpen = "";
			if(shopId.equals("FFFFFF")){
			  	shopOpen = Tool.getList("select id,name from shop ", "name", "id",vo.getShopId());
			}else{
				shopOpen = Tool.getList("select id,name from shop where id='"+shopId+"'", "name", "id");
			}
			request.setAttribute("shopOpen", shopOpen);
			
			
			return mapping.findForward("update");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 预修改所有用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preMainUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "id", true);
			
			SysUserForm vo = mgr.view(ID);

			request.setAttribute("SysUserForm", vo);

			return mapping.findForward("mainupdate");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 增加用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			SysUserForm vo = (SysUserForm) form;
			log.info("======================="+vo.getOrgName());
			vo.setType("1");
			mgr.add(vo);

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 预增加用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			SysUserForm vo = new SysUserForm();
			String orgId = ParamUtils.getParameter(request, "orgId", true);
			String orgName = Tool
					.getValue("select name from sys_org where id='" + orgId
							+ "'");

			vo.setOrgId(orgId);
			vo.setOrgName(orgName);
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("SysUserForm", vo);

			String shopId = Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'");
			 String shopOpen = "";
			if(shopId.equals("FFFFFF")){
			  	shopOpen = Tool.getList("select id,name from shop ", "name", "id");
			}else{
				shopOpen = Tool.getList("select id,name from shop where id='"+shopId+"'", "name", "id");
			}
			request.setAttribute("shopOpen", shopOpen);
			return mapping.findForward("add");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}


	/**
	 * 禁用、启用
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			mgr.delete(ids);

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	/**
	 * 删除用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				if(!"FFFFFF".equals(ids[i])){
					Tool.execute("delete from sys_user where id = '"+ids[i]+"'");
				}
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	// ////////////////////////////////////////////////////////////
	//
	// 以下是给用户分配角色
	//
	// ////////////////////////////////////////////////////////////

	/**
	 * 得到角色列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward roleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
			sessionContainer=new SessionContainer();
		try {

			String userIds = "";
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			
			String state = ParamUtils.getParameter(request, "state", "0");

			if (ids.length == 1) {
				userIds = ParamUtils.getParameter(request, "userIds", false);
			} else {
				for (int i = 0; i < ids.length; i++) {
					userIds += ids[i] + ",";
				}
				userIds = userIds.substring(0, userIds.length() - 1);
			}

			int currentPageInt = ParamUtils.getIntParameter(request,
					"currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request,
					"totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils
					.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
						15);
			}
			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

			// 设置查询条件
			Collection queryConds = new ArrayList();
			if(!"FFFFFF".equals(sessionContainer.getUserId())){
				queryConds.add(new QueryCond("role.shopId", "String", "=",Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'")));
			//	queryConds.add(new QueryCond("role.type", "String", "=","0"));
			}
			request.setAttribute("state",state);
			//
			//queryConds.add(new QueryCond("role.state", "String", "=",state));
			ListContainer lc = roleMgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			// 设置request.attribute
			request.setAttribute("userIds", userIds);
			request.setAttribute("lc", lc);
			request.setAttribute("state",state);
			if("0".equals(state)){
				//如果是o 管理员
				request.setAttribute("orgId", mgr.view(userIds.split(",")[0])
						.getOrgId());
				
				return mapping.findForward("rolelist");
			}else{
				//社区管理员 shop表
				request.setAttribute("orgId",Tool.getValue("select id from shop where id = '"+userIds.split(",")[0]+"'"));
				
				return mapping.findForward("srolelist");
			}
				
			

			// 返回list页
			
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取角色列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 保存角色配置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveRoleCfg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
		//	String state = ParamUtils.getParameter(request, "state", "");
			String userIds = ParamUtils.getParameter(request, "userIds", true);
			String id = ParamUtils.getParameter(request, "id", true);
			String[] userid = userIds.split(",");

			for (int i = 0; i < userid.length; i++) {
				mgr.saveRoleCfg(userid[i], id);
			}

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "保存角色配置时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 查询没有分配的那些企业用户
	 * 
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public ActionForward assign(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			// 此处的orgId为点击左边树时的orgId
			String orgId = String.valueOf(request.getParameter("orgId"));
			SysUserForm form = (SysUserForm) actionform;
			String userid = "";

			if (form.getUserid() == null) {
				userid = ParamUtils.getParameter(request, "id", true);
			} else {
				userid = form.getUserid();
			}

			int currentPageInt = ParamUtils.getIntParameter(request,
					"currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request,
					"totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils
					.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
						15);
			}

			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

			// 设置查询条件
			String loginName = ParamUtils.getParameter(request, "loginName",
					true);
			String email = ParamUtils.getParameter(request, "email", true);
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("user.loginName", "String", "like",
					loginName));
			queryConds
					.add(new QueryCond("user.email", "String", "like", email));

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			String topOrgId = "";

			if (userid.equalsIgnoreCase("0")) {

				// 此处的topOrgId为管理员登陆时所在父组织的ID
				topOrgId = sessionContainer.getTopOrgId();
			} else {
				topOrgId = mgr.view(userid).getOrgId();
			}

			queryConds.add(new QueryCond("user.sysOrg.id", "String", "=",
					topOrgId));
			ListContainer lc = mgr.assignList(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("lc", lc);
			request.setAttribute("userid", userid);
			request.setAttribute("orgId", orgId);
			request.setAttribute("loginName", loginName);
			request.setAttribute("email", email);

			return mapping.findForward("enterpriselist");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "查询未分配企业用户时出错",
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	 

	/**
	 * 查看企业用户
	 * 
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward selectassign(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String orgId = String.valueOf(request.getParameter("orgId"));

			SysUserForm form = (SysUserForm) actionform;
			String userid = "";

			if (form.getUserid() == null) {
				userid = ParamUtils.getParameter(request, "userid", true);
			} else {
				userid = form.getUserid();
			}

			int currentPageInt = ParamUtils.getIntParameter(request,
					"currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request,
					"totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils
					.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem",
						15);
			}

			String action = ParamUtils
					.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

			// 设置查询条件
			Collection queryConds = new ArrayList();

			String loginName = ParamUtils.getParameter(request, "loginName",
					true);
			String email = ParamUtils.getParameter(request, "email", true);

			queryConds.add(new QueryCond("user.loginName", "String", "like",
					loginName));
			queryConds
					.add(new QueryCond("user.email", "String", "like", email));

			ListContainer lc = mgr.selectlist(queryConds, currentPageInt,
					itemsInPage, action, jumpPage, userid, orgId);

			request.setAttribute("lc", lc);
			request.setAttribute("userid", userid);
			request.setAttribute("orgId", orgId);
			request.setAttribute("loginName", loginName);
			request.setAttribute("email", email);

			return mapping.findForward("selectuser");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "查看企业用户时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	 

 
	/**
	 * 得到用户详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userManagerView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");

			String typeUser = sessionContainer.getStyleUser();// 判断是企业用户还是国税用户
			String userId = sessionContainer.getUserId();

			if ("qy".equals(typeUser)) {
				//request.setAttribute("SysUserForm", mgr.qyUserView(userId));
			} else {
				request.setAttribute("SysUserForm", mgr.view(userId));
			}

			return mapping.findForward("userManagerView");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 保存修改用户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userManagerUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		SysUserForm vo = (SysUserForm) form;

		try {

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");

			String typeUser = sessionContainer.getStyleUser();

			mgr.userManagerUpdate(vo, typeUser);

			request.setAttribute("successFlag", "1");

			return mapping.findForward("homepage");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	 

	 
 

	 
	/**
	 * 验证是否有此企业用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void validateuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String loginName = request.getParameter("loginName");
			String loginname = Tool
					.getValue("Select login_name from sys_comp_user where login_name='"
							+ loginName + "'");

			boolean boo = false;
			if (null != loginname && loginname.length() > 0) {
				boo = true;
			}

			response.setCharacterEncoding("UTF-8");

			PrintWriter writer = response.getWriter();
			writer.write(String.valueOf(boo));

			writer.flush();
			writer.close();
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
		}
	}

	 

	 

	/**
	 * 
	 * @Description: 获取部门
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 * @author: 窦恩虎
	 * @date Dec 12, 2011 10:07:17 AM
	 */
	public ActionForward getOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SystemException, Exception {
		String backMark = ParamUtils.getParameter(request, "backMark", true);
		request.setAttribute("backMark", backMark);
		String rootid = "FFFFFF";
		String orgId = "FFFFFF";
		List treelist = null;
		treelist = mgr.getOrg(rootid,orgId);
		request.setAttribute("nodes", treelist);

		return mapping.findForward("getorg");
	}
/**
 * 
 * @Description:  导出EXCel文件
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception  
 * @author: 窦恩虎
 * 
 * @date Dec 12, 2011 10:49:14 AM
 */
	public ActionForward importExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title[] = {"姓名",	"登录密码",	"性别",	"文化程度",	"参加工作时间",	"出生日期",	"年龄",	"职务",	"电话号码",	"邮箱", "家庭地址",	"备注"};
		SysUserForm vo = (SysUserForm) form;
		String orgId = ParamUtils.getParameter(request, "orgId", true);
		try {
			List<String[]> list = mgr.importExcel(vo.getFile().getInputStream(),
					orgId, title);
		
			if (list.size() < 1) {
				request.setAttribute("returnInfo", "用户资料导入Excel完成!");
				return mapping.findForward("importExcel");

			} else {
				String tableData [][] = new String [list.size()+1][];
				
				tableData [0] = title;
				for(int i=0;i<list.size();i++){
					tableData[i+1] = (String[])list.get(i);
				}
				String fileName = "无法导入的用户资料.xls";
				fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
				ExcelUtils.exportExcelData("错误的用户资料", tableData, fileName,
						request, response);

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			WebDialogBox dialog = new WebDialogBox(1, "错误", e.getMessage(), "返回",
					"javascript:window.history.back();");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}

		return null;

	}
/**
 * 
 * @Description: 下载Excel模板 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception  
 * @author: 窦恩虎
 * @date Dec 12, 2011 10:49:36 AM
 */
	public ActionForward downloadExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			String fileName = "批量导入用户Excel模板.xls"; // 文件名，输出到用户的下载对话框
			String filePath = request.getRealPath("/") + "/uploadfiles/" + fileName;
			// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
			fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
			// 打开指定文件的流信息
			FileInputStream fs = null;
			fs = new FileInputStream(new File(filePath));
			// 设置响应头和保存文件名
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 写出流信息
			int b = 0;
			PrintWriter out = response.getWriter();
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
			fs.close();
			out.close();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误",
					"下载批量导入用户Excel模板下载时出错", "返回",
					"javascript:window.history.back()");
			ex.printStackTrace();
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		return null;

	}
	
	
	
	 
	/**
	 * 密码重置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward reqPsw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String id=ParamUtils.getParameter(request, "id");
			SysUser sysUser=(SysUser)mgr.loadObject(SysUser.class, id);
			MD5 md5=MD5.getInstance();
			sysUser.setPassword(md5.getMD5ofStr("123456").toLowerCase());
			mgr.update(sysUser);
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			return mapping.findForward("DialogBox");
		}
		
	}

}