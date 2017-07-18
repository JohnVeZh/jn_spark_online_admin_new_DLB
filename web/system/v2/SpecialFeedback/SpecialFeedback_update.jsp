<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<!--tap页start-->
	<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	<!--3.3框架必需end-->
	<!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<!-- 日期选择框end -->
	<!-- 表单验证start -->
	<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
	<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
	<!-- 表单验证end -->
	<!-- 异步上传 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>


</head>
<body class="ali02">
<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：题目纠错详情</span>
				</div>
			</div>
		</div>
	</div>
	<form name="listForm" action="<%=path%>/business/training/feedback.do?act=update" method="post" id="listForm" >
		<input type="hidden" name="id" id="id" value="${item.id}" />
		<input type="hidden" name="isSolved" id="isSolved" value="1" />
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
								<c:when test='${item.trainingType == "1"}'>听力</c:when>
								<c:when test='${item.trainingType == "2"}'>阅读</c:when>
								<c:when test='${item.trainingType == "3"}'>翻译</c:when>
								<c:when test='${item.trainingType == "4"}'>写作</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							分类：
						</td>
						<td width="35%" align="left">
							${item.trainingParentCatalogName }
						</td>
						<td width="15%" align="right">
							题目：
						</td>
						<td width="35%" align="left">
							${item.trainingCatalogName }
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							问题内容：
						</td>
						<td width="85%" align="left" colspan="3">
							<textarea style="width:80%; height:120px;" readonly="readonly">${item.content }</textarea>
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
			<table class="tableStyle" style="width: 800px; margin: 0px auto; border: none" formMode="true">
				<tr>
					<td colspan="4" style="border: none;">
						<input type="button" value=" 解 决 " onclick="sub()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value=" 关闭 " onclick="javascript:top.Dialog.close();" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<div class="diverror" align="left">友情提示：请确认问题已处理，然后点击“解决”按钮</div>
			<br />
			<br />
		</div>
	</form>
</div>
</body>
<script language="javascript" type="text/javascript">
	function sub(){
		$("#scrollContent").mask("表单正在提交...");
		$('#listForm').ajaxSubmit(function(data){
			if(data.result){
				top.frmright.window.location.reload();
				top.Dialog.close();
			}
		});
	}
</script>
</html>
