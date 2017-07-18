<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.NetworkVideo.NetworkVideo"%>
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
				   		listForm.action="NetworkVideo.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NetworkVideo.do?act=realdeleteById&networkId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="NetworkVideo.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			  listForm.action="NetworkVideo.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="NetworkVideo.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("networkName").value="";
			document.getElementById("stateStr").value="";
		}
		
		//课时管理
		function catalog(id) {
			if(id!=""){
		  	   listForm.action="NetworkVideo.do?act=preAddByCateLog&nvId="+id;
		  		listForm.submit();
			}
		}
		//视频管理
		function catalogVideo(id) {
			if(id!=""){
		  	   listForm.action="NetworkVideo.do?act=preAddByCateLogVideo&nvId="+id;
		  		listForm.submit();
			}
		}
		 
		 //搭配套餐
		function prePackage (id) {
			if(id!=""){
			  listForm.action="ProductCollocation.do?act=list&productFid="+id+"&type=1";
			  listForm.submit();
			}
		}
		//首页推荐
		function AjaxUpt(id,type){
			if(id!=''){
			top.Dialog.confirm("如已有首页推荐，将自动取消原有的推荐 ，是否继续?",
			function() {
			  $.post("NetworkVideo.do?act=AjaxUpt", {id:id,type:type},
			   function(data){
			    if(data.succeed='000'){
			      window.location.reload();
			    }
			   }, "json");
			});
		}
	}
	
	function addnv(id){
	  var productId = $("#productId").val();
	  if(id!=""){
	    $.post(
	     "ProductNetworkVideo.do?act=addNv",
	     {id:id,productId:productId},
	     function(data){
	      if(data.succeed='000'){
		    	//top.frmright.window.location.reload();
		    	alert("搭配成功，请手动刷新");
		    	top.Dialog.close();
		   }
	     },
	     "json"
	    );
	  }
	}
	function addNvList(){
	  var productId=$("#productId").val();
	  var idVal = isSel1();
	  if(idVal!=""){
	  	   $.post(
	  	       "ProductNetworkVideo.do?act=addNv"+idVal,
	  	        {productId:productId},
			    function(data){
			     if(data.succeed='000'){
			    	top.frmright.window.location.reload();
			    	top.Dialog.close();
			    }
			   }, "json");
			}
	}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：课程管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkVideo.do?act=productList" method="post" >
			<input type="text" id="productId" name="productId" value="${productId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left">
						<a href="javascript:;" onclick="addNvList('')" title="配套绑定"> <span
								class="img_add"></span>
						</a>&nbsp;&nbsp;
					</div>
					    名称：<input type="text" value="${networkName }" name="networkName" id="networkName"/>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="网课名称 ">网课名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="简介">网课简介</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="销售量 ">销售量 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="课程价格 ">课程价格</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="课程类型 ">课程类型</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否免费 ">是否免费</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否限时免费 ">是否限时免费</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="排序 ">排序</th>		
						<th height="25"  align="center" class="DataTable_Field" title="">操作</th>						 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	NetworkVideo po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NetworkVideo)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getNetworkName() %>"><%= po.getNetworkName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getBrief() %>"><%= po.getBrief() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getBrief() %>"><%= po.getSaleNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getNetworkMoney() %>"><%= po.getNetworkMoney() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getNetworkType()==0)out.print("录播");else out.print("直播"); %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getIsFree()==0)out.print("否");else out.print("是"); %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getIsLimitFree()==0)out.print("否");else out.print("是"); %></td>								
		<td class="DataTable_Content" align="left"><%= po.getSort() %></td>	
		<td class="DataTable_Content" align="left">
		 	
			<a href="javascript:;" title="配套绑定"
						onclick="addnv('<%=po.getId()%>')" > <span class="img_add"></span>
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
