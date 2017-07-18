<%@page import="com.business.News.News"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.News.News"%>
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
	<script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->

 
	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		var $$ = top.$("#_DialogFrame_${srcpage}")[0].contentWindow.$;
		
		function selectItem(id, name) {
			$$("#contentId").val(id);
			$$("#contentName").val(name);
			$$("#contentName").attr("title", name);
			
            top.Dialog.close();
		}
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/training/explain.do?act=newsSelector&srcpage=${srcpage}" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left">
						资讯名称：<input type="text" name="title" id="title" value="${title }"/>&nbsp;
					</div>
						<input type="submit" value="查询" />
                    </td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						<th width="50" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
						<th height="25" align="center" class="DataTable_Field" title="资讯名称 ">资讯名称</th>
						<th height="25" width="100" align="center" class="DataTable_Field" title="">操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	News po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (News)list.get(i);
%>
	<tr  >
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getTitle() %>"><%= po.getTitle() %></td>								
		<td class="DataTable_Content" align="left">
			 <a href="javascript:;" title="选择"
				onclick="selectItem('<%=po.getId()%>', '<%= po.getTitle() %>')"> <span class="img_ok"></span>
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
					<%@include file="../../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
