<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NetworkVideoTeaher.NetworkVideoTeaherActionForm"%>
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
<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>

	<!-- 七牛文件上传 -->
	<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
	<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common/jquery.qiniu.js"></script>
 <script type="application/javascript">
	 $(function () {
		 $("#2_container").css("z-index","99999");
     })

	 function submitForm() {
		 var jumpType = $("#jumpType").val();
		 if(jumpType==1){
		     var content = $("textarea[name='content']").val();
             if(!content){
                 top.Dialog.alert("请填写富文本内容");
		         return false;
			 }
		 }else{
             if(!( $("#contentId").val())){
                 top.Dialog.alert("请填写跳转内容id");
                 return false;
             }
		 }

		 $("#listForm").submit();
//		 return true;
     }

 </script>
	 
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
		<form name="listForm" action="/business/Dlb/message.do?act=update" method="post" id="listForm" target="frmright" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<input type="hidden" name="id" value="${message.id}">

							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>
									标题：
								</td>
								<td width="40%" align="left">
									<input type="text" name="title" class="validate[required] iptClass" required="required" value="${message.title}" readonly="readonly" />
									<span class="star"></span>
								</td>

								<td width="10%" align="right">
									内容：
								</td>
								<td width="40%" align="left">
									<input type="text" name="intro" class="validate[requird] iptClass" required="required" value="${message.intro}" readonly="readonly" />
									<span class="star"></span>
								</td>



							</tr>
							<tr>
								<td width="10%" align="right">
									跳转类型：
								</td>
								<td width="40%" align="left">

									<select name="jumpType" id="jumpType"  style="z-index: 9999" readonly="readonly" disabled="disabled" >

										<c:choose>
											<c:when test="${message.jumpType==1}">
												<option value="1" selected="selected" >富文本</option>
											</c:when>
											<c:otherwise><option value="1"  >富文本</option></c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${message.jumpType==2}">
												<option value="2" selected="selected" >外部链接</option>
											</c:when>
											<c:otherwise><option value="2"  >外部链接</option></c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${message.jumpType==4}">
												<option value="4" selected="selected" >资讯</option>
											</c:when>
											<c:otherwise><option value="4"  >资讯</option></c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${message.jumpType==5}">
												<option value="5" selected="selected" >图书</option>
											</c:when>
											<c:otherwise><option value="5"  >图书</option></c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${message.jumpType==6}">
												<option value="6" selected="selected" >网课</option>
											</c:when>
											<c:otherwise><option value="6"  >网课</option></c:otherwise>
										</c:choose>

									</select>
									<span class="star"></span>
								</td>
								<td width="10%" align="right">
									跳转内容id ：
								</td>
								<td width="40%" align="left">
									<input type="text" id="contentId" name="contentId" class="validate[] iptClass" value="${message.contentId}" readonly="readonly"/>
								</td>

							</tr>
							<tr>
								<td width="10%" align="right">
									图片：
								</td>
								<td width="40%" align="left">
									<input type="text" style="display: none" name="img" id="img" class="validate[] iptClass" value="${message.img}"/>

									<div id="PhoneDiv" style="float: left;padding-right: 20px" >
										<c:if test="${message.img!=null && message.img!=''}">
											<img src="${message.img}" alt="" style="width: 80px;"/>
										</c:if>
									</div>
								</td>
								<td width="10%" align="right">
									<span class="star">*</span>
									推送时间 ：
								</td>
								<td width="40%" align="left">
									<input type="text" name="pushTimeStr" id="pushTimeStr" class="validate[]" dateFmt="yyyy-MM-dd HH:mm:ss" value="${message.pushTime}" readonly="readonly"/>
								</td>

							</tr>
								<tr>
								<td width="10%" align="right" >
									介绍：
								</td>
								<td width="90%" align="left" colspan="3">
									<div style=" height:450px; overflow:auto; " >
										${message.content}
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
							<input type="button" value=" 提 交 " onclick="flush();submitForm()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%--<input type="reset" value=" 重 置 " />--%>
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
				<%--<html:form action="/business/Dlb/message.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>--%>
			</div>

	<%--<div style="display: none">
		<html:form action="/System/SysConfig.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
			<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
		</html:form>
	</div>--%>
</body>
<script language="javascript" type="text/javascript">
 
 
	function back(){
	  top.Dialog.close();
	}
	function flush(){
	 top.frmright.window.location.reload();
	}
	
	  $(function(){
	 	 UE.getEditor('ue_introduce', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#ue_introduce").attr("class","");
	  	});

    $(function(){
        $("#uploadImgBtn").upload({
            "backInputId" : "img",
            "backShowId" : "PhoneDiv"
        });
    });
	   
</script>


</html>
