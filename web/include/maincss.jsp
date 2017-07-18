<%@ page import="com.easecom.common.util.SessionContainer" %>
<%
 /* SessionContainer sessionContainer = (SessionContainer)session.getAttribute("SessionContainer");
  String skin = "";
  if(sessionContainer==null){
	  skin= "normal";
  }else{
      skin = sessionContainer..equals("")?"normal":sessionContainer.getSkinpath(); 
  }*/
  String skin= "normal";
String contextPath=request.getContextPath();
%>
<link href="<%=contextPath%>/style/<%=skin%>/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="<%=contextPath%>/style/<%=skin%>/js/poslib.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/style/<%=skin%>/js/scrollbutton.js"></script>
<script type="text/javascript" language="javascript" src="<%=contextPath%>/style/<%=skin%>/js/menu.js"></script>
<script type="text/javascript" src="<%=contextPath%>/style/<%=skin%>/js/prototype.js"/></script>
<script type="text/javascript" language="javascript">
	Menu.prototype.cssFile = "<%=contextPath%>/style/<%=skin%>/css/menu.css";
	var menuItemIcon = "<%=contextPath%>/style/<%=skin%>/images/menu.gif";
</SCRIPT>
