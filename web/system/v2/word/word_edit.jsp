<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.v2.word.TbWord"%>
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
	<script type="text/javascript">
	</script>
	 <%
	 TbWord po = (TbWord) request.getAttribute("TbWord");
	 %>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：单词详情<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" action="<%=path %>/business/Word.do?act=update" method="post" id="listForm" >
			<input type="text" name="id" value="${TbWord.id}" style="display: none" />
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr>
					<td width="15%" align="right">
						级别：
					</td>
					<td width="35%" align="left">
						${type}
					</td>
					<td width="15%" align="right">
						类型：
					</td>
					<td width="35%" align="left">
						${types}--${name}
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						单词：
					</td>
					<td width="35%" align="left">
						<input type="text" name="word" id="word" value="${TbWord.word}"/>
					</td>
					<td width="15%" align="right">
						音标类：
					</td>
					<td width="35%" align="left">
						<input type="text" name="phoneticSymbol" id="phoneticSymbol" value="${TbWord.phoneticSymbol}"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						音标：
					</td>
					<td class="DataTable_Content" align="left" >
						<input type="text" name="pronunciationUrl" id="pronunciationUrl" value="${TbWord.pronunciationUrl}" style="width: 100%" />
					</td>
					<td width="15%" align="right">
						释义：
					</td>
					<td width="35%" align="left">
						<input type="text" name="paraphrase" id="paraphrase" value="${TbWord.paraphrase}" style="width: 100%"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						例句：
					</td>
					<td width="35%" align="left" colspan="3">
						<div>
							<input  type="text" style="width: 100%" name="exampleSentence" id="exampleSentence" maxNum="500" value="${TbWord.exampleSentence}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						例句翻译：
					</td>
					<td width="35%" align="left" colspan="3">
						<div>
							<input  type="text" style="width: 100%" name="exampleReference" id="exampleReference" maxNum="500" value="${TbWord.exampleReference}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						例句发音：
					</td>
					<td width="35%" align="left" colspan="3">
						<input type="text" name="exampleUrl" id="exampleUrl" value="${TbWord.exampleUrl}" />
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
			var word=$("#word").val();
			var phoneticSymbol=$("#phoneticSymbol").val();
			var pronunciationUrl=$("#pronunciationUrl").val();
			var paraphrase=$("#paraphrase").val();
			var exampleSentence=$("#exampleSentence").val();
			var exampleReference=$("#exampleReference").val();
			var exampleUrl=$("#exampleUrl").val();
			if(word.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("单词名称不能为空",285,285);
				return ;
			}
			if(phoneticSymbol.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("音标不能为空",285,285);
				return ;
			}
			if(pronunciationUrl.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("发音不能为空",285,285);
				return ;
			}
			if(paraphrase.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("释义不能为空",285,285);
				return ;
			}
			if(exampleSentence.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("例句不能为空",285,285);
				return ;
			}
			if(exampleReference.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("例句翻译不能为空",285,285);
				return ;
			}
			
			$("#scrollContent").mask("表单正在提交...");
			$('#listForm').submit();
		}
	</script>
	</body>
</html>
