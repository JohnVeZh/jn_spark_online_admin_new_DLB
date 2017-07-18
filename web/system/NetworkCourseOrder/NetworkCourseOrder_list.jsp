<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.ProductOrder.ProductOrder"%>
<%@page import="com.easecom.common.util.Tool"%>
<%@page import="com.business.NetworkCourse.NetworkCourse " %>
<%@page import="com.business.NetworkCourseOrder.NetworkCourseOrder" %>
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
<script type="text/javascript" src="<%=path%>/libs/js/table/detailTable.js"></script>
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
    $(function(){
		top.Dialog.close();
	})
	$(function(){
		var msg="";//jstl $ { msg}
		if("200"==msg){
			top.Dialog.alert("删除成功.");
		}
	})
	$(function () {
        if(${empty mList}){
			top.Dialog.alert('<h3 align="center">木有找到相关记录</h3>')
		}
    })
    $(function () {
        var value = $("#phone").val();
        if(value == "telephoneShow"){
            $("#telephoneShow").css("display","");
            $("#mobileShow").css("display","none");
        }
        if(value == "mobileShow"){
            $("#mobileShow").css("display","");
            $("#telephoneShow").css("display","none");
        }
        $("#phone").change(function () {
            var val = $("#phone").val();
            if(val == "telephoneShow"){
                $("#telephoneShow").css("display","");
                $("#mobileShow").css("display","none");
                $("#telephone").val("");
                $("#mobile").val("");
            }
            if(val == "mobileShow"){
                $("#mobileShow").css("display","");
                $("#telephoneShow").css("display","none");
                $("#mobile").val("");
                $("#telephone").val("");
            }
            if(val == ""){
                $("#mobileShow").css("display","none");
                $("#telephoneShow").css("display","none");
                $("#mobile").val("");
                $("#telephone").val("");
            }
        })
    })

	function isSel(){
		var inps = document.getElementsByName('id');
		var idVal = "";
		var j =0 ;
		for(var i = 0; i <inps.length; i++){
			if(inps[i].checked){
				idVal +="&id="+ inps[i].value;
				j++;
			}else{
				continue;
			}
		}
		if (Number(j)>=2 || Number(j)<1) {
			top.Dialog.alert("请选择一项进行操作",185,185);
			return "";
		}else{
			return idVal;
		}
	}

	//判断
	function isSel1(){
		var inps = document.getElementsByName('id');
		var idVal = "";
		var j =0 ;
		for(var i = 0; i <inps.length; i++){
			if(inps[i].checked){
				idVal +="&id="+ inps[i].value;
				j++;
			}else{
				continue;
			}
		}
		return idVal;
	}

	//删除数据
	function doDels() {
		var idVal = isSel1();
		if(idVal!=""){
			top.Dialog.confirm("您确信要删除吗?",
					function() {
						listForm.action="ProductOrder.do?act=realdelete"+idVal;
						listForm.submit();
					}
			)
		}
	}
	function doDelsById(id) {
		if(id!=""){
			top.Dialog.confirm("您确信要删除吗?",
					function() {
						listForm.action="ProductOrder.do?act=realdeleteById&id="+id;
						listForm.submit();
					}
			)
		}
	}
	//添加
	function preAdd(){
		listForm.action="ProductOrder.do?act=preAdd"
		listForm.submit();
	}
	//修改
	function preUpdate(id) {
		if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/NetworkCourseOrder.do?act=preUpdate&id="+id,ID:"a1",Width:910,Height:540,Title:"修改"});
		}
	}
	//详情
	function view(id) {
		if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/NetworkCourseOrder.do?act=view&id="+id,ID:"a1",Width:400,Height:540,Title:"查看"});
		}
	}
	//发货
	function code(id) {
		if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preCodes&orderId="+id,ID:"a1",Width:1024,Height:768,Title:"发货"});
		}
	}
	//完成订单
	function complete(id) {
		if(id!=""){
			top.Dialog.confirm("您确信要完成订单吗?",
					function() {
						listForm.action="ProductOrder.do?act=complete&orderId="+id;
						listForm.submit();
					})
		}
	}
	//取消订单
	function cancel(id) {
		if(id!=""){
			top.Dialog.confirm("您确信要取消吗?",
					function() {
						listForm.action="ProductOrder.do?act=preCancel&orderId="+id;
						listForm.submit();
					}
			)
		}
	}

	//清空查询数据
	function qing(){
		document.getElementById("title").value="";
		document.getElementById("sel").value="";
	}

	//相关评论
	function comment(userId,id){
		//var id=$("#id").val();
		var userId=$("#userId").val();
		if(id != ""&& userId!=""){
			top.Dialog.open({URL:"<%=path%>/business/ProductOrderEvaluate.do?act=comment&id="+id+"&userId="+userId,ID:"a1",Width:1024,Height:768,Title:"相关评论"});
		}
	}
	//退款
	function collEdit(id,status){
		if(id != ""){
			top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preCollEdit&id="+id+"&status="+status,ID:"a1",Width:450,Height:300,Title:"退单管理"});
		}
	}
	//修改订单状态
	function orderStateEdit(id,status){
		if(id != ""){
			top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=preOrderStateEdit&id="+id+"&status="+status,ID:"a1",Width:450,Height:200,Title:"退单管理"});
		}
	}

	//添加
	function chaxun(){
		/*listForm.action="ProductOrder.do?act=preQuery"
		listForm.submit();*/
        alert("123")
	}

	function showlayer(){
		document.GetElementById("invisible_layer").style.visibility="visible";
	}
	function exportBookClick(){
		$("#netWorkOrBook").val("图书");
		$("#exportEX").click();
	}
	function exportNetWorkClick(){
		$("#netWorkOrBook").val("网课");
		$("#exportEX").click();
	}
	function exportExcel(){
		$("#starttime1").val($("#starttime").val());
		$("#endtime1").val($("#endtime").val());
		$("#StatrSel1").val($("#StatrSel").val());
		$("#ncLevelName1").val($("#ncLevelName").val());
		$("#ncLevelTypeName1").val($("#ncLevelTypeName").val());
		$("#shouName1").val($("#consignee").val());
		$("#telephone1").val($("#telephone").val());
		$("#ncName1").val($("#ncName").val());
		$("#orderCode1").val($("#orderCode").val());
		$("#mobile1").val($("#mobile").val());
		lf1.action = "NetworkCourseOrder.do?act=exportExcel";
		lf1.submit();
	}
	function getCoupon(couponCodeId) {
		top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=coupon&couponCodeId="+couponCodeId,ID:"a1",Width:1024,Height:500,Title:"优惠信息"});
	}


