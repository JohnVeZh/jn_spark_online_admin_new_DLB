package com.business.TbCouponProduct;

import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.PreferentialCodeProduct.PreferentialCodeProduct;
import com.business.Product.ProductMgr;
import com.business.TbCouponProduct.TbCouponProduct;
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
public class TbCouponProductAction extends BaseAction {
    TbCouponProductMgr mgr = new TbCouponProductMgr();

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
            String pcId = ParamUtils.getParameter(request, "pcId", false);
            String serchType = ParamUtils.getParameter(request, "serchType", "");

            // 设置查询条件
            Collection queryConds = new ArrayList();
            queryConds.add(new QueryCond("TbCouponProduct.couponId", "String", "=", pcId));
            if(!serchType.equals("all")){
                queryConds.add(new QueryCond("TbCouponProduct.type", "String", "=", serchType));
            }
            ListContainer lc = mgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);
            request.setAttribute("pcId", pcId);
            request.setAttribute("lc", lc);
            List<TbCouponProduct> pcpList = lc.getList();
            List<Map> lm = new ArrayList<Map>();
            for (TbCouponProduct pcp : pcpList) {
                Map m = new HashMap();
                m.put("id", pcp.getId());
                m.put("contentId", pcp.getContentId());
                if(pcp.getType().equals("2")){
                    m.put("pName", Tool.getValue("select p_name from product where id='"+pcp.getContentId()+"'"));
                }else{
                    m.put("pName", Tool.getValue("select network_name from Network_video where id='"+pcp.getContentId()+"'"));
                }
                m.put("type", pcp.getType());
                if(pcp.getType().equals("2")){
                    m.put("typeName", "图书");
                }else{
                    m.put("typeName", "网课");
                }
                lm.add(m);
            }
            request.setAttribute("lm", lm);
            request.setAttribute("lmSize", lm.size());
            request.setAttribute("pcId", pcId);
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

            request.setAttribute("TbCouponProductActionForm", mgr.view(ID));

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
        TbCouponProductActionForm vo = (TbCouponProductActionForm) form;
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

            TbCouponProductActionForm vo = mgr.view(ID);
            //vo.setPasswordold(vo.getPassword());

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("TbCouponProductActionForm", vo);

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
            TbCouponProductActionForm vo = (TbCouponProductActionForm) form;
            mgr.add(vo);

            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
    //绑定图书商品
    public ActionForward addProduct(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String couponId = ParamUtils.getParameter(request, "pcId", true);
            String pId = ParamUtils.getParameter(request, "pId", true);
            String type = ParamUtils.getParameter(request, "type", true);
            TbCouponProductActionForm vo = new TbCouponProductActionForm();
            vo.setCouponId(couponId);
            vo.setContentId(pId);
            vo.setType(type);
            mgr.add(vo);

            map.put("result", true);
        } catch (Exception ex) {
            map.put("result",false);
        }
        JsonUtils.outputJsonResponse(response, map);
        return null;
    }
    /**
     * 批量绑定
     * 方法功能说明：
     * 创建：2016年7月29日 by Zzc
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
    public ActionForward addProducts(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String pcId = ParamUtils.getParameter(request, "pcId", true);
            String pId = ParamUtils.getParameter(request, "pId", true);
            String type = ParamUtils.getParameter(request, "type", true);
            String ids[] = pId.split(",");
            String deletePro = "";
            String insetSql = "insert into tb_coupon_product(id,type,content_id,coupon_id) values ";
            for (int i = 0; i < ids.length; i++) {
                String id = UUID.randomUUID().toString().replaceAll("-","");
                if(i == ids.length-1){
                    insetSql +="('"+id+"','"+type+"','"+ids[i]+"','"+pcId+"');";
                    deletePro += "'"+ids[i]+"'";
                }else{
                    insetSql +="('"+id+"','"+type+"','"+ids[i]+"','"+pcId+"'),";
                    deletePro += "'"+ids[i]+"',";
                }
            }
            String deleteSql = "delete from tb_coupon_product where coupon_id = '"+pcId+"' and content_id in ("+deletePro+")";
            mgr.addProducts(deleteSql,insetSql);
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

            TbCouponProductActionForm vo = new TbCouponProductActionForm();
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }

            request.setAttribute("TbCouponProductActionForm", vo);

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
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] ids = ParamUtils.getParameter(request, "idVal", true).split(",");
            String deleteids = "";
            for (int i = 0; i < ids.length; i++) {
                    if(i == ids.length-1){
                        deleteids+="'"+ids[i]+"'";
                    }else{
                        deleteids+="'"+ids[i]+"',";
                    }
            }
            String sql = "delete from tb_coupon_product where id in( "+deleteids+")";
            Tool.execute(sql);
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
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            String idVal = ParamUtils.getParameter(request, "idVal", true);
            Tool.execute("delete from tb_coupon_product where id = '"+idVal+"'");


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
     * 优惠卷绑定商品
     * 方法功能说明：
     * 创建：2016年7月29日 by Zzc
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
    public ActionForward p_list(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        ProductMgr pmgr = new ProductMgr();
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
            String pcId = ParamUtils.getParameter(request, "pcId", false);
            String nameStr = ParamUtils.getParameter(request, "nameStr", false);
            //查询已绑定的商品
            List<TbCouponProduct> lp = Tool.findListByHql("from TbCouponProduct where couponId='"+pcId+"' and type='2'");
            String lpId = "'"+pcId+"'";
            if(lp.size()>0){
                lpId = "'"+lp.get(0).getContentId()+"'";
            }
            for (int i = 1; i < lp.size(); i++) {
                lpId += ",'"+lp.get(i).getContentId()+"'";
            }

            // 设置查询条件
            Collection queryConds = new ArrayList();

            queryConds.add(new QueryCond("Product.pName", "String", "like", nameStr));
            queryConds.add(new QueryCond("Product.pIsDel", "number", "=", "0"));
            queryConds.add(new QueryCond("Product.id", "String", "not in", lpId));
            ListContainer lc = pmgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            request.setAttribute("lc", lc);
            request.setAttribute("nameStr", nameStr);
            request.setAttribute("pcId", pcId);


            return mapping.findForward("book");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }
    /**
     * 优惠券绑定网课
     * 方法功能说明：
     * 创建：2016年7月29日 by Zzc
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
    public ActionForward net_list(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        NetworkVideoMgr nmgr = new NetworkVideoMgr();
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
            String pcId = ParamUtils.getParameter(request, "pcId", false);

            String nvNamelike = ParamUtils.getParameter(request, "nvNamelike", false);
            String stateStr = ParamUtils.getParameter(request, "stateStr", false);

            //查询已绑定的网课
            List<TbCouponProduct> lp = Tool.findListByHql("from TbCouponProduct where couponId='"+pcId+"' and type='1'");
            String lpId = "'"+pcId+"'";
            if(lp.size()>0){
                lpId = "'"+lp.get(0).getContentId()+"'";
            }
            for (int i = 1; i < lp.size(); i++) {
                lpId += ",'"+lp.get(i).getContentId()+"'";
            }
            // 设置查询条件
            Collection queryConds = new ArrayList();

            queryConds.add(new QueryCond("NetworkVideo.networkName", "String", "like", nvNamelike));
            queryConds.add(new QueryCond("NetworkVideo.networkType", "String", "=", stateStr));
            queryConds.add(new QueryCond("NetworkVideo.isDel", "number", "=", "0"));
            queryConds.add(new QueryCond("NetworkVideo.id", "String", "not in", lpId));
            ListContainer lc = nmgr.list(queryConds, currentPageInt,
                    itemsInPage, action, jumpPage);

            request.setAttribute("lc", lc);

            request.setAttribute("nvNamelike", nvNamelike);
            request.setAttribute("stateStr", stateStr);
            request.setAttribute("pcId", pcId);


            return mapping.findForward("net");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }



}
