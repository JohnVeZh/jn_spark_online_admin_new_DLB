<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.ProductOrderRefund.ProductOrderRefund"%>
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
<!--3.3框架必需end-->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript">


</script>


<%
    ListContainer lc = (ListContainer) request.getAttribute("lc");
%>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：优惠码管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/CouponCode.do?act=codeList" method="post">
        <div class="box2" paneltitle="功能面板" roller="false" boxtype="box2"><div class="box_topcenter" id="box_topcenter"><div class="box_topleft"><div class="box_topright"><div class="title"><span>功能面板</span></div><div class="boxSubTitle"></div><div class="status"><span class="ss">收缩</span></div><div class="clear"></div></div></div></div><div class="box_middlecenter"><div class="box_middleleft"><div class="box_middleright"><div class="boxContent" style="overflow: visible;">
            <table style="width:100%">
                <tbody><tr>
                    <td>
                        <div style="float: left">
                            优惠码：<input type="text" name="code" id="title" value="" truetype="textinput" class="textinput" style="font-family: 宋体; font-size: 12px;">&nbsp;&nbsp;
                        </div>
                        <div style="float: left">
                            状态：</div>
                        <div style="float: left">
                            <div class="mainCon">
                                <select id="status" name="status">
                                    <option value="1">有效</option>
                                    <option value="2">已使用</option>
                                    <option value="3">失效</option>
                                </select>
                        </div>

                        <div style="float: left">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="submit" value="查询" class="button" style="font-family: 宋体; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" id="add"> <span class="icon_add">新增</span></a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        </div>
        </div>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <%--<th width="3%" height="25" align="center" class="DataTable_Field">--%>
                <%--</th>--%>
                <th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                <th height="25"  align="center" class="DataTable_Field" title="优惠码">优惠码</th>
                <th height="25"  align="center" class="DataTable_Field" title="状态">状态</th>
                <th height="25"  align="center" class="DataTable_Field" title="生效时间">生效时间</th>
                <th height="25"  align="center" class="DataTable_Field" title="失效时间">失效时间</th>
                <th height="25"  align="center" class="DataTable_Field" title="生成时间">生成时间</th>
                <th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>
            </tr>

            <c:forEach items="${lm }" var="code" varStatus="s">
            <tr >
                <td align="center">${s.index+1 }</td>
                <td class="DataTable_Content" align="left" title="${code.code }">${code.code }</td>
                <td class="DataTable_Content" align="left" title="<c:choose><c:when test="${code.status==1 }">正常</c:when><c:when test="${code.status==2 }">已使用</c:when><c:when test="${code.status==3 }">失效</c:when></c:choose>"><c:choose><c:when test="${code.status==1 }">正常</c:when><c:when test="${code.status==2 }">已使用</c:when><c:when test="${code.status==3 }">失效</c:when></c:choose></td>
                <td class="DataTable_Content" align="left" title="<fmt:formatDate value="${code.effectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"><fmt:formatDate value="${code.effectTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="DataTable_Content" align="left" title="<fmt:formatDate value="${code.invalidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"><fmt:formatDate value="${code.invalidTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="DataTable_Content" align="left" title="<fmt:formatDate value="${code.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"><fmt:formatDate value="${code.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td align="left">
                    <a href="javascript:;" onclick="view('${code.id}')"><span class="icon_view">查看</span>
                    </a>
                    <a href="javascript:;"
                       onclick="preUpdate('${code.id}')"> <span class="icon_edit">修改</span>
                    </a>
                    <a id="del_mfp"
                       href="javascript:;" onclick="doDelsById('${code.id}')"> <span
                            class="icon_delete">删除</span>
                    </a>
                </td>
            </tr>
            </c:forEach>



            <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
                <div class="center">
                    <div class="right">
                        <%@include file="../../../include/listpage.jsp"%>
                    </div>
                </div>
            </div>
            <div class="diverror">友情提示: 123</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        //添加
        $('#add').click(function () {
            top.Dialog.open({URL:"<%=path%>/business/CouponCode.do?act=preCodeAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});
        });
    });
    function view(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/CouponCode.do?act=preCodeView&id="+id,ID:"a2",Width:1024,Height:768,Title:"详情"});
        }
    }
    function doDelsById(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要删除吗?",
                function() {
                    listForm.action="CouponCode.do?act=realdeleteCode&id="+id;
                    listForm.submit();
                }
            )
        }
    }
    //修改
    function preUpdate(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/CouponCode.do?act=preCodeEdit&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
        }
    }
</script>
</body>
</html>
