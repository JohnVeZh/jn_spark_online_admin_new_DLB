package com.business.v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.business.News.NewsMgr;
import com.business.Product.ProductMgr;
import com.business.v2.vo.TbSpecialExplainForm;
import com.easecom.common.framework.struts.BaseAction;
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

@SuppressWarnings("all")
public class SpecialExplainAction extends BaseAction {
	private SpecialExplainMgr mgr = new SpecialExplainMgr();
	private NewsMgr newsMgr = new NewsMgr();
	
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
            Collection queryConds = new ArrayList();
            if(!section.equals("") && !section.equals("all")) {
                queryConds.add(new QueryCond("section", "String", "=", section));
            }
            if(!trainingType.equals("") && !trainingType.equals("all")){
                queryConds.add(new QueryCond("trainingType", "String", "=", trainingType));
            } else {
                queryConds.add(new QueryCond("trainingType", "String", "in", "'1', '2', '3', '4'"));
            }
            ListContainer lc = mgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);
            request.setAttribute("section", section);
            request.setAttribute("trainingType", trainingType);

            return mapping.findForward("SpecialExplain_list");
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
			for (int i = 0; i < ids.length; i++) {
				String name = Tool.getValue("select title from tb_special_explain where id='"+ids[i]+"'");
				Tool.AddLog(sessionContainer.getUserId(), username, "删除专项讲解,名称:"+name, "1", ipaddress);
				Tool.execute("delete from tb_special_explain where id = '"+ids[i]+"'");
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
	
    public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			return mapping.findForward("SpecialExplain_add");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取增加页面出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TbSpecialExplainForm tb = (TbSpecialExplainForm) form;
            tb.setId(UUID.randomUUID().toString().replace("-",""));
            tb.setCreateTime(new Date());
            
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username, "添加专项讲解,名称:"+tb.getTitle(), "1", ipaddress);
            Tool.testReflect_admin(tb);
            mgr.add(tb);
            
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
	
    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			request.setAttribute("item", mgr.view(id));
			return mapping.findForward("SpecialExplain_view");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			WebDialogBox dialog = new WebDialogBox(1, "错误", "获取数据时出错", "返回", "javascript:window.history.back()");
			request.setAttribute("DialogBox", dialog);
			return mapping.findForward("DialogBox");
		}
	}
    
    public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id", true);
			request.setAttribute("item", mgr.view(id));
			return mapping.findForward("SpecialExplain_update");
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
            TbSpecialExplainForm tb = (TbSpecialExplainForm) form;
            
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username, "修改专项讲解,名称:"+tb.getTitle(), "1", ipaddress);
            mgr.update(tb);
            
            map.put("result", true);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            map.put("result",false);
        }

        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
    
    public ActionForward newsSelector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
    	if(null==sessionContainer)
    		sessionContainer=new SessionContainer();
    	
    	ProductMgr pmgr = new ProductMgr();
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
    		queryConds.add(new QueryCond("News.title", "string", "like", title));
    		queryConds.add(new QueryCond("News.isDel", "number", "=", "0"));
    		ListContainer lc = newsMgr.list(queryConds, currentPageInt, itemsInPage, action, jumpPage);
    		
    		request.setAttribute("lc", lc);
    		request.setAttribute("title", title);
    		request.setAttribute("srcpage", request.getParameter("srcpage"));
    		
    		return mapping.findForward("NewsSelector");
    	} catch (Exception ex) {
    		log.error(ex.getMessage(), ex);
    		WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回", "javascript:window.history.back()");
    		request.setAttribute("DialogBox", dialog);
    		return mapping.findForward("DialogBox");
    	}
    }
}
