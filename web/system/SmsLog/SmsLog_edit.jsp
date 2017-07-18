<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.SmsLog.SmsLogActionForm"%>
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
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/"/>
	<link href="<%=path%>/css/product_add.css" rel="stylesheet" type="text/css"/>
	<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
	 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：sms_log_edit<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/SmsLog.do?act=update" method="post"  onsubmit="return sub(this)">
			<html:hidden property="id" ></html:text>  
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>sms_log基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
								<tr>
								<td width="15%">
									请求IP：
								</td>
								<td width="35%">									 
									<html:text property="logIp" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:15</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									短信内容：
								</td>
								<td width="35%">									 
									<html:text property="logMsg" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:300</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									短信动态码：
								</td>
								<td width="35%">									 
									<html:text property="logCaptcha" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:6</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									添加时间：
								</td>
								<td width="35%">									 
									<html:text property="addTime" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									手机号：
								</td>
								<td width="35%">									 
									<html:text property="logPhone" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:11</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									记录ID：
								</td>
								<td width="35%">									 
									<html:text property="id" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:10</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									会员名：
								</td>
								<td width="35%">									 
									<html:text property="memberName" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:50</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									会员ID,注册为0：
								</td>
								<td width="35%">									 
									<html:text property="memberId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:10</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									短信类型:1为注册,2为登录,3为找回密码,默认为1：
								</td>
								<td width="35%">									 
									<html:text property="logType" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:0</span>
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
							<input type="submit" value=" 提 交 "/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " /> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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
<script language="javascript" type="text/javascript">
 
	function back(){
		listForm.action="business/News.do?act=list";
		listForm.submit();
	}

	  $(function(){
	  		var msg='';//jstl的标签 $，{，msg}
	  		if(msg==200){
	  			top.Dialog.alert("添加成功。");
	  		}else if(msg==201){
	  			top.Dialog.alert("添加失败。");
	  		}else if(msg==202){
	  			top.Dialog.alert("添加失败!");
	  		}
	  	});
</script>
</html>
