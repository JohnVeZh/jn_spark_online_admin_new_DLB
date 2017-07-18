<%@ page import="com.easecom.common.util.SessionContainer" %>
<%
  String contextPath=request.getContextPath();
  SessionContainer sessionContainer1 = (SessionContainer)session.getAttribute("SessionContainer");
  String skin = "normal"; 
%>
<%-- <link href="<%=contextPath %>/style/<%=skin%>/css/tab.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath %>/style/<%=skin%>/css/sorttable.css" rel="stylesheet" type="text/css"> --%>
<link href="<%=contextPath %>/style/<%=skin%>/css/xtree.css" rel="stylesheet" type="text/css">
<%-- <link href="<%=contextPath %>/style/<%=skin%>/css/page.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript">
   var contextPath='<%=contextPath%>';
</script>
<script type="text/javascript">   
document.onkeydown = check;   
function check(e) {   
    var code;   
    if (!e) var e = window.event;   
    if (e.keyCode) code = e.keyCode;   
    else if (e.which) code = e.which;   
    var obj = e.srcElement?e.srcElement:e.target;   
    var keycode = e.keyCode?e.keyCode:e.which;   
    if (((keycode == 8) &&   //BackSpace    
         ((obj.type != "text" &&    
         obj.type != "textarea" &&    
         obj.type != "password") ||    
         obj.readOnly == true)) ||    
        ((e.ctrlKey) && ((keycode == 78) || (keycode == 82)) ) ||  
        (keycode == 116) ) {
        if(window.event){   
            event.keyCode = 0;    
            event.returnValue = false;    
        }else{   
            e.preventDefault();   
        }   
    }   
    return true;   
}   
</script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/tabpane.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/datetime.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/input.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/select.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/sorttable.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/validate.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/xtree.js" ></script>
 
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/export.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/cookie.js" ></script>
<script type="text/javascript" language="javascript" src="<%=contextPath %>/style/<%=skin%>/js/print.js" ></script>
 
<%-- <script type="text/javascript" src="<%=contextPath %>/style/<%=skin%>/js/prototype.js"/></script> --%>

<%-- <style type="text/css">.mxxCalendar{behavior: url(<%=contextPath %>/style/<%=skin%>/js/MxxCalendar.htc);border-bottom: 0px;border-left: 0px;border-right: 0px;border-top: 0px}</style>
<style type="text/css">.mxxCalendar1{behavior: url(<%=contextPath %>/style/<%=skin%>/js/MxxCalendar1.htc);}</style>
<link href="<%=contextPath %>/style/<%=skin%>/css/style-gbk.css" rel="stylesheet" type="text/css"> --%>