</script>
<body>
<form name="lf1" style="display: none;" scope="request" action="<%=path%>/business/NetworkCourseOrder.do?act=exportExcel" method="post">
	<input type="hidden" name="starttime1"  id="starttime1"  />
	<input type="hidden" name="endtime1"  id="endtime1"  />
	<input type="hidden"  name="StatrSel1" id="StatrSel1" />
	<input type="hidden"  name="shouName1" id="shouName1" />

	<input type="hidden"  name="ncLevelName1" id="ncLevelName1" />
	<input type="hidden"  name="ncLevelTypeName1" id="ncLevelTypeName1" />
	<input type="hidden"  name="orderCode1" id="orderCode1" />
	<input type="hidden"  name="ncName1" id="ncName1" />
	<input type="hidden"  name="telephone1" id="telephone1" />
	<input type="hidden"  name="mobile1" id="mobile1" />

	<input type="hidden"  name="netWorkOrBook" id="netWorkOrBook" />
	<input type="submit" id="exportEX" value="导出excel" onclick="exportExcel()"/>&nbsp;&nbsp;
</form>

<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：订单管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div>

	<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourseOrder.do?act=list" method="post">
		<div class="box2" panelTitle="功能面板" roller="false">
			<table style="width:100%">
				<tr>
					<td>
						<div style="float: left;">
							起始时间：<input  class="date" type="text" name="starttime" value="${starttime }"
                                         id="starttime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')}'})"/>
                            -<input  class="date" type="text" name="endtime" value="${endtime }" id="endtime"
                                     dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\')}',maxDate: '%y-%M-%d %H:%m:%s'})"})"/>
						</div>
						<div style="float: left;">
							<div style="float: left;">
								订单状态：
							</div>
							<div style="float: left;">
								<select name="StatrSel" selWidth="75" id="StatrSel" >
									<option  value="" >全部</option>
									${orderStatus }
								</select>
							</div>
						</div>
						<div style="float: left; ">
							<div style="float: left;">
								课程大类：
							</div>
							<div style="float: left;">
								<select name="ncLevelName" selWidth="60" id="ncLevelName">
									<option  value="" >全部</option>
									${ncLevelStatus }
								</select>
							</div>
						</div>
						<div style="float: left; margin-left: 10px">
							<div style="float: left;">
								课程级别：
							</div>
							<div style="float: left;">
								<select name="ncLevelTypeName" selWidth="75" id="ncLevelTypeName">
									<option  value="" >全部</option>
									${ncLevelTypeStatus }
								</select>
							</div>
						</div>
                        <%--<div style="float: left;">
                            <div style="float: left;">
                                订单来源：
                            </div>
                            <div style="float: left;">
                                <select name="fromType" selWidth="80" id="fromType" >
                                    <option  value="" <c:if test="${fromType == null}" >selected</c:if>>全部</option>
                                    <option  value="0" <c:if test="${fromType == 0}" >selected</c:if>>老订单</option>
									<option  value="1" <c:if test="${fromType == 1}" >selected</c:if>>星火APP</option>
									<option  value="2" <c:if test="${fromType == 2}" >selected</c:if>>M站</option>
									<option  value="3" <c:if test="${fromType == 3}" >selected</c:if>>PC</option>
									<option  value="4" <c:if test="${fromType == 4}" >selected</c:if>>知米APP</option>
								</select>
                            </div>
                        </div>--%>
                        <div style="float: left; margin-left: 10px">
                            <div style="float: left;">
                                联系电话：
                            </div>
                            <div style="float: left;">
                                <select name="phone" selWidth="120" id="phone">
                                    <option  value="" >全部</option>
                                    ${phoneType}
                                </select>
                            </div>
                        </div><br/><br/>
						<div id="telephoneShow" style="display: none;float: left; margin-left: 0px" >
							<div style="float: left;">
								收货人手机号：
							</div>
							<div style="float: left;width: 110px">
								<input type="text" id="telephone" style="width: 110px" name="telephone" value="${telephone}"/>
							</div>
						</div>
						<div id="mobileShow" style="display: none;float: left; margin-left: 0px" >
							<div style="float: left;width:">
								登陆账号：
							</div>
							<div style="float: left;width:110px">
								<input type="text" id="mobile" style="width: 110px" name="mobile" value="${mobile}"/>
							</div>
						</div>
                        &nbsp;订单号：<input type="text" id="orderCode" name="orderCode" value="${orderCode}"/>
						&nbsp;
						课程名字：<input type="text" id="ncName" name="ncName" value="${ncName}"/>
						&nbsp;
						收货人：<input type="text" id="consignee" name="shouName" maxlength="5" style="width: 90px" value="${shouName}" placeholder="5个汉字以内"/>
						&nbsp;

						<input type="submit" value="筛选" />&nbsp;&nbsp;

						<input type="button" value="导出" id="export" onclick="exportNetWorkClick()"/>&nbsp;&nbsp;

						<input type="button" value="物流单号导入" onclick="top.Dialog.open({URL:'<%=path%>/business/NetworkCourseOrder.do?act=preExpressImport',Title:'物流单号导入',Width:400,Height:200});"/>

					</td>
				</tr>
			</table>
		</div>
		<table  class="detailTable" mode="list" useCheckbox="true" trClick="false" selectRowButtonOnly="false" id="listTable">
			<tr >
				<th width="1" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
				<th width="16" height="25"  align="center" class="DataTable_Field" title="课程名称">课程名称</th>
				<th width="10" height="25"  align="center" class="DataTable_Field" title="订单号">订单号</th>
				<th width="10" height="25"  align="center" class="DataTable_Field" title="收货人姓名">收货人姓名</th>
				<th width="8" height="25"  align="center" class="DataTable_Field" title="收货人电话 ">收货人手机号</th>
				<th width="8" height="25"  align="center" class="DataTable_Field" title="登陆账号 ">登陆账号</th>
				<th width="4" height="25"  align="center" class="DataTable_Field" title="订单状态 ">订单状态</th>
				<th width="4" height="25"  align="center" class="DataTable_Field" title="有无讲义 ">有无讲义</th>
				<th width="2" height="25"  align="center" class="DataTable_Field" title="订单金额">金额</th>
				<th width="4" height="25"  align="center" class="DataTable_Field" title="实付金额">实付金额</th>
				<th width="15" height="25"  align="center" class="DataTable_Field" title="创建时间">创建时间</th>
				<th width="15" height="25"  align="center" class="DataTable_Field" title="物流单号">物流单号</th>
				<th width="20" height="25"  align="center" class="DataTable_Field" title="收货地址">收货地址</th>
				<th width="4" height="25"  align="center" class="DataTable_Field" title="订单来源">订单类型</th>
				<th width="1" height="25" width="220"  align="center" class="DataTable_Field" title="操作">操作</th>
			</tr>
            <c:if test="${empty mList}">
                <% out.println("<script>top.Dialog.alert('<h3 align=\"center\">木有找到相关记录'</h3>');</script>"); %>
                <% out.println("<script>$(\"#export\").attr(\"disabled\",\"disabled\")</script>"); %>
            </c:if>
			<c:forEach items="${mList }" var="ml" varStatus="s">
				<tr >
					<td class="DataTable_Content" align="center">${s.index+1 }</td>
					<td class="DataTable_Content" align="left"  title="${ml.ncName}">${ml.ncName}</td>
					<td class="DataTable_Content" align="left" title="${ml.orderCode }">${ml.orderCode }</td>
					<td class="DataTable_Content" align="left" title="${ml.userName }">${ml.userName }</td>
					<td class="DataTable_Content" align="left" title="${ml.telephone }">${ml.telephone }</td>
					<td class="DataTable_Content" align="left" title="${ml.account }">${ml.account }</td>
					<td class="DataTable_Content" align="left" title="${ml.OrderState }">${ml.OrderState }</td>
					<td class="DataTable_Content" align="left" title="${ml.isGiftBook}">${ml.isGiftBook}</td>
					<td class="DataTable_Content" align="left" title="${ml.price }"><fmt:formatNumber value="${ml.price }" pattern="0.00" /></td>
					<td class="DataTable_Content" align="left" title="${ml.payPrice }"><fmt:formatNumber value="${ml.payPrice }" pattern="0.00" /></td>
					<td class="DataTable_Content" align="left" title="${ml.createtime }">${ml.createtime }</td>
					<td class="DataTable_Content" align="left" title="${ml.productOrderLogistics.logisticscode}">${ml.productOrderLogistics.logisticscode}</td>
					<td class="DataTable_Content" align="left" title="${ml.addressPost}${ml.address}">${ml.addressPost}</td>
					<td class="DataTable_Content" align="left" title="${ml.type}">${ml.type}</td>
					<td  align="left" >
						<a href="javascript:;" title="详情"
						   onclick="view('${ml.Id }')"> <span class="img_view"></span>
						</a>
						<a href="javascript:;" onclick="preUpdate('${ml.Id }')" title="修改">
							<span class="img_edit"></span>
						</a>
					</td>
				</tr>
				<tr style="display: none;">
					<td></td>
					<td colspan="11">
						<table class="tableStyle" style="font-size: 35px;">
							<tbody>
							<tr>
								<th height="25"  align="center" class="DataTable_Field" title="订单类型">订单类型</th>
								<th height="25"  align="center" class="DataTable_Field" title="商品名称">商品名称</th>
								<th height="25"  align="center" class="DataTable_Field" title="商品类型">商品类型</th>
								<th height="25"  align="center" class="DataTable_Field" title="数量">数量</th>
								<th height="25"  align="center" class="DataTable_Field" title="状态">状态</th>
								<th height="25"  align="center" class="DataTable_Field" title="下单金额">下单金额</th>
								<th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>
							</tr>
							<c:forEach items="${ml.mList }" var="coll">
								<tr>
									<td class="DataTable_Content" align="left">主商品</td>
									<td class="DataTable_Content" align="left" title="${coll.pFName }">${coll.pFName }</td>
									<td class="DataTable_Content" align="left" title="${coll.pFType==0?'图书':'网课' }">${coll.pFType==0?"图书":"网课" }</td>
									<td class="DataTable_Content" align="left" >${coll.pcount }</td>
									<td class="DataTable_Content" align="left" title="${coll.status }">${coll.status }</td>
									<td class="DataTable_Content" align="left" title="${coll.orderPrice }"><fmt:formatNumber value="${coll.orderPrice }" pattern="0.00" /></td>
									<td class="DataTable_Content" align="left" >
										<a href="javascript:;"
										   onclick="collEdit('${coll.id }','${coll.statusValue }')" title="退款状态"> <span class="img_edit"></span>
										</a>
									</td>
								</tr>
								<c:forEach items="${coll.collMap }" var="cm">
									<tr>
										<td class="DataTable_Content" align="left">套餐商品</td>
										<td class="DataTable_Content" align="left" title="${cm.pFName }">${cm.pFName }</td>
										<td class="DataTable_Content" align="left" title="${cm.pFType==0?'图书':'网课' }">${cm.pFType==0?"图书":"网课" }</td>
										<td class="DataTable_Content" align="left" ></td>
										<td class="DataTable_Content" align="left" title="${cm.status }">${cm.status }</td>
										<td class="DataTable_Content" align="left" title="${cm.colPrice }"><fmt:formatNumber value="${cm.colPrice }" pattern="0.00" /></td>
										<td class="DataTable_Content" align="left" ></td>
									</tr>
								</c:forEach>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<input type="text" name="userId" id="userId" value="${ml.userId }" style="display:none"/>
			</c:forEach>



		</table>
		<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../include/listpage.jsp"%>
				</div>
			</div>
		</div>
		<%--<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>--%>
	</form>
</div>
</body>
</html>
