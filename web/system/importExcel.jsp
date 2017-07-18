<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.easecom.base.web.*"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--框架必需start-->
	<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
	<link href="<%=path %>/css/import_basic.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" />
	<!--框架必需end-->

	<!--多选框脚本start-->
	<script type="text/javascript" src="<%=path %>/js/form/multiselect.js"></script>
	<!--多选框脚本end-->

	<!--表单验证脚本start-->
	<script src="<%=path %>/js/form/validationEngine-cn.js"
		type="text/javascript"></script>
	<script src="<%=path %>/js/form/validationEngine.js"
		type="text/javascript"></script>
	<!--表单验证脚本end-->
	<!--富文本框start-->
	<link rel="stylesheet" type="text/css"
		href="<%=path %>/js/form/CLEditor/jquery.cleditor.css" />
	<script type="text/javascript"
		src="<%=path %>/js/form/CLEditor/jquery.cleditor.js"></script>
	<!--富文本框end-->
	<script type="text/javascript">
	function checkImportExcel(){
		var orgId =  $("#orgId").val();
		var orgName = $("#orgName").val();
		if(orgId == "" || orgId == null ){
			top.Dialog.alert("请选择部门");
			return false;
		}
		return true;
	}
   function open2(){
    var diag = new top.Dialog();
    var inputValue ;
    var inputId; 
    diag.ID="diag02";
    diag.Title = "部门";
    diag.URL = "<%=request.getContextPath()%>/system/sysUser.do?act=getOrg&backMark=userImport";

    diag.show();
	}

	function getRes(bm_id,bm_name){
  	  $("#orgId").val(bm_id);
   	 $("#orgName").val(bm_name);
	}
</script>
</head>
<%
       if(request.getAttribute("returnInfo")!=null){
     %>
	<script type="text/javascript">
			top.Dialog.confirm("<%=request.getAttribute("returnInfo")%>",function(){
 				top.Dialog.close();
 			});
       </script>
<%
    }
   %>
<body>
	<div class="box1 center" panelWidth="85%">
		<form action="<%=path %>/system/sysUser.do?act=importExcel" method="post" enctype="multipart/form-data" onSubmit="return checkImportExcel()">
			<fieldset>&nbsp; 
				<legend> 
					用户批量导入 
				</legend>
				<table class="tableStyle" useHover="false" useClick="false">
					<tr>
						<td width="10%">
							部门选择
						</td>
						<td width="90%" colspan="3">
						<input type="hidden" name ="orgId" id="orgId"/>
						<input type="text" name ="orgName" id="orgName" styleClass="Input0" onclick="open2();"/>
						</td>
					</tr>
				</table>
				<table class="tableStyle" useHover="false" useClick="false">
					<tr>
						<td>
							请选择文件：
						</td>
						<td>
						<input type="file" name ="file" id="file" class="fileinput"/>
						<input type="submit" name="sub" id="sub" value="导入用户信息"/>
						</td>
					</tr>
					<tr>
						<td align="right" colspan="2">
							[
							<a href="<%=path%>/system/sysUser.do?act=downloadExcel">下载导入数据模板</a>]
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="padding_top10">
				<table class="tableStyle" transMode="true">
					<tr>
						<td colspan="4">
							<button onclick="javascript:top.Dialog.close();">
								<span class="icon_close_min">关闭窗口</span>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html:html>