<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.MatchedPapers.MatchedPapers"%>
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

<!-- 树组件start -->
<script type="text/javascript" src="<%=path%>/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/libs/js/tree/ztree/ztree.css"></link>
<!-- 树组件end -->
 
<!-- 树形下拉框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/selectTree.js"></script>
<!-- 树形下拉框end -->
 

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
	
		
		//绑定试卷
		function addMp(id) {
		var productId = $("#productId").val();
			if(id!=""){
		  	   $.post("ProductMatchedPaper.do?act=addMp", {id:id,productId:productId},
				   function(data){
				    if(data.succeed='000'){
				    	//top.frmright.window.location.reload();
				    	alert("搭配成功，请手动刷新");
				    	top.Dialog.close();
				    }
				   }, "json");
			}
		}
		
		function addMpList() {
			var productId = $("#productId").val();
			var idVal = isSel1();
			if(idVal!=""){
		  	   $.post("ProductMatchedPaper.do?act=addMp"+idVal, {productId:productId},
				   function(data){
				    if(data.succeed='000'){
				    	//top.frmright.window.location.reload();
				    	alert("搭配成功，请手动刷新");
				    	top.Dialog.close();
				    }
				   }, "json");
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		
		
		
		function likeFind() {
			var levelType =  $("#selectTree1").attr("relValue");
	  	    listForm.action="<%=path%>/business/MatchedPapers.do?act=productList&scType="+levelType;
	  		listForm.submit();
		} 
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" >
					<span>当前位置：试卷管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/MatchedPapers.do?act=productList" method="post">
			<input type="text" id="productId" name="productId" value="${productId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
						<div style="float: left;">
							<a id="del_mfp" title="配套绑定"
							href="javascript:;" onclick="addMpList()"> <span
								class="img_add"></span></a>
						</div>

						<div class="selectTree" data='${levelTypeStr }' id="selectTree1" selectedValue="${scType }">
						</div>
						<div style="float: left;">
						名称：<input type="text" name="loginName" value="${loginName }"/>
						</div>
						 <input type="button" value="查询" onclick="likeFind()"/>&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td> 
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="id * 类型：String 长度:32">序号</th>								 
						<th width="50%" height="25"  align="center" class="DataTable_Field" title="试卷名称 * 类型：String 长度:100">试卷名称 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建时间 * 类型：String 长度:50">创建时间 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="排序 * 类型：int 长度:10">排序 </th>	
						<th width="5%"height="25"  align="center" class="DataTable_Field" title="排序 * 类型：int 长度:10">操作</th>	
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	MatchedPapers po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (MatchedPapers)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getMpName() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getSort() %></td>	
		<td class="DataTable_Content" align="left">
			<a id="del_mfp"
				href="javascript:;" onclick="addMp('<%=po.getId()%>')" title="配套绑定"> <span
					class="img_add"></span>
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
