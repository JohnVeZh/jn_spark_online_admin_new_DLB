/**
 * @(#)$CurrentFile
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.easecom.system.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easecom.common.framework.struts.BaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.business.SysFunctionMgr;

/**
 * MyEclipse Struts Creation date: 01-10-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/system/sysFunction" name="SysFunctionForm"
 *                parameter="act" scope="request" validate="true"
 */
public class SysFunctionAction extends BaseAction {

	/*
	 * Generated Methods
	 */
	SysFunctionMgr mgr = new SysFunctionMgr();

	/**
	 * 生成功能树
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
			String rootid = "FFFFFF";

			List treelist = null;
			treelist = mgr.getFuntree(rootid);

			request.setAttribute("treelist", treelist);
			request.setAttribute("rootid", rootid);
			return mapping.findForward("tree");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能树时出错", "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 得到功能列表
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

		try {

			int currentPageInt = ParamUtils
					.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem",
					false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(
					request, "RowCountPerPage"));

			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}  

			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

			String parentid = ParamUtils.getParameter(request, "parentid", true);

			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("function.sysFunction.id", "String", "=",
					parentid));
			// 查询
			ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage,
					action, jumpPage);

			// 设置request.attribute
			request.setAttribute("parentid", parentid);
			request.setAttribute("lc", lc);
			

			// 返回list页
			return mapping.findForward("list");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能列表时出错", "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 预增加功能
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
			String state = ParamUtils.getParameter(request, "state", "0");
			SysFunctionForm vo = new SysFunctionForm();

			String parentid = ParamUtils.getParameter(request, "parentid", true);

			vo.setParentid(parentid);
			vo.setParentcode(mgr.getCodeById(parentid));
			vo.setCode(mgr.getNewCodeByparentid(parentid));
			vo.setSort(new Long(mgr.getNewSortByParentid(parentid)));

			// 保存到request中
			request.setAttribute("SysFunctionForm", vo);
			request.setAttribute("state",state);

			return mapping.findForward("add");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "增加功能时出错", "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 添加功能
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SysFunctionForm vo = (SysFunctionForm) form;

		try {

			mgr.add(vo);

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}

	}

	/**
	 * 预修改功能
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

			String id = ParamUtils.getParameter(request, "id", true);

			SysFunctionForm vo = mgr.view(id);
			request.setAttribute("SysFunctionForm", vo);

			return mapping.findForward("update");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "获得修改页面出错", "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 修改功能
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SysFunctionForm vo = (SysFunctionForm) form;

		try {

			mgr.update(vo);

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 查看详细信息
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

			String id = ParamUtils.getParameter(request, "id", true);

			SysFunctionForm vo = mgr.view(id);

			String parentid = ParamUtils.getParameter(request, "parentid", true);

			vo.setParentcode(mgr.getCodeById(parentid));

			request.setAttribute("SysFunctionForm", vo);

			return mapping.findForward("view");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);

			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取详细信息页面时出错", "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 删除功能
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

			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回",
					"javascript:window.history.back()");

			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 生成主界面功能树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward funTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String rootid = "FFFFFF";

			SessionContainer sc = (SessionContainer) request.getSession()
					.getAttribute("SessionContainer");
			request.setAttribute("treelist", mgr.getFunTree(sc.getUserId(), rootid));

			request.setAttribute("rootid", rootid);

			return mapping.findForward("funtree");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取功能树时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);

			return mapping.findForward("DialogBox");
		}
	}
}