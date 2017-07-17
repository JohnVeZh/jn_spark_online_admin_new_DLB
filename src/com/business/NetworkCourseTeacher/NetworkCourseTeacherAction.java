package com.business.NetworkCourseTeacher;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.FileUploadUtil;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class NetworkCourseTeacherAction extends BaseAction{

	NetworkCourseTeacherMgr mgr = new NetworkCourseTeacherMgr();
	
	/**
	 * 列表展示
	 */
	
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
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
			 
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
					 
			queryConds.add(new QueryCond("NetworkCourseTeacher.isDelete", "String", "=", "0"));
			
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

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
	 * 预添加
	 */
	public ActionForward preAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		try{
			NetworkCourseTeacherActionForm vo = new NetworkCourseTeacherActionForm();
			SessionContainer sess = (SessionContainer) request.getSession().getAttribute("sessionContainer");
			if(sess == null){
				sess = new SessionContainer();
			}
			
			request.setAttribute("NetworkCourseTeacherActionForm", vo);
								  
			return mapping.findForward("add");
		}catch(Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 添加信息
	 */
	public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		try{
			NetworkCourseTeacherActionForm vo = (NetworkCourseTeacherActionForm) form;
			vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date()));
			Tool.testReflect_admin(vo);
		
			String ipAddr = IpAddressUtil.getIpAddr(request);
			SessionContainer sess = (SessionContainer) request.getSession().getAttribute("sessionContainer");
			if(sess == null ){
				sess = new SessionContainer();
			}
			String username = "admin";
			Tool.AddLog(sess.getUserId(), username,
					"添加讲师,讲师名称:"+vo.getName(), "1", ipAddr);
			mgr.add(vo);
			
			return list(mapping,form,request,response);
		}catch(Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
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
	public ActionForward updateImgPath(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		NetworkCourseTeacherActionForm vo = (NetworkCourseTeacherActionForm) form; 
		Map<String,Object> map = new HashMap();
		try{
			if(vo.getFile().getFileSize()>0){
				String value = FileUploadUtil.uploadFile(vo.getFile(), DictionaryUtils.NETWORK_TEACHER_PATH_PHONE, request);
				map.put("result", true);
				map.put("imgPath", value);
				JsonUtils.outputJsonResponse(response, map);
			}
		}catch(Exception ex){
			map.put("result", false);
			JsonUtils.outputJsonResponse(response, map);
		}
		return null;
	}
	
	/**
	 * 预更新
	 */
	
	public ActionForward preUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		try {
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
			if(sessionContainer == null){
				sessionContainer = new SessionContainer();
			}
			String id = ParamUtils.getParameter(request, "id", true);
			NetworkCourseTeacherActionForm vo = mgr.view(id);
			request.setAttribute("NetworkCourseTeacherActionForm", vo);
			return mapping.findForward("update");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	/**
	 * 更新数据
	 */
	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		NetworkCourseTeacherActionForm vo = (NetworkCourseTeacherActionForm) form;
		try {
			if(null!=vo){
				mgr.update(vo);
				
				String ipaddress = IpAddressUtil.getIpAddr(request);
				SessionContainer sessionContainer = (SessionContainer) request
						.getSession().getAttribute("SessionContainer");
				if (null == sessionContainer) {
					sessionContainer = new SessionContainer();
				}
				String username = "admin";
				Tool.AddLog(sessionContainer.getUserId(), username,
						"修改讲师,讲师名称:"+vo.getName(), "1", ipaddress);
			}
			
			return list(mapping,form,request,response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	
	/**
	 * 删除数据，根据id删除
	 */
	public ActionForward deleteById(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		try {
			String id = (String) request.getParameter("id");
			String ipAddr = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("sessionContainer");
			if(null == sessionContainer){
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			NetworkCourseTeacher po =  mgr.deleteById(id);
			Tool.AddLog(sessionContainer.getUserId(), username, 
					"删除讲师,讲师名称:"+po.getName(), "1", ipAddr);
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	/**
	 * 教师名称唯一校验
	 */
	public ActionForward checkName(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		try{
			String teacherName = ParamUtils.getParameter(request, "teacherName");
			List<String> list = Tool.findListByHql("select name from NetworkCourseTeacher");
			int i = 0;
			for(String str:list){
				if(str.equals(teacherName)){
					i++;
				}
			}
			outputJsonResponse(response,list.contains(teacherName),String.valueOf(i));
		}catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
		return null;
	}
}
