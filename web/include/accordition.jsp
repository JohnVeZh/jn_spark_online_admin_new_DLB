<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.easecom.system.business.MenuMgr" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String pid = request.getParameter("pid");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin"/>
<!--框架必需end-->

<script type="text/javascript" src="<%=path %>/js/nav/ddaccordion.js"></script>
<script type="text/javascript" src="<%=path %>/js/text/text-overflow.js"></script>
<style>
a {
	behavior:url(<%=path %>/js/method/focus.htc)
}
.categoryitems span{
	width:160px;
}
</style>

<body leftFrame="true">
<div id="scrollContent">
<div class="arrowlistmenu">
  <% out.write( MenuMgr.getNewMenus(request.getContextPath(),request.getSession(),pid));%>
</div>				
</body>
</html>