package com.easecom.system.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.AddZero;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.business.SysConfigMgr;
import com.easecom.system.model.SysConfig;

public class SysConfigAction extends BaseAction{

	SysConfigMgr mgr = new SysConfigMgr();
	
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
		//类型
		String type = ParamUtils.getParameter(request, "type", true);
		//是否具有增加、删除功能；是:true 否：false
		String rs = ParamUtils.getParameter(request, "rs", true);
		//具体模块；例如：图片轮播：Imgs 不带编辑器：value  编辑器：ue
		String modular = ParamUtils.getParameter(request, "modular", true);
		
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
			 
			// 设置查询条件
			Collection queryConds = new ArrayList();
			ListContainer lc = new ListContainer();
			if(null != type && !"".equals(type)){
				queryConds.add(new QueryCond("sc.type", "String", "=",type ));
				 lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			}
			
			request.setAttribute("lc", lc);
			request.setAttribute("type", type);
			request.setAttribute("rs", rs);
			request.setAttribute("modular", modular);
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

			request.setAttribute("SysConfigForm", mgr.view(ID));

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

		SysConfigForm vo = (SysConfigForm) form;
		try {
//			System.out.println(vo.getAlias());
			String modular = ParamUtils.getParameter(request, "modular", true);
			String rs = ParamUtils.getParameter(request, "rs", true);
			 if(vo.getType().equals("TOP_DISCOUNT") || vo.getType().equals("TOP_CONPANY")||vo.getType().equals("REFRESH_PRICE"))
				 vo.setValue(AddZero.format(vo.getValue(), 2));
				 
			// 当含有跳转类型时
			if(StringUtils.isNotBlank(vo.getJumpType())) {
				String jt = vo.getJumpType();
				if("11".equals(jt) || "12".equals(jt) || "13".equals(jt) || "14".equals(jt)) {
					vo.setValue(vo.getValueId());
				}
			}
				
			mgr.update(vo);
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
			redirect.addParameter("type", vo.getType());
			redirect.addParameter("modular", modular);
			redirect.addParameter("rs", rs);
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
		  String map=null;
		try {

			String ID = ParamUtils.getParameter(request, "id", true);
			String rs = ParamUtils.getParameter(request, "rs", true);
			SysConfigForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			String type = ParamUtils.getParameter(request, "type", true);
			String modular = ParamUtils.getParameter(request, "modular", true);
			
			request.setAttribute("SysConfigForm", vo);
			request.setAttribute("type", type);
			request.setAttribute("rs", rs);
			request.setAttribute("modular", modular);

			if(type.equals("NETWORK_CAROUSEL")){
				if(null != vo){
					String nkName = Tool.getValue("select network_name from network_video where id='"+vo.getValue()+"'");
					request.setAttribute("nkName", nkName);
				}
			}else if(type.equals("NETWORK_COURSE_BANNER")){
				if(null != vo){
					String nkName = Tool.getValue("select nc_name from network_course where id='"+vo.getValue()+"'");
					request.setAttribute("nkName", nkName);
				}
			}
			
			if(modular.equals("imgs")){
//				vo.setAlias(DictionaryUtils.WEB_PATH+vo.getAlias());
				map = "editImgs";
			}else if(modular.equals("ue")){
				map = "editue";
			}else if(modular.equals("value")){
				map = "editvalue";
			}
			return mapping.findForward(map);
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
			String type = ParamUtils.getParameter(request, "type", true);
			String modular = ParamUtils.getParameter(request, "modular", true);
			String rs = ParamUtils.getParameter(request, "rs", true);
			SysConfigForm vo = (SysConfigForm)form;
			SysConfig config = new SysConfig();
			super.copyProperties(config, vo);
			
			// 当含有跳转类型时
			if(StringUtils.isNotBlank(vo.getJumpType())) {
				String jt = vo.getJumpType();
				if("11".equals(jt) || "12".equals(jt) || "13".equals(jt) || "14".equals(jt)) {
					config.setValue(vo.getValueId());
				}
			}
			
			config.setType(type);
			 if(config.getType().equals("TOP_DISCOUNT") || config.getType().equals("TOP_CONPANY")||config.getType().equals("REFRESH_PRICE"))
				 config.setValue(AddZero.format(config.getValue(), 2));
			Tool.testReflect_admin(config);
			mgr.add(config);
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
			redirect.addParameter("type", type);
			redirect.addParameter("modular", modular);
			redirect.addParameter("rs", rs);
			return redirect;
			
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}finally{
			if (!isTokenValid(request))
			       resetToken(request); //删除session中的令牌
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

			SysConfigForm vo = new SysConfigForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			String type = ParamUtils.getParameter(request, "type", true);
			String modular = ParamUtils.getParameter(request, "modular", true);
			String rs = ParamUtils.getParameter(request, "rs", true);
			
			request.setAttribute("SysConfigForm", vo);
			request.setAttribute("type", type);
			request.setAttribute("modular", modular);
			request.setAttribute("rs", rs);
			if(modular.equals("imgs"))
				return mapping.findForward("addImgs");
			else if(modular.equals("ue"))
				return mapping.findForward("addue");
			else if(modular.equals("value"))
				return mapping.findForward("addvalue");
			return null;
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
				 
			Tool.execute("delete from sys_config where id = '"+ids[i]+"'");
				 
			}
			String type = ParamUtils.getParameter(request, "type", true);
			String modular = ParamUtils.getParameter(request, "modular", true);
			String rs = ParamUtils.getParameter(request, "rs", true);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
			redirect.addParameter("type", type);
			redirect.addParameter("modular", modular);
			redirect.addParameter("rs", rs);
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
	 * 异步上传图片
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateImgPath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SysConfigForm vo = (SysConfigForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String type = ParamUtils.getParameter(request, "type", true);
				String value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.PRODUCT_ICON_PATH_FILESPHONE,request);
			  
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
