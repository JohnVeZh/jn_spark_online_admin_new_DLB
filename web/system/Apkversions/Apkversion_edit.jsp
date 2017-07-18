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
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <style>
        .mainCon {
            position: relative;
            display: inline-block;
            z-index: 500;
            float: none;
        }
    </style>
    <!--3.3框架必需end-->
    <script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
    <!-- 表单验证start -->
    <script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
    <script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
    <!-- 表单验证end -->
    <!-- 异步上传 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
    <script>
        $(function () {

        });
    </script>

</head>
<body class="ali02">
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right" align="left">
                    <span>当前位置：版本修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" action="<%=path%>/business/AppVersions.do?act=update" method="post" id="listForm" >
        <div class="box1 center" whiteBg="true" id="form1">
            <input type="hidden" name="id" value="${apk.id }" >
            <fieldset>
                <legend>基本信息</legend>
                <table class="tableStyle" transMode="true" footer="normal">

                    <tr>
                        <td width="30%" style="text-align:right;">
                            APP类型：
                        </td>
                        <td width="70%" style="text-align:left;">
                        	<%-- 
                        	<label>
                        		<input type="radio" name="type" value="0" <c:if test="${apk.type==0}">checked="checked"</c:if> />Android
                        	</label>
                        	<label>
                        		<input type="radio" name="type" value="1" <c:if test="${apk.type==1}">checked="checked"</c:if> />iOS
                        	</label>
                            <span class="star">* </span>
                        	 --%>
                            <input type="hidden" name="type" value="${apk.type }" />
		                	<c:if test="${apk.type == 0}">Android</c:if>
		                	<c:if test="${apk.type == 1}">iOS</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                            应用名称：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input type="text" id="apkname" name="apkname" value="${apk.apkname }" class="validate[required] iptClass" style="width:80%;" />
                            <span class="star">* </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                            版本号：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input type="text" id="apkversion" name="apkversion" value="${apk.apkversion }" class="validate[required] iptClass" style="width:80%;" />
                            <span class="star">* </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                            版本名称：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input type="text" id="versionName" name="versionName" value="${apk.versionName }" class="validate[required] iptClass" style="width:80%;" />
                            <span class="star">* </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                            文件大小：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input type="text" id="filesize" name="filesize" value="${apk.filesize }" class="validate[required] iptClass" style="width:80%;" />
                            <span class="star">* </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                            更新时间：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input  class="date" type="text" name="updatetime" id="updatetime"  class="validate[required] iptClass" value="${apk.updatetime}" style="width:80%;" />
                            <span class="star">* </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                           商店地址：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input type="text" id="apkurl" name="apkurl" class="validate[required] iptClass" value="${apk.apkurl}" style="width:80%;"/>
                            <span class="star">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                           下载地址：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <input type="text" id="downloadUrl" name="downloadUrl" value="${apk.downloadUrl}" style="width:80%;"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" style="text-align:right;">
                            更新说明：
                        </td>
                        <td width="70%" style="text-align:left;">
                            <textarea id="remark" name="remark" style="height:100px; width:80%;">${apk.remark}</textarea>
                            <span class="star"> * </span>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <!-- ---- -->
            <table class="tableStyle"
                   style="width: 800px; margin: 0px auto; border: none"
                   formMode="true">
                <tr>
                    <td colspan="4" style="border: none;">
                        <input type="button" value=" 提 交 " onclick="sub()"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value=" 重 置 " />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value=" 关 闭 " onclick="back()" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示:</br></div>
            <br />
            <br />
        </div>
    </form>
</div>
</body>
<script language="javascript" type="text/javascript">

    function back(){
        top.Dialog.close();
    }
    function sub(){
        $("#scrollContent").mask("表单正在提交...");
        $('#listForm').ajaxSubmit(function(data){
            if(data.result){
                top.frmright.window.location.reload();
                top.Dialog.close();
            }
        })
    }

</script>


</html>
