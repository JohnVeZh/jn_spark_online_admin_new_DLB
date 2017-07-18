<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.business.NetworkCourseTeacher.NetworkCourseTeacher"%>
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
<!-- 配置文件 -->
<%--<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>--%>
	<!-- 异步上传图片 -->
	<%--<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>	--%>
	<%--<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>--%>
<!-- 树组件start -->
<script type="text/javascript" src="<%=path%>/libs/js/tree/ztree/ztree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/libs/js/tree/ztree/ztree.css"></link>
<!-- 树组件end -->

<!-- 树形下拉框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/selectTree.js"></script>
<!-- 树形下拉框end -->

	 
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
		<form name="listForm" action="business/NetworkCourseTeacher.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
								<tr>
								<%--<td width="10%" align="right">
									<span class="star">*</span>	图片：
								</td>
								<td width="40%" align="left">
									<input type="text" style="display: none" name="logo" id="iconPhone" class="validate[required] iptClass" />
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickphone()"/>
									</div>
									<span class="star">*10M以内 ;96px * 96px;png&jpg  </span>
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									</div>
								</td>--%>
								<td width="10%" align="right">
									<span class="star">*</span>	图片路径：
								</td>
								<td width="40%" align="left">
									<input type="text" style="width: 100%" name="logo" id="logo" placeholder="请输入至少10个字符" class="validate[required] iptClass" required="required" value="${NetworkCourseTeacherActionForm.logo }"/>
								</td>
								<td width="10%" align="right">
									<span class="star">*</span>	老师名称：
								</td>
								<td width="40%" align="left">
									<input id="" type="text" name="name" maxlength="10" required="required" placeholder="10个汉字以内" required="required" class="validate[required] iptClass" />
									<span id="NCTN" class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									<span class="star">*</span>	电话：
								</td>
								<td width="40%" align="left">
									<input type="text" name="moblie" required="required" class="validate[] iptClass" />
									<span class="star"></span>
								</td>
								<td width="10%" align="right">
									<span class="star">*</span>	性别 ：
								</td>
								<td width="40%" align="left">
									<input type="radio" name="sex" value="0" checked="checked"/>男
									<input type="radio" name="sex" value="1"/>女
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
										标签：
								</td>
								<td width="90%" align="left" colspan="3">
									<input type="text" name="tags" class="validate[] iptClass" />
									<span class="star"></span>
								</td>
							</tr>
								<tr>
								<td width="10%" align="right" >
									介绍：
								</td>
								<td width="90%" align="left" colspan="3">
									<div style=" height:250px; overflow:auto; ">
										 <%--<textarea  id="ue_introduce" name="introduce" style="width: 100%"   ></textarea>--%>
										<textarea id="ue_introduce" rows="10" name="introduce" maxlength="200" style="width: 90%;height: 95%;" onchange="this.value=this.value.substring(0, 200)" onkeydown="this.value=this.value.substring(0, 200)" onkeyup="this.value=this.value.substring(0, 200)" ></textarea>
										<span>200字以内</span>
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
							<input type="button" value=" 提 交 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%--<input type="reset" value=" 关闭 " onclick="back()" />--%>
							<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>

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
				<html:form action="business/NetworkCourseTeacher.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">

    function back(){
        top.Dialog.close();
    }
 
 	$(function(){
 		$("input[name='name']").blur(function(){
			var value = $("input[name='name']").val()
 			if(value.length ==0) {

			}else{
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/business/NetworkCourseTeacher.do?act=checkName",
					data:{teacherName:value},
					dataType:"json",
					success:function(data){
						$.each(data,function (indxe,ele) {
							if(ele.result){
								$("#NCTN").html("已经有相同的名字，请重新输入")
								$("input[type='submit']").attr("disabled","disabled")
							}else{
								$("#NCTN").html("名字可用")
								$("input[type='submit']").removeAttr("disabled")
							}
						})

					}
				})

 			}
 		})

        $("input[type='button']").click(function(){
            var val = $.trim($("#logo").val());
            if(!val.startsWith("http://") && !val.startsWith("https://")){
                    top.Dialog.alert('<h3 align="center">请输入http://或者https://格式的网址</h3>');
                    return;
            }else {
                if(val.startsWith("http://")){
                    if(val.length<8 || val.length>200){
                        top.Dialog.alert('<h3 align="center">请输入规定长度的网址</h3>');
                        return;
                    }
                }
                if(val.startsWith("https://")){
                    if(val.length<9 || val.length>200){
                        top.Dialog.alert('<h3 align="center">请输入规定长度的网址</h3>');
                        return;
                    }
                }
                $("#listForm").submit();
            }
        })
 		/*$("input[type='submit']").click(function(){
			var ends = $("img").attr("src");
 			if(ends!=null && ends!=''){
	 			if(ends.endsWith(".jpg") || ends.endsWith(".png")){
	 				function flush(){
						 top.frmright.window.location.reload();
					}
	 			}
 			}else{
 				alert("请上传头像");
 			}
 		})*/
 	})


 	

	
	  $(function(){
	 	 UE.getEditor('ue_introduce', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#ue_introduce").attr("class","");
	  	});
//上传图片
	function fileClickphone(){
  		$("#filephone").click();
 	}
	
	function ajaxFilephone(){
	  	var path = $("#filephone").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".PNG" && ext != ".JPG" ) {
            alert("图片限于png,jpg格式");
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
<%request.removeAttribute("NetworkCourseTeacherActionForm"); %>

</html>
