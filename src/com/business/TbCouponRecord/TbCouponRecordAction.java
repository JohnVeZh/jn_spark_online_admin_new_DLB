package com.business.TbCouponRecord;

import com.business.TbCouponCode.TbCouponCodeMgr;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by liubaibing on 2016/11/7.
 */
public class TbCouponRecordAction extends BaseAction {
    TbCouponRecordMgr mgr = new TbCouponRecordMgr();

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
            String couponId = ParamUtils.getParameter(request, "couponId", false);
            String nameStr = ParamUtils.getParameter(request, "nameStr", false);
            // 设置查询条件
            Collection queryConds = new ArrayList();
            String sql = "select id from tb_coupon_code where coupon_id = '"+couponId+"'";
            if(null!=nameStr && !"".equals(nameStr))
                sql += " and code like '%"+nameStr+"%'";
            List<Map> lm = Tool.query(sql);
            String pcIds = "";
            for (int i = 0; i < lm.size(); i++) {
                if(i==0){
                    pcIds = "'"+lm.get(0).get("ID").toString()+"'";
                }else{
                    pcIds += ",'"+lm.get(i).get("ID")+"'";
                }

            }
            ListContainer lc;
            if(pcIds.length()>0){
                queryConds.add(new QueryCond("TbCouponRecord.couponCodeId", "String", "in", pcIds));
                lc = mgr.list(queryConds, currentPageInt,
                        itemsInPage, action, jumpPage);
            }else{
                lc=new ListContainer();

            }
            request.setAttribute("nameStr", nameStr);
            request.setAttribute("couponId", couponId);
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

            request.setAttribute("TbCouponRecordActionForm", mgr.view(ID));

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
        Map<String, Object> map = new HashMap<String, Object>();
        TbCouponRecordActionForm vo = (TbCouponRecordActionForm) form;
        try {
            mgr.update(vo);

            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
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

            TbCouponRecordActionForm vo = mgr.view(ID);
            //vo.setPasswordold(vo.getPassword());

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("TbCouponRecordActionForm", vo);

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
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TbCouponRecordActionForm vo = (TbCouponRecordActionForm) form;
            mgr.add(vo);

            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
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

            TbCouponRecordActionForm vo = new TbCouponRecordActionForm();
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("TbCouponRecordActionForm", vo);

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

                Tool.execute("delete from tb_coupon_record where id = '"+ids[i]+"'");

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
     * 单个删除
     * 方法功能说明：
     * 创建：2016年7月27日 by Zzc
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
    public ActionForward realdeleteById(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) {

        try {

            String idVal = ParamUtils.getParameter(request, "idVal", true);
            Tool.execute("delete from tb_coupon_record where id = '"+idVal+"'");


            return list(mapping, form, request, response);
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
}
