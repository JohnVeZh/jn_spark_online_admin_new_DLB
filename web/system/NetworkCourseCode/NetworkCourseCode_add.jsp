<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page buffer="16kb" %>  
<%@page import="com.business.NetworkCourseCode.NetworkCourseCode"%>
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
</head>
 <%
 NetworkCourseCode po = (NetworkCourseCode) request.getAttribute("NetworkCourseCode");
 %>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：批量生成兑换码</span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/NetworkCourseCode.do?act=add" method="post" id="listForm" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>批量添加</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>选择网课：
								</td>
								<td width="90%" align="left" colspan="3">
									<select name="nvId" id="nvId" size="5"  style="width:500px;">
										<c:forEach items="${networkCourse }" var="nc">
											<option  value="${nc.id }" >
												${nc.ncName}
											</option> 
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>生成数量：
								</td>
								<td width="85%" align="left" colspan="3">
									<input type="text" name="sort" id="sort" class="validate[] iptClass"  inputMode="numberOnly"  watermark="限制输入小于1000正整数"/>
									<span class="star"></span>
								</td>
							</tr>
							<!-- <tr>
								<td width="10%" align="right">
									<span class="star">*</span>生效时间：
								</td>
								<td width="35%" align="left">
									<input type="text" name="startTime" id="startTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td width="10%" align="right">
									<span class="star">*</span>失效时间：
								</td>
								<td width="35%" align="left">
									<input type="text" name="endTime" id="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr> -->
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="checkbox" name="checkExport" id="checkExport" checked="checked" value="1"/>是否导出
							<input type="button" value=" 提 交 " onclick="flush()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
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
	function ncprice(){
		var a=$("option[selected = 'selected']").find("p");
		alert(a);
		
	}
    function back(){
	   //top.Dialog.close();
	   top.Dialog.close();
	}
	function flush(){
		var nvId=$("#nvId option:selected").val();
		var checkExport=$("#checkExport:checked").val();
		/* var startTime=$("#startTime").val();
		var endTime=$("#endTime").val(); */
		var sort=$("#sort").val();
		if(nvId==null){
			top.Dialog.alert("请选择课程",285,285);
		    return "";
		}
		if(sort=='限制输入小于1000正整数'){
			top.Dialog.alert("限制输入小于1000正整数",285,285);
		    return "";
		}
		if(sort>1000 || sort==0){
			top.Dialog.alert("限制输入小于1000正整数",285,285);
		    return "";
		}
		/* if(startTime.replace(/(^\s*)/g, "").length==0){
			$("#startTime").val("2000-01-01 01:01:01");
		}
		if(endTime.replace(/(^\s*)/g, "").length==0){
			$("#endTime").val("2000-01-01 01:01:01");
		} */
		if(checkExport==1){
			top.Dialog.alert("Excel文件导入方可关闭页面刷新列表",285,285);
		}
		$("#scrollContent").mask("等待生成兑换码，请勿关闭页面...");
		$('#listForm').submit();
		window.setTimeout(function(){
			$("#scrollContent").unmask();
		}, 3000);
	}
</script>


</html>
