<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.MatchedPapers.MatchedPapersActionForm"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <!-- 异步上传图片 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
    <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <!--3.3框架必需end-->

</head>
<body class="ali02">
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right" align="left">
                    <span>当前位置：四级激活码导入<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <div style="" align="center">
         <form action="<%=path%>/business/BookActivationCode.do?act=fourCodeByExcel" id="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
             <%--<!--  useTime实际为batch   -->--%>
             <table>
                 <tr>
                     <td>批次：</td>
                     <td><input type="text" name="useTime" id="userTime" /></td>
                 </tr>
                 <tr>
                    <td>文件：</td>
                     <td> <input type="file" id="formfile" name="formfile" /></td>
                 </tr>
             </table>
         </form>
    </div>
    <!-- ---- -->
    <table class="tableStyle"
           style="width: 400px; margin: 0px auto; border: none"
           formMode="true">
        <tr>
            <td colspan="4" style="border: none;">
                <input type="button" value=" 提 交 " onclick="ajaxFileExport()"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" value=" 关 闭 " onclick="back()" />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
    </table>
    <div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    <br />
    <br />
</div>
</body>
<script language="javascript" type="text/javascript">


    function back(){
        top.Dialog.close();
    }


    function ajaxFileExport(){
        $.ajaxSetup({async:false});
        if($("#useTime").val() == ""){
            top.Dialog.alert("批次不可以为空！");
            return;
        }
        if($("#formfile").val() == ""){
            top.Dialog.alert("文件不可以为空！");
            return;
        }
        var path = $("#formfile").val();
        var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".XLS" && ext != ".XLSX" && ext != ".TXT") {
            alert("请上传正确格式表单");
            return;
        }
//        $("#scrollContent").mask("表单正在提交...");
        if(ext == ".XLS" || ext == ".XLSX"){
            formAction.action="<%=path%>/business/BookActivationCode.do?act=fourCodeByExcel";
        }else{
            formAction.action="<%=path%>/business/BookActivationCode.do?act=fourCodeByTxt";
        }
        formAction.submit();
        setTimeout(function () {
            window.location.href="<%=path%>/system/BookActivationCode/export_progress4.jsp";
        },1000)
    }

</script>


</html>
