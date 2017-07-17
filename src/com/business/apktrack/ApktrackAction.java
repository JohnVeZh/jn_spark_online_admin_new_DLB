package com.business.apktrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;
import com.easecom.system.model.SysConfig;

public class ApktrackAction extends BaseAction {
	private static LogUtils logger = LogUtils.getLogger(ApktrackAction.class);
	private ApktrackMgr trackMgr = new ApktrackMgr();

	public ActionForward apktrackData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
            String startTime = ParamUtils.getParameter(request, "startTime", false);
            String endTime = ParamUtils.getParameter(request, "endTime", false);
            
            List<String> areaList = new ArrayList<String>();
            List<Map<String, Object>> dataList = trackMgr.apktrackData(startTime, endTime, areaList);

            request.setAttribute("startTime", startTime);
            request.setAttribute("endTime", endTime);
            request.setAttribute("areaList", areaList);
            request.setAttribute("dataList", dataList);

            return mapping.findForward("apktrackData");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "统计数据出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
		}
	}
	
	public ActionForward channelList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			@SuppressWarnings("unchecked")
            List<Map<String, String>> channelList = Tool.query("select t.id, t.`name`, t.`value`, t.sort from sys_config t where t.type='APK_DOWNLOAD_CHANNEL' order by t.sort");
            request.setAttribute("channelList", channelList);
            return mapping.findForward("channel_list");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "查询渠道出错", "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
		}
	}

    public ActionForward channelAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String name = ParamUtils.getParameter(request, "name", false);
            String value = ParamUtils.getParameter(request, "value", false);
            int sort = ParamUtils.getIntParameter(request, "sort", 0);
            
        	SysConfig channel = new SysConfig();
        	channel.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        	channel.setType("APK_DOWNLOAD_CHANNEL");
        	channel.setSort(sort);
        	channel.setName(name);
        	channel.setValue(value);
            trackMgr.save(channel);
            
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

    public ActionForward channelDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	String[] ids = ParamUtils.getParameterValues(request, "id");
        	if(ids != null) {
        		for(String id : ids) {
        			Tool.execute("delete from sys_config where id='" + id + "'");
        		}
        	}
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
	
	public ActionForward channelPreAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("channel_add");
	}
}
