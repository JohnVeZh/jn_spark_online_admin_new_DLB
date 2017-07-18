<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
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
		var $$ = top.$("#frmright")[0].contentWindow.$;
		if(top.$("#_DialogFrame_${srcpage}")[0]) {
			$$ = top.$("#_DialogFrame_${srcpage}")[0].contentWindow.$;
		}
		
		function selectItem(id, name) {
			$$("#${idKey}").val(id);
			$$("#${valueKey}").val(name);
			$$("#${valueKey}").attr("title", name);
			
            top.Dialog.close();
		}
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourse.do?act=networkCourseSelector&srcpage=${srcpage}&idKey=${idKey}&valueKey=${valueKey}" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
					<tr>
						<td>
							<div style="float: left">
								标题：<input type="text" name="title" id="title" value="${title }"/>&nbsp;
							</div>
							<input type="submit" value="查询" />
	                    </td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr >
					<th width="40" height="40"  align="center" class="DataTable_Field" title="序号">序号</th>
					<th height="25" class="DataTable_Field" title="网课名称 ">网课名称</th>								 
					<th height="25" width="60" align="center" class="DataTable_Field" title="网课级别 ">网课级别类型</th>								 
					<th height="10" width="60" align="center" class="DataTable_Field" title="现价 ">现价</th>								 
					<th height="10" width="60" align="center" class="DataTable_Field" title="实际销售量 ">实际销售量 </th>								 
					<th height="25" width="60" align="center" class="DataTable_Field" title="网课形式">网课形式</th>								 
					<th height="25" width="60" align="center" class="DataTable_Field" title="网课状态  ">网课状态</th>								 
					<th height="25" width="150" align="center" class="DataTable_Field" title="开始时间 ">开始时间</th>								 
					<th height="25" width="150" align="center" class="DataTable_Field" title="结束时间  ">结束时间</th>
					<th height="25" width="40" align="center" class="DataTable_Field">操作</th>							 
				</tr>
				<c:forEach items="${lc.list}" var="item" varStatus="stat">
					<tr>
	        			<td align="center">${lc.index + stat.count}</td>
	        			<td align="left">${item.ncName}</td>
	        			<td align="left">
	        				<c:choose>
	        					<c:when test="${item.ncLevel == 'cet4'}">四级</c:when>
	        					<c:when test="${item.ncLevel == 'cet6'}">六级</c:when>
	        					<c:when test="${item.ncLevel == 'pe'}">考研</c:when>
	        					<c:when test="${item.ncLevel == 'system_course'}">系统课</c:when>
	        					<c:when test="${item.ncLevel == 'special_course'}">专项课</c:when>
	        					<c:when test="${item.ncLevel == 'public_course'}">公开课</c:when>
	        					<c:when test="${item.ncLevel == 'wei_course'}">微课</c:when>
	        				</c:choose>
	        			</td>
	        			<td align="left">${item.currentPrice}</td>
	        			<td align="left">${item.saleNumber}</td>
	        			<td align="left">
	        				<c:if test="${item.ncType==1}">直播课</c:if>
	        				<c:if test="${item.ncType==0}">录播课</c:if>
	        			</td>
	        			<td align="left">
	        				<c:choose>
	        					<c:when test="${item.ncState==0}">待发布</c:when>
	        					<c:when test="${item.ncState==1}">发布中</c:when>
	        					<c:when test="${item.ncState==2}">预约中</c:when>
	        					<c:when test="${item.ncState==3}">开售中</c:when>
	        					<c:when test="${item.ncState==4}">已停售</c:when>
	        					<c:when test="${item.ncState==5}">已下架</c:when>
	        				</c:choose>
	        			</td>
	        			<td align="left">${item.ncLiveTime}</td>
	        			<td align="left">${item.ncEndTime}</td>
	        			<td align="center">
							<a href="javascript:;" title="选择" onclick="javascript:selectItem('${item.id}', '${item.ncName}');">
								<span class="img_ok"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../../include/listpage.jsp"%>
				</div>
			</div>
			<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
