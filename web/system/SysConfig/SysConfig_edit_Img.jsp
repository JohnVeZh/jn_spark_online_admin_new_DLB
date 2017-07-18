<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.easecom.system.web.SysConfigForm"%>
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
 <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	 <!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/jquery.qiniu.js"></script>
</head>
<%
 SysConfigForm po = (SysConfigForm) request.getAttribute("SysConfigForm");
 %>
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
 		  <html:form  action="System/SysConfig.do?act=update" method="post"  onsubmit="return sub(this)">
			<html:text property="id" style="display:none"></html:text>  
			<input type="text" name="type" id="type" value="${type }" class="validate[required] iptClass" style="display: none"/>
			<input type="text" name="rs" value="${rs }" class="validate[required] iptClass" style="display: none"/>
			<input type="modular" name="modular" value="${modular }" style="display:none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							<c:if test="${type=='NEWS_CAROUSEL3' || type=='INDEX_CAROUSEL3' || type=='START_CAROUSEL'}">
								<tr>
									<td width="10%" align="right">
									  跳转类型： 
									</td>
									<td width="90%" align="left" colspan="3">
										<select id="jumpType" name="jumpType" onchange="javascript:cleanResource();">
											<option value="01" <c:if test="${SysConfigForm.jumpType=='01'}">selected="selected"</c:if>>自有内容（富文本）</option>
											<option value="02" <c:if test="${SysConfigForm.jumpType=='02'}">selected="selected"</c:if>>外部链接（链接地址）</option>
											<option value="11" <c:if test="${SysConfigForm.jumpType=='11'}">selected="selected"</c:if>>社区资讯</option>
											<option value="12" <c:if test="${SysConfigForm.jumpType=='12'}">selected="selected"</c:if>>社区活动</option>
											<option value="13" <c:if test="${SysConfigForm.jumpType=='13'}">selected="selected"</c:if>>图书</option>
											<option value="14" <c:if test="${SysConfigForm.jumpType=='14'}">selected="selected"</c:if>>网课</option>
										</select>
										<span style="padding-left:50px; color:red;">当选择“社区资讯”、“社区活动”、“网课”时，请选择对应的资源名称</span>
									</td>
								</tr>
								<tr>
									<td width="10%" align="right">
									  资源名称： 
									</td>
									<td width="90%" align="left" colspan="3">
										<input type="hidden" id="valueId" name="valueId" watermark="请输入链接地址" value="${SysConfigForm.valueId}"/>
										<input type="text" id="valueName" name="valueName" watermark="请输选择资源名称" value="${SysConfigForm.valueName}" style="width:70%; cursor:pointer;" readonly="readonly" onclick="javascript:selectResource();"/>
										<input type="button" value="清除" onclick="javascript:cleanResource();" />
									</td>
								</tr>
							</c:if>
							<tr>
								<td width="10%" align="right">
									图片地址：
								</td>
								<td width="90%" align="left" colspan="3">
									<input type="text" name="alias" id="alias" value="${SysConfigForm.alias }" class="validate[] iptClass" style="width:85%;"/>
									<input type="button" id="upimgs" value="上传预览"/>
									<br/>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									  <c:if test="${SysConfigForm.alias != null && SysConfigForm.alias != '' }">
										 <% if(po.getAlias().startsWith("http")){
								  		 %>
											<a href="javascript:thumbImgsDiv('${SysConfigForm.alias }',0)" >
												 <img src="${SysConfigForm.alias }" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
											</a>
										 <% }else{
									     %>
									     	<a href="javascript:thumbImgsDiv('<%=path%>/${SysConfigForm.alias }',0)" >
												<img src="<%=path%>/${SysConfigForm.alias }" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
										   	</a>
									      <%} %>
									  </c:if>
									</div>
									<c:choose>
						    		 <c:when test="${type =='BACKIMG' }">
						    		 	<span class="star">* 限制宽高： 600 x 330</span>
						    		 </c:when>
						    		 <c:when test="${type =='PRACTICE_CAROUSEL' }">
						    		 	<span class="star">* 限制宽高 ： 1929 × 1080</span>
						    		 </c:when>
						    		 <c:when test="${type=='INTERNET_CAROUSEL' }">
						    		    <span class="star">* 限制宽高 ： 1929 × 1080 </span>
						    		 </c:when>
						    		 <c:when test="${type=='INDEX_CAROUSEL2' }">
						    		    <span class="star">* 限制宽高 ： 750 × 132 </span>
						    		 </c:when>
						    		 <c:when test="${type=='START_CAROUSEL' }">
						    		    <span class="star">* 限制宽高 ： 1 : 0.66 </span>
						    		 </c:when>
						    		  <c:otherwise>
										<span class="star">* 限制宽高 ： 600 x 300</span>
						    		  </c:otherwise>
						    		 </c:choose>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									名称：
								</td>
								<td width="90%" align="left" colspan="3">									 
									<html:text property="name" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									排序值：
								</td>
								<td width="90%" align="left" colspan="3">									 
									<html:text property="sort" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<c:if test="${type=='INDEX_CAROUSEL' || type=='NEWS_CAROUSEL' || type=='NEWS_CAROUSEL3' || type=='INDEX_CAROUSEL2' || type=='INDEX_CAROUSEL3' || type=='START_CAROUSEL'}">
								<tr>
									<td width="10%" align="right">
									  链接地址： 
									</td>
									<td width="90%" align="left" colspan="3">
										<input type="text" id="url" name="url" watermark="请输入链接地址，以http://开头" style="width:70%;" value="${SysConfigForm.url}"/>
									</td>
								</tr>
							</c:if>
							<c:choose>
				    		 <c:when test="${type =='NETWORK_CAROUSEL' || type =='NETWORK_COURSE_BANNER'}">
				    		 	<tr>
									<td width="10%" align="right">
									  选择网课： 
									</td>
									<td width="90%" align="left" colspan="3">
									  <a href="javascript:;" onclick="preNet()" title="新增"> <span
												class="img_add"></span>
										</a>
										<input type="text" name="value" id="value" style="display: none" value="${SysConfigForm.value }"/>
										<span name="vlSpan" id="vlSpan">${nkName }</span>
									</td>
								</tr>
				    		 </c:when>
				    		  <c:otherwise>
								<tr>
									<td width="10%" align="right">
									  值： 
									</td>
									<td width="90%" align="left" colspan="3">
									<div style=" height:450px; overflow:auto; " >
										<textarea rows="10" id="ue_content"  name="value" >
											<c:if test="${SysConfigForm.jumpType==null || SysConfigForm.jumpType=='' || SysConfigForm.jumpType=='01' || SysConfigForm.jumpType=='02'}">${SysConfigForm.value }</c:if>
										</textarea>
									</div>
									</td>
								</tr>
				    		  </c:otherwise>
				    		 </c:choose>
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
							<input type="button" value=" 返 回 " onclick="back()"/> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
  </html:form>
		</div>
		
		<div style="display: none">
			<html:form action="/System/SysConfig.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
			</html:form>
		</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
