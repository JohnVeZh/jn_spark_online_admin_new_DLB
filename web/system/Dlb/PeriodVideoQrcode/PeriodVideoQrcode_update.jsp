<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Dlb.PeriodVideoQrcode.PeriodVideoQrcode"%>

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

<!-- 七牛文件上传 -->
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/jquery.qiniu.js"></script>	
</head>
 <%
 PeriodVideoQrcode po = (PeriodVideoQrcode) request.getAttribute("PeriodVideoQrcode");
 %>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：新增二维码信息<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="<%=path%>/business/PeriodVideoQrcode.do?act=update" method="post" id="listForm" target="frmright">
			<input type="hidden" name="id" id="id" value="${PeriodVideoQrcode.id }" />
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>课程信息</legend> 
					<div style="overflow:auto;">
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right" colspan="2">
									二维码名称：
								</td>
								<td width="35%" align="left" colspan="2">
									<input type="text" name="title" id="title" value="${PeriodVideoQrcode.title }"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" colspan="2">
									四六级：
								</td>
								<td width="35%" align="left" colspan="2">
									<select name="section" id="section">
									  <option value="">全部</option>
									  <option value="01" <c:if test="${PeriodVideoQrcode.section=='01'}">selected="selected"</c:if>>四级</option>
									  <option value="02" <c:if test="${PeriodVideoQrcode.section=='02'}">selected="selected"</c:if>>六级</option>
									</select>
								</td>
								
							</tr>
							<tr>
								<td width="15%" align="right" colspan="2">
									<span class="star">*</span>二维码内容：
								</td>
								<td width="35%" align="left" colspan="2">
								<input type="text" style="width: 100%" name="qrcodeContent" id="qrcodeContent" value="${PeriodVideoQrcode.qrcodeContent }" class="validate[] iptClass" style="width:70%;"/><br/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" colspan="2">
									<span class="star">*</span>二维码图片：
								</td>
								<td width="35%" align="left" colspan="2">
								 	<input type="text" name="qrcodeUrl" id="qrcodeUrl" value="${PeriodVideoQrcode.qrcodeUrl }" class="validate[] iptClass" style="width:70%;"/>
									<input id="upimgs" type="button" value="上传预览"/>
									<br/>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									  <c:if test="${PeriodVideoQrcode.qrcodeUrl != null && PeriodVideoQrcode.qrcodeUrl != '' }">
										 <% if(po.getQrcodeUrl().startsWith("http")){
								  		 %>
											<a href="javascript:thumbImgsDiv('${PeriodVideoQrcode.qrcodeUrl }',0)" >
												 <img src="${PeriodVideoQrcode.qrcodeUrl }" width="80px" style="border:1px solid #ccc;padding:5px;"/>
											</a>
										 <% }else{
									     %>
									     	<a href="javascript:thumbImgsDiv('<%=path%>/${PeriodVideoQrcode.qrcodeUrl }',0)" >
												<img src="<%=path%>/${PeriodVideoQrcode.qrcodeUrl }" width="80"  style="border:1px solid #ccc;padding:5px;"/>
										   	</a>
									      <%} %>
									  </c:if>
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
		  <div style="display: none">
				<html:form action="/business/PeriodPaperQrcode.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
	$(function(){
			$("#upimgs").upload({
				"backInputId" : "qrcodeUrl",
				"backShowId" : "PhoneDiv"
			});
	});

	function flush(){
		var title=$("#title").val();
		var section=$("#section").find("option:selected").val();
		var qrcodeContent=$("#qrcodeContent").val();
		var qrcodeUrl=$("#qrcodeUrl").val();
		if(title==""){
			alert("二维码名称不能为空！");
			return;
		}
		if(section==""){
			alert("请选择四六级！");
			return;
		}
		if(qrcodeContent==""){
			alert("二维码地址不能为空！");
			return;
		}
		if(qrcodeUrl==""){
			alert("请上传二维码图片");
			return;
		}
		
		$("#scrollContent").mask("表单提交中！");
		$("#listForm").submit();
	}
	
	$(function(){
		UE.getEditor('translate', {initialFrameWidth:'100%',initialFrameHeight : 300});
		$("#translate").attr("class","");
		UE.getEditor('writing', {initialFrameWidth:'100%',initialFrameHeight : 300});
		$("#writing").attr("class","");
		
	});
	function back(){
		top.Dialog.close();
	}
	
	
	//上传图片
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

</script>


</html>
