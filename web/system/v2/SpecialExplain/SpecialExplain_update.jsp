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
					<span>当前位置：修改专项讲解</span>
				</div>
			</div>
		</div>
	</div>
	<form name="listForm" action="<%=path%>/business/training/explain.do?act=update" method="post" id="listForm" >
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
								<option value="1" <c:if test='${item.trainingType == "1"}'>selected="selected"</c:if>>听力</option>
								<option value="2" <c:if test='${item.trainingType == "2"}'>selected="selected"</c:if>>阅读</option>
								<option value="3" <c:if test='${item.trainingType == "3"}'>selected="selected"</c:if>>翻译</option>
								<option value="4" <c:if test='${item.trainingType == "4"}'>selected="selected"</c:if>>写作</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
								标题：
						</td>
						<td width="85%" align="left" colspan="3">
							<input type="text" name="title" id="title" value="${item.title}" style="width:80%"/>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
					   		<input type="hidden" name="contentType" id="contentType" value="${item.contentType}" />
						  	资讯名称：
						</td>
						<td width="85%" align="left" colspan="3">
							<input type="hidden" name="contentId" id="contentId" value="${item.contentId}" />
							<input type="text" name="contentName" id="contentName" value="${item.contentName}" readonly="readonly" style="width:80%; cursor:pointer;" />
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							访问次数：
						</td>
						<td width="35%" align="left">
							<input type="text" name="visitNum" id="visitNum" value="${item.visitNum}" style="width:80%"/>
						</td>
						<td width="15%" align="right">
							创建时间：
						</td>
						<td width="35%" align="left">
							<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
			<div class="diverror" align="left">友情提示：</div>
			<br />
			<br />
		</div>
	</form>
</div>
</body>
<script language="javascript" type="text/javascript">
	$(function() {
		var contentType = $("#contentType").val();
		$("#contentName").bind("click", function() {
			if(contentType == "11") {
				top.Dialog.open({URL:"<%=path%>/business/training/explain.do?act=newsSelector&srcpage=special_explain_update",ID:'col1',Title:"选择资讯",Width:1024,Height:768});
			}
		});
	});

	function sub(){
		var title = $("#title").val();
		if(!title) {
			top.Dialog.alert("请输入标题！");
			return;
		}

		var contentId = $("#contentId").val();
		if(!contentId) {
			top.Dialog.alert("请选择资讯！");
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
