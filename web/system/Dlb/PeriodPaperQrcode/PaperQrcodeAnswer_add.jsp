<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Dlb.PeriodPaperQrcode.PeriodPaperQrcode"%>

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
		<form name="listForm" action="<%=path%>/business/PeriodPaperQrcode.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>课程信息</legend> 
					<div style="overflow:auto;">
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									试卷名称：
								</td>
								<td width="35%" align="left">
									<input type="text" name="name" id="name"/>
								</td>
								<td width="15%" align="right">
									四六级：
								</td>
								<td width="35%" align="left">
									<select name="section" id="section">
									  <option value="">全部</option>
									  <option value="01">四级</option>
									  <option value="02">六级</option>
									</select>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>二维码内容：
								</td>
								<td width="35%" align="left" >
								<input type="text" style="width: 100%" name="qrcodeContent" id="qrcodeContent" class="validate[] iptClass" /><br/>
								</td>
								<td width="15%" align="right">
									<span class="star">*</span>二维码图片：
								</td>
								<td width="35%" align="left" >
								 	<input type="text" name="qrcodeUrl" id="qrcodeUrl" class="validate[] iptClass" style="width:70%;"/>
									<input id="upimgs" type="button" value="上传预览"/>
									<br/>
									<div id="PhoneDiv" style="float: left;padding-right: 20px"></div>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									试卷分类：
								</td>
								<td width="35%" align="left" colspan="3">
									<select name="period" id="period">
									  <option value="">全部</option>
									  <option value="1">学前测试</option>
									  <option value="2">学中测试</option>
									  <option value="3">学末测试</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="15%" align="center" colspan="4">
									试卷答案：
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									<span class="star">*</span>听力部分：
								</td>
								<td width="35%" align="left">
									01.选项:<input type="text" align="center" id="ncName1" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									02.选项:<input type="text" align="center" id="ncName2" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									03.选项:<input type="text" align="center" id="ncName3" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									04.选项:<input type="text" align="center" id="ncName4" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									05.选项:<input type="text" align="center" id="ncName5" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									06.选项:<input type="text" align="center" id="ncName6" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									07.选项:<input type="text" align="center" id="ncName7" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									08.选项:<input type="text" align="center" id="ncName8" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									09.选项:<input type="text" align="center" id="ncName9" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									10.选项:<input type="text" align="center" id="ncName10" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									11.选项:<input type="text" align="center" id="ncName11" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									12.选项:<input type="text" align="center" id="ncName12" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									13.选项:<input type="text" align="center" id="ncName13" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									14.选项:<input type="text" align="center" id="ncName14" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									15.选项:<input type="text" align="center" id="ncName15" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									16.选项:<input type="text" align="center" id="ncName16" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									17.选项:<input type="text" align="center" id="ncName17" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									18.选项:<input type="text" align="center" id="ncName18" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									19.选项:<input type="text" align="center" id="ncName19" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									20.选项:<input type="text" align="center" id="ncName20" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									21.选项:<input type="text" align="center" id="ncName21" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									22.选项:<input type="text" align="center" id="ncName22" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									23.选项:<input type="text" align="center" id="ncName23" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									24.选项:<input type="text" align="center" id="ncName24" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									25.选项:<input type="text" align="center" id="ncName25" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td width="15%" align="right">
									<span class="star">*</span>阅读部分：
								</td>
								<td width="35%" align="left">
									26.选项:<input type="text" align="center" id="ncName26" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									27.选项:<input type="text" align="center" id="ncName27" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									28.选项:<input type="text" align="center" id="ncName28" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									29.选项:<input type="text" align="center" id="ncName29" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									30.选项:<input type="text" align="center" id="ncName30" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									31.选项:<input type="text" align="center" id="ncName31" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									32.选项:<input type="text" align="center" id="ncName32" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									33.选项:<input type="text" align="center" id="ncName33" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									34.选项:<input type="text" align="center" id="ncName34" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									35.选项:<input type="text" align="center" id="ncName35" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									36.选项:<input type="text" align="center" id="ncName36" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									37.选项:<input type="text" align="center" id="ncName37" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									38.选项:<input type="text" align="center" id="ncName38" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									39.选项:<input type="text" align="center" id="ncName39" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									40.选项:<input type="text" align="center" id="ncName40" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									41.选项:<input type="text" align="center" id="ncName41" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									42.选项:<input type="text" align="center" id="ncName42" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									43.选项:<input type="text" align="center" id="ncName43" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									44.选项:<input type="text" align="center" id="ncName44" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									45.选项:<input type="text" align="center" id="ncName45" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									46.选项:<input type="text" align="center" id="ncName46" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									47.选项:<input type="text" align="center" id="ncName47" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									48.选项:<input type="text" align="center" id="ncName48" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									49.选项:<input type="text" align="center" id="ncName49" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									50.选项:<input type="text" align="center" id="ncName50" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									51.选项:<input type="text" align="center" id="ncName51" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									52.选项:<input type="text" align="center" id="ncName52" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
									53.选项:<input type="text" align="center" id="ncName53" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									54.选项:<input type="text" align="center" id="ncName54" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									55.选项:<input type="text" align="center" id="ncName55" class="validate[required] iptClass" style="width: 10%"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									翻译答案：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:410px; overflow:auto; ">
										 <textarea  id="translate" name="translate" style="width: 100%"  ></textarea> 
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									写作答案：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:410px; overflow:auto; ">
										 <textarea  id="writing" name="writing" style="width: 100%"  ></textarea> 
									</div>
								</td>
							</tr>
						</table>
						</div>
					</fieldset>
					  <input type="hidden" name="hearingValues" id="hearingValues"/>
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
		var name=$("#name").val();
		var section=$("#section").find("option:selected").val();
		var qrcodeContent=$("#qrcodeContent").val();
		var qrcodeUrl=$("#qrcodeUrl").val();
		var period=$("#period").find("option:selected").val();
		if(name==""){
			alert("试卷名称不能为空！");
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
		if(period==""){
			alert("请试卷分类!");
			return;
		}
		
		var hearingValues="";
		var ncNameValue="";
		for(var a=1;a<56;a++){
			ncNameValue=$("#ncName"+a+"").val();
			if(ncNameValue!=""){
				hearingValues+=a+"."+ncNameValue+",";
			}else{
				alert("第"+a+"题没有填写答案");
				return ;
			}
		}
		$("#hearingValues").val(hearingValues);
		var translate=$("#translate").val();
		var writing=$("#writing").val();
		/* if(translate==""){
			alert("请输入翻译答案！");
			return ;
		}
		if(writing==""){
			alert("请输入写作答案！");
			return ;
		} */
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
