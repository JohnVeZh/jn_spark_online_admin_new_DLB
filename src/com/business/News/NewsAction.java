package com.business.News; 


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.NewLables.NewLablesMgr;
import com.business.NewsLablesRecord.NewsLablesRecord;
import com.business.NewsLablesRecord.NewsLablesRecordActionForm;
import com.business.NewsLablesRecord.NewsLablesRecordMgr;
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

public class NewsAction extends BaseAction{
	 NewsMgr mgr = new NewsMgr();
	 NewLablesMgr newLablesMgr = new NewLablesMgr();
	 NewsLablesRecordMgr newsLablesRecordMgr = new NewsLablesRecordMgr();

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
			 
			String titleStr = ParamUtils.getParameter(request, "titleStr", false);
			String author=ParamUtils.getParameter(request, "authorStr", false);
			String starttime=ParamUtils.getParameter(request,"starttimeStr");//开始时间
			String endtime=ParamUtils.getParameter(request,"endtimeStr");//结束时间
			String searchStetus=ParamUtils.getParameter(request,"searchStetus");//结束时间

			// 设置查询条件
			Collection queryConds = new ArrayList();
			if(titleStr!=null && !titleStr.equals("")){
				queryConds.add(new QueryCond("News.title", "String", "like", titleStr));
			}
			queryConds.add(new QueryCond("News.author","String","=",author));
			queryConds.add(new QueryCond("News.ptime", "String", ">=", starttime));
			if(null != endtime && !"".equals(endtime)){
				queryConds.add(new QueryCond("News.ptime", "String", "<=", endtime+" 23:59:59"));
			}
			queryConds.add(new QueryCond("News.isDel", "number", "=", "0"));
		 	if(null !=searchStetus && !searchStetus.equals("all")){
				queryConds.add(new QueryCond("News.isShow", "String", "=", searchStetus));
			}
			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			
			request.setAttribute("titleStr", titleStr);
			request.setAttribute("authorStr", author);
			request.setAttribute("starttimeStr",starttime);
			request.setAttribute("endtimeStr",endtime);
			request.setAttribute("searchStetus",searchStetus);
			request.setAttribute("lc", lc);
			
