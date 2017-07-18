<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.ProductType.ProductTypeActionForm"%>
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

</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：产品类型管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/ProductType.do?act=add" method="post" enctype="multipart/form-data" id="listForm">
			<input type="text" name="parentid" value="${parentid }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>产品类型基本信息</legend> 
						<table class="tableStyle" >
							
								<tr>
								<td width="15%" align="right">
									名称：
								</td>
								<td width="35%" align="left">
									<input type="text" name="typeName" class="validate[required] iptClass" maxlength="15"/>
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" class="validate[required,length[0,4],custom[onlyNumber]] iptClass" onkeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))"  />
									<span class="star">*</span>
								</td>
							</tr>
							<!-- <tr>
								<td  align="right">
									图片（手机端）：<br/>
							  	<span class="star">（156px*156px）</span>
								</td>
								<td  align="left">
									<input type="text" style="display: none" name="imgPhone" id="iconPhone" class="validate[] iptClass" />
									<div style="float: left;">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star"></span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px"></div>
								</td>
								<td  align="right">
									图片（Pad端）：<br/>
							  	<span class="star">（369px*369px） </span>
								</td>
								<td  align="left">
									<input type="text" style="display: none" name="imgPad" id="iconPad" class="validate[] iptClass" />
									<div style="float: left;">
										<input type="button" value="上传并预览" onclick="fileClickpad()"/>
									</div>
									<span class="star"></span>
									<div id="PadDiv" style="float: left;padding-right: 20px"></div>
								</td>
							</tr> -->
								
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
							<input type="reset" value=" 关闭" onclick="back()" />
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
				<html:form action="/business/ProductType.do?act=updateImgPath&type=phone" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
				
				<html:form action="/business/ProductType.do?act=updateImgPath&type=pad" styleId="formActionpad"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filepad" onchange="ajaxFilepad()"></html:file>
				</html:form>
				
			</div>
</body>
<script language="javascript" type="text/javascript">
 
	function back(){
     top.Dialog.close();
	}
	function flush(){
	 top.frmright.window.location.reload();
	}
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
	
	   function fileClickpad(){
  		 	$("#filepad").click();
 		}
 		
	function ajaxFilepad(){
	  	var path = $("#filepad").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	  
	 $('#formActionpad').ajaxSubmit(function(data){
	    if(data.result){
			$("#iconPad").val(data.imgPath); 
	    	$("#PadDiv").html("<img src='"+data.imgPath+"' style='width: 80px;'/>");
	    	$("#filepad").val("");
	    }
	    });
	}

</script>


</html>
