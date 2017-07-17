package com.business.NetworkCourseComment;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.*;

public class NetworkCourseCommentAction extends BaseAction{
	
	NetworkCourseCommentMgr mgr=new NetworkCourseCommentMgr();
	
	/**
	 * 新网课评论列表
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(null==sessionContainer) sessionContainer=new SessionContainer();
		try {
			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
			if (strItemsInPage != null) {
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			String action = ParamUtils.getParameter(request, "pageAction", true);
			if ("".equals(action)) action = PageAction.FIRST.toString();
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);

			// 设置查询条件
			String ncNameQuery = ParamUtils.getParameter(request, "ncNameQuery", true);
			String startTime = ParamUtils.getParameter(request,"startTime",false);
	        String endTime = ParamUtils.getParameter(request,"endTime",false);
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("nc.nc_name", "String", "like", ncNameQuery));
			if(null!=startTime&&!"".equals(startTime)){
                queryConds.add(new QueryCond("ncc.create_time", "String", ">=", startTime));
            }
			if(null!=endTime&&!"".equals(endTime)){
				queryConds.add(new QueryCond("ncc.create_time", "String", "<=", endTime));
			}

			ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);

			List<Object[]> nccList = lc.getList();
			List<Map<String, Object>> commentList = new ArrayList<>();
			for (Object[] obj : nccList) {
				Map<String, Object> commentMap = new HashMap<>();
				commentMap.put("id", obj[0]);
				commentMap.put("ncId", obj[1]);
				commentMap.put("userId", obj[2]);
				commentMap.put("content", obj[3]);
				commentMap.put("voteUp", obj[4]);
//				commentMap.put("voteDown", obj[5]);
				commentMap.put("createTime", obj[7]);
				commentMap.put("ncName", obj[8]);
				commentMap.put("mobile", obj[9]);
				commentList.add(commentMap);
			}
			request.setAttribute("ncNameQuery", ncNameQuery);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("commentList", commentList);
			request.setAttribute("lc", lc);

			return mapping.findForward("list");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	

	public ActionForward deleteById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);

			String ipAddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			String username = "admin";
			String content = Tool.getValue("select content from network_course_comment where id='"+id+"'");
			Tool.AddLog(sessionContainer.getUserId(), username, "删除评论,评论内容:"+content, "1", ipAddress);

			Tool.execute("update network_course_comment set is_hide = 1 where id = '"+id+"'");

			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}


	/**
	 * 修改点赞数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preVoteUpEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			String voteUp = ParamUtils.getParameter(request, "voteUp", true);
			request.setAttribute("id", id);
			request.setAttribute("voteUp", voteUp);
			return mapping.findForward("voteUpEdit");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}

	public ActionForward voteUpEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			String voteUp= ParamUtils.getParameter(request, "voteUp", true);
			// 修改点赞数
			Tool.execute("UPDATE network_course_comment SET vote_up = " + voteUp +" WHERE id = '" + id + "'");
			map.put("result", true);
		} catch (Exception ex) {
			map.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}


	/**
	 * 修改网课评论状态
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward CourseStateEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String id = ParamUtils.getParameter(request, "id", true);
			String ncState= ParamUtils.getParameter(request, "ncState", true);
			//修改订单退单状态
			System.out.println("update network_course set nc_state="+ncState+" where id='"+id+"'");
			Tool.execute("update network_course set nc_state="+ncState+" where id='"+id+"'");

			map.put("result", true);
		} catch (Exception ex) {
			map.put("result", false);
		}
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	
	
	/**
	 * sql拼接去''
	 * @param obj
	 * @return
	 */
	public static String getFormatField(Object obj) {
		String resultStr = "NULL";
		if (obj instanceof Integer) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr =  obj.toString() ;
			}
		} else if(obj instanceof String) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Timestamp) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Double) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = obj.toString();
			}
		}
		return resultStr;
	}
}
