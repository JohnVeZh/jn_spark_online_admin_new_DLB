<%@page import="com.business.coupon.dao.CouponTemplate"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri ="/struts-tags"%>
<%@page import="com.easecom.common.util.ListContainer"%>
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
<script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
<!--3.3框架必需end-->

<%
    ListContainer lc = (ListContainer) request.getAttribute("lc");
%>
<script type="text/javascript">
    // 获取选中的数据
    function getSelectedIds() {
        var inps = document.getElementsByName('id');
        var idVal = "";
        var j =0 ;
        for(var i = 0; i <inps.length; i++){
            if(inps[i].checked){
                idVal += inps[i].value+",";
                j++;
            }else{
                continue;
            }
        }
        if (j < 1) {
            top.Dialog.alert("请至少选中一条数据！");
            return "";
        }else{
            return idVal.substring(0,idVal.length-1);
        }
    }


    // 资源绑定
    function bindResource(id){
        var idVal ;
        if(id){
            idVal =  id;
        }else{
            idVal =  getSelectedIds();
        }
        var templateId = $("#templateId").val();
        var relationType = $('#relationType').val();
        if(idVal){
            $.post('<%=path%>/business/coupon.do?act=bindResource',{templateId:templateId,relationType:relationType,contentId:idVal},function (data) {
                if(data.result){
                    top.Dialog.alert("轮播绑定成功！");
                    window.location.reload();
                    reload("coupon.do?act=templateRelationList&templateId=${templateId}");
                }else{
                    top.Dialog.alert("轮播绑定失败！");
                }
            },"json");
        }
    }
</script>
<body>
<form name="operForm" style="display: none;" scope="request" method="post"></form>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：轮播绑定</span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/coupon.do?act=preBindResourceList" method="post">
        <input type="hidden" name="templateId" id="templateId"value="${templateId }" />
        <input type="hidden" name="relationType" id="relationType" value="${relationType }" />
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <div style="float: left">
                            网课标题：
                        </div>
                        <div style="float: left">
                            <input type="text" name="name"/>&nbsp;&nbsp;
                        </div>
                        <div style="float: left">
                            <input type="submit" value="查询" />&nbsp;&nbsp;
                            <input type="button" onclick="bindResource()" value="批量绑定" />&nbsp;&nbsp;
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field"></th>
                <th style="width:40px">序号</th>
                <th height="25"  align="center" class="DataTable_Field">轮播名称</th>
                <th height="25" style="width: 200px;" align="center" class="DataTable_Field" style="width:80px">排序</th>
                <th style="width: 120px;">操作</th>
            </tr>
            <c:forEach items="${lc.list}" var="item" varStatus="stat">
                <tr>
                    <td align="center"><input type="checkbox" name="id" value="${item.id}" onclick="event.cancelBubble=true;"></td>
                    <td align="center">${lc.index + stat.count}</td>
                    <td class="DataTable_Content" align="left" title="${item.name}">${item.name}</td>
                    <td class="DataTable_Content" align="left" title="${item.sort}">${item.sort}</td>
                    <td>
                        <a title="绑定" href="javascript:void(0);" onclick="bindResource('${item.id}')">
                            <span class="img_ok"></span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
            <div class="center">
                <div class="right">
                    <%@include file="../../../include/listpage.jsp"%>
                </div>
            </div>
        </div>
        <div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    </form>
</div>
</body>
</html>
