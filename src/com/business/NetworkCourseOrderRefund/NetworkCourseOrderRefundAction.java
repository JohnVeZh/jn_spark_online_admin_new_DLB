package com.business.NetworkCourseOrderRefund;

import com.business.NetworkCourse.NetworkCourse;
import com.business.NetworkCourse.NetworkCourseActionForm;
import com.business.NetworkCourseOrder.*;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.ProductOrderLogistics.ProductOrderLogistics;
import com.business.Users.Users;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by john on 2017/4/10.
 */
public class NetworkCourseOrderRefundAction extends BaseAction implements Serializable {

    NetworkCourseOrderRefundMgr mgr = new NetworkCourseOrderRefundMgr();
    NetworkCourseView networkCourseView = new NetworkCourseView();

    /**
     * 列表展示
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        SessionContainer sessionContainer = (SessionContainer) request.getSession().getAttribute("SessionContainer");
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


            //设置查询条件
            String StatrSel = ParamUtils.getParameter(request, "StatrSel", false);
            String ncLevelTypeName = ParamUtils.getParameter(request, "ncLevelTypeName", false);
            String ncLevelName = ParamUtils.getParameter(request, "ncLevelName", false);
            String starttime = ParamUtils.getParameter(request, "starttime");//开始时间
            String endtime = ParamUtils.getParameter(request, "endtime");//结束时间
            String shouName = ParamUtils.getParameter(request, "shouName");//收货人
            String orderCode = ParamUtils.getParameter(request, "orderCode");//订单号
            String ncName = ParamUtils.getParameter(request, "ncName");//课程名称
            String phone = ParamUtils.getParameter(request, "telephone");//手机号
            String mobile = ParamUtils.getParameter(request,"mobile");
            String phoneType = ParamUtils.getParameter(request,"phone");

            String username = ParamUtils.getParameter(request, "username");//购货人
            String address = ParamUtils.getParameter(request, "address");//地址
            String provinceIdStr = ParamUtils.getParameter(request, "provinceIdStr", false);
            String cityIdStr = ParamUtils.getParameter(request, "cityIdStr", false);
            String areaIdStr = ParamUtils.getParameter(request, "areaIdStr", false);

            String userId = "";
            Users us = (Users) mgr.getObjectByHql("from Users where username='" + username + "'");
            if (!"".equals(us) && null != us) {
                userId = us.getId();
            }
            //查询条件语句
            Collection queryConds = new ArrayList();
            Collection queryConds1 = new ArrayList();
            Collection queryConds2 = new ArrayList();
            if (null != StatrSel && !"".equals(StatrSel)) {
                if ("check_ing".equals(StatrSel) || "check_passed".equals(StatrSel) || "pay_finished".equals(StatrSel)) {
                    queryConds.add(new QueryCond("check_status", "String", "=", StatrSel));
                }
            }
            if (null != ncLevelName && !"".equals(ncLevelName)) {
                queryConds1.add(new QueryCond("nc_level", "String", "=", ncLevelName));
            }
            if (null != ncLevelTypeName && !"".equals(ncLevelTypeName)) {
                queryConds1.add(new QueryCond("nc_level_type", "String", "=", ncLevelTypeName));
            }
            if (null != starttime && !"".equals(starttime) || null != endtime && !"".equals(endtime)) {
                queryConds.add(new QueryCond("createtime", "String", ">=", starttime));
                queryConds.add(new QueryCond("createtime", "String", "<=", endtime));
            }
            queryConds2.add(new QueryCond("order_code", "String", "=", orderCode));
            queryConds1.add(new QueryCond("nc_name", "String", "like", ncName));
            queryConds2.add(new QueryCond("consignee", "String", "=", shouName));
            if (mobile != null){
                Users uu = (Users) mgr.getObjectByHql("from Users where mobile = '"+mobile+"'");
                if(null != uu && !"".equals(uu)){
                    queryConds2.add(new QueryCond("user_id","String","=",uu.getId()));
                }else{
                    queryConds2.add(new QueryCond("user_id","String","=","\""+"\""));
                }
            }else{
                queryConds2.add(new QueryCond("telephone","String","=",phone));
            }

            ListContainer lc = mgr.list(queryConds, queryConds1, queryConds2, currentPageInt,
                    itemsInPage, action, jumpPage);

            List ll = lc.getList();
            List<Map> la = new ArrayList<Map>();
            List<NetworkCourseOrderRefund> ncorList = lc.getList();
            for (NetworkCourseOrderRefund ncor : ncorList) {
                List<NetworkCourseOrder> ncoList = Tool.findListByHql("FROM NetworkCourseOrder WHERE orderCode = '" + ncor.getOrderCode()+"'");
                NetworkCourseOrder nco = null;
                Map pp = new HashMap();
                pp.put("Id", ncor.getId());
                pp.put("orderCode", ncor.getOrderCode());
                pp.put("createtime", ncor.getCreateTime().replaceAll("-", "/"));
                pp.put("fee",ncor.getFee());
                if ("check_ing".equals(ncor.getCheckStatus())) {
                    pp.put("OrderState", "退款审核中");
                }
                if ("check_passed".equals(ncor.getCheckStatus())) {
                    pp.put("OrderState", "退款中");
                }
                if ("pay_finished".equals(ncor.getCheckStatus())) {
                    pp.put("OrderState", "已退款");
                }
                if (ncoList.size() > 0) {
                    nco = ncoList.get(0);
                    String pName = "";
                    String cityName = "";
                    String areaName = "";

                    ProductOrderLogistics productOrderLogistics = (ProductOrderLogistics) mgr.getObjectByHql("from ProductOrderLogistics where orderHistory = '" + nco.getId() + "'");
                    if (productOrderLogistics != null){
                        pp.put("productOrderLogistics",productOrderLogistics);
                    }

                    String type = "";
                    if (nco.getType() == 1 || nco.getType() == 2){
                        type = "网课1.0";
                    }
                    if (nco.getType() == 3 || nco.getType() == 4){
                        type = "网课2.0";
                    }
                    pp.put("type",type);

                    pp.put("userName", nco.getConsignee());

                    pp.put("account", Tool.getValue("select account from users where id='" + nco.getUserId() + "'"));

                    if (nco.getProvinceId() != null && nco.getProvinceId() != "") {
                        pName = Tool.getValue("select pname from province where provinceID = '" + nco.getProvinceId() + "'");
                    }
                    if (nco.getCityId() != null && nco.getCityId() != "") {
                        cityName = Tool.getValue("select city from city where cityID= '" + nco.getCityId() + "'");
                    }
                    if (nco.getAreaId() != null && nco.getAreaId() != "") {
                        areaName = Tool.getValue("select area from area where areaID = '" + nco.getAreaId() + "'");
                    }
                    if (null != nco.getAddress()) {
                        if (nco.getAddress().contains("区") || nco.getAddress().contains("县") || nco.getAddress().contains("省") || nco.getAddress().contains("市")) {
                            if (nco.getAddress().lastIndexOf("区") > 0) {
                                pp.put("addressPost", nco.getAddress().substring(0, nco.getAddress().indexOf("区") + 1));
                                pp.put("address", nco.getAddress().substring(nco.getAddress().indexOf("区") + 1));
                            }
                            if (nco.getAddress().lastIndexOf("市") > 0) {
                                pp.put("addressPost", nco.getAddress().substring(0, nco.getAddress().indexOf("市") + 1));
                                pp.put("address", nco.getAddress().substring(nco.getAddress().indexOf("市") + 1));
                            }
                            if (nco.getAddress().lastIndexOf("县") > 0) {
                                pp.put("addressPost", nco.getAddress().substring(0, nco.getAddress().indexOf("县") + 1));
                                pp.put("address", nco.getAddress().substring(nco.getAddress().indexOf("县") + 1));
                            }
                        } else {
                            pp.put("addressPost", pName + cityName + areaName);
                            pp.put("address", nco.getAddress());
                        }
                    } else {
                        pp.put("addressPost", pName + cityName + areaName);
                        pp.put("address", nco.getAddress());
                    }

                    pp.put("telephone", nco.getTelephone());

                    pp.put("userId", nco.getUserId());
                    String status = "";

                    pp.put("price", nco.getPrice());
                    pp.put("payPrice", nco.getPayPrice());
                    List<ProductOrderDetails> detailsList = Tool.findListByHql("from ProductOrderDetails where productType in (1,2) and productOrderId = '" + nco.getId() + "'");
                    ProductOrderDetails pod = null;
                    if (detailsList.size() > 0) {
                        pod = detailsList.get(0);
                        NetworkCourseActionForm net = networkCourseView.view(pod.getProductId());
                        pp.put("ncName", net.getNcName());
                        if ("0".equals(String.valueOf(net.getIsGiftBook()))) {
                            pp.put("isGiftBook", "无");
                        }
                        if ("1".equals(String.valueOf(net.getIsGiftBook()))) {
                            pp.put("isGiftBook", "有");
                        }
                    }
                }
//                pp.put("userName",Tool.getValue("select username from users where id='"+nco.getUserId()+"'"));
//                pp.put("consignee",nco.getConsignee());
                la.add(pp);
            }

            //获取课程大类列表
            String ncLevelStatus = Tool.getList("select value,name from sys_config where type='NC_LEVEL'order by sort asc", "name", "value");
            if (!"".equals(ncLevelName) && null != ncLevelName) {
                ncLevelStatus = Tool.getList("select value,name from sys_config where type='NC_LEVEL' order by sort asc", "name", "value", ncLevelName);
            }
            //获得课程级别列表
            String ncLevelTypeStatus = Tool.getList("select value,name from sys_config where type='NC_LEVEL_TYPE' order by sort asc"
                    , "name", "value");
            if (!"".equals(ncLevelTypeName) && null != ncLevelTypeName) {
                ncLevelTypeStatus = Tool.getList("select value,name from sys_config where type='NC_LEVEL_TYPE' order by sort asc"
                        , "name", "value", ncLevelTypeName);
            }
            //获取联系电话列表
            String phoneType1 = "<option  value=\"telephoneShow\" >收货人手机号</option><option  value=\"mobileShow\" >登陆账号</option>";
            if (phoneType != null){
                if (phoneType.equals("telephoneShow")){
                    phoneType1 = "<option  value=\"telephoneShow\" selected=\"selected\" >收货人手机号</option><option  value=\"mobileShow\" >登陆账号</option>";
                }
                if (phoneType.equals("mobileShow")){
                    phoneType1 = "<option  value=\"telephoneShow\" >收货人手机号</option><option  value=\"mobileShow\" selected=\"selected\" >登陆账号</option>";
                }
            }
            request.setAttribute("mList", la);
            request.setAttribute("starttime", starttime);
            request.setAttribute("endtime", endtime);
            request.setAttribute("orderStatus", StatrSel);
            request.setAttribute("ncLevelStatus", ncLevelStatus);
            request.setAttribute("ncLevelTypeStatus", ncLevelTypeStatus);
            request.setAttribute("shouName", shouName);
            request.setAttribute("telephone", phone);
            request.setAttribute("ncName", ncName);
            request.setAttribute("orderCode", orderCode);
            request.setAttribute("mobile",mobile);
            request.setAttribute("phoneType",phoneType1);
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
            NetworkCourseOrderRefundActionForm ncorForm = mgr.view(ID);
            request.setAttribute("NetworkCourseOrderRefundActionForm", ncorForm);
            List<NetworkCourseOrder> ncoList = Tool.findListByHql("from NetworkCourseOrder where orderCode = '" + ncorForm.getOrderCode()+"'");
            NetworkCourseOrderActionForm networkCourseOrderActionForm = new NetworkCourseOrderActionForm();
            if (null != ncoList && ncoList.size() > 0) {
                NetworkCourseOrder networkCourseOrder = ncoList.get(0);
                ConvertUtils.register(new SqlDateConverter(), java.util.Date.class);
                Tool.copyProperties(networkCourseOrderActionForm, networkCourseOrder);
            }
            request.setAttribute("NetworkCourseOrderActionForm", networkCourseOrderActionForm);
            ProductOrderLogistics productOrderLogistics = (ProductOrderLogistics) mgr.getObjectByHql("from ProductOrderLogistics where orderHistory = '" + networkCourseOrderActionForm.getId() + "'");
            if (productOrderLogistics != null){
                request.setAttribute("productOrderLogistics",productOrderLogistics);
            }
            List<ProductOrderDetails> podList = Tool.findListByHql("from ProductOrderDetails where id = " + "'" + ncorForm.getProductOrderDetailsId() + "'");
            NetworkCourseActionForm networkCourseActionForm = new NetworkCourseActionForm();
            NetworkCourse networkCourse = new NetworkCourse();
            if (null != podList && podList.size() > 0) {
                ProductOrderDetails productOrderDetails = podList.get(0);
                List<NetworkCourse> ncList = Tool.findListByHql("from NetworkCourse where id = " + "'" + productOrderDetails.getProductId() + "'");
                if (null != ncList && ncList.size() > 0) {
                    networkCourse = ncList.get(0);
//                    ConvertUtils.register(new SqlDateConverter(), java.sql.Date.class);
//                    BeanUtils.copyProperties(networkCourseActionForm , networkCourse);
                }
            }
            request.setAttribute("NetworkCourseActionForm", networkCourse);
            String pca = Tool.getValue("select pname from province where provinceID = '" + networkCourseOrderActionForm.getProvinceId() + "'");
            pca = pca + Tool.getValue("select city from city where cityID = '" + networkCourseOrderActionForm.getCityId() + "'");
            pca = pca + Tool.getValue("select area from area where areaID = '" + networkCourseOrderActionForm.getAreaId() + "'");
            if (networkCourseOrderActionForm.getAddress() != null) {
                pca = pca + networkCourseOrderActionForm.getAddress();
            }

            request.setAttribute("telephone", networkCourseOrderActionForm.getTelephone());

            request.setAttribute("pca", pca);
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
            NetworkCourseOrderRefundActionForm ncorForm = mgr.view(ID);
            request.setAttribute("NetworkCourseOrderRefundActionForm", ncorForm);
            List<NetworkCourseOrder> ncoList = Tool.findListByHql("from NetworkCourseOrder where orderCode = '" + ncorForm.getOrderCode()+"'");
            NetworkCourseOrderActionForm networkCourseOrderActionForm = new NetworkCourseOrderActionForm();
            if (null != ncoList && ncoList.size() > 0) {
                NetworkCourseOrder networkCourseOrder = ncoList.get(0);
                ConvertUtils.register(new SqlDateConverter(), java.util.Date.class);
                Tool.copyProperties(networkCourseOrderActionForm, networkCourseOrder);
            }
            request.setAttribute("NetworkCourseOrderActionForm", networkCourseOrderActionForm);
            List<ProductOrderDetails> podList = Tool.findListByHql("from ProductOrderDetails where id = " + "'" + ncorForm.getProductOrderDetailsId() + "'");
            NetworkCourseActionForm networkCourseActionForm = new NetworkCourseActionForm();
            NetworkCourse networkCourse = new NetworkCourse();
            if (null != podList && podList.size() > 0) {
                ProductOrderDetails productOrderDetails = podList.get(0);
                List<NetworkCourse> ncList = Tool.findListByHql("from NetworkCourse where id = " + "'" + productOrderDetails.getProductId() + "'");
                if (null != ncList && ncList.size() > 0) {
                    networkCourse = ncList.get(0);
                }
            }
            try {
                if (networkCourseOrderActionForm.getTelephone().equals("")) {
                    request.setAttribute("phone", Tool.getValue("select mobile from users where id='" + networkCourseOrderActionForm.getUserId() + "'"));
                } else {
                    request.setAttribute("phone", networkCourseOrderActionForm.getTelephone());
                }
            } catch (Exception e) {
                request.setAttribute("phone", Tool.getValue("select mobile from users where id='" + networkCourseOrderActionForm.getUserId() + "'"));
            }
            request.setAttribute("NetworkCourseActionForm", networkCourse);
            String province = Tool.getValue("select pname from province where provinceID = '" + networkCourseOrderActionForm.getProvinceId() + "'");
            String city = Tool.getValue("select city from city where cityID = '" + networkCourseOrderActionForm.getCityId() + "'");
            String area = Tool.getValue("select area from area where areaID = '" + networkCourseOrderActionForm.getAreaId() + "'");
            request.setAttribute("province", province);
            request.setAttribute("city", city);
            request.setAttribute("area", area);
            request.setAttribute("productOrderId", ID);
            String pca = Tool.getValue("select pname from province where provinceID = '" + networkCourseOrderActionForm.getProvinceId() + "'");
            pca = pca + Tool.getValue("select city from city where cityID = '" + networkCourseOrderActionForm.getCityId() + "'");
            pca = pca + Tool.getValue("select area from area where areaID = '" + networkCourseOrderActionForm.getAreaId() + "'");
            String address = networkCourseOrderActionForm.getAddress();
            if (null != address && !"".equals(address)) {
                if (address.contains("区") || address.contains("县") || address.contains("省") || address.contains("市")) {
                    request.setAttribute("pca", address);
                } else {
                    request.setAttribute("pca", pca + networkCourseOrderActionForm.getAddress());
                }
            } else {
                request.setAttribute("pca", address);
            }
            return mapping.findForward("update");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 修改
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) {

        String productOrderId = ParamUtils.getParameter(request, "productOrderId");
        String shouName = ParamUtils.getParameter(request, "consignee");
        String telephone = ParamUtils.getParameter(request, "phone");
        String address = ParamUtils.getParameter(request, "address");
        String zipcode = ParamUtils.getParameter(request, "zipcode");
        String checkStatus = ParamUtils.getParameter(request, "checkStatus");
        try {
            NetworkCourseOrderRefund ncor = mgr.viewPOJO(productOrderId);
            List<NetworkCourseOrder> ncoList = Tool.findListByHql("from NetworkCourseOrder where orderCode = '" + ncor.getOrderCode()+"'");
            NetworkCourseOrder nco = new NetworkCourseOrder();
            if (null != ncoList && ncoList.size() > 0) {
                nco = ncoList.get(0);
            }
            String provinceID = nco.getProvinceId();
            String cityID = nco.getCityId();
            String areaID = nco.getAreaId();

            String province = null;
            String city = null;
            String area = null;
            if (null != provinceID && !"".equals(provinceID)) {
                province = Tool.getValue("select pname from province where provinceID = " + provinceID);
            }
            if (null != cityID && !"".equals(cityID)) {
                city = Tool.getValue("select city from city where cityID = " + cityID);
            }
            if (null != areaID && !"".equals(areaID)) {
                area = Tool.getValue("select area from area where areaID = " + areaID);
            }
            if (null != nco) {
                nco.setConsignee(shouName);
                nco.setTelephone(telephone);
                String newAddress = address;
                if (null != area && !"".equals(area)) {
                    if (newAddress.contains(area)) {
                        int i = address.lastIndexOf(area);
                        int start = i + area.length();
                        newAddress = address.substring(start);
                        nco.setAddress(newAddress);
                    }
                } else if (null != city && !"".equals(city)) {
                    if (newAddress.contains(city)) {
                        int i = address.lastIndexOf(city);
                        int start = i + area.length();
                        newAddress = address.substring(start);
                        nco.setAddress(newAddress);
                    }
                } else if (null != province && !"".equals(province)) {
                    if (newAddress.contains(province)) {
                        int i = address.lastIndexOf(province);
                        int start = i + area.length();
                        newAddress = address.substring(start);
                        nco.setAddress(newAddress);
                    }
                } else {
                    nco.setAddress(address);
                }
                nco.setZipCode(zipcode);
                NetworkCourseOrderMgr networkCourseOrdermgr = new NetworkCourseOrderMgr();
                networkCourseOrdermgr.updatePOJO(nco);
            }
            ncor.setCheckStatus(checkStatus);
            mgr.updatePOJO(ncor);
            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            String ipaddress = IpAddressUtil.getIpAddr(request);
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username,
                    "修改订单,订单编号:"+ncor.getOrderCode(), "1", ipaddress);

            return list(mapping, form, request, response);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", "获取列表时出错", "返回",
                    "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 导出订单
     */
    public void exportExcel(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) {
        SessionContainer sessionContainer=(SessionContainer)request.getSession().getAttribute("SessionContainer");
        if(null==sessionContainer)
            sessionContainer=new SessionContainer();
        try {

            // 接收传值
            String StatrSel = ParamUtils.getParameter(request, "StatrSel1", false);
            String starttime=ParamUtils.getParameter(request,"starttime1");//开始时间
            String endtime=ParamUtils.getParameter(request,"endtime1");//结束时间
            String orderCode=ParamUtils.getParameter(request,"orderCode1");//订单号
            String ncLevelName=ParamUtils.getParameter(request,"ncLevelName1");//
            String ncLevelTypeName=ParamUtils.getParameter(request,"ncLevelTypeName1");
            String ncName=ParamUtils.getParameter(request,"ncName1");
            String phone=ParamUtils.getParameter(request,"telephone1");
            String shouName = ParamUtils.getParameter(request, "shouName1");//收货人
            String netWorkOrBook = ParamUtils.getParameter(request, "netWorkOrBook");//图书或者网课
            String mobile = ParamUtils.getParameter(request,"mobile1");
            // 设置查询条件
            Collection queryConds = new ArrayList();
            Collection queryConds1 = new ArrayList();
            Collection queryConds2 = new ArrayList();
            if (null != StatrSel && !"".equals(StatrSel)) {
                if ("check_ing".equals(StatrSel) || "check_passed".equals(StatrSel) || "pay_finished".equals(StatrSel)) {
                    queryConds.add(new QueryCond("check_status", "String", "=", StatrSel));
                }
            }
            if (null != ncLevelName && !"".equals(ncLevelName)) {
                queryConds1.add(new QueryCond("nc_level", "String", "=", ncLevelName));
            }
            if (null != ncLevelTypeName && !"".equals(ncLevelTypeName)) {
                queryConds1.add(new QueryCond("nc_level_type", "String", "=", ncLevelTypeName));
            }
            if (null != starttime && !"".equals(starttime) && null != endtime && !"".equals(endtime)) {
                queryConds.add(new QueryCond("createtime", "String", ">=", starttime));
                queryConds.add(new QueryCond("createtime", "String", "<=", endtime));
            }
            queryConds2.add(new QueryCond("order_code", "String", "=", orderCode));
            queryConds1.add(new QueryCond("nc_name", "String", "like", ncName));
            queryConds2.add(new QueryCond("consignee", "String", "=", shouName));
            if (mobile != null){
                Users uu = (Users) mgr.getObjectByHql("from Users where mobile = '"+mobile+"'");
                if(null != uu && !"".equals(uu)){
                    queryConds2.add(new QueryCond("user_id","String","=",uu.getId()));
                }
            }else{
                queryConds2.add(new QueryCond("telephone","String","=",phone));
            }

//            ListContainer lc = mgr.list(queryConds, queryConds1, queryConds2, currentPageInt,
//                    itemsInPage, action, jumpPage);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestr = sdf.format(new Date());
            String fileName=new String((netWorkOrBook+"退单-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setCharacterEncoding("utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            List list = new ArrayList ();
            ExcelUtil eu = new ExcelUtil();
            if(netWorkOrBook.equals("图书")){
//                String[] titles = { "网店订单号", "商品名称","ERP编码", "数量", "订单金额", "实付金额","应付金额","买家昵称","收货人姓名","收货人手机号","收货人电话","收货人邮编","收货人所在省","收货人所在市","收货人所在区","收货人详细地址 ","买家订单留言","商家订单留言","订单运费","下单时间"};
//                list = (ArrayList<Object>) mgr.getBookOrderRefundList(queryConds);
//                eu.ExportExcel(titles, list, outputStream);
            }else if(netWorkOrBook.equals("网课")){
                String[] titles = { "网店订单号","课程名称","ERP编码","数量", "支付方式","登陆账号","收货人姓名","收货人手机号","订单状态", "有无讲义", "订单金额", "实付金额","退款金额","创建时间","物流单号","收货人所在省","收货人所在市","收货人所在区","收货人详细地址","买家订单留言","商家订单留言","订单运费","订单来源"};
                String[] column = {"order_code","nc_name",null,"pcount", "payType","account","consignee","telephone","check_status","is_gift_book","price","pay_price","fee","createtime","logisticsCode","pname","city","area","address","remark",null,"postage","type"};
                list =  mgr.getNetWorkOrderRefundList(queryConds,queryConds1,queryConds2);
                eu.ExportExcelConmon(titles,column,list,fileName,response);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);

        }
    }
}
