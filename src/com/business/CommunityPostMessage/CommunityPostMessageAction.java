package com.business.CommunityPostMessage; 


import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.URL;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.DictionaryUtils;
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.FontImg;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.CommunityPostMessage.CommunityPostMessage;
import com.business.CommunityPostMessage.CommunityPostMessageActionForm;
import com.business.CommunityPostMessage.CommunityPostMessageMgr;

public class CommunityPostMessageAction extends BaseAction{
	 CommunityPostMessageMgr mgr = new CommunityPostMessageMgr();

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
			String postId = ParamUtils.getParameter(request, "postId", false);
			// 接收传值
			String commentsId = ParamUtils.getParameter(request, "replayCommentsId");
			if(commentsId == null || commentsId.equals("")){
				commentsId = ParamUtils.getParameter(request, "commentsId");
				if(commentsId == null || commentsId.equals("")) {
					commentsId = "FFFFFF";
				}
			}
			// 接收传值
			String title = ParamUtils.getParameter(request, "title", false);
			String newsContent = ParamUtils.getParameter(request, "newsContent", false);
			String username = ParamUtils.getParameter(request, "username", false);
			String mobile = ParamUtils.getParameter(request, "mobile", false);
			String starttime = ParamUtils.getParameter(request, "starttime", false);
			String endtime = ParamUtils.getParameter(request, "endtime", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("cp.title", "String", "like", title));
			queryConds.add(new QueryCond("cast(cpm.content as char(500))", "String", "like", newsContent));
			queryConds.add(new QueryCond("users.username", "String", "=", username));
			queryConds.add(new QueryCond("users.mobile", "String", "=", mobile));
			if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
				queryConds.add(new QueryCond("cpm.createtime", "String", ">=", starttime));
				queryConds.add(new QueryCond("cpm.createtime", "String", "<=", endtime+" 23:59:59"));
			}
			
			queryConds.add(new QueryCond("cpm.community_post_id", "String", "=", postId));
			queryConds.add(new QueryCond("cpm.comments_id", "String", "=", commentsId));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			
			List<Object[]> list=lc.getList();
            List<Map> lm = new ArrayList<Map>();
			for (Object[] ob : list) {
				Map m = new HashMap();
				m.put("title",ob[0]);
				m.put("id", ob[1]);
				m.put("commentsId", ob[6]);
				m.put("communityPostId", ob[2]);
				m.put("createtime", ob[5]);
				m.put("imgpths", ob[8]);
				m.put("likeNum", ob[7]);
				m.put("userId", ob[3]);
				m.put("isTop",ob[10]);
				m.put("topTime",ob[11]);
				m.put("content", ob[4]);
				m.put("userName",ob[12]);
				m.put("mobile",ob[13]);
				lm.add(m);
			}
			lc.setList(lm);
			request.setAttribute("lm", lm);
			request.setAttribute("postId", postId);
			request.setAttribute("commentsId",commentsId);
			request.setAttribute("lc", lc);
			request.setAttribute("title", title);
			request.setAttribute("newsContent", newsContent);
			request.setAttribute("username", username);
			request.setAttribute("mobile", mobile);
			request.setAttribute("starttime", starttime);
			request.setAttribute("endtime", endtime);
			 
			return mapping.findForward("list");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
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
			String postId = ParamUtils.getParameter(request, "postId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
			queryConds.add(new QueryCond("CommunityPostMessage.communityPostId", "String", "=", postId));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			List<CommunityPostMessage> list=lc.getList();
            List<Map> lm = new ArrayList<Map>();
			for (CommunityPostMessage cp : list) {
				Map m = new HashMap();
				m.put("id", cp.getId());
				m.put("commentsId", cp.getCommentsId());
				m.put("communityPostId", cp.getCommunityPostId());
				m.put("createtime", cp.getCreatetime());
				m.put("imgpths", cp.getImgpths());
				m.put("likeNum", cp.getLikeNum());
				m.put("userId", cp.getUserId());
				if(null!=cp.getContent()&&!"".equals(cp.getContent())){
					String blobString = new String(cp.getContent().getBytes(1, (int) cp.getContent().length()),"utf-8");//blob 转 String
					m.put("content", blobString);
				}else{
					m.put("content", "");
				}
				m.put("userName",Tool.getValue("select username from users where id='"+cp.getUserId()+"'"));
				lm.add(m);
			}
			lc.setList(lm);
			//request.setAttribute("lm", lm);
			request.setAttribute("postId", postId);
			//request.setAttribute("name", name);
			//request.setAttribute("loginName", loginName);
			request.setAttribute("lc", lc);
			 
			return mapping.findForward("comment");
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
			
			CommunityPostMessageActionForm vo = mgr.view(ID);
			
			request.setAttribute("CommunityPostMessageActionForm", vo);
			if(vo!=null && vo.getImgpths()!=null){
				String urls[] = vo.getImgpths().split(",");
				List<Map> imgLm = new ArrayList<Map>();
				for (int i = 0; i < urls.length; i++) {
					if(null!=urls[i] && !"".equals(urls[i])){
						Map m = new HashMap();
						if(!urls[i].startsWith("http")){
							if(FontImg.fontFile("http://114.55.73.238:9090/"+urls[i])){
								m.put("imgs","http://114.55.73.238:9090/"+urls[i]);
							}else if(FontImg.fontFile("http://114.55.87.86:8988/"+urls[i])){
								m.put("imgs","http://114.55.87.86:8988/"+urls[i]);
							}else if(FontImg.fontFile("http://114.55.87.86:9090/"+urls[i])){
								m.put("imgs","http://114.55.87.86:8988/"+urls[i]);
							}else{
								m.put("imgs","");
							}
						}else{
							m.put("imgs",urls[i]);
						}
						imgLm.add(m);
							
					}
				}
				request.setAttribute("imgsLm", imgLm);
			}
			
			request.setAttribute("userName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
			
			if(null!=vo.getContent()&&!"".equals(vo.getContent())){
				String blobString = new String(vo.getContent().getBytes(1, (int) vo.getContent().length()),"utf-8");//blob 转 String
				request.setAttribute("content",blobString);
			}else{
				request.setAttribute("content","");
			}
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

		CommunityPostMessageActionForm vo = (CommunityPostMessageActionForm) form;
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

			CommunityPostMessageActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("id", ID);
			request.setAttribute("CommunityPostMessageActionForm", vo);

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
			String communityPostId=ParamUtils.getParameter(request, "communityPostId",false);
			//System.out.println(communityPostId);
			CommunityPostMessageActionForm vo = (CommunityPostMessageActionForm) form;			  
		   
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			//vo.setType("1");
			vo.setCommunityPostId(communityPostId);
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setUserId(sessionContainer.getUserId());
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
			String communityPostId=ParamUtils.getParameter(request, "postId",false);
			CommunityPostMessageActionForm vo = new CommunityPostMessageActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
            Collection queryConds = new ArrayList();
			
			 
			request.setAttribute("communityPostId", communityPostId);
			request.setAttribute("CommunityPostMessageActionForm", vo);
			
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
				 
			Tool.execute("delete from community_post_message where id = '"+ids[i]+"'");
				 
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
	 * 删除信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String ID = ParamUtils.getParameter(request, "mesId", true);
			Tool.execute("delete from community_post_message where id = '"+ID+"'");
			return list(mapping, form, request, response);
		} catch (Exception ex) {
 
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	//回复信息
	public ActionForward huifu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id=ParamUtils.getParameter(request, "id",false);
			String content=ParamUtils.getParameter(request, "contentStr",false);
			String communityPostId=ParamUtils.getParameter(request, "communityPostId",false);
			
				CommunityPostMessage cpm = new CommunityPostMessage();
				cpm.setCommunityPostId(communityPostId);
				cpm.setUserId("29551");
				cpm.setCommentsId(id);
				cpm.setFirstmesId(id);
				if(null!=content&&!"".equals(content)){
					Blob b = new SerialBlob(content.getBytes("utf-8"));//String 转 blob
					cpm.setContent(b);
				}
				cpm.setCreatetime(DateUtils.getCurrDateTimeStr());
				mgr.add(cpm);
			map.put("result", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("result",false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}

	public ActionForward toTop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String id = ParamUtils.getParameter(request, "id");
			String isTop = ParamUtils.getParameter(request, "enable");
			String topTime = "";
			if(isTop.equals("1")){
				topTime= DateUtils.getCurrDateTimeStr();
			}
			boolean result = Tool.execute("update community_post_message set is_top='"+isTop+"',top_time='"+topTime+"' where id = '"+id+"'");
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
