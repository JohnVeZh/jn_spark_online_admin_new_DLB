package com.business.TbCoupon;

import com.business.PreferentialCode.PreferentialCodeActionForm;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liubaibing on 2016/11/2.
 */
public class TbCouponAction extends BaseAction {
    TbCouponMgr mgr = new TbCouponMgr();

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
            String CouponType = ParamUtils.getParameter(request,
                    "CouponType", "");
            String CouponStatus = ParamUtils.getParameter(request,
                    "CouponStatus", "");
            String action = ParamUtils
                    .getParameter(request, "pageAction", true);
            if ("".equals(action))
                action = PageAction.FIRST.toString();
            int jumpPage = ParamUtils.getIntParameter(request, "jumpPage", 1);
            String name = ParamUtils.getParameter(request, "name");
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("title", "String", "like", name));
            if(!CouponType.equals("") && !CouponType.equals("all")){
                queryConds.add(new QueryCond("type", "String", "=", CouponType));
            }
            if(!CouponStatus.equals("") && !CouponStatus.equals("all")) {
                queryConds.add(new QueryCond("status", "String", "=", CouponStatus));
            }
            ListContainer lc = mgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            request.setAttribute("lc", lc);
            request.setAttribute("CouponType",CouponType);
            request.setAttribute("CouponStatus",CouponStatus);
            request.setAttribute("name",name);
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
            request.setAttribute("TbCouponActionForm", mgr.view(ID));
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
        TbCouponActionForm vo = (TbCouponActionForm) form;
        try {
            TbCouponActionForm po = mgr.view(vo.getId());
            if(po!=null){
                if(vo.getMaxUseNum()>0){
                    po.setMaxUseNum(vo.getMaxUseNum());
                }else{
                    po.setMaxUseNum(-1);
                }
                po.setEndTime(vo.getEndTime());
                po.setType(vo.getType());
                po.setStartTime(vo.getStartTime());
                po.setTitle(vo.getTitle());
                po.setStatus(vo.getStatus());
                po.setRelationType(vo.getRelationType());
                if(vo.getType()==1){
                    po.setDiscountRate(vo.getDiscountRate());
                    po.setMinMoney(0);
                    po.setDiscountMoney(0);
                }else if(vo.getType()==2){
                    po.setMinMoney(vo.getMinMoney());
                    po.setDiscountMoney(vo.getDiscountMoney());
                    po.setDiscountRate(0.0);
                }
                mgr.update(po);
            }

            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }

            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username,
                    "修改优惠码,标题:"+vo.getTitle(), "1", ipaddress);


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
            TbCouponActionForm tc = mgr.view(ID);
            //vo.setPasswordold(vo.getPassword());
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("TbCouponActionForm", tc);

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
            TbCouponActionForm tb = (TbCouponActionForm) form;
            tb.setCreateTime(DateUtils.getCurrDateTimeStr());
            tb.setId(UUID.randomUUID().toString().replace("-",""));
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username,
                    "添加优惠码,标题:"+tb.getTitle(), "1", ipaddress);

            Tool.testReflect_admin(tb);
            mgr.add(tb);
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

            TbCouponActionForm tb = new TbCouponActionForm();
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("TbCouponActionForm", tb);

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
            String ipaddress = IpAddressUtil.getIpAddr(request);
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if (null == sessionContainer) {
                sessionContainer = new SessionContainer();
            }
            String username = "admin";

            String[] ids = ParamUtils.getParameterValues(request, "id", true);
            for (int i = 0; i < ids.length; i++) {
                String name = Tool.getValue("select title  from tb_coupon where id='"+ids[i]+"'");
                Tool.AddLog(sessionContainer.getUserId(), username,
                        "删除优惠码,标题:"+name, "1", ipaddress);
                Tool.execute("delete from tb_coupon where id = '"+ids[i]+"'");

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
     * 导出优惠券
     *
     *
     *
     */

    public void exportExcel(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try {
            String CouponType = ParamUtils.getParameter(request,
                    "CouponType1", "");
            String CouponStatus = ParamUtils.getParameter(request,
                    "CouponStatus1", "");
            String name = ParamUtils.getParameter(request, "name1");
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("c.title", "String", "like", name));
            if(!CouponType.equals("") && !CouponType.equals("all")){
                queryConds.add(new QueryCond("c.type", "String", "=", CouponType));
            }
            if(!CouponStatus.equals("") && !CouponStatus.equals("all")) {
                queryConds.add(new QueryCond("c.status", "String", "=", CouponStatus));
            }
            String sql = "SELECT c.title, c.type, c.`status`, c.max_use_num, c.start_time, c.end_time, " +
                    "c.min_money,c.discount_money, c.discount_rate, c.create_time AS ctime, c.relation_type,"+
                    "cc.id as code_id, cc.`code`, cc.coupon_id, cc.use_num, cc.create_time as codetime "+
                    "FROM tb_coupon c INNER JOIN tb_coupon_code cc ON cc.coupon_id = c.id where 1=1 "+QueryHelper.toAndQuery(queryConds) +" order by ctime,codetime";
            List lc = mgr.SQLQuery(sql);
            List<String []> list = new ArrayList<String []>();
            for(int i = 0; i<lc.size();i++){
                Map map = (Map) lc.get(i);
                String type="",status="",max_use_num="",start_time="",end_time="",relation_type="";
                try {type = map.get("type").toString(); }catch (Exception e){System.out.println(e.getMessage()+"导出优惠券获取类型出错！" );};
                try {  status = map.get("status").toString(); }catch (Exception e){System.out.println(e.getMessage()+"导出优惠券获取状态出错！" );};
                try {  max_use_num = map.get("max_use_num").toString(); }catch (Exception e){System.out.println(e.getMessage()+"导出优惠券获取最大使用次数出错！" );};
                try {  start_time = map.get("start_time").toString().substring(0,10); }catch (Exception e){System.out.println(e.getMessage()+"导出优惠券获取开始时间出错！" );};
                try {  end_time = map.get("end_time").toString().substring(0,10); }catch (Exception e){System.out.println(e.getMessage()+"导出优惠券获取结束时间出错！" );};
                try {  relation_type = map.get("relation_type").toString(); }catch (Exception e){System.out.println(e.getMessage()+"导出优惠券获取绑定类型出错！" );};
                if(type.equals("1"))type="折扣券";else type="现金券";
                if(status.equals("0"))status="启用";else status="停用";
                if(max_use_num.equals("-1"))max_use_num="无限制";
                if(relation_type.equals("1"))relation_type="非全场";else if(relation_type.equals("2"))relation_type="全场网课";else if(relation_type.equals("3"))relation_type="全场图书";else if(relation_type.equals("4")) relation_type="全场通用";
                map.put("type",type);
                map.put("status",status);
                map.put("start_time",start_time);
                map.put("end_time",end_time);
                map.put("max_use_num",max_use_num);
                map.put("relation_type",relation_type);
                String [] str ={};
                list.add(str);
                lc.set(i,map);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestr = sdf.format(new Date());
            String fileName=new String(("优惠券-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
            String[] titles = { "标题", "类型","状态", "最大使用次数", "开始时间", "结束时间","满额","减额","折扣比例","优惠券创建时间","绑定类型","优惠码","已使用次数","优惠码生成时间"};
            String[] column = {"title","type","status","max_use_num","start_time","end_time","min_money","discount_money","discount_rate","ctime","relation_type","code","use_num","codetime"};
            ServletOutputStream outputStream = response.getOutputStream();
            ExcelUtil eu = new ExcelUtil();
            eu.ExportExcelConmon(titles,column,lc,fileName,response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);

        }
    }
}
