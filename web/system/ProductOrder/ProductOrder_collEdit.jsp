<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
 <!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：退单管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/ProductOrder.do?act=add" method="post" id="listForm" >
			<input type="text" name="ID" id="ID" value="${ID }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend></legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
							
							<tr>
								<td width="15%" align="right">
									退款订单编号：
								</td>
								<td width="35%" align="left">${orderCode }</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									类型：
								</td>
								<td width="35%" align="left">
								  <c:if test="${type == '0'}">退款</c:if> 
								  <c:if test="${type == '1'}">退款退货</c:if> 
								  <c:if test="${type == '2'}">待发货退款</c:if> 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									状态：
								</td>
								<td width="35%" align="left"> 
								  <select name="status" id="status">
								   ${selStr }
								  </select>
								</td>
							</tr>
								
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 400px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="button" value=" 提 交 " onclick="ajaxSub()"/>
							&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关 闭 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
				<br />
			</div>
		</form>
		</div>
</body>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		top.Dialog.close();
	}
	
	function ajaxSub(){
		var podId = $("#ID").val();
		var status = $("#status").val();
		 $.post(
                '<%=path%>/business/ProductOrder.do?act=collEdit',
                {
                podId:podId,
                status:status
                },
                function(data) {
                 if(data.result){
                   alert("修改成功，请手动刷新");
                   top.Dialog.close();
                 }
                },
            'json'
            );
	}
	</script>


</script>


</html>
