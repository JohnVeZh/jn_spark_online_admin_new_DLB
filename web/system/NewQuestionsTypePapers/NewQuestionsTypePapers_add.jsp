<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NewQuestionsTypePapers.NewQuestionsTypePapersActionForm"%>
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
	 
<!--基本选项卡start-->
<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
<!--基本选项卡end-->
	 
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
		<form name="listForm" action="business/NewQuestionsTypePapers.do?act=add" method="post" id="listForm" >
			<input type="text" name="nqtId" value="${nqtId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>新题型基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
							  <td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="sort" class="validate[required] iptClass" style="width:30%" watermark="限制输入正整数"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									详情图片：
								</td>
								<td width="35%" align="left">
									图片路径：<input type="text" style="width: 100%" name="mpViewImgpath" id="iconPhone" class="validate[] iptClass" /><br/>
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸：300px * 500px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px"></div>
								</td>
								<td width="15%" align="right">
									列表图片：
								</td>
								<td width="35%" align="left">
									图片路径：<input type="text" style="width: 100%" name="mpListImgpath" id="iconPhones" class="validate[] iptClass" /><br/>
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphones()"/>
									</div>
									<span class="star">*图片尺寸：60px * 100px</span>
									<div id="PhoneDivs" style="float: left;padding-right: 20px;"></div>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									试卷名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="mpName" class="validate[required] iptClass" style="width: 100%" maxNum="100"/>
									<span class="star"></span>
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
				<html:form action="business/NewQuestionsTypePapers.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
				
				<html:form action="business/NewQuestionsTypePapers.do?act=updateImgPath" styleId="formActionphones"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephones" onchange="ajaxFilephones()"></html:file>
				</html:form>
				
		</div>
</body>
<script language="javascript" type="text/javascript">
 
    function flush(){
      window.location.reload();
    }
	function back(){
		top.Dialog.close();
	}
	
	function fileClickphone(){
  		$("#filephone").click();
  		
 	}
	function ajaxFilephone(){
	  	var path = $("#filephone").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	  
	 $('#formActionphone').ajaxSubmit(function(data){
	    if(data.result){
			$("#iconPhone").val(data.imgPath); 
	    	$("#PhoneDiv").html("<img src='"+data.imgPath+"' style='width: 80px;'/>");
	    	$("#filephone").val("");
	    }
	    });
	   }
	   
	   
	function fileClickphones(){
  		$("#filephones").click();
  		
 	}
	function ajaxFilephones(){
	  	var path = $("#filephones").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	  
	 $('#formActionphones').ajaxSubmit(function(data){
	    if(data.result){
	    	var imgpath = $("#iconPhones").val();
	    	if(imgpath!=""){
	    		$("#iconPhones").val(imgpath+','+data.imgPath); 
	    	}else{
				$("#iconPhones").val(data.imgPath); 
	    	}
	    	$("#PhoneDivs").append("<div style='padding: 5px 5px 5px 5px;float: left'><img src='"+data.imgPath+"' style='width: 80px;'/></div>");
	    	$("#filephones").val("");
	    }
	    });
	   }
	   


</script>


</html>
