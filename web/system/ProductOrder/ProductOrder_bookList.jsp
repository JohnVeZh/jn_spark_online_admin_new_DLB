<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.ProductOrder.ProductOrder"%>
<%@page import="com.easecom.common.util.Tool"%>
<%
    String path = request.getContextPath();
    path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<style>
    *{margin: 0;padding: 0;}
    .all{width: 99%;}
    .all ul{list-style: none;width: 100%;line-height: 25px;height: 25px;background-color: #c5dbf0;margin-top:0px;}
    .all ul li{font-size:12px;float: left;width: 100px;min-width:60px;text-align: center;line-height: 25px;}
    /*.menu{width:130px;float: left;}*/
    .outBox {width: 200px;height: 50px;border-right: 1px solid #000;float: left;}
    .Box { margin-left: 10px; width: 100%; height: 30px;float: left;}
    .date{width: 120px;height: 26px;border: 1px solid #999;margin-top: 0px;}
    li:hover{background-color: #7F9DB9;}
    .seleced{background-color: #7F9DB9;}
</style>

<%
    ListContainer lc = (ListContainer) request.getAttribute("lc");
%>
<script type="text/javascript">
    $(function () {
        var quick = $("#quickSearchType").val();
        if(quick != null && quick != "" && quick!=undefined && quick!="undefined"){
            $("#"+quick).addClass("seleced");
        }

    });
    function execute(seachType) {
        $.ajaxSetup({async:false});
        $("#searchType").val(seachType);
        if("search"== seachType){
            $("#quickSearchType").val("");
            listForm.submit();
        }else{
            listForm.submit();
            $("#searchType").val("");
        }
    }

    function quickSearch(status) {
        $("#quickSearchType").val(status);
        $("#searchType").val('search');
        listForm.submit();
    }

    function isSel(){
        var inps = document.getElementsByName('id');
        var idVal = "";
        var j =0 ;
        for(var i = 0; i <inps.length; i++){
            if(inps[i].checked){
                idVal +="&id="+ inps[i].value;
                j++;
            }else{
                continue;
            }
        }
        if (Number(j)>=2 || Number(j)<1) {
            top.Dialog.alert("请选择一项进行操作",185,185);
            return "";
        }else{
            return idVal;
        }
    }

    //判断
    function isSel1(){
        var inps = document.getElementsByName('id');
        var idVal = "";
        var j =0 ;
        for(var i = 0; i <inps.length; i++){
            if(inps[i].checked){
                idVal +="&id="+ inps[i].value;
                j++;
            }else{
                continue;
            }
        }
        return idVal;
    }



    //修改
    function preUpdate(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
        }
    }
    //详情
    function view(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=view&id="+id,ID:"a1",Width:1024,Height:768,Title:"查看"});
        }
    }
    //发货
    function code(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preCodes&orderId="+id,ID:"a1",Width:1024,Height:768,Title:"发货"});
        }
    }
    //完成订单
    function complete(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要完成订单吗?",
                    function() {
                        listForm.action="ProductOrder.do?act=complete&orderId="+id;
                        listForm.submit();
                    })
        }
    }
    //取消订单
    function cancel(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要取消吗?",
                    function() {
                        listForm.action="ProductOrder.do?act=preCancel&orderId="+id;
                        listForm.submit();
                    }
            )
        }
    }

    //相关评论
    function comment(userId,id){
        //var id=$("#id").val();
        var userId=$("#userId").val();
        if(id != ""&& userId!=""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrderEvaluate.do?act=comment&id="+id+"&userId="+userId,ID:"a1",Width:1024,Height:768,Title:"相关评论"});
        }
    }
    //退款
    function collEdit(id,status){
        if(id != ""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preCollEdit&id="+id+"&status="+status,ID:"a1",Width:450,Height:300,Title:"退单管理"});
        }
    }
    //修改订单状态
    function orderStateEdit(id,status){
        if(id != ""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preOrderStateEdit&id="+id+"&status="+status,ID:"a1",Width:450,Height:200,Title:"退单管理"});
        }
    }


    //添加
    function chaxun(){
        listForm.action="ProductOrder.do?act=preQuery"
        listForm.submit();
    }
    function getCoupon(couponCodeId) {
        top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=coupon&couponCodeId="+couponCodeId,ID:"a1",Width:1024,Height:500,Title:"优惠信息"});
    }
