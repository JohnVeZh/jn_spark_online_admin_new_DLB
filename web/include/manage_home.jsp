<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.easecom.common.util.*" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

List companyResult = (List)request.getAttribute("companyResult");
List ourCompanyResult = (List)request.getAttribute("ourCompanyResult");

SessionContainer sessionContainer = (SessionContainer)session.getAttribute("SessionContainer");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/skins/sky/import_skin.css" rel="stylesheet" type="text/css" id="skin" themeColor="blue" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path %>/js/bsFormat.js"></script>
<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
<!--框架必需end-->
		
<!--图表脚本start-->
<script language="JavaScript" src="<%=path %>/charts/FusionCharts/JSClass/FusionCharts.js"></script>
<!--图表脚本end-->

<!-- tab页 -->
<script type="text/javascript" src="<%=path %>/js/nav/switchable.js"></script>

	</head>
	<body>
	<div id="scrollContent" class="border_gray">
		<table class="tableStyle" useColor=false useHover=false useClick=false>
			<tr align="center">
				<td width="50%">
				<div id="companyresult"> 
				    <div class="flash_install">
						<div class="msg_icon3"></div>
						<div class="flash_install_con" >
						您需要升级Flash播放器，<span class="forIE"><a href="../flash/flash_IE.exe">点击这里</a>安装</span>
						<span class="forFF"><a href="../flash/flash_FF.exe">点击这里</a>进行安装</span>。
						<br />安装后请关闭浏览器再重新打开
						</div>
					</div>
				</div>
				</td>
				<td width="50%">
				<div id="personlist"> 
				    <div class="flash_install">
						<div class="msg_icon3"></div>
						<div class="flash_install_con" >
						您需要升级Flash播放器，<span class="forIE"><a href="../flash/flash_IE.exe">点击这里</a>安装</span>
						<span class="forFF"><a href="../flash/flash_FF.exe">点击这里</a>进行安装</span>。
						<br />安装后请关闭浏览器再重新打开
						</div>
					</div>
				</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<IFRAME scrolling="no" width="100%" frameBorder=0 id=myorderlist 
							name=myorderlist onload="iframeHeight('myorderlist')" src="<%=path %>/base/home.do?act=myOrderList"
							allowTransparency="true"></IFRAME>
				</td>
				<td>
				<IFRAME scrolling="no" width="100%" frameBorder=0 id=resultlist 
							name=resultlist onload="iframeHeight('resultlist')" src="<%=path %>/base/home.do?act=resultList&companyId=<%=sessionContainer.getTopOrgId() %>"
							allowTransparency="true"></IFRAME>
				
				</td>
			</tr>
		</table>
	</div>
		
	</body>
</html>

<script language="javascript">
var char1 =  "<chart palette='2' caption='分公司业绩' baseFontSize='12' rotateNames='0' xAxisName='公司'" 
		    +" yAxisName='订单量' showValues='0' decimals='0' formatNumberScale='0'  labelDisplay='NONE'>" 
		    
		    <%
		    	if(null!=companyResult && companyResult.size()>0){
		    		for(int i=0; i<companyResult.size(); i++){
		    			Map m = (Map)companyResult.get(i);
		    %>
		    		+"<set label='<%=m.get("NAME")%>' value='<%=m.get("RESULT")%>' />"
		    <%
		    		}
		    	}
		    %>
			+"</chart>" ;

var chart1 = new FusionCharts("../charts/FusionCharts/Charts/Column2D.swf", "ChartId", "500", "300", "0", "0");
chart1.setDataXML(char1);		   
chart1.render("companyresult");


var char2 =  "<chart palette='2' caption='本公司业绩排行榜' baseFontSize='12' rotateNames='0' xAxisName='人员'" 
		    +" yAxisName='订单金额' showValues='0' decimals='0' formatNumberScale='0' >" 
		    <%
		    	if(ourCompanyResult!=null && ourCompanyResult.size()>0){
		    		for(int i=0; i<ourCompanyResult.size(); i++){
		    			Map m = (Map)ourCompanyResult.get(i);
		    %>
		    		+"<set label='<%=m.get("NAME")%>' value='<%=m.get("RESULT")%>' />"
		    <%
		    		}
		    	}
		    %>
		    
			+"</chart>" ;

var chart2 = new FusionCharts("../charts/FusionCharts/Charts/Column2D.swf", "ChartId", "500", "300", "0", "0");
chart2.setDataXML(char2);		   
chart2.render("personlist");
</script>