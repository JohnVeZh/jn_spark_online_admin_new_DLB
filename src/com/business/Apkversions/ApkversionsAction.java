package com.business.Apkversions;

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
import com.easecom.common.util.JsonUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.ParamUtils;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;
import com.easecom.common.util.Tool;
import com.easecom.common.util.WebDialogBox;

public class ApkversionsAction extends BaseAction {

    ApkversionsMgr mgr = new ApkversionsMgr();

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
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ApkversionsActionForm apkForm = (ApkversionsActionForm) form;

            if(apkForm.getType().equals("1")){
                apkForm.setApkname("IOS版");
            }else{
                apkForm.setApkname("安卓版");
            }
            mgr.add(apkForm);
            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
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
//            String orderCode=ParamUtils.getParameter(request,"orderCode");//退款订单编号

            // 设置查询条件
            Collection queryConds = new ArrayList();

//            queryConds.add(new QueryCond("Apkversions.orderCode", "String", "=", orderCode));


            ListContainer lc = mgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            request.setAttribute("lc", lc);

            List<Map<String,Object>> lm = new ArrayList<>();
            List<Apkversions> apkversions = lc.getList();
            for (Apkversions apkversion : apkversions) {
                Map m = new HashMap();
                m.put("id", apkversion.getId());
                m.put("apkName", apkversion.getApkname());			// 应用名称
                m.put("apkVersion", apkversion.getApkversion());	// 版本号
                m.put("versionName",apkversion.getVersionName());	// 版本名称
//                m.put("twoCode", apkversion.getTwoCode());
                m.put("type", apkversion.getType());				// 客户端类型：0 Android，1 iOS
                m.put("remark", apkversion.getRemark());			// 更新内容
                m.put("updateTime", apkversion.getUpdatetime());	// 更新时间
                m.put("apkUrl", apkversion.getApkurl());			// 商店地址
                m.put("fileSize", apkversion.getFilesize());		// 文件大小
                m.put("downloadUrl", apkversion.getDownloadUrl());	// 下载地址
                
//                m.put("clientType", apkversion.getClienttype());
//                m.put("apkVersion",apkversion.getApkversion());
                lm.add(m);
            }
//            System.out.println(" 1!");
            request.setAttribute("lm", lm);
            request.setAttribute("iosVersion", Tool.getValue("select t.value from sys_config t where t.alias='iosVersion' and t.type='iosVersion'"));
            return mapping.findForward("list");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    public ActionForward updateIosVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String iosVersion = request.getParameter("iosVersion");
        boolean flag = false;
        try {
            if(StringUtils.isNotBlank(iosVersion)) {
            	flag = Tool.execute("update sys_config set `value`='" + iosVersion + "' where `alias`='iosVersion' and `type`='iosVersion'");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JsonUtils.outputJsonResponse(response, flag);
        return null;
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
            ApkversionsActionForm apkversionsActionForm = mgr.view(ID);
            request.setAttribute("apk", apkversionsActionForm);
            return mapping.findForward("view");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取数据出错", "返回",
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

        ApkversionsActionForm apkversionsActionForm = (ApkversionsActionForm) form;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
//            if(apkversionsActionForm.getType().equals("1")){
//                apkversionsActionForm.setApkname("IOS版");
//            }else{
//                apkversionsActionForm.setApkname("安卓版");
//            }
            mgr.update(apkversionsActionForm);
            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }

    /**
     * 真实根据版本id删除记录
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) {

        try {

            String id = ParamUtils.getParameter(request, "id", true);

            mgr.delete(id);
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

            ApkversionsActionForm actionForm = new ApkversionsActionForm();
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("ApkversionsActionForm", actionForm);

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

            ApkversionsActionForm apkversionsActionForm = mgr.view(ID);

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("apk", apkversionsActionForm);

            return mapping.findForward("update");
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取更新页面时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
}
