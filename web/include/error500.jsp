<%@ page contentType="text/html; charset=UTF-8" %>

<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.aa {
	text-align: center;
	color:#F00;
	font-family:"微软雅黑";
}
</style>
 
</head><body >
<div id="zDialogCon">
<div class="aa" ><img src="<%=path%>/system/images/error500.jpg" /></div>
<div  class="aa" ><span >抱歉：服务器内部异常，请联系正在加班的程序员。</span></div>
</div>
</body></html>