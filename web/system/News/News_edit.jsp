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
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path%>/deboeditor/dist/css/wangEditor.min.css">
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!--缩略图样式-->
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
<!-- 异步上传图片 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
</head>
<%
 NewsActionForm po = (NewsActionForm) request.getAttribute("NewsActionForm");
 %>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：修改新闻信息<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  styleId="listForm" action="business/News.do?act=update" method="post" enctype="multipart/form-data" onsubmit="return sub(this)" target="frmright">
			<html:text style="display:none" property="id" ></html:text>
			<html:text style="display:none" property="sysUserId" ></html:text>
			<html:text style="display:none" property="browseVolume" ></html:text>
			<div class="box1 center" whiteBg="true" id="form1">
				<input type="hidden" id="isShow" name="isShow">
				<fieldset>
					<legend>新闻基本信息</legend>
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									标题：
								</td>
								<td width="85%" align="left" colspan="3">
									<input type="text" name="title" style="width:95%" styleClass="validate[required] iptClass" maxNum="50" value="${NewsActionForm.title }">
									<span class="star">*</span>
								<td>
							</tr>
							<tr>
								<td width="10%" align="right">
									帖子链接：
								</td>
								<td width="90%" align="left" colspan="3">
									http://dy.sparke.cn/phoneMobile.do?act=shareNewsDetail&id=${NewsActionForm.id }	
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									简介：
								</td>
								<td width="35%" align="left" colspan="3">
									<textarea name="subtitle" style="width:95%" styleClass="validate[] iptClass"  maxNum="200">${NewsActionForm.subtitle }</textarea>
									<span class="star"></span>
								</td>
							</tr>
							<tr style="display: none" >
								<td width="15%" align="right">
									来源：
								</td>
								<td width="35%" align="left" colspan="3">
									<html:text property="source" style="width:250px" styleClass="validate[] iptClass" maxlength="25"></html:text>
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" rowspan="2">
									列表图片：
								</td>
								<td width="35%" align="left" rowspan="2" >
									<div id="ptqnsubmit1">
									图片路径：<html:text property="phoneImg"   styleId="iconPhone"  style="width:100%;"   styleClass="validate[] iptClass"></html:text> <br/>
									<div  style="float: left;  padding: 8px 8px 8px 8px">
										<input id = "qnsubmit1" type="button" value="上传并预览" />
										<span class="star">*图片尺寸：80px : 60px</span>
									</div>
									</div>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									  <%
									  	if(null != po.getPhoneImg() && !"".equals(po.getPhoneImg())){
									  		if(po.getPhoneImg().startsWith("http")){
									   %>
									      	<a href="javascript:thumbImgsDiv('<%=po.getPhoneImg()%>',0)" >
												<img src="<%= po.getPhoneImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
										    </a>
										   <% }else{
										   %>
											<a href="javascript:thumbImgsDivList('<%=po.getPhoneImg()%>',0,'<%=path%>')" >
												<img src="<%=path%>/<%=po.getPhoneImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
											</a>
									   <%} }%>

									</div>
								</td>
								<td width="15%" align="right">
									作者：
								</td>
								<td width="35%" align="left">
									<html:text property="author" style="width:250px" styleClass="validate[] iptClass" maxlength="15"></html:text>
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td  align="center"  colspan="2">
									<span title="此项用于定时展示，草稿状态下无效" >文章将在</span>
									<input type="text" name="ptime" id="ptime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" maxlength="15" value="<%=po.getPtime()%>"  title="此项用于定时展示，草稿状态下无效"  />
									<span title="此项用于定时展示，草稿状态下无效">后展示</span>
								</td>
							</tr>
							<tr >
									<td align="right">
										选择标签:<input type="checkbox" id="checkAll" onclick="CheckAll()"/>
									</td>
									<td colspan="3">
									  <c:forEach items="${lbs }" var="lb">
									    <div style="padding: 5px 5px 5px 5px; float: left;">
									    	<input type="checkbox" name="clb" value="${lb.id }" <c:if test="${lb.isCheck==0 }">checked="checked" </c:if>/>${lb.lableName }
									    </div>
									  </c:forEach>
									</td>
								</tr>

							<tr >
									<td align="right" >
										新闻详情:
									</td>
									<td colspan="3" >
										 <textarea  id="ue_content2" name="content" style="width: 100% ; height:440px;"   class="validate[] iptClass" >${NewsActionForm.content}</textarea>
									</td>
								</tr>


								<tr style="display: none">
									<td width="15%" align="right">
										点击数：
									</td>
									<td width="35%" align="left">
										<html:text property="count" style="width:250px" styleClass="validate[] iptClass"></html:text>
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
							<input type="button" value=" 提 交 " onclick="release()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value=" 存草稿 " onclick="saveDraft()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭 " onclick="back()" />
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
				<html:form action="business/News.do?act=updateImgPath&type=phone" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
				
				<html:form action="business/News.do?act=updateImgPath&type=pad" styleId="formActionpad"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filepad" onchange="ajaxFilepad()"></html:file>
				</html:form>
				
			</div>
	<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
</body>



<script type="text/javascript" src="<%=path%>/deboeditor/dist/wangEditor/js/wangEditor.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
<script type="text/javascript">

    $(function(){
        uploadInit2();
        new deboEditor().init("ue_content2");
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
     
	function back(){
		top.Dialog.close();
	}

	  $(function(){
//	  		UE.getEditor('ue_content', {initialFrameWidth:'100%',initialFrameHeight : 300});
//	  		$("#ue_content").attr("class","");
//		  setTimeout(function () {
//			  UE.getEditor('ue_content').setHeight(300);
//		  },300);
//		  UE.getEditor('ue_content').addListener( 'contentChange', function( editor ) {
//			  UE.getEditor('ue_content').setHeight(300);
//		  });
	  	});

	  function fileClickphone(){
  		$("#filephone").click();
 	}
 function CheckAll(){
	if($("#checkAll").attr("checked")){
	  $("input[name='clb']").attr("checked",true);      
	}else{
	  $("input[name='clb']").attr("checked",false);      
	}
  }
	function saveDraft() {
		$("#isShow").val(0);
		$('#listForm').ajaxSubmit(function(data){
			top.frmright.window.location.reload();
			top.Dialog.close();
		});
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


    function uploadInit2() {

        var uploader1 = Qiniu.uploader({
            runtimes: 'html5,flash,html4',
            browse_button: "qnsubmit1",
            uptoken_url: '<%=path%>/business/common/storage.do?act=getQiniuToken',

            domain: 'http://oanafcpi7.bkt.clouddn.com/',

            container: "ptqnsubmit1",

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
                    this.prototype.phoneImg = sourceLink ;
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
