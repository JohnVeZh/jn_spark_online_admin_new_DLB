package com.business.NetworkCourseOrder;

import com.business.Express.ExpressMgr;
import com.business.NetworkCourse.NetworkCourseActionForm;
import com.business.NetworkCourse.NetworkCourseMgr;
import com.business.NetworkVideo.NetworkVideoMgr;
import com.business.Product.ProductActionForm;
import com.business.Product.ProductMgr;
import com.business.ProductCollocation.ProductCollocationMgr;
import com.business.ProductOrder.ProductOrder;
import com.business.ProductOrder.ProductOrderMgr;
import com.business.ProductOrderDetails.ProductOrderDetails;
import com.business.ProductOrderDetails.ProductOrderDetailsMgr;
import com.business.ProductOrderDetailsCollocation.ProductOrderDetailsCollocation;
import com.business.ProductOrderLogistics.ProductOrderLogistics;
import com.business.ProductOrderLogistics.ProductOrderLogisticsMgr;
import com.business.Users.Users;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.framework.struts.BaseAction;
import com.easecom.common.util.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by john on 2017/3/27.
 */
public class NetworkCourseOrderAction extends BaseAction implements Serializable {

    NetworkCourseView networkCourseView = new NetworkCourseView();
    NetworkCourseOrderDetailsMgr networkCourseOrderDetailsMgr = new NetworkCourseOrderDetailsMgr();
    NetworkCourseOrderMgr mgr = new NetworkCourseOrderMgr();
    ProductMgr productMgr = new ProductMgr();

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


            //设置查询条件
            String StatrSel = ParamUtils.getParameter(request, "StatrSel", false);
            String ncLevelTypeName = ParamUtils.getParameter(request,"ncLevelTypeName",false);
            String ncLevelName = ParamUtils.getParameter(request,"ncLevelName",false);
            String starttime=ParamUtils.getParameter(request,"starttime");//开始时间
            String endtime=ParamUtils.getParameter(request,"endtime");//结束时间
            String shouName = ParamUtils.getParameter(request, "shouName");//收货人
            String orderCode = ParamUtils.getParameter(request,"orderCode");//订单号
            String ncName = ParamUtils.getParameter(request,"ncName");//课程名称
            String phone = ParamUtils.getParameter(request, "telephone");//手机号
            String mobile = ParamUtils.getParameter(request,"mobile");
            String phoneType = ParamUtils.getParameter(request,"phone");
            String fromType = ParamUtils.getParameter(request, "fromType", false);//订单来源

            String username = ParamUtils.getParameter(request, "username");//购货人
            String address = ParamUtils.getParameter(request, "address");//地址
            String provinceIdStr=ParamUtils.getParameter(request, "provinceIdStr",false);
            String cityIdStr=ParamUtils.getParameter(request, "cityIdStr",false);
            String areaIdStr=ParamUtils.getParameter(request, "areaIdStr",false);

            String userId = "";
            Users us = (Users)mgr.getObjectByHql("from Users where username='"+username+"'");
            if(!"".equals(us)&&null!=us){
                userId = us.getId();
            }

            Collection queryConds = new ArrayList();
            Collection queryConds1 = new ArrayList();

            if(null!=StatrSel && !"".equals(StatrSel)){

                if("not_paid".equals(StatrSel) || "not_received".equals(StatrSel)
                        || "completed".equals(StatrSel) || "been_canceled".equals(StatrSel)){
                    queryConds.add(new QueryCond("order_state", "String", "=", StatrSel));
                }

                if ("not_deliver".equals(StatrSel)){
                    String megrStatrSel = "'not_deliver','not_comment'";
                    queryConds.add(new QueryCond("order_state", "String", "in", megrStatrSel));
                }
            }
            if(null!=fromType && !"".equals(fromType)){
                queryConds.add(new QueryCond("from_type", "String", "in", fromType));
            }
            if(null!=ncLevelName && !"".equals(ncLevelName)){
                queryConds1.add(new QueryCond("nc_level", "String", "=", ncLevelName));
            }
            if(null!=ncLevelTypeName && !"".equals(ncLevelTypeName)){
                queryConds1.add(new QueryCond("nc_level_type", "String", "=", ncLevelTypeName));
            }

