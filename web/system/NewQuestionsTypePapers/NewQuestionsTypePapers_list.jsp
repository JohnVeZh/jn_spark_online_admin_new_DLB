<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.NewQuestionsTypePapers.NewQuestionsTypePapers"%>
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
		function doDels() {
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NewQuestionsTypePapers.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		
		function doDelsById(id){
		  if(id !=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NewQuestionsTypePapers.do?act=realdeleteById&id="+id;
						listForm.submit();
					}
				)
			}
		}
		
		//添加
		function preAdd(){   
		    var nqtId=$("#nqtId").val();
		    if(nqtId!=""){
		    top.Dialog.open({URL:"<%=path%>/business/NewQuestionsTypePapers.do?act=preAdd&nqtId="+nqtId,ID:"a1",Width:1024,Height:768,Title:"新增"});    
		   }   
		//    listForm.action="NewQuestionsTypePapers.do?act=preAdd"
		//	listForm.submit();
		}
		//修改
		function preUpdate(id) {
			//var idVal = isSel();
			if(id !=""){
			  top.Dialog.open({URL:"<%=path%>/business/NewQuestionsTypePapers.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});    
//			  listForm.action="NewQuestionsTypePapers.do?act=preUpdate&id="+id;
//			  listForm.submit();
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="NewQuestionsTypePapers.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		
		//试题专区
		function preSubject(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/NewQuestionsTypePapers.do?act=nqpcList&nqtpId="+id,ID:"a1",Width:1024,Height:768,Title:"试题专区"});    
			}
		}
		
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		//
		function preHear(id) {
			if(id!=""){
		  	  top.Dialog.open({URL:"<%=path%>/business/NewQuestionsPapersTopicType.do?act=list&nqtpId="+id,ID:"a1",Width:1024,Height:768,Title:"配套听力"});    
//			   listForm.action="NewQuestionsPapersTopicType.do?act=list&nqtpId="+id;
//		  	   listForm.submit();
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：听力管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NewQuestionsTypePapers.do?act=list" method="post">
			<input type="text" name="nqtId" value="${nqtId }" id="nqtId" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<a href="javascript:;" onclick="preAdd('')" title="新增">
					    <span class="img_add"></span>
					</a>
					<a id="del_mfp" href="javascript:;" title="删除" onclick="doDels()">
						 <span class="img_delete"></span>
					</a>
					<a id="" href="javascript:;" title="听力" onclick="top.Dialog.open({URL:'<%=path%>/system/NewQuestionsTypePapers/hearing_export.jsp',Title:'听力'});">
					   <span class="img_mp3"></span>
					</a>
					<!--  <input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" />-->	
						
					</td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="">列表图片</th>
						<th height="25"  align="center" class="DataTable_Field" title="">详情图片</th>					 
						<th width="35%"height="25"  align="center" class="DataTable_Field" title="">试卷名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="">创建时间</th>								 
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="">排序</th>
						<th>操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	NewQuestionsTypePapers po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NewQuestionsTypePapers)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
         <td class="DataTable_Content" align="center">
        <% 
        	if(po.getMpListImgpath()!=null&&!po.getMpListImgpath().equals("")){ 
        		if(po.getMpListImgpath().startsWith("http://")){
        %>
       		 <div style="float: center;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=po.getMpListImgpath()%>',0)" >
			      <img src="<%= po.getMpListImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        	<% }else{
			%>
        	<div style="float: center;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getMpListImgpath()%>',0)" >
			      <img src="<%=path%>/<%= po.getMpListImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        <% 
        	}}
        %>
        </td>
        <td class="DataTable_Content" align="center">
        <% 
        	if(po.getMpViewImgpath()!=null&&!po.getMpViewImgpath().equals("")){
        		if(po.getMpListImgpath().startsWith("http://")){
        %>
        	<div style="float: center;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=po.getMpViewImgpath()%>',0)" >
			      <img src="<%= po.getMpViewImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
	        <% }else{
			%>
        	<div style="float: center;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getMpViewImgpath()%>',0)" >
			      <img src="<%=path%>/<%= po.getMpViewImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        <% 
        	}}
        %>
        </td>
		<td class="DataTable_Content" align="left" title="<%= po.getMpName() %>"><%= po.getMpName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getSort() %>"><%= po.getSort() %></td>
		<td>
		    <a href="javascript:;" onclick="preUpdate('<%=po.getId()%>')" title="修改"> 
				<span class="img_edit"></span>
			</a>
			<a href="javascript:;" title="配套听力" onclick="preHear('<%=po.getId()%>')">
				 <span class="img_mp3"></span>
			</a>
			<a id="del_mfp" href="javascript:;" title="删除" onclick="doDelsById('<%=po.getId()%>')"> 
				<span class="img_delete"></span>
			</a>
			<a id="del_mfp" title="导入字幕"
						href="javascript:;" onclick="top.Dialog.open({URL:'<%=path%>/business/NewQuestionsTypePapers.do?act=prelyricExl&nqtpId=<%=po.getId()%>',Title:'听力',Width:400,Height:200});"> <span
							class="img_xls"></span>
			</a>
			<a href="javascript:;" class="showOrhide" title="试题专区"
			onclick="preSubject('<%=po.getId()%>')"> <span class="img_list"></span>
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
