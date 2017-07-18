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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/index.css" rel="stylesheet">

 

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
				   		listForm.action="ProductOrderRefund.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="ProductOrderRefund.do?act=realdelete&porId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="ProductOrderRefund.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			 top.Dialog.open({URL:"<%=path%>/business/ProductOrderRefund.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
			}
		}
		//详情
		function view(id) {
			if(id!=""){
			 top.Dialog.open({URL:"<%=path%>/business/ProductOrderRefund.do?act=view&id="+id,ID:"a2",Width:1024,Height:768,Title:"修改"});
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：退款记录<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/ProductOrderRefund.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left;">
						<a id="del_mfp"
							href="javascript:;" onclick="doDels()"> <span
								class="icon_delete">删除</span>
						</a>
					</div>
					<div style="float: left;">
						退款订单编号：<input type="text" name="orderCode" value="${orderCode }"/>					 
					</div>
					<div style="float: left;">
						    起始时间：<input  class="date" type="text" name="starttimeStr" value="${starttime }" id="starttimeStr"  />-<input  class="date" type="text" name="endtimeStr" value="${endtime }" id="endtimeStr"  />
					</div>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="订单商品">订单商品</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="商品状态 ">商品状态</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="退款金额 ">退款金额</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="类型 ">类型</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>	
						<th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>								 
					</tr>
					 
			<c:forEach items="${lm }" var="por" varStatus="s">
				<tr >
			    	<td align="center"><input type="checkbox" name="id" value="${por.id }" onclick="event.cancelBubble=true;"></td>
			        <td align="center">${s.index+1 }</td>
					<td class="DataTable_Content" align="left" title="${por.pName }">${por.pName }</td>								
					<td class="DataTable_Content" align="left" title="${por.productStatus==0?'未收到货':'已收到货' }">${por.productStatus==0?"未收到货":"已收到货" }</td>								
					<td class="DataTable_Content" align="left" title="${por.fee }">${por.fee }</td>								
					<td class="DataTable_Content" align="left" title="${por.type==0?'退款':'退款退货' }">${por.type==0?"退款":"退款退货" }</td>								
					<td class="DataTable_Content" align="left" title="${por.createtime }">${por.createtime }</td>	
					<td align="left">
						 <a href="javascript:;"
							onclick="view('${por.id}')"> <span class="icon_view">查看</span>
						 </a>
						
					<a id="del_mfp"
						href="javascript:;" onclick="doDelsById('${por.id}')"> <span
							class="icon_delete">删除</span>
					</a>
					</td>							
			 	</tr>
			</c:forEach>	 		 
 	
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
