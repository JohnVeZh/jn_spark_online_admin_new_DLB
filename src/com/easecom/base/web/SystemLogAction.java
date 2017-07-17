package com.easecom.base.web;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.base.business.SystemLogMgr;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.DictionaryManage;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionUtils;

public class SystemLogAction extends BaseAction {
	private SystemLogMgr mgr = new SystemLogMgr();
	
	/**
	 * 按指定条件获取系统日志列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// 获取当前页数，默认值为1
			int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
			// 获取总记录数，如果为空则为null，用于显示全部数据时使用
			String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
			// 获取每页显示多少记录数
			int itemsInPage = Integer.parseInt((String)SessionUtils.getAttribute(request , "RowCountPerPage"));
			// 当从前台获取的总记录数不为空时，代表要显示所有的数据，所以将每页显示的数据数设置成总记录数
			if(strItemsInPage != null)
			{
				itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
			}
			// 获取翻页行为
			String action = ParamUtils.getParameter(request, "pageAction", true);
			// 如果获取的翻页行为为空，则默认翻到第一页
			if("".equals(action))
			{
				action = PageAction.FIRST.toString();
			}
			// 获取跳页的页数，默认也是第一页
			int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
			
			// 获取操作人姓名
			String cx_name = ParamUtils.getParameter(request, "cx_name", true);
			// 获取操作开始时间
			String cx_stime = ParamUtils.getParameter(request, "cx_stime", true);
			// 获取操作结束时间
			String cx_etime = ParamUtils.getParameter(request, "cx_etime", true);
			// 获取操作内容
			String cx_opecontent = ParamUtils.getParameter(request, "cx_opecontent", true);
			
			// 获取操作类型
			String cx_opetype = ParamUtils.getParameter(request, "cx_opetype", true);
			// 转换操作类型到整型
			String cx_opetypeInt = "";
			if(null!=cx_opetype && !"".equals(cx_opetype) && !cx_opetype.equals("全部")){
				 cx_opetypeInt = String.valueOf((DictionaryManage.getOpeType(cx_opetype)));
			}
			if(null != cx_stime && !"".equals(cx_stime)){
				cx_stime += " 00:00:00";
			}
			if(null != cx_etime && !"".equals(cx_etime)){
				cx_etime += " 23:59:59";
			}
			
			// 设置查询条件
			Collection <QueryCond>queryConds = new ArrayList<QueryCond>();
			queryConds.add(new QueryCond("SystemLog.oper" , "String" , "like" , cx_name));
			queryConds.add(new QueryCond("SystemLog.opetime" , "String" , ">=" , cx_stime));
			queryConds.add(new QueryCond("SystemLog.opetime" , "String" , "<=" , cx_etime));
			queryConds.add(new QueryCond("SystemLog.content" , "String" , "like" , cx_opecontent));
			queryConds.add(new QueryCond("SystemLog.type" , "String" , "=" , cx_opetypeInt));
			
			ListContainer lc = mgr.list(queryConds , currentPageInt , itemsInPage , action , jumpPage);
			
			request.setAttribute("cx_name", cx_name);
			request.setAttribute("cx_stime", ParamUtils.getParameter(request , "cx_stime" , true));
			request.setAttribute("cx_etime", ParamUtils.getParameter(request , "cx_etime" , true));
			request.setAttribute("cx_opecontent", cx_opecontent);
			request.setAttribute("cx_opetype", cx_opetype);
			request.setAttribute("lc", lc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("list");
	}

	/**
	 * 删除系统日志操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteWeekMonthALL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = ParamUtils.getParameter(request , "action" , true);
		boolean isDelete = false;
		System.out.println(action);
		if("deleteWeek".equals(action) || "deleteMonth".equals(action) || "deleteAll".equals(action)){
			isDelete = mgr.deleteWeekMonthALL(action);
		}
		if(isDelete)
			return this.list(mapping, form, request, response);
		else{
			return mapping.findForward("DialogBox");
		}
	}
	
	
}
