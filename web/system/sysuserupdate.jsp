<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title></title>
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
	<script type="text/javascript" src="<%=path%>/js/text/text-overflow.js"></script>
	<script src="../js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="../js/form/validationEngine.js" type="text/javascript"></script>
	<style type="text/css">
		td{
			font-size: 12px;
		}
	</style>
  </head>  
  <body>
  
  <html:form  action="system/sysUser.do?act=update" method="post">
  <!--  -->
  <div class="position">
	<div class="center">
	<div class="left">
	<div class="right">
		<span>当前位置：人员管理 >>修改用户信息</span>
	</div>	
	</div>	
	</div>
</div>	 
<div class="box2" panelWidth="100%" panelTitle="用户信息增加" showStatus="false" >
	<fieldset> 
		<legend>用户修改必填信息</legend> 
		<table class="tableStyle" transMode="true" footer="normal">
			<tr>
			<td  width="20%">用户名：</td>
			<td  width="30%">
			   <html:hidden  property="id"/>
			   <input type="text" name="loginName" id="loginName" value="<bean:write name='SysUserForm' property='loginName'/>" class="validate[required]"/>
			   <span class="star">*</span>
			</td>
			<td  width="15%">真实姓名：</td>
			<td  width="30%">
				<input type="text" name="name" id="name" value="<bean:write name='SysUserForm' property='name'/>" class="validate[required]"/>
				<span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td>组织名称：</td>
			<td class="DataTable_Content">
				<html:hidden  property="orgId" styleId="orgId"/>
				<input type="text" id="orgName" name="orgName" value="<bean:write name='SysUserForm' property='orgName'/>" onclick="open2();"/>
				<span class="star">*</span>
			</td>
			<td> </td>
			<td>
			</td>
		</tr>
	 
		</table>
	</fieldset> 
	<fieldset> 
		<legend>用户选填信息</legend> 
		<table class="tableStyle" transMode="true" footer="normal">
		<tr >
			<td  width="20%">新密码：</td>
			<td width="30%">
				<input type="password" id="passwordnew" name="passwordnew" class="" onblur="checkInput(this,'noSpecialCaracters','')"/>
			</td>
			<td  width="15%">确认密码：</td>
			<td  width="30%">
				<input type="password" id="passwordnew2" class="validate[confirm[passwordnew]]"/>
			</td>
		</tr>
		<tr>
			<td>性别：</td>
			<td>
				<html:radio property="sex" value="男">男</html:radio >
		        <html:radio  property="sex"  value="女">女</html:radio >  
			</td>
			<td>年龄：</td>
			<td>
				<input type="text" name="age" id="age" value="<bean:write name='SysUserForm' property='age'/>" onblur="checkInput(this,'onlyNumber',2)" onkeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))"/>
			</td>
		</tr>
		<tr>
			<td>邮箱：</td>
			<td>
				<input type="text" name="email" id="email" value="<bean:write name='SysUserForm' property='email'/>" onblur="checkInput(this,'email','')"/>
			</td>
			<td>职务：</td>
			<td>
				<input type="text" name="duty" id="duty" value="<bean:write name='SysUserForm' property='duty'/>" />
			</td>
		</tr>
		<tr>
			<td>电话号码：</td>
			<td>
				<input type="text" name="workPhone" id="workPhone" value="<bean:write name='SysUserForm' property='workPhone'/>" onblur="checkInput(this,'mobilephone','')"/>
			</td>
			<td>工号：</td>
			<td>
				<input type="text" name="worknum" id="worknum" value="<bean:write name='SysUserForm' property='worknum'/>" />
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td colspan="3">
				<span class="float_left">
					<textarea style="width:400px" name="remark" id="remark"><bean:write name='SysUserForm' property='remark'/> </textarea>
				</span>
				<span class="img_light" style="height:80px;" title="限制在200字以内"></span>
			</td>
		</tr>
		</table>
	</fieldset> 
	<div class="padding_top10">
		<table class="tableStyle" transMode="true">
			<tr>
			<td colspan="4">
				<input name="submit1" type="submit"  value="保 存" onClick="return checkForm()">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <input name="submit2" type="button" value="返 回" onClick="history.back();">
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		</table>
	</div> 
	</div>
  </html:form>
  </body>

  <script language="javascript" type="text/javascript">
   /**
	验证 
	@param inp 要验证的输入框
	@param cls  要加入的class
  */
   function checkInput(inp,cls,len){
   	if(inp.value.length!=0){
	   	if(len==''){
	   		inp.className= "validate[custom["+cls+"]]";
	   	}else{
	   		inp.className= "validate[length[0,"+len+"],custom["+cls+"]]";
	   	}
	  }
	}
	
  
  function open2(){
    var diag = new top.Dialog();
    var inputValue ;
    var inputId; 
    diag.Title = "部门";
    diag.URL = "<%=request.getContextPath()%>/system/sysUser.do?act=getOrg";

    diag.show();
}

function getRes(bm_id,bm_name){
    $("#orgId").val(bm_id);
    $("#orgName").val(bm_name);
}

   function checkForm(){
    if(document.SysUserForm.name.value == "")
    {
            alert("用户名称不能为空!");
            document.SysUserForm.name.focus();
            document.SysUserForm.name.value="";
            return false;	
    }
    if(document.SysUserForm.passwordnew.value!= document.SysUserForm.passwordnew2.value)
    {
    	if(document.SysUserForm.passwordnew.value ==""){
    		alert("请先输入新密码再输入确认密码!");
    		 document.SysUserForm.passwordnew.value="";
    		 document.SysUserForm.passwordnew2.value="";
    		 document.SysUserForm.passwordnew.focus();
    	}else{
            alert("新密码与确认密码不一致!");
            document.SysUserForm.passwordnew2.focus();
            document.SysUserForm.passwordnew2.value="";
            }
            return false;	
    }
    if(getElementById('loginName').value.length>100){
  		alert("用户登录名称长度过长！");
  		getElementById('loginName').focus();
  		return false;
  	}
   if(getElementById('name').value.length>100){
  		alert("用户真实名称长度过长！");
  		getElementById('name').focus();
  		return false;
  	}
  	if(getElementById('duty').value.length>32){
  		alert("职务长度过长！");
  		getElementById('duty').focus();
  		return false;
  	}
  	if(getElementById('email').value.length>0){
	  	if(getElementById('email').value.length>100){
	  		alert("邮箱长度过长！");
	  		getElementById('email').focus();
	  		return false;
	  	}
  	}
  	
 	if(getElementById('age').value<0 || getElementById('age').value>200){
	   	alert('请输入合法的年龄，在1-200之间');
	   	getElementById('age').focus();
	   	getElementById('age').value="";
	   	return false;
   }
   
   var workPhoneExec = /^\d+\-?\d+$/;
   if(getElementById('workPhone').value.length>0){
   	 if(!workPhoneExec.exec(getElementById('workPhone').value)){
   		alert('电话号码格式不正确');
   		$('phoneCornet').focus();
   		return false;
  	 }
   }
  
   
   var emailExec = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if(getElementById('email').value.length>0){
	   	if(!emailExec.exec(getElementById('email').value)){
	   		alert('邮箱格式不正确');
	   		getElementById('email').focus();
	   		return false;
	   }
   }
   
}

function sys(value_){
  	if('1'==value_){
  		document.getElementById('emailword').style.display='none';
  		document.getElementById('emailempty').style.display='none';
  	}else{
  		document.getElementById('emailword').style.display='';
  		document.getElementById('emailempty').style.display='';
  	}
 }
 
 function getElementById(id){
 	return document.getElementById(id);
 }
</script>
</html:html>
