<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.MatchedPapers.MatchedPapers"%>
<%@page import="com.easecom.common.util.Tool"%>
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
		//修改题号
		function updateNum(id,num){
			if(id!=''){
			  var numtext = $("#num"+num).val();
			  $.post("MatchedPapersTopicHearingSubject.do?act=AjasRealUpdate", {id:id,num:numtext},
			   function(data){
			    if(data.succeed='000'){
				    $("#span"+num).attr("class","img_ok");
			    }else{
			    	$("#span"+num).attr("class","img_delete");
			    }
			   }, "json");
		}

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
		<form name="listForm" scope="request" action="<%=path%>/business/MatchedPapers.do?act=list" method="post">
			<input type="text" name="mpId" id="mpId" value="${mpId }" style="display:none"/>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
						<th height="25"  align="center" class="DataTable_Field" >Passage名称</th>
						<th height="25"  align="center" class="DataTable_Field" >题号</th>
						<th width="50%" height="25"  align="center" class="DataTable_Field" >提示选项内容</th>
					</tr>
					 
		<c:forEach items="${lm }" var="l" varStatus="s">
			<tr  >
		    	<td align="center"><input type="checkbox" name="id" value="${l.mpthsId }" onclick="event.cancelBubble=true;"></td>
		        <td align="center">${s.index+1 }</td>
				<td class="td" align="left" title="${l.pasName }">${l.pasName }</td>								
				<td class="td" align="left" title="${l.sNumber }">
					<input type="text" id="num${s.index }" value="${l.sNumber }" onchange="updateNum('${l.mpthsId }',${s.index })"/>
					<span id="span${s.index }" ></span>	
				</td>
				<td class="td"  align="left" title="${l.content }">${l.content }</td>
		 	</tr>
		</c:forEach> 		 
			</table>
		</form>
		<br/>
	</div>
	</body>
</html>
