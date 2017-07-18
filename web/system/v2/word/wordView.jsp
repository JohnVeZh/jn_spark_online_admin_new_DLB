<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.v2.word.TbWord"%>
<%@page import="com.easecom.common.util.*"%>
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
		<form name="listForm" scope="request" action="<%=path%>/business/Word.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr>
					<td width="15%" align="right">
						单词：
					</td>
					<td width="35%" align="left">
						${TbWord.word}
					</td>
					<td width="15%" align="right">
						音标类：
					</td>
					<td width="35%" align="left">
						${TbWord.phoneticSymbol}
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						音标：
					</td>
					<td class="DataTable_Content" align="left" ><audio style="width: 80px" controls="controls"><source src="<% if(po.getPronunciationUrl().startsWith("http")) {%><% if(null != po.getPronunciationUrl()){out.print(po.getPronunciationUrl());} %><%}else{ %>http://7xqc0j.com1.z0.glb.clouddn.com/spark-exam/spark-exam/<% if(null != po.getPronunciationUrl()){out.print(po.getPronunciationUrl());} %><%} %>" /></audio></td>
					<td width="15%" align="right">
						释义：
					</td>
					<td width="35%" align="left">
						${TbWord.paraphrase}
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						例句：
					</td>
					<td width="35%" align="left" colspan="3">
						<div>
							 <!-- <textarea  style="width: 100%"  maxNum="500" disabled="disabled"> -->${TbWord.exampleSentence}${TbWord.exampleReference}<!-- </textarea>  -->
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">
						所属组：
					</td>
					<td width="35%" align="left" colspan="3">
						${name}
					</td>
				</tr>
				<tr>
						<td colspan="4" style="border: none;" align="center">
							<input type="reset" value=" 关闭 " onclick="back()" />

						</td>
					</tr>
			</table>
		</form>
	</div>
	</body>
	<script language="javascript" type="text/javascript">
	function back(){
		top.Dialog.close();
	}
	</script>
</html>
