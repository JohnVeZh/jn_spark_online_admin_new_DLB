<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.*" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>rolefuncfg.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="../include/css.jsp" %>  
	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
  </head>
  <%
    String roleid = (String)request.getAttribute("roleid"); //角色ID
    Collection nodes =(Collection)request.getAttribute("nodes");
    String funrootid=(String)request.getAttribute("funrootid"); //功能根ID
  %>
  
  <body>
     <form action="<%=contextPath %>/system/sysrole.do?act=saveFun" method="post">
        <input type="hidden" name="roleid" value="<%=roleid%>">
     <div  >
   		<table width="100%" id="list1" border="0" align="center" cellpadding="0"   >  
         <tr>
           <td width="50%" >
               <input type="hidden" name="funids" value="">
               <input type="checkbox" name="selectAll" value="checkbox" onClick="selectAllFun()">全选&nbsp;          
               <input type="submit" name="Submit1" value="保  存"   onClick="getSelected()">
               <input type="reset" name="Submit2" value="重  选"  >
		       <input type="button" name="back" value="返  回"   onClick="history.back()">         
           </td>
           <td width="50%" ></td>
          </tr>
     </table>
      </div> 
     <jsp:useBean id="treeMaker" class="com.easecom.common.util.TreeMaker" scope="page"/> 
     <div noWrap style="position: absolute; width:90%; height:90%; left: 50px; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow:auto;">
     <script language="javascript">
       if (document.getElementById) { 
	   <%
	       StringBuffer tree=treeMaker.TreeRootInit("","系统功能",request.getContextPath()+"/style/normal/images/tree01.gif",request.getContextPath()+"/style/normal/images/tree01.gif","","");
		   tree = treeMaker.TreeListApp(nodes,"",request.getContextPath()+"/style/normal/images/c1.gif",request.getContextPath()+"/style/normal/images/c2.gif","","main");
		   out.println(tree.toString());
	   %>
	       document.write(node);
       }
    </script>
    </div>
          
     </form>
  </body>
  <script language="javascript" type="text/javascript">   
  function doSelected(isCheckBox,input){
  if(isCheckBox) {
	  doChildNodeSelect(input.value,input.checked);//选择儿子节点
	  doParentNodeSelect(input.value,input.checked);//选择父亲节点
  }
}
function selectAllFun(){
  var input = document.all("selectAll");
  var input1 = document.all("DBID");
  if (input1 == null) return;
  if (input1.length != null) {
    for(var i = 0; i <input1.length; i++) {
      if (input.checked) {
        input1[i].checked = true;
      } else {
        input1[i].checked = false;
      }
    }
  } else {
    if (input.checked) {
      input1.checked = true;
    } else {
      input1.checked = false;
    }
  }
}       
function getSelected(){
  var input1 = document.all("DBID");
  if (input1 == null) return;
  //给funids赋值
  var input2 = document.all("funids");
  var input2Val = "";
  if (input1.length != null) {    
    for(var i = 0; i <input1.length; i++) {
      if (input1[i].checked) {
        input2Val += ","+input1[i].value;
      } else {
        continue;
      }
    }
  } else {
    if (input1.checked) {
      input2Val += ","+input1.value;
    }
  }
  if (input2Val != null && input2Val.length >1 ) input2Val = input2Val.substring(1);
  input2.value = input2Val;
}       
  </script>
</html:html>
