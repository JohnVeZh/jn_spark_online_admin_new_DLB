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
            <input type="hidden" name="create_time" value="${CouponCodeForm.createTime }" >
            <input type="hidden" name="template" id="template">
            <fieldset>
                <legend>查询条件</legend>
                <div style="margin: 0 auto">
                    模版标题：<input type="text" id="search"/> <input type="button" id="searchTemplate" value="查询"/>
                </div>
                <%--<div class="lister" style="margin: 15px auto" id="lister1" url="<%=path%>/ceshi.json" fromTitle="未选模版" toTitle="已选模版" listerWidth="400"></div>--%>
                <div class="lister" style="margin: 15px auto" id="lister" selectedValue="${templateList}" url="<%=path%>/business/CouponCode.do?act=getTemplate4DoubleSelect" fromTitle="未选模版" toTitle="已选模版" listerWidth="400"></div>
                <table class="tableStyle" transMode="true" footer="normal">

                    <tr>
                        <td width="15%">
                            优惠码：
                        </td>
                        <td width="35%">
                            <input type="text" name="code" class="validate[required] iptClass" value="${CouponCodeForm.code}" readonly/>
                            <span class="star">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%">
                            状态：
                        </td>
                        <td width="35%" style="text-align: center">
                            <select name="status" >
                                <option value="1" <c:if test="${CouponCodeForm.status=='1' }">selected="selected"</c:if>>可用</option>
                                <option value="2" <c:if test="${CouponCodeForm.status=='2'  }">selected="selected"</c:if>>已使用</option>
                                <option value="3" <c:if test="${CouponCodeForm.status=='3'  }">selected="selected"</c:if>>失效</option>
                            </select>
                            <span class="star">* </span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%">
                            生效时间：
                        </td>
                        <td width="35%">
                            <input  class="date" type="text" name="effect_time" id="effect_time" dateFmt="yyyy-MM-dd HH:mm:ss" value="<fmt:formatDate value="${CouponCodeForm.effectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                            <span class="star">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%">
                            失效时间：
                        </td>
                        <td width="35%">
                            <input  class="date" type="text" name="invalid_time" id="invalid_time" dateFmt="yyyy-MM-dd HH:mm:ss"  value="<fmt:formatDate value="${CouponCodeForm.invalidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                            <span class="star">* </span>
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
    $(function () {
        var $listerVal = $("#lister");
        $('#searchTemplate').click(function (e) {

            var searchVal = $('#search').val();
            $.get('<%=path%>/business/CouponCode.do?act=getTemplate4DoubleSelect',{title:searchVal},function (result) {
                $listerVal.data("data",result);
                $listerVal.render();
            },'json');
        });
    });
    function back(){
        top.Dialog.close();
    }
    function sub(){
        var listerVal = $("#lister").attr("relValue");
        if(!listerVal){
            top.Dialog.alert("请选择模版！");
            return;
        }
        $('#template').val(listerVal);
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
