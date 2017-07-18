<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NetworkVideo.NetworkVideoActionForm"%>

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
<!--缩略图样式-->
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
</head>
<%
 NetworkVideoActionForm po = (NetworkVideoActionForm) request.getAttribute("NetworkVideoActionForm");
 %>
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
		<form name="listForm" action="business/NetworkVideo.do?act=update" method="post" id="listForm" target="frmright">
		 <input type="text" name="id" value="${NetworkVideoActionForm.id }" style="display: none"/>
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
								  图片路径：<input type="text" style="width: 100%" name="networkImgpath" id="iconPhone" class="validate[] iptClass" value="${NetworkVideoActionForm.networkImgpath }" /><br/>
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸： 120px : 96px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									<% 
										if(null!=po.getNetworkImgpath() && !"".equals(po.getNetworkImgpath())){
										if(po.getNetworkImgpath().startsWith("http://")){
									   %>
										   <a href="javascript:thumbImgsDiv('<%=po.getNetworkImgpath() %>',0)" >
										  	  <img src="<%=po.getNetworkImgpath() %>" width="80px" />
										  	</a>	
									   <% }else{
									   %>
									    	<a href="javascript:thumbImgsDivList('<%=po.getNetworkImgpath() %>',0,'<%=path%>')" >
											<img src="<%=path%>/<%=po.getNetworkImgpath() %>" style="width: 80px;"/>
										</a>
									    <%} }%>
									</div>
								</td>
								<td width="15%" align="right">
									级别类型：
								</td>
								<td width="35%" align="left">
									<select name="levelType">
									  ${levelTypeStr }
									</select>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									网课名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="networkName" class="validate[required] iptClass" style="width: 98%"  maxNum="100" value="${NetworkVideoActionForm.networkName}"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									选取老师：
								</td>
								<td width="35%" align="left" colspan="3">
									<c:forEach items="${teachers }" var="tc">
									  <div style="padding: 5px 5px 5px 5px;float: left;" >
									     <input type="checkbox" name="tckbox" value="${tc.id }"  <c:if test="${tc.isCheck=='Y' }">checked="checked"</c:if> />${tc.name }
									  </div>
									</c:forEach>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									授课内容：
								</td>
								<td width="35%" align="left" colspan="3">
									<textarea style="width: 98%;height: 45px"  name="brief" maxNum="200">${NetworkVideoActionForm.brief}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									课程类型：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="networkType" value="0"  <c:if test="${NetworkVideoActionForm.networkType==0}" >checked="checked"</c:if> onclick="ntChk(0)" />录播
									<input type="radio" name="networkType" value="1" <c:if test="${NetworkVideoActionForm.networkType==1}" >checked="checked"</c:if> onclick="ntChk(1)" />直播
								</td>
								<td width="15%" align="right">
									直播时间：
								</td>
								<td width="35%" align="left">
								   <input type="text" name="networkLiveTime" id="network" value="${NetworkVideoActionForm.networkLiveTime }" disabled="disabled" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									名师视频：
								</td>
								<td width="35%" align="left" >
									<input type="radio" name="isTeacher" value="0" <c:if test="${NetworkVideoActionForm.isTeacher==0}" >checked="checked"</c:if> />否
									<input type="radio" name="isTeacher" value="1" <c:if test="${NetworkVideoActionForm.isTeacher==1}" >checked="checked"</c:if>/>是
								</td>
								<td width="15%" align="right">
									状态：
								</td>
								<td width="35%" align="left" >
									<div class="sl">
										<input type="radio"  name="state" value="0" checked="checked" class="defl" <c:if test="${NetworkVideoActionForm.state==0}" >checked="checked"</c:if>/>更新
										<input type="radio"  name="state" value="1" <c:if test="${NetworkVideoActionForm.state==1}" >checked="checked"</c:if> />完结
									</div>
									
									<div class="sz">
										<input type="radio"  name="state" value="2" class="defz" <c:if test="${NetworkVideoActionForm.state==2}" >checked="checked"</c:if> />未开始
										<input type="radio"  name="state" value="3" <c:if test="${NetworkVideoActionForm.state==3}" >checked="checked"</c:if> />直播中
										<input type="radio"  name="state" value="4" <c:if test="${NetworkVideoActionForm.state==4}" >checked="checked"</c:if> />直播结束
										<input type="radio"  name="state" value="5" <c:if test="${NetworkVideoActionForm.state==5}" >checked="checked"</c:if> />直播结束不可观看
									</div>
								</td>
							</tr>
							<tr>
							   <td width="15%" align="right">
									直播链接：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="networkLiveLink" id="networkLiveLink"  style="width: 100%" value="${NetworkVideoActionForm.networkLiveLink }"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									原价：
								</td>
								<td width="35%" align="left">
									<input type="text" name="originalPrice"    value="${NetworkVideoActionForm.originalPrice }"/>
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									现价：
								</td>
								<td width="35%" align="left">
									<input type="text" name="networkMoney"  value="${NetworkVideoActionForm.networkMoney}"/>
									<span class="star">*</span>
								</td>
								
								</tr>
							<tr><td width="15%" align="right">
									课时：
								</td>
								<td width="35%" align="left">
								   <input type="text" name="catalogNum" inputMode="numberOnly"  watermark="限制输入正整数" value="${NetworkVideoActionForm.catalogNum}"/>
								</td>
								
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" inputMode="numberOnly"  watermark="限制输入正整数" value="${NetworkVideoActionForm.sort}"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否免费：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="isFree" value="0" <c:if test="${NetworkVideoActionForm.isFree==0}" >checked="checked"</c:if> onclick="freeChk(0)"/>否
									<input type="radio" name="isFree" value="1" <c:if test="${NetworkVideoActionForm.isFree==1}" >checked="checked"</c:if> onclick="freeChk(1)"/>是
								</td>
								<td align="right">是否含有课时目录：</td>
								<td align="left">
									<input type="radio" name="isCatalog" value="0" <c:if test="${NetworkVideoActionForm.isCatalog==0}" >checked="checked"</c:if> />否
									<input type="radio" name="isCatalog" value="1" <c:if test="${NetworkVideoActionForm.isCatalog==1}" >checked="checked"</c:if> />是
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否限时免费：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="isLimitFree" value="0" <c:if test="${NetworkVideoActionForm.isLimitFree==0}" >checked="checked"</c:if> onclick="disableSelect()"  disabled="disabled"/>否
									<input type="radio" name="isLimitFree" value="1" <c:if test="${NetworkVideoActionForm.isLimitFree==1}" >checked="checked"</c:if> onclick="networkMoney1()" disabled="disabled"/>是
								</td>
								<td width="15%" align="right" >
									限时时间：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="limitStartTime" id="limitStartTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="${NetworkVideoActionForm.limitStartTime}" disabled="disabled"/>-
									<input type="text" name="limitEndTime" id="limitEndTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="${NetworkVideoActionForm.limitEndTime}" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否附赠图书：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="isBook"  value="0" <c:if test="${NetworkVideoActionForm.isBook ==0}"  >checked="checked"</c:if> />否
									<input type="radio" name="isBook"  value="1" <c:if test="${NetworkVideoActionForm.isBook ==1}"  >checked="checked"</c:if> />是
								</td>
								<td width="15%" align="right">
									是否公开课：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="isPublic"  value="0" <c:if test="${NetworkVideoActionForm.isPublic ==0}"  >checked="checked"</c:if> />否
									<input type="radio" name="isPublic"  value="1" <c:if test="${NetworkVideoActionForm.isPublic ==1}"  >checked="checked"</c:if> />是
								</td>
							</tr>
                            <tr>
                                <td width="15%" align="right">
                                    礼包费用：
                                </td>
                                <td width="35%" align="left">
                                    <input type="text" name="bookPrice" value="${NetworkVideoActionForm.bookPrice }" />
                                </td>
                                <td width="15%" align="right">
                                    报名截止时间：
                                </td>
                                <td width="35%" align="left">
                                    <input type="text" name="enrolEndTime" id="enrolEndTime" value="${NetworkVideoActionForm.enrolEndTime }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
                                </td>
                            </tr>
                            <tr>
                                <td width="15%" align="right">
                                    开课日期：
                                </td>
                                <td width="35%" align="left">
                                    <input type="text" name="teachStartTime" id="teachStartTime" value="${NetworkVideoActionForm.teachStartTime }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td width="15%" align="right">
                                    结课日期：
                                </td>
                                <td width="35%" align="left">
                                    <input type="text" name="teachEndTime" id="teachEndTime" value="${NetworkVideoActionForm.teachEndTime }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="15%" align="right">
                                    报名人数上限：
                                </td>
                                <td width="35%" align="left">
                                    <input type="text" name="limitCount"  inputMode="numberOnly" value="${NetworkVideoActionForm.limitCount}"  watermark="限制输入正整数" />
                                </td>
                                 <td width="15%" align="right">
                                    二维码ID：
                                </td>
                                <td width="35%" align="left">
                                    <input type="text" name="qrcode"  inputMode="qrcode" value="${NetworkVideoActionForm.qrcode}" />
                                </td>
                            </tr>

							<tr>
								<td width="15%" align="right">
									课程介绍：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:410px; overflow:auto; ">
										 <textarea  id="ue_networkIntroduce" name="networkIntroduce" style="width: 100%"  >${NetworkVideoActionForm.networkIntroduce}</textarea> 
									</div>
								</td>
							</tr>
							
							<tr >
								<td width="15%" align="right">
									课程目录：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:450px; overflow:auto; ">
										 <textarea  id="ue_catalogIntroduce" name="catalogIntroduce" style="width: 100%"  >${NetworkVideoActionForm.catalogIntroduce}</textarea> 
									</div>
								</td>
							</tr>
							<tr style="display: none">
								<td width="15%" align="right" >
									老师简介：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:450px; overflow:auto; ">
										 <textarea  id="ue_teacherIntroduce" name="teacherIntroduce" style="width: 100%"  >${NetworkVideoActionForm.teacherIntroduce}</textarea> 
									</div>
								</td>
							</tr>
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
				<html:form action="business/NetworkVideo.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
			</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
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
   
   
   //判断是直播或录播
   function ntChk(value){
     if(value==0){
	      $("#network").attr("disabled","disabled");
		  $("#networkLiveLink").attr("disabled","disabled");
	      //隐藏状态
		  $(".sz").attr("style","display:none");
		  $(".sl").attr("style","");
     }else{
      	  $("#network").removeAttr("disabled");
          $("#networkLiveLink").removeAttr("disabled");
      
	      //隐藏状态
	      $(".sl").attr("style","display:none");
	      $(".sz").attr("style","");
     }
   }
   //判断是否免费
   function freeChk(value){
     if(value==0){
      $("[name='isLimitFree']").attr("disabled","disabled");
     }else{
      $("[name='isLimitFree']").removeAttr("disabled");
     }
   }
   function networkMoney1(){
       $('#limitStartTime').removeAttr("disabled"); 
	    $('#limitEndTime').removeAttr("disabled"); 
	    document.getElementById("networkMoney").value="0";
   }
   function disableSelect(){
     $('#limitStartTime').val("");
     $('#limitStartTime').val("");
     $("#limitStartTime").attr("disabled", "false");
	 $("#limitEndTime").attr("disabled", "false");
     
   }
   
	  $(function(){
	  		if(${NetworkVideoActionForm.state < 2}){
	  		 $(".sz").attr("style","display:none");
	  		}else{
	  		 $(".sl").attr("style","display:none");
	  		}
	  
	  		UE.getEditor('ue_networkIntroduce', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_catalogIntroduce', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		UE.getEditor('ue_teacherIntroduce', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		
	  		$("#ue_networkIntroduce").attr("class","");
	  		$("#ue_catalogIntroduce").attr("class","");
	  		$("#ue_teacherIntroduce").attr("class","");
	  		
	  		ntChk(${NetworkVideoActionForm.networkType});
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
