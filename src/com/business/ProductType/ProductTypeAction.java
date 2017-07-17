package com.business.ProductType; 


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.IpAddressUtil;
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
import com.business.News.NewsActionForm;
import com.business.ProductType.ProductType;
import com.business.ProductType.ProductTypeActionForm;
import com.business.ProductType.ProductTypeMgr;

public class ProductTypeAction extends BaseAction{
	 ProductTypeMgr mgr = new ProductTypeMgr();

	 
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
			String parentid = ParamUtils.getParameter(request, "parentid", true);
			String sort = ParamUtils.getParameter(request, "StatrSel", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("ProductType.parentId", "String", "=", parentid));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage,sort);

			request.setAttribute("lc", lc);
			request.setAttribute("parentid", parentid);
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
			ProductTypeActionForm vo = mgr.view(ID);
			request.setAttribute("ProductTypeActionForm",vo );
			request.setAttribute("phonePath", vo.getImgPhone());
			request.setAttribute("padPath", vo.getImgPad());

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

		ProductTypeActionForm vo = (ProductTypeActionForm) form;
		try {
			
			mgr.update(vo);
			
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改商品类型,类型名称:"+vo.getTypeName(), "1", ipaddress);
			
			
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
			redirect.addParameter("parentid", vo.getParentId());
			return redirect;
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

			ProductTypeActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductTypeActionForm", vo);
			request.setAttribute("phonePath", vo.getImgPhone());
			request.setAttribute("padPath", vo.getImgPad());
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
			ProductTypeActionForm vo = (ProductTypeActionForm) form;			  
			//vo.setType("1");
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setSysUserId(sessionContainer.getUserId());
			mgr.add(vo);

			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"添加商品类型,类型名称:"+vo.getTypeName(), "1", ipaddress);
			
			
			
			
			//return list(mapping, form, request, response);
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
			redirect.addParameter("parentid", vo.getParentId());
			return redirect;
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

			String parentid = ParamUtils.getParameter(request, "parentid", true);

			ProductTypeActionForm vo = new ProductTypeActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("ProductTypeActionForm", vo);
			request.setAttribute("parentid", parentid);
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

			
			
			
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			
			
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				 
				String name = Tool.getValue("select TYPE_NAME from  product_type  where id='"+ids[i]+"'");
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除商品类型,类型名称:"+name, "1", ipaddress);
				
				
			Tool.execute("delete from product_type where id = '"+ids[i]+"'");
				 
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
	 * 异步上传图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ProductTypeActionForm vo = (ProductTypeActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String type = ParamUtils.getParameter(request, "type", true);
				String value = "";
					value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.PRODUCT_TYPE_ICON_PATH_PHONEIMG,request);
			  
			  map.put("result", true);
			  map.put("imgPath", value);
			  JsonUtils.outputJsonResponse(response, map);
			}
		} catch (Exception ex) {
			  map.put("result", false);
			  JsonUtils.outputJsonResponse(response, map);
		}
		return null;
	}
	
}
