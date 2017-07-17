package com.business.HomeworkCorrecting;

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
import com.easecom.common.util.IpAddressUtil;
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
import com.business.HomeworkCorrecting.HomeworkCorrecting;
import com.business.HomeworkCorrecting.HomeworkCorrectingActionForm;
import com.business.HomeworkCorrecting.HomeworkCorrectingMgr;
import com.business.HomeworkCorrectingRecord.HomeworkCorrectingRecord;
import com.business.HomeworkCorrectingRecord.HomeworkCorrectingRecordActionForm;
import com.business.HomeworkCorrectingRecord.HomeworkCorrectingRecordMgr;
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubject;
import com.business.MatchedPapersTopicHearingSubject.MatchedPapersTopicHearingSubjectActionForm;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoice;
import com.business.MatchedPapersTopicHearingSubjectChoice.MatchedPapersTopicHearingSubjectChoiceActionForm;

public class HomeworkCorrectingAction extends BaseAction {
	HomeworkCorrectingMgr mgr = new HomeworkCorrectingMgr();
	HomeworkCorrectingRecordMgr hcrmgr = new HomeworkCorrectingRecordMgr();

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
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (null == sessionContainer)
			sessionContainer = new SessionContainer();
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
			 String nameStr = ParamUtils.getParameter(request, "nameStr", false);
			// String loginName = ParamUtils.getParameter(request, "loginName",
			// false);
			// 设置查询条件
			Collection queryConds = new ArrayList();
			if(null!=nameStr&&!"".equals(nameStr)){
				List<Map> lm = Tool.query("select username as UN from users where username like '%"+nameStr+"%'");
				String usersIds = "";
				if(lm.size()>0){
					usersIds = "'"+lm.get(0).get("UM")+"'";
				}else{
					queryConds.add(new QueryCond("HomeworkCorrecting.userId", "String", "in","''"));
				}
				for (int i = 1; i < lm.size(); i++) {
					usersIds += ",'"+lm.get(i).get("UM")+"'";
				}
				queryConds.add(new QueryCond("HomeworkCorrecting.userId", "String", "in",usersIds));
			}

			ListContainer lc = mgr.list(queryConds, currentPageInt,
					itemsInPage, action, jumpPage);

			// request.setAttribute("orgId", orgId);
			// request.setAttribute("name", name);
			 request.setAttribute("nameStr", nameStr);
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
			HomeworkCorrectingActionForm vo = mgr.view(ID);
			String username=Tool.getValue("select username from users where id='"+vo.getUserId()+"'");
			String name=Tool.getValue("select name from sys_user where id='"+vo.getSysUserId() +"'");
			List<HomeworkCorrectingRecord> choices=Tool.findListByHql("from HomeworkCorrectingRecord where hcId='"+ID+"'");
			
			request.setAttribute("choices", choices);
			request.setAttribute("username", username);
			request.setAttribute("name", name);
			request.setAttribute("HomeworkCorrectingActionForm", vo);

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

