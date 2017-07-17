package com.business.NewQuestionsPapersTopic; 


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
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubject;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoice;
import com.business.NewQuestionsPapersTopic.NewQuestionsPapersTopic;
import com.business.NewQuestionsPapersTopic.NewQuestionsPapersTopicActionForm;
import com.business.NewQuestionsPapersTopic.NewQuestionsPapersTopicMgr;
import com.business.NewQuestionsPapersTopicChoice.NewQuestionsPapersTopicChoice;
import com.business.NewQuestionsPapersTopicChoice.NewQuestionsPapersTopicChoiceActionForm;
import com.business.NewQuestionsPapersTopicChoice.NewQuestionsPapersTopicChoiceMgr;

public class NewQuestionsPapersTopicAction extends BaseAction{
	 NewQuestionsPapersTopicMgr mgr = new NewQuestionsPapersTopicMgr();
	 NewQuestionsPapersTopicChoiceMgr nqptcmgr=new NewQuestionsPapersTopicChoiceMgr();

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
			String nqpttId = ParamUtils.getParameter(request, "nqpttId", false);
			//String loginName = ParamUtils.getParameter(request, "loginName", false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			
		 
			 
			//
			queryConds.add(new QueryCond("NewQuestionsPapersTopic.nqpttId", "String", "=", nqpttId));
			queryConds.add(new QueryCond("NewQuestionsPapersTopic.isDel", "number", "=","0"));
			 

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			request.setAttribute("nqpttId", nqpttId);
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

			request.setAttribute("NewQuestionsPapersTopicActionForm", mgr.view(ID));

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

