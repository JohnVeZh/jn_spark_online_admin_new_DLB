<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.Product.Product"%>
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
				   		listForm.action="Product.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="Product.do?act=realdeleteById&pId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){  
		    if($("#pTypeId").val()!=""){
			    listForm.action="Product.do?act=preAdd"
				listForm.submit();
		    }else{
		    	alert("请先选取左侧类别");
		    }
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			  listForm.action="Product.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="Product.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		
		//搭配图书套餐
		function preMoney(id) {
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/ProductCollocation.do?act=preMoney&productId="+id+"&productFid=${productFid}&type=${type}&cType=0",ID:'Coll1',Title:'配套价格',Width:450,Height:150});
			}
		}
		function back(){
			top.Dialog.close();
		}
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/Product.do?act=Colloc_list" method="post">
			<input type="text" name="productFid" id="productFid" value="${productFid }" style="display: none"/>
			<input type="text" name="type" id="type" value="${type }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<!-- <a href="javascript:;" onclick="preAdd('')"> <span
							class="icon_add">新增</span>
					</a>
					<a id="del_mfp"
						href="javascript:;" onclick="doDels()"> <span
							class="icon_delete">删除</span>
					</a> -->
						 名称：<input type="text" name="nameStr" id="nameStr" value="${nameStr }"/>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						 <th width="5%">序号</th>
							<th width="35%" height="25"  align="center" class="DataTable_Field" title="产品名称">产品名称</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="原价 ">原价</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="现价 ">现价</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="图书类型">类型</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="">操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Product po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Product)list.get(i);
		String typeName = Tool.getValue("select type_name from product_type where id='"+po.getpTypeId()+"'");
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getpName() %>"><%= po.getpName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpOriginalPrice() %>"><%= po.getpOriginalPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpPresentPrice() %>"><%= po.getpPresentPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%=typeName   %>"><%=typeName   %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpCreatetime() %>"><%= po.getpCreatetime() %></td>	
		<td class="DataTable_Content" align="center">
		   <%--  <a href="javascript:;"
						onclick="preUpdate('<%=po.getId()%>')"> <span class="icon_edit">修改</span>
					</a>
						 <a href="javascript:;"
						onclick="prePackage('<%=po.getId()%>')"> <span class="icon_edit">搭配套餐</span>
					</a>
					<a id="del_mfp"
						href="javascript:;" onclick="doDelsById('<%=po.getId()%>')"> <span
							class="icon_delete">删除</span>
					</a> --%>
					 
					 <a href="javascript:;" title="搭配"
						onclick="preMoney('<%=po.getId()%>')"> <span class="img_add"></span>
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
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
