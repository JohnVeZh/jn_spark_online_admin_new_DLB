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
import com.easecom.common.util.DateUtils;
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
import com.easecom.system.business.SysNoticeMgr;
import com.easecom.system.business.SysNoticeRecordMgr;
import com.easecom.system.exception.SystemException;
import com.easecom.system.model.SysNotice;


public class SysNoticeAction extends BaseAction{
	 SysNoticeMgr mgr = new SysNoticeMgr();
	 SysNoticeRecordMgr recordMgr = new SysNoticeRecordMgr();
/**
	 * 得到列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
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
			 
			// 接收传值
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			//如果不是超级管理员，查询该当前登录用户所在区域内所有会员
			String shopId = Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'");
			if(!shopId.equals("FFFFFF")){
				queryConds.add(new QueryCond("SysNotice.sysUserId", "String", "=",sessionContainer.getUserId() ));
			}
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			//request.setAttribute("orgId", orgId);
			//request.setAttribute("name", name);
			//request.setAttribute("loginName", loginName);
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
	 * 得到详细信息
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

			request.setAttribute("SysNoticeActionForm", mgr.view(ID));

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
	 * 修改信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SysNoticeActionForm vo = (SysNoticeActionForm) form;
		try {
			SysNoticeActionForm notice = mgr.view(vo.getId());
			notice.setTitle(vo.getTitle());
			notice.setContent(vo.getContent());
			mgr.update(notice);

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
	 * 预修改信息
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

			SysNoticeActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String[] shopIds = vo.getShopIds().split(",");
			List listShop = new ArrayList();
			for (int i = 0; i < shopIds.length; i++) {
				listShop.add(Tool.getValue("select name from shop where id='"+shopIds[i]+"'"));
			}
			
			request.setAttribute("SysNoticeActionForm", vo);
			request.setAttribute("listShop", listShop);
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
	 * 增加信息
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
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
		if(sessionContainer==null){
			sessionContainer = new SessionContainer();
		}
			String[] shopids = request.getParameterValues("sids");
			String sids = "";
			String sid  = "";
			if(shopids!=null && shopids.length>0){
				sids = shopids[0];
				 sid = "'"+shopids[0]+"'";
				for (int i = 1; i < shopids.length; i++) {
					sids = sids+","+shopids[i];
					sid = sid+","+"'"+shopids[0]+"'";
				}
			}
			SysNoticeActionForm vo = (SysNoticeActionForm) form;
			vo.setSysUserId(sessionContainer.getUserId());
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setShopIds(sids);
			SysNotice notice = new SysNotice();
					super.copyProperties(notice, vo);
			String id = mgr.add(notice);
			//发送给个人
			List<Map> userList = new ArrayList<Map>();
			if(vo.getType().equals("0")){
				String shopId = Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'");
				//判断是否为总部人员
				if(!shopId.equals("FFFFFF")){
					userList =  Tool.query("select id from sys_user where id<>'FFFFFF' and shop_id='"+shopId+"'");
				}else{
					userList =  Tool.query("select id from sys_user where id<>'FFFFFF'");
				}
			}else{
				if(!sid.equals("")){
					userList =  Tool.query("select id from sys_user where id <> 'FFFFFF' and shop_id in ("+sid+") ");
				}
			}
			for (Map map : userList) {
				SysNoticeRecordActionForm record = new SysNoticeRecordActionForm();
				record.setSysUserId(map.get("ID").toString());
				record.setState("0");
				record.setSysNoticeId(id);
				recordMgr.add(record);
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

	/**
	 * 预增加信息
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

			SysNoticeActionForm vo = new SysNoticeActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//所属店铺
			List ShopList = new ArrayList();
			String shopId = Tool.getValue("select shop_id from sys_user where id='"+sessionContainer.getUserId()+"'");
			if(sessionContainer.getUserId().equals("FFFFFF")){
				ShopList=Tool.query("select name,id from shop  order by sort asc");
			}else{
				ShopList=Tool.query("select name,id from shop where id='"+shopId+"'  order by sort asc");
			}
			
			//判断是否为总部人员
			if(shopId.equals("FFFFFF")){
				request.setAttribute("isFF", true);
			}else{
				request.setAttribute("isFF", false);
			}
			request.setAttribute("SysNoticeActionForm", vo);
			request.setAttribute("ShopList", ShopList);
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
	 * 删除信息
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
				Tool.execute("delete from sys_notice_record where sys_notice_id='"+ids[i]+"'");
				Tool.execute("delete from sys_notice where id = '"+ids[i]+"'");
				 
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
	
	
}
