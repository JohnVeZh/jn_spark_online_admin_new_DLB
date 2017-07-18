<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@ page buffer="16kb" %>  
<%@page import="com.business.NetworkCourseCode.NetworkCourseCode"%>
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
<!--缩略图样式-->
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
 

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		
		$(function(){
			if(${msg =="200" }){
				top.Dialog.close();
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
	
		//删除
		function disable(id,isEnable) {
			if(id!=""){
				if(isEnable=='1'){
					top.Dialog.confirm("您确定要禁用此兑换码吗?",
					   	function() {
						listForm.action="NetworkCourseCode.do?act=disable&id="+id;
							listForm.submit();
						}
					)
				}else if(isEnable=='0'){
					top.Dialog.confirm("您确定要启用此兑换码吗?",
					   	function() {
						listForm.action="NetworkCourseCode.do?act=disable&id="+id;
							listForm.submit();
						}
					)
				}
			}
		}
		//添加
		function preAdd(){       
		    top.Dialog.open({URL:"<%=path%>/business/NetworkCourseCode.do?act=toAdd",ID:"a1",Width:1080,Height:768,Title:"添加兑换码"});
		}
		//清空查询数据
		function qing(){
			document.getElementById("ncNamelike").value="";
			document.getElementById("type").value="0";
			document.getElementById("codeId").value="";
		}
		
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：兑换码管理</span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourseCode.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left">
						<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
								class="img_add"></span>
						</a>
					</div>
					<div style="float: left">
						兑换码：<input type="text" name="codeId" id="codeId" value="${codeId }"/>&nbsp;
					</div>
					<div style="float: left">
						网课名称：<input type="text" name="ncNamelike" id="ncNamelike" value="${ncNamelike }"/>&nbsp;
					</div>
					<div style="float: left">
					<div style="float: left;margin-top:3px">&nbsp;&nbsp;是否使用&nbsp;</div>
						 <select name="type" id="type">
					 		<option value="all" <c:if test="${type=='all'}">selected="selected"</c:if> >全部</option>
					 		<option value="0" <c:if test="${type=='0'}">selected="selected"</c:if> >未使用</option>
					 		<option value="1" <c:if test="${type=='1'}">selected="selected"</c:if>>已使用</option>
						 </select>
					</div>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th height="25" width="5%" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" width="10%" align="center" class="DataTable_Field" title="兑换码 ">兑换码</th>
						<th height="25"  align="center" class="DataTable_Field" title="网课名称">网课名称</th>								 
						<th height="25" width="15%" class="DataTable_Field" title="用户ID">用户ID</th>								 
						<th height="10"  align="center" class="DataTable_Field" title="网课类型">网课类型</th>								 
						<th height="10"  align="center" class="DataTable_Field" title="兑换码状态 ">兑换码状态</th>								 
						<th height="10"  align="center" class="DataTable_Field" title="兑换码状态 ">是否禁用</th>								 
						<th height="25" width="10%" align="center" class="DataTable_Field" title="操作">操作</th>						 
					</tr>
					 
				 		 
			<%
	 int sn=lc.getIndex();
	List list=lc.getList();
	NetworkCourseCode po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NetworkCourseCode)list.get(i);
%>
	<tr>
		<td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getId() %>"><%= po.getId() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getNcName() %>"><%= po.getNcName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getUserId() %>">
			<% if(CommUtil.isObject(po.getUserId()))out.print(po.getUserId());else out.print(""); %>
		</td>								
		<td class="DataTable_Content" align="center" title="">
			<% if(po.getNcLevel().equals("cet4")){ out.print("四级"); }else if(po.getNcLevel().equals("cet6")){ out.print("六级"); }else if(po.getNcLevel().equals("pe")) {out.print("考研");} %>-
			<% if(po.getNcLevelType().equals("system_course")){ out.print("系统课"); }else if(po.getNcLevelType().equals("special_course")){ out.print("专项课"); }else if(po.getNcLevelType().equals("public_course")) {out.print("公开课");}else if(po.getNcLevelType().equals("wei_course")) {out.print("微课");} %>-
			<% if(po.getNcType()==0){out.print("录播课");}else if(po.getNcType()==1){out.print("直播课");} %>
		</td>								
		<td class="DataTable_Content" align="center">
			<% if(CommUtil.isObject(po.getUserId()))out.print("已使用");else out.print("未使用"); %>
		</td>								
		<td class="DataTable_Content" align="center">
			<% if(po.getIsEnable()==0){out.print("禁用");}else if(po.getIsEnable()==1){out.print("可用");} %>
		</td>								
		<td  align="center">
			<% if(!CommUtil.isObject(po.getUserId())){ %>
				<% if(po.getIsEnable()==1){ %>
				 	<a href="javascript:;" title="禁用" onclick="disable('<%=po.getId()%>','<%=po.getIsEnable()%>')"> <span class="img_edit"></span> </a>
				<%} %>
				<% if(po.getIsEnable()==0){ %>
				 	<a href="javascript:;" title="启用" onclick="disable('<%=po.getId()%>','<%=po.getIsEnable()%>')"> <span class="img_edit"></span> </a>
				<%} %>
			<%} %>
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
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>
