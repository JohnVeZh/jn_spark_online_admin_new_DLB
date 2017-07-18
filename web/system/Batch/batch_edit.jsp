<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <!--3.3框架必需end-->

    <!-- 表单验证start -->
    <script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
    <script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
    <!-- 表单验证end -->
    <!-- 异步上传 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

</head>
<body class="ali02">
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right" align="left">
                    <span>当前位置：批次修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" action="<%=path%>/business/Batch.do?act=add" method="post" id="listForm" >
        <input id="id" name="id" type="hidden"/>
        <div class="box1 center" whiteBg="true" id="form1">
            <fieldset>
                <legend>基本信息</legend>
                <table class="tableStyle" transMode="true" footer="normal">
                    <tr>
                        <td width="15%" align="right" >
                            批次名称：
                        </td>
                        <td width="35%" align="left" >
                            <input type="text" name="name" id="name" class="validate[required] iptClass"  />
                        </td>
                    </tr>
                </table>
            </fieldset>
            <!-- ---- -->
            <table class="tableStyle"
                   style="width: 320px; margin: 0px auto; border: none"
                   formMode="true">
                <tr>
                    <td colspan="4" style="border: none;">
                        <input type="button" value=" 提 交 " onclick="checkIsExists()"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value=" 关  闭 " onclick="back()" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
            <br />
            <br />
        </div>
    </form>
</div>
</body>
<script language="javascript" type="text/javascript">
setTimeout(function () {
    var id = GetQueryString("id");
    var name = decodeURIComponent(GetQueryString("name")) ;
    $("#id").val(id);
    $("#name").val(name);
},200);

    function back(){
        top.Dialog.close();
    }
    //验证该批次是否已经存在
//验证该批次是否已经存在
function checkIsExists() {
    $("#scrollContent").mask("数据提交...");
    listForm.action="/business/Batch.do?act=isExists";
    $('#listForm').ajaxSubmit(function(data){
        var data = eval(data);
        if(data.RES > 0){
            $("#scrollContent").unmask();
            var tip = "该批次名称已经存在";
            top.Dialog.alert(tip);
            return;
        }else if(data.RES < 0){
            $("#scrollContent").unmask();
            var tip = "程序异常，请稍后再试！";
            top.Dialog.alert(tip);
            return;
        }
        sub();
    })
}
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }
    function sub(){
        listForm.action="/business/Batch.do?act=update";
        $('#listForm').ajaxSubmit(function(data){
            var data = eval(data);
            if(data.res){
                var tip = "修改成功";
                $("#scrollContent").unmask();
                top.Dialog.alert(tip);
                reload("Batch.do?act=list");
            }
        })
    }


</script>


</html>
