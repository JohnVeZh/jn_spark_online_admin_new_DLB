<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommonType.CommonType"%>
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
		$(function(){
			var msg="";//jstl $ { msg}
			if("200"==msg){
				top.Dialog.alert("删除成功.");
			}
		})
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
	
		//删除数据
		function doDels() {
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="CommonType.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//删除单个数据
		function doDelById(id){
		  if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="CommonType.do?act=realdelete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="CommonType.do?act=preAdd";
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			//var idVal = isSel();
			if(id !=""){
			  listForm.action="CommonType.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id!=""){
		  	   listForm.action="CommonType.do?act=view&id="+id;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("typeName").value="";
			document.getElementById("sel").value="";
		}
		
		function checkWords(id){
		   if(id!=""){
		  	    listForm.action="CommonWords.do?act=list&ctId="+id;
		  		listForm.submit();
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" align="left">
					<span>当前位置：词汇类别列表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/CommonType.do?act=type" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
			  <input type="text" name="parentid" value="${parentid }" style="display: none"/>
				<table style="width:100%">
				<tr>
				类别名称：<input type="text" value="${typeName }" name="typeName" id="typeName"/>	&nbsp;&nbsp; 
				       <input type="submit" value="查询" />&nbsp;&nbsp;
				       <input type="button" value="清空" onclick="qing()" />
				        
				</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr >
					<th width="3%" height="25" align="center" class="DataTable_Field">
					</th>
			        <th height="25"  align="center" class="DataTable_Field">序号</th>
					<th height="25"  align="center" class="DataTable_Field" title="类别名称 * 类型：String 长度:100">类别名称</th>								 
					<th height="25"  align="center" class="DataTable_Field" title="创建时间">创建时间</th>
					<th height="25"  align="center" class="DataTable_Field" title="排序 * 类型：String 长度:10">排序</th>								 
					<th height="25"  align="center" style="width: 200px" align="center" class="DataTable_Field" title=" * 类型：int 长度:10" >操作</th>								 
				</tr>
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	CommonType po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (CommonType)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getTypeName() %></td>
		<td class="DataTable_Content" align="left"><%=po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getSort() %></td>								
		<td class="DataTable_Content" align="left">
		<a href="javascript:;" onclick="checkWords('<%=po.getId()%>')"> 
		<span class="icon_edit">相关词汇</span>
		</a>
		</td>								
 	</tr>
<%
	}
%>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
