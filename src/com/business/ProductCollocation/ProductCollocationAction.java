package com.business.ProductCollocation; 


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
import com.easecom.common.util.AddZero;
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
import com.easecom.system.exception.SystemException;
import com.business.ProductCollocation.ProductCollocation;
import com.business.ProductCollocation.ProductCollocationActionForm;
import com.business.ProductCollocation.ProductCollocationMgr;

public class ProductCollocationAction extends BaseAction{
	 ProductCollocationMgr mgr = new ProductCollocationMgr();

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
			String productFid = ParamUtils.getParameter(request, "productFid", false);
			String type = ParamUtils.getParameter(request, "type", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("ProductCollocation.productFid", "String", "=", productFid));
			queryConds.add(new QueryCond("ProductCollocation.isDel", "number", "=", "0"));
			 
			//
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("productFid", productFid);
			request.setAttribute("type", type);
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

			request.setAttribute("ProductCollocationActionForm", mgr.view(ID));

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

		ProductCollocationActionForm vo = (ProductCollocationActionForm) form;
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

			ProductCollocationActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductCollocationActionForm", vo);

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
			ProductCollocationActionForm vo = (ProductCollocationActionForm) form;			  
			//vo.setType("1");
			vo.setMoney(Double.parseDouble(AddZero.format(vo.getMoney(), 2)));
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
	 * 保存套餐信息
	 * 方法功能说明：  
	 * 创建：2016年5月26日 by Zzc   
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
	public ActionForward collAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			Map map = new HashMap();
		try {
			ProductCollocationActionForm vo = (ProductCollocationActionForm) form;
			ProductCollocation pc = (ProductCollocation)mgr.getObjectByHql("from ProductCollocation where productId='"+vo.getProductId()+"' and productFid='"+vo.getProductFid()+"'");
			if(null == pc){
				vo.setCreatetime(DateUtils.getCurrDateTimeStr());
				mgr.add(vo);
				map.put("result", true);
			}else{
				map.put("result", false);
			}
			JsonUtils.outputJsonResponse(response, map);
		} catch (Exception ex) {
			map.put("result", false);
			JsonUtils.outputJsonResponse(response, map);
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		return null;
	}
	/**
	 * 修改搭配商品金额
	 * 方法功能说明：  
	 * 创建：2016年5月27日 by Zzc   
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
	public ActionForward editMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			Map map = new HashMap();
		try {
			ProductCollocationActionForm vo = (ProductCollocationActionForm) form;
			ProductCollocationActionForm colle = mgr.view(vo.getId());
			if(null != colle){
				colle.setMoney(Double.parseDouble(AddZero.format(vo.getMoney(), 2)));
				colle.setSort(colle.getSort());
				mgr.update(colle);
			}
			map.put("result", true);
			JsonUtils.outputJsonResponse(response, map);
		} catch (Exception ex) {
			map.put("result", false);
			JsonUtils.outputJsonResponse(response, map);
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		return null;
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

			ProductCollocationActionForm vo = new ProductCollocationActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductCollocationActionForm", vo);
			
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
	 * 预修改套餐金额
	 * 方法功能说明：  
	 * 创建：2016年5月27日 by Zzc   
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
	public ActionForward preEditMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			
			String ID = ParamUtils.getParameter(request, "cId", true);
			ProductCollocationActionForm vo = mgr.view(ID);		
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductCollocationActionForm", vo);
			
			return mapping.findForward("preEditMoney");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 保存套餐跳转
	 * 方法功能说明：  
	 * 创建：2016年5月26日 by Zzc   
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
	public ActionForward preMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			ProductCollocationActionForm vo = new ProductCollocationActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String productId = ParamUtils.getParameter(request, "productId", true);
			String productFid = ParamUtils.getParameter(request, "productFid", true);
			String type = ParamUtils.getParameter(request, "type", true);
			String cType = ParamUtils.getParameter(request, "cType", true);
			
			request.setAttribute("productId", productId);
			request.setAttribute("productFid", productFid);
			request.setAttribute("type", type);
			request.setAttribute("cType", cType);
			
			return mapping.findForward("preMoney");
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
				 
			Tool.execute("update product_collocation set is_del = 1 where id = '"+ids[i]+"'");
				 
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
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "cId", true);
			Tool.execute("update product_collocation set is_del = 1 where id = '"+ID+"'");
				 
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
