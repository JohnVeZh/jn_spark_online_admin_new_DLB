<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>sysfunctionfrm.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

</head>

<frameset id="sysfunctionfrm" cols="20%,*" framespacing="1"
	frameborder="yes" bordercolor="#CCCCCC" MARGINWIDTH="0"
	MARGINHEIGHT="0" LEFTMARGIN="0" TOPMARGIN="0">
	<frame src="<%=path%>/business/JCommonWords.do?act=treelist" id="left" name="leftFrame"
		scrolling="auto" frameSpacing=0 marginHeight=0 frameBorder=no
		LEFTMARGIN="0" TOPMARGIN="0" />
	<frame name="main" id="main" frameborder="no" 
		src="<%=path%>/business/JCommonWords.do?act=list" />
</frameset>
<noframes></noframes>
<body>
	
</body>
</html:html>
