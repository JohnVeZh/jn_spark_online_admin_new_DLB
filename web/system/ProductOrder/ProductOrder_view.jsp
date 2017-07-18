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
				<div class="right" align="left">
					<span>当前位置：详情<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/ProductOrder.do?act=update" method="post"  onsubmit="return sub(this)">
									
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
						<tr>
							<td width="15%" align="right">
								订单号：
								</td>
								<td width="35%" align="left">
								  ${ProductOrderActionForm.orderCode }							 
								</td>
								<td width="15%" align="right">
									用户名：
								</td>
								<td width="35%" align="left">									 
									${uName }
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									收货人：
								</td>
								<td width="35%" align="left">
								  	${ProductOrderActionForm.consignee }					 
								</td>
								<td width="15%" align="right">
									收货人电话：
								</td>
								<td width="35%" align="left">
									${ProductOrderActionForm.telephone }				 
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									邮编：
								</td>
								<td width="35%" align="left">
									${ProductOrderActionForm.zipcode }									 
								</td>
								<td width="15%" align="right">
									订单金额：
								</td>
								<td width="35%" align="left">
									<fmt:formatNumber value="${ProductOrderActionForm.price }" pattern="0.00" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否包邮：
								</td>
								<td width="35%" align="left">
									${ProductOrderActionForm.isPostage==0?"否":"是" }									 
								</td>
								<td width="15%" align="right">
									邮费：
								</td>
								<td width="35%" align="left">
									<fmt:formatNumber value="${ProductOrderActionForm.postage }" pattern="0.00" />
								</td>
							</tr>
							<tr>
								
								<td width="15%" align="right">
									城市：
								</td>
								<td align="left">
									${pca }
								</td>
								<td width="15%" align="right">
									详细地址：
								</td>
								<td width="35%" align="left">
									${ProductOrderActionForm.address }									 
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									订单状态：
								</td>
								<td width="35%" align="left">
									${state }								
								</td>
								<td width="15%" align="right">
									创建时间：
								</td>
								<td width="35%" align="left">
									${ProductOrderActionForm.createtime }									 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right" align="right">
									备注：
								</td>
								<td width="35%" align="left" align="left" colspan="3">
									${ProductOrderActionForm.remark }									 
								</td>
							</tr>
								
						</table>
					</fieldset>
					<fieldset> 
						<legend>订单商品</legend> 
						<table class="tableStyle" >
							<tr >
								<th height="25"  align="center" class="DataTable_Field" title="唯一标识 * 类型：String 长度:32"></th>
								<th height="25"  align="center" class="DataTable_Field" title="唯一标识 * 类型：String 长度:32">订单类型</th>
								<th height="25"  align="center" class="DataTable_Field" title="唯一标识 * 类型：String 长度:32">商品名称</th>
								<th height="25"  align="center" class="DataTable_Field" title="唯一标识 * 类型：String 长度:32">商品类型</th>
								<th height="25"  align="center" class="DataTable_Field" title="用户id * 类型：String 长度:32">原价</th>	
								<th height="25"  align="center" class="DataTable_Field" title="用户id * 类型：String 长度:32">现价</th>
								<th height="25"  align="center" class="DataTable_Field" title="用户id * 类型：String 长度:32">套餐价</th>
							</tr>
							<c:forEach items="${mList }" var="ml">
								<tr>
									<td  align="left"></td>
									<td  align="left">主商品</td>
									<td  align="left">${ml.pFName }</td>
									<td  align="left">${ml.pFType==0?"图书":"网课" }</td>
									<td  align="left"><fmt:formatNumber value="${ml.presentPrice }" pattern="0.00" /></td>
									<td  align="left"><fmt:formatNumber value="${ml.originalPrice }" pattern="0.00" /></td>
									<td  align="left"><fmt:formatNumber value="${ml.originalPrice }" pattern="0.00" /></td>
								</tr>
								<c:forEach items="${ml.collMap }" var="coll">
								   <tr>
									<td  align="left"></td>
									<td  align="left">套餐商品</td>
									<td  align="left">${coll.pFName }</td>
									<td  align="left">${coll.pFType==0?"图书":"网课" }</td>
									<td  align="left"><fmt:formatNumber value="${coll.presentPrice }" pattern="0.00" /></td>
									<td  align="left"><fmt:formatNumber value="${coll.originalPrice }" pattern="0.00" /></td>
									<td  align="left"><fmt:formatNumber value="${coll.colloMoney }" pattern="0.00" /></td>
								</tr>
								</c:forEach>
							</c:forEach>
						</table>
					</fieldset>
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="reset" value=" 关闭 " onclick="back()" />
						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
  </html:form>
		</div>
</body>
</html>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		top.Dialog.close();
	}
	
	</script>