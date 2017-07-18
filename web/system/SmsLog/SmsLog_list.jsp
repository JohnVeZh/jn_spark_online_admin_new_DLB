<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.SmsLog.SmsLog"%>
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
<!-- 日期控件引入这个js ， 在  text属性里加入：  class="default"   onfocus="showCalendar(this)" 
	 -->
	<script src="<%=path%>/js/wpCalendar.js" type="text/javascript"></script>
<!--框架必需end-->

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
				   		listForm.action="SmsLog.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="SmsLog.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(j) {
			var idVal = isSel();
			if(idVal!=""){
			  listForm.action="SmsLog.do?act=preUpdate"+idVal;
			  listForm.submit();
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="SmsLog.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("phone").value="";
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：手机短信管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/SmsLog.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><!-- <a href="javascript:;" onclick="preAdd('')"> <span
							class="icon_add">新增</span>
					</a>
						<div class="box_tool_line"></div> <a href="javascript:;"
						onclick="preUpdate(0)"> <span class="icon_edit">修改</span>
					</a>
						<div class="box_tool_line"></div> <a href="javascript:;"
						onclick="view()"> <span class="icon_view">查看</span>
					</a>
						<div class="box_tool_line"></div> 
					<a id="del_mfp"
						href="javascript:;" onclick="doDels()"> <span
							class="icon_delete">删除</span>
					</a> -->
						 手机号：<input type="text" name="phone" id="phone" value="${phone }"/>
						 <div class="box_tool_line"></div>
						 <%-- <select name="logType">
						  <option value="" <c:if test='${logType=="" }'>selected="selected"</c:if>>全部</option>
						  <option value="1" <c:if test='${logType=="1" }'>selected="selected"</c:if>>注册</option>
						  <option value="2" <c:if test='${logType=="2" }'>selected="selected"</c:if>>登录</option>
						  <option value="3" <c:if test='${logType=="3" }'>selected="selected"</c:if>>找回密码</option>
						 </select> --%>
						 起始时间：<input onfocus="showCalendar(this)" class="cusDate" type="text" name="starttime" value="${starttime }" id="starttime"  />-<input onfocus="showCalendar(this)" class="cusDate" type="text" name="endtime" value="${endtime }" id="endtime"  />
						 &nbsp; 
						<input type="submit" value="查询" />&nbsp;&nbsp; 
						<div style="float: right;">
						  <span></span> |
						  <span>已发送 ${sum } 条</span>
						</div>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" width="3%"  align="center" class="DataTable_Field" title="序号">序号</th>							
						<th height="25" width="10%" align="center" class="DataTable_Field" title="手机号 * 类型：String 长度:11">手机号</th>								 
						<th height="25" width="10%" align="center" class="DataTable_Field" title="短信动态码 * 类型：String 长度:6">动态码</th>								 
						<th height="25" width="30%" align="center" class="DataTable_Field" title="短信内容 * 类型：String 长度:300">内容</th>								 
						<th height="25" width="10%" align="center" class="DataTable_Field" title="添加时间 * 类型：int 长度:10">时间</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	SmsLog po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (SmsLog)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getLogPhone() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getLogCaptcha() %>"><%= po.getLogCaptcha() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getLogMsg() %>"><%= po.getLogMsg() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getAddTime() %>"><%= po.getAddTime() %></td>								
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
