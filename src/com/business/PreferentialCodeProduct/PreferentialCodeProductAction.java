package com.business.PreferentialCodeProduct; 


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
import com.business.PreferentialCodeProduct.PreferentialCodeProduct;
import com.business.PreferentialCodeProduct.PreferentialCodeProductActionForm;
import com.business.PreferentialCodeProduct.PreferentialCodeProductMgr;

public class PreferentialCodeProductAction extends BaseAction{
	 PreferentialCodeProductMgr mgr = new PreferentialCodeProductMgr();

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
			String pcId = ParamUtils.getParameter(request, "pcId", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("PreferentialCodeProduct.pcId", "String", "=", pcId));
			queryConds.add(new QueryCond("PreferentialCodeProduct.isDel", "String", "=", "0"));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			
			request.setAttribute("pcId", pcId);
			request.setAttribute("lc", lc);
			List<PreferentialCodeProduct> pcpList = lc.getList();
			List<Map> lm = new ArrayList<Map>();
			for (PreferentialCodeProduct pcp : pcpList) {
				Map m = new HashMap();
				m.put("id", pcp.getId());
				m.put("pId", pcp.getProductId());
				if(pcp.getType()==0){
					m.put("pName", Tool.getValue("select p_name from product where id='"+pcp.getProductId()+"'"));
				}else{
					m.put("pName", Tool.getValue("select network_name from Network_video where id='"+pcp.getProductId()+"'"));
				}
				m.put("createtime", pcp.getCreatetime());
				m.put("type", pcp.getType());
				if(pcp.getType()==0){
					m.put("typeName", "图书");
				}else{
					m.put("typeName", "网课");
				}
				lm.add(m);
			}
			request.setAttribute("lm", lm);
			request.setAttribute("lmSize", lm.size());
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

			request.setAttribute("PreferentialCodeProductActionForm", mgr.view(ID));

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
		Map<String, Object> map = new HashMap<String, Object>();
		PreferentialCodeProductActionForm vo = (PreferentialCodeProductActionForm) form;
		try {
			mgr.update(vo);

			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
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

			PreferentialCodeProductActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("PreferentialCodeProductActionForm", vo);

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
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PreferentialCodeProductActionForm vo = (PreferentialCodeProductActionForm) form;			  
			mgr.add(vo);
			
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	//绑定图书商品
	public ActionForward addProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String pcId = ParamUtils.getParameter(request, "pcId", true);
			String pId = ParamUtils.getParameter(request, "pId", true);
			String type = ParamUtils.getParameter(request, "type", true);
			PreferentialCodeProductActionForm vo = new PreferentialCodeProductActionForm();
			vo.setPcId(pcId);
			vo.setProductId(pId);
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setType(Integer.parseInt(type));
			mgr.add(vo);
			
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	/**
	 * 批量绑定
	 * 方法功能说明：  
	 * 创建：2016年7月29日 by Zzc   
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
	public ActionForward addProducts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String pcId = ParamUtils.getParameter(request, "pcId", true);
			String pId = ParamUtils.getParameter(request, "pId", true);
			String type = ParamUtils.getParameter(request, "type", true);
			String ids[] = pId.split(",");
			for (int i = 0; i < ids.length; i++) {
				PreferentialCodeProductActionForm vo = new PreferentialCodeProductActionForm();
				vo.setPcId(pcId);
				vo.setProductId(ids[i]);
				vo.setCreatetime(DateUtils.getCurrDateTimeStr());
				vo.setType(Integer.parseInt(type));
				mgr.add(vo);
			}
			
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
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

			PreferentialCodeProductActionForm vo = new PreferentialCodeProductActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("PreferentialCodeProductActionForm", vo);
			
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
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String[] ids = ParamUtils.getParameter(request, "idVal", true).split(",");
			for (int i = 0; i < ids.length; i++) {
				 
			Tool.execute("delete from preferential_code_product where id = '"+ids[i]+"'");
				 
			}
			map.put("result", true);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	/**
	 * 单个删除
	 * 方法功能说明：  
	 * 创建：2016年7月27日 by Zzc   
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
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String idVal = ParamUtils.getParameter(request, "idVal", true);
			Tool.execute("delete from preferential_code_product where id = '"+idVal+"'");
			
			
			map.put("result", true);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
