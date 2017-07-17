package com.business.v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.business.v2.vo.TbSpecialFeedbackForm;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.IpAddressUtil;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.QueryCond;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.WebDialogBox;

@SuppressWarnings("all")
public class SpecialFeedbackAction extends BaseAction {
	private SpecialFeedbackMgr mgr = new SpecialFeedbackMgr();
	
	/**
	 * 专项讲解列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute("SessionContainer");
		if(sessionContainer == null) {
			sessionContainer=new SessionContainer();
		}
		
		try {
            int currentPageInt = ParamUtils.getIntParameter(request, "currentPage", 1);
            String strItemsInPage = ParamUtils.getParameter(request, "totalItem", false);
            int itemsInPage = Integer.parseInt((String) SessionUtils.getAttribute(request, "RowCountPerPage"));
            if (strItemsInPage != null) {
                itemsInPage = ParamUtils.getIntParameter(request, "totalItem", 15);
            }
            String action = ParamUtils.getParameter(request, "pageAction", true);
            if ("".equals(action)) {
            	action = PageAction.FIRST.toString();
            }
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            
            String section = ParamUtils.getParameter(request, "section", "");
            String trainingType = ParamUtils.getParameter(request, "trainingType", "");
            String isSolved = ParamUtils.getParameter(request, "isSolved", "");
            Collection queryConds = new ArrayList();
            if(!section.equals("") && !section.equals("all")) {
                queryConds.add(new QueryCond("section", "String", "=", section));
            }
            if(!trainingType.equals("") && !trainingType.equals("all")){
                queryConds.add(new QueryCond("trainingType", "String", "=", trainingType));
            }
            if(!isSolved.equals("") && !isSolved.equals("all")){
                queryConds.add(new QueryCond("isSolved", "String", "=", isSolved));
            }
            ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);
            request.setAttribute("section", section);
            request.setAttribute("trainingType", trainingType);
            request.setAttribute("isSolved", isSolved);

            return mapping.findForward("SpecialFeedback_list");
		} catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
		}
	}
	    
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = "admin";
			String ipaddress = IpAddressUtil.getIpAddr(request);
			SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
			if (null == sessionContainer) {
				sessionContainer = new SessionContainer();
			}
			
			String[] ids = ParamUtils.getParameterValues(request, "id", true);
			mgr.delete(ids);
			
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
			"返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			request.setAttribute("item", mgr.view(id));
			return mapping.findForward("SpecialFeedback_update");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取数据时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TbSpecialFeedbackForm tb = (TbSpecialFeedbackForm) form;
            
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            mgr.update(tb);
            
            map.put("result", true);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(), "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            map.put("result",false);
        }

        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
}
