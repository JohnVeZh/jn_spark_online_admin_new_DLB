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
	<script type="text/javascript" src="<%=path%>/libs/js/table/detailTable.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	<!--3.3框架必需end-->

	<!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

	<!-- 表单验证start -->
	<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
	<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
	<!-- 表单验证end -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>

	<!-- 异步上传 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

	<!--基本选项卡start-->
	<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
	<!--基本选项卡end-->
	<!--缩略图样式-->
	<link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
</head>
<body class="ali02">
<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：网课列表>>作业列表>>修改作业<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div>
	<form name="listForm" action="<%=path%>/business/Dlb/Homework.do?act=update" method="post" id="listForm" target="frmright" onsubmit="javascript:return checkForm();">
		<input type="hidden" name="videoId" value="${homework.courseCatalogId}"/>
		<input type="hidden" name="ncId" value="${homework.courseId}"/>
		<input type="hidden" name="section" value="${homework.section}"/>
		<input type="hidden" name="id" value="${homework.id}"/>
		<div class="box1 center" whiteBg="true" id="form1">
			<fieldset>
				<legend>基本信息</legend>
				<table class="tableStyle" transMode="true" footer="normal">
					<tr>
						<td width="15%" align="right">
							学段：
						</td>
						<td width="90%" align="left" >
							<input type="radio" disabled="disabled"
									<c:if test="${'01'.equals(homework.section)}">
										checked="checked"
									</c:if>
							/>四级
							<input type="radio" disabled="disabled"
									<c:if test="${'02'.equals(homework.section)}">
										checked="checked"
									</c:if>
							/>六级
						</td>
					<tr>
						<td width="15%" align="right">
							作业题型：
						</td>
						<td width="90%" align="left" >
							<input type="radio" name="questionType" value="1" <c:if test="${homework.questionType == 1}"> checked </c:if> />听力
							<input type="radio" name="questionType" value="2" <c:if test="${homework.questionType == 2}"> checked </c:if> />阅读
							<input type="radio" name="questionType" value="3" <c:if test="${homework.questionType == 3}"> checked </c:if> />翻译
							<input type="radio" name="questionType" value="4" <c:if test="${homework.questionType == 4}"> checked </c:if> />写作
						</td>
					</tr>
					<tr>
						<td width="10%" align="right">
							作业名称：
						</td>
						<td width="90%" align="left" >
							<input type="text" name="title" id="title" class="validate[required] iptClass" style="width: 45%" value="${homework.title}" />
							<span class="star"></span>
						</td>
					</tr>
					<%--<tr>
						<td width="15%" align="right">
							选择题目：
						</td>
						<td width="90%" align="left" >
							<a href="javascript:;" onclick="preSub('${homework.id}')" title="新增"> <span
									class="img_add"></span>
							</a>
							&lt;%&ndash;<input type="text" name="questionName" id="questionName" style="display: none" value=""/>
							<span name="questionName" id="questionName"></span>&ndash;%&gt;
							<input type="text" name="value" id="value" style="display: none" value="${questionId}"/>
							<input type="hidden" name="questionName" id="questionName" value="${questionName}"/>
							<span name="vlSpan" id="vlSpan" >${questionName}</span>
						</td>
					</tr>--%>
					<tr>
						<td width="10%" align="right">
							排序：
						</td>
						<td width="90%" align="left">
							<input type="text" name="sort" id="sort" class="validate[required] iptClass" placeholder="只能是数字" value="${homework.sort}"
								   onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"/>
						</td>
					</tr>
					<tr>
						<td width="10%" align="right">
							作业展示时间：
						</td>
						<td width="90%" align="left">
							<input  class="date" type="text" name="displayDate" value="${homework.displayDate}"
									id="displayDate" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
						</td>
					</tr>
					<tr>
						<td width="10%" align="right">
							已选网课：
						</td>
						<td width="90%" align="left" colspan="3">
							<span name="" id="">${ncName }</span>
						</td>
					</tr>
				</table>
				<br/>
			</fieldset>
			<!-- ---- -->
			<table class="tableStyle"
				   style="width: 800px; margin: 0px auto; border: none"
				   formMode="true">
				<tr>
					<td colspan="4" style="border: none;">
						<input type="submit" value=" 提 交 " />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value=" 关闭" onclick="back()" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
				</tr>
			</table>
			<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
			<br />
			<br />
		</div>
	</form>
</div>
</body>
<script language="javascript" type="text/javascript">

    function flush(){
        top.frmright.window.location.reload();
    }
    function back(){
        top.Dialog.close();
    }

    //解绑网课
    function deleteById(id){
        listForm.action="<%=path%>/business/Dlb/Homework.do?act=delete&id="+id;
        listForm.submit();
    }

    //选择听力作业题目
    function preSub(execriseId){
        top.Dialog.open({URL:"<%=path%>/business/Dlb/HomeworkQuestion.do?act=listenList&execriseId="+execriseId,Title:"绑定窗口",Width:1024,Height:768});
    }

    function checkForm() {
        return true;
    }

    function back(){
        history.back();
    }
</script>
</html>
