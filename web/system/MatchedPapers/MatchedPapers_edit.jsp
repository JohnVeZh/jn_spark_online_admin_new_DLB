<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.MatchedPapers.MatchedPapersActionForm"%>
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
<!--缩略图样式-->
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>

<%
 MatchedPapersActionForm po = (MatchedPapersActionForm) request.getAttribute("MatchedPapersActionForm");
 %>

</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置:修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/MatchedPapers.do?act=update" method="post" id="listForm">
			<input type="text" name="mptId" style="display: none" value="${MatchedPapersActionForm.mptId }" />
			<input type="text" name="id" style="display: none" value="${MatchedPapersActionForm.id }" />
			<input type="text" id="textName" name="textName" style="display: none" value="${textName }"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
							<tr>
								<td width="15%" align="right">
									详情图片：
								</td>
								<td width="35%" align="left">
									详情图片：<input type="text" style="width: 100%" name="mpViewImgpath" id="iconPhone" class="validate[] iptClass" value="${MatchedPapersActionForm.mpViewImgpath }"/><br/>
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸：100px * 100px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									<% 
										if(null!=po.getMpViewImgpath() && !"".equals(po.getMpViewImgpath())){
										if(po.getMpViewImgpath().startsWith("http://")){
									   %>
									   <a href="javascript:thumbImgsDiv('<%=po.getMpViewImgpath() %>',0)" >
									  	  <img src="<%=po.getMpViewImgpath() %>" width="80px" />
									  	</a>
									   <% }else{
									   %>
										   <a href="javascript:thumbImgsDivList('<%=po.getMpViewImgpath() %>',0,'<%=path%>')" >
										  	  <img src="<%=path%>/<%=po.getMpViewImgpath() %>" width="80px" />
										  	</a>
									   <%} }%>
									</div>
								</td>
								<td width="15%" align="right">
									列表图片：
								</td>
								<td width="35%" align="left">
									图片路径：<input type="text" style="width: 100%" name="mpListImgpath" id="iconPhones" class="validate[] iptClass" value="${MatchedPapersActionForm.mpListImgpath }"/><br/>
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphones()"/>
									</div>
									<span class="star">*图片尺寸：100px * 100px</span>
									<div id="PhoneDivs" style="float: left;padding-right: 20px;">
										<% 
										 if(null!=po.getMpListImgpath() && !"".equals(po.getMpListImgpath())){
											if(po.getMpListImgpath().startsWith("http://")){
									   %>
										   <a href="javascript:thumbImgsDiv('<%=po.getMpListImgpath() %>',0)" >
										  	  <img src="<%=po.getMpListImgpath() %>" width="80px" />
										  	</a>
									   <% }else{
									   %>
										   <a href="javascript:thumbImgsDivList('<%=po.getMpListImgpath() %>',0,'<%=path%>')" >
										  	  <img src="<%=path%>/<%=po.getMpListImgpath() %>" width="80px" />
										  	</a>
									   <%} }%>
									
									</div>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									试卷名称：
								</td>
								<td width="35%" align="left">
									<input type="text" name="mpName" class="validate[required] iptClass" style="width: 96%" value="${MatchedPapersActionForm.mpName }" maxNum="100"/>
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" class="validate[required] iptClass" value="${MatchedPapersActionForm.sort }" inputMode="numberOnly"  watermark="限制输入正整数"/>
									<span class="star">*</span>
								</td>
							</tr>
							<c:if test="${textName =='听力专区' }">
								<tr>
									<td width="15%" align="right">
										微信二维码编码：
									</td>
									<td width="35%" align="left">
										<input type="text" name="qrCode" class="validate[] iptClass" value="${MatchedPapersActionForm.qrCode }"/>
										<span class="star"></span>
									</td>
									<td width="15%" align="right"></td>
									<td width="35%" align="left"></td>
								</tr>
							</c:if>
						</table>
						<br/>
						 <div class="basicTab">
						    <c:choose>
						    <c:when test="${textName =='听力专区'}">
						     </c:when>
						     <c:when test="${textName=='写作专区' }">
						       <div name="写作" id="writing" style="width:100%;min-height: 450px">
						      	<input type="text" name="mptwId" value="${mptw.id }" style="display: none"/>
						      <div style="min-height: 450px">
						       <h3>文本内容</h3>
								 	<textarea  id="ue_wContent" name="wContent" style="width: 100%"  >${mptw.wContent }</textarea> 
								
								<h3>参考范文</h3>
								 	<textarea  id="ue_wTest" name="wTest" style="width: 100%"  >${mptw.wTest }</textarea> 
								
								<h3>点评</h3>
									 	<textarea  id="ue_wAnalysis" name="wAnalysis" style="width: 100%"  >${mptw.analysis }</textarea> 
							    </div>
						    </div>
						     </c:when>
						     <c:when test="${textName=='翻译专区' }">
						       <div name="翻译" id="translate" style="width:100%;min-height: 450px" >
						   	 	<input type="text" name="mpttId" value="${mptt.id }" style="display: none"/>
							      <div style="min-height: 450px">
							       <h3>文本内容</h3>
									 	<textarea  id="ue_tContent" name="tContent" style="width: 100%"  >${mptt.tContent }</textarea> 
									
									<h3>参考范文</h3>
									 	<textarea  id="ue_tTest" name="tTest" style="width: 100%"  >${mptt.tTest }</textarea> 
									
							       <h3>解析</h3>
									 	<textarea  id="ue_tAnalysis" name="tAnalysis" style="width: 100%"  >${mptt.analysis }</textarea> 
							       </div>
							</div>
						     </c:when>
						     <c:otherwise>
						       <div name="写作" id="writing" style="width:100%;min-height: 450px">
						      	<input type="text" name="mptwId" value="${mptw.id }" style="display: none"/>
						      <div style="min-height: 450px">
						       <h3>文本内容</h3>
								 	<textarea  id="ue_wContent" name="wContent" style="width: 100%"  >${mptw.wContent }</textarea> 
								
								<h3>参考范文</h3>
								 	<textarea  id="ue_wTest" name="wTest" style="width: 100%"  >${mptw.wTest }</textarea> 
								
								<h3>点评</h3>
									 	<textarea  id="ue_wAnalysis" name="wAnalysis" style="width: 100%"  >${mptw.analysis }</textarea> 
							    </div>
						    </div>
						
						    <div name="翻译" id="translate" style="width:100%;min-height: 450px" >
						   	 	<input type="text" name="mpttId" value="${mptt.id }" style="display: none"/>
							      <div style="min-height: 450px">
							       <h3>文本内容</h3>
									 	<textarea  id="ue_tContent" name="tContent" style="width: 100%"  >${mptt.tContent }</textarea> 
									
									<h3>参考范文</h3>
									 	<textarea  id="ue_tTest" name="tTest" style="width: 100%"  >${mptt.tTest }</textarea> 
									
							       <h3>解析</h3>
									 	<textarea  id="ue_tAnalysis" name="tAnalysis" style="width: 100%"  >${mptt.analysis }</textarea> 
							       </div>
							</div>
						     </c:otherwise>
						    </c:choose>
						    
						</div>
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
				<html:form action="business/MatchedPapers.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
				
				<html:form action="business/MatchedPapers.do?act=updateImgPath" styleId="formActionphones"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephones" onchange="ajaxFilephones()"></html:file>
				</html:form>
				
		</div>
	<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
</body>
<script language="javascript" type="text/javascript">
 
     function back(){
	  top.Dialog.close();
	}
	function flush(){
	 top.frmright.window.location.reload();
	 //window.location.reload();
	}
	
	  $(function(){
	 	    UE.getEditor('ue_wContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_wTest', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_tContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_tTest', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_wAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_tAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		
	  		$("#ue_wContent").attr("class","");
	  		$("#ue_wTest").attr("class","");
	  		$("#ue_tContent").attr("class","");
	  		$("#ue_tTest").attr("class","");
	  		$("#ue_wAnalysis").attr("class","");
	  		$("#ue_tAnalysis").attr("class","");
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
	   		$("#PhoneDiv").html("");
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
	    	$("#PhoneDivs").html("");
			$("#iconPhones").val(data.imgPath); 
	    	$("#PhoneDivs").html("<div style='padding: 5px 5px 5px 5px;float: left'><img src='"+data.imgPath+"' style='width: 80px;'/></div>");
	    	$("#filephones").val("");
	    }
	    });
	   }

</script>


</html>
