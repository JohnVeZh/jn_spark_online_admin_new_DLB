<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommonWords.CommonWords"%>
<%@page import="com.easecom.common.util.*"%>
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
		  $(function(){
        top.Dialog.close()
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
				   		listForm.action="CommonWords.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id){
		  if(id != ""){
		    top.Dialog.confirm("您确信要删除吗?",
			   	function() {
			   		listForm.action="CommonWords.do?act=realdelete&id="+id;
					listForm.submit();
				});
		  }
		}
		
		//添加
		function preAdd(){  
		    var ctId=$("#ctId").val();
		    //alert(ctId);
		    if(ctId !=""){
		       top.Dialog.open({URL:"<%=path%>/business/CommonWords.do?act=preAdd&ctId="+ctId,ID:"a1",Width:1024,Height:768,Title:"新增"});
		
			   // listForm.action="CommonWords.do?act=preAdd&ctId="+ctId;
			  //  listForm.submit();
		    }
		}
		
		//修改
		function preUpdate(id) {
			//var idVal = isSel();
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/CommonWords.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
		   }
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/CommonWords.do?act=view&id="+id,ID:"a1",Width:1024,Height:768,Title:"查看"});
		   }
		}
		//清空查询数据
		function qing(){
			document.getElementById("word").value="";
		}
		function back(){
		   listForm.action="CommonType.do?act=type";
		   listForm.submit();
		   //history.back();
		}
		
		function preExl(ctId) {
			if(ctId!=""){
				top.Dialog.open({URL:"<%=path%>/business/CommonWords.do?act=preExl&ctId="+ctId,ID:"a3",Width:400,Height:200,Title:"导入"}); 
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：常用词汇管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/CommonWords.do?act=list" method="post">
			<input type="text" name="ctId" id="ctId" value="${ctId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><a href="javascript:;" onclick="preAdd('')" title="新增" > <span
							class="img_add"></span>
					</a>
					<a id="del_mfp" title="删除"
						href="javascript:;" onclick="doDels()"> <span
							class="img_delete"></span>
					</a>
					 <a id="" title="刷数据" 
						href="javascript:;" onclick="preExl('${ctId }')"> <span
							class="img_xls"></span>
					   </a>
					         单词名称：<input type="text" name="words" id="word" value="${word }"/>&nbsp;&nbsp;
						<input type="submit" value="查询" />&nbsp;&nbsp; 
						<input type="button" value="清空" onclick="qing()" />&nbsp;&nbsp; 
						</td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
				        <th width="5%">序号</th>
				        <th height="25"  align="center" class="DataTable_Field" title="单词名称 * 类型：String 长度:200">单词名称</th>
						<th height="25"  align="center" class="DataTable_Field" title="音标 * 类型：String 长度:200">音标</th>
						<th height="25"  align="center" class="DataTable_Field" title="考频 * 类型：int 长度:10">考频</th>	
						<th height="25"  align="center" class="DataTable_Field" title="类别 * 类型：String 长度:32">类别</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建日期 * 类型：String 长度:32">创建日期</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="音频地址 * 类型：String 长度:2000">音频地址</th>
						<th height="25"  align="center" class="DataTable_Field" title="考频 * 类型：int 长度:10">试听</th>									 
						<th height="25"  align="center" class="DataTable_Field" title="排序值 * 类型：int 长度:10">排序</th>
						<th >操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	CommonWords po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (CommonWords)list.get(i);
		String typeName = Tool.getValue("select type_name from common_type where id='"+po.getCtId()+"'");
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left" title="<%= po.getWord() %>"><%= po.getWord() %></td>
        <td class="DataTable_Content" align="left" title="<%= CommUtil.isString(po.getPhonogram()) %>"><%= CommUtil.isString(po.getPhonogram()) %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getTestFrequency() %>"><%= po.getTestFrequency() %></td>								
		<td class="DataTable_Content" align="left" title="<%=typeName    %>"><%=typeName    %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left" title="<%= CommUtil.isString(po.getAudioPath()) %>"><%= CommUtil.isString(po.getAudioPath()) %></td>								
		<td class="DataTable_Content" align="left" ><audio style="width: 80px" controls="controls"><source src="<% if(po.getAudioPath().startsWith("http")) {%><% if(null != po.getAudioPath()){out.print(po.getAudioPath());} %><%}else{ %>http://7xqc0j.com1.z0.glb.clouddn.com/spark-exam/spark-exam/<% if(null != po.getAudioPath()){out.print(po.getAudioPath());} %><%} %>" /></audio></td>		
		<td class="DataTable_Content" align="left" title="<%= po.getSort() %>"><%= po.getSort() %></td>	
		<td>
		<a href="javascript:;" onclick="preUpdate('<%=po.getId()%>')" title="修改">
		 <span class="img_edit"></span>
		</a>
		<a href="javascript:;" onclick="view('<%=po.getId()%>')" title="查看">
			 <span class="img_view"></span>
		</a>
		<a id="del_mfp" href="javascript:;" onclick="doDelsById('<%=po.getId()%>')" title="删除"> 
		<span class="img_delete"></span>
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
