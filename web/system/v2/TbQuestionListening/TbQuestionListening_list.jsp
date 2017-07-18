<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
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
				   		listForm.action="TbQuestionListening.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//删除单个数据
		function doDelById(id){
		  if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="TbQuestionListening.do?act=delete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){     
		    var id=$("#id").val();
		    var flag = $("#flag").val();
		    if(flag == "1"){
                if(id != ""){
                    top.Dialog.open({URL:"<%=path%>/business/TbQuestionListening.do?act=preAdd&id="+id,ID:"a1",Width:1024,Height:768,Title:"新增"});
                }
            }else {
                top.Dialog.alert('<h3 align="center">选择左侧对应的菜单后'+'<br>'+'再点击添加按钮</h3>')
			}
		}
		//修改
		function preUpdate(id) {
			if(id !=""){
			top.Dialog.open({URL:"<%=path%>/business/TbQuestionListening.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
			}
		}

		//配题
		function matchSubject(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/TbQuestionListening.do?act=matchSubject&catalogId="+id,ID:"a1",Width:1024,Height:768,Title:"查看"});
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		  $(function(){
        top.Dialog.close()
        })
		
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" align="left">
					<span>当前位置：听力类别管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/TbQuestionListening.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
			  <input type="text" name="parentid" value="${parentid }" id="parentid" style="display: none"/>
				<input type="hidden" name="flag" value="${flag}" id="flag"/>
				<table style="width:100%">
				<tr>
					<td>

					<a href="javascript:;" onclick="preAdd()" title="新增"> <span
							class="img_add"></span>
					</a>
						&nbsp;&nbsp;
					<a id="" title="批量导入"
					   href="javascript:;" onclick="top.Dialog.open({URL:'<%=path%>/system/v2/TbQuestionListening/TbQuestionListening_export.jsp',Title:'听力专区',Width:400,Height:200});"> <span
							class="img_xls"></span>批量导入
					</a>
						&nbsp;&nbsp;
					<a id="" title="模板下载"
					   href="<%=path%>/business/TbQuestionListening.do?act=fileDownload&fileName=听力.xls&filePath=/template/training/听力.xls" >
							模板下载
					</a>

					</td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr >
					<th width="3%" height="25" align="center" class="DataTable_Field">
					</th>
			        <th width="5%" height="25"  align="center" class="DataTable_Field">序号</th>
					<th height="25"  align="center" class="DataTable_Field" title="目录名称">目录名称</th>
					<th height="25"  align="center" class="DataTable_Field" title="音频地址">音频地址</th>
					<th height="25"  width="5%" align="center" class="DataTable_Field" title="试听">试听</th>
					<th height="25"  width="5%" align="center" class="DataTable_Field" title="目录排序">目录排序</th>
					<th height="25"  align="center" class="DataTable_Field" title="题型">题型</th>
					<th height="25"  align="center" class="DataTable_Field" title="创建日期">创建日期</th>
					<th height="25"  width="6%" align="center" align="center" class="DataTable_Field" title="" >操作</th>
				</tr>
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Object[] po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Object[]) list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" id="id" value="<%=po[0]%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po[0] %>"><%= po[1] %></td>
		<td class="DataTable_Content" align="left" title="<%= po[2] %>"><%= po[2] %></td>
		<td class="DataTable_Content" align="left" >
			<audio style="width: 80px" controls="controls">
				<source src="<% if(po[2].toString().startsWith("http")) {%><% if(null != po[2].toString()){out.print(po[2].toString());} %><%}else{ %>http://7xqc0j.com1.z0.glb.clouddn.com/spark-exam/spark-exam/<% if(null != po[2].toString()){out.print(po[2].toString());} %><%} %>" /></audio> </td>
		<td class="DataTable_Content" align="left" title="<%= po[3] %>"><%= po[3] %></td>
		<td class="DataTable_Content" align="left" title="<%= po[4] %>"><%= po[4] %></td>
		<td class="DataTable_Content" align="left" title=""><fmt:formatDate value="<%= (Date)po[5] %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>
			<a href="javascript:;" onclick="matchSubject('<%=po[0]%>')" title="配题">
				<span class="img_txt"></span>
			<a href="javascript:;" onclick="preUpdate('<%=po[0]%>')" title="修改">
			<span class="img_edit"></span>
			</a>
			<a id="del_mfp" href="javascript:;" title="删除" onclick="doDelById('<%=po[0]%>')">
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
					<%@include file="../../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
