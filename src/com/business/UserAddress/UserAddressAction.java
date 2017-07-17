package com.business.UserAddress; 


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
import com.easecom.common.util.DictionaryUtils;
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
import com.business.UserAddress.UserAddress;
import com.business.UserAddress.UserAddressActionForm;
import com.business.UserAddress.UserAddressMgr;

public class UserAddressAction extends BaseAction{
	 UserAddressMgr mgr = new UserAddressMgr();

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
			String realname = ParamUtils.getParameter(request, "realnameStr", false);
			String userId = ParamUtils.getParameter(request, "userId", false);
			Collection queryConds = new ArrayList();
			// 设置查询条件
			if(realname!=null && !realname.equals("")){
				queryConds.add(new QueryCond("UserAddress.realname", "String", "like", realname));
			}
			if(userId != null && !userId.equals("")){
				queryConds.add(new QueryCond("UserAddress.userId", "String", "=", userId));
			}
			 queryConds.add(new QueryCond("UserAddress.isView", "String", "=", "1"));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			request.setAttribute("lc", lc);
			request.setAttribute("userId", userId);
			
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
			UserAddressActionForm vo = mgr.view(ID);
			//获取省份城市等信息
			//获取省份城市等信息
			String provinceName = Tool.getValue("select name from system_province_city where id='"+vo.getProvinceId()+"'");
			//获取城市名称
			String cityName = Tool.getValue("select name from system_province_city where id='"+vo.getCityId()+"'");
			//获取县名称
			String citysName = Tool.getValue("select name from system_province_city where id='"+vo.getAreaId()+"'");
			
			
			request.setAttribute("provinceName", provinceName);
			request.setAttribute("cityName", cityName);
			request.setAttribute("citysName", citysName);

			request.setAttribute("UserAddressActionForm", vo);

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

		UserAddressActionForm vo = (UserAddressActionForm) form;
		try {
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setIsView("1");
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

			UserAddressActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//获取省份城市等信息
			String provinceList = Tool.getList("select name,id from system_province_city where parentid='FFFFFF'",
							"name", "id",vo.getProvinceId());
			String cityList = Tool.getList("select name,id from system_province_city where parentid='"+vo.getProvinceId()+"'",
					"name", "id",vo.getCityId());
			String citysList = Tool.getList("select name,id from system_province_city where parentid='"+vo.getCityId()+"'",
					"name", "id",vo.getAreaId());
			
			request.setAttribute("provinceList", provinceList);
			request.setAttribute("cityList", cityList);
			request.setAttribute("citysList", citysList);
			
			
			request.setAttribute("UserAddressActionForm", vo);

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
			UserAddressActionForm vo = (UserAddressActionForm) form;			  
			//vo.setType("1");
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setIsView("1");
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

			String userId =ParamUtils.getParameter(request, "userId", true);
			UserAddressActionForm vo = new UserAddressActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			//获取省份城市等信息
			request.setAttribute("provinceList", Tool.getList("select provinceID,pname from province", "pname", "provinceID"));
			request.setAttribute("UserAddressActionForm", vo);
			request.setAttribute("userId", userId);
			
			
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
			UserAddressActionForm vo = null;
			if(ids.length>0){
				
				vo = mgr.view(ids[0]);
				
			}
			for (int i = 0; i < ids.length; i++) {
			//Tool.execute("delete from user_address where id = '"+ids[i]+"'");
			 Tool.execute("update user_address ua set ua.is_view='0' where id = '"+ids[i]+"'");
			}
			return list(mapping, vo, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
		
		/**
		 * 根据类型，查询省市区县
		 * 方法功能说明：  
		 * 创建：2016年3月12日 by Zzc   
		 * 修改：日期 by 修改者  
		 * 修改内容：  
		 * @参数： @param mapping
		 * @参数： @param form
		 * @参数： @param request
		 * @参数： @param response
		 * @参数： @return
		 * @参数： @throws SystemException      
		 * @return ActionForward     
		 * @throws
		 */
		public ActionForward province_city_area_List(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws SystemException {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				String type = ParamUtils.getParameter(request, "type");
				String Fid = ParamUtils.getParameter(request, "Fid");
				if(type.equals("city"))
					map.put("dataList", Tool.getList("select cityID,city from city where provinceID = '"+Fid+"'", "city", "cityID"));
				else if(type.equals("area"))
					map.put("dataList", Tool.getList("select areaID,area from area where cityID = '"+Fid+"'", "area", "areaID"));
				
				map.put("succeed","000");
				map.put("sucInfo", "操作成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map.put("succeed","001");
				map.put("sucInfo", "操作失败");
			}
			JsonUtils.outputJsonResponse(response, map);
			return null;
		}
}
