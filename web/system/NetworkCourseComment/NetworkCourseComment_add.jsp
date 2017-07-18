<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NetworkCourseComment.NetworkCourseComment"%>

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
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/NetworkCourseComment.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>课程信息</legend> 
					<div style="overflow:auto;">
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									课程级别：
								</td>
								<td width="35%" align="left">
									<select name="ncLevel">
									  ${ncLevel }
									</select>
								</td>
								<td width="15%" align="right">
									课程大类：
								</td>
								<td width="35%" align="left">
									<select name="ncLevelType">
									  ${ncLevelType }
									</select>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>封面图片：
								</td>
								<td width="35%" align="left" >
								 图片路径：<input type="text" style="width: 100%" name="ncImg" id="iconPhone" class="validate[] iptClass" /><br/>
									<div style="float: left">
										<input type="button" value="选择图片上传" onclick="fileClickphone()"/>
									</div>
									<span class="star">*图片尺寸： 120px : 96px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									</div>
								</td>
								<td width="15%" align="right">
									宣传视频：
								</td>
								<td width="35%" align="left">
									 图片路径：<input type="text" style="width: 100%" name="ncPreviewLink" id="ncPreviewLink" class="validate[] iptClass" /><br/>
									<div style="float: left">
										<input type="button" value="上传URL" onclick="ncPreviewLink()" disabled="disabled"/>
									</div>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>网课名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="ncName" id="ncName" class="validate[required] iptClass" style="width: 98%"  maxNum="50"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>选取老师：
								</td>
								<td width="35%" align="left" colspan="3">
									<c:forEach items="${teachers }" var="tc">
									  <div style="padding: 5px 5px 5px 5px;float: left;" >
									     <input type="checkbox" name="tckbox" value="${tc.id }"/>${tc.name }
									  </div>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									授课内容：
								</td>
								<td width="35%" align="left" colspan="3">
									<textarea style="width: 98%;height: 45px"  name="ncBrief" maxNum="200"></textarea>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									课程形式：
								</td>
								<td width="35%" align="left">
									<input type="radio" id="ncType" name="ncType" value="0" checked="checked" onclick="ntChk(0)"/>录播
									<input type="radio" id="ncType" name="ncType" value="1" onclick="ntChk(1)" />直播
								</td>
								<td width="15%" align="right">
									直播间ID：
								</td>
								<td width="35%" align="left">
									<input type="text" name="ncLiveRome" id="ncLiveRome"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>直播链接：
								</td>
								<td width="35%" align="left" colspan="3">
								   <input type="text" name="ncLiveLink" id="ncLiveLink" style="width: 98%"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									预约开放时间：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="reserveTime" id="reserveTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td width="15%" align="right">
									<span class="star">*</span>课程有效期：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="offShelfTime" id="offShelfTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>开始时间：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="ncLiveTime" id="ncLiveTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td width="15%" align="right">
									<span class="star">*</span>结束时间：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="ncEndTime" id="ncEndTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>开售时间：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="saleTime" id="saleTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td width="15%" align="right">
									停售时间：
								</td>
								<td width="35%" align="left" >
									<input type="text" name="saleEndTime" id="saleEndTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否免费：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="radio" id="isFree" name="isFree" checked="checked" value="0"  onclick="freeChk(0)"/>否
									<input type="radio" id="isFree" name="isFree"  value="1"  onclick="freeChk(1)"/>是
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									是否限时免费：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="radio" id="isLimitFree" name="isLimitFree" value="0" onclick="disableSelect()" checked="checked" disabled="disabled"/>否
									<input type="radio" id="isLimitFree" name="isLimitFree" value="1" onclick="networkMoney1()" disabled="disabled"/>是
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									限时免费时间段：
								</td>
								<td width="35%" align="left" colspan="3">
									开始时间：<input type="text" name="limitStartTime" id="limitStartTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" disabled="disabled"/>
									----
									结束时间：<input type="text" name="limitEndTime" id="limitEndTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" disabled/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>原价：
								</td>
								<td width="35%" align="left">
									<input type="text" name="originalPrice" id="originalPrice" />
								</td>
								<td width="15%" align="right">
									<span class="star">*</span>现价：
								</td>
								<td width="35%" align="left">
									<input type="text" name="currentPrice" id="currentPrice" />
								</td>
							<tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>课时数：
								</td>
								<td width="35%" align="left">
								   <input type="text" name="catalogNumber" id="catalogNumber" inputMode="numberOnly"  watermark="限制输入正整数"/>
								</td>
								 <td width="15%" align="right">
                          		    <span class="star">*</span>  限购人数：
                                </td>
                                <td width="35%" colspan="3" align="left">
                                    <input type="text" name="limitNumber" id="limitNumber"  inputMode="numberOnly" watermark="限制输入正整数" />
                                </td>
							</tr>
							<tr>
								<td width="15%" align="right">
									有无图书：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="isGiftBook" value="0" checked="checked" onclick="isGB(0)"/>否
									<input type="radio" name="isGiftBook" value="1" onclick="isGB(1)"/>是
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" inputMode="numberOnly"  watermark="限制输入正整数" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									图书价格：
								</td>
								<td width="35%" align="left">
									<input type="text" name="giftBookPrice" id="giftBookPrice" disabled="disabled"/>
								</td>
								<td width="15%" align="right">
									QQ群聊：
								</td>
								<td width="35%" align="left">
									<input type="text" name="ncQqGroup"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									老师介绍：
								</td>
								<td width="35%" align="left" colspan="3">
									<div>
										 <textarea  id="teacherIntroduce" name="teacherIntroduce" style="width: 100%"   maxNum="500"></textarea> 
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									课程介绍：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:410px; overflow:auto; ">
										 <textarea  id="ncIntroduce" name="ncIntroduce" style="width: 100%"  ></textarea> 
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
				<html:form action="business/NetworkCourseComment.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
	function flush(){
		var iconPhone=$("#iconPhone").val();
		var ncName=$("#ncName").val();
		var ncType=$("#ncType:checked").val();
		var ncLiveLink=$("#ncLiveLink").val();
		var ncLiveRome=$("#ncLiveRome").val();
		var reserveTime=$("#reserveTime").val();
		var offShelfTime=$("#offShelfTime").val();
		var ncLiveTime=$("#ncLiveTime").val();
		var ncEndTime=$("#ncEndTime").val();
		var saleTime=$("#saleTime").val();
		var saleEndTime=$("#saleEndTime").val();
		var isFree = $('input:radio[name="isFree"]:checked').val();
		var isLimitFree = $('input:radio[name="isLimitFree"]:checked').val();
		var limitStartTime=$("#limitStartTime").val();
		var limitEndTime=$("#limitEndTime").val();
		var originalPrice=$("#originalPrice").val();
		var currentPrice=$("#currentPrice").val();
		var catalogNumber=$("#catalogNumber").val();
		var limitNumber=$("#limitNumber").val();
		if(iconPhone.replace(/(^\s*)/g, "").length==0){
			 top.Dialog.alert("请选择上传封面图片",285,285);
			//alert("");
			return ;
		} 
		if(ncName.replace(/(^\s*)/g, "").length==0){
			top.Dialog.alert("课程名称不能为空",285,285);
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
		      top.Dialog.alert("请选择至少一位最多十位讲师",285,285);
		      return "";
		}
		//判断时间
		if(offShelfTime.replace(/(^\s*)/g, "").length==0){
			top.Dialog.alert("课程有效期不能为空",285,285);
		    return "";
		}
		if(ncType==1){
			if(ncLiveRome.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("直播间ID不能为空",285,285);
			    return "";
			}
			if(ncLiveLink.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("直播时直播路径不能为空",285,285);
			    return "";
			}
			if(ncLiveTime.replace(/(^\s*)/g, "").length>0 && ncEndTime.replace(/(^\s*)/g, "").length>0){
				 var startdate = new Date((ncLiveTime).replace(/-/g,"/"));  
			     var enddate = new Date((ncEndTime).replace(/-/g,"/")); 
			     if(enddate < startdate){
			    	 top.Dialog.alert("开始时间不能大于结束时间",285,285);
					    return "";
			     }
			}else{
				top.Dialog.alert("直播时直播开始或结束时间不能为空",285,285);
			    return "";
			}
			if(saleTime.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("课程开售时间不能为空",285,285);
			    return "";
			}
		}
		if(ncType==1){
			if(limitNumber=='限制输入正整数'){
				top.Dialog.alert("限购人数不能为空",285,285);
			    return "";
			}
		}
		if(isLimitFree==1){
			if(limitStartTime.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("限时免费开始时间不能为空",285,285);
			    return "";
			}
			if(limitEndTime.replace(/(^\s*)/g, "").length==0){
				top.Dialog.alert("限时免费结束时间不能为空",285,285);
			    return "";
			}
		}
		if(originalPrice.replace(/(^\s*)/g, "").length==0){
			top.Dialog.alert("原价不能为空",285,285);
		    return "";
		}
		if(currentPrice.replace(/(^\s*)/g, "").length==0){
			top.Dialog.alert("现价不能为空",285,285);
		    return "";
		}
		if(catalogNumber=='限制输入正整数'){
			top.Dialog.alert("课时数不能为空",285,285);
		    return "";
		}
		if(ncType==0){
			if(saleTime.replace(/(^\s*)/g, "").length==0){
				$("#saleTime").val("2000-01-01 01:01:01");
			}
		}
		if(reserveTime.replace(/(^\s*)/g, "").length==0){
			$("#reserveTime").val("2000-01-01 01:01:01");
		}
		if(saleEndTime.replace(/(^\s*)/g, "").length==0){
			$("#saleEndTime").val("2000-01-01 01:01:01");
		}
		$("#scrollContent").mask("表单提交中！");
		$("#listForm").submit();
		
	}
	
	function back(){
		top.Dialog.close();
	}
	
	//判断直播还是录播
	function ntChk(value){
	  if(value==0){
	 	  $("#ncLiveTime").val("");
	 	  $("#ncLiveLink").val("");
	 	  $("#ncLiveRome").val("");
	 	  $("#ncEndTime").val("");
	 	  
		  $("#ncLiveTime").attr("disabled","disabled");
		  $("#ncEndTime").attr("disabled","disabled");
		  $("#ncLiveRome").attr("disabled","disabled");
		  $("#ncLiveLink").attr("disabled","disabled");
	  }else{
	      $("#ncLiveTime").removeAttr("disabled");
	      $("#ncEndTime").removeAttr("disabled");
	      $("#ncLiveRome").removeAttr("disabled");
	      $("#ncLiveLink").removeAttr("disabled"); 
	      
	  }
	}
	//是否有图书
	function isGB(value){
		if(value==0){
			$("#giftBookPrice").attr("disabled","disabled");
		}else{
			$("#giftBookPrice").removeAttr("disabled");
		}
	}
	//判断是否免费
	function freeChk(value){
	  if(value==0){//否
	     $("[name='isLimitFree']").attr("disabled","disabled");
	  }else{
	  	 $("[name='isLimitFree']").removeAttr("disabled");
	  }
	}
	/*限时免费*/
	function networkMoney1(){
	    $('#limitStartTime').removeAttr("disabled"); 
	    $('#limitEndTime').removeAttr("disabled"); 
	    document.getElementById("currentPrice").value="0";
	}
	function disableSelect(){
		$("#limitStartTime").val("");
		 $("#limitEndTime").val("");
	    $("#limitStartTime").attr("disabled", "false");
	    $("#limitEndTime").attr("disabled", "false");
	}
	

	  $(function(){
	 		ntChk(0);
	 		freeChk(0);
	  		UE.getEditor('ncIntroduce', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		
	  		$("#ncIntroduce").attr("class","");
	  		
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
