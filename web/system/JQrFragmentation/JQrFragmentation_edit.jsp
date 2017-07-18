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
		<form name="listForm" action="business/JQrFragmentation.do?act=update" method="post" id="listForm" >
		<input type="text" name="id" value="${JQrFragmentationActionForm.id }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
							<tr>
								<td width="15%" align="right">
									名称：
								</td>
								<td width="85%" align="left">
									<input type="text" name="name" style="width: 95%" class="validate[required] iptClass" value="${JQrFragmentationActionForm.name }"/>
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									听力路径：
								</td>
								<td width="85%" align="left">
									<input type="text" name="hearUrl" style="width: 95%"  class="validate[required] iptClass" value="${JQrFragmentationActionForm.hearUrl }"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									二维码：
								</td>
								<td width="85%" align="left">
									<input type="text" name="qrCode" style="width: 95%" class="validate[required] iptClass" value="${JQrFragmentationActionForm.qrCode }"/>
									<span class="star">*</span>
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
							<input type="reset" value=" 关 闭 " onclick="back()" />
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
		    	alert("添加成功，请手动刷新");
		    	top.Dialog.close();
		    }
		   })
	}


</script>


</html>
