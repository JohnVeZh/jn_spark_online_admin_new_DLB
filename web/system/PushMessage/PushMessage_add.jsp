<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.PushMessage.PushMessageActionForm"%>
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
<!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
	 <!-- 异步上传图片 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<!-- 遮罩层 -->
	<script type="text/javascript" src="../js/form/loadmask.js"></script>
	
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/PushMessage.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="10%" align="right">
									标题：
								</td>
								<td width="90%" align="left" colspan="3">
									<input type="text" name="pmTitle" style="width: 80%"  class="validate[required] iptClass" maxNum="15"/>
									<span class="star">* </span>
								</td>
								</tr>
							<tr>
							<tr>
								<td width="10%" align="right">
									副标题（仅通知栏展示）：
								</td>
								<td width="90%" align="left" colspan="3">
									<input type="text" name="subTitle" style="width: 80%"  class="validate[required] iptClass" maxNum="40"/>
									<span class="star">* </span>
								</td>
								</tr>
							<tr>
								<td width="10%" align="right">
									图片：
								</td>
								<td width="40%" align="left" colspan="3">
									<input type="text" style="display: none" name="pmImg" id="icon"  class="validate[] iptClass" />
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickpad()"/>
									</div>
									<span class="star">*图片尺寸：96px * 96px</span>
									<div id="iconDiv" style="float: left;padding-right: 20px"></div>
								</td>
								<%-- <td width="10%" align="right">
									是否推送：
								</td>
								<td width="40%" align="left">
									<input type="radio" name="pmIsPush" value="1" checked="checked"/> 是 <input type="radio" name="pmIsPush" value="0" /> 否 
								  <select name="levelType"  class="default" >
										${type }
									</select>
								</td> --%>
							</tr>
							<tr>
								<td width="10%" align="right">
									内容：
								</td>
								<td width="90%" align="left" colspan="3">
									 <textarea  id="ue_pmContent" name="pmContent" style="width: 99%;"  ></textarea> 
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
							<input type="submit" value=" 提 交 " onclick="flush()"/>
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
		<div style="display: none">
				<html:form action="/business/PushMessage.do?act=updateImgPath" styleId="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="fileIcon" onchange="ajaxFileIcon()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
   function flush(){
   	$("#scrollContent").mask("表单正在提交...");
    top.frmright.window.location.reload();
   }
  
	function back(){
		//document.getElementById('listForm').action="business/News.do?act=list";
		//document.getElementById('listForm').submit();
		top.Dialog.close();
	}
	function fileClickpad(){
  		 	$("#fileIcon").click();
 		}
 		
	function ajaxFileIcon(){
	  	var path = $("#fileIcon").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	 	$('#formAction').ajaxSubmit(function(data){
		    if(data.result){
				$("#icon").val(data.imgPath); 
		    	$("#iconDiv").html("<img src='"+data.imgPath+"' style='width: 80px;'/>");
		    	$("#fileIcon").val("");
		    }
	    });
	}
	
	$(function(){
	  		UE.getEditor('ue_pmContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#ue_pmContent").attr("class","");
	  	});
	  $(function(){
	  		var msg='';//jstl的标签 $，{，msg}
	  		if(msg==200){
	  			top.Dialog.alert("添加成功。");
	  		}else if(msg==201){
	  			top.Dialog.alert("添加失败。");
	  		}else if(msg==202){
	  			top.Dialog.alert("添加失败!");
	  		}
	  	});


</script>


</html>
