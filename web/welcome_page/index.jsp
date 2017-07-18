<%@ page language="java" pageEncoding="utf-8"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String myerr = (String)request.getAttribute("myerrors");
myerr = (null == myerr)?"":myerr; 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<head>
    <meta charset="utf-8">
    <title>星火集团统一管理中心</title>
    <link rel="stylesheet" href="<%=path %>/welcome_page/css/style.css" type="text/css">
</head>

<body>
<div class="main3">
    <div class="logo"><img src="<%=path %>/welcome_page/images/logo_03.png" alt="" /></div>
    <div class="txt"> 星火英语，致力于简化中国人的英语学习，聚力改善中国英语教育。</div>
    <div class="u-img">
        <div class="u-img1">
            <a href="<%=path %>/login_log/login.jsp">统一日志管理系统</br>
                （集团版）</a>
        </div>
        <div class="u-img2">
            <a href="<%=path %>/login_user/login.jsp">统一用户管理系统</br>
                （集团版）</a>
        </div>
        <div class="u-img3">
            <a href="<%=path %>/login_question/login.jsp">统一题库管理系统</br>
                （集团版）</a>
        </div>
        <div class="u-img4">
            <a href="<%=path %>/login/login.jsp">星火在线管理系统</br>
                （大英）</a>
        </div>
    </div>
    <div class="footer">Copyright © 2016 All Rights Reserved 山东星火国际传媒集团有限公司</br>
        京ICP证070017号 - 京ICP备08105196号</div>
</div>



</body>
</html>
