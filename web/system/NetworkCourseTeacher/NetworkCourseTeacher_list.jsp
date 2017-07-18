<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.NetworkCourseTeacher.NetworkCourseTeacher"%>
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
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!--缩略图样式-->
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
 

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
	$(function(){
	 top.Dialog.close();
	})
	
		$(function(){
			var msg="";//jstl $ { msg}
			if("200"==msg){
				top.Dialog.alert("删除成功.");
			}
		})
		function isSel(){
			var inps = document.getElementsByName('id');
			var idVal = "";
			var j =0 ;
			for(var i = 0; i <inps.length; i++){
				if(inps[i].checked){
					idVal +="&id="+ inps[i].value;
		            j++;
				}else{
					continue;
				}
			}
			if (Number(j)>=2 || Number(j)<1) {
		      top.Dialog.alert("请选择一项进行操作",185,185);
		      	return "";
		    }else{
		    	return idVal; 
		    }	
		}
	
		//判断
		function isSel1(){
			var inps = document.getElementsByName('id');
			var idVal = "";
			var j =0 ;
			for(var i = 0; i <inps.length; i++){
				if(inps[i].checked){
					idVal +="&id="+ inps[i].value;
		            j++;
				}else{
					continue;
				}
			}
		    return idVal; 
		}
	
		//删除数据
		/* function doDels() {
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NetworkCourseTeacher.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		} */
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NetworkCourseTeacher.do?act=deleteById&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){
		   top.Dialog.open({URL:"<%=path%>/business/NetworkCourseTeacher.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"})       
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/NetworkCourseTeacher.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"})       
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="NetworkCourseTeacher.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：讲师管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourseTeacher.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><a href="javascript:;" onclick="preAdd('')" title="新增"> <span
							class="img_add"></span>
					</a>
					<!-- <a id="del_mfp" title="删除"
						href="javascript:;" onclick="doDels()"> <span
							class="img_delete"></span>
					</a> -->
					   <!--  
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" />--></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="头像">讲师头像</th>
						<th height="25"  align="center" class="DataTable_Field" title="老师名称 ">名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="性别 ">性别</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="电话 ">电话</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>
						<th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	NetworkCourseTeacher po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NetworkCourseTeacher)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="" align="center">
			<%
				if(po.getLogo()!=null&&!po.getLogo().equals("")){
					if(po.getLogo().startsWith("http://")){
			%>
			<div style="float: left;padding: 5px 5px 5px 5px">
				<a href="javascript:thumbImgsDiv('<%=po.getLogo()%>',0)" >
					<img src="<%= po.getLogo()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
				</a>
			</div>
			<% }else{
			%>
			<div style="float: left;padding: 5px 5px 5px 5px">
				<a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getLogo()%>',0)" >
					<img src="<%=path%>/<%= po.getLogo()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
				</a>
			</div>
			<%
					}}
			%>
        </td>
		<td class="DataTable_Content" align="left" title="<%= po.getName() %>"><%= po.getName() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getSex()==0)out.print("男");else out.print("女"); %></td>								
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getMoblie())%>"><% if(po.getMoblie()!=null)out.print(po.getMoblie()); %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreateTime() %>"><%= po.getCreateTime() %></td>	
		<td class="DataTable_Content" align="left">
		 <a href="javascript:;" title="编辑"
						onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
					</a>
					<a id="del_mfp" title="删除"
						href="javascript:;" onclick="doDelsById('<%=po.getId()%>')"> <span
							class="img_delete"></span>
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
					<%@include file="../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>
