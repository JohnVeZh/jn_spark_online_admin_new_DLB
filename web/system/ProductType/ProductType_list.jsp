<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.ProductType.ProductType"%>
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
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
	 $(function(){
	  top.Dialog.close();
	 })
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
				   		listForm.action="ProductType.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}//删除数据
		function doDelById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="ProductType.do?act=realdelete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){  
		    var parentid=$("#parentid").val();
		    if(parentid!=""){
		      top.Dialog.open({URL:"<%=path%>/business/ProductType.do?act=preAdd&parentid="+parentid,ID:"a1",Width:1024,Height:768,Title:"新增"});     
		    }
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			var parentid = $("#parentid").val();
			    top.Dialog.open({URL:"<%=path%>/business/ProductType.do?act=preUpdate&id="+id+"&parentid="+parentid,ID:"a1",Width:1024,Height:768,Title:"修改"});     
			}
		}
		//详情
		function view(id) {
			if(id!=""){
		  	   listForm.action="ProductType.do?act=view&id="+id;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：产品类型管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/ProductType.do?act=list" method="post">
			<input type="text" name="parentid" id="parentid" value="${parentid }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left;">
					<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
							class="img_add"></span>
					</a>
					</div>
					<div style="float: left;">
						<div style="float: left;">
						排序方式：
						</div>
						<div style="float: left;">
							<select name="StatrSel" id="StatrSel">
							  <option value="0" selected>默认</option>
							  <option value="1">排序（正序）</option>
					          <option value="2">排序（倒序）</option>
							</select>	  
						</div>
					</div>&nbsp;&nbsp; 
					<input type="submit" value="排序" />&nbsp;&nbsp; 
					<!-- <a id="del_mfp"
						href="javascript:;" onclick="doDels()"> <span
							class="icon_delete">删除</span>
					</a> -->
						<!-- <div class="box_tool_line"></div> 
						名称：<input type="text" id="title" name="title"/>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td> -->
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:32">序号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:100">名称</th>								 
						<th>创建人</th>
						<th height="25"  align="center" class="DataTable_Field" title=" * 类型：int 长度:10">排序</th>			
						<th height="25"  style="width: 200px" align="center" class="DataTable_Field" title=" * 类型：int 长度:10">操作</th>							 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	ProductType po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (ProductType)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getTypeName() == null ? "":po.getTypeName() %></td>
		<td class="DataTable_Content" align="left">
			<% out.print(Tool.getValue("select name from sys_user where id='"+po.getSysUserId()+"'")); %>
		</td>								
		<td class="DataTable_Content" align="left"><%= po.getSort() %></td>	
		<td class="DataTable_Content" align="left">
			<a href="javascript:;"	onclick="preUpdate('<%=po.getId()%>')" title="修改"> 
				<span class="img_edit"></span>	
			</a>
			
			<%-- <div align="center">
				<div class="box_tool_line"></div>
				 <a href="javascript:;"	onclick="view('<%=po.getId()%>')"> 
				 	<span class="icon_view">查看</span>	
				 </a>
			</div> --%>
				<%-- <a id="del_mfp"	href="javascript:;" onclick="doDelById('<%=po.getId()%>')">
				 <span class="icon_delete">删除</span>
				</a> --%>
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
