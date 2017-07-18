<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Product.ProductActionForm"%>
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
 <!-- 配置文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
 <!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
 <!-- 异步上传图片 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	 
	 	<link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<!--缩略图样式-->
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
	 
</head>
<%
 ProductActionForm po = (ProductActionForm) request.getAttribute("ProductActionForm");
 %>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：编辑<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/Product.do?act=update" method="post"  onsubmit="return sub(this)" >
			<html:text property="id" style="display:none"></html:text>  
			<html:text property="pIsDel" style="display:none"></html:text>  
			<html:text property="pCreatetime" style="display:none"></html:text>
			<html:text property="pTypeId" style="display:none"></html:text>
			<html:text property="sales" style="display:none"></html:text>
			<html:text property="evaluateNum" style="display:none"></html:text>
			<html:text style="display:none" property="sysUserId" ></html:text>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									封面图片：
								</td>
								<td width="35%" align="left" >									 
									图片路径：<html:text property="pCoverImg" styleId="iconPhone" style="width:250px;" styleClass="validate[required] iptClass"></html:text>     	
									<div style="float: left;padding: 5px 5px 5px 5px">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸： 400px * 600px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									  <c:if test="${ProductActionForm.pCoverImg !='' }">
									   <% if(po.getpCoverImg().startsWith("http")){
									   %>
									    	<a href="javascript:thumbImgsDiv('<%=po.getpCoverImg()%>',0)" >
												<img src="<%= po.getpCoverImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
										    </a>
									   <% }else{
									   %>
									   		<a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getpCoverImg()%>',0)" >
												<img src="<%=path%>/<%= po.getpCoverImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
										   	</a>
									   <%} %>
									  </c:if>
									</div>
								</td>
								<td width="15%" align="right">
									列表图片：
								</td>
								<td width="35%" align="left" >									 
									图片路径：<html:text property="pListImg" styleId="iconPhone2" style="width:250px;" styleClass="validate[required] iptClass"></html:text>     	
									<div style="float: left;padding: 5px 5px 5px 5px">
										<input type="button" value="上传并预览" onclick="fileClickphone2()"/>
									</div>
									<span class="star">**图片尺寸： 90px * 100px</span>
									<div id="PhoneDiv2" style="float: left;padding-right: 20px">
									  <c:if test="${ProductActionForm.pListImg !='' }">
									    
									   <% if(po.getpListImg().startsWith("http://")){
									   %>
									    	<a href="javascript:thumbImgsDiv('<%=po.getpListImg()%>',0)" >
												<img src="<%= po.getpListImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
										    </a>
									   <% }else{
									   %>
									   		<a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getpListImg()%>',0)" >
												<img src="<%=path%>/<%= po.getpCoverImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
										   	</a>
									   <%} %>
									  
									  </c:if>
									</div>
								</td>
								
							</tr>
							<tr>
								<td width="15%" align="right" >
									详情多图片：
								</td>
								<td width="35%" align="left" colspan="3">
									图片路径：<input type="text" style="width: 90% " name="viewImgs" id="iconPhone3" class="validate[] iptClass" value="${ProductActionForm.viewImgs }"/>
									<div style="float: left;padding: 5px 5px 5px 5px">
										<input type="button" value="上传并预览" onclick="fileClickphone3('','')" title="限制尺寸：196px*196px"/>
									</div>
									<span class="star">**图片尺寸： 400px * 400px</span>
									<div id="PhoneDiv3" style="float: left;padding-right: 20px">
										<% 
										
										if(po.getViewImgs()!=null && !po.getViewImgs().equals("")){
											String imgs[] = po.getViewImgs().split(",");
											  
											  for(int j = 0;j<imgs.length;j++){
										%>
										<div style="float: left;padding: 5px 5px 5px 5px">
										    <% if(imgs[j].startsWith("http://")){
										   %>
										    	<a href="javascript:thumbImgsDiv('<%=imgs[j]%>',0)" >
													<img src="<%= imgs[j]%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
											    </a>
										   <% }else{
										   %>
										   		<a href="javascript:thumbImgsDiv('<%=path%>/<%=imgs[j]%>',0)" >
													<img src="<%=path%>/<%= imgs[j]%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
											   	</a>
										   <%} %>
										   
										  <%--  <a href="javascript:thumbImgsDivList('<%=po.getViewImgs()%>',<%=j %>,'<%=path%>')" >
											<img src="<%=path%>/<%= imgs[j]%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;" id='imgptId<%=j %>'/>
										   </a> --%>
											<div align="center">
								  	   	        <a href="javascript:" onclick="delImg(this,'<%= imgs[j]%>')">删除</a>
								  	   	        <a href="javascript:" onclick="fileClickphone3('<%= imgs[j]%>',<%=j %>)">修改</a>
							  	   	        </div>
										</div>
										<%  
											  }
											}
										%>
									</div>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									产品名称：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<input type="text" name="pName" style="width:98%" styleClass="validate[required] iptClass" value="${ProductActionForm.pName }" maxNum="100">     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否包邮 0 否 1是：
								</td>
								<td width="35%" align="left">									 
									<html:radio property="pIsPostage" value="0">否</html:radio>
									<html:radio property="pIsPostage" value="1">是</html:radio>
								</td>
								<td width="15%" align="right">
									是否促销：
								</td>
								<td width="35%" align="left">
									<html:radio property="isPromotion" value="0">否</html:radio>
									<html:radio property="isPromotion" value="1">是</html:radio>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									原价：
								</td>
								<td width="35%" align="left">									 
									<html:text property="pOriginalPrice" style="width:250px" styleClass="validate[required,length[0,10],custom[onlyNumber]] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									现价：
								</td>
								<td width="35%" align="left">									 
									<html:text property="pPresentPrice" style="width:250px" styleClass="validate[required,length[0,10],custom[onlyNumber]] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>

								<td width="15%" align="right">
									邮费：
								</td>
								<td width="35%" align="left">									 
									<html:text property="pPostage" style="width:250px" styleClass="validate[required,length[0,10],custom[onlyNumber]] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
								<td rowspan="4" width="15%" align="right">
									级别类型：
								</td>
								<td rowspan="4" width="35%" align="left">
								   <%-- <html:select property="levelType">
								    ${levelTypeStr }
								   </html:select> --%>
								   <c:forEach items="${levelTypeStr }" var="t">
									 <html:radio property="levelType" value="${t.VALUE }">${t.NAME }</html:radio><br/>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right">
									图书批次：
								</td>
								<td  width="35%" align="left">
									<input type="hidden" value="${ProductActionForm.batchTime}" id="batchValue"/>
									<select name="batchTime" id="batchTime"  >
									</select>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									上架时间：${string2}
								</td>
								<td width="35%" align="left">
									<select name="marketTime" id="marketTime" >
										<option value="01" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 01 }">selected="selected"</c:if>>1月</option>
										<option value="02" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 02 }">selected="selected"</c:if>>2月</option>
										<option value="03" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 03 }">selected="selected"</c:if>>3月</option>
										<option value="04" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 04 }">selected="selected"</c:if>>4月</option>
										<option value="05" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 05 }">selected="selected"</c:if>>5月</option>
										<option value="06" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 06 }">selected="selected"</c:if>>6月</option>
										<option value="07" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 07 }">selected="selected"</c:if>>7月</option>
										<option value="08" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 08 }">selected="selected"</c:if>>8月</option>
										<option value="09" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)== 09 }">selected="selected"</c:if>>9月</option>
										<option value="10" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)==10}">selected="selected"</c:if>>10月</option>
										<option value="11"  <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)==11}">selected="selected"</c:if>>11月</option>
										<option value="12" <c:if test="${fn:substring(ProductActionForm.marketTime, 5, 7)==12}">selected="selected"</c:if>>12月</option>
									</select>
									<span class="star"> *</span>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right">
									是否展示：
								</td>
								<td width="35%" align="left">
									<html:radio property="isShow" value="1">否</html:radio>
									<html:radio property="isShow" value="0">是</html:radio>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									简介：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<html:text property="brief" style="width:98%" styleClass="validate[] iptClass"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									特殊简介：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<html:text property="specialBrief" style="width:98%" styleClass="validate[] iptClass"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									出版社：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<html:text property="press" style="width:98%" styleClass="validate[] iptClass"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
								
							
								<tr>
								<td width="15%" align="right">
									详细介绍：
								</td>
								<td width="35%" height="100%" align="left" colspan="3"  >	
									 <textarea class="as" id="ue_detail" name="pContent" style="width: 100%;">${ProductActionForm.pContent}</textarea>
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
							<input type="button" value=" 关闭" onclick="back()"/> 
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
			<input type="text" id="imgsId"/>
			<input type="text" id="imgspath"/>
		
			<html:form action="/business/Product.do?act=uploadImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone" onchange="ajaxFilephone()" accept="image/*" ></html:file>
			</html:form>
			<html:form action="/business/Product.do?act=uploadImgPath" styleId="formActionphone2"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone2" onchange="ajaxFilephone2()" accept="image/*" ></html:file>
			</html:form>
			<html:form action="/business/Product.do?act=uploadImgPath" styleId="formActionphone3"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone3" onchange="ajaxFilephone3('',this)" accept="image/*" ></html:file>
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
     window.location.reload();
    }
	  $(function(){
//		  $.ajaxSetup({async: false});
	 	 UE.getEditor('ue_detail', {initialFrameWidth:'100%',initialFrameHeight : 300});
	 	 $("#ue_detail").attr("class","");
		  setTimeout(function () {getBatch();},200)
	  	});
	  	
	  	//phone端点击上传，异步上传图片
	function fileClickphone(){
  		$("#filephone").click();
 	}
 	//异步上传，返回图片上传的路径
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
	    	$("#PhoneDiv").html("<img src='"+data.imgPath+"' width='82px' height='82px' style='border:1px solid #ccc;padding:5px;'/>");
	    	$("#filephone").val("");
	    }
	    });
	   }
	   
	   function fileClickphone2(){
  		$("#filephone2").click();
 	}
 	//异步上传，返回图片上传的路径
	function ajaxFilephone2(){
	  	var path = $("#filephone2").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	 $('#formActionphone2').ajaxSubmit(function(data){
	    if(data.result){
			$("#iconPhone2").val(data.imgPath); 
	    	$("#PhoneDiv2").html("<img src='"+data.imgPath+"' width='82px' height='82px' style='border:1px solid #ccc;padding:5px;'/>");
	    	$("#filephone2").val("");
	    }
	    });
	   }
	   
	function fileClickphone3(url,id){
		 $("#imgsId").val(id);
		  $("#imgspath").val(url);
  		$("#filephone3").click();
 	}
	   //异步上传，返回图片上传的路径
	function ajaxFilephone3(){
	  	var path = $("#filephone3").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
		 $('#formActionphone3').ajaxSubmit(function(data){
		    if(data.result){
		    	 var imgsId = $("#imgsId").val();
		  		 var imgspath = $("#imgspath").val();
			    if(imgspath==''){
				    if($("#iconPhone3").val()==''){
						$("#iconPhone3").val(data.imgPath); 
				    }else{
				    	$("#iconPhone3").val($("#iconPhone3").val()+","+data.imgPath);
				    }
				    	$("#PhoneDiv3").append("<div style='float: left;padding: 5px 5px 5px 5px'><img src='"+data.imgPath+"' style='width: 80px;'/></div>");
				    	$("#filephone3").val("");
			    }else{
			    	var path = $("#iconPhone3").val();
			    	$("#iconPhone3").val(path.replace(imgspath,data.imgPath));
			    	$("#imgptId"+imgsId).attr("src",data.imgPath);
			    }
			 }
	    });
	  }
	  
	  	/*图片删除操作*/	
 	function delImg(t,url){
    		var path = $("#iconPhone3").val();
			    $("#iconPhone3").val(path.replace(url,""));
			    $(t).parent().parent().html("");
  	}
	function getBatch() {
		$.ajax({
			type: 'POST',
			url: '<%=basePath%>business/Batch.do?act=getBatch',
			data: {'batchName':$("#batchName").val()},
			dataType:"json",
			success: function (data) {
				var batch = eval(data);
				var selData="{'list':[";
				for(var i = 0;i<batch.length;i++){
					var name = batch[i].name;
					if(i==(batch.length-1)){
						selData +="{'value':'"+name+"','key':'"+name+"'}";
					}else{
						selData +="{'value':'"+name+"','key':'"+name+"'},";
					}
				}
				selData += "]}";
				selData = eval("("+selData+")");
				$("#batchTime").data("data",selData);
				$("#batchTime").setValue($("#batchValue").val());
				$("#batchTime").render();

			},
			error:function () {
				top.Dialog.alert("批次列表获取失败！");
			}
		});

	}
</script>
</html>
