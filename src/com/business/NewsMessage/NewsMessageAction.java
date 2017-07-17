package com.business.NewsMessage; 


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

import com.business.News.NewsMgr;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class NewsMessageAction extends BaseAction{
	 NewsMessageMgr mgr = new NewsMessageMgr();
     NewsMgr newsmgr=new NewsMgr();
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
			String title = ParamUtils.getParameter(request, "title", false);
			String newsContent = ParamUtils.getParameter(request, "newsContent", false);
			String username = ParamUtils.getParameter(request, "username", false);
			String mobile = ParamUtils.getParameter(request, "mobile", false);
			String starttime = ParamUtils.getParameter(request, "starttime", false);
			String endtime = ParamUtils.getParameter(request, "endtime", false);
			String newsId = ParamUtils.getParameter(request, "newsId", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("news.title", "String", "like", title));
			queryConds.add(new QueryCond("cast(nm.content as char(500))", "String", "like", newsContent));
			queryConds.add(new QueryCond("users.username", "String", "=", username));
			queryConds.add(new QueryCond("users.mobile", "String", "=", mobile));
			if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
				queryConds.add(new QueryCond("nm.createtime", "String", ">=", starttime));
				queryConds.add(new QueryCond("nm.createtime", "String", "<=", endtime+" 23:59:59"));
			}
			queryConds.add(new QueryCond("nm.news_id", "String", "=", newsId));

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
            List<Object[]> list=lc.getList();
            List<Map> lm=new ArrayList<Map>();
            for(Object[] ob:list ){
            	Map m=new HashMap();
            	m.put("id", ob[0]);
            	m.put("commentsId", ob[5]);
            	m.put("createtime",ob[4]);
            	m.put("newsId", ob[1]);
            	m.put("title", ob[12]);
            	m.put("userId", ob[2]);
            	m.put("userName", ob[10]);
				m.put("isTop",ob[8]);
				m.put("topTime",ob[9]);
				m.put("content", ob[3]);
				m.put("mobile", ob[11]);
//            	if(null!=nm.getContent()&&!"".equals(nm.getContent())){
//					String blobString = new String(nm.getContent().getBytes(1, (int) nm.getContent().length()),"utf-8");//blob 转 String

//				}
				lm.add(m);
            }
            lc.setList(lm);
			request.setAttribute("title", title);
			request.setAttribute("newsContent", newsContent);
			request.setAttribute("username", username);
			request.setAttribute("mobile", mobile);
			request.setAttribute("starttime", starttime);
			request.setAttribute("endtime", endtime);
			request.setAttribute("lc", lc);
			request.setAttribute("lm", lm);
			request.setAttribute("newsId", newsId);
			 
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
			NewsMessageActionForm vo = mgr.view(ID);
			String userId=vo.getUserId();
			String userName=Tool.getValue("select username from users where id='"+userId+"'");
			
			if(null!=vo.getContent()&&!"".equals(vo.getContent())){
				String blobString = new String(vo.getContent().getBytes(1, (int) vo.getContent().length()),"utf-8");//blob 转 String
				request.setAttribute("content", blobString);
			}else{
				request.setAttribute("content", "");
			}
			
			request.setAttribute("title", Tool.getValue("select title from news where id='"+vo.getNewsId()+"'"));
			request.setAttribute("userName", userName);
			request.setAttribute("NewsMessageActionForm", vo);
			if(null!=vo.getContent()&&!"".equals(vo.getContent())){
				String blobString = new String(vo.getContent().getBytes(1, (int) vo.getContent().length()),"utf-8");//blob 转 String
				request.setAttribute("content", blobString);
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
		
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
				if(sessionContainer==null){
					sessionContainer = new SessionContainer();
				}
				
		NewsMessageActionForm vo = (NewsMessageActionForm) form;
		try {
			
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setUserId(sessionContainer.getUserId());
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

			NewsMessageActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			
			request.setAttribute("NewsMessageActionForm", vo);

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
			NewsMessageActionForm vo = (NewsMessageActionForm) form;			  
			//vo.setType("1");
			//vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			Tool.testReflect_admin(vo);
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

			NewsMessageActionForm vo = new NewsMessageActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("NewsMessageActionForm", vo);
			
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

			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {
				
		    Tool.execute("update news_message set is_del = 1 where id='"+ids[i]+"'");
			//Tool.execute("delete from news_message where id = '"+ids[i]+"'");
		   
		    // 获取ip地址
 			String ipaddress = IpAddressUtil.getIpAddr(request);
 			//添加操作记录
 			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"删除资讯,Id:" +ids[i] , "1", ipaddress);
		 			 
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
	 * 资讯的相关评论
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
			String newsId = ParamUtils.getParameter(request, "newsId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			 
			queryConds.add(new QueryCond("NewsMessage.newsId", "String", "=", newsId));
			queryConds.add(new QueryCond("NewsMessage.isDel", "number", "=", "0"));
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			List<NewsMessage> list=lc.getList();
            List<Map> lm=new ArrayList<Map>();
            for(NewsMessage nm:list ){
            	Map m=new HashMap();
            	m.put("id", nm.getId());
            	m.put("commentsId", nm.getCommentsId());
            	m.put("createtime",nm.getCreatetime());
            	m.put("newsId", nm.getNewsId());
            	m.put("title", Tool.getValue("select title from news where id='"+nm.getNewsId()+"'"));
            	m.put("userId", nm.getUserId());
            	m.put("userName", Tool.getValue("select username from users where id='"+nm.getUserId()+"'"));
            	if(null!=nm.getContent()&&!"".equals(nm.getContent())){
					String blobString = new String(nm.getContent().getBytes(1, (int) nm.getContent().length()),"utf-8");//blob 转 String
					m.put("content", blobString);
				}
            	
				lm.add(m);
            }
            lc.setList(lm);
			
			request.setAttribute("newsid", newsId);
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
	 * 删除信息
	 * 资讯评论-删除
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
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}

			String mId = ParamUtils.getParameter(request, "mId", false);
				
		    Tool.execute("update news_message set is_del = 1 where id='"+mId+"'");
			//Tool.execute("delete from news_message where id = '"+ids[i]+"'");
		   
		    // 获取ip地址
 			String ipaddress = IpAddressUtil.getIpAddr(request);
 			//添加操作记录
 			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"删除资讯,Id:" +mId , "1", ipaddress);
		 			 
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	public ActionForward toTop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String id = ParamUtils.getParameter(request, "id");
			String isTop = ParamUtils.getParameter(request, "enable");
			String topTime = null;
			if(isTop.equals("1")){
				topTime= DateUtils.getCurrDateTimeStr();
				boolean result = Tool.execute("update news_message set is_top='"+isTop+"',top_time='"+topTime+"' where id = '"+id+"'");
			}else{
				boolean result = Tool.execute("update news_message set is_top='"+isTop+"',top_time="+topTime+" where id = '"+id+"'");
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
