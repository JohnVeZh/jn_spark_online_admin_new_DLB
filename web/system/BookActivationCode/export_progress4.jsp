<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>" />
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <!--3.3框架必需start-->
    <script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
    <script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
    <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <title>Title</title>
</head>
<script language="JavaScript" type="application/javascript">
    $(function () {
        document.getElementById("progress").scrollTop = document.getElementById("progress").scrollHeight;
    })
    function reload () {
        $('#progress').attr('src', $('#progress').attr('src')+"?"+Math.random());
        document.getElementById("progress").scrollTop = document.getElementById("progress").scrollHeight;
    }

</script>
<body>
<div class="box1">
    <input type="button"  value="刷新" onclick="reload()">
</div>
<iframe id="progress" src="<%=path%>/error/error4.txt" style="width: 380px;height:150px; "></iframe>
</body>
</html>