</script>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：图书订单报表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" id="listForm" scope="request" action="<%=path%>/business/ProductOrder.do?act=bookList" method="post">
        <input type="hidden" name="searchType" id="searchType" value=""/>
        <input type="hidden" name="quickSearchType" id="quickSearchType" value="${quickSearchType}" />
        <div class="box2" panelTitle="功能面板" roller="false">
            <div class="outBox">
                <div class="Box">
                    开始时间
                    <input  class="date" type="text" name="starttime" value="${starttime }" id="starttime"  />
                </div>
                <div class="Box">
                    截止时间
                    <input  class="date" type="text" name="endtime" value="${endtime }" id="endtime"  />
                </div>
            </div>
            <table >
                <tr >
                    <td style="width: 80%;">  <div class="all">
                        <ul>
                            <li id="all" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('all')">全部订单</a></li>
                            <li id="not_paid" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('not_paid')">待支付</a></li>
                            <li id="not_deliver" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('not_deliver')">待发货</a></li>
                            <li id="not_received" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('not_received')">待收货</a></li>
                            <li id="not_comment" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('not_comment')">待评论</a></li>
                            <li id="success" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('success')">交易成功</a></li>
                            <li id="refund" class="sel" ><a href="javascript:void(0)" style="text-decoration:none;" onclick="quickSearch('refund')">退款中</a></li>
                        </ul>
                    </div></td>
                </tr>
                <tr>
                    <td><div style="width: 180px;float: left;">
                        订单号：<input type="text" style="width: 120px;" name="orderCode" value="${orderCode}" />
                    </div>
                        <div style="width: 160px;float: left;">
                            手机号：<input type="text" style="width: 100px;" name="telPhone" value="${telPhone}" />
                        </div>
                        <div style="width: 140px;float: left;">
                            收货人：<input type="text" style="width: 80px;" name="shouName" value="${shouName}" />
                        </div>
                        <div  style="width: 180px;float: left;">
                            <div style="float: left;">
                                订单状态：
                            </div>
                            <div style="float: left;">
                                <select name="orderStatusStr" id="orderStatusStr" selWidth="100" >
                                    <option  value="" >不限</option>
                                    ${orderStatus }
                                </select>
                            </div>
                        </div>
                        <div style="width: 180px;float: left;">
                            <div style="float: left;">
                                退货状态：
                            </div>
                            <div style="float: left;">
                                <select name="statusStr" id="statusStr" selWidth="100">
                                    <option  value="" >不限</option>
                                    ${status }
                                </select>
                            </div>
                        </div>
                        <input type="button" value="查询" onclick="execute('search')" />&nbsp;&nbsp;
                        <input type="button"  value="导出当前页订单" onclick="execute('current')"/>&nbsp;&nbsp;
                        <input type="button"  value="导出所有订单" onclick="execute('all')"/>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <div style="width: 99.9%;overflow: auto;">
                <table  class="tableStyle" mode="list" useCheckbox="true" trClick="true" selectRowButtonOnly="false" id="listTable">
                    <tr >
                        <th width="3%" height="25" align="center" class="DataTable_Field">
                        </th>
                        <th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                        <th height="25"  align="center" class="DataTable_Field" title="订单号">订单号</th>
                        <th height="25"  align="center" class="DataTable_Field" title="订单状态 ">订单状态</th>
                        <th align="center" class="DataTable_Field" title="退货状态 ">退货状态</th>
                        <th align="center" class="DataTable_Field" title="商品名称">商品名称</th>
                        <th height="25"  align="center" class="DataTable_Field" title="数量">数量</th>
                        <th height="25"  align="center" class="DataTable_Field" title="订单金额">总订单金额</th>
                        <th height="25"  align="center" class="DataTable_Field" title="总订单实付金额">总订单实付金额</th>
                        <th height="25"  align="center" class="DataTable_Field" title="实付金额">实付金额</th>
                        <th align="center" class="DataTable_Field" title="昵称">昵称</th>
                        <th align="center" class="DataTable_Field" title="收货人 ">收货人</th>
                        <th align="center" class="DataTable_Field" title="联系电话 ">联系电话</th>
                        <th align="center" class="DataTable_Field" title="省-市-区、县 ">省-市-区、县</th>
                        <th align="center" class="DataTable_Field" title="地址 ">地址</th>
                        <th align="center" class="DataTable_Field" title="创建时间">创建时间</th>
                        <th align="center" class="DataTable_Field" title="操作">操作</th>
                    </tr>
                    <c:forEach items="${mList }" var="ml" varStatus="s">
                        <tr >
                            <td align="center"><input type="checkbox" name="id" id="id" value="${ml.id}" onclick="event.cancelBubble=true;"></td>
                                <%--<td align="center">--%>
                                <%--<span class="hand img_add2"  keepdefaultstyle="true" title="点击展开"></span>--%>
                                <%--</td>--%>
                            <td class="DataTable_Content" align="left">${s.index+1 }</td>
                            <td class="DataTable_Content" align="left" title="${ml.order_code }">${ml.order_code }</td>
                            <td class="DataTable_Content" align="left" title="${ml.order_state }">${ml.order_state }</td>
                            <td class="DataTable_Content" align="left" title="${ml.status }">${ml.status }</td>
                            <td class="DataTable_Content" align="left" title="${ml.p_name }">${ml.p_name }</td>
                            <td class="DataTable_Content" align="left" title="${ml.pcount }">${ml.pcount }</td>
                            <td class="DataTable_Content" align="left" title="${ml.price }">${ml.price }</td>
                            <td class="DataTable_Content" align="left" title="${ml.pay_price }">${ml.pay_price }</td>
                            <td class="DataTable_Content" align="left" title="${ml.money }">${ml.money }</td>
                            <td class="DataTable_Content" align="left" title="${ml.username }">${ml.username }</td>
                            <td class="DataTable_Content" align="left" title="${ml.consignee }">${ml.consignee }</td>
                            <td class="DataTable_Content" align="left" title="${ml.telephone }">${ml.telephone }</td>
                            <td class="DataTable_Content" align="left" title="${ml.pname }">${ml.pname }</td>
                            <td class="DataTable_Content" align="left" title="${ml.address }">${ml.address }</td>
                            <td class="DataTable_Content" align="left" title="${ml.createtime }">${ml.createtime }</td>
                            <td  align="left" >
                                <a href="javascript:;" onclick="preUpdate('${ml.id }')" title="修改">
                                    <span class="img_edit"></span>
                                </a>
                                <a href="javascript:;" title="详情"
                                   onclick="view('${ml.id }')"> <span class="img_view"></span>
                                </a>
                                <!-- 相关评论 -->
                                <a href="javascript:;" onclick="comment('${ml.user_id }','${ml.id }')" title="评价"><span class="img_item"></span></a>

                                <c:if test="${ml.order_code=='not_deliver' }">
                                    <a href="javascript:;"
                                       onclick="code('${ml.id }')" title="发货"> <span class="img_attention"></span>
                                    </a>
                                    <a href="javascript:;"
                                       onclick="cancel('${ml.id }')" title="取消订单"> <span class="img_disk"></span>
                                    </a>
                                </c:if>
                                <c:if test="${ml.order_code=='not_received' }">
                                    <a href="javascript:;"
                                       onclick="complete('${ml.id }')" title="完成订单"> <span class="img_cart"></span>
                                    </a>
                                </c:if>
                                <c:if test="${ml.logisticscode != '' }" >
                                    <a href="http://www.kuaidi100.com/chaxun?com=${ml.logisticscode }&nu=${ml.eCode }" target="_blank" title="查询物流"><span class="img_globe"></span>
                                    </a>
                                </c:if>
                                <a href="javascript:;"
                                   onclick="orderStateEdit('${ml.id }','${ml.order_code }')" title="修改订单状态"> <span class="img_edit"></span>
                                </a>
                                <c:if test="${ml.couponCodeId != '' }" >
                                    <a href="javascript:;"
                                       onclick="getCoupon('${ml.couponCodeId }')" title="优惠券"> <span class="img_coupon"></span>
                                    </a>
                                </c:if>
                                <a href="javascript:;"
                                   onclick="collEdit('${ml.details_id }','${ml.status }')" title="退款状态"> <span class="img_edit"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
            <div class="center">
                <div class="right">
                    <%@include file="../../include/listpage.jsp"%>
                </div>
            </div>
        </div>
        <div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    </form>
</div>
</body>
</html>

