<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.Product.Product"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path %>/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path %>/js/domeURL.js"></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
<!--框架必需end-->

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
		int star = Integer.parseInt(request.getAttribute("colloSize").toString());
		
	%>
	<script type="text/javascript">
		//详情
		function view(id) {
			if(id!=""){
		  	   listForm.action="Product.do?act=view&id="+id;
		  		listForm.submit();
			}
		}
		
		function selProduct(productId,type){
			/* top.document.getElementById("frmright").contentWindow.document.getElementById("proId").value 	= productId;
			top.document.getElementById("frmright").contentWindow.document.getElementById("selName").innerHTML 	= "   已选择："+proName;
			top.Dialog.close(); */
			var nameStr = $("#nameStr").val();
			var packageId = $("#packageId").val();
			if(productId!=""){
		  	   listForm.action="<%=path%>/business/ProductPackage.do?act=updateCollocation&productId="+productId+"&packageId="+packageId+"&nameStr="+nameStr+"&type="+type;
		  		listForm.submit();
			}

		}
		
		function comments(id){
			if(id!=""){
		  	   listForm.action="Comments.do?act=list&proId="+id;
		  		listForm.submit();
			}
		}
		
		//清空查询数据
		function qing(){
			document.getElementById("nameStr").value="";
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/ProductPackage.do?act=collocationList" method="post">
				<input type="text" style="display: none;" name="packageId" id="packageId" value="${packageId }" />
				<table style="width:100%">
				<tr>
					<td>
						<div class="box_tool_line"></div> 
					    名称：<input type="text" name="nameStr" id="nameStr" value="${nameStr }"/>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr >
					<th height="25"  align="center" class="DataTable_Field" title="序号">序号</th>								 
					<th height="25"  align="center" class="DataTable_Field" title="商品名称 * 类型：String 长度:100">商品名称</th>	
					<th height="25"  align="center" class="DataTable_Field" title="价格 * 类型：int 长度:10">价格</th>								 
					<th height="25"  align="center" class="DataTable_Field" title="销量 * 类型：int 长度:10">销量</th>	
												 
					<th width="260px"> 操作	</th>							 
				</tr>
				 <tr>
				    <td colspan="5"><h4>已搭配</h4></td>
				 </tr>
				<!-- 循环出已搭配的产品 -->
				 <c:forEach items="${collocationList }" var="pro" varStatus="status"> 
				   <tr  >
				        <td align="center">${status.index +1 }</td>
						<td class="DataTable_Content" align="left"> ${pro.pName }</td>								
						<td class="DataTable_Content" align="left">${pro.pPresentPrice }</td>	
						<td class="DataTable_Content" align="left">${pro.sales }</td>								
						<td>
						<div align="center">
							<div class="box_tool_line"></div> <a href="javascript:;" 
								onclick="selProduct('${pro.id }','del')"> <span class="icon_edit">取消搭配</span>
							</a>
						</div>
						</td>							
				 	</tr>
				 </c:forEach>
				 	<tr>
				    <td colspan="5"><h4>未搭配</h4></td>
					 </tr>
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Product po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Product)list.get(i);
%>
	<tr  >
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getpName() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getpPresentPrice() %></td>	
		<td class="DataTable_Content" align="left"><%= po.getSales() %></td>								
		<td>
		<div align="center">
			<div class="box_tool_line"></div> <a href="javascript:;" 
				onclick="selProduct('<%=po.getId()%>','add')"> <span class="icon_edit">搭配</span>
			</a>
		</div>
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
		</form>
	</div>
	</body>
</html>
