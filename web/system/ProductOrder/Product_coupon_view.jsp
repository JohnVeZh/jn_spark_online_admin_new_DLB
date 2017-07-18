<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.ProductOrder.ProductOrderActionForm"%>
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
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
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
				<div class="right">
					<span>当前位置：优惠券信息</span>
				</div>
			</div>
		</div>
	</div>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset>
					<legend>优惠券基本信息</legend>
						<table class="tableStyle" transMode="true" footer="normal">
								<tr>
								<td width="15%">
									标题：
								</td>
								<td width="35%">
									<span>${title}</span>
								</td>
									<td width="15%">
										优惠码：
									</td>
									<td width="35%">
										<span >${code}</span>
									</td>
							</tr>
								<tr>
								<td width="15%">
									类型：
								</td>
								<td width="35%">
									<span >${type}</span>
								</td>
								<td width="15%">
									状态：
								</td>
								<td width="35%">
									<span >${status}</span>
								</td>
							</tr>
							<tr>

								<td width="15%">
									绑定类型：
								</td>
								<td width="35%">
									<span >${relation_type}</span>
								</td>
								<td width="15%">
									折扣：
								</td>
								<td width="35%">
									<span >${discount_rate}</span>
								</td>
							</tr>
							<tr>
								<td width="15%">
									满额：
								</td>
								<td width="35%">
									<span >${min_money}</span>
								</td>
								<td width="15%">
									减额：
								</td>
								<td width="35%">
									<span >${discount_money}</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									最大使用次数：
								</td>
								<td width="35%">
									<span >${max_use_num}</span>
								</td>
								<td width="15%">
									已使用次数：
								</td>
								<td width="35%">
									<span >${use_num}</span>
								</td>
							</tr>

								<tr>
								<td width="15%">
									开始时间：
								</td>
								<td width="35%">
									<span >${start_time}</span>
								</td>

								<td width="15%">
									结束时间：
								</td>
								<td width="35%">
									<span >${end_time}</span>
								</td>
							</tr>
							<tr>
								<td width="15%">
									优惠券创建时间：
								</td>
								<td width="35%">
									<span >${ctime}</span>
								</td>
								<td width="15%">
									优惠码生成时间：
								</td>
								<td width="35%">
									<span >${codetime}</span>
								</td>
							</tr>
						</table>
					</fieldset>

				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="reset" value=" 关 闭 " onclick="closeWindow()" />
						</td>
					</tr>
				</table>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
		</div>
</body>
</html>
<script language="javascript" type="text/javascript">
 
 
	function closeWindow(){
		top.Dialog.close();
	}
	
	</script>