<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.MatchedPapersUser.MatchedPapersUser"%>
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
				   		listForm.action="MatchedPapersUser.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="MatchedPapersUser.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(j) {
			var idVal = isSel();
			if(idVal!=""){
			  listForm.action="MatchedPapersUser.do?act=preUpdate"+idVal;
			  listForm.submit();
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="MatchedPapersUser.do?act=view"+idVal;
		  		listForm.submit();
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
					<span>当前位置：matched_papers_user_list<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/MatchedPapersUser.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><a href="javascript:;" onclick="preAdd('')"> <span
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
					</a>
						<div class="box_tool_line"></div> 
							  
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						
								<th height="25"  align="center" class="DataTable_Field" title="题号ID * 类型：String 长度:32">mptsId</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="状态（2：全部完成 1：中途离开 0 做题中） * 类型：int 长度:10">state</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="套卷id * 类型：String 长度:32">mpId</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="id * 类型：String 长度:32">id</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="用户id * 类型：String 长度:32">userId</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="创建时间 * 类型：String 长度:50">createtime</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="类型 0 听力 1阅读理解 * 类型：int 长度:10">type</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="完成做题用时 * 类型：String 长度:50">completeTime</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	MatchedPapersUser po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (MatchedPapersUser)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getMptsId() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getState() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getMpId() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getId() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getUserId() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getType() %></td>								
		<td class="DataTable_Content" align="left"><%= po.getCompleteTime() %></td>								
 	</tr>
<%
	}
%>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
