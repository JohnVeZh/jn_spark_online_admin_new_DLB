<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.JQrCode.JQrCode"%>
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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
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
				   		listForm.action="JQrCode.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(idVal) {
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="JQrCode.do?act=realdeleteById&idVal="+idVal;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){    
		
			top.Dialog.open({URL:"<%=path%>/business/JQrCode.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});
		   
		}
		//修改
		function preUpdate(idVal) {
			if(idVal!=""){
				top.Dialog.open({URL:"<%=path%>/business/JQrCode.do?act=preUpdate&id="+idVal,ID:"a2",Width:1024,Height:768,Title:"编辑"});
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="JQrCode.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("nameStr").value="";
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：二维码集中管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/JQrCode.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
						<div style="float: left;">
							<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
									class="img_add" ></span>
							</a>
							<a id="del_mfp"
								href="javascript:;" onclick="doDels()" title="删除"> <span
									class="img_delete"></span>
							</a>	
						</div>
						<div style="float: left;">
						内容：<input type="text" name="nameStr" value="${nameStr }"/>
						</div>
						<div style="float: left;">
						  <select name="typeStr">
						     <option value="">全部类型</option>
						     ${selStr }
						  </select>
						</div>
						  
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25"  align="center" class="DataTable_Field" title="序号">序号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="二维码内容">二维码内容</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="二维码类型">类型</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="目标类型 ">目标类型</th>								 
                        <th height="25"  align="center" class="DataTable_Field" title="操作 ">操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	JQrCode po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (JQrCode)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" >
			<div class="textSlice" title="<%= po.getCode() %>"><%= po.getCode() %></div>
		</td>								
		<td class="DataTable_Content" align="left" >
			<div class="textSlice" title="<%= Tool.getValue("select name from sys_config where type='J_CODE_TYPE' and alias='"+po.getType()+"'") %>"><%= Tool.getValue("select name from sys_config where type='J_CODE_TYPE' and alias='"+po.getType()+"'") %></div>
		</td>								
		<td class="DataTable_Content" align="left" >
			<div class="textSlice" title="<%= po.getTargetType() %>"><% if(po.getTargetType()==0)out.print("列表");else out.print("详情"); %></div>
		</td>								
       <td align="left">
	 		<a href="javascript:;"
				onclick="preUpdate('<%=po.getId()%>')" title="修改"> <span class="img_edit"></span>
			</a>
			<a id="del_mfp"
				href="javascript:;" onclick="doDelsById('<%=po.getId()%>')" title="删除"> <span
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
