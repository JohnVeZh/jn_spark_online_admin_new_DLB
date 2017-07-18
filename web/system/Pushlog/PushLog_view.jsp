<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.PushLog.PushLogActionForm"%>
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
   <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
   <link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
 <!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script> 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：查看推送<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/PushLog.do?act=update" method="post"  onsubmit="return sub(this)">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
								<tr>
								<td width="20%" align="right">
									推送标题：
								</td>
								<td width="75%" align="left">
									${PushLogActionForm.title }										 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									推送内容：
								</td>
								<td width="35%" align="left">	
									${PushLogActionForm.content }									 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									推送管理员：
								</td>
								<td width="35%" align="left">
									${userName }										 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									推送时间：
								</td>
								<td width="35%" align="left">	
									${PushLogActionForm.createtime }									 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									是否包含编辑内容 ：
								</td>
								<td width="35%" align="left">
									${PushLogActionForm.ismap==0?"否":"是" }										 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									编辑的内容：
								</td>
								<td width="35%" align="left">	
									${PushLogActionForm.editcontent }								 
								</td>
							</tr>
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="reset" value=" 返 回 " onclick="back()" />
						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
  </html:form>
		</div>
</body>
</html>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		//document.getElementById('listForm').action="business/News.do?act=list";
		//document.getElementById('listForm').submit();
		history.back();
	}
	
	</script>