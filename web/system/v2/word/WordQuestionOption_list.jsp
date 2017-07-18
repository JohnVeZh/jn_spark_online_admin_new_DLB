<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.v2.word.TbWordQuestionOption"%>
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
	 /* $(function(){
	  top.Dialog.close();
	 }) */
		$(function(){
			var msg="";//jstl $ { msg}
			if("200"==msg){
				top.Dialog.alert("删除成功.");
			}
		})
	
		//删除数据
		function preDelete(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="Word.do?act=realdelete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){  
		    var wordId=$("#wordId").val();
		    if(wordId!=""){
		      top.Dialog.open({URL:"<%=path%>/business/Word.do?act=preWordQuestionOptionAdd&wordId="+wordId,ID:"a01",Width:600,Height:400,Title:"新增"});     
		    }
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			    top.Dialog.open({URL:"<%=path%>/business/Word.do?act=preWordQuestionOptionUpdate&id="+id+"&id="+id,ID:"a01",Width:600,Height:400,Title:"修改"});     
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：单词选项<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/Word.do?act=list" method="post">
			<input type="text" name="wordId" id="wordId" value="${wordId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
						<div style="float: left;">
							<a href="javascript:;" onclick="preAdd()" title="新增"> <span class="img_add"></span> </a>
						</div>
						<td/>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="5%" height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:32">序号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:100">单词</th>								 
						<th height="25"  align="center" class="DataTable_Field" >选项</th>								 
						<th height="25"  align="center" class="DataTable_Field" >内容</th>
						<th height="25"  align="center" class="DataTable_Field" >正确答案</th>
						<th height="25"  align="center" class="DataTable_Field" >操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	TbWordQuestionOption po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (TbWordQuestionOption)list.get(i);
%>
	<tr>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="center" title="${word}">${word}</td>	
		<td class="DataTable_Content" align="center" ><%= po.getPrefix() %></td>	
		<td class="DataTable_Content" align="left" title="<%= po.getContent() %>"><%= po.getContent() %></td>	
		<td class="DataTable_Content" align="center"><% if(po.getIsAnswer().equals("0")){out.print("否");}else if(po.getIsAnswer().equals("1")){out.print("是");} %></td>	
		<td class="DataTable_Content" align="center">
			<a href="javascript:;"	onclick="preUpdate('<%=po.getId()%>')" title="修改"> 
				<span class="img_edit"></span>	
			</a>
			<a href="javascript:;"	onclick="preDelete('<%=po.getId()%>')" title="删除"> 
				<span class="img_delete"></span>	
			</a>
		</td>		
 	</tr>
<%
	}
%>
			</table>
		</form>
	</div>
	</body>
</html>
