<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommunityPost.CommunityPost"%>
<%@page import="com.easecom.common.util.Tool"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>

	<script type="text/javascript">
		function bindById(id) {
			if(id) {
				$.ajaxSetup({async:false});
				$("#scrollContent").mask("表单提交中！");
				$.post("<%=path%>/business/coupon.do?act=bindResource",{contentId:id,templateId:'${templateId}',relationType:'${relationType}'},function(data){
	               if(data.result) {
					   reload("coupon.do?act=templateRelationList&templateId=${templateId}");
					   window.location.reload(true, function () {
						   $("#scrollContent").unmask();
					   });
	               }
	            });
			}
		}
		
		function batchBind() {
			
			$.ajaxSetup({async:false});
			var inps = document.getElementsByName('id');
			var idVal = "";
			for(var i = 0; i <inps.length; i++){
				if(inps[i].checked){
					if(idVal==""){
						idVal = inps[i].value;
					}else{
					 	idVal = idVal + "," + inps[i].value;
					}
				}
			}
			if(idVal == "") {
            	top.Dialog.alert("请选择一项进行操作",185,285);
            	return false;
			}
		
			$("#scrollContent").mask("表单提交中！");
			$.post("<%=path%>/business/coupon.do?act=bindResource",{contentId:idVal,templateId:'${templateId}',relationType:'${relationType}'},function(data){
               if(data.result){
				   reload("coupon.do?act=templateRelationList&templateId=${templateId}");
                   window.location.reload(true, function () {
					   $("#scrollContent").unmask();
				   });
               } 
            });  
		}
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/coupon.do?act=preBindResourceList" method="post">
			<input type="hidden" name="templateId" value="${templateId }" />
			<input type="hidden" name="relationType" value="${relationType }" />
			<div class="box2" panelTitle="功能面板" roller="false">
	            <table style="width:100%">
	                <tr>
	                    <td>
	                        <div style="float: left">
	                        	标题：
	                        </div>
	                        <div style="float: left">
	                            <input type="text" name="name" value="${name}" />&nbsp;&nbsp;
	                        </div>
	                        <div style="float: left">
	                        	<input type="submit" value="查询" />&nbsp;&nbsp;
	                            <input type="button" onclick="batchBind()" title="批量绑定" value="批量绑定" />&nbsp;&nbsp;
	                        </div>
	                    </td>
	                </tr>
	            </table>
	        </div>
	        
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						<th style="width:30px;" height="25" align="center" class="DataTable_Field"></th>
						<th style="width:30px;" height="25"  align="center" class="DataTable_Field">序号</th>
						<th height="25"  align="center" class="DataTable_Field">标题 </th>								 
						<th height="25"  align="center" class="DataTable_Field" style="width:60px;">发布人</th>
						<%-- 
						<th height="25"  align="center" class="DataTable_Field">客服展示</th>
						 --%>
						<th height="25"  align="center" class="DataTable_Field" style="width:60px;">点击数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" style="width:60px;">评论数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" style="width:60px;">点赞数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" style="width:60px;">点差数量</th>
						<th height="25"  align="center" class="DataTable_Field" style="width:60px;">关注数量</th>								 
						<%-- 
						<th height="25"  align="center" class="DataTable_Field">是否推荐</th>								 
						<th height="25"  align="center" class="DataTable_Field">是否置顶</th>								 
						<th height="25"  align="center" class="DataTable_Field">是否加精</th>								 
						 --%>
						<th height="25"  align="center" class="DataTable_Field" style="width:150px;">发布时间 </th>	
						<th height="25"  align="center" style="width:30px;">操作</th>							 
					</tr>
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	CommunityPost po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (CommunityPost)list.get(i);
		String name = Tool.getValue("select name from sys_user where id='"+po.getSysUserId()+"'");
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="td" align="left"><%= po.getTitle() %></td>								
		<td class="DataTable_Content" align="left" title="<%=name  %>"><%=name  %></td>
		<%-- 
		<td class="DataTable_Content" align="left"><% if(po.getIsShow()==0)out.print("隐藏");else out.print("展示"); %></td>
		 --%>
		<td class="DataTable_Content" align="left" title="<%= po.getSeeNum() %>"><%= po.getSeeNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCommentNum() %>"><%= po.getCommentNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getLikeNum() %>"><%= po.getLikeNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getDifferNum() %>"><%= po.getDifferNum() %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getConcernNum() %>"><%= po.getConcernNum() %></td>	
		<%-- 
		<td class="DataTable_Content" align="left">
		<% if(po.getIsRecommend()==0)out.print("否");else out.print("是"); %>
		</td>								
		<td class="DataTable_Content" align="left">
		<% if(po.getIsTop()==0)out.print("否"); else out.print("是"); %>
		</td>								
		<td class="DataTable_Content" align="left">
		<% if(po.getIsVary()==0)out.print("否");else out.print("是"); %>
		</td>								
		 --%>							
		<td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>								
		<td  align="left">
				<a  href="javascript:;" onclick="bindById('<%=po.getId()%>')"> 
					<span class="img_ok"></span>
				</a>	
		</td>								
 	</tr>
<%
	}
%>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../../include/listpage.jsp"%>
				</div>
			</div>
				<div class="diverror">友情提示:</br></div>
		</form>
	</div>
	</body>
</html>
