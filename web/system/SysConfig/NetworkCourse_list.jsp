<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.NetworkCourse.NetworkCourse"%>
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
	
	function addnv(id,name){
	    top.document.getElementById("frmright").contentWindow.document.getElementById("value").value 	= id;
	    top.document.getElementById("frmright").contentWindow.document.getElementById("vlSpan").innerHTML = name;
		top.Dialog.close();
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
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourse.do?act=scList" method="post" >
			<input type="text" id="productId" name="productId" value="${productId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
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
						<th height="25"  align="center" class="DataTable_Field" title="销售量 ">销售量 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="课程价格 ">课程价格</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="课程类型 ">课程类型</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">课程数</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="">操作</th>						 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	NetworkCourse po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NetworkCourse)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getNcName() %>"><%= po.getNcName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getSaleNumber() %>"><%= po.getSaleNumber() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCurrentPrice() %>"><%= po.getCurrentPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCurrentPrice() %>">
			<% if(po.getNcLevel().equals("cet4")){ out.print("四级"); }else if(po.getNcLevel().equals("cet6")){ out.print("六级"); }else if(po.getNcLevel().equals("pe")) {out.print("考研");} %>-
			<% if(po.getNcLevelType().equals("system_course")){ out.print("系统课"); }else if(po.getNcLevelType().equals("special_course")){ out.print("专项课"); }else if(po.getNcLevelType().equals("public_course")) {out.print("公开课");}else if(po.getNcLevelType().equals("wei_course")) {out.print("微课");} %>
			-<% if(po.getNcType()==0){out.print("录播课");}else if(po.getNcType()==1){out.print("直播课");} %>
		</td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCatalogNumber() %>"><%= po.getCatalogNumber() %></td>								
		<td class="DataTable_Content" align="left">
		 	
			<a href="javascript:;" title="配套绑定"
						onclick="addnv('<%=po.getId()%>','<%=po.getNcName()%>')" > <span class="img_add"></span>
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
