<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.business.NetworkCourseOrder.NetworkCourseOrderActionForm " %>
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
					<span>当前位置：退单详情<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
			<div  whiteBg="true" id="form1">
				<fieldset> 
					<legend style="font-size: large">退单详情</legend>
						<table
							   formMode="true" >
						<tr align="center">
							<td width="15%" align="right">
								课程名称：
								</td>
								<td width="35%" align="left">
									${NetworkCourseActionForm.ncName }
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">

								课程大类：
								</td>
								<td width="35%" align="left">
                                    <c:if test="${NetworkCourseActionForm.ncLevel =='cet4' }">
                                        四级
                                    </c:if>
                                    <c:if test="${NetworkCourseActionForm.ncLevel =='cet6' }">
                                        六级
                                    </c:if>
                                    <c:if test="${NetworkCourseActionForm.ncLevel =='pe' }">
                                        考研
                                    </c:if>
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								课程类别：
								</td>
								<td width="35%" align="left">
                                    <c:if test="${NetworkCourseActionForm.ncLevelType =='system_course' }">
                                        系统课
                                    </c:if>
                                    <c:if test="${NetworkCourseActionForm.ncLevelType =='special_course' }">
                                        专项课
                                    </c:if>
                                    <c:if test="${NetworkCourseActionForm.ncLevelType =='public_course' }">
                                        公开课
                                    </c:if>
                                    <c:if test="${NetworkCourseActionForm.ncLevelType =='wei_course' }">
                                        微课
                                    </c:if>
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								订 单 号：
								</td>
								<td width="35%" align="left">
									${NetworkCourseOrderRefundActionForm.orderCode }
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								邮    编：
								</td>
								<td width="35%" align="left">
									${NetworkCourseOrderActionForm.zipCode }
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								备    注：
								</td>
								<td width="35%" align="left">
										${NetworkCourseOrderActionForm.remark }
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								创建时间：
								</td>
								<td width="35%" align="left">
									${NetworkCourseOrderRefundActionForm.createTime }
								</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								姓    名：
								</td>
								<td width="35%" align="left">
								  	${NetworkCourseOrderActionForm.consignee }
								</td>
						</tr>
						<tr>
								<td width="15%" align="right">
								手 机 号：
								</td>
								<td width="35%" align="left">
									${telephone }
								</td>
						</tr>
							<tr>
								<td width="15%" align="right">
								收货地址：
								</td>
								<td width="35%" align="left">
									${pca }
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
								订单状态：
								</td>
								<td width="35%" align="left">
									<c:if test="${NetworkCourseOrderRefundActionForm.checkStatus =='check_ing'}">退款审核中</c:if>
									<c:if test="${NetworkCourseOrderRefundActionForm.checkStatus =='check_passed'}">退款中</c:if>
									<c:if test="${NetworkCourseOrderRefundActionForm.checkStatus =='pay_finished'}">已退款</c:if>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
								有无讲义：
								</td>
								<td width="35%" align="left">
									<c:if test="${NetworkCourseActionForm.isGiftBook =='0' }">
										无
									</c:if>
									<c:if test="${NetworkCourseActionForm.isGiftBook =='1' }">
										有
									</c:if>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									物流单号：
								</td>
								<td width="35%" align="left">
									<c:if test="${productOrderLogistics != null}">
										${productOrderLogistics.logisticscode}
									</c:if>
									<c:if test="${productOrderLogistics == null}">

									</c:if>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
								金    额：
								</td>
								<td width="35%" align="left">
									<fmt:formatNumber value="${NetworkCourseOrderActionForm.price }" pattern="0.00" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
								实付金额：
								</td>
								<td width="35%" align="left">
									<fmt:formatNumber value="${NetworkCourseOrderActionForm.payPrice }" pattern="0.00" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
								退款金额：
								</td>
								<td width="35%" align="left">
									<fmt:formatNumber value="${NetworkCourseOrderRefundActionForm.fee }" pattern="0.00" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									支付方式：
								</td>
								<td width="35%" align="left">
									<c:if test="${NetworkCourseOrderActionForm.payType =='0' }">
										支付宝
									</c:if>
									<c:if test="${NetworkCourseOrderActionForm.payType =='1' }">
										微信
									</c:if>
									<c:if test="${NetworkCourseOrderActionForm.payType =='2' }">
										兑换码
									</c:if>
								</td>
							</tr>
						</table>
					</fieldset>
				<table class="tableStyle"
					style=" margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="reset" value=" 关闭 " onclick="back()" />
						</td>
					</tr>
				</table>
			</div>
		</div>
</body>
</html>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		top.Dialog.close();
	}
	
	</script>