<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<!-- 表单验证start -->
	<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
	<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
	<!-- 表单验证end -->
	<!-- jquery form -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>

	<!-- 七牛文件上传 -->
	<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/plupload.full.min.js"></script>
	<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/plupload/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/deboeditor/dist/js/qiniu.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common/jquery.qiniu.js"></script>
	<script type="application/javascript">
        $(function () {
//		 $("#2_container").css("z-index","99999");
        })

        function submitForm() {
            var startTimeStr = $("#startTimeStr").val();
            var endTimeStr = $("#endTimeStr").val();
            if (startTimeStr >= endTimeStr){
                top.Dialog.alert("“失效时间”必须晚于“生效时间");
                return;
            }
            if(!($("#imgPath").val())){
                top.Dialog.alert("请上传图片");
                return;
            }

            $('#updateForm').ajaxSubmit(function(data){
                if(data.success){
//                 alert("添加成功，请刷新页面");
                    alert("修改成功");
                    top.research();

                    top.Dialog.close();

                }else{
                    top.Dialog.alert(data.msg);
                }
            })
//		 return true;
        }

	</script>

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
	<form name="updateForm" action="/business/Dlb/popup.do?act=update" method="post" id="updateForm" target="frmright" >
		<div class="box1 center" whiteBg="true" id="form1">
			<fieldset>
				<legend>基本信息</legend>
				<table class="tableStyle" transMode="true" footer="normal">
				<input type="hidden" name="id" value="${m.id}"/>

					<tr>
						<td width="20%" align="right">
							<span class="star">*</span>
							标题：
						</td>
						<td width="40%" align="left">
							<input type="text" name="title" class="validate[required] iptClass" required="required" value="${m.title}"  maxlength="10" />
							<span class="star"></span>
						</td>
					</tr>
					<tr>
						<td width="10%" align="right">
							<span class="star">*</span>
							上传图片：
						</td>
						<td width="40%" align="left">
							<input id="imgPath" type="text" style="display: none" name="img" id="img" class="validate[required] iptClass" required="required" value="${m.img}"/>
							<div style="float: left">
								<input id="uploadImgBtn" type="button" value="上传并预览" />
								<span class="star">最大为3M</span>&nbsp;
							</div>
							<div id="PhoneDiv" style="float: left;padding-right: 20px">
								<img src="${m.img}" alt="" style="width: 50px;height: 50px;"/>
							</div>
						</td>

					</tr>
					<tr>
						<td width="10%" align="right">
							<span class="star">*</span>
							展示频道：
						</td>
						<td width="40%" align="left">

							<select name="module" id="module"  style="z-index: 9999" >
								<c:choose>
									<c:when test="${m.module==1}"><option value="1" selected="selected" >首页</option></c:when>
									<c:otherwise><option value="1" >首页</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.module==5}"><option value="5" selected="selected" >网课</option></c:when>
									<c:otherwise><option value="5" >网课</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.module==2}"><option value="2" selected="selected" >社区</option></c:when>
									<c:otherwise><option value="2" >社区</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.module==3}"><option value="3" selected="selected" >商城</option></c:when>
									<c:otherwise><option value="3" >商城</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.module==4}"><option value="4" selected="selected" >我的</option></c:when>
									<c:otherwise><option value="4" >我的</option></c:otherwise>
								</c:choose>
							</select>
							<span class="star"></span>
						</td>
					</tr>

					<tr>
						<td width="10%" align="right">
							<span class="star">*</span>
							跳转类型：
						</td>
						<td width="40%" align="left">

							<select name="jumpType" id="jumpType"  style="z-index: 9999" >


								<c:choose>
									<c:when test="${m.jumpType==2}"><option value="2" selected="selected" >外部链接</option></c:when>
									<c:otherwise><option value="2" >外部链接</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.jumpType==3}"><option value="3" selected="selected" >活动</option></c:when>
									<c:otherwise><option value="3" >活动</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.jumpType==4}"><option value="4" selected="selected"> 咨询</option></c:when>
									<c:otherwise><option value="4" > 咨询</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.jumpType==5}"><option value="5" selected="selected"> 图书</option></c:when>
									<c:otherwise><option value="5" > 图书</option></c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${m.jumpType==6}"><option value="6" selected="selected"> 网课</option></c:when>
									<c:otherwise><option value="6" > 网课</option></c:otherwise>
								</c:choose>



							</select>
						</td>
					</tr>

					<tr>
						<td width="10%" align="right">
							链接页面：
						</td>
						<td width="40%" align="left">
							<input type="text" id="contentId" name="contentId" class="validate[] iptClass" value="${m.contentId}" />
						</td>

					</tr>
					<tr>
						<td width="10%" align="right">
							<span class="star">*</span>
							生效时间 ：
						</td>
						<td width="40%" align="left">
							<input type="text" name="startTimeStr" id="startTimeStr" value="<fmt:formatDate value="${m.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  class="date validate[required] iptClass" required="required" dateFmt="yyyy-MM-dd HH:mm:ss" />
						</td>

					</tr>
					<tr>
						<td width="10%" align="right">
							<span class="star">*</span>
							失效时间 ：
						</td>
						<td width="40%" align="left">
							<input type="text" name="endTimeStr" id="endTimeStr" value="<fmt:formatDate value="${m.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  class="date validate[required] iptClass" required="required" dateFmt="yyyy-MM-dd HH:mm:ss" />
						</td>

					</tr>



				</table>
			</fieldset>

			<!-- ---- -->
			<table class="tableStyle"
				   style="width: 600px; margin: 0px auto; border: none"
				   formMode="true">
				<tr>
					<td colspan="2" style="border: none;">
						<input type="button" value=" 提 交 " onclick="submitForm()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%--<input type="reset" value=" 重 置 " />--%>
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
	<%--<html:form action="/business/Dlb/popup.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
        <html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
    </html:form>--%>
</div>

<%--<div style="display: none">
    <html:form action="/System/SysConfig.do?act=updateImgPath" styleId="formActionphone"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
        <html:file property="file" styleId="filephone" onchange="ajaxFilephone()"></html:file>
    </html:form>
</div>--%>
</body>
<script language="javascript" type="text/javascript">


    function back(){
        top.Dialog.close();
    }
    function flush(){
        top.frmright.window.location.reload();
    }



    $(function(){
        $("#uploadImgBtn").upload({
            "backInputId" : "img",
            "backShowId" : "PhoneDiv",
            "maxFileSize" : "3mb"
        });
    });

</script>


</html>
