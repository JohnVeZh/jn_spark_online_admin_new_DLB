<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.base.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	List data1=(List)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html:html lang="true" xhtml="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--3.3框架必需start-->
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	<script language="JavaScript" src="<%=path %>/charts/FusionCharts/JSClass/FusionCharts.js"></script>
	<link rel="stylesheet" type="text/css" id="skin"/>
 <!--3.3框架必需end-->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
</head>
<body style="overflow-y:hidden;">
	  	<div class="position">
		<div class="center">
		<div class="left">
		<div class="right ali01">
			<span>当前位置：激活量统计</span>
		</div>	
		</div>	
		</div>
	</div>
	<div id="scrollContent" class="border_gray">
	<form name="listForm" scope="request" action="<%=path%>/business/Statistics.do?act=userbook" method="post" id="listForm">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table width="100%">
					<tr>
						<td>
						起始时间：<input  class="date" type="text" name="beginTimes" value="${beginTimes }" id="beginTimes"  />-<input  class="date" type="text" name="endTimes" value="${endTimes }" id="endTimes"  />
							<input type="submit"   value="查询"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<fieldset> 
		  	<legend>激活量</legend>
		  	<!-- ------ -->
		  	<table  class="tableStyle"  useHover="false" useClick="false" style="max-width: 400px;min-width: 400px;margin: 0px left">
		  	<tr style="border: 0px">
		  		<td  style="border: 0px">
		  			 <span style="color:red">激活总量：${userNum}个</span>
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
				<!-- --------------- -->
		  		</td>
		  	</tr>
		  	</table>
	  	</fieldset>
	  	<table  class="tableStyle"  useHover="false" useClick="false" >
  					<tr>
					 	<th  height="25" align="center" class="DataTable_Field">
							序号
						</th>
						<th  height="25" align="center" class="DataTable_Field">
							激活码
						</th>
						<th  height="25" align="center" class="DataTable_Field">
							激活时间
						</th>
						<th  height="25" align="center" class="DataTable_Field">
							使用次数
						</th>
					</tr>
					<c:forEach items="${li }" var="ml" varStatus="s">
						<tr>
							<td  align="left">${s.index+1}</td>
							<td  align="left">${ml.code }</td>
							<td  align="left">${ml.useTime }</td>
							<td  align="left">${ml.num }</td>
						</tr>
					</c:forEach>
  	 	</table>
</div>
</body>
<script>
//////////////////////////
var data_xml = "<chart caption='本月激活量' palette='1' baseFontSize='12' rotateYAxisName='0'>"+
<% if(data1.size()>0){
	for(int i=0;i<data1.size();i++){
		Map map=(Map)data1.get(i);
%>
"<set label='<%=map.get("KEY")%>' value='<%=map.get("VALUE")%>' />"+
<%}}%>
"</chart>"
var chart1 = new FusionCharts("<%=path%>/charts/FusionCharts/Charts/Column3D.swf", "ChartId", "1500", "400", "0", "0");
chart1.setDataXML(data_xml);	
chart1.render("chartdiv");

function changeMonth(){
	$('#endTimes').val(null);
	$('#beginTimes').val(null);
}
function changeTime(){
	$('#month').val(null);
}
</script>
</html:html>