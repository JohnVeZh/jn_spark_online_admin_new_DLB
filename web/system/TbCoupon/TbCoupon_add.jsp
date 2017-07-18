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
    <!--tap页start-->
    <script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
    <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <!--3.3框架必需end-->
    <!-- 日期选择框start -->
    <script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
    <!-- 日期选择框end -->
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
                    <span>当前位置：新增优惠码<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" action="<%=path%>/business/TbCoupon.do?act=add" method="post" id="listForm" >
        <div class="box1 center" whiteBg="true" id="form1">
            <fieldset>
                <legend>优惠券基本信息</legend>
                <table class="tableStyle" transMode="true" footer="normal">
                    <tr>
                        <td width="15%" align="right">
                            优惠标题：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            <input type="text" name="title" maxNum="100" style="width:95%" class="validate[required] iptClass" />
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            优惠券类型：
                        </td>
                        <td width="35%" align="left">
                            <input type="radio" value="1" checked="checked" name="type" onclick="ntChk(1)"/>折扣券
                            <input type="radio" value="2"  name="type" onclick="ntChk(2)" />现金券
                        </td>
                        <td width="15%" align="right" >
                            折扣率：
                        </td>
                        <td width="35%" align="left">
                            <input type="text"  name="discountRate" id="discountRate" class="sz" />
                        </td>
                    </tr>

                    <tr >
                        <td width="15%" align="right">
                            满额：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="minMoney" id="minMoney" class="sm" />
                        </td>
                        <td width="15%" align="right">
                            减额：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="discountMoney" id="discountMoney" class="sm" />
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            优惠券状态：
                        </td>
                        <td width="35%" align="left">
                            <input type="radio" value="0" checked="checked" name="status" />启用
                            <input type="radio" value="1" name="status" />停用
                        </td>
                        <td width="15%" align="right">
                            使用次数：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" watermark="无限制"  name="maxUseNum" id="maxUseNum" />
                        </td>

                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            关联类型：
                        </td>
                        <td width="35%" align="left">
                            <input type="radio" value="1" name="relationType" onclick="" />非全场
                            <input type="radio" value="2" name="relationType" />全场网课
                            <input type="radio" value="3" name="relationType" />全场图书
                            <input type="radio" value="4"  checked="checked" name="relationType" />全场通用
                        </td>
                        <td width="15%" align="right">
                            优惠券有效期
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="startTime" id="startTime" class="date" style="width: 120px;" dateFmt="yyyy-MM-dd"/> —
                            <input type="text" name="endTime" id="endTime" class="date" style="width: 120px;" dateFmt="yyyy-MM-dd"/>
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
                        <input type="reset" value=" 关闭 " onclick="back()" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示: 1、《使用次数》如果不限制次数使用，不填即可；2、《关联类型》：“非全场”需优惠券提交后另行关联，其他类型则无需再次关联<!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
            <br />
            <br />
        </div>
    </form>
</div>
<%--<div id="relevanceDiv">--%>
    <%--<div class="basicTab">--%>
        <%--<div name="关联图书" style="width:400px;height:200px;">--%>

        <%--</div>--%>
        <%--<div name="关联网课" style="width:400px;height:200px;">--%>

        <%--</div>--%>
    <%--</div>--%>

<%--</div>--%>
</body>
<script language="javascript" type="text/javascript">


    function back(){
        top.Dialog.close();
    }

    function sub(){
        var maxUseNum = $("#maxUseNum").val();
        var discountRate = $("#discountRate").val();
        var minMoney = $("#minMoney").val();
        var discountMoney = $("#discountMoney").val();
        if(discountMoney != ""&& discountMoney !=null){
            if(!isNaN(discountMoney) ){
                if(discountMoney < 0){
                    top.Dialog.alert("减额必须输入0及以上的数字！");
                    return;
                }
            }else{
                top.Dialog.alert("减额必须输入0及以上的数字！");
                return;
            }
        }
        if(minMoney != ""&& minMoney !=null){
            if(!isNaN(minMoney) ){
                if(minMoney < 0){
                    top.Dialog.alert("满额必须输入0及以上的数字！");
                    return;
                }
            }else{
                top.Dialog.alert("满额必须输入0及以上的数字！");
                return;
            }
        }
        if(discountRate != ""&& discountRate !=null){
            if(!isNaN(discountRate) ){
                if(discountRate < 0||discountRate>1){
                    top.Dialog.alert("折扣率必须输入0-1之间的两位小数");
                    return;
                }
            }else{
                top.Dialog.alert("折扣率必须输入0-1之间的两位小数");
                return;
            }
        }
        if(maxUseNum != null && maxUseNum != ""&& maxUseNum != "无限制"){
            if(!isNaN(maxUseNum) ){
                if(maxUseNum < 0){
                    top.Dialog.alert("请输入正整数！");
                    return;
                }
            }else{
                top.Dialog.alert("请输入正整数！");
                return;
            }
        }else{
            maxUseNum = -1;
        }

        $("#scrollContent").mask("表单正在提交...");
        $('#listForm').ajaxSubmit(function(data){
            if(data.result){
                top.frmright.window.location.reload();
                top.Dialog.close();
            }
        })
    }


    $(function(){
        ntChk(1);
        initDate();
    });
    function initDate() {
        var dt = new Date();
        var year = dt.getFullYear();
        var month = dt.getMonth() + 1;
        var day = dt.getDate();
        if(month<10){
            month = "0"+month;
        }
        if(day <10){
            day= "0"+day;
        }
        var time = year +"-"+month+"-"+day;
        $("#startTime").val(time);
        $("#endTime").val(time);
    }
    //判断满减还是折扣
    function ntChk(value){
        if(value==2){
            //隐藏状态
            $(".sz").attr("disabled","true");
            $(".sm").removeAttr("disabled");
            $(".sz").val("");
        }else{
            //隐藏状态
            $(".sm").attr("disabled","true");
            $(".sz").removeAttr("disabled");
            $(".sm").val("");

        }
    }
//    //关联类型
//    function reChk(value){
//        if(value==1) {
//            top.Dialog.open({
//                InnerHtml: $("#relevanceDiv").html(),//这里还可以直接写html代码
//                Title:"关联产品页"
//            });
//        }
//    }

</script>


</html>
