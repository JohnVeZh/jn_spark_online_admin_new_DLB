<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Dlb.PeriodVideoQrcode.PeriodVideoDetail"%>

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

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
<!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
<!-- 异步上传图片 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<%
PeriodVideoDetail po = (PeriodVideoDetail) request.getAttribute("PeriodVideoDetail");
 %>
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：新增二维码资源<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="<%=path%>/business/PeriodVideoDetail.do?act=update" method="post" id="listForm" target="frmright">
			<input type="hidden" value="${PeriodVideoDetail.videoQrcodeId}" id="videoQrcodeId" name="videoQrcodeId"/>
			<input type="hidden" value="${PeriodVideoDetail.id}" id="id" name="id"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>课程信息</legend> 
					<div style="overflow:auto;">
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right" colspan="2">
									资源名称：
								</td>
								<td width="35%" align="left" colspan="2">
									<input type="text" name="title" id="title" value="${PeriodVideoDetail.title }"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" colspan="2">
									资源排序：
								</td>
								<td width="35%" align="left" colspan="2">
									<input type="text" name="sort" id="sort" value="${PeriodVideoDetail.sort }"/>
								</td>
								
							</tr>
							<tr>
								<td width="15%" align="right" colspan="2">
									CCID：
								</td>
								<td width="35%" align="left" colspan="2">
									<input type="text" name="videoCcid" id="videoCcid" value="${PeriodVideoDetail.videoCcid }"/>
								</td>
							</tr>
							<tr>
								<td width="100%" align="left" colspan="4">
									<div style=" height:410px; overflow:auto; ">
										 <textarea  id="detail" name="detail"  style="width: 100%"  >${PeriodVideoDetail.detail }</textarea> 
									</div>
								</td>
							</tr>
						</table>
						</div>
					</fieldset>
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="button" value=" 提 交 " onclick="flush()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
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
	function flush(){
		var videoQrcodeId=$("#videoQrcodeId").val();
		var title=$("#title").val();
		var sort=$("#sort").val();
		var videoCcid=$("#videoCcid").val();
		if(videoQrcodeId==""){
			alert("页面加载错误,请重新打开！");
			return;
		}
		if(title==""){
			alert("请输入资源名称！");
			return;
		}
		if(sort==""){
			alert("请输入排序号！！");
			return;
		}
		if(videoCcid==""){
			alert("请输入cc视频ID或链接");
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
	
	$(function(){
		UE.getEditor('detail', {initialFrameWidth:'100%',initialFrameHeight : 300});
		$("#detail").attr("class","");
	});
	function back(){
		top.Dialog.close();
	}
</script>


</html>
