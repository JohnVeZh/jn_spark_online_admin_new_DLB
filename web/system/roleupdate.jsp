<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.easecom.system.web.*" %>
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
	<script src="../js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="../js/form/validationEngine.js" type="text/javascript"></script>
  </head>  
  <body>
  <html:form  action="system/sysrole.do?act=update" method="post"  onsubmit="return sub(this)">
  <div class="box2" panelWidth="100%" panelHeight="200"panelTitle="角色信息修改" showStatus="false" roller="true">
		<table class="tableStyle"  width="100%">
        <tr>
          <td>
             <table width="100%"  border="0" cellpadding="0" style="font-size: 12px"  align="center" class="Table">
                <tr>
                   <td width="18%" class="DataTable_ID" align="right">角色名称</td>
                   <td width="82%" class="DataTable_Content" align="left">
                       <div align="left">
                          <html:hidden  property="id"/>
		                  <html:text property="name" style="width:250px" styleClass="validate[required,length[0,64]]"></html:text><span class="star">*</span>
		               	
		               </div>
		           </td>
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID" align="right">默认功能URL</td>
                   <td width="82%" class="DataTable_Content" align="left">
                       <div align="left">
							<html:text property="roleurl" style="width:250px"  ></html:text>
		               </div>
		           </td>
                </tr>
                 <tr  style="display:none;">
						<td>所属店铺：</td>
						<td>
						<select name="shopId">
						 ${shopOpen }
						</select>
						</td>
						<td></td>
						<td class="DataTable_Content"></td>
					</tr>
               <tr>
                   <td width="18%" class="DataTable_ID" align="right">备注</td>
                   <td width="82%" class="DataTable_Content" colspan=3 align="left">                       
		               <html:textarea property="remark"  style="border:1px solid #d3d7d4" rows="5" cols="120" styleClass="Textarea0" onkeyup="this.value = this.value.substring(0, 250)"/>		              
		           </td>
                </tr>
             </table>
          </td>
        </tr>
      </table>
      <div align="center">
         <input name="submit1" type="submit" class="button" value="保 存">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input name="submit2" type="button" class="button" value="返 回" onClick="history.back();">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     </div>
     </div>
  </html:form>
  </body>
 <script language="javascript" type="text/javascript">
  function sub(form) {
  	if($('name').value.length>64){
  		alert("角色名称长度过长！");
  		$('name').focus();
  		return false;
  	}
  	if($('remark').value.length>256){
  		alert("备注长度过长！");
  		$('remark').focus();
  		return false;
  	}
  	return validateForm(form);
  }
  function required () {   
      this.ab = new Array("name", "角色名称不能为空!", new Function ("varName", " return this[varName];"));	
  }
   function DateTimeValidations(){  
  }
    function DateValidations () {
  }
  function  FloatValidations(){}
  function IntegerValidations () {
     //this.ac = new Array("sort", "排序值必须是整型数据!", new Function ("varName",  "return this[varName];"));
  }
</script>
</html:html>
