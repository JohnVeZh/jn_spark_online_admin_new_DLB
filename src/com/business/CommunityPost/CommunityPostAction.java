package com.business.CommunityPost; 


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.business.CommunityPostFollowRecord.CommunityPostFollowRecordMgr;
import com.business.CommunityPostLikeRecord.CommunityPostLikeRecordMgr;
import com.business.NetworkVideo.NetworkVideoMgr;
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

public class CommunityPostAction extends BaseAction{
	 CommunityPostMgr mgr = new CommunityPostMgr();
	 CommunityPostLikeRecordMgr cplMgr=new CommunityPostLikeRecordMgr();
	 CommunityPostFollowRecordMgr cpfMgr=new CommunityPostFollowRecordMgr();
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
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))action = PageAction.FIRST.toString();

			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			 
			// 接收传值
			String title = ParamUtils.getParameter(request, "Title", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			 
			//
			queryConds.add(new QueryCond("CommunityPost.title", "String", "like", title));
			queryConds.add(new QueryCond("CommunityPost.isDel", "number", "=", "0"));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("Title", title);
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

			request.setAttribute("CommunityPostActionForm", mgr.view(ID));

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

		CommunityPostActionForm vo = (CommunityPostActionForm) form;
		try {
			CommunityPostActionForm com = mgr.view(vo.getId());
			if(com!=null){
//				com.setViewImgpath(vo.getViewImgpath());
				//后台添加至添加列表图片，详情图片存列表图片地址，以备详情图片再用
				com.setViewImgpath(vo.getImgpathList());
				com.setImgpathList(vo.getImgpathList());
				com.setTitle(vo.getTitle());
				com.setBrief(vo.getBrief());
				com.setCountent(vo.getCountent());
				com.setIsShow(vo.getIsShow());
				mgr.update(com);
			}

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"修改社区管理,管理标题:"+vo.getTitle(), "1", ipaddress);
			
			
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

			CommunityPostActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("CommunityPostActionForm", vo);
			if(null!=vo && vo.getImgpathList()!=null){
				String imgs[] = vo.getImgpathList().split(",");
				request.setAttribute("imgs", imgs);
			}
  
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
			CommunityPostActionForm vo = (CommunityPostActionForm) form;			  
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setSysUserId(sessionContainer.getUserId());
			//后台添加至添加列表图片，详情图片存列表图片地址，以备详情图片再用
			vo.setViewImgpath(vo.getImgpathList());
			Tool.testReflect_admin(vo);
			mgr.add(vo);
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"添加社区管理,管理标题:"+vo.getTitle(), "1", ipaddress);
			
			
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

			CommunityPostActionForm vo = new CommunityPostActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("CommunityPostActionForm", vo);
			
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
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				 
				String name = Tool.getValue("select title from community_post where id='"+ids[i]+"'");
				
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除社区管理,管理标题:"+name, "1", ipaddress);
//			Tool.execute("delete from community_post where id = '"+ids[i]+"'");
				Tool.execute("update community_post set is_del=1 where id = '"+ids[i]+"'");
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
	 * 删除单个信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			
			
			String ID = ParamUtils.getParameter(request, "postId", true);
			
			String name = Tool.getValue("select title from community_post where id='"+ID+"'");
			
			Tool.AddLog(sessionContainer.getUserId(), username,
					"删除社区管理,管理标题:"+name, "1", ipaddress);
			
			
//			Tool.execute("delete from community_post where id = '"+ID+"'");
			Tool.execute("update community_post set is_del=1 where id = '"+ID+"'");
				 
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

		CommunityPostActionForm vo = (CommunityPostActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String value = "";
					value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.POST_PATH_PHONEIMG,request);
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
	
	/**
	 * 推荐
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward enable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommunityPostActionForm vo = (CommunityPostActionForm) form;	
			String ID = ParamUtils.getParameter(request, "id", true);
			String enable = ParamUtils.getParameter(request, "enable", true);
			int n=Integer.parseInt(enable);
			vo = mgr.view(ID);
			if(n==0){
				vo.setIsRecommend(n);
				vo.setRecommendTime("");
			}else{
				vo.setIsRecommend(n);
				vo.setRecommendTime(DateUtils.getCurrDateTimeStr());
				
			}
			Tool.testReflect_admin(vo);
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
	 * 置顶
	 */
	public ActionForward toTop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommunityPostActionForm vo = (CommunityPostActionForm) form;	
			String ID = ParamUtils.getParameter(request, "id", true);
			String enable = ParamUtils.getParameter(request, "enable", true);
			int n=Integer.parseInt(enable);
			vo = mgr.view(ID);
			if(n==0){
				vo.setIsTop(n);
				vo.setTopTime("");
			}else{
				vo.setIsTop(n);
				vo.setTopTime(DateUtils.getCurrDateTimeStr());
				
			}
			Tool.testReflect_admin(vo);
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

    public ActionForward communityPostSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer)
		sessionContainer=new SessionContainer();
		NetworkVideoMgr nmgr = new NetworkVideoMgr();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action))
				action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 接收传值
			String title = ParamUtils.getParameter(request, "title", false);
			
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("CommunityPost.title", "String", "like", title));
			queryConds.add(new QueryCond("CommunityPost.isDel", "number", "=", "0"));
			 
			ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("lc", lc);
			request.setAttribute("title", title);
			request.setAttribute("srcpage", request.getParameter("srcpage"));
			request.setAttribute("idKey", request.getParameter("idKey"));
			request.setAttribute("valueKey", request.getParameter("valueKey"));
			
			return mapping.findForward("communityPostSelector");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
}