		NewQuestionsPapersTopicActionForm vo = (NewQuestionsPapersTopicActionForm) form;
		try {
			NewQuestionsPapersTopicActionForm nqpt=mgr.view(vo.getId());
			//mgr.update(vo);
       
			if(null != nqpt){
				nqpt.settNum(vo.gettNum());
				nqpt.setScore(vo.getScore());
				nqpt.settSubject(vo.gettSubject());
				nqpt.settAnalysis(vo.gettAnalysis());
				nqpt.settText(vo.gettText());
				nqpt.settUrl(vo.gettUrl());
				nqpt.setSort(vo.getSort());
				mgr.update(nqpt);
				
				
				String arrbills = ParamUtils.getParameter(request, "arrbills", true);
				String str[] = arrbills.split(",");
				for (String string : str) {
					String[] arrb = string.split("/");
				//	System.out.println(arrb.length);
					if(null!=arrb[4] && !"".equals(arrb[4]) && !"noId".equals(arrb[4])){
						NewQuestionsPapersTopicChoiceActionForm nqptc= nqptcmgr.view(arrb[4]);
						if(null!= nqptc){
							if(null!=arrb[0])
								nqptc.setcName(StrUtils.IosToUtf8(arrb[0]));
							if(null!=arrb[1])
								nqptc.setcContent(StrUtils.IosToUtf8(arrb[1]));
							if(null!=arrb[2])
								nqptc.setcSort(Integer.parseInt(arrb[2]));
							if(null!=arrb[3] && !"".equals(arrb[3]));
							   nqptc.setcIsAnswer(Integer.parseInt(arrb[3]));
						   nqptcmgr.update(nqptc);
						}
					}else{
						NewQuestionsPapersTopicChoiceActionForm nqptc = new NewQuestionsPapersTopicChoiceActionForm();
						nqptc.setNqptId(vo.getId());
						if(null!=arrb[0])
							nqptc.setcName(StrUtils.IosToUtf8(arrb[0]));
						if(null!=arrb[1])
							nqptc.setcContent(StrUtils.IosToUtf8(arrb[1]));
						if(null!=arrb[2] && !"".equals(arrb[2]));
						nqptc.setcSort(Integer.parseInt(arrb[2]));
						if(null!=arrb[3] && !"".equals(arrb[3]));
						nqptc.setcIsAnswer(Integer.parseInt(arrb[3]));
						nqptcmgr.add(nqptc);
						
					}
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

			NewQuestionsPapersTopicActionForm vo = mgr.view(ID);
			//vo.setPasswordold(vo.getPassword());
			String  nqpttId=ParamUtils.getParameter(request, "nqpttId",false);
			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("nqpttId", nqpttId);
			request.setAttribute("NewQuestionsPapersTopicActionForm", vo);

			//查询选项
			List<NewQuestionsPapersTopicChoice> choices = Tool.findListByHql("from NewQuestionsPapersTopicChoice where nqptId = '"+vo.getId()+"' and isDel=0 order by cSort ");
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
			NewQuestionsPapersTopicActionForm vo = (NewQuestionsPapersTopicActionForm) form;
			NewQuestionsPapersTopic nqpt=new NewQuestionsPapersTopic();
			this.copyProperties(nqpt, vo);
			//vo.setType("1");
			//mgr.add(vo);
			String id = mgr.add(nqpt);
			
			String arrbills = ParamUtils.getParameter(request, "arrbills", true);
			String str[] = arrbills.split(",");
			for (String string : str) {
				String[] arrb = string.split("/");
				NewQuestionsPapersTopicChoice nqptc= new NewQuestionsPapersTopicChoice();
				nqptc.setNqptId(id);
				if(null!=arrb[0])
					nqptc.setcName(StrUtils.IosToUtf8(arrb[0]));
				if(null!=arrb[1])
					nqptc.setcContent(StrUtils.IosToUtf8(arrb[1]));
				if(null!=arrb[2] && !"".equals(arrb[2]));
				    nqptc.setcSort(Integer.parseInt(arrb[2]));
				if(null!=arrb[3] && !"".equals(arrb[3]));
				    nqptc.setcIsAnswer(Integer.parseInt(arrb[3]));
//				Tool.testReflect_admin(mpthsu);
					nqptcmgr.add(nqptc);
					
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

			NewQuestionsPapersTopicActionForm vo = new NewQuestionsPapersTopicActionForm();			
			SessionContainer sessionContainer = (SessionContainer) request
			.getSession().getAttribute("SessionContainer");
			if(sessionContainer==null){
				sessionContainer = new SessionContainer();
			}
			String nqpttId = ParamUtils.getParameter(request, "nqpttId", false);
			System.out.println(nqpttId);
			request.setAttribute("nqpttId", nqpttId);
			request.setAttribute("NewQuestionsPapersTopicActionForm", vo);
			
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
				 
			//Tool.execute("delete from new_Questions_papers_topic where id = '"+ids[i]+"'");
			Tool.execute("update new_Questions_papers_topic set is_del = 1 where id = '"+ids[i]+"'");
			 	 
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

			String ID = ParamUtils.getParameter(request, "Id", true);
				 
			Tool.execute("update new_Questions_papers_topic set is_del = 1 where id = '"+ID+"'");
			return list(mapping, form, request, response);
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
					"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
	
	public ActionForward AjasRealdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");	
		System.out.println(id);
		if (id != null && !"".equals(id)) {
			try {
				boolean isSuc = Tool.execute("update new_Questions_papers_topic_choice set is_del = 1 where id='"+id+"'");
				
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
		map.put("interface_name", "NewQuestionsPapersTopic_realdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
	
	/**
	 * 异步修改
	 * 方法功能说明：  
	 * 创建：2016年6月24日 by Zzc   
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
	public ActionForward AjasRealUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succeed", "001");
		String id = ParamUtils.getParameter(request, "id");	
		String num = ParamUtils.getParameter(request, "num");	
		if (id != null && !"".equals(id)) {
			try {
				NewQuestionsPapersTopic nqpt = (NewQuestionsPapersTopic)mgr.getObjectByHql("from NewQuestionsPapersTopic where id='"+id+"'");
				if(nqpt!=null){
					if(null!=num && !"".equals(num)){
						nqpt.settNum(Integer.parseInt(num));
						boolean result = mgr.update(nqpt);
						if (result) {
							// 成功
							map.put("succeed", "000");
						} 
					}
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
		map.put("interface_name", "MatchedPapersTopicHearingSubject_realdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}
}
