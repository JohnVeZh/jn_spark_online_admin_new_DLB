<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="com.business.MatchedPapers.MatchedPapersActionForm"%>
<%@page import="com.easecom.common.util.Tool"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/"
	scrollerY="true" />
<link rel="stylesheet" type="text/css" id="theme" />
<!--3.3框架必需end-->

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js"
	type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js"
	type="text/javascript"></script>
<!-- 表单验证end -->
<!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript"
	src="<%=path%>/ueditor/ueditor.all.min.js"></script>
<!-- 异步上传图片 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

<!--基本选项卡start-->
<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->

</head>
<body class="ali02">
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" align="left">
						<span>当前位置：阅读理解<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" action="business/MatchedPapers.do?act=updateRead"
			method="post" id="listForm">
			<table  class="tableStyle" transMode="true" footer="normal">
				<tr>
					<td width="20%">
						选项数量：
					</td>
					<td >
						<input type="text" name="num" value="" style="width: 100%"/>
					</td>
				</tr>
				<tr style="text-align: center;">
					<td>
						题号
					</td>
					<td>
						正确答案
					</td>
				</tr>
    			<c:forEach var="stu" items="${mptrsl}" varStatus="status" >
					<tr style="text-align: left;">
					<td>
						${stu.sNumber }<input type="hidden" name="tId${status.index }" value="${stu.id }"/>
					</td>
					<td>
						<input type="text" name="vl${status.index }"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			<input type="submit" value="提交" />
		</form>
	</div>
</body>
</html>