		String ID = ParamUtils.getParameter(request, "id", true);
		// System.out.println(ID);
		HomeworkCorrectingActionForm vo = (HomeworkCorrectingActionForm) form;
		try {
			vo.setIsCorrecting(1);
			mgr.update(vo);

			String arrbills = ParamUtils
					.getParameter(request, "arrbills", true);
			String str[] = arrbills.split(",");
			for (String string : str) {
				String[] arrb = string.split("/");
				HomeworkCorrectingRecord hcr = new HomeworkCorrectingRecord();
				hcr.setHcId(ID);
				if (null != arrb[0])
					hcr.setcContent(StrUtils.IosToUtf8(arrb[0]));
				if (null != arrb[1])
					hcr.setcTest(StrUtils.IosToUtf8(arrb[1]));

				// Tool.testReflect_admin(mpthsu);
				hcrmgr.add(hcr);
			}
			
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"批改作业,用户ID:"+vo.getUserId(), "1", ipaddress);
			
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
			String userId = ParamUtils.getParameter(request, "userId", true);
			String sysUserId = ParamUtils.getParameter(request, "sysUserId",
					true);
			HomeworkCorrectingActionForm vo = mgr.view(ID);
			// vo.setPasswordold(vo.getPassword());

			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}
			vo.setSysUserId(sessionContainer.getUserId());

			request.setAttribute("sysUserId", sysUserId);
			request.setAttribute("userId", userId);
			request.setAttribute("HomeworkCorrectingActionForm", vo);
            
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
			HomeworkCorrectingActionForm vo = (HomeworkCorrectingActionForm) form;
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}
			vo.setUserId(sessionContainer.getUserId());
			Tool.testReflect_admin(vo);
			// vo.setType("1");
			mgr.add(vo);
			
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			Tool.AddLog(sessionContainer.getUserId(), username,
					"添加作业,用户ID:"+vo.getUserId(), "1", ipaddress);
			
			
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
			String ID = ParamUtils.getParameter(request, "id", true);

			HomeworkCorrectingActionForm vo = new HomeworkCorrectingActionForm();
			SessionContainer sessionContainer = (SessionContainer) request
					.getSession().getAttribute("SessionContainer");
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}
			request.setAttribute("HomeworkCorrectingActionForm", vo);
			// System.out.println(ID);
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
			if (sessionContainer == null) {
				sessionContainer = new SessionContainer();
			}
			String ipaddress = IpAddressUtil.getIpAddr(request);
			String username = "admin";
			
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			for (int i = 0; i < ids.length; i++) {

				String name = Tool.getValue("select user_id from homework_correcting where id='"+ids[i]+"'");
				Tool.AddLog(sessionContainer.getUserId(), username,
						"删除作业,用户ID:"+name, "1", ipaddress);
				Tool.execute("delete from homework_correcting where id = '"
						+ ids[i] + "'");

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
	 * 批改作业
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		//String ID = ParamUtils.getParameter(request, "id", true);
		// System.out.println(ID);
		HomeworkCorrectingActionForm vo = (HomeworkCorrectingActionForm) form;
		try {
			HomeworkCorrectingActionForm mpthsj = mgr.view(vo.getId());
			vo.setIsCorrecting(1);
			if (null != mpthsj) {
				mpthsj.setContent(vo.getContent());
				mpthsj.setReviewed(vo.getReviewed());

				mgr.update(mpthsj);
			}
				mgr.update(vo);

				String arrbills = ParamUtils.getParameter(request, "arrbills",true);
				String str[] = arrbills.split(",");
				for (String string : str) {
					String[] arrb = string.split("/");
					//System.out.println(arrb.length);
					if (null != arrb[2] && !"".equals(arrb[2]) && !"noId".equals(arrb[2])) {
						HomeworkCorrectingRecordActionForm mpthsu = hcrmgr.view(arrb[2]);
						if (null != mpthsu) {
							if (null != arrb[0])
								mpthsu.setcContent(StrUtils.IosToUtf8(arrb[0]));
							if (null != arrb[1])
								mpthsu.setcTest(StrUtils.IosToUtf8(arrb[1]));
							hcrmgr.update(mpthsu);
						}
					}else {
							HomeworkCorrectingRecord hcr = new HomeworkCorrectingRecord();
							hcr.setHcId(vo.getId());
							if (null != arrb[0])
								hcr.setcContent(StrUtils.IosToUtf8(arrb[0]));
							if (null != arrb[1]){
								hcr.setcTest(StrUtils.IosToUtf8(arrb[1]));
							}
								
							hcrmgr.add(hcr);
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
	 * 预批改信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			String ID = ParamUtils.getParameter(request, "id", true);
			String userId = ParamUtils.getParameter(request, "userId", true);
			String sysUserId = ParamUtils.getParameter(request, "sysUserId",
					true);
			HomeworkCorrectingActionForm vo = mgr.view(ID);
			// vo.setPasswordold(vo.getPassword());

			if(null == vo.getHcValue() || "".equals(vo.getHcValue())){
				vo.setHcValue(vo.getContent());
			}
			request.setAttribute("sysUserId", sysUserId);
			request.setAttribute("userId", userId);
			request.setAttribute("HomeworkCorrectingActionForm", vo);

			// 查询选项
			List<HomeworkCorrectingRecord> record = Tool.findListByHql("from HomeworkCorrectingRecord where hcId = '"
							+ ID+ "'");
			request.setAttribute("record", record);
			return mapping.findForward("check");
		} catch (Exception ex) {

			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
					"javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}

	}

	/**
	 * 删除批改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward AjasRealdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = ParamUtils.getParameter(request, "id");
		System.out.println(id);
		if (id != null && !"".equals(id)) {
			try {
				boolean isSuc = Tool
						.execute("delete from Homework_correcting_record where id = '"
								+ id + "'");

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
		map.put("interface_name", "HomeworkCorrecting_realdelete");
		JsonUtils.outputJsonResponse(response, map);
		return null;
	}

}
