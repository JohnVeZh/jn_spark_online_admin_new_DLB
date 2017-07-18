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
						operForm.action="<%=path%>/business/training/suggestion.do?act=delete"+idVal;
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
					operForm.action="<%=path%>/business/training/suggestion.do?act=delete&id="+id;
					operForm.submit();
				}
			)
		}
	}

	// 新增
	function preAdd(){
		top.Dialog.open({URL:"<%=path%>/business/training/suggestion.do?act=preAdd",ID:'special_suggestion_add',Width:1024,Height:768,Title:'新增'});
	}
	
	// 修改
	function preUpdate(id) {
		if(id !=""){
			top.Dialog.open({URL:"<%=path%>/business/training/suggestion.do?act=preUpdate&id="+id,ID:'special_suggestion_update',Width:1024,Height:768,Title:'修改'});
		}
	}
	
	// 详情
	function view(id) {
		if(id != ""){
			top.Dialog.open({URL:"<%=path%>/business/training/suggestion.do?act=view&id="+id,ID:'special_suggestion_view',Width:1024,Height:768,Title:'查看'});
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
					<span>当前位置：分析建议</span>
				</div>
			</div>
		</div>
	</div>
	<form name="listForm" scope="request" action="<%=path%>/business/training/suggestion.do?act=list" method="post">
		<div class="box2" panelTitle="功能面板" roller="false">
			<table style="width:100%">
				<tr>
					<td>
						<div style="float: left">
							学段：
						</div>
						<div style="float: left">
							<select name="section" id="section" >
								<option value="all">全部</option>
								<option value="01" <c:if test='${section == "01"}'>selected="selected"</c:if>>四级</option>
								<option value="02" <c:if test='${section == "02"}'>selected="selected"</c:if>>六级</option>
							</select>&nbsp;&nbsp;
						</div>
						<div style="float: left">
							专项类型：
						</div>
						<div style="float: left">
							<select name="trainingType" id="trainingType" >
								<option value="all">全部</option>
								<option value="0" <c:if test='${trainingType == "0"}'>selected="selected"</c:if>>背单词</option>
								<option value="1" <c:if test='${trainingType == "1"}'>selected="selected"</c:if>>听力</option>
								<option value="2" <c:if test='${trainingType == "2"}'>selected="selected"</c:if>>阅读</option>
								<option value="3" <c:if test='${trainingType == "3"}'>selected="selected"</c:if>>翻译</option>
								<option value="4" <c:if test='${trainingType == "4"}'>selected="selected"</c:if>>写作</option>
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
				<th height="25"  align="center" class="DataTable_Field" style="width:60px;">学段</th>
				<th height="25"  align="center" class="DataTable_Field" style="width:80px">专项类型</th>
				<th height="25"  align="center" class="DataTable_Field" style="width:60px">开始分数</th>
				<th height="25"  align="center" class="DataTable_Field" style="width:60px">结束分数</th>
				<th height="25"  align="center" class="DataTable_Field">分析</th>
				<th height="25"  align="center" class="DataTable_Field" style="width:160px;">创建时间</th>
				<th style="width: 80px;">操作</th>
			</tr>
			<c:forEach items='${lc.list}' var="item" varStatus="stat">
				<tr>
					<td align="center"><input type="checkbox" name="id" value='${item.id}' onclick="event.cancelBubble=true;"></td>
					<td align="center">${lc.index + stat.count}</td>
					<td class="DataTable_Content" align="left" title='${item.section}'>
						<c:choose>
							<c:when test='${item.section == "01"}'>四级</c:when>
							<c:when test='${item.section == "02"}'>六级</c:when>
						</c:choose>
					</td>
					<td class="DataTable_Content" align="left" title='${item.trainingType}'>
						<c:choose>
							<c:when test='${item.trainingType == "0"}'>背单词</c:when>
							<c:when test='${item.trainingType == "1"}'>听力</c:when>
							<c:when test='${item.trainingType == "2"}'>阅读</c:when>
							<c:when test='${item.trainingType == "3"}'>翻译</c:when>
							<c:when test='${item.trainingType == "4"}'>写作</c:when>
						</c:choose>
					</td>
					<td class="DataTable_Content" align="left" title='${item.start}'>
						<fmt:formatNumber value="${item.start}" pattern="0.0" />
					</td>
					<td class="DataTable_Content" align="left" title='${item.end}'>
						<fmt:formatNumber value="${item.end}" pattern="0.0" />
					</td>
					<td class="DataTable_Content" align="left" style="overflow:hidden;">${item.analysis}</td>
					<td class="DataTable_Content" align="left" title='${item.createTime}'>
						<fmt:formatDate value='${item.createTime}' pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<a  title="详情" href="javascript:void(0);" onclick="view('${item.id}')">
							<span class="img_view"></span>
						</a>
						<a href="javascript:void(0);" title="修改" onclick="preUpdate('${item.id}')">
							<span class="img_edit"></span>
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
		<div class="diverror">
			友情提示：单词（0~100）、听力（0~248.5）、阅读（0~248.5）、翻译（0~106.5）、写作（0~106.5）
		</div>
	</form>
</div>
</body>
</html>
