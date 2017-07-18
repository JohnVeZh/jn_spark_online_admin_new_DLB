<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.base.model.*" %>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	List data1=(List)request.getAttribute("list1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html:html lang="true" xhtml="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	<script language="JavaScript" src="<%=path %>/charts/FusionCharts/JSClass/FusionCharts.js"></script>
	<link rel="stylesheet" type="text/css" id="skin"/>
</head>
<body style="overflow-y:hidden;">
	  	<div class="position">
		<div class="center">
		<div class="left">
		<div class="right ali01">
			<span>当前位置：接口分析</span>
		</div>	
		</div>	
		</div>
	</div>		
	<div id="scrollContent" class="border_gray">
		<fieldset> 
		  	<legend>接口分析</legend>
		  	<!-- ------ -->
		  	<table  class="tableStyle"  useHover="false" useClick="false" style="max-width: 400px;min-width: 400px;margin: 0px left">
		  	<tr style="border: 0px">
		  		<td  style="border: 0px" align="left">
		  			<span style="color:red">app页面点击率（次）</span>
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
</div>
</body>
<script>
//////////////////////////
var data_xml = "<chart palette='1' baseFontSize='12' rotateYAxisName='0'>"+
<% if(data1.size()>0){
	for(int i=0;i<data1.size();i++){
		Map map=(Map)data1.get(i);
%>
"<set label='<%=map.get("NAME")%>' value='<%=map.get("NUM")%>' />"+
<%}}%>
"</chart>"
var chart1 = new FusionCharts("<%=path%>/charts/FusionCharts/Charts/Bar2D.swf", "ChartId", "1500", "3000", "0", "0");
chart1.setDataXML(data_xml);	
chart1.render("chartdiv");

</script>
</html:html>