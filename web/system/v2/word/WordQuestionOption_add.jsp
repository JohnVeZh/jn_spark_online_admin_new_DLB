<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<!-- 表单验证end -->
	 <!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<!-- 日期选择框end -->
	<!-- 异步上传图片 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：新增单词选项</span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" action="<%=path %>/business/Word.do?act=WordQuestionOptionAdd" method="post" id="listForm" >
			<input type="text" name="wordId" value="${wordId}" style="display: none"/>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr>
					<td width="15%" align="right">
						单词题目：
					</td>
					<td width="35%" align="left">
						${wordName}
					</td>
					<td width="15%" align="right">
						选项：
					</td>
					<td width="35%" align="left">
						<input type="text" name="prefix" id="prefix"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						选项内容：
					</td>
					<td  width="35%" align="left" >
						<input type="text" name="content" id="content" style="width: 100%" />
					</td>
					<td width="15%" align="right">
						是否是答案：
					</td>
					<td width="35%" align="left">
						<input type="checkbox" name="isAnswer" id="isAnswer" value="1"/>
					</td>
				</tr>
				<tr>
						<td colspan="4" align="center" style="border: none;">
							<input type="button" value=" 提 交 " onclick="flush()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭" onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
				</tr>
			</table>
		</form>
	</div>
	<script language="javascript" type="text/javascript">
		function back(){
		   //top.Dialog.close();
		   top.Dialog.close();
		}
		function flush(){
			var prefix=$("#prefix").val();
			var content=$("#content").val();
			if(prefix.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("选项不能为空",285,285);
				return ;
			} 
			if(content.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("选项内容不能为空",285,285);
				return ;
			} 
			$("#scrollContent").mask("表单正在提交...");
			$('#listForm').ajaxSubmit(function(data){
			    if(data.result){
			    	alert("添加成功，请刷新页面");
			    	top.Dialog.close();
			    }
			   })
		}
	</script>
	</body>
</html>
