<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.MatchedPapersTopicReadType.MatchedPapersTopicReadType"%>
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

 

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
	function back(){
		top.Dialog.close();
	}
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
				   		listForm.action="MatchedPapersTopicReadType.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="MatchedPapersTopicReadType.do?act=realdelete&tId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){    
		    var mptrId=$("#mptrId").val();
		    if(mptrId!=""){
		      top.Dialog.open({URL:"<%=path%>/business/MatchedPapersTopicReadType.do?act=preAdd&mptrId="+mptrId,ID:"a3",Width:1024,Height:768,Title:"新增"});    
		    }   
	//	    listForm.action="MatchedPapersTopicReadType.do?act=preAdd"
   //			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			 top.Dialog.open({URL:"<%=path%>/business/MatchedPapersTopicReadType.do?act=preUpdate&id="+id,ID:'a3',Width:1024,Height:768,Title:"修改"});    
//			  listForm.action="MatchedPapersTopicReadType.do?act=preUpdate&id="+id;
//			  listForm.submit();
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="MatchedPapersTopicReadType.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		 
		  //配套题目
		 function preSubject(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/MatchedPapersTopicReadSubject.do?act=list&mptrtId="+id,ID:"a3",Width:1024,Height:768,Title:"配套题目"});    
//			  listForm.action="MatchedPapersTopicReadSubject.do?act=list&mptrtId="+id;
//			  listForm.submit();
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：模块管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/MatchedPapersTopicReadType.do?act=list" method="post">
			<input type="text" name="mptrId" value="${mptrId }" id="mptrId" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<a href="javascript:;" title="新增" onclick="preAdd('')"> <span
							class="img_add"></span>
					</a>
					<a id="del_mfp" title="删除"
						href="javascript:;" onclick="doDels()"> <span
							class="img_delete"></span>
					</a>
							  
						<!-- <input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /> --></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="题号名称">模块名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="排序 ">排序</th>	
						<th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	MatchedPapersTopicReadType po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (MatchedPapersTopicReadType)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getTypeName() %>"><%= po.getTypeName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getSort() %>"><%= po.getSort() %></td>		
		<td class="DataTable_Content" align="left">
			<a href="javascript:;" title="修改"
						onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
					</a>
					<a href="javascript:;" title="配题"
						onclick="preSubject('<%=po.getId()%>')"> <span class="img_page"></span>
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
	</body>
</html>
