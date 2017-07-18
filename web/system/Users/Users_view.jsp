<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Users.UsersActionForm"%>
<%@page import="com.easecom.common.util.*"%>

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
	 <!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：详情<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/Users.do?act=update" method="post"  onsubmit="return sub(this)">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
								<tr>
								<td width="15%">
									QQ：
								</td>
								<td width="35%">									 
									<html:text property="qq" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:20--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									：
								</td>
								<td width="35%">									 
									<html:text property="id" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									联系电话：
								</td>
								<td width="35%">									 
									<html:text property="mobile" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:16--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									ios推送开关：
								</td>
								<td width="35%">									 
									<html:text property="iosOff" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:1--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									用户名称：
								</td>
								<td width="35%">									 
									<html:text property="username" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									创建时间：
								</td>
								<td width="35%">									 
									<html:text property="createtime" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:50--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									账户积分：
								</td>
								<td width="35%">									 
									<html:text property="score" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：int 长度:10--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									imsi号：
								</td>
								<td width="35%">									 
									<html:text property="imsi" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									短信验证码：
								</td>
								<td width="35%">									 
									<html:text property="code" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:10--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									状态 0：正常 1：禁用：
								</td>
								<td width="35%">									 
									<html:text property="isdisable" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：int 长度:10--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									账号：
								</td>
								<td width="35%">									 
									<html:text property="account" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									性别（0：男  1：女）：
								</td>
								<td width="35%">									 
									<html:text property="sex" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：int 长度:10--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									密码：
								</td>
								<td width="35%">									 
									<html:text property="password" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									成长值：
								</td>
								<td width="35%">									 
									<html:text property="growing" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：int 长度:10--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									手机串号：
								</td>
								<td width="35%">									 
									<html:text property="imel" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									余额：
								</td>
								<td width="35%">									 
									<html:text property="balance" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：double 长度:22--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									经度：
								</td>
								<td width="35%">									 
									<html:text property="mapy" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：double 长度:22--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									微信号：
								</td>
								<td width="35%">									 
									<html:text property="wx" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:20--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									真实姓名：
								</td>
								<td width="35%">									 
									<html:text property="realname" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:30--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									图片路径：
								</td>
								<td width="35%">									 
									<html:text property="icon" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:200--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									邮箱：
								</td>
								<td width="35%">									 
									<html:text property="email" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:32--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									纬度：
								</td>
								<td width="35%">									 
									<html:text property="mapx" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：double 长度:22--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									最后一次登录时间：
								</td>
								<td width="35%">									 
									<html:text property="loginTime" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star"><!--* 类型：String 长度:50--></span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									融云token：
								</td>
								<td width="35%">									 
									<html:text property="token" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
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