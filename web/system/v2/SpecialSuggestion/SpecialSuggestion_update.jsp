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
					<span>当前位置：修改分析建议</span>
				</div>
			</div>
		</div>
	</div>
	<form name="listForm" action="<%=path%>/business/training/suggestion.do?act=update" method="post" id="listForm" >
		<input type="hidden" name="id" id="id" value="${item.id}" />
		<div class="box1 center" whiteBg="true" id="form1">
			<fieldset>
				<legend>基本信息</legend>
				<table class="tableStyle" transMode="true" footer="normal">
					<tr>
						<td width="15%" align="right">
							学段：
						</td>
						<td width="35%" align="left">
							<select name="section" id="section" >
								<option value="01" <c:if test='${item.section == "01"}'>selected="selected"</c:if>>四级</option>
								<option value="02" <c:if test='${item.section == "02"}'>selected="selected"</c:if>>六级</option>
							</select>
						</td>
						<td width="15%" align="right">
							专项类型：
						</td>
						<td width="35%" align="left">
							<select name="trainingType" id="trainingType" >
								<option value="0" <c:if test='${item.trainingType == "0"}'>selected="selected"</c:if>>背单词</option>
								<option value="1" <c:if test='${item.trainingType == "1"}'>selected="selected"</c:if>>听力</option>
								<option value="2" <c:if test='${item.trainingType == "2"}'>selected="selected"</c:if>>阅读</option>
								<option value="3" <c:if test='${item.trainingType == "3"}'>selected="selected"</c:if>>翻译</option>
								<option value="4" <c:if test='${item.trainingType == "4"}'>selected="selected"</c:if>>写作</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							开始分数：
						</td>
						<td width="35%" align="left">
							<input type="text" id="start" name="start" value='<fmt:formatNumber value="${item.start}" pattern="0.0" />' />
						</td>
						<td width="15%" align="right">
							结束分数：
						</td>
						<td width="35%" align="left">
							<input type="text" id="end" name="end" value='<fmt:formatNumber value="${item.end}" pattern="0.0" />' />
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							分析：
						</td>
						<td width="85%" align="left" colspan="3">
							<textarea id="analysis" name="analysis" style="width:80%; height:120px;">${item.analysis }</textarea>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							建议：
						</td>
						<td width="85%" align="left" colspan="3">
							<textarea id="suggestion" name="suggestion" style="width:80%; height:120px;">${item.suggestion }</textarea>
						</td>
					</tr>
				</table>
			</fieldset>

			<!-- ---- -->
			<table class="tableStyle" style="width: 800px; margin: 0px auto; border: none" formMode="true">
				<tr>
					<td colspan="4" style="border: none;">
						<input type="button" value=" 提 交 " onclick="sub()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value=" 关闭 " onclick="javascript:top.Dialog.close();" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<div class="diverror" align="left">
				友情提示：单词（0~100）、听力（0~248.5）、阅读（0~248.5）、翻译（0~106.5）、写作（0~106.5）
			</div>
			<br />
			<br />
		</div>
	</form>
</div>
</body>
<script language="javascript" type="text/javascript">
	function sub(){
		var start = $("#start").val();
		if(!/\d(\.\d+)?/.test(start)/*  || parseFloat(end)<0 || parseFloat(start)>1 */) {
			//top.Dialog.alert("开始分数，请输入0-1之间的数字！");
			top.Dialog.alert("开始分数，请输入有效数字！");
			return;
		}
		
		var end = $("#end").val();
		if(!/\d(\.\d+)?/.test(end)/*  || parseFloat(end)<0 || parseFloat(end)>1 */) {
			//top.Dialog.alert("结束分数，请输入0-1之间的数字！");
			top.Dialog.alert("结束分数，请输入有效数字！");
			return;
		}
		
		var analysis = $("#analysis").val();
		if(!analysis) {
			top.Dialog.alert("请输入分析内容！");
			return;
		}
		
		var suggestion = $("#suggestion").val();
		if(!suggestion) {
			top.Dialog.alert("请输入建议内容！");
			return;
		}
	
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
