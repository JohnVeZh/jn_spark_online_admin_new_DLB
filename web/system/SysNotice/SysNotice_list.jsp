<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.system.model.SysNotice"%>
<%@page import="com.easecom.common.util.Tool"%>
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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
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
				   		listForm.action="SysNotice.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="SysNotice.do?act=realdelete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="SysNotice.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			  listForm.action="SysNotice.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view(id) {
			if(idVal!=""){
		  	   listForm.action="SysNotice.do?act=view&id="+id;
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
					<span>当前位置：系统公告<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/SysNotice.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><a href="javascript:;" onclick="preAdd('')"> <span
							class="icon_add">新增</span>
					</a>
						<div class="box_tool_line"></div> 
					<a id="del_mfp"
						href="javascript:;" onclick="doDels()"> <span
							class="icon_delete">删除</span>
					</a>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
								<th height="25"  align="center" class="DataTable_Field" title="序号">序号</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="公告标题 * 类型：String 长度:200">标题</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="发送公告人员id * 类型：String 长度:32">创建人</th>	
								<th height="25"  align="center" class="DataTable_Field" title="类型* 类型：String 长度:50">类型</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="创建时间 * 类型：String 长度:50">创建时间</th>	
								<th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	SysNotice po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (SysNotice)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getTitle() %></td>								
		<td class="DataTable_Content" align="left"><%= Tool.getValue("select name from sys_user where id='"+po.getSysUserId()+"'")  %></td>								
		<td class="DataTable_Content" align="left"><%= po.getType().equals("0")?"全部":"区域" %></td>
		<td class="DataTable_Content" align="left"><%= po.getCreatetime() %></td>
		<td class="DataTable_Content" align="left">
 			<div class="box_tool_line"></div> <a href="javascript:;"
						onclick="preUpdate('<%=po.getId()%>')"> <span class="icon_edit">修改</span>
				</a>
					<div class="box_tool_line"></div> 
				<a id="del_mfp"
					href="javascript:;" onclick="doDelsById('<%=po.getId()%>')"> <span
						class="icon_delete">删除</span>
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
