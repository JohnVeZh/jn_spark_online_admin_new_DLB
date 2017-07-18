<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	<!--3.3框架必需end-->

</head>
<body class="ali02">
<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：分析建议详情</span>
				</div>
			</div>
		</div>
	</div>
	<html:form action="business/training/explain.do?act=update" method="post"  onsubmit="return sub(this)">
		<div class="box1 center" whiteBg="true" id="form1">
			<fieldset>
				<legend>基本信息</legend>
				<table class="tableStyle" transMode="true" footer="normal">
					<tr>
						<td width="15%" align="right">
							学段：
						</td>
						<td width="35%" align="left">
							<c:choose>
								<c:when test='${item.section == "01"}'>四级</c:when>
								<c:when test='${item.section == "02"}'>六级</c:when>
							</c:choose>
						</td>
						<td width="15%" align="right">
							专项类型：
						</td>
						<td width="35%" align="left">
							<c:choose>
								<c:when test='${item.trainingType == "0"}'>背单词</c:when>
								<c:when test='${item.trainingType == "1"}'>听力</c:when>
								<c:when test='${item.trainingType == "2"}'>阅读</c:when>
								<c:when test='${item.trainingType == "3"}'>翻译</c:when>
								<c:when test='${item.trainingType == "4"}'>写作</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							开始分数：
						</td>
						<td width="35%" align="left">
							<fmt:formatNumber value="${item.start}" pattern="0.0" />
						</td>
						<td width="15%" align="right">
							结束分数：
						</td>
						<td width="35%" align="left">
							<fmt:formatNumber value="${item.end}" pattern="0.0" />
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							分析：
						</td>
						<td width="85%" align="left" colspan="3">
							<textarea style="width:80%; height:120px;" readonly="readonly">${item.analysis }</textarea>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							建议：
						</td>
						<td width="85%" align="left" colspan="3">
							<textarea style="width:80%; height:120px;" readonly="readonly">${item.suggestion }</textarea>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							创建时间：
						</td>
						<td width="35%" align="left">
							<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td width="15%" align="right">
							
						</td>
						<td width="35%" align="left">
							
						</td>
					</tr>
				</table>
			</fieldset>

			<!-- ---- -->
			<table class="tableStyle"
				   style="width: 800px; margin: 0px auto; border: none"
				   formMode="true">
				<tr>
					<td colspan="4" style="border: none;">
						<input type="reset" value=" 关闭 " onclick="back()" />
					</td>
				</tr>
			</table>
			<div class="diverror" align="left">
				友情提示：单词（0~100）、听力（0~248.5）、阅读（0~248.5）、翻译（0~106.5）、写作（0~106.5）
			</div>
			<br />
			<br />
		</div>
	</html:form>
</div>
</body>
</html>
<script language="javascript" type="text/javascript">


	function back(){
		top.Dialog.close();
	}


</script>