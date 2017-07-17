package com.business.TbCouponCode;

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
public class TbCouponCodeAction extends BaseAction {
        TbCouponCodeMgr mgr = new TbCouponCodeMgr();

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
                queryConds.add(new QueryCond("TbCouponCode.couponId", "String", "=", couponId));
                queryConds.add(new QueryCond("TbCouponCode.code", "String", "like", nameStr));
                ListContainer lc = mgr.list(queryConds, currentPageInt,
                        itemsInPage, action, jumpPage);
                request.setAttribute("couponId", couponId);
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
                request.setAttribute("TbCouponCodeActionForm", mgr.view(ID));
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

            TbCouponCodeActionForm vo = (TbCouponCodeActionForm) form;
            try {
                mgr.update(vo);
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
                TbCouponCodeActionForm vo = mgr.view(ID);
                //vo.setPasswordold(vo.getPassword());
                SessionContainer sessionContainer = (SessionContainer) request
                        .getSession().getAttribute("SessionContainer");
                if(sessionContainer==null){
                    sessionContainer = new SessionContainer();
                }
                request.setAttribute("TbCouponCodeActionForm", vo);
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
                String couponId = ParamUtils.getParameter(request, "couponId", true);
                String pcgLenth = ParamUtils.getParameter(request, "pcgLenth", true);
                String pcgNum = ParamUtils.getParameter(request, "pcgNum", true);
                Set<String> st = new HashSet<>();
                String codeStr = "";
                String sql = " insert into tb_coupon_code(id,code,coupon_id,use_num,create_time) values ";
                List ls =null;
                int error=0;
                int success = 0;
                if(StringUtils.isNumber(pcgNum) && StringUtils.isNumber(pcgLenth)){
                    //死循环，当生成的优惠码个数满足后直接break跳出循环,set去重
                    for(int i= 0;i<i+1;i++){
                        String code = RandomCode.getRandomChar(Integer.parseInt(pcgLenth));
                        st.add(code);
                        if(st.size() == Integer.parseInt(pcgNum)){
                            break;
                        }
                    }
                    for(String s : st){
                        codeStr +="'"+s+"',";
                    }
                    codeStr = codeStr.substring(0,codeStr.length()-1);
                    ls = mgr.SQLQuery("SELECT id FROM tb_coupon_code where id in ("+codeStr+")");
                    for(int i = 0;i<ls.size();i++){
                        Map mp = (Map) ls.get(i);
                        st.remove(mp.get("id").toString());
                    }
                    sql = " insert into tb_coupon_code(id,code,coupon_id,use_num,create_time) values ";
                    for(String s : st){
                        String id = UUID.randomUUID().toString().replaceAll("-","");
                        sql +="('"+id+"','"+s+"','"+couponId+"','0','"+DateUtils.getCurrDateTimeStr()+"'),";
                    }
                    sql = sql.substring(0,sql.length()-1);
                    success = mgr.SQLExecute(sql);
                    error = Integer.parseInt(pcgNum)-success;
                }
                map.put("err",error );
                map.put("suc",success );
                map.put("res", true);
            } catch (Exception ex) {
                map.put("res",false);
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
                String couponId = ParamUtils.getParameter(request, "couponId", true);
                TbCouponCodeActionForm vo = new TbCouponCodeActionForm();
                SessionContainer sessionContainer = (SessionContainer) request
                        .getSession().getAttribute("SessionContainer");
                if(sessionContainer==null){
                    sessionContainer = new SessionContainer();
                }

                request.setAttribute("TbCouponCodeActionForm", vo);
                request.setAttribute("couponId", couponId);

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
                    Tool.execute("delete from tb_coupon_code where id = '"+ids[i]+"'");
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
                String idVal = ParamUtils.getParameter(request, "idVal", true);
                Tool.execute("delete from tb_coupon_code where id = '"+idVal+"'");
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

