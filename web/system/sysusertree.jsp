<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.easecom.common.util.SessionContainer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="../include/css.jsp" %> 
<script type="text/javascript" src="../js/tree/dtree/dtree.js"></script>
<link href="../js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
</head>
<body >
<br/>
<center>
	<input type="submit" name="Submit" value="刷  新"   onClick="javascript:window.location.reload()"/>
</center>
<jsp:useBean id="treeMaker" class="com.easecom.common.util.TreeMaker" scope="page"/> 
<% 
Collection collection = (Collection)request.getAttribute("treelist");
String rootid=(String)request.getAttribute("rootid");
String orgid=(String)request.getAttribute("orgid");
%>
<div noWrap style="position: absolute; width:90%px; height:100%px; top: 20px; left: 5px; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow:auto;" style=" background-image:url(crm/style/normal/main_images/middlerightxiugaibg.gif);"> 
<br/><br/><br/><br/><br/>
<script language="javascript">
  if (document.getElementById) { 
	<%
	    StringBuffer tree=treeMaker.TreeRootInit3(rootid,"系统目录",request.getContextPath()+"/style/normal/images/tree01.gif",request.getContextPath()+"/style/normal/images/tree01.gif",request.getContextPath()+"/system/sysUser.do?act=list&orgId="+orgid+"&isckmttype=0","main");
		tree = treeMaker.TreeListApp(collection,request.getContextPath()+"/style/normal/images/c1.gif",request.getContextPath()+"/style/normal/images/c2.gif","","main");
		out.println(tree.toString());
	%>
	document.write(node);
  }
</script>
</div>
</body>
</html>
