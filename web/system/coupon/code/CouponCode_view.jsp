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
    <script type="text/javascript" src="<%=path%>/libs/js/lister.js"></script>
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

</head>
<body class="ali02">
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right" align="left">
                    <span>当前位置：修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" action="<%=path%>/business/CouponCode.do?act=codeUpdate" method="post" id="listForm" >
        <div class="box1 center" whiteBg="true" id="form1">
            <input type="hidden" name="id" value="${CouponCodeForm.id }" >
            <input type="hidden" name="createTime" value="${CouponCodeForm.createTime }" >
            <fieldset>
                <legend>基本信息</legend>
                <div class="lister" style="margin: 15px auto" id="lister" selectedValue="${templateList}" url="<%=path%>/business/CouponCode.do?act=getTemplate4DoubleSelect" disabled="true" fromTitle="未选模版" toTitle="已选模版" listerWidth="400"></div>
                <table class="tableStyle" transMode="true" footer="normal">

                    <tr>
                        <td width="15%">
                            优惠码：
                        </td>
                        <td width="35%">
                            ${CouponCodeForm.code}
                        </td>
                    </tr>
                    <tr>
                        <td width="15%">
                            状态：
                        </td>
                        <td width="35%" style="text-align: center">
                            <c:choose>
                                <c:when test="${CouponCodeForm.status=='1' }">
                                    可用
                                </c:when>
                                <c:when test="${CouponCodeForm.status=='2' }">
                                   已使用
                                </c:when>
                                <c:otherwise>
                                    失效
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%">
                            生效时间：
                        </td>
                        <td width="35%">
                            <fmt:formatDate value="${CouponCodeForm.effectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%">
                            失效时间：
                        </td>
                        <td width="35%">
                            <fmt:formatDate value="${CouponCodeForm.invalidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
                        <input type="reset" value=" 关 闭 " onclick="back()" />
                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示:</br></div>
            <br />
            <br />
        </div>
    </form>
</div>
<script>
    function back(){
        top.Dialog.close();
    }
</script>
</body>
</html>
