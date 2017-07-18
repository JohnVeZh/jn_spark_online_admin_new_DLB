<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.system.model.*" %>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    <%@ include file="../include/css.jsp" %> 
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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
	<script type="text/javascript" src="../js/text/text-overflow.js"></script>
  </head>
 <%
  ListContainer lc=(ListContainer)request.getAttribute("lc");	
  String parentid=(String)request.getAttribute("parentid");
  request.setAttribute("parentid",parentid);
  String parentname=Tool.getValue("select name from sys_function where id='"+parentid+"'");	
 %>
  <body>  
  <div id="scrollContent" class="border_gray">
  <div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：<%if(parentname.equals("系统功能")) {%><%=parentname%><%}else{ %>
								系统功能》<%=parentname%><%} %>
					 </span>
				</div>
			</div>
		</div>
	</div>
   <form name="listForm" scope="request" action="sysFunction.do?act=list&parentid=<%=parentid%>" method="post">
   <input type="hidden" name="state" value="${state}"/>
  <div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
			<div class="center">
				<div class="left">
					<div class="right">
						<div class="padding_top5 padding_left10">
							<a href="javascript:;"
								onClick="preAdd('sysFunction.do?act=preAdd&parentid=<%=parentid%>')" title="新增">
								<span class="img_add"></span> </a>
							<div class="box_tool_line"></div>
							<a href="javascript:;" title="修改" onClick="preUpdate()"><span
								class="img_edit"></span> </a>
							<div class="box_tool_line"></div>
							<a id="del_mfp" title="删除" href="javascript:;" onClick="doDels()"><span
								class="img_delete"></span> </a>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
  <table  style="width: 98%"  class="tableStyle" id="listTable">
	<thead>
       	<tr class="TrTlitBar" >
       		<td width="4%" height="25"  align="center" class="DataTable_Field"><input type="checkbox" name="IDs" value="checkbox" onClick="doAllSelect(this, document.all('id'))"></td>
       		<td width="5%" height="25" align="center"  class="DataTable_Field">序号</td>
       		<td width="15%" height="25"  align="center" class="DataTable_Field">功能名称</td>
       		<td width="10%" height="25" align="center" class="DataTable_Field">编码</td>
       		<td width="5%" height="25" align="center" class="DataTable_Field">排序值</td>
       		<td width="50%" height="25" align="center" class="DataTable_Field">URL</td>
      	</tr>
    </thead>
<%
	int sn=lc.getIndex();
	List list=lc.getList();
	SysFunction po = null;
	String name = "";
	for (int i = 0; i < list.size(); i++) 
	{
		po = (SysFunction)list.get(i);
		name = po.getName();
		if(name!=null&&!"".equals(name)) {
		
			name = name.length()>15?name.substring(0,14)+"...":name;
		} else {
			name = "";
		}
%>
	<tr class="TrBG1" onmouseover=changeRowColor(this,0); onmouseout=changeRowColor(this,1);
	 onclick="this.firstChild.firstChild.checked=!this.firstChild.firstChild.checked;selectedcolor(document.getElementById('listTable'),this,'SelectBG')"  id="tr1">
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="center" title="<%=po.getName() %>"><%= name %></td>
        <td align="center" class="DataTable_Content" title="<%=po.getCode()%>"><%=po.getCode()%></td>
        <td align="center" class="DataTable_Content" title="<%=po.getSort()%>"><%=po.getSort()%></td>
        <td align="center" class="DataTable_Content" title="<%=po.getUrl()%>"><%=po.getUrl()%></td>
 		</tr>
<%
	}
%>
  </table>
  
<script type="text/javascript">
	var st = new SortableTable(document.getElementById("listTable"),["None", "Number", "String", "String","Number","None"]);
</script>
<%@include file="../include/listpage.jsp"%>
</form>
</div>
</body>
<SCRIPT LANGUAGE="JavaScript">
function doDels() {
    if (isSelected(document.all('id'))) {
   		top.Dialog.confirm("您确信要删除吗？",
	   		function() {
	   			listForm.action="sysFunction.do?act=delete&parentid=<%=parentid%>";
				listForm.submit();
			}
		);
       
    }else{
    	top.Dialog.alert('请至少选择一项进行操作');
    }
}
function doDel(url) {
    if(top.Dialog.confirm("您确信要删除吗？")) {
    	listForm.action=url;
      	listForm.submit();
    }
}
function preAdd(url,state) 
{       
    	listForm.action=url;
      	listForm.submit();
}
function preUpdate() {
    if (isSelected(document.all('id'))) {
        var input2 = document.getElementsByName('id');
        var idVal=""; 
        var m = 0 ;  
        for(var i = 0; i <input2.length; i++) {
          if (input2[i].checked) {
            idVal +="&id="+ input2[i].value;
            m++;
         } else {
        continue;
        }
      }
      if (Number(m)>=2) {
      	
      	top.Dialog.alert("只能选择一项进行操作！");
      	return false;
      }
    listForm.action="sysFunction.do?act=preUpdate"+idVal;
    listForm.submit();
  }else{
    	top.Dialog.alert('请选择一项进行操作');
    }

}


</SCRIPT>
</html:html>


