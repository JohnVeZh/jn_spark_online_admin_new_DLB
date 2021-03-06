<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.JQrNw.JQrNwActionForm"%>
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

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
	 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：j_qr_nw_view<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/JQrNw.do?act=update" method="post"  onsubmit="return sub(this)">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>j_qr_nw基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
								<tr>
								<td width="15%">
									课程链接：
								</td>
								<td width="35%">									 
									<html:text property="networkLiveLink" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:200--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									二维码：
								</td>
								<td width="35%">									 
									<html:text property="qrCode" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:2000--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									图片路径：
								</td>
								<td width="35%">									 
									<html:text property="imgPath" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:200--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									id：
								</td>
								<td width="35%">									 
									<html:text property="id" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									简系列网课类型：
								</td>
								<td width="35%">									 
									<html:text property="qrnwtId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									课程类型  0：录播  1：直播：
								</td>
								<td width="35%">									 
									<html:text property="networkType" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：int 长度:10--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									课程录播路径：
								</td>
								<td width="35%">									 
									<html:text property="networkRecordLink" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:200--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									网课直播时间段：
								</td>
								<td width="35%">									 
									<html:text property="networkLiveTime" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:200--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									网课名称：
								</td>
								<td width="35%">									 
									<html:text property="name" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:100--></span>
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
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
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