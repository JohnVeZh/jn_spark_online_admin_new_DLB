<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ page import="java.util.*"%>
<%@ page import="com.easecom.common.util.*"%>
<%@ page import="com.easecom.base.model.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	 <html:base />
     <%@ include file="../include/css.jsp" %> 
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--框架必需start-->
	<script type="text/javascript" src="../js/jquery-1.4.js"></script>
	<script type="text/javascript" src="../js/framework.js"></script>
	<link href="../css/import_basic.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" />
	<!--框架必需end-->
	<!--截取文字start-->
	<script type="text/javascript" src="../js/text/text-overflow.js"></script>
	<!--截取文字end-->
	<%
		String path = request.getContextPath();
		ListContainer lc = (ListContainer) request.getAttribute("lc");
			List _list = new ArrayList();
			if (lc != null)
				_list = lc.getList();
	%>
</head>

<body>
	<div id="scrollContent" class="border_gray">
		<form id="listForm" name="listForm" scope="request" action="<%=path %>/management/comments.do?act=list" method="post">
			<table style="width: 100%" class="tableStyle" >
				<thead>
					<tr>
						<th>编号</th>
						<th>用户名称</th>
						<th>订单数量</th>
						<th>金额数量</th>
					</tr>
				</thead>
				<%
					int sn = lc.getIndex();
					List list = lc.getList();
					Object[] objs = null;
					if(list!=null && list.size()>0){
						for (int i = 0; i < list.size(); i++) {
							objs = (Object[]) list.get(i);
				%>	
				<tr>
					<td><%=++sn %></td>
					<td><a href="javascript:;" onclick="orderList('<%=objs[0] %>');"><%=objs[1] %></a></td>
					<td><%=objs[2] %></td>
					<td><%=objs[3] %></td>
				</tr>
				<%
						}
					}
				%>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
				<div class="center">
					<div class="right">
						<%@include file="../include/listpage_list.jsp"%>
					</div>
				</div>
			</div>
	</form>
	</div>
</body>
<SCRIPT LANGUAGE="JavaScript">

function orderList(userid){
		var date = new Date();
		var year = date.getFullYear();
		var diag = new top.Dialog();
		diag.Title = "订单列表";
		diag.URL = "<%=path %>/base/order.do?act=find&userid="+userid+"&date="+year;
		diag.Width = 1000;
		diag.Height = 700;
		diag.show();
}


</SCRIPT>
</html:html>