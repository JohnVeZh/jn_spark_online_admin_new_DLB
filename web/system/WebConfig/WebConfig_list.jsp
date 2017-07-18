<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.WebConfig.WebConfig"%>
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
			if(${msg==200}){
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
				   		listForm.action="WebConfig.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="WebConfig.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
		//	var idVal = isSel();
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/WebConfig.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"编辑"}); 
			}
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id!=""){
		  	   listForm.action="WebConfig.do?act=view&id="+id;
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
					<span>当前位置：SEO管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/WebConfig.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><!-- <a href="javascript:;" onclick="preAdd('')"title="新增"> <span
							class="img_add"></span>
					</a>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td> -->
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						        <th>序号</th>
								<th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:100">名称</th>								 
								<th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:100">内容</th>								 
								<th height="25"  align="center" class="DataTable_Field" title="类型  01:首页  02：一级页面 03：二级 ...... * 类型：String 长度:100">类型</th>								 
								<!-- <th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:100">创建时间</th> -->
								<th>操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	WebConfig po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (WebConfig)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left"><% if(po.getConfigName()!=null)out.print(po.getConfigName()); %></td>
        <td class="DataTable_Content" align="left"><%if(po.getConfigContent()!=null)out.print(po.getConfigContent()); %></td>								
		<td class="DataTable_Content" align="left">
		<% if("01".equals(po.getType())) out.print("title");else if("02".equals(po.getType())) out.print("keywords");else out.print("description");%>
		</td>								
		<%-- <td class="DataTable_Content" align="left"><% if(po.getOpetime()!=null)out.print(po.getOpetime()); %></td>	 --%>							
		<td>
		<a href="javascript:;" title="修改"
			onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
		</a>
		 <%--  <a href="javascript:;" title="查看"
			onclick="view('<%=po.getId()%>')"> <span class="img_view"></span>
		</a> --%>
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