			String type = ParamUtils.getParameter(request, "type", false);
			if(type != null && type.equals("excnews")){
				return mapping.findForward("excnews");
			}
			
			
			return mapping.findForward("list");
		} catch (Exception ex) {
			ex.printStackTrace();
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
			NewsActionForm vo = mgr.view(ID);
			request.setAttribute("content", vo.getContent());
			request.setAttribute("NewsActionForm",vo );

			List lbs = newLablesMgr.SQLQuery("select nl.lableName from news_lables_record nlr,new_lables nl where nlr.new_lables_id = nl.id and  nlr.news_id='"+ID+"'");
			request.setAttribute("lbs", lbs);
			
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

		NewsActionForm vo = (NewsActionForm) form;
		try {
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
					if(sessionContainer==null){
						sessionContainer = new SessionContainer();
					}
			NewsActionForm news = mgr.view(vo.getId());
			if(news!=null){
				news.setTitle(vo.getTitle());
				news.setSubtitle(vo.getSubtitle());
				news.setPhoneImg(vo.getPhoneImg());
				news.setAuthor(vo.getAuthor());
				news.setContent(vo.getContent());
				news.setIsShow(vo.getIsShow());
				String ptime = vo.getPtime();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				boolean result = false;
//				if(null != ptime){
//					try {
//						Date ptime1 = sdf.parse(ptime) ;
//						Date de = new Date();
//						if(ptime1.before(de)){
//							ptime = DateUtils.getCurrDateTimeStr();
//						}
//					}catch (Exception e){
//						ptime = DateUtils.getCurrDateTimeStr();
//					}
//				}else{
//					ptime = DateUtils.getCurrDateTimeStr();
//				}
				news.setPtime(ptime);
				mgr.update(news);
			}

			//删除标签
			Tool.execute("delete news_lables_record where news_id='"+vo.getId()+"'");
			//添加标签
			String[] clbs = request.getParameterValues("clb");
			if(clbs != null && clbs.length>0){
				for (int i = 0; i < clbs.length; i++) {
					NewsLablesRecordActionForm nlr = new NewsLablesRecordActionForm();
					nlr.setNewsId(vo.getId());
					nlr.setNewLablesId(clbs[i]);
					newsLablesRecordMgr.add(nlr);
				}
			}
			
			// 获取ip地址
			String ipaddress = IpAddressUtil.getIpAddr(request);
			//添加操作记录
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"修改资讯,Id:" +vo.getId() , "1", ipaddress);
			
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

			NewsActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			
			request.setAttribute("NewsActionForm", vo);

			if(vo!=null){
				/*查询资讯标签*/
				List<Map> lbs = newLablesMgr.SQLQuery("select id,lableName from new_lables where is_del=0 ");
				for (Map map : lbs) {
					NewsLablesRecord nr = (NewsLablesRecord)newsLablesRecordMgr.getObjectByHql("from NewsLablesRecord where newsId='"+vo.getId()+"' and newLablesId='"+map.get("id")+"'");
					if(nr!=null)
						map.put("isCheck", 0);
					else
						map.put("isCheck", 1);
				}
				request.setAttribute("lbs", lbs);
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
			NewsActionForm vo = (NewsActionForm) form;		
			vo.setCount(0);
			vo.setSysUserId(sessionContainer.getUserId());
			vo.setCreatetime(DateUtils.getCurrDateTimeStr());
			vo.setTopTime("");
			vo.setRecommendTime("");

			News nw = new News();
			Tool.testReflect_admin(nw);
			this.copyProperties(nw, vo);
			String id = mgr.add(nw);
			
			//添加标签
			String[] clbs = request.getParameterValues("clb");
			if(clbs != null && clbs.length>0){
				for (int i = 0; i < clbs.length; i++) {
					NewsLablesRecordActionForm nlr = new NewsLablesRecordActionForm();
					nlr.setNewsId(id);
					nlr.setNewLablesId(clbs[i]);
					newsLablesRecordMgr.add(nlr);
				}
			}
			
			// 获取ip地址
			String ipaddress = IpAddressUtil.getIpAddr(request);
			//添加操作记录
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"添加资讯,Id:" +id , "1", ipaddress);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
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

			NewsActionForm vo = new NewsActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("NewsActionForm", vo);
			/*查询资讯标签*/
			List lbs = newLablesMgr.SQLQuery("select id,lableName from new_lables where is_del=0 ");
			request.setAttribute("lbs", lbs);
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
				 
//			Tool.execute("delete from news where id = '"+ids[i]+"'");
			Tool.execute("update news set is_del = 1 where id='"+ids[i]+"'");
			
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
	 * 根据 id 删除信息
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
					
			String ID = ParamUtils.getParameter(request, "newId", true);
			if(ID!=null && !"".equals(ID)) {
				 
//			Tool.execute("delete from news where id = '"+ids[i]+"'");
			Tool.execute("update news set is_del = 1 where id='"+ID+"'");
			
			// 获取ip地址
			String ipaddress = IpAddressUtil.getIpAddr(request);
			//添加操作记录
			Tool.AddLog(sessionContainer.getUserId(), sessionContainer.getUserName(),"删除资讯,Id:" +ID , "1", ipaddress);
			
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

		NewsActionForm vo = (NewsActionForm) form;
		Map map = new HashMap();
		try {
			if(vo.getFile().getFileSize() > 0){
				String value = "";
					value = FileUploadUtil.uploadFile(vo.getFile(),DictionaryUtils.NEWS_ICON_PATH_PHONEIMG,request);
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
	 * 是否置顶
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toTop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			String ID = ParamUtils.getParameter(request, "id", true);

			NewsActionForm vo = (NewsActionForm) form;
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
	
	/**
	 * 是否推荐
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward enable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			NewsActionForm vo = (NewsActionForm) form;	
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
    public void release(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response) {
		String id=ParamUtils.getParameter(request,"id");//id
		String ptime=ParamUtils.getParameter(request,"ptime");//发布时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean result = false;
		if(null != ptime){
			try {
				Date ptime1 =sdf.parse(ptime);
				if(ptime1.before(new Date())){
					ptime = DateUtils.getCurrDateTimeStr();
				}
			}catch (Exception e){
				ptime = DateUtils.getCurrDateTimeStr();
			}
		}else{
			ptime = DateUtils.getCurrDateTimeStr();
		}
		try {
			result = Tool.execute("update news set is_show = 1,ptime = '"+ptime+"' where id = '"+id+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonUtils.outputJsonResponse(response, result);
	}

    public ActionForward newsSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
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
			queryConds.add(new QueryCond("News.title", "String", "like", title));
			queryConds.add(new QueryCond("News.isDel", "number", "=", "0"));
			 
			ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
			
			request.setAttribute("lc", lc);
			request.setAttribute("title", title);
			request.setAttribute("srcpage", request.getParameter("srcpage"));
			request.setAttribute("idKey", request.getParameter("idKey"));
			request.setAttribute("valueKey", request.getParameter("valueKey"));
			
			return mapping.findForward("newsSelector");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
}
