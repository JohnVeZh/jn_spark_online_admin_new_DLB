package com.business.MatchedPapersTopicReadSubject; 


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
import com.easecom.common.util.ExcelUtils;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.MD5;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StrUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.exception.SystemException;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoice;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoiceActionForm;
import com.business.MatchedPapersTopicReadSubject.MatchedPapersTopicReadSubject;
import com.business.MatchedPapersTopicReadSubject.MatchedPapersTopicReadSubjectActionForm;
import com.business.MatchedPapersTopicReadSubject.MatchedPapersTopicReadSubjectMgr;
import com.business.MatchedPapersTopicReadSubjectChoice.MatchedPapersTopicReadSubjectChoice;
import com.business.MatchedPapersTopicReadSubjectChoice.MatchedPapersTopicReadSubjectChoiceActionForm;
import com.business.MatchedPapersTopicReadSubjectChoice.MatchedPapersTopicReadSubjectChoiceMgr;

public class MatchedPapersTopicReadSubjectAction extends BaseAction{
	 MatchedPapersTopicReadSubjectMgr mgr = new MatchedPapersTopicReadSubjectMgr();
	 MatchedPapersTopicReadSubjectChoiceMgr mptrscMgr = new MatchedPapersTopicReadSubjectChoiceMgr();

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
			String mptrtId = ParamUtils.getParameter(request, "mptrtId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			queryConds.add(new QueryCond("MatchedPapersTopicReadSubject.mptrtId", "String", "=", mptrtId));
			queryConds.add(new QueryCond("MatchedPapersTopicReadSubject.isDel", "String", "=", "0"));
			
			
		 
			 
			//
			//queryConds.add(new QueryCond("user.loginName", "String", "=", loginName));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);
			request.setAttribute("mptrtId", mptrtId);
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

			request.setAttribute("MatchedPapersTopicReadSubjectActionForm", mgr.view(ID));

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

		MatchedPapersTopicReadSubjectActionForm vo = (MatchedPapersTopicReadSubjectActionForm) form;
		try {
			MatchedPapersTopicReadSubjectActionForm mptrs = mgr.view(vo.getId());
			if(null != mptrs){
				mptrs.setcTitle(vo.getcTitle());
				mptrs.setScore(vo.getScore());
				mptrs.setcAnalysis(vo.getcAnalysis());
				mptrs.setsNumber(vo.getsNumber());
				mgr.update(mptrs);
				request.setAttribute("mptrtId", vo.getMptrtId());
			}
			
			//修改选项信息
			String arrbills = ParamUtils.getParameter(request, "arrbills", true);
			String str[] = arrbills.split(",");
			for (String string : str) {
				String[] arrb = string.split("/");
				//System.out.println(arrb.length);
				if(null!=arrb[4] && !"".equals(arrb[4]) && !"noId".equals(arrb[4])){
					MatchedPapersTopicReadSubjectChoiceActionForm mpthsu = mptrscMgr.view(arrb[4]);
					if(null != mpthsu){
						if(null!=arrb[0])
							mpthsu.setcName(StrUtils.IosToUtf8(arrb[0]));
						if(null!=arrb[1])
							mpthsu.setcContent(StrUtils.IosToUtf8(arrb[1]));
						if(null!=arrb[2] && !"".equals(arrb[2]));
							mpthsu.setcSort(Integer.parseInt(arrb[2]));
						if(null!=arrb[3] && !"".equals(arrb[3]));
							mpthsu.setcIsAnswer(Integer.parseInt(arrb[3]));
							mptrscMgr.update(mpthsu);
					}
				}else{
					MatchedPapersTopicReadSubjectChoice mptrsu = new MatchedPapersTopicReadSubjectChoice();
					mptrsu.setMptrsId(mptrs.getId());
					if(null!=arrb[0])
						mptrsu.setcName(StrUtils.IosToUtf8(arrb[0]));
					if(null!=arrb[1])
						mptrsu.setcContent(StrUtils.IosToUtf8(arrb[1]));
					if(null!=arrb[2] && !"".equals(arrb[2]));
						mptrsu.setcSort(Integer.parseInt(arrb[2]));
					if(null!=arrb[3] && !"".equals(arrb[3]));
						mptrsu.setcIsAnswer(Integer.parseInt(arrb[3]));
						mptrscMgr.add(mptrsu);
				}
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

			MatchedPapersTopicReadSubjectActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String mptrtId = ParamUtils.getParameter(request, "mptrtId", false);
			request.setAttribute("mptrtId", mptrtId);
			request.setAttribute("MatchedPapersTopicReadSubjectActionForm", vo);
			
			//查询选项
			List<MatchedPapersTopicReadSubjectChoice> choices = (List<MatchedPapersTopicReadSubjectChoice>)mgr.getListByHql("from MatchedPapersTopicReadSubjectChoice where mptrsId = '"+vo.getId()+"' and isDel=0 order by cSort ", 0, 0);
			request.setAttribute("choices", choices);

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
			MatchedPapersTopicReadSubjectActionForm vo = (MatchedPapersTopicReadSubjectActionForm) form;
			//vo.setType("1");
			MatchedPapersTopicReadSubject mptrs = new MatchedPapersTopicReadSubject();
			mptrs.setcTitle(vo.getcTitle());
			mptrs.setScore(vo.getScore());
			mptrs.setcAnalysis(vo.getcAnalysis());
			mptrs.setsNumber(vo.getsNumber());
			mptrs.setMptrtId(vo.getMptrtId());
			String id = mgr.add(mptrs);
			
			String arrbills = ParamUtils.getParameter(request, "arrbills", true);
			String str[] = arrbills.split(",");
			for (String string : str) {
				String[] arrb = string.split("/");
				MatchedPapersTopicReadSubjectChoice mpthsu = new MatchedPapersTopicReadSubjectChoice();
				mpthsu.setMptrsId(id);
				if(null!=arrb[0])
					mpthsu.setcName(StrUtils.IosToUtf8(arrb[0]));
				if(null!=arrb[1])
					mpthsu.setcContent(StrUtils.IosToUtf8(arrb[1]));
				if(null!=arrb[2] && !"".equals(arrb[2]));
					mpthsu.setcSort(Integer.parseInt(arrb[2]));
				if(null!=arrb[3] && !"".equals(arrb[3]));
					mpthsu.setcIsAnswer(Integer.parseInt(arrb[3]));
//				Tool.testReflect_admin(mpthsu);
					mptrscMgr.add(mpthsu);
					
			}
			request.setAttribute("mptrtId",vo.getMptrtId());
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

			MatchedPapersTopicReadSubjectActionForm vo = new MatchedPapersTopicReadSubjectActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String mptrtId = ParamUtils.getParameter(request, "mptrtId", false);
			request.setAttribute("mptrtId", mptrtId);
			request.setAttribute("MatchedPapersTopicReadSubjectActionForm", vo);
			
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
				 
			Tool.execute("update matched_papers_topic_read_subject set is_del = 1 where id = '"+ids[i]+"'");
				 
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
	
	public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String id = ParamUtils.getParameter(request, "id", false);
				 
			Tool.execute("update matched_papers_topic_read_subject set is_del = 1 where id = '"+id+"'");
				 
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
	 * 删除歌词列
	 * 方法功能说明：  
	 * 创建：2016年5月31日 by Zzc   
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
	public ActionForward AjasRealdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");	
		if (id != null && !"".equals(id)) {
			try {
				boolean isSuc = Tool.execute("update matched_papers_topic_read_subject_choice set is_del = 1 where id = '"+id+"'");
				
				if (isSuc) {
					// 成功
					map.put("succeed", "000");
				} else {
					// 失败
					map.put("succeed", "001");
				}
			} catch (Exception e) {
				// 异常
				map.put("succeed", "003");
				e.printStackTrace();
			}
		} else {
			// 传值为空
			map.put("succeed", "501");
		}
		map.put("interface_name", "matched_papers_topic_lyric_AjasRealdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
