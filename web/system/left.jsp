<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.easecom.system.business.MenuMgr" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="<%=path %>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path %>/libs/js/framework.js"></script>
<link href="<%=path %>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path %>/" scrollerX="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!-- 树型抽屉导航start-->
<script type="text/javascript" src="<%=path %>/libs/js/tree/ztree/ztree.js"></script>
<link href="<%=path %>/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path %>/libs/js/nav/treeAccordion_normal.js"></script>
<!-- 树型抽屉导航end -->
<style>
	.ztree li span.zbutton.diy01_ico_open, .ztree li span.zbutton.diy01_ico_close{width:24px!important;height:24px!important;padding-top:0;}
</style>
<script type="text/javascript">

	var zNodes =[
	<% out.write( MenuMgr.get3_3Menus(path,request.getSession()));%>
 
	];
	
	 
</script>
</head>

<body leftFrame="true">
	<div>
		<ul id="treeDemo" class="ztree ztree_accordition"></ul>
	</div>
</body>
</html>