            if(null!=starttime&&!"".equals(starttime) || null!=endtime&&!"".equals(endtime)){
                queryConds.add(new QueryCond("createtime", "String", ">=", starttime));
                queryConds.add(new QueryCond("createtime", "String", "<=", endtime));
            }
            queryConds.add(new QueryCond("order_code","String","=",orderCode));
            queryConds1.add(new QueryCond("nc_name","String","like",ncName));
            queryConds.add(new QueryCond("consignee", "String", "=", shouName));
            if (mobile != null){
                Users uu = (Users) mgr.getObjectByHql("from Users where mobile = '"+mobile+"'");
                if(null != uu && !"".equals(uu)){
                    queryConds.add(new QueryCond("user_id","String","=",uu.getId()));
                }else{
                    queryConds.add(new QueryCond("user_id","String","=","\""+"\""));
                }
            }else{
                queryConds.add(new QueryCond("telephone","String","=",phone));
            }

            ListContainer lc = mgr.list(queryConds, queryConds1,currentPageInt,
                    itemsInPage, action, jumpPage);

            List ll = lc.getList();
            List<Map> la = new ArrayList<Map>();
            for (int i = 0; i < ll.size(); i++)
            {
                NetworkCourseOrder po = null;
                po = (NetworkCourseOrder)ll.get(i);

                Map pp = new HashMap();
                pp.put("Id",po.getId());
                pp.put("orderCode",po.getOrderCode());
                pp.put("userName",po.getConsignee());
                pp.put("account",Tool.getValue("SELECT account FROM users WHERE id = '" + po.getUserId() + "'"));



                ProductOrderLogistics productOrderLogistics = (ProductOrderLogistics) mgr.getObjectByHql("from ProductOrderLogistics where orderHistory = '" + po.getId() + "'");
                if (productOrderLogistics != null){
                    pp.put("productOrderLogistics",productOrderLogistics);
                }
                String type = "";
                if (po.getType() == 1 || po.getType() == 2){
                    type = "网课1.0";
                }
                if (po.getType() == 3 || po.getType() == 4){
                    type = "网课2.0";
                }
                pp.put("type",type);

                String pName = "";
                String cityName = "";
                String areaName = "";
                if(po.getProvinceId()!=null && po.getProvinceId()!=""){
                    pName = Tool.getValue("select pname from province where provinceID = '" + po.getProvinceId() + "'");
                }
                if(po.getCityId()!=null && po.getCityId()!=""){
                    cityName = Tool.getValue("select city from city where cityID= '" + po.getCityId() + "'");
                }
                if(po.getAreaId()!=null && po.getAreaId()!=""){
                    areaName = Tool.getValue("select area from area where areaID = '" + po.getAreaId() + "'");
                }
                pp.put("addressPost",pName+cityName+areaName);
                pp.put("address",po.getAddress());
                pp.put("telephone",po.getTelephone());
                pp.put("userId",po.getUserId());
                String status = "";
                String statusName = "";

                pp.put("price",po.getPrice());
                pp.put("payPrice",po.getPayPrice());
                pp.put("createtime",po.getCreateTime().replaceAll("-","/"));
                pp.put("logisticscode", StrUtils.null2Str(po.getLogisticsCode()));
                String eCode = Tool.getValue("select log.e_code from product_order_logistics log where log.logisticsCode='"+po.getLogisticsCode()+"'");
                pp.put("eCode",eCode);

				  /*查询订单商品*/
                List<ProductOrderDetails> detailsList = Tool.findListByHql("from ProductOrderDetails where productOrderId = '"+po.getId()+"'");
                List<Map> mList = new ArrayList<Map>();
                //查询出主商品
                for (ProductOrderDetails productOrderDetails : detailsList) {
                    Map map = new HashMap();
                    map.put("id", productOrderDetails.getId());
                    map.put("pFType", productOrderDetails.getProductType());//套餐类型
                    map.put("pcount", productOrderDetails.getPcount());//购买数量
                    map.put("orderPrice", productOrderDetails.getMoney());//购买价格
                    map.put("statusValue", productOrderDetails.getStatus());//状态
                    if(productOrderDetails.getStatus()!=null){
                        status = Tool.getValue("select name from sys_config where value='" + productOrderDetails.getStatus() + "'");
                        statusName = productOrderDetails.getStatus();
                                map.put("status", status);
                    }

                    if(productOrderDetails.getProductType()==2 || productOrderDetails.getProductType()==1){

                        //查询网课
                        NetworkCourseActionForm net = networkCourseView.view(productOrderDetails.getProductId());
                        map.put("pFId", net.getId());
                        map.put("pFName", net.getNcName());
                        pp.put("ncName",net.getNcName());
                        int isGiftBook = net.getIsGiftBook();

                        //订单状态
                        if ("been_canceled".equals(po.getOrderState())){
                            pp.put("OrderState","已取消");
                        }
                        if("not_paid".equals(po.getOrderState())){
                            pp.put("OrderState","待付款");
                        }
                        if("completed".equals(po.getOrderState())){
                            pp.put("OrderState","已完成");
                        }
                        if("0".equals(String.valueOf(isGiftBook))){
                            pp.put("isGiftBook","无");
                        }
                        if("1".equals(String.valueOf(isGiftBook))){
                            pp.put("isGiftBook","有");
                            if("not_deliver".equals(po.getOrderState()) || "not_comment".equals(po.getOrderState())){
                                pp.put("OrderState","待发货");
                            }
                            if("not_received".equals(po.getOrderState())){
                                pp.put("OrderState","待收货");
                            }
                        }
                        map.put("presentPrice", net.getCurrentPrice());
                        map.put("originalPrice", net.getOriginalPrice());
                        //查询套餐集合
                        List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
                        List<Map> collMap = new ArrayList<Map>();
                        for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
                            Map colMap = new HashMap();
                            colMap.put("pFType", productOrderDetailsCollocation.getProductType());

                            //图书
                            if(productOrderDetailsCollocation.getProductType()==0){
                                //查询套餐商品
                                ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
                                if(null != colPro){
                                    colMap.put("pFId", colPro.getId());
                                    colMap.put("pFName", colPro.getpName());
                                    colMap.put("presentPrice", colPro.getpPresentPrice());
                                    colMap.put("originalPrice", colPro.getpOriginalPrice());
                                    Double t = Tool.geDoubleValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'");
                                    if(t!=0&&t!=null){
                                        colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
                                    }else{
                                        colMap.put("colloMoney","0");
                                    }
                                }
                            }else{
                                //查询搭配套餐的网课
                                NetworkCourseActionForm collNet = networkCourseView.view(productOrderDetailsCollocation.getProductId());
                                if(null != collNet){
                                    colMap.put("pFId", collNet.getId());
                                    colMap.put("pFName", collNet.getNcName());
                                    colMap.put("presentPrice", collNet.getCurrentPrice());
                                    colMap.put("originalPrice", collNet .getOriginalPrice());
			    		    			/*colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));*/
                                    Double t = Tool.geDoubleValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'");
                                    if(t!=0&&t!=null){
                                        colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
                                    }else{
                                        colMap.put("colloMoney","0");
                                    }
                                }
                            }
                            collMap.add(colMap);
                        }
                        map.put("collMap", collMap);
                    }
                    mList.add(map);
                }
                pp.put("mList",mList);
                la.add(pp);
            }
            request.setAttribute("mList", la);

            String pro = Tool.getList("select id,p_name from product where p_is_del='0' order by level_type asc","p_name","id");
            //获取订单列表
            String orderStatus= OrderStatusTool.getList("SELECT VALUE,NAME FROM sys_config WHERE TYPE IN('ORDER_STATUS','ORDER_REFUND_STATUS')ORDER BY sort ASC","name","value");
            if(!"".equals(StatrSel)&&null!=StatrSel){
                orderStatus=OrderStatusTool.getList("SELECT VALUE,NAME FROM sys_config WHERE TYPE IN('ORDER_STATUS','ORDER_REFUND_STATUS')ORDER BY sort ASC","name","value",StatrSel);
            }
            //获取课程大类列表
            String ncLevelStatus = Tool.getList("select value,name from sys_config where type='NC_LEVEL'order by sort asc","name","value");
            if(!"".equals(ncLevelName)&&null!=ncLevelName){
                ncLevelStatus=Tool.getList("select value,name from sys_config where type='NC_LEVEL' order by sort asc","name","value",ncLevelName);
            }
            //获得课程级别列表
            String ncLevelTypeStatus=Tool.getList("select value,name from sys_config where type='NC_LEVEL_TYPE' order by sort asc"
                                                        ,"name","value");
            if(!"".equals(ncLevelTypeName)&&null!=ncLevelTypeName){
                ncLevelTypeStatus=Tool.getList("select value,name from sys_config where type='NC_LEVEL_TYPE' order by sort asc"
                                                    ,"name","value",ncLevelTypeName);
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
            request.setAttribute("starttime", starttime);
            request.setAttribute("endtime", endtime);
            request.setAttribute("endtime", endtime);
            request.setAttribute("orderStatus", orderStatus);
            request.setAttribute("ncLevelStatus", ncLevelStatus);
            request.setAttribute("ncLevelTypeStatus", ncLevelTypeStatus);
            request.setAttribute("shouName", shouName);
            request.setAttribute("telephone",phone);
            request.setAttribute("ncName",ncName);
            request.setAttribute("orderCode",orderCode);
            request.setAttribute("mobile",mobile);
            request.setAttribute("phoneType",phoneType1);
            request.setAttribute("fromType",fromType);
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
            if(null!=StatrSel && !"".equals(StatrSel)){
                if("not_paid".equals(StatrSel) || "not_received".equals(StatrSel) || "completed".equals(StatrSel)){
                    queryConds.add(new QueryCond("order_state", "String", "=", StatrSel));
                }

                if ("not_deliver".equals(StatrSel)){
                    StatrSel = "'not_deliver','not_comment'";
                    queryConds.add(new QueryCond("order_state", "String", "in", StatrSel));
                }
            }
            if(null!=starttime&&!"".equals(starttime)&&null!=endtime&&!"".equals(endtime)){
                queryConds.add(new QueryCond("createtime", "String", ">=", starttime));
                queryConds.add(new QueryCond("createtime", "String", "<=", endtime));
            }
            if(null!=ncLevelName && !"".equals(ncLevelName)){
                queryConds1.add(new QueryCond("nc_level", "String", "=", ncLevelName));
            }
            if(null!=ncLevelTypeName && !"".equals(ncLevelTypeName)){
                queryConds1.add(new QueryCond("nc_level_type", "String", "=", ncLevelTypeName));
            }
            if(null!=shouName && !"".equals(shouName)){
                queryConds.add(new QueryCond("po.consignee", "String", "=", shouName));
            }
            queryConds.add(new QueryCond("order_code","String","=",orderCode));
            queryConds1.add(new QueryCond("nc_name","String","like",ncName));
            queryConds.add(new QueryCond("consignee", "String", "=", shouName));
            if (mobile != null){
                Users uu = (Users) mgr.getObjectByHql("from Users where mobile = '"+mobile+"'");
                if(null != uu && !"".equals(uu)){
                    queryConds.add(new QueryCond("user_id","String","=",uu.getId()));
                }
            }else{
                queryConds.add(new QueryCond("telephone","String","=",phone));
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestr = sdf.format(new Date());
            String fileName=new String((netWorkOrBook+"订单-"+timestr).getBytes("gb2312"), "iso8859-1")+ ".xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setCharacterEncoding("utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            List list = new ArrayList ();
            ExcelUtil eu = new ExcelUtil();
            if(netWorkOrBook.equals("图书")){
                String[] titles = { "网店订单号", "商品名称","ERP编码", "数量", "订单金额", "实付金额","应付金额","买家昵称","收货人姓名","收货人手机号","收货人电话","收货人邮编","收货人所在省","收货人所在市","收货人所在区","收货人详细地址 ","买家订单留言","商家订单留言","订单运费","下单时间"};
                list = (ArrayList<Object>) mgr.getBookOrderList(queryConds);
                eu.ExportExcel(titles, list, outputStream);
            }else if(netWorkOrBook.equals("网课")){
                String[] titles = { "网店订单号","课程名称","ERP编码", "数量", "支付方式","登陆账号","收货人姓名", "收货人手机号","订单状态", "有无讲义", "订单金额", "实付金额","创建时间","物流单号","收货人所在省","收货人所在市","收货人所在区","收货人详细地址","买家订单留言","商家订单留言","订单运费","订单来源"};
                String[] column = {"order_code","nc_name",null, "pcount","payType","account","consignee","telephone","order_state","is_gift_book","price","pay_price","createtime","logisticsCode","pname","city","area","address","remark",null,"postage","type"};
                list =  mgr.getNetWorkOrderList(queryConds,queryConds1);
                eu.ExportExcelConmon(titles,column,list,fileName,response);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);

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
            NetworkCourseOrderActionForm vo = mgr.view(ID);
            String state = "";
            List<Map> mList = new ArrayList<Map>();
            if (vo != null){
                request.setAttribute("NetworkCourseOrderActionForm", vo);
                request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
                String pca = Tool.getValue("select pname from province where provinceID = '"+vo.getProvinceId()+"'");
                pca = pca + Tool.getValue("select city from city where cityID = '"+vo.getCityId()+"'");
                pca = pca + Tool.getValue("select area from area where areaID = '"+vo.getAreaId()+"'");
                if (vo.getAddress()!=null){
                    pca = pca + vo.getAddress();
                }
                request.setAttribute("pca",pca);
                request.setAttribute("telephone", vo.getTelephone());
		    /*查询订单商品*/
                List<ProductOrderDetails> detailsList = Tool.findListByHql("from ProductOrderDetails where productOrderId = '"+vo.getId()+"'");
                //查询出主商品
                for (ProductOrderDetails productOrderDetails : detailsList) {
                    Map map = new HashMap();
                    map.put("pFType", productOrderDetails.getProductType());
                    String status = productOrderDetails.getStatus();
                    if(productOrderDetails.getProductType()==2 || productOrderDetails.getProductType() == 1){
                        //查询网课
                        NetworkCourseActionForm net = networkCourseView.view(productOrderDetails.getProductId());
                        request.setAttribute("NetworkCourseActionForm",net);
                        map.put("pFId", net.getId());
                        map.put("pFName", net.getNcName());
                        map.put("presentPrice", net.getCurrentPrice());
                        map.put("originalPrice", net.getOriginalPrice());

                        int isGiftBook = net.getIsGiftBook();
                        String isGiftBookstate = "";
                        map.put("isGiftBook",isGiftBook);
                        if("not_paid".equals(vo.getOrderState())){
                            state="待付款";
                        }
                    /*if("refund_audit".equals(status)){
                        state="退款审核中";
                    }
                    if("refund".equals(status)){
                        state="退款中";
                    }
                    if("refunded_completed".equals(status)){
                        state="已退款";
                    }*/
                        if("completed".equals(vo.getOrderState())){
                            state="已完成";
                        }
                        if("0".equals(String.valueOf(isGiftBook))){
                            isGiftBookstate="无";
                        }
                        if("1".equals(String.valueOf(isGiftBook))){
                            isGiftBookstate="有";
                            if("not_deliver".equals(vo.getOrderState()) || "not_comment".equals(vo.getOrderState())){
                                state="待发货";
                            }
                            if("not_received".equals(vo.getOrderState())){
                                state="待收货";
                            }
                        }
                        request.setAttribute("isGiftBookstate",isGiftBookstate);
                        //查询套餐集合
                        List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
                        List<Map> collMap = new ArrayList<Map>();
                        for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
                            Map colMap = new HashMap();
                            colMap.put("pFType", productOrderDetailsCollocation.getProductType());
                            //图书
                            if(productOrderDetailsCollocation.getProductType()==0){
                                //查询套餐商品
                                ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
                                if(null != colPro){
                                    colMap.put("pFId", colPro.getId());
                                    colMap.put("pFName", colPro.getpName());
                                    colMap.put("presentPrice", colPro.getpPresentPrice());
                                    colMap.put("originalPrice", colPro.getpOriginalPrice());
                                    colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
                                }
                            }else{
                                //查询搭配套餐的网课
                                NetworkCourseActionForm collNet = networkCourseView.view(productOrderDetailsCollocation.getProductId());
                                if(null != collNet){
                                    colMap.put("pFId", collNet.getId());
                                    colMap.put("pFName", collNet.getNcName());
                                    colMap.put("presentPrice", collNet.getCurrentPrice());
                                    colMap.put("originalPrice", collNet .getOriginalPrice());
                                    colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
                                }
                            }
                            collMap.add(colMap);
                        }
                        map.put("collMap", collMap);
                    }
                    mList.add(map);
                }
                ProductOrderLogistics productOrderLogistics = (ProductOrderLogistics) mgr.getObjectByHql("from ProductOrderLogistics where orderHistory = '" + ID + "'");
                if (productOrderLogistics != null){
                    request.setAttribute("productOrderLogistics",productOrderLogistics);
                }
                request.setAttribute("mList",mList);
                request.setAttribute("state",state);
                return mapping.findForward("view");
            }else{
                return mapping.findForward("inexistence");
            }
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
            NetworkCourseOrderActionForm vo = mgr.view(ID);
            if (vo != null){
                String provinceID = vo.getProvinceId();
                String cityID = vo.getCityId();
                String areaID = vo.getAreaId();

                String province = Tool.getList("select provinceID,pname from province ","pname","provinceID",provinceID );
                String city = Tool.getList("select cityID,city from city ","city","cityID",cityID );
                String area = Tool.getList("select areaID,area from area ","area","areaID",areaID );

                request.setAttribute("province",province);
                request.setAttribute("city",city);
                request.setAttribute("area",area);
                request.setAttribute("productOrderId",ID);
                vo.setCreateTime(vo.getCreateTime().replaceAll("-","/"));
                request.setAttribute("NetworkCourseOrderActionForm", vo);
                request.setAttribute("uName", Tool.getValue("select username from users where id='"+vo.getUserId()+"'"));
                String state = "";

		    /*查询订单商品*/
                List<ProductOrderDetails> detailsList = Tool.findListByHql("from ProductOrderDetails where productOrderId = '"+vo.getId()+"'");
                List<Map> mList = new ArrayList<Map>();
                //查询出主商品
                for (ProductOrderDetails productOrderDetails : detailsList) {
                    Map map = new HashMap();
                    map.put("pFType", productOrderDetails.getProductType());
                    String status = productOrderDetails.getStatus();
                    if(productOrderDetails.getProductType()==2 || productOrderDetails.getProductType() == 1){
                        //查询网课
                        NetworkCourseActionForm net = networkCourseView.view(productOrderDetails.getProductId());
                        request.setAttribute("productOrderDetailsId",productOrderDetails.getId());
                        request.setAttribute("NetworkCourseId",net.getId());
                        request.setAttribute("NetworkCourseActionForm",net);
                        map.put("pFId", net.getId());
                        map.put("pFName", net.getNcName());
                        map.put("presentPrice", net.getCurrentPrice());
                        map.put("originalPrice", net.getOriginalPrice());

                        int isGiftBook = net.getIsGiftBook();
                        String isGiftBookstate = "";
                        map.put("isGiftBook",isGiftBook);
                   /* if("refunded_del".equals(vo.getOrderState())){
                        state="refunded_del";
                    }
                    if("refund_audit".equals(status)){
                        state="refund_audit";
                    }
                    if("refund".equals(status)){
                        state="refund";
                    }
                    if("refunded_completed".equals(status)){
                        state="refunded_completed";
                    }*/
                        if("not_paid".equals(vo.getOrderState())){
                            state="not_paid";
                        }
                        if("completed".equals(vo.getOrderState())){
                            state="completed";
                        }
                        if("0".equals(String.valueOf(isGiftBook))){
                            isGiftBookstate="无";
                        }
                        if("1".equals(String.valueOf(isGiftBook))){
                            isGiftBookstate="有";
                            if("not_deliver".equals(vo.getOrderState()) || "not_comment".equals(vo.getOrderState())){
                                state="not_deliver";
                            }
                            if("not_received".equals(vo.getOrderState())){
                                state="not_received";
                            }
                        }
                        request.setAttribute("isGiftBookstate",isGiftBookstate);
                        //查询套餐集合
                        List<ProductOrderDetailsCollocation> cList = Tool.findListByHql("from ProductOrderDetailsCollocation where podId='"+productOrderDetails.getId()+"'");
                        List<Map> collMap = new ArrayList<Map>();
                        for (ProductOrderDetailsCollocation productOrderDetailsCollocation : cList) {
                            Map colMap = new HashMap();
                            colMap.put("pFType", productOrderDetailsCollocation.getProductType());
                            //图书
                            if(productOrderDetailsCollocation.getProductType()==0){
                                //查询套餐商品
                                ProductActionForm colPro = productMgr.view(productOrderDetailsCollocation.getProductId());
                                if(null != colPro){
                                    colMap.put("pFId", colPro.getId());
                                    colMap.put("pFName", colPro.getpName());
                                    colMap.put("presentPrice", colPro.getpPresentPrice());
                                    colMap.put("originalPrice", colPro.getpOriginalPrice());
                                    colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
                                }
                            }else{
                                //查询搭配套餐的网课
                                NetworkCourseActionForm collNet = networkCourseView.view(productOrderDetailsCollocation.getProductId());
                                if(null != collNet){
                                    colMap.put("pFId", collNet.getId());
                                    colMap.put("pFName", collNet.getNcName());
                                    colMap.put("presentPrice", collNet.getCurrentPrice());
                                    colMap.put("originalPrice", collNet .getOriginalPrice());
                                    colMap.put("colloMoney",Tool.getValue("select money from product_collocation where id='"+productOrderDetailsCollocation.getPcId()+"'"));
                                }
                            }
                            collMap.add(colMap);
                        }
                        map.put("collMap", collMap);
                    }
                    mList.add(map);
                }
                //获取订单列表
                String StatrSel = ParamUtils.getParameter(request, "StatrSel", false);
                String orderStatus= OrderStatusTool.getList("SELECT VALUE,NAME FROM sys_config WHERE TYPE IN('ORDER_STATUS','ORDER_REFUND_STATUS')ORDER BY sort ASC","name","value");
                if(!"".equals(StatrSel)&&null!=StatrSel){
                    orderStatus=OrderStatusTool.getList("SELECT VALUE,NAME FROM sys_config WHERE TYPE IN('ORDER_STATUS','ORDER_REFUND_STATUS')ORDER BY sort ASC","name","value",StatrSel);
                }
                request.setAttribute("state",state);
                request.setAttribute("mList",mList);
                request.setAttribute("state",state);
                request.setAttribute("provinceList", Tool.getList("select provinceID,pname from province", "pname", "provinceID",provinceID));
                request.setAttribute("cityList", Tool.getList("select cityID,city from city where provinceID = '"+provinceID+"'", "city", "cityID",cityID));
                request.setAttribute("areaList", Tool.getList("select areaID,area from area where cityID = '"+cityID+"'", "area", "areaID",areaID));
                return mapping.findForward("update");
            }else {
                return mapping.findForward("inexistence");
            }

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

        String productOrderId = ParamUtils.getParameter(request,"productOrderId");
        String shouName = ParamUtils.getParameter(request,"consignee");
        String telephone = ParamUtils.getParameter(request,"phone","");
        String address = ParamUtils.getParameter(request,"address");
        String zipcode = ParamUtils.getParameter(request,"zipcode");
        String state = ParamUtils.getParameter(request,"state","");
        String provinceIdStr = ParamUtils.getParameter(request,"provinceIdStr");
        String cityIdStr = ParamUtils.getParameter(request,"cityIdStr");
        String areaIdStr = ParamUtils.getParameter(request,"areaIdStr");

        try {
            NetworkCourseOrderActionForm expo = mgr.view(productOrderId);
            String provinceID = expo.getProvinceId();
            String cityID = expo.getCityId();
            String areaID = expo.getAreaId();


            String province = null;
            String city = null;
            String area = null;
            if(null != provinceID && !"".equals(provinceID)){
                province = Tool.getValue("select pname from province where provinceID = "+provinceID);
            }
            if (null != cityID && !"".equals(cityID)){
                city = Tool.getValue("select city from city where cityID = "+ cityID);
            }
            if (null !=areaID && !"".equals(areaID)){
                area = Tool.getValue("select area from area where areaID = "+areaID);
            }
           if (null != provinceIdStr && !"".equals(provinceIdStr)){
               expo.setProvinceId(provinceIdStr);
           }
           if (null != cityIdStr && !"".equals(cityIdStr)){
               expo.setCityId(cityIdStr);
           }
           if (null != areaIdStr && !"".equals(areaIdStr)){
               expo.setAreaId(areaIdStr);
           }
            if(null != expo){
                expo.setConsignee(shouName);
                expo.setTelephone(telephone);
                expo.setZipCode(zipcode);
                expo.setAddress(address);
                String orderState = expo.getOrderState();
                NetworkCourseOrderDetails ncod = networkCourseOrderDetailsMgr.getDetailsByNCOId(expo.getId());
                if(state != null){
                    //跟新活动记录表单
//                    if (!state.equals(orderState)){
//                        mgr.updateActlog(expo,ncod,orderState,state);
//                    }
                    if(state.equals("not_paid") || state.equals("not_deliver") || state.equals("not_comment") || state.equals("not_received") || state.equals("completed")){
                        expo.setOrderState(state);
                        if("not_paid".equals(orderState)) {
                            if ("not_deliver".equals(state) || "not_comment".equals(state)) {
                                Date curr = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String payTime = format.format(curr);

                                Calendar ca = Calendar.getInstance();
                                ca.add(Calendar.DATE,7);
                                Date later = ca.getTime();
                                String autoRewardTime = format.format(later);
                                expo.setPayTime(payTime);
                                expo.setAutoRewardTime(autoRewardTime);
//                                mgr.update(expo);
                            }
                        }
                    }
                }
                mgr.updateOrderAndActlog(expo,ncod,orderState,state);
            }

            SessionContainer sessionContainer = (SessionContainer) request
                    .getSession().getAttribute("SessionContainer");
            if(sessionContainer==null){
                sessionContainer = new SessionContainer();
            }
            String ipaddress = IpAddressUtil.getIpAddr(request);
            String username = "admin";
            Tool.AddLog(sessionContainer.getUserId(), username,
                    "修改订单,订单编号:"+expo.getOrderCode(), "1", ipaddress);

            return list(mapping, form, request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
            WebDialogBox dialog = new WebDialogBox(1, "错误", ex.getMessage(),
                    "返回", "javascript:window.history.back()");
            request.setAttribute("DialogBox", dialog);
            return mapping.findForward("DialogBox");
        }
    }

    /**
     * 删除订单
     *
     */

    /**
     * 物流单号导入页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     */

    public ActionForward preExpressImport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("express_import");
    }

    /**
     * 物流单号导入
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public ActionForward expressImport(ActionMapping mapping,
                                       ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        SessionContainer sessionContainer = (SessionContainer) request
                .getSession().getAttribute("SessionContainer");
        if (null == sessionContainer)
            sessionContainer = new SessionContainer();
        Map m = new HashMap();
        // 获取excel 文件
        NetworkCourseOrderActionForm fm = (NetworkCourseOrderActionForm) form;
        FormFile formfile = fm.getFile();
        InputStream inputstream = formfile.getInputStream();
        fm.clear();// 清空
        ArrayList list = new ArrayList();
        int input = 0; // 导入记数

        boolean result = true;
        long succNum = 0;
        try {
            // 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
            /*ClassLoader classloader =
                    org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
            URL res = classloader.getResource(
                    "org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
            String path = res.getPath();
            System.out.println("Core POI came from " + path);*/
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);
            HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);// 第一个工作表
            HSSFRow hssfrow = null;
            String orderCode = null;	// 订单code
            String expressName = null;	// 物流商
            String logistics = null;	// 物流单号
            for (int i=1; i<hssfsheet.getPhysicalNumberOfRows(); i++) {
                hssfrow = hssfsheet.getRow(i);
                if(hssfrow == null) {
                    continue;
                }
                orderCode = String.valueOf(hssfrow.getCell(0).getStringCellValue()).trim();
                expressName = String.valueOf(hssfrow.getCell(1).getStringCellValue()).trim();
                logistics = String.valueOf(hssfrow.getCell(2).getStringCellValue()).trim();
                if(StringUtils.isEmpty(logistics)) {
                    continue;
                }
                try {
                    if(mgr.orderExpress(orderCode, expressName, logistics)) {
                        succNum++;
                    }
                } catch (Exception e) {
                    log.error("物流单号导入出错：订单号" + orderCode, e);
                }
            }

        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            if(inputstream != null) {
                try {
                    inputstream.close();
                } catch (Exception e) {}
            }
            try {
                HibernateSessionFactory.closeSession();
            } catch (Exception e) {}
        }

        m.put("result", result);
        m.put("succNum", succNum);
        JsonUtils.outputJsonResponse(response, m);
        return null;
    }
}
