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
                idVal +="&id="+ inps[i].value;
                j++;
            }else{
                continue;
            }
        }
        if (j < 1) {
            top.Dialog.alert("请至少选中一条数据！");
            return "";
        }else{
            return idVal;
        }
    }

    // 批量删除
    function batchDelete() {
        var idVal = getSelectedIds();
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        operForm.action="<%=path%>/business/coupon.do?act=templateDelete"+idVal;
                        operForm.submit();
                    }
            )
        }
    }
    
    // 删除
    function deleteById(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        operForm.action="<%=path%>/business/coupon.do?act=templateDelete&id="+id;
                        operForm.submit();
                    }
            )
        }
    }

    // 新增
    function preAdd(){
        top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=templatePreAdd",ID:'coupon_template_add',Width:1024,Height:420,Title:'新增'});
    }
    
    // 修改
    function preUpdate(id) {
        if(id !=""){
            top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=templatePreUpdate&id="+id,ID:'coupon_template_update',Width:1024,Height:420,Title:'修改'});
        }
    }
    
    // 详情
    function view(id) {
        if(id != ""){
            top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=templateView&id="+id,ID:'a13',Width:1024,Height:768,Title:'查看'});
        }
    }
    
    // 资源绑定
    function preBindList(id) {
        if(id != ""){
            top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=templateRelationList&templateId="+id,ID:'coupon_template_relation_list',Width:1024,Height:768,Title:'查看'});
        }
    }

    $(function(){
        top.Dialog.close();
    });
</script>
<body>
<form name="operForm" style="display: none;" scope="request" method="post"></form>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：优惠券模板</span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/coupon.do?act=templateList" method="post">
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <div style="float: left">
                        	模板名称：
                        </div>
                        <div style="float: left">
                        	<input type="text" name="title" id="title" value="${title }"/>&nbsp;&nbsp;
                        </div>
                        <div style="float: left">
                        	模板类型：
                        </div>
                        <div style="float: left">
                            <select name="templateType" id="templateType" >
                                <option value="all">全部</option>
                                <option value="1" <c:if test="${templateType == '1'}">selected="selected"</c:if>>图书网课</option>
                                <option value="2" <c:if test="${templateType == '2'}">selected="selected"</c:if>>其他</option>
                        	</select>&nbsp;&nbsp;
                        </div>
                        <div style="float: left">
                        	<input type="submit" value="查询" />&nbsp;&nbsp;
                            <input type="button" onclick="preAdd()" title="新增" value="新增" />&nbsp;&nbsp;
                            <input type="button" onclick="batchDelete()" title="批量删除" value="批量删除" />&nbsp;&nbsp;
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field"></th>
                <th style="width:40px">序号</th>
                <th height="25"  align="center" class="DataTable_Field">模板名称</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:80px">模板类型</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:40px;">状态</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:60px;">有效期限</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:60px">优惠类型</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:70px;">满额</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:70px;">减额</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:50px;">折扣</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:70px;">领取数量</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:70px;">使用数量</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:160px;">创建时间</th>
                <th style="width: 120px;">操作</th>
            </tr>
			<c:forEach items="${lc.list}" var="item" varStatus="stat">
				<tr>
					<td align="center"><input type="checkbox" name="id" value="${item.id}" onclick="event.cancelBubble=true;"></td>
					<td align="center">${lc.index + stat.count}</td>
	                <td class="DataTable_Content" align="left" title="${item.title}">${item.title}</td>
	                <td class="DataTable_Content" align="left" title="${item.type}">
	                	<c:choose>
	                		<c:when test="${item.type == 1}">图书网课</c:when>
	                		<c:when test="${item.type == 2}">其他</c:when>
	                	</c:choose>
	                </td>
	                <td class="DataTable_Content" align="left" title="${item.status}">
	                	<c:choose>
	                		<c:when test="${item.status == 1}">正常</c:when>
	                		<c:otherwise>停用</c:otherwise>
	                	</c:choose>
	                </td>
	                <td class="DataTable_Content" align="left" title="${item.effectPeriod}">${item.effectPeriod}天</td>
	                <td class="DataTable_Content" align="left" title="${item.discountType}">
	                	<c:choose>
	                		<c:when test="${item.discountType == 1}">满减</c:when>
	                		<c:when test="${item.discountType == 2}">折扣</c:when>
	                	</c:choose>
	                </td>
	                <td class="DataTable_Content" align="left" title="${item.minMoney}">
	                	<c:if test="${item.discountType == 1}">
		                	<fmt:formatNumber value="${item.minMoney}" pattern="0.00" />
	                	</c:if>
	                </td>
	                <td class="DataTable_Content" align="left" title="${item.discountMoney}">
	                	<c:if test="${item.discountType == 1}">
		                	<fmt:formatNumber value="${item.discountMoney}" pattern="0.00" />
	                	</c:if>
	                </td>
	                <td class="DataTable_Content" align="left" title="${item.discountRate}">
	                	<c:if test="${item.discountType == 2}">
		                	<fmt:formatNumber value="${item.discountRate}" pattern="0.00" />
	                	</c:if>
	                </td>
	                <td class="DataTable_Content" align="left" title="${item.gainNum}">${item.gainNum}</td>
	                <td class="DataTable_Content" align="left" title="${item.useNum}">${item.useNum}</td>
	                <td class="DataTable_Content" align="left" title="${item.createTime}">
	                	<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                </td>
	                <td>
	                    <a  title="详情" href="javascript:void(0);" onclick="view('${item.id}')">
	                    	<span class="img_view"></span>
	                    </a>
	                    <a href="javascript:void(0);" title="修改" onclick="preUpdate('${item.id}')">
	                       <span class="img_edit"></span>
	                    </a>
	                    <a id="building" href="javascript:;" title="资源绑定" onclick="preBindList('${item.id}')">
	                    	<span class="img_mark"></span>
	                    </a>
	                    <a title="删除" href="javascript:void(0);" onclick="deleteById('${item.id}')">
	                    	<span class="img_delete"></span>
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
