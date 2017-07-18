<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.Dlb.PeriodPaperUserAnswer.PeriodPaperUserAnswer"%>

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
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
<!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
<!-- 异步上传图片 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<!-- 七牛文件上传 -->
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/jquery.qiniu.js"></script>		
</head>
 <%
 PeriodPaperUserAnswer po = (PeriodPaperUserAnswer) request.getAttribute("PeriodPaperUserAnswer");
 %>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：作业批改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm"  method="post" action="<%=path%>/business/PeriodPaperUserAnswer.do?act=ReplyUpdate" id="listForm" target="frmright">
			<input type="hidden" name="id" id="id"  value="${PeriodPaperUserAnswer.id }"/>
			<input type="hidden" name="ides" id="ides" />
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>学生作答：<% if(po.getSection().equals("01")){ out.print("四级"); }else if(po.getSection().equals("02")){ out.print("六级"); } %><% if(po.getQuestionType()==3){ out.print("翻译"); }else if(po.getQuestionType()==4){ out.print("写作"); } %></legend> 
					<div style="overflow:auto;">
						<table class="tableStyle" >
							<tr>
								<td width="35%" align="left"  colspan="4">
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									  <c:if test="${PeriodPaperUserAnswer.userAnswer != null && PeriodPaperUserAnswer.userAnswer != '' }">
										 <% if(po.getUserAnswer().startsWith("http")){
								  		 %>
											<a href="javascript:thumbImgsDiv('${PeriodPaperUserAnswer.userAnswer }',0)" >
												 <img src="${PeriodPaperUserAnswer.userAnswer }" width="1024px" height="600px" style="border:1px solid #ccc;padding:5px;"/>
											</a>
										 <% }else{
									     %>
									     	<a href="javascript:thumbImgsDiv('<%=path%>/${PeriodPaperUserAnswer.userAnswer }',0)" >
												<img src="<%=path%>/${PeriodPaperUserAnswer.userAnswer }" width="1024px" height="600px" style="border:1px solid #ccc;padding:5px;"/>
										   	</a>
									      <%} %>
									  </c:if>
									</div>
									<br/>
									<input type="hidden" name="answerUrl" value="${PeriodPaperUserAnswer.userAnswer }"/>
									<input type="button" onclick="upimgs('')" value="下载预览"/>
									
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" colspan="4">
									老师批改：
								</td>
							</tr>
							<tr>
								<td width="100%" align="left" colspan="4">
								<c:forEach items="${EvaluateRuleList}" var="er" varStatus="s">
									<c:if test="${er.id=='1' || er.id=='4' || er.id=='7' ||er.id=='10' || er.id=='13' || er.id=='16' || er.id=='19' || er.id=='22' || er.id=='25'}">${er.name }：</c:if>
									<c:forEach items="${userAnswerEvaluate}" var="ue" varStatus="s">
										<c:if test="${ue.ruleDetailId==er.id}">
											<input type="button" id="button${er.id}"  style="text-decoration: none; width: 114px;height: 38;background:rgba(62, 176, 87, 0.93);"  value="${er.levelName }"/>
										</c:if>								
									</c:forEach>
									
									<c:if test="${er.id=='3' || er.id=='6' || er.id=='9' || er.id=='12' || er.id=='15' || er.id=='18' || er.id=='21' || er.id=='24'}"><br /><br /></c:if> 
								</c:forEach>
								</td>
							</tr>
							<tr>
								<td width="100%" align="left" colspan="4">
									<div style=" height:410px; overflow:auto; ">
										 <textarea  id="translate" name="translate"  style="width: 100%"  >${PeriodPaperUserAnswer. replyContent}</textarea> 
									</div>
								</td>
							</tr>
						</table>
						</div>
					</fieldset>
					  <input type="hidden" name="hearingValues" id="hearingValues"/>
				<!-- ---- -->
				<!-- <table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="button" value=" 提 交 " onclick="flush()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table> -->
				
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
		</form>
		
		</div>
</body>
<script language="javascript" type="text/javascript">
	$(function(){
		UE.getEditor('translate', {initialFrameWidth:'100%',initialFrameHeight : 300});
		$("#translate").attr("class","");
	});
	function back(){
		top.Dialog.close();
	}
	
	function upimgs(answerUrl){
		listForm.action="business/PeriodPaperUserAnswer.do?act=fileDownload";
		listForm.submit();
	}
</script>


</html>
