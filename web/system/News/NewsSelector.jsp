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
		<form name="listForm" scope="request" action="<%=path%>/business/News.do?act=newsSelector&srcpage=${srcpage}&idKey=${idKey}&valueKey=${valueKey}" method="post" >
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
					<th width="40" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
					<th height="25" align="center" class="DataTable_Field" title="标题">标题</th>
					<th height="25" width="60" align="center" class="DataTable_Field" title="阅读量">阅读量</th>
					<th height="25" width="60" align="center" class="DataTable_Field" title="是否置顶">是否置顶</th>
					<th height="25" width="60" align="center" class="DataTable_Field" title="是否推荐">是否推荐</th>
					<th height="25" width="150" align="center" class="DataTable_Field" title="发布时间 ">发布时间</th>
					<th height="25" width="40" align="center" class="DataTable_Field">操作</th>							 
				</tr>
				<c:forEach items="${lc.list}" var="item" varStatus="stat">
					<tr>
	        			<td align="center">${lc.index + stat.count}</td>
	        			<td align="left">${item.title}</td>
	        			<td align="left">${item.count}</td>
	        			<td align="left">
	        				<c:if test="${item.isTop==1}">是</c:if>
	        				<c:if test="${item.isTop==0}">否</c:if>
	        			</td>
	        			<td align="left">
	        				<c:if test="${item.isRecommend==1}">是</c:if>
	        				<c:if test="${item.isRecommend==0}">否</c:if>
	        			</td>
	        			<td align="left">${item.ptime}</td>
	        			<td align="center">
							<a href="javascript:;" title="选择" onclick="javascript:selectItem('${item.id}', '${item.title}');">
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
