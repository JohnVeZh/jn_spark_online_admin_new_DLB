<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.easecom.base.model.*"  %>
<%@page import="com.easecom.common.util.SessionContainer"%>

<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
<style type="text/css">
.aa {
	text-align: center;
	color:#F00;
	font-family:"微软雅黑";
}
</style>
<script>
function reLogin(){
	parent.location.reload();
}
</script>
</head><body >
<div id="zDialogCon">
<% if(null==request.getSession().getAttribute("SessionContainer")){ %>
<div class="aa" ><img src="<%=path%>/images/error500.jpg" /></div>
<div  class="aa" ><span >您的登陆信息失效了，请 <input type="button" onclick="reLogin();" value="重新登陆" /> </span></div>
<%} %>
</div>
</body></html>