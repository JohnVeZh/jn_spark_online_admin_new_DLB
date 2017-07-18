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
                    <span>当前位置：新增优惠券模板</span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" action="<%=path%>/business/coupon.do?act=templateAdd" method="post" id="listForm" >
        <div class="box1 center" whiteBg="true" id="form1">
            <fieldset>
                <legend>基本信息</legend>
                <table class="tableStyle" transMode="true" footer="normal">
                    <tr>
                        <td width="15%" align="right">
                            	模板名称：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            <input type="text" name="title" id="title" style="width:80%" maxlength="8"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	模板类型：
                        </td>
                        <td width="35%" align="left">
                            <input type="radio" name="type" value="1" />图书网课
                        	<input type="radio" name="type" value="2" />其他
                        </td>
                        <td width="15%" align="right">
                            	模板状态：
                        </td>
                        <td width="35%" align="left">
                            <input type="radio" name="status" value="1" checked="checked" />正常
                        	<input type="radio" name="status" value="0" />停用
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	模板生效时间：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="effectTimeStr" id="effectTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td width="15%" align="right">
                            	模板失效时间：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="invalidTimeStr" id="invalidTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	优惠类型：
                        </td>
                        <td width="35%" align="left">
                            <input type="radio" name="discountType" value="1" />满减
                            <input type="radio" name="discountType" value="2" />折扣
                        </td>
                        <td width="15%" align="right" >
                            	折扣率：
                        </td>
                        <td width="35%" align="left">
                            <input type="text"  name="discountRate" id="discountRate"/>
                        </td>
                    </tr>
                    <tr >
                        <td width="15%" align="right">
                            	满额：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="minMoney" id="minMoney"/>
                        </td>
                        <td width="15%" align="right">
                           	 	减额：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="discountMoney" id="discountMoney"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	有效期限：
                        </td>
                        <td width="35%" align="left">
                            <input type="text" name="effectPeriod" id="effectPeriod" />天
                        </td>
                        <td width="15%" align="right">
                            	商品类型：
                        </td>
                        <td width="35%" align="left">
                        	<input type="radio" name="productType" value="1" />图书
                            <input type="radio" name="productType" value="2" />网课
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	商品名称：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            <input type="hidden" name="productId" id="productId" />
                            <input type="text" name="productName" id="productName" readonly="readonly" style="width:80%; cursor:pointer;" />
                        </td>
                    </tr>
                </table>
            </fieldset>

            <!-- ---- -->
            <table class="tableStyle" style="width: 800px; margin: 0px auto; border: none" formMode="true">
                <tr>
                    <td colspan="4" style="border: none;">
                        <input type="button" value=" 提 交 " onclick="sub()"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" value=" 关闭 " onclick="javascript:top.Dialog.close();" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示: 1、《图书网课类模板》无需选择商品类型及商品名称；2、《商品名称》若不限制商品则无需选择。</div>
            <br />
            <br />
        </div>
    </form>
</div>
</body>
<script language="javascript" type="text/javascript">
	$(function() {
		$("input[name=discountType]").bind("click", function() {
			if($(this).val() == "2") {
				$("input[name=discountRate]").removeAttr("disabled");
				$("input[name=minMoney]").attr("disabled", "disabled");
				$("input[name=discountMoney]").attr("disabled", "disabled");
				$("input[name=minMoney]").val("");
				$("input[name=discountMoney]").val("");
			} else {
				$("input[name=discountRate]").attr("disabled", "disabled");
				$("input[name=minMoney]").removeAttr("disabled");
				$("input[name=discountMoney]").removeAttr("disabled");
				$("input[name=discountRate]").val("");
			}
		});
		$("input[name=discountType][value=1]").click();
		
		var templateType = $("input[name=type]:checked").val();
		$("input[name=type]").bind("click", function() {
			var nowType = $(this).val();
			if(templateType != nowType) {
				templateType = nowType;
				if(nowType == "1") {
					$("input[name=productType]:checked").removeAttr("checked");
					$("#productId").val("");
					$("#productName").val("");
				} else {
					$("input[name=productType][value=1]").click();
				}
			}
		});
		$("input[name=type][value=1]").click();
		
		var productType = $("input[name=productType]:checked").val();
		$("input[name=productType]").bind("click", function() {
			if(templateType == 2) {
				if(productType != $(this).val()) {
					$("#productId").val("");
					$("#productName").val("");
				}
				productType = $(this).val();
			} else {
				return false;
			}
		});
		
		$("#productName").bind("click", function() {
			if(templateType == 2) {
				if($("input[name=productType]:checked").val() == "1") {
					top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=productBookSelector&srcpage=coupon_template_add",ID:'col1',Title:"选择图书",Width:1024,Height:768});
				} else {
					top.Dialog.open({URL:"<%=path%>/business/coupon.do?act=productNetworkSelector&srcpage=coupon_template_add",ID:'col1',Title:"选择网课",Width:1024,Height:768});
				}
			}
		});
	});

    function sub(){
        var title = $("#title").val();
        if(!title) {
            top.Dialog.alert("请输入模板名称！");
            return;
        }
        if(title.length > 8) {
            top.Dialog.alert("模板名称最多输入8个字符！");
            return;
        }
        
        var discountType = $("input[name=discountType]:checked").val();
        if(discountType == "1") {	// 满减券
	        var minMoney = $("#minMoney").val();
	        var discountMoney = $("#discountMoney").val();
            if(!/^\d+(\.\d+)?$/.test(minMoney)){
                top.Dialog.alert("满额必须输入0及以上的数字！");
                return;
            }
        	if(!/^\d+(\.\d+)?$/.test(discountMoney) ){
                top.Dialog.alert("减额必须输入0及以上的数字！");
                return;
            }
        } else {	// 折扣券
        	var discountRate = $("#discountRate").val();
        	if(!/^0(\.\d+)?$/.test(discountRate)){
                top.Dialog.alert("折扣率必须输入0-1之间的两位小数");
                return;
            }
        }
        
        var effectPeriod = $("#effectPeriod").val();
        if(!/^[1-9]\d*$/.test(effectPeriod)){
            top.Dialog.alert("有效期限必须输入正整数！");
            return;
        }

		var templateType = $("input[name=type]:checked").val();
        if(templateType=="2" && !$("input[name=productType]:checked").val()) {
            top.Dialog.alert("请选择商品类型！");
            return;
        }
	
        $("#scrollContent").mask("表单正在提交...");
        $('#listForm').ajaxSubmit(function(data){
            if(data.result){
                top.frmright.window.location.reload();
                top.Dialog.close();
            }
        });
    }
</script>
</html>
