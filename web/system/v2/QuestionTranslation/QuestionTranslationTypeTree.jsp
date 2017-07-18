<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<HTML>
	<HEAD>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<%@ include file="../../../include/css.jsp"%>
		<TITLE></TITLE>
	</HEAD>
	<BODY >
		<br>
		<center>
			<input type="submit" name="Submit" value="刷  新"  
				onClick="javascript:window.location.reload()">
		</center>
		<jsp:useBean id="treeMaker" class="com.easecom.common.util.TreeMaker"
			scope="page" />
		<% 
Collection collection = (Collection)request.getAttribute("treelist");
String Pid=(String)request.getAttribute("Pid");
%>
		<div noWrap
			style="position: absolute; width: 90% px; height: 100% px; top: 50px; left: 5px; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow: auto;">
			<br/><br/><br/>
			<script language="javascript">
  if (document.getElementById) { 
	<%
	    StringBuffer tree=treeMaker.TreeRootInit3(Pid,"类型管理",request.getContextPath()+"/style/normal/images/tree01.gif",request.getContextPath()+"/style/normal/images/tree01.gif","javascript:window.location.reload()","main");
		tree = treeMaker.TreeListApp(collection,request.getContextPath()+"/style/normal/images/c1.gif",request.getContextPath()+"/style/normal/images/c2.gif","","main");
		out.println(tree.toString());
	%>
	document.write(node);
  }
</script>
		</div>
	</body>
</html>
