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
		<form name="listForm" action="business/PreferentialCode.do?act=add" method="post" id="listForm" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>优惠码基本信息</legend> 
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
									折扣类型：
								</td>
								<td width="35%" align="left">
									<input type="radio" value="0" checked="checked" name="discountType" onclick="ntChk(0)" />满减
									<input type="radio" value="1" name="discountType" onclick="ntChk(1)"/>折扣
								</td>
								<td width="15%" align="right" >
									折扣率：
								</td>
								<td width="35%" align="left">
									<input type="text"  name="rate" class="sz" />
								</td>
							</tr>
							
								<tr >
								<td width="15%" align="right">
									满额：
								</td>
								<td width="35%" align="left">
									<input type="text" name="fullMoney" class="sm" />
								</td>
								<td width="15%" align="right">
									减额：
								</td>
								<td width="35%" align="left">
									<input type="text" name="deductionMoney" class="sm" />
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									使用开始时间：
								</td>
								<td width="35%" align="left">
									<input type="text" name="startTime" class="date" dateFmt="yyyy-MM-dd"/>
								</td>
								<td width="15%" align="right">
									到期时间：
								</td>
								<td width="35%" align="left">
									<input type="text" name="endTime" class="date" dateFmt="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									优惠内容：
								</td>
								<td width="35%" colspan="3" align="left">
									<textarea style="width:95%" name="content" class="validate[] iptClass" ></textarea>
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
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
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
	
	
	  $(function(){
	  		ntChk(0);
	  	});
	  	
	  	//判断满减还是折扣
	function ntChk(value){
	  if(value==0){
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


</script>


</html>