</body>
<script language="javascript" type="text/javascript">
 	$(function(){
 		$("#upimgs").upload({
 			"backInputId" : "alias",
 			"backShowId" : "PhoneDiv"
 		});
 	});
 	
	function back(){
		history.back();
	}

	  $(function(){
	  		UE.getEditor('ue_content', {initialFrameWidth:'99%',initialFrameHeight : 400});
			$("#ue_content").attr("class","");
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
			$("#alias").val(data.imgPath); 
	    	$("#PhoneDiv").html("<img src='"+data.imgPath+"' style='width: 80px;'/>");
	    	$("#filephone").val("");
	    }
	    });
	   }
	   
	    //选择网课
	   function preNet(){
		   var type=$("#type").val();
		   if(type=='NETWORK_CAROUSEL'){
		  		top.Dialog.open({URL:"<%=path%>/business/NetworkVideo.do?act=scList",Title:"绑定窗口",Width:1024,Height:768});
		   }else if(type=='NETWORK_COURSE_BANNER'){
		  		top.Dialog.open({URL:"<%=path%>/business/NetworkCourse.do?act=scList",Title:"绑定窗口",Width:1024,Height:768});
		   }
		}

	   
	   /**
	    * 清除选择的资源
	    */
	   function cleanResource() {
		   $("#valueId").val("");
		   $("#valueName").val("");
		   $("#valueName").removeAttr("title");
	   }
	   
	   /**
	    * 选择资源
	    */
	   function selectResource() {
		   var jumpType = $("#jumpType").val();
		   if(jumpType == "11") {			// 资讯
			   top.Dialog.open({URL:"<%=path%>/business/News.do?act=newsSelector&idKey=valueId&valueKey=valueName",Title:"绑定窗口",Width:1024,Height:768});
		   } else if(jumpType == "12") {	// 活动
			   top.Dialog.open({URL:"<%=path%>/business/CommunityPost.do?act=communityPostSelector&idKey=valueId&valueKey=valueName",Title:"绑定窗口",Width:1024,Height:768});
		   } else if(jumpType == "13") {	// 图书
			   top.Dialog.open({URL:"<%=path%>/business/Product.do?act=bookSelector&idKey=valueId&valueKey=valueName",Title:"绑定窗口",Width:1024,Height:768});
		   } else if(jumpType == "14") {	// 网课
			   top.Dialog.open({URL:"<%=path%>/business/NetworkCourse.do?act=networkCourseSelector&idKey=valueId&valueKey=valueName",Title:"绑定窗口",Width:1024,Height:768});
		   }
	   }
	   
		function sub() {
		   if($("#jumpType").val()) {
			   var jumpType = $("#jumpType").val();
			   if(jumpType=="11" || jumpType == "12" || jumpType == "13" || jumpType == "14") {
				   if(!$("#valueId").val()) {
					   top.Dialog.alert("请选择资源名称");
					   return false;
				   }
			   }/* else if(jumpType=="02") {
				   if(!$("#url").val() || $("#url").val()=="请输入链接地址") {
					   top.Dialog.alert("请输入链接地址");
					   return false;
				   }
			   }*/
		   }
		   
		   return true;
		}
</script>
</html>
