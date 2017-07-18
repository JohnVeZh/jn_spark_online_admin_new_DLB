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
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 异步提交form -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		
		
		//搭配套餐
		function preAdd() {
		
			$("#scrollContent").mask();
			$('#listForm').ajaxSubmit(function(data){
		    	if(data.result){
		    	//top.frmright.window.location.reload();
		    	 alert("搭配成功，请手动刷新");
		    	 top.Dialog.close();
			    }else{
			    	alert("无法搭配该商品");
			    	 top.Dialog.close();
			    }
		    }); 
		}
		function back(){
			top.Dialog.close();
		}
		
		
		
	</script>
	<body>
		<div id="scrollContent">
		   <form name="listForm" id="listForm" scope="request" action="ProductCollocation.do?act=collAdd" method="post">
			<input type="text" name="productId" value="${productId }" style="display: none"/>
			<input type="text" name="productFid" value="${productFid }" style="display: none"/>
			<input type="text" name="productFtype" value="${type }" style="display: none"/>
			<input type="text" name="productType" value="${cType }" style="display: none"/>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr>
					<td align="right">
						套餐金额：
					</td>
					<td align="left">
						<input type="text" name="money" class="validate[required] iptClass" />
						<span class="star">*</span>
					</td>
				</tr>
				<tr>
				  <td align="right">
						排序：
				  </td>
				  <td align="left">
				 	 <input type="text" name="sort" class="validate[required] iptClass" inputMode="numberOnly"  watermark="限制输入正整数"/>
				 	 <span class="star">*</span>
				  </td>
				</tr>
			</table>
			<br/>
			<div align="center">
					<input type="button" value=" 提 交 " onclick="preAdd()"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value=" 关闭 " onclick="back()" />
			</div>
			</form>
		</div>
	</body>
</html>
