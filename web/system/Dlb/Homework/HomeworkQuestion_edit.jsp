<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.MatchedPapers.MatchedPapersActionForm"%>
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

	<!-- 表单验证start -->
	<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
	<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
	<!-- 表单验证end -->
	<!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
	<!-- 异步上传图片 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

	<!--基本选项卡start-->
	<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
	<!--基本选项卡end-->
	<script type="text/javascript" src="<%=path%>/libs/js/form/loadmask.js"></script>

</head>
<body class="ali02">
<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：当前位置：网课列表>>作业列表>>
                        <c:if test="${'1'.equals(questionType)}">听力</c:if>
                        <c:if test="${'2'.equals(questionType)}">阅读</c:if>
                        <c:if test="${'3'.equals(questionType)}">翻译</c:if>
                        <c:if test="${'4'.equals(questionType)}">写作</c:if>题目列表>>修改
                        <c:if test="${'1'.equals(questionType)}">听力</c:if>
                        <c:if test="${'2'.equals(questionType)}">阅读</c:if>
                        <c:if test="${'3'.equals(questionType)}">翻译</c:if>
                        <c:if test="${'4'.equals(questionType)}">写作</c:if>
                        题目<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span></span>
				</div>
			</div>
		</div>
	</div>
	<div style="" align="center">
		<form action="<%=path%>/business/Dlb/HomeworkQuestion.do?act=update" method="post" onsubmit="return sub(this)" >
			<input type="hidden" name="homeworkQuestionId" id="homeworkQuestionId" value="${homeworkQuestion.id}"/>
			<input type="hidden" name="questionType" id="questionType" value="${questionType}"/>
			<input type="hidden" name="execriseId" id="execriseId" value="${homeworkQuestion.homeworkId}"/>
			<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr>
					<td height="25" width="3" align="center" class="DataTable_Field">题目排序</td>
					<td height="25" width="3" align="center" class="DataTable_Field">
						<input name="sort" id="sort" value="${homeworkQuestion.sort}"/>
					</td>
				</tr>
			</table>
	</div>
	<table class="tableStyle"
		   style="width: 400px; margin: 0px auto; border: none"
		   formMode="true">
		<tr>
			<td colspan="4" style="border: none;">
				<input type="submit" value=" 提 交 " onclick="flush()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value=" 关 闭" onclick="back()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>

	</table>
	</form>
</div>
</body>
<script language="javascript" type="text/javascript">

    function back(){
        history.back();
    }

    function checkForm() {
        return true;
    }

    function flush(){
        top.frmright.window.location.reload(true);
    }

</script>


</html>
