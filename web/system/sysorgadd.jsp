<%@ page language="java" import="com.easecom.system.web.SysOrgForm" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>增加部门</title>

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

</head>
<body>

	<div class="position">
		<div class="center">
		<div class="left">
		<div class="right">
			<span>当前位置：部门管理 >> 新增部门</span>
		</div>	
		</div>	
		</div>
	</div>	

	<div class="box2"   panelTitle="增加组织部门">

  
  <%
		SysOrgForm so = (SysOrgForm)request.getAttribute("SysOrgForm");
	 %>
	 <fieldset style="">
	 	<legend>组织部门详细信息</legend>
	 
  	<form action="sysOrg.do?act=add" method="post" target="frmright" onsubmit="return sub(this)">
		<table id="mfp_add" class="tableStyle" transMode="true">
		<tr>
			<td class="font_12">名称：</td><td>
			<input class="validate[required,length[0,20]]"  style="height: 20px;" name="name" type="text" />
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
			<input  class="validate[required,length[0,20]],custom[onlyNumber]" style="height: 20px;" name="code" type="text" value="<%=so.getCode() %>"/>
			<span class="star">*</span>
			</td>
			<td class="font_12">排序值：</td><td>
			<input class="validate[required,length[0,20]],custom[onlyNumber]" style="height: 20px;" name="sort" type="text" value="<%=so.getSort()%>"/>
			<span class="star">*</span>
			</td>
		</tr>
		
		<tr style="display:none;">
			<td class="font_12">部门类型：</td>
			<td class="font_12">
				<input type="hidden" name="parenttype" value="<%=so.getParenttype() %>">
				<select name="type" class="default">
					<option value="0">普通部门</option>
					<option value="1">特殊部门</option>
				</select>
			</td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td class="font_12">备注：</td>
			<td colspan="3" >
				<textarea class="validate[length[0,50]]" name="remark" style="width: 100%;"></textarea>
				
			</td>
			<td>
				<span class="img_light" style="height:80px;" title="限制在50字以内"></span>
				
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value=" 提 交 "/>
				<input  type="reset" value=" 返 回 " onClick="history.back();"/>
			</td>
		</tr>
	</table>
	</form>				
  </fieldset>
  </div>

</body>
<script language="javascript" type="text/javascript">
  var selInx;  
  var parenttype;     
     function window.onload(){
       selInx =   document.all.type.selectedIndex;
	   parenttype =  $('parenttype').value;
    } 
    function NoChangeRcd(sel){
    var parentcode = document.getElementById("parentcode").value;  
      if(parenttype=='1'&& parentcode!='0000'){
    	sel.options(selInx).selected=true;
     	alert('上级部门是特殊部门，下级部门也是特殊部门，不能修改');
      }
    }
  
  function sub(form) {
  	if($('name').value.length>64){
  		alert("名称长度过长！");
  		$('name').focus();
  		return false;
  	}
  	if($('remark').value.length>50){
  		alert("备注长度过长！");
  		$('remark').focus();
  		return false;
  	}
  	return validateForm(form);
  }
  function required () {   
     this.aa = new Array("code", "编码不能为空!", new Function ("varName", " return this[varName];"));	
     this.ab = new Array("name", "名称不能为空!", new Function ("varName", " return this[varName];"));	
  }
  function DateTimeValidations(){  
  }
  function DateValidations () {
  }
  function  FloatValidations(){
  }
  function IntegerValidations () {
     this.aa = new Array("sort", "排序值必须是整型数据!", new Function ("varName",  "return this[varName];"));
  }
</script>
</html:html>
