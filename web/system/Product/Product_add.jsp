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
		<form name="listForm" failAlert="true" action="business/Product.do?act=add" method="post" id="listForm" >
		  <input type="text" name="pTypeId" value="${pTypeId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right" >
									封面图片：
								</td>
								<td width="35%" align="left" >
										图片路径：<input type="text" style="width: 90% " name="pCoverImg" id="iconPhone" class="validate[] iptClass" /><br/>
									<div style="float: left;padding: 5px 5px 5px 5px">
										<input type="button" value="上传并预览" onclick="fileClickphone()" title="限制尺寸：196px*196px"/>
									</div>
									<span class="star">*图片尺寸： 400px * 600px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px"></div>
								</td>
								<td width="15%" align="right" >
									列表小图片：
								</td>
								<td width="35%" align="left" >
										图片路径：<input type="text" style="width: 90% "  name="pListImg" id="iconPhone2" class="validate[] iptClass" /><br/>
									<div style="float: left;padding: 5px 5px 5px 5px">
										<input type="button" value="上传并预览" onclick="fileClickphone2()" title=""/>
									</div>
									<span class="star">*图片尺寸： 90px * 100px</span>
									<div id="PhoneDiv2" style="float: left;padding-right: 20px"></div>
								</td>
								
							</tr>
							<tr>
								<td width="15%" align="right" >
									详情多图片：
								</td>
								<td width="85%" align="left" colspan="3">
									图片路径：<input type="text" style="width: 90% " name="viewImgs" id="iconPhone3" class="validate[] iptClass" /><br/>
									<div style="float: left;padding: 5px 5px 5px 5px">
										<input type="button" value="上传并预览" onclick="fileClickphone3()" title="限制尺寸：196px*196px"/>
									</div>
									<span class="star">*图片尺寸： 400px * 400px</span>
									<div id="PhoneDiv3" style="float: left;padding-right: 20px"></div>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									产品名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="pName" class="validate[required] iptClass" style="width: 98%" maxNum="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否促销：
								</td>
								<td width="35%" align="left">
									<input type="radio" value="0" checked="checked" name="isPromotion" /> 否
									<input type="radio" value="1" name="isPromotion" /> 是
								</td>
								<td width="15%" align="right">
									是否包邮 ：
								</td>
								<td width="35%" align="left">
									<input type="radio" value="0" checked="checked" name="pIsPostage" /> 否
									<input type="radio" value="1" name="pIsPostage" /> 是
								</td>
							</tr>
							
								<tr>
								<td width="15%" align="right">
									原价：
								</td>
								<td width="35%" align="left">
									<input type="text" name="pOriginalPrice" class="validate[required,length[0,10],custom[onlyNumber]] iptClass" />
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									现价：
								</td>
								<td width="35%" align="left">
									<input type="text" name="pPresentPrice" class="validate[required,length[0,10],custom[onlyNumber]] iptClass" />
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									邮费：
								</td>
								<td width="35%" align="left">
									<input type="text" name="pPostage" class="validate[required,length[0,10],custom[onlyNumber]] iptClass" />
									<span class="star">*</span>
								</td>
								<td rowspan="4" width="15%" align="right">
									级别类型：
								</td>
								<td rowspan="4" width="35%" align="left">
									<%--  <select name="levelType" class="default">
                                        ${levelTypeStr }
                                     </select> --%>
									<c:forEach items="${levelTypeStr }" var="t" varStatus="s">
										<input type="radio" name="levelType" value="${t.VALUE }" <c:if test="${s.index==0}">checked="checked"</c:if> />${t.NAME }<br/>
									</c:forEach>

								</td>
							</tr>
								<tr>
									<td width="15%" align="right">
										图书批次：
									</td>
									<td width="35%" align="left">
										<select name="batchTime" id="batchTime" ></select>
										<span class="star">*</span>
									</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									上架时间：
								</td>
								<td width="35%" align="left">
									<select name="marketTime" id="marketTime" >
										<option value="1">1月</option>
										<option value="2">2月</option>
										<option value="3">3月</option>
										<option value="4">4月</option>
										<option value="5">5月</option>
										<option value="6">6月</option>
										<option value="7">7月</option>
										<option value="8">8月</option>
										<option value="9">9月</option>
										<option value="10">10月</option>
										<option value="11">11月</option>
										<option value="12">12月</option>
									</select>
									<span class="star"> *</span>
								</td>
							</tr>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否展示：
								</td>
								<td width="35%" align="left">
									<input type="radio" value="1"  name="isShow" /> 否
									<input type="radio" value="0" name="isShow" checked="checked"/> 是
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									简介：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="brief" class="validate[] iptClass" style="width: 98%" />
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									特殊简介：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="specialBrief" class="validate[] iptClass" style="width: 98%" />
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									出版社：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="press" class="validate[] iptClass" style="width: 98%" />
									<span class="star"></span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									详细介绍：
								</td>
								<td width="35%" align="left" colspan="3">
									<!-- 加载编辑器的容器 -->
									<textarea id="ue_detail" name="pContent" style="width: 100%;"></textarea>	
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
			<html:form action="/business/Product.do?act=uploadImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone" onchange="ajaxFilephone()" accept="image/*" ></html:file>
			</html:form>
			<html:form action="/business/Product.do?act=uploadImgPath" styleId="formActionphone2"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone2" onchange="ajaxFilephone2()" accept="image/*" ></html:file>
			</html:form>
			<html:form action="/business/Product.do?act=uploadImgPath" styleId="formActionphone3"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="filephone3" onchange="ajaxFilephone3()" accept="image/*" ></html:file>
			</html:form>
		</div>
</body>
<script language="javascript" type="text/javascript">
	function back(){
		top.Dialog.close();
	}
	
	function flush(){
		// top.frmright.window.location.reload();
		$("#scrollContent").mask("表单提交中！");
		window.setTimeout(function(){
			$("#scrollContent").unmask();
		}, 3000);
	}
	
	  $(function(){
//		  initBatchTime();
	 	 UE.getEditor('ue_detail', {initialFrameWidth:'100%',initialFrameHeight : 300});
	 	 $("#ue_detail").attr("class","");
		  setTimeout(function () {
			  getBatch();
		  },200);
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
	  
	  function fileClickphone3(){
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
		    if($("#iconPhone3").val()==''){
				$("#iconPhone3").val(data.imgPath); 
		    }else{
		    	$("#iconPhone3").val($("#iconPhone3").val()+","+data.imgPath);
		    }
		    	$("#PhoneDiv3").append("<div style='float: left;padding: 5px 5px 5px 5px'><img src='"+data.imgPath+"' width='82px' height='82px' style='border:1px solid #ccc;padding:5px;'/></div>");
		    	$("#filephone3").val("");
		    }
	    });
	  }
	  
//	  function initBatchTime() {
//		  $("#batchTime").val(new Date().getFullYear());
//	  }

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
				$("#batchTime").render();

            },
            error:function () {
                top.Dialog.alert("批次列表获取失败！");
            }
        });

	  }
</script>


</html>
