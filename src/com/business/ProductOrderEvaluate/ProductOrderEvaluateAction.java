package com.business.ProductOrderEvaluate; 


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
import com.easecom.system.exception.SystemException;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.ProductOrderEvaluate.ProductOrderEvaluate;
import com.business.ProductOrderEvaluate.ProductOrderEvaluateActionForm;
import com.business.ProductOrderEvaluate.ProductOrderEvaluateMgr;

public class ProductOrderEvaluateAction extends BaseAction{
	 ProductOrderEvaluateMgr mgr = new ProductOrderEvaluateMgr();

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
//			String id = ParamUtils.getParameter(request, "id", false);
//			String userId = ParamUtils.getParameter(request, "userId", false);
			// 设置查询条件
			
			Collection queryConds = new ArrayList();
			
			 
			//
			//queryConds.add(new QueryCond("ProductOrderEvaluate.userId", "String", "=", userId));
			queryConds.add(new QueryCond("ProductOrderEvaluate.isDel", "String", "=", "0"));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
		   List<ProductOrderEvaluate> list=lc.getList();
		   List<Map> lm=new ArrayList<Map>();
		   for(ProductOrderEvaluate poe:list ){
			   Map m=new HashMap();
			   m.put("id", poe.getId());
			   m.put("createtime", poe.getCreatetime());
			   m.put("averageScore", poe.getAverageScore());
			   m.put("isDel", poe.getIsDel());
			   m.put("productId",poe.getProductId());
			   m.put("pName", Tool.getValue("select p_name from product where id='"+poe.getProductId()+"'"));
			   m.put("podId", poe.getPodId());
			   m.put("thinkingScore", poe.getThinkingScore());
			   m.put("userId", poe.getUserId());
			   m.put("userName", Tool.getValue("select username from users where id='"+poe.getUserId()+"'"));
			   m.put("curriculumScore", poe.getCurriculumScore());
			   m.put("styleScore", poe.getStyleScore());
			   if(null != poe.getContent() &&"".equals(poe.getContent())){
				   String blobString = new String(poe.getContent().getBytes(1, (int) poe.getContent().length()),"utf-8");//blob 转 String
					m.put("content", blobString);
			   }
			   lm.add(m);
		   }
		   lc.setList(lm);
		//	request.setAttribute("userId", userId);
			//request.setAttribute("name", name);
			//request.setAttribute("loginName", loginName);
			request.setAttribute("lc", lc);
			//request.setAttribute("lm", lm);
			 
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
	 * 订单相关评价
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward comment(ActionMapping mapping, ActionForm form,
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
			String id = ParamUtils.getParameter(request, "id", false);
			String userId = ParamUtils.getParameter(request, "userId", false);
			// 设置查询条件
			
			Collection queryConds = new ArrayList();
			
			 
			//
			queryConds.add(new QueryCond("ProductOrderEvaluate.userId", "String", "=", userId));
			queryConds.add(new QueryCond("ProductOrderEvaluate.isDel", "String", "=", "0"));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

		   List<ProductOrderEvaluate> list=lc.getList();
		   List<Map> lm=new ArrayList<Map>();
		   for(ProductOrderEvaluate poe:list ){
			   Map m=new HashMap();
			   m.put("id", poe.getId());
			   m.put("createtime", poe.getCreatetime());
			   m.put("averageScore", poe.getAverageScore());
			   m.put("isDel", poe.getIsDel());
			   m.put("productId",poe.getProductId());
			   m.put("pName", Tool.getValue("select p_name from product where id='"+poe.getProductId()+"'"));
			   m.put("podId", poe.getPodId());
			   m.put("thinkingScore", poe.getThinkingScore());
			   m.put("userId", poe.getUserId());
			   m.put("userName", Tool.getValue("select username from users where id='"+poe.getUserId()+"'"));
			   m.put("curriculumScore", poe.getCurriculumScore());
			   m.put("styleScore", poe.getStyleScore());
			   if(null != poe.getContent() &&"".equals(poe.getContent())){
				   String blobString = new String(poe.getContent().getBytes(1, (int) poe.getContent().length()),"utf-8");//blob 转 String
					m.put("content", blobString);
			   }
			   lm.add(m);
		   }
			request.setAttribute("userId", userId);
			//request.setAttribute("name", name);
			//request.setAttribute("loginName", loginName);
			request.setAttribute("lc", lc);
			request.setAttribute("lm", lm);
			 
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
			ProductOrderEvaluateActionForm vo = mgr.view(ID);
			if(null != vo.getContent() &&"".equals(vo.getContent())){
				   String blobString = new String(vo.getContent().getBytes(1, (int) vo.getContent().length()),"utf-8");//blob 转 String
				   request.setAttribute("blobString", blobString);
			}else{
				  request.setAttribute("blobString", "");
			}
			String product=Tool.getValue("select p_name from product where id='"+vo.getProductId()+"'");
	           // System.out.println(product);
		    request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
			request.setAttribute("product", product);
			request.setAttribute("ProductOrderEvaluateActionForm", vo);

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

		ProductOrderEvaluateActionForm vo = (ProductOrderEvaluateActionForm) form;
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

			ProductOrderEvaluateActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String product=Tool.getValue("select p_name from product where id='"+vo.getProductId()+"'");
           // System.out.println(product);
			request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
			request.setAttribute("product", product);
			request.setAttribute("ProductOrderEvaluateActionForm", vo);

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
			ProductOrderEvaluateActionForm vo = (ProductOrderEvaluateActionForm) form;			  
			//vo.setType("1");
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
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

			ProductOrderEvaluateActionForm vo = new ProductOrderEvaluateActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductOrderEvaluateActionForm", vo);
			
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
				 
			Tool.execute("delete from product_order_evaluate where id = '"+ids[i]+"'");
				 
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

			String ID = ParamUtils.getParameter(request, "eId", true);
				 
			Tool.execute("delete from product_order_evaluate where id = '"+ID+"'");
				 
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
