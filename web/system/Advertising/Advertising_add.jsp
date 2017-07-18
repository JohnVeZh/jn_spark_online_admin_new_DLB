<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Users.UsersActionForm"%>
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
	
	<!-- 异步上传图片 -->
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/domeURL.js"></script>
		<!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
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
		<form name="listForm" action="business/Advertising.do?act=add" method="post" id="listForm" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							
							<tr style="">
								<td width="15%" align="right">
									图片：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" style="display: none" name="imgPath" id="icon"  class="validate[] iptClass" />
									<div style="float: left;">
										<input type="button" value="上传并预览" onclick="fileClickpad()"/>
									</div>
									<span class="star"></span>
									<div id="iconDiv" style="float: left;padding-right: 20px"></div>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									名称：
								</td>
								<td width="35%" align="left">
									<input type="text" name="name" class="validate[required] iptClass" />
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="sort" class="validate[required,length[0,10],custom[onlyNumber]] iptClass" onkeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))" />
									<span class="star">*</span>
								</td>
							</tr>
								
								<tr>
								<td width="15%" align="right">
									详情：
								</td>
								<td width="35%" align="left" align="left" colspan="3">
									<div style=" height:450px; overflow:auto; " >
										 <textarea id="ue_detail" name="content" style="width: 100%"  class="validate[] iptClass" ></textarea> 
									</div>
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
							<input type="submit" value=" 提 交 "/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 返 回 " onclick="back()" />
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
				<html:form action="/business/Users.do?act=updateImgPath" styleId="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="fileImage" styleId="fileIcon" onchange="ajaxFileIcon()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		history.back();
	}
	
	  $(function(){
	  		UE.getEditor('ue_detail', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  	});
	  	
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


</script>


</html>
