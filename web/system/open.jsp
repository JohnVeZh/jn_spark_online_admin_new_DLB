<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.base.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.easecom.system.business.MenuMgr" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
List data1=(List)request.getAttribute("list");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title></title>
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script language="JavaScript" src="<%=path %>/charts/FusionCharts/JSClass/FusionCharts.js"></script>
<!--基本选项卡start-->
<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTabModern.js"></script>
<!--基本选项卡end-->

<!--弹出式提示框start-->
<script type="text/javascript" src="<%=path%>/libs/js/popup/messager.js"></script>
<!--弹出式提示框end-->
<script>
	$(function(){
		setTimeout(function(){
			//$.messager.show('提示信息','提示内容','stay','','box_msg_custom');
		},2000)
		
		//修正由于使用了tab导致高度计算不准确
		if(broswerFlag=="IE6"){
			setTimeout(function(){
				top.iframeHeight('frmrightChild');
			},500)
		}
	})
	function customHeightSet(contentHeight){
		if($("#newsBox").width()<420){
			$("#newsBox").width(420);
		}
	}
	
		//详情
		function view(id) {
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/UserFeedback.do?act=view&id="+id,ID:"a1",Width:1000,Height:500,Title:"查看"});
			}
		}
		//详情
		function view1(orderId) {
			if(orderId!=""){
		  	  top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=view&id="+orderId,ID:"a1",Width:1000,Height:600,Title:"查看"});
			}
		}
		
</script>
</head>
<body >
	<div id="scrollContent" class="border_gray">
	<form name="listForm" scope="request" action="<%=path%>/business/Statistics.do?act=list" method="post" id="listForm">

	<table width="100%">
		<tr>
			<td width="45%" class="ver01">
				<div class="basicTabModern_top">
				<div class="basicTabModern_top_left">
				<div class="basicTabModern_top_right">
					<div class="open_info_left">
						今天共有<span style="color:#d2ff00;">${userNum }</span>个用户使用客户端,占全部用户的<span style="color:#d2ff00;">${users }</span>。
					</div>
					<div class="open_info_right">
						<a onclick='top.Dialog.open({URL:"<%=path%>/business/Statistics.do?act=userlist",Title:" ",Width:1800,Height:800});'>点击查看更多 >></a>
					</div>
					<div class="clear"></div>
				</div>		
				</div>	
				</div>
				<div class="box2"  panelTitle="&nbsp;最新动态" showStatus="false" iconSrc="images/alert.png">
					<div id="newsBox">
						<table  class="tableStyle"  useHover="false" useClick="false" >
							<c:forEach items="${lm }" var="l">
								<%-- <li class="list_time"></li>
								<li class="list_title">${l.content}</li>
								<li class="list_more"><a href="javascript:;">详细 >></a></li>
								<div class="clear"></div> --%>
								<li class="list_time"></li>
								<li class="list_title">${l.content}</li>
								<li class="list_more">&nbsp;<a  href="javascript:;" title="查看" onclick="view('${l.Id}')">详细 >></a></li>
								<div class="clear"></div>
							</c:forEach>
		  	 			</table>
					</div>
				</div>
			</td>
			<td width="55%" class="ver01" >
				<div class="basicTabModern" selectedIdx="2" style="text-align: left;">
				     <table  class="tableStyle"  useHover="false" useClick="false" style="min-width: 300px;margin: 0px auto">
		  		<tr style="border: 0px">  
		  		<td  style="border: 0px">
		  			<div id="chartdiv" >
						<div class="flash_install">
							<div class="msg_icon3"></div>
								<div class="flash_install_con">
									您需要升级Flash播放器，
								<span class="forIE"><a href="../flash/flash_IE.exe">点击这里</a>安装</span>
								<span class="forFF"><a href="../flash/flash_FF.exe">点击这里</a>进行安装</span>。
								<br/>
								安装后请关闭浏览器再重新打开
							</div>
						</div>
					</div>
		  		</td>
		  	</tr>
		  	</tr>
		  	</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="basicTabModern" selectedIdx="2">
					<table  class="tableStyle"  useHover="false" useClick="false" >
					<tr >
						</th>
							<th width="3%" height="25"  align="center" class="DataTable_Field" title="唯一标识 * 类型：String 长度:32">序号</th>
							<th height="25"  align="center" class="DataTable_Field" title="订单号* 类型：String 长度:32">订单号</th>									 
							<th height="25"  align="center" class="DataTable_Field" title="用户id * 类型：String 长度:32">用户名</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="收货人电话 * 类型：String 长度:50">收货人</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="收货人 * 类型：String 长度:50">联系电话</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="城市 * 类型：String 长度:32">省-市-区、县</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="订单状态 * 类型：String 长度:50">订单状态</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="商品兑换积分 * 类型：int 长度:10">订单金额</th>								 
							<th height="25"  align="center" class="DataTable_Field" title="创建时间 * 类型：String 长度:50">创建时间</th>		
							<th width="3%" height="25"  align="center" class="DataTable_Field" title="操作* 类型：String 长度:50">操作</th>							 
		 			</tr>
					<c:forEach items="${poList }" var="ml" varStatus="s">
						<tr>	
							<td  align="left" class="DataTable_Field">${s.index+1 }</td>
							<td  align="left" class="td" title="${ml.orderCode }">${ml.orderCode }</td>
							<td  align="left" class="td" title="${ml.username }">${ml.username }</td>
							<td  align="left" class="td" title="${ml.consignee }">${ml.consignee }</td>
							<td  align="left" class="td" title="${ml.telephone }">${ml.telephone }</td>
							<td  align="left" class="td" title="${ml.proName }">${ml.proName }</td>
							<td  align="left" class="td" title="${ml.OrderState }">${ml.OrderState }</td>
							<td  align="left" class="td" title="${ml.price }">${ml.price }</td>
							<td  align="left" class="td" title="${ml.createtime }">${ml.createtime }</td>
							<td class="DataTable_Content" align="left" width="20%">
								<a href="javascript:;" title="详情"
									onclick="view1('${ml.orderId }')"> <span class="img_view"></span>
								</a>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</td>
		</tr>
	</table>
	</form>
	</div>
</body>
<script>
var data_xml = "<chart palette='1' baseFontSize='12' rotateYAxisName='0'>"+
<% if(data1.size()>0){
	for(int i=0;i<data1.size();i++){
		Map map=(Map)data1.get(i);
%>
"<set label='<%=map.get("KEY")%>' value='<%=map.get("VALUE")%>'/>"+
<%}}%>
"</chart>"
var chart1 = new FusionCharts("<%=path%>/charts/FusionCharts/Charts/Line.swf", "ChartId", "950", "260", "0", "0");
chart1.setDataXML(data_xml);	
chart1.render("chartdiv");

</script>
</html>

