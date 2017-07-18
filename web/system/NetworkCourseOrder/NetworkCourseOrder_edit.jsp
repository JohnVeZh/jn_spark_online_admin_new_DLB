<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NetworkCourseOrder.NetworkCourseOrderActionForm "%>
<%@page import="com.business.NetworkCourse.NetworkCourseActionForm"%>
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
<form  action="${pageContext.request.contextPath}/business/NetworkCourseOrder.do?act=update" method="post" id="form1" target="frmright">
	<input id="productOrderId"  name="productOrderId" value = "${productOrderId}"type="hidden" />
	<input  name="productOrderDetailsId" value = "${productOrderDetailsId}"type="hidden" />
	<input  name="NetworkCourseId" value="${NetworkCourseId}" type="hidden" />
	<div  style="position:absolute;left:42px;top:68px;width:380px;height:100px;z-index:1;">
		<table formMode="true">
			<tr>
				<td width="15%"  align="right">
					姓    名：
				</td>
				<td width="35%" align="left">
					<input type="text" name = "consignee" value="${NetworkCourseOrderActionForm.consignee }" />
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					手  机  号：
				</td>
				<td width="35%" align="left">
					<input type="text" name="phone" value="${NetworkCourseOrderActionForm.telephone }" />
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					详细地址：
				</td>
				<td width="35%" align="left">
					<input type="text" name="address" value="${NetworkCourseOrderActionForm.address }" />
				</td>
			</tr>
		</table>
	</div>
	<div  style="position:absolute;left:480px;top:68px;width:380px;height:60px;z-index:1; ">
		<table formMode="true">
			<tr>
				<td width="15%"  align="right">
					直辖市/省：
				</td>
				<td width="35%" align="left">
					<select  name="provinceIdStr" id="select" onchange="getCity_Area('#select','#select1','city')">
						<option value="">请选择省份</option>
						${provinceList}
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					市：
				</td>
				<td width="35%" align="left">
					<select name="cityIdStr" id="select1"  onchange="getCity_Area('#select1','#select2','area')" >
						<option value="">请选择</option>
                        ${cityList}
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					区/县：
				</td>
				<td width="35%" align="left">
					<select name="areaIdStr" id="select2"  onchange="addstr()" >
						<option value="">请选择</option>
                        ${areaList}
					</select>
				</td>
			</tr>
        </table>
    </div>
    <div  style="position:absolute;left:480px;top:198px;width:380px;height:60px;z-index:0; ">
        <table formMode="true">
			<tr>
				<td width="15%"  align="right">
					邮    编：
				</td>
				<td width="35%" align="left">
					<input type="text" name="zipcode" value="${NetworkCourseOrderActionForm.zipCode }" />
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					备    注：
				</td>
				<td width="35%" align="left">

				</td>
			</tr>
		</table>
	</div>
	<div  style="position:absolute;left:480px;top:260px;width:380px;height:50px;z-index:0; ">
		<div style="float:right;width:40px; bottom: auto ; background-color:#FF6633"></div>
		<table formMode="true">
			<tr>
				<td width="10%"  align="right">
				</td>
				<td width="40%" align="left">
					<textarea  disabled="disabled">${NetworkCourseOrderActionForm.remark}</textarea>
				</td>
			</tr>
		</table>
	</div>
    <div  style="position:absolute;left:572px;top:360px;width:380px;height:30px;z-index:0; ">
        <%--<input type="submit" value=" 提 交 " onclick="flush()"/>--%>
        <input type="button" value=" 提 交 " onclick="flush()"/>
    </div>
	<div  style="position:absolute;left:42px;top:198px;width:380px;height:200px;z-index:1;">
		<table formMode="true">
			<tr>
				<td width="15%"  align="right">
					订单状态：
				</td>
				<td width="35%" align="left">
					<select name="state" id="state" >
						<option value="">全部</option>
						<%--<option value="refund_audit" <c:if test="${state=='refund_audit'}"> selected  = "selected " </c:if> >退款审核中</option>--%>
						<%--<option value="refund" <c:if test="${state=='refund'}"> selected  = "selected " </c:if>  >退款中</option>--%>
						<%--<option value="refunded_completed" <c:if test="${state=='refunded_completed'}"> selected  = "selected " </c:if> >已退款</option>--%>
						<option value="not_paid" <c:if test="${state=='not_paid'}"> selected  = "selected " </c:if> >待付款</option>
						<c:if test="${isGiftBookstate == '有'}">
							<option value="not_deliver" <c:if test="${state=='not_deliver'}"> selected  = "selected " </c:if> >待发货</option>
							<option value="not_received" <c:if test="${state=='not_received'}"> selected  = "selected " </c:if> >待收货</option>
						</c:if>
						<option value="completed" <c:if test="${state=='completed'}"> selected  = "selected " </c:if> >已完成</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					有无讲义：
				</td>
				<td width="35%" align="left">
					${isGiftBookstate}
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					金    额：
				</td>
				<td width="35%" align="left">
					<fmt:formatNumber value="${NetworkCourseOrderActionForm.price }" pattern="0.00" />
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					实付金额：
				</td>
				<td width="35%" align="left">
					<fmt:formatNumber value="${NetworkCourseOrderActionForm.payPrice }" pattern="0.00" />
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					支付方式：
				</td>
				<td width="35%" align="left">
					<c:if test="${NetworkCourseOrderActionForm.payType == '0'}">
						支付宝支付
					</c:if>
					<c:if test="${NetworkCourseOrderActionForm.payType == '1'}">
						微信支付
					</c:if>
					<c:if test="${NetworkCourseOrderActionForm.payType =='2' }">
						兑换码
					</c:if>
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					课程名称：
				</td>
				<td width="35%" align="left">
					${NetworkCourseActionForm.ncName }
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
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
				<td width="15%"  align="right">
					课程级别：
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
				<td width="15%"  align="right">
					订 单 号：
				</td>
				<td width="35%" align="left">
					${NetworkCourseOrderActionForm.orderCode }
				</td>
			</tr>
			<tr>
				<td width="15%"  align="right">
					创建时间：
				</td>
				<td width="35%" align="left">
					${NetworkCourseOrderActionForm.createTime }
				</td>
			</tr>
		</table>
	</div>
</form>
<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：修改订单<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script language="javascript" type="text/javascript">
	function flush(){
	    var val = $("#state").val();
	    if ("" === val){
            top.Dialog.alert('<h3 align="center">订单状态必须选一项</h3>');
            return;
        }
        $("#form1").submit();
	/*    var url = "${pageContext.request.contextPath}/business/NetworkCourseOrder.do?act=update";
        $.post(url, $("#form1").serialize());
        top.Dialog.close();
		top.frmright.window.location.reload();*/
	}
	function back(){
		top.Dialog.close();
	}
    //查询城市区县
    function getCity_Area(id,id1,type){
        var Fid = $(id).val();
        $.post("business/UserAddress.do?act=province_city_area_List",{Fid:Fid,type:type},
            function (data){
                $(id1).show();
                $(id1).empty();
                $(id1).append("<option value=''>请选择</option>");
                $(id1).append(data.dataList);
                $(id1).setValue();
            },
            "json"
        );
    }



    function addstr(){
        var str= $("#select").find("option:selected").text();
        var str1= $("#select1").find("option:selected").text()
        var str2= $("#select2").find("option:selected").text();
    }

</script>

</html>
