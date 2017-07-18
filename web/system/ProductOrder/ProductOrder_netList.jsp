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
<script type="text/javascript" src="<%=path%>/libs/js/nav/spliter.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<style>
    *{margin: 0;padding: 0;}
    .all{width: 99%;}
    .all ul{list-style: none;width: 100%;line-height: 25px;height: 25px;background-color: #7db5de;margin-top:0px;}
    .all ul li{font-size:12px;float: left;width: 100px;min-width:60px;text-align: center;line-height: 25px;}
    .menu{width:200px;float: left;}
</style>
<%
    ListContainer lc = (ListContainer) request.getAttribute("lc");
%>
<script type="text/javascript">
    function execute(seachType) {
        $.ajaxSetup({async:false});
        $("#searchType").val(seachType);
        listForm.submit();
        $("#searchType").val("");
    }

    function quickSearch(status) {
        $("#quickSearchType").val(status);
        $("#searchType").val('search');
        listForm.submit();
    }
</script>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：网课订单报表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" id="listForm" scope="request" action="<%=path%>/business/ProductOrder.do?act=netList" method="post">
        <input type="hidden" name="searchType" id="searchType" value=""/>
        <div class="box2" panelTitle="功能面板" roller="false">
            <table >
                <tr>
                    <td>
                        <div style="width: 190px;float: left;">
                            开始时间
                            <input  class="date" style="width: 120px;" type="text" name="starttime" value="${starttime }" id="starttime"  />
                        </div>
                        <div style="width: 190px;float: left;">
                            截止时间
                            <input  class="date" style="width: 120px;" type="text" name="endtime" value="${endtime }" id="endtime"  />
                        </div>
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
                        <input type="button" value="查询" onclick="execute('search')" />&nbsp;&nbsp;
                        <input type="button"  value="导出当前页订单" onclick="execute('current')"/>&nbsp;&nbsp;
                        <input type="button"  value="导出所有订单" onclick="execute('all')"/>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <div style="width: 99.9%;overflow: auto;">
                <table  class="tableStyle"  mode="list" useCheckbox="true" trClick="true" selectRowButtonOnly="false" id="listTable">
                    <tr >
                        <th width="25" height="25" align="center" class="DataTable_Field"></th>
                        <%--<th width="3%" height="25"  align="center" class="DataTable_Field" ></th>--%>
                        <th width="25" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                        <th width="125" height="25"  align="center" class="DataTable_Field" title="订单号">订单号</th>
                        <th width="60" height="25"  align="center" class="DataTable_Field" title="订单状态 ">订单状态</th>
                        <%--<th height="25"  align="center" class="DataTable_Field" title="退货状态 ">退货状态</th>--%>
                        <th height="25"  align="center" class="DataTable_Field" title="商品名称">商品名称</th>
                        <th width="50" height="25"  align="center" class="DataTable_Field" title="数量">数量</th>
                        <th width="70" height="25"  align="center" class="DataTable_Field" title="订单金额">总订单金额</th>
                        <th width="70" height="25"  align="center" class="DataTable_Field" title="总订单实付金额">总订单实付金额</th>
                        <th width="70" height="25"  align="center" class="DataTable_Field" title="实付金额">实付金额</th>
                        <th height="25"  align="center" class="DataTable_Field" title="昵称">昵称</th>
                        <th height="25"  align="center" class="DataTable_Field" title="收货人 ">收货人</th>
                        <th height="25"  align="center" class="DataTable_Field" title="联系电话 ">联系电话</th>
                        <%--<th height="25"  align="center" class="DataTable_Field" title="省-市-区、县 ">省-市-区、县</th>--%>
                        <%--<th height="25"  align="center" class="DataTable_Field" title="地址 ">地址</th>--%>
                        <th height="25"  align="center" class="DataTable_Field" title="创建时间">创建时间</th>
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
                                <%--<td class="DataTable_Content" align="left" title="${ml.status }">${ml.status }</td>--%>
                            <td class="DataTable_Content" align="left" title="${ml.p_name }">${ml.p_name }</td>
                            <td class="DataTable_Content" align="left" title="${ml.pcount }">${ml.pcount }</td>
                            <td class="DataTable_Content" align="left" title="${ml.price }">${ml.price }</td>
                            <td class="DataTable_Content" align="left" title="${ml.pay_price }">${ml.pay_price }</td>
                            <td class="DataTable_Content" align="left" title="${ml.money }">${ml.money }</td>
                            <td class="DataTable_Content" align="left" title="${ml.username }">${ml.username }</td>
                            <td class="DataTable_Content" align="left" title="${ml.consignee }">${ml.consignee }</td>
                            <td class="DataTable_Content" align="left" title="${ml.telephone }">${ml.telephone }</td>
                            <%--<td class="DataTable_Content" align="left" title="${ml.pname }">${ml.pname }</td>--%>
                            <%--<td class="DataTable_Content" align="left" title="${ml.address }">${ml.address }</td>--%>
                            <td class="DataTable_Content" align="left" title="${ml.createtime }">${ml.createtime }</td>
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

