<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.v2.question.TbQuestionTranslation"%>
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
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
	</script>
	 <%
	 TbQuestionTranslation po = (TbQuestionTranslation) request.getAttribute("TbQuestionTranslation");
	 %>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：修改翻译详情<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" action="<%=path %>/business/QuestionTranslation.do?act=update" method="post" id="listForm" >
			<input type="text" name="id" value="${TbQuestionTranslation.id}" style="display: none" />
			<input type="text" name="catalogId" value="${TbQuestionTranslation.catalogId}" style="display: none" />
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr>
					<td width="15%" align="right">
						目录：
					</td>
					<td width="35%" align="left">
						<input type="text"  name="name" style="width: 100%" id="name" value="${TbQuestionTranslation.name }" />
					</td>
					<td width="15%" align="right">
						排序：
					</td>
					<td width="35%" align="left">
						<input type="text"  name="sortOrder" id="sortOrder" value="${TbQuestionTranslation.sortOrder }" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						试用范围：
					</td>
					<td width="35%" align="left" >
						<select name="target" id="id">
							<option value = "${TbQuestionTranslation.target}"
									<c:if test="${'0'.equals(TbQuestionTranslation.target)}">
										selected
									</c:if>
							>全部</option>
							<option value = "${TbQuestionTranslation.target}"
									<c:if test="${'1'.equals(TbQuestionTranslation.target)}">
										selected
									</c:if>
							>专项</option>
							<option value = "${TbQuestionTranslation.target}"
									<c:if test="${'2'.equals(TbQuestionTranslation.target)}">
										selected
									</c:if>
							>试卷</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						题干：
					</td>
					<td width="35%" align="left" colspan="3">
						<input type="text" name="title" style="width: 100%" id="title" value="${TbQuestionTranslation.title}"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						题目内容：
					</td>
					<td width="35%" align="left" colspan="3">
						<div style=" height:410px; overflow:auto; ">
							 <textarea  name="content" id="content" style="width: 100%" >${TbQuestionTranslation.content}</textarea> 
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						参考译文：
					</td>
					<td  align="left" colspan="3">
						<div style=" height:410px; overflow:auto; ">
							 <textarea  name="reference" id="reference" style="width: 100%" >${TbQuestionTranslation.reference}</textarea> 
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						解析：
					</td>
					<td width="35%" align="left" colspan="3">
						<div style=" height:410px; overflow:auto; ">
							 <textarea  name="analysis" id="analysis" style="width: 100%" >${TbQuestionTranslation.analysis}</textarea> 
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						视频讲解：
					</td>
					<td class="DataTable_Content" align="left" colspan="3">
						<input type="text" style="width: 100%" name="analysisUrl" id="analysisUrl" value="${TbQuestionTranslation.analysisUrl}">
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
	 $(function(){
		 UE.getEditor('content', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#content").attr("class","");
	  		UE.getEditor('reference', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#reference").attr("class","");
	  		UE.getEditor('analysis', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#analysis").attr("class","");
	  	});
		function back(){
		   //top.Dialog.close();
		   top.Dialog.close();
		}
		function flush(){
			var name=$("#name").val();
			var content_ue = UE.getEditor('content');
			var content_html = content_ue.getContent();
			var content = content_ue.getContentTxt();
			
			var reference_ue = UE.getEditor('reference');
			var reference_html = reference_ue.getContent();
			var reference = reference_ue.getContentTxt();
			
			var ue = UE.getEditor('analysis');
			var html = ue.getContent();
			var analysis = ue.getContentTxt();
			if(name.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("目录不能为空",285,285);
				return ;
			}
			if(content.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("题目内容不能为空",285,285);
				return ;
			}
			if(reference.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("参考译文不能为空",285,285);
				return ;
			}
			if(analysis.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("解析不能为空",285,285);
				return ;
			}
			$("#scrollContent").mask("表单正在提交...");
			$('#listForm').submit();
		}
	</script>
	</body>
</html>
