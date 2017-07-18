<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.News.NewsActionForm"%>
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
	<link rel="stylesheet" type="text/css" href="<%=path%>/deboeditor/dist/css/wangEditor.min.css">
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
 <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
	<%--<!-- 配置文件 -->--%>
	<%--<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>--%>
	<%--<!-- 编辑器源码文件 -->--%>
	<%--<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>--%>
	<!-- 异步上传图片 -->
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

</head>
<body class="ali02" style="height:100%">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：新增新闻<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" id="listForm" action="business/News.do?act=add" method="post" enctype="multipart/form-data" id="listForm" target="frmright" >
			<input type="hidden" id="isShow" name="isShow">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>新闻	基本信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="10%" align="right">
									标题：
								</td>
								<td width="90%" align="left" colspan="3">
									<input type="text" name="title" style="width: 95%" class="validate[required] iptClass" maxNum="50"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									简介：
								</td>
								<td width="90%" align="left" colspan="3">
									<textarea name="subtitle" style="width: 95%" maxNum="200"></textarea>
									<span class="star"></span>
								</td>
							</tr>
							<tr style="display: none" width="15%">
								
								<td  align="right" width="15%">
									来源：
								</td>
								<td  align="left" width="35%">
									<input type="text" name="source" class="validate[] iptClass" maxlength="25"/>
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td  align="right" width="15%" rowspan="2">
									列表图片：
								</td>
								<td  align="left" width="35%" rowspan="2">
									<div id="ptqnsubmit">
									图片路径：<input  id = "qnimageurl" type="text" style="width: 100%" name="phoneImg" id="iconPhone" class="validate[] iptClass" /><br/>
									<div style="float: left">
										<input type="button"  id = "qnsubmit" value="上传并预览" />
									</div>
									<span class="star">*图片尺寸：80px : 60px</span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px"></div>
									</div>
								</td>
								<td  align="right" >
									 发布者：
								</td>
								<td  align="left" width="35%">
									<input type="text" name="author" class="validate[] iptClass" maxlength="15" value="星火小编"/>
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td  align="center"  colspan="2">
								<span title="此项用于定时展示，草稿状态下无效" >文章将在</span>
									<input type="text" name="ptime" id="ptime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" maxlength="15"  title="此项用于定时展示，草稿状态下无效"  />
									<span title="此项用于定时展示，草稿状态下无效">后展示</span>
								</td>
							</tr>
								<tr >
									<td align="right" width="10%">
										选择标签:<input type="checkbox" id="checkAll" onclick="CheckAll()"/>
									</td>
									<td colspan="3" width="90%">
									  <c:forEach items="${lbs }" var="lb">
									    <div style="padding: 5px 5px 5px 5px; float: left;">
									    	<input type="checkbox" name="clb" value="${lb.id }"/>${lb.lableName }
									    </div>
									  </c:forEach>
									</td>
								</tr>
								<tr >
									<td align="right"width="10%">
										新闻详情:
									</td>
									<td colspan="3" width="90%">
										<div style=" height:450px; overflow:auto; ">
											<textarea  id="ue_content1" name="content" style="width: 100%;height:440px;"  ></textarea>
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
							<input type="button" value=" 提 交 " onclick="release()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value=" 存草稿 " onclick="saveDraft()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭" onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:《发布时间》项，在发布后用于定时在所填时间点发布，此项在草稿状态下无效！</div>
				<br />
				<br />
			</div>
		</form>
		</div>
		
		 <div style="display: none">
				<html:form action="business/News.do?act=updateImgPath&type=phone" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
				
				<html:form action="business/News.do?act=updateImgPath&type=pad" styleId="formActionpad"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filepad" onchange="ajaxFilepad()"></html:file>
				</html:form>
				
			</div>


</body>


<%--<script type="text/javascript" src="/dist/js/jquery-1.9.1.min.js"></script>--%>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/wangEditor/js/wangEditor.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
<script type="text/javascript">


    $(function(){
        uploadInit1();
        new deboEditor().init("ue_content1");
    })
</script>


<script language="javascript" type="text/javascript">

 function release(){

	 $("#isShow").val(1);
	 $('#listForm').ajaxSubmit(function(data){
		 top.frmright.window.location.reload();
			 top.Dialog.close();
	 });
    }

 function saveDraft() {
	 $("#isShow").val(0);
	 $('#listForm').ajaxSubmit(function(data){
		 top.frmright.window.location.reload();
			 top.Dialog.close();
	 });
 }
	function back(){
		top.Dialog.close();
	}
	
	function initDate() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		if(month<10){month = "0"+month };
		var day = date.getDate();
		if(day<10){day = "0"+day};
		var hour = date.getHours();
		if(hour<10){hour = "0"+hour};
		var minutes = date.getMinutes();
		if(minutes<10){ minutes = "0"+minutes};
		var seconds = date.getSeconds();
		if(seconds<10){seconds = "0"+seconds};
		$("#ptime").val(year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+seconds);
	}

	  $(function(){
		  initDate();
//	  		UE.getEditor('ue_content', {initialFrameWidth:'100%',initialFrameHeight : 300});
//	  		$("#ue_content").attr("class","");
	  	});

 function CheckAll(){
	if($("#checkAll").attr("checked")){
	  $("input[name='clb']").attr("checked",true);      
	}else{
	  $("input[name='clb']").attr("checked",false);      
	}
  }

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

        uploadInit();

	   }
	
	   function fileClickpad(){
  		 	$("#filepad").click();
 		}
 		
	function ajaxFilepad(){
	  	var path = $("#filepad").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	  
	 $('#formActionpad').ajaxSubmit(function(data){
	    if(data.result){
             $("#iconPad").val(data.imgPath);
             $("#PadDiv").html("<img src='"+data.imgPath+"' style='width: 80px;'/>");
             $("#filepad").val("");
         }
	    });
	}



 function uploadInit1() {

     var uploader1 = Qiniu.uploader({
         runtimes: 'html5,flash,html4',
         browse_button: "qnsubmit",
         uptoken_url: '<%=path%>/business/common/storage.do?act=getQiniuToken',

         domain: 'http://oanafcpi7.bkt.clouddn.com/',

         container: "ptqnsubmit" ,
         max_file_size: '100mb',
         flash_swf_url: 'js/plupload/Moxie.swf',
         filters: {
             mime_types: [
                 {title: "图片文件", extensions: "jpg,gif,png,bmp"}
             ]
         },
         max_retries: 3,
         dragdrop: true,
         drop_element: 'editor-container',
         chunk_size: '4mb',
         auto_start: true,
         init: {
             'FilesAdded': function (up, files) {
                 plupload.each(files, function (file) {

                     printLog('on FilesAdded');
                 });
             },
             'BeforeUpload': function (up, file) {

                 printLog('on BeforeUpload');
             },
             'UploadProgress': function (up, file) {

//                 editor.showUploadProgress(file.percent);
             },
             'FileUploaded': function (up, file, info) {

                 printLog(info);


                 var domain = up.getOption('domain');
                 var res = $.parseJSON(info);
                 var sourceLink = domain + res.key;
                 printLog(sourceLink);
                 $("#iconPhone").val(sourceLink);
                 $("#PhoneDiv").html("<img src='"+sourceLink+"' style='width: 80px;'/>");
                 $("#filephone").val("");
                 $("#qnimageurl").val(sourceLink)
             },
             'Error': function (up, err, errTip) {

                 printLog('on Error');
             },
             'UploadComplete': function () {
                 printLog('on UploadComplete');
//                 editor.hideUploadProgress();
             }

         }
     });
 }



</script>


</html>
