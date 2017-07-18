<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.easecom.system.business.MenuMgr" %>
<html>
	<head>
		<title></title>
		<%
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String funid = request.getParameter("funid");
		%>
		<!--框架必需start-->
		<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
		<link href="<%=path%>/css/import_basic.css" rel="stylesheet"
			type="text/css" />
		<!--框架必需end-->
		<link href="<%=path%>/style/normal/css/xtree.css" rel="stylesheet"
			type="text/css">
		<script type="text/javascript" language="javascript"
			src="<%=path%>/style/normal/js/xtree.js"></script>
		<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" />
		<script language="javascript">
		  if (document.getElementById){
			<%=(MenuMgr.classTreeList(request.getContextPath(),request.getSession(),funid))%>
			
 		 }
		</script>
	</head>
	<body  leftFrame="true">
	
		<table  width="100%"   class="tableStyle" useColor="false" useHover="false" useClick="false">
			<tr>
				<td>
						<div style="font-size:14px; font-weight:bold; padding:5px 2px; color:#6666666; width:200px; ">
						    <div style="float:left; margin-right: 5px;"><span><%=(MenuMgr.TreeName(request.getSession(),funid))%></span></div>
						    <div style="float:right;">
						      <a href="javascript:void(0);" onclick="javascript:window.location.reload()">
								<span class="img_refresh"></span>
							  </a>
						    </div>
						    <div style="clear:both;"></div>
						<div>
				</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<div id="treecontent" >
						<script>
							document.write(node);
						</script>
					</div>
					
				</td>
			</tr>
		</table>

	</body>
</html>