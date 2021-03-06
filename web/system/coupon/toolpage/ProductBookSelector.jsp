<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.Product.Product"%>
<%@page import="com.easecom.common.util.Tool"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		var $$ = top.$("#_DialogFrame_${srcpage}")[0].contentWindow.$;
		
		function selectProduct(id, name) {
			$$("#productId").val(id);
			$$("#productName").val(name);
			$$("#productName").attr("title", name);
			
            top.Dialog.close();
		}
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/coupon.do?act=productBookSelector&srcpage=${srcpage}" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
					<tr>
						<td>
							 名称：<input type="text" name="nameStr" id="nameStr" value="${nameStr }"/>
							<input type="submit" value="查询" />
                    	</td>
					</tr>
				</table>
			</div>
			
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						 <th width="5%">序号</th>
							<th width="35%" height="25"  align="center" class="DataTable_Field" title="产品名称">产品名称</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="原价 ">原价</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="现价 ">现价</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="图书类型">类型</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="">操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Product po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Product)list.get(i);
		String typeName = Tool.getValue("select type_name from product_type where id='"+po.getpTypeId()+"'");
%>
	<tr  >
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getpName() %>"><%= po.getpName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpOriginalPrice() %>"><%= po.getpOriginalPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpPresentPrice() %>"><%= po.getpPresentPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%=typeName   %>"><%=typeName   %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpCreatetime() %>"><%= po.getpCreatetime() %></td>	
		<td class="DataTable_Content" align="center">
			 <a href="javascript:;" title="选择"
				onclick="selectProduct('<%=po.getId()%>', '<%= po.getpName() %>')"> <span class="img_ok"></span>
			</a>
		</td>							
 	</tr>
<%
	}
%>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror">友情提示:</br></div>
		</form>
	</div>
	</body>
</html>
