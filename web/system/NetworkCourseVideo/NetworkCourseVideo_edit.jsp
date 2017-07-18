<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NetworkCourseVideo.NetworkCourseVideo"%>
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
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<!-- 表单验证end -->
	 <!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<!-- 日期选择框end -->
	<!-- 异步上传图片 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
</head>
 <%
  NetworkCourseVideo po = (NetworkCourseVideo) request.getAttribute("NetworkCourseVideo");
 %>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：视频编辑<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/NetworkCourseVideo.do?act=update" method="post" id="listForm" >
			<input type="text" name="id" value="${NetworkCourseVideo.id }" style="display: none"/>
			<input type="text" name="nvId" value="${NetworkCourseVideo.nvId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>视频编辑</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>视频名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="ncvName" id="ncvName" class="validate[required] iptClass" value="${NetworkCourseVideo.ncvName}" style="width: 96%"/>
								</td>
							</th>
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>选取讲师：
								</td>
								<td width="35%" align="left" colspan="3" >
									<c:forEach items="${teachers }" var="tc">
									  <div style="padding: 5px 5px 5px 5px;float: left;" >
									     <input type="checkbox" name="tckbox" value="${tc.id }" <c:if test="${tc.isCheck=='Y' }">checked="checked"</c:if>/>${tc.name }
									  </div>
								    </c:forEach>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									类型：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="radio" name="ncvType" id="ncvType" value="1" <c:if test="${NetworkCourseVideo.ncvType==1}" >checked="checked"</c:if> onclick="ntChk(1)" />直播
									<input type="radio" name="ncvType" id="ncvType" value="0" <c:if test="${NetworkCourseVideo.ncvType==0}" >checked="checked"</c:if> onclick="ntChk(0)"/>录播
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>课时时长：
								</td>
								<td width="35%" align="left">
									<input type="text" name="ncvDuration" id="ncvDuration" class="validate[required] iptClass" value="${NetworkCourseVideo.ncvDuration }" />
								</td>
								<td width="10%" align="right">
									<span class="star">*</span>开课时间：
								</td>
								<td width="35%" align="left">
									<input type="text" name="ncvStartTimes" id="ncvStartTimes" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="${NetworkCourseVideo.ncvStartTime }"/>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									预告视频：
								</td>
								<td width="35%" align="left">
									cc视频路径:<br /><input type="text" name="ncvPreviewLink" id="ncvPreviewLink" class="validate[] iptClass" style="width:80%" value="${NetworkCourseVideo.ncvPreviewLink }"/>
									<span class="star"></span>
								</td>
								<td width="10%" align="right">
									<span class="star">*</span>封面图片：
								</td>
								<td width="35%" align="left" >
								 图片路径：<input type="text" style="width: 100%" name="ncvImg" id="iconPhone" class="validate[] iptClass" value="${NetworkCourseVideo.ncvImg }"/><br/>
									<div style="float: left">
										<input type="button" value="选择图片上传" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸： 120px : 120px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									 <% 
										if(null!=po.getNcvImg() && !"".equals(po.getNcvImg())){
										if(po.getNcvImg().startsWith("http://")){
									   %>
										   <a href="javascript:thumbImgsDiv('<%=po.getNcvImg() %>',0)" >
										  	  <img src="<%=po.getNcvImg() %>" width="80px" />
										  	</a>	
									   <% }else{
									   %>
									    	<a href="javascript:thumbImgsDivList('<%=po.getNcvImg() %>',0,'<%=path%>')" >
											<img src="<%=path%>/<%=po.getNcvImg() %>" style="width: 80px;"/>
										</a>
									    <%} }%>
									</div>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									课程视频ID：
								</td>
								<td width="35%" align="left">
									<input type="text" name="ncvRecordLinkId" id="ncvRecordLinkId" class="validate[] iptClass" style="width: 96%" value="${NetworkCourseVideo.ncvRecordLinkId }"/>
									<span class="star"></span>
								</td>
								<td width="10%" align="right">
									课程链接：
								</td>
								<td width="35%" align="left">
									<input type="text" name="ncvLiveLink" id="ncvLiveLink" class="validate[] iptClass" style="width: 96%" value="${NetworkCourseVideo.ncvLiveLink }"/>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>排序：
								</td>
								<td width="85%" align="left">
									<input type="text" name="sort" id="sort" class="validate[] iptClass" value="${NetworkCourseVideo.sort }"  inputMode="numberOnly"  watermark="限制输入正整数"/>
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									直播间ID：
								</td>
								<td width="85%" align="left">
									<input type="text" name="ncvLiveRoomId" id="ncvLiveRoomId" class="validate[] iptClass" value="${NetworkCourseVideo.ncvLiveRoomId }"/>
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
							<input type="button" value=" 提 交 " onclick="flush()"/>
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
				<html:form action="business/NetworkCourseVideo.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
	$(function(){
		ntChk(${NetworkCourseVideo.ncvType});
	});
    function back(){
	   //top.Dialog.close();
	   top.Dialog.close();
	}
	function flush(){
		var ncvName=$("#ncvName").val();
		var iconPhone=$("#iconPhone").val();
		var ncvType=$("#ncvType:checked").val();
		var ncvDuration=$("#ncvDuration").val();
		var reg = /^(0[0-9]|1[01]):[0-5][0-9](:[0-5][0-9])?$/;  
		var ncvStartTimes=$("#ncvStartTimes").val();
		var sort=$("#sort").val();
		if(ncvName.replace(/(^\s*)/g, "").length==0){
			top.Dialog.alert("视频名称不能为空",285,285);
			return ;
		}
		var inps = document.getElementsByName('tckbox');
		var idVal = "";
		var j =0 ;
		for(var i = 0; i <inps.length; i++){
			if(inps[i].checked){
				idVal +="&id="+ inps[i].value;
	            j++;
			}else{
				continue;
			}
		}
		if (Number(j)>10||Number(j)<1) {
		      top.Dialog.alert("请选择至少一位最多五位讲师",285,285);
		      return "";
		}
		if(ncvDuration.replace(/(^\s*)/g, "").length>0){
			var nc=ncvDuration.replace(/(^\s*)/g, "");
			var r=nc.match(reg);
			if(r==null){
				top.Dialog.alert("请输入课时为00:00:00格式",285,285);
			    return "";
			}
		}else{
			top.Dialog.alert("请输入课时长度",285,285);
		    return "";
		}
		if(ncvType==1){
			if(ncvStartTimes.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("直播开始时间不能为空",285,285);
				return ;
			}
		}
		if(iconPhone.replace(/(^\s*)/g, "").length==0){
			top.Dialog.alert("封面图片路径不能为空",285,285);
			return ;
		}
		if(sort=='限制输入正整数'){
			top.Dialog.alert("请输入正确的排序格式",285,285);
			return ;
		}
		
		$("#scrollContent").mask("表单正在提交...");
		$('#listForm').ajaxSubmit(function(data){
		    if(data.result){
		    	top.frmright.window.location.reload();
		    	top.Dialog.close();
		    }
		   });
	}
	function ntChk(value){
	  if(value==0){
	 	  $("#ncvStartTimes").val("");
		  $("#ncvStartTimes").attr("disabled","disabled");
	  }else{
	      $("#ncvStartTimes").removeAttr("disabled");
	  }
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
