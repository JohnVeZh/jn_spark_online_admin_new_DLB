<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.ProductOrderEvaluate.ProductOrderEvaluate"%>
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
				   		listForm.action="ProductOrderEvaluate.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="ProductOrderEvaluate.do?act=realdeleteById&eId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="ProductOrderEvaluate.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			//var idVal = isSel();
			if(id !=""){
			  listForm.action="ProductOrderEvaluate.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id !=""){
			top.Dialog.open({URL:"<%=path%>/business/ProductOrderEvaluate.do?act=view&id="+id,ID:"a11",Width:1024,Height:768,Title:"查看"});
			
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
					<span>当前位置：评价管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/ProductOrderEvaluate.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<!-- 
					<a onclick="preAdd();">新增</a> -->
					<!-- <a id="del_mfp"
						href="javascript:;" onclick="doDels()"> <span
							class="icon_delete">删除</span>
					</a>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr> -->
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%">序号</th>      
						<!-- <th height="25"  align="center" class="DataTable_Field" title="订单商品表">商品订单</th>    -->
						<th height="25"  align="center" class="DataTable_Field" title="订单商品 名称">商品名称</th>
						<th height="25"  align="center" class="DataTable_Field" title="平均分 ">平均分</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="评价时间 ">评价时间</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="用户">用户</th>								 
						<th>操作</th>								 
					</tr>
					 
<%-- 				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Map po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Map)list.get(i);
%>
	<tr>
    	<td align="center"><input type="checkbox" name="id" value="<%=po.get("id")%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left"><% out.print(Tool.getValue("select p_name from product where id='"+po.get("productId")+"'"));  %></td>	
		<td class="DataTable_Content" align="left"><%= po.get("averageScore") %></td>								
		<td class="DataTable_Content" align="left"><%= po.get("createtime") %></td>								
									
		<td class="DataTable_Content" align="left"><% out.print(Tool.getValue("select username from users where id='"+po.get("userId")+"'"));  %></td>								
		<td>
			<a href="javascript:;" title="查看"
				onclick="view('<%= po.get("id") %>')"> <span class="img_view"></span>
			</a>
			<a id="del_mfp" title="删除"
				href="javascript:;" onclick="doDels()"> <span
					class="img_delete"></span>
			</a>
		</td>								
 	</tr>
<%
	}
%>
 --%>
	<c:forEach items="${lm }" var="l" varStatus="s">
		<tr  >
	    	<td align="center"><input type="checkbox" name="id" value="${l.id }" onclick="event.cancelBubble=true;"></td>
	        <td align="center">${s.index+1 }</td>
	        <%-- 
	        <td class="DataTable_Content" align="left">${l.podId}</td>
	         --%>
	        <td class="DataTable_Content" align="left">${l.pName }</td>	
			<td class="DataTable_Content" align="left">${l.averageScore }</td>								
			<td class="DataTable_Content" align="left">${l.createtime }</td>								
										
			<td class="DataTable_Content" align="left">${l.userName }</td>								
			<td>
			    <%-- <a href="javascript:;"
							onclick="preUpdate('${l.id }')"> <span class="icon_edit">修改</span>
						</a> --%>
				<a href="javascript:;"
					onclick="view('${l.id }')"> <span class="icon_view">查看</span>
				</a>
				<a id="del_mfp"
					href="javascript:;" onclick="doDelsById('${l.id }')"> <span
						class="icon_delete">删除</span>
				</a>
			</td>								
	 	</tr>
	
	</c:forEach>	 		 
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
