<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.JQrNw.JQrNwActionForm"%>

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
		<form name="listForm" action="business/JQrNw.do?act=update" method="post" id="listForm" target="frmright">
			<input type="text" name="id" value="${JQrNwActionForm.id }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
					<div style="overflow:auto;">
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									课程图片：
								</td>
								<td width="35%" align="left" >
								  <input type="text" style="display: none" name="imgPath" id="iconPhone" value="${JQrNwActionForm.imgPath }" class="validate[] iptClass" />
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸： 120px : 96px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									<c:if test="${JQrNwActionForm.imgPath!='' }">
										<a href="javascript:thumbImgsDivList('${JQrNwActionForm.imgPath }',0,'<%=path%>')" >
											<img src="<%=path%>/${JQrNwActionForm.imgPath }" style="width: 80px;"/>
										</a>
									</c:if>
									</div>
								</td>
								<td width="15%" align="right">
									级别类型：
								</td>
								<td width="35%" align="left">
									<select name="levelType" class="default">
									  ${levelTypeStr }
									</select>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									网课名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="name" value="${JQrNwActionForm.name }" class="validate[required] iptClass" style="width: 98%"  maxNum="100"/>
								</td>
							</tr>
							<tr>
								<%-- <td width="15%" align="right">
									课程类型：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="networkType" value="0"  <c:if test="${JQrNwActionForm.networkType==0}" >checked="checked"</c:if> onclick="ntChk(0)" />录播
									<input type="radio" name="networkType" value="1" <c:if test="${JQrNwActionForm.networkType==1}" >checked="checked"</c:if> onclick="ntChk(1)" />直播
								</td> --%>
								<td width="15%" align="right" class="sl" >
									视频Id：
								</td>
								<td width="35%" align="left" class="sl" colspan="3">
									<input type="text" name="networkRecordLink" style="width: 95%" value="${JQrNwActionForm.networkRecordLink }" />
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" inputMode="numberOnly" value="${JQrNwActionForm.sort }" watermark="限制输入正整数" />
									<span class="star">*</span>
								</td>
							</tr>
							<%-- <tr>
							 	<td width="15%" align="right" class="sz">
									直播链接：
								</td>
								<td width="35%" align="left"  class="sz" colspan="3">
									<input type="text" name="networkLiveLink" style="width: 95%" value="${JQrNwActionForm.networkLiveLink }" />
									<span class="star">*</span>
								</td>
							</tr> --%>
							
						</table>
						</div>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="button" value=" 提 交 " onclick="flush()"/>
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
				<html:form action="business/JQrNw.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
   function flush(){
    $("#scrollContent").mask("表单正在提交...");
		$('#listForm').ajaxSubmit(function(data){
		    if(data.result){
		    	top.frmright.window.location.reload();
		    	top.Dialog.close();
		    }
		   })
   }
	function back(){
		top.Dialog.close();
	}
	//判断直播还是录播
	function ntChk(value){
	  if(value==0){
		  //隐藏状态
		  $(".sz").attr("style","display:none");
		  $(".sl").attr("style","");
	  }else{
	        //隐藏状态
	       $(".sl").attr("style","display:none");
	       $(".sz").attr("style","");
	       
	      
	  }
	}
	

	  $(function(){
	 		ntChk(0);
	  	});
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
