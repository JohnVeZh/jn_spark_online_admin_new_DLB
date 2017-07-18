<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.CommunityPost.CommunityPostActionForm"%>
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

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
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
		<form name="listForm" action="business/CommunityPost.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
							<tr>
								<%--<td width="15%" align="right">--%>
									<%--详情图片：--%>
								<%--</td>--%>
								<%--<td width="35%" align="left">--%>
									<%--<input type="text" style="width:100%" name="viewImgpath" id="iconPhone" class="validate[] iptClass" />--%>
									<%--<div style="float: left">--%>
										<%--<input type="button" value="上传并预览" onclick="fileClickphone()"/>--%>
									<%--</div>--%>
									<%--<span class="star">*图片尺寸：100px * 100px</span>--%>
									<%--<div id="PhoneDiv" style="float: left;padding-right: 20px"></div>--%>
								<%--</td>--%>
								<td width="6%" align="right">
									列表图片：
								</td>
								<td width="35%" align="left" colspan="3">
									<div id="ptqnsubmit2">
									<input type="text" style="width:100%" name="imgpathList" id="iconPhones" class="validate[] iptClass" />
									<div style="float: left">
										<input id = "qnsubmit2" type="button" value="上传并预览" />
									</div>
									<span class="star">*图片尺寸： 80px : 60px</span>
									<div id="PhoneDivs" style="float: left;padding-right: 20px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									标题：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="title" class="validate[required] iptClass" style="width: 97%" maxNum="100"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									客服展示：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="radio" name="isShow" value="0" checked="checked" /> 隐藏
									<input type="radio" name = "isShow" value="1" /> 展示
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									简介：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="brief" class="validate[required] iptClass" style="width: 97%" maxNum="200"/>
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right" >
									内容：
								</td>
								<td width="35%" align="left" colspan="3">
									<div style=" height:450px; overflow:auto; ">
										 <textarea  id="ue_content3" name="countent" style="width: 100% ; height:440px;"  ></textarea>
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
				<html:form action="business/CommunityPost.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
				
				<html:form action="business/CommunityPost.do?act=updateImgPath" styleId="formActionphones"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephones" onchange="ajaxFilephones()"></html:file>
				</html:form>
				
		</div>
</body>



<script type="text/javascript" src="<%=path%>/deboeditor/dist/wangEditor/js/wangEditor.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
<script type="text/javascript">

    $(function(){
        uploadInit3();
        new deboEditor().init("ue_content3");
    })
</script>


<script language="javascript" type="text/javascript">
 
 
	function back(){
     top.Dialog.close();
	}
	function flush(){
	 //top.frmright.window.location.reload();
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
	    	var imgpath = $("#iconPhones").val();
	    	if(imgpath!=""){
	    		$("#iconPhones").val(imgpath+','+data.imgPath); 
	    	}else{
				$("#iconPhones").val(data.imgPath); 
	    	}
	    	$("#PhoneDivs").append("<div style='padding: 5px 5px 5px 5px;float: left'><img src='"+data.imgPath+"' style='width: 80px;'/></div>");
	    	$("#filephones").val("");
	    }
	    });
	   }


    function uploadInit3() {

        var uploader1 = Qiniu.uploader({
            runtimes: 'html5,flash,html4',
            browse_button: "qnsubmit2",
            uptoken_url: '<%=path%>/business/common/storage.do?act=getQiniuToken',

            domain: 'http://oanafcpi7.bkt.clouddn.com/',

            container: "ptqnsubmit2",

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
                    $("#iconPhones").val(sourceLink);

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
