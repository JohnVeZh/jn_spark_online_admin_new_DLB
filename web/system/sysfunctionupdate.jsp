<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.easecom.system.web.*"  %>
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
	<link rel="stylesheet" type="text/css" href="../js/form/CLEditor/jquery.cleditor.css" />
	<script type="text/javascript" src="../js/form/CLEditor/jquery.cleditor.js"></script>
  </head>  
  <body><div id="scrollContent">
  
  <html:form  action="system/sysFunction.do?act=update" method="post" enctype="multipart/form-data"  onsubmit="return sub(this)">
  <div class="box2" panelWidth="1000" panelTitle="功能信息修改" showStatus="false" roller="true">
	<table class="tableStyle" transMode="true" width="90%" >
        <tr>
          <td>
             <table width="90%"  cellpadding="0"   align="center" class="Table" style="font-size: 12px">
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">上级编码</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                          <html:hidden  property="parentid"/>
		                  <html:text property="parentcode"  readonly="true" styleClass="Input" style="border:1px solid #d3d7d4"></html:text>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">编码</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                           <html:text property="code"  readonly="true" styleClass="Input" style="border:1px solid #d3d7d4"></html:text>
		               </div>
		            </td>  
                </tr> 
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">功能名称<font style="font-size:12px;color: blue">（可修改）</font></div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                          <html:hidden  property="id"/>
		                  <html:text property="name" styleId="nameid" onblur="checkname();" styleClass="Input0" style="border:1px solid #d3d7d4"></html:text>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">功能全称</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                       		<input type="text" name="fullname" value="${SysFunctionForm.fullname }" style="border:1px solid #d3d7d4"/>
		               </div>
		            </td>  
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">功能URL<font style="font-size:12px;color: blue">（可修改）</font></div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                       		<input type="text" name="url" value="${SysFunctionForm.url }" style="border:1px solid #d3d7d4"/>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">排序值</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                   <html:text property="sort"   styleClass="Input" style="border:1px solid #d3d7d4"></html:text>
		               </div>
		            </td>  
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">图标类型<font style="font-size:12px;color: blue">（可修改）</font></div></td>
                   <td width="31%" class="DataTable_Content">
                      <div align="left">
                      	<input type="text" name="icon"  onblur="checkicon()"  value="${SysFunctionForm.icon }" style="border:1px solid #d3d7d4"/>icon_(1-9).png
		               </div>
		            </td>  
		           <td width="18%" class="DataTable_ID"><div align="left">是否菜单显示</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                   <html:radio property="isview" value="1"/>是
		                   <html:radio property="isview"   value="0"/>否
		               </div>
		            </td>  
                </tr>
                 <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">左菜单是否缩回</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                       <html:radio   property="isvalid"   value="1"  />是
		               <html:radio   property="isvalid"   value="0"  />否
		               </div>
		           </td>
		            <td width="18%" class="DataTable_ID"><div align="left">是否目录</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                  <html:radio property="isdir" value="1"/>是
		                  <html:radio property="isdir" value="0"/>否
		               </div>
		           </td>
		          
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">友情提示：</div></td>
                   <td width="60%" class="DataTable_Content" colspan="3"><html:textarea property="remark" style="width:100%;height:100px;border:1px solid #d3d7d4"  styleClass="rich" />
		           </td>
                </tr>
             </table>
          </td>
        </tr>
      </table>
      <div align="center">
         <input name="submit1" type="submit"  value="保 存" onClick="tijiao();">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input name="submit2" type="button"   value="返 回" onClick="history.back();">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     </div>
     </div>
  </html:form>
 </div>
  </body>
  <script language="javascript" type="text/javascript">
   function sub(form) {
  	if($('name').value.length>64){
  		alert("名称长度过长！");
  		$('name').focus();
  		return false;
  	}
   if($('url').value.length>128){
  		alert("功能URL长度过长！");
  		$('url').focus();
  		return false;
  	}
  	if($('remark').value.length>1024){
  		alert("备注长度过长！");
  		$('remark').focus();
  		return false;
  	}
  	return validateForm(form);
  }
  function required () {   
      this.ab = new Array("name", "功能名称不能为空!", new Function ("varName", " return this[varName];"));	
  }
  function DateTimeValidations(){  
  }
  function DateValidations () {
  }
  function  FloatValidations(){}
  function IntegerValidations () {
     this.ac = new Array("sort", "排序值必须是整型数据!", new Function ("varName",  "return this[varName];"));
  }
  
  
  var flag=false;
  function checkurl(){
  	var urlvalue=document.getElementById("urlid").value;
  	if(urlvalue==""){
  		top.Dialog.alert("<font color='red'>URL不能为空</font>");
  		return flag=false;
  	}else{return flag=true;}
  }
  function checkname(){
  	var namevalue=document.getElementById("nameid").value;
  	if(namevalue==""){
  		top.Dialog.alert("<font color='red'>功能名称不能为空</font>");
  		return flag=false;
  	}else{return flag=true;}
  }
  function checkicon(){
  	var iconvalue=document.getElementById("iconid").value;
  	if(iconvalue==""){
  		top.Dialog.alert("<font color='red'>图标类型不能为空</font>");
  		return flag=false;
  	}else{return flag=true;}
  }
  function tijiao(){
  	var iconvalue=document.getElementById("iconid").value;
  	var namevalue=document.getElementById("nameid").value;
  	var urlvalue=document.getElementById("urlid").value;
  	if(iconvalue!=""&&namevalue!=""&&urlvalue!=""){
  		event.returnValue=true;
  	}else{
  		top.Dialog.alert("<font color='red'>存在空值，请检查！</font>");
  		event.returnValue=flag;
  	}
  }
</script>
</html:html>
     