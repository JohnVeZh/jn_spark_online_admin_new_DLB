<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.system.model.*" %>
<%
	String path = request.getContextPath();
	path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    <%@ include file="../include/css.jsp" %> 
    <title>角色列表</title>   
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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
    
  </head>
 <%
  ListContainer lc=(ListContainer)request.getAttribute("lc");	
 %>
  <body>
   <form name="listForm" scope="request" action="sysrole.do?act=list" method="post">
  <div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
			<div class="center">
				<div class="left">
					<div class="right">
						<div class="padding_top5 padding_left10">
							<a href="javascript:;" title="新增"
								onClick="preAdd('sysrole.do?act=preAdd')">
								<span class="img_add"></span> </a>
							<div class="box_tool_line"></div>
							<a href="javascript:;" onClick="preUpdate()" title="修改"><span
								class="img_edit"></span> </a>
							<div class="box_tool_line"></div>
							<a id="del_mfp" href="javascript:;" onClick="doDels()" title="删除"><span
								class="img_delete"></span> </a>
							<div class="box_tool_line"></div>
							<a id="del_mfp" href="javascript:;" onClick="preCfgFun()" title="分配权限"><span
								class="img_user"></span> </a>
								<div class="box_tool_line"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
  <table  class="tableStyle" mode="list" useRadio="true" id="radioTable">
	
       	<tr>
       		<td  width="50"></td>
       		<td   align="center" >序号</td>
       		<td  align="center" >角色名称</td>
       		<td   align="center">备注</td>
      	</tr>

<%
	int sn=lc.getIndex();
	List list=lc.getList();
	SysRole po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (SysRole)list.get(i);
%>
	<tr id="tr1">
    	<td align="center"  class="ali02"><input type="radio" name="id" value="<%=po.getId()%>" ></td>
        <td align="center" class="ali02"><%=++sn%></td>
        <td  align="center" class="ali02"><%= po.getName() %></td>
         <td  align="center" class="ali02"><%= po.getRemark() %></td>
 	</tr>
<%
	}
%>
  </table>
<div class=" padding_top2 padding_bottom2 padding_right">
<div class="center">
	<div class="right">
<%@include file="../include/listpage.jsp"%>
</div></div></div>
<div class="diverror">注意： ① 删除角色前，请先清除分配给该角色的菜单权限。</div>
</form>

</body>
<SCRIPT LANGUAGE="JavaScript">

function doDels() {
    if (isSelected(document.all('id'))) {
   		top.Dialog.confirm("您确信要删除吗？",
	   		function() {
	   			listForm.action="sysrole.do?act=delete";
				listForm.submit();
			}
		);
    }else{
    	top.Dialog.alert('请至少选择一项进行操作');
    }
}
function preCfgFun() {
    if (isSelected(document.all('id'))) {
        var input2 = document.getElementsByName('id');
        var idVal="";   
        for(var i = 0; i <input2.length; i++) {
          if (input2[i].checked) {
            idVal +="&id="+ input2[i].value;
            break;
         } else {
        continue;
        }
      }
    listForm.action="sysrole.do?act=preCfgFun"+idVal;
    listForm.submit();
    }else{
    	top.Dialog.alert('请选择至少一条数据');
    }
  }
  
 
function preAdd(url) 
{       
    	listForm.action=url;
      	listForm.submit();
}
 function preUpdate() {
    if (isSelected(document.all('id'))) {
        var input2 = document.getElementsByName('id');
        var idVal="";   
        var m=0;
        for(var i = 0; i <input2.length; i++) {
          if (input2[i].checked) {
            idVal +="&id="+ input2[i].value;
            m++;
         } else {
        continue;
        }
      }
      if (Number(m)>1) {
      	
      	top.Dialog.alert("请选择一项进行操作！");
      	return false;
      }
    listForm.action="sysrole.do?act=preUpdate"+idVal;
    listForm.submit();
  }else{
    	top.Dialog.alert('请选择一项进行操作！');
    }

}
</SCRIPT>
</html:html>


