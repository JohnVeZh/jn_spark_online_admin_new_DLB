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
        if(idVal != ""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        operForm.action="<%=path%>/business/coupon.do?act=templateRelationDelete&templateId=${templateId }"+idVal;
                        operForm.submit();
                    }
            )
        }
    }
    
    // 删除
    function deleteById(id) {
        if(id != ""){
            top.Dialog.confirm("您确信要删除吗?",
               function() {
                   operForm.action="<%=path%>/business/coupon.do?act=templateRelationDelete&templateId=${templateId }&id="+id;
                   operForm.submit();
               }
            )
        }
    }
    
    // 资源绑定
    function preBindResource(relationType) {
    	top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=preBindResourceList&templateId=${templateId }&relationType=" + relationType,ID:'coupon_template_relation_bind',Width:1024,Height:768,Title:'查看'});
    }
</script>
<body>
<form name="operForm" style="display: none;" scope="request" method="post"></form>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：资源绑定</span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/coupon.do?act=templateRelationList" method="post">
    	<input type="hidden" name="templateId" value="${templateId }" />
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <div style="float: left">
                        	资源类型：
                        </div>
                        <div style="float: left">
                            <select name="relationType" id="relationType" >
                                <option value="all">全部</option>
                                <c:if test="${templateType == 2}">
                                	<option value="1" <c:if test="${relationType == '1'}">selected="selected"</c:if>>社区活动</option>
                                	<option value="5" <c:if test="${relationType == '5'}">selected="selected"</c:if>>首页轮播图</option>
                                	<option value="6" <c:if test="${relationType == '6'}">selected="selected"</c:if>>2.0轮播图</option>
                                	<option value="7" <c:if test="${relationType == '7'}">selected="selected"</c:if>>2.0轮播图-新</option>
                                </c:if>
                                <c:if test="${templateType == 1}">
	                                <option value="2" <c:if test="${relationType == '2'}">selected="selected"</c:if>>图书</option>
	                                <option value="3" <c:if test="${relationType == '3'}">selected="selected"</c:if>>网课</option>
                                </c:if>
                        	</select>&nbsp;&nbsp;
                        </div>
                        <div style="float: left">
                        	<input type="submit" value="查询" />&nbsp;&nbsp;
                        	<c:if test="${templateType == 2}">
                            	<input type="button" onclick="preBindResource('1')" title="绑定社区活动" value="绑定社区活动" />&nbsp;&nbsp;
                            	<input type="button" onclick="preBindResource('5')" title="绑定轮播图" value="绑定轮播图" />&nbsp;&nbsp;
                            	<input type="button" onclick="preBindResource('6')" title="2.0轮播图" value="2.0轮播图" />&nbsp;&nbsp;
                            	<input type="button" onclick="preBindResource('7')" title="2.0轮播图-新" value="2.0轮播图-新" />&nbsp;&nbsp;
                            </c:if>
                            <c:if test="${templateType == 1}">
	                            <input type="button" onclick="preBindResource('2')" title="绑定图书" value="绑定图书" />&nbsp;&nbsp;
	                            <input type="button" onclick="preBindResource('3')" title="绑定网课" value="绑定网课" />&nbsp;&nbsp;
                            </c:if>
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
                <th height="25" style="width:150px" align="center" class="DataTable_Field">资源名称</th>
                <th height="25"  align="center" class="DataTable_Field" style="width:80px">资源类型</th>
                <th style="width: 120px;">操作</th>
            </tr>
			<c:forEach items="${relList}" var="item" varStatus="stat">
				<tr>
					<td align="center"><input type="checkbox" name="id" value="${item.id}" onclick="event.cancelBubble=true;"></td>
					<td align="center">${lc.index + stat.count}</td>
	                <td class="DataTable_Content" align="left" title="${item.contentName}">${item.contentName}</td>
	                <td class="DataTable_Content" align="left" title="${item.type}">
	                	<c:choose>
	                		<c:when test="${item.type == 1}">社区活动</c:when>
	                		<c:when test="${item.type == 2}">图书</c:when>
	                		<c:when test="${item.type == 3}">网课</c:when>
	                		<c:when test="${item.type == 5}">首页轮播图</c:when>
	                		<c:when test="${item.type == 6}">2.0轮播图</c:when>
	                		<c:when test="${item.type == 7}">2.0轮播图-新</c:when>
	                	</c:choose>
	                </td>
	                <td>
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
