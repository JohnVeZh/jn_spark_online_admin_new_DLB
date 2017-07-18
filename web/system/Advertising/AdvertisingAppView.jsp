<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AdvertisingAppView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--框架必需start-->
<script type="text/javascript" src="<%=path %>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path %>/libs/js/framework.js"></script>
<link href="<%=path %>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

  </head>
  
  <body>
    ${content }
  </body>
</html>
