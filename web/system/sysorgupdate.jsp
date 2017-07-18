<%@ page language="java" import="com.easecom.system.web.SysOrgForm" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>sysorgupdate.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
 
	<!-- 表单验证start -->
	<script src="../js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="../js/form/validationEngine.js" type="text/javascript"></script>
	<!-- 表单验证end -->
	<style type="text/css">
		#mfp_add tr td{height: 30px; vertical-align: middle}
	</style>
  </head>  
  <body>
  
  <div class="position">
		<div class="center">
		<div class="left">
		<div class="right">
			<span>当前位置：部门管理 >> 修改部门</span>
		</div>	
		</div>	
		</div>
	</div>	
  
  <div class="box2"   panelTitle="修改组织部门信息">

  
  <%
		SysOrgForm so = (SysOrgForm)request.getAttribute("SysOrgForm");
	 %>
	 <fieldset style="">
	 	<legend>组织部门详细信息</legend>
	 
  	<form action="sysOrg.do?act=update" method="post" target="frmright"  >
		<table id="mfp_add" class="tableStyle" transMode="true">
		<tr>
			<td class="font_12">名称：</td><td>
			<input class="validate[required,length[0,20]]"  style="height: 20px;" name="name" type="text" value="<%=so.getName() %>" />
			<span class="star">*</span>
			</td>
			<td class="font_12">上级编码：</td><td>
			<input type="hidden" name="id" value="<%=so.getId() %>">
			<input type="hidden" name="parentid" value="<%=so.getParentid() %>">
			<input disabled="disabled" type="text" style="height: 20px;" name="parentcode" value="<%=so.getParentcode() %>"/>
			<span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td class="font_12">编码：</td><td>
			<input disabled="disabled" class="validate[required,length[0,20]],custom[onlyNumber]" style="height: 20px;" name="code" type="text" value="<%=so.getCode() %>"/>
			<span class="star">*</span>
			</td>
			<td class="font_12">排序值：</td><td>
			<input class="validate[required,length[0,20]],custom[onlyNumber]" style="height: 20px;" name="sort" type="text" value="<%=so.getSort()%>"/>
			<span class="star">*</span>
			</td>
		</tr>
		
		<tr  style="display:none;">
			<td class="font_12">部门类型：</td>
			<td class="font_12">
				<input type="hidden" name="parenttype" value="<%=so.getParenttype() %>">
				<select name="type" id="deptype" class="default">
					<option value="0">普通部门</option>
					<option value="1">特殊部门</option>
				</select>
			</td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td class="font_12">备注：</td>
			<td colspan="2" >
				<textarea class="validate[length[0,50]]" name="remark" style="width: 100%;"><%=so.getRemark() %></textarea>
				
			</td>
			<td>
				<span class="img_light" style="height:80px;" title="限制在50字以内"></span>
				
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value=" 提 交 "/>
				<input   type="reset" value=" 返回 " onClick="back()"/>
			</td>
		</tr>
	</table>
	</form>				
  </fieldset>
  </div>
  </body>
 <script language="javascript" type="text/javascript">
 var name='${SysOrgForm.type}';
for(var i=0;i<document.getElementById("deptype").options.length;i++){
	if(name==document.getElementById("deptype").options[i].value){
		document.getElementById("deptype").options[i].selected = true;
	}
}

function back(){
	location.href='sysOrg.do?act=list&parentid=<%=so.getParentid() %>';
}
</script>
</html:html>
