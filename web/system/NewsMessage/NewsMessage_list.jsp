<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.NewsMessage.NewsMessage"%>
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
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
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
			if(idVal !=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NewsMessage.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id !=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NewsMessage.do?act=realdeleteById&mId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    listForm.action="NewsMessage.do?act=preAdd"
			listForm.submit();
		}
		//修改
		function preUpdate(id) {
			//var idVal = isSel();
			if(id !=""){
			  listForm.action="NewsMessage.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id !=""){
			  top.Dialog.open({URL:"<%=path%>/business/NewsMessage.do?act=view&id="+id,ID:"a2",Width:1024,Height:768,Title:"查看"});
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("newsid").value="";
			document.getElementById("sel").value="";
		}
		//置顶
		function toTop(id,enable){
			if(id!=""){
				listForm.action="<%=path%>/business/NewsMessage.do?act=toTop&id="+id+"&enable="+enable;
				listForm.submit();
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：评论管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NewsMessage.do?act=list" method="post">
			    	    <input type="text" name="newsId" id="newsId" value="${newsId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
						<a id="del_mfp" href="javascript:;" title="删除" onclick="doDels('${l.id}')">
						 <span class="img_delete"></span>
						</a>
						资讯：<input   type="text" name="title" value="${title }" />
						评论内容：<input   type="text" name="newsContent" value="${newsContent }"  />
						用户：<input type="text" id="username" name="username" value="${username }"/>
						手机号：<input type="text" id="mobile" name="mobile" value="${mobile }"/>
						评论时间：<input  class="date" type="text" name="starttime" value="${starttime }" id="starttime"  />-<input  class="date" type="text" name="endtime" value="${endtime }" id="endtime"  />
						<input type="submit" value="查询" />&nbsp;&nbsp;
						<input type="button" value="清空" onclick="qing()" />
					 </td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="资讯 ">资讯</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="评论内容">评论内容</th>
						<th height="25"  align="center" class="DataTable_Field" title="评论时间 ">评论时间</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="用户">用户</th>
						<th height="25"  align="center" class="DataTable_Field" title="手机号">手机号</th>
						<th height="25"  align="center" class="DataTable_Field" title="置顶时间">置顶时间 </th>
						<th height="25"  align="center" class="DataTable_Field" title="操作" >操作</th>								 
					</tr>
				<c:forEach items="${lm }" var="l" varStatus="s"> 
					<tr  >
				    	<td align="center"><input type="checkbox" name="id" value="${l.id}" onclick="event.cancelBubble=true;"></td>
				        <td align="center">${s.index+1 }</td>
						<td class="DataTable_Content" align="left" title="${l.title }">${l.title }</td>								
						<td class="DataTable_Content" align="left" title="${l.content}">${l.content}</td>
						<td class="DataTable_Content" align="left" title="${l.createtime}">${l.createtime}</td>								
						<td class="DataTable_Content" align="left" title="${l.userName }">${l.userName }</td>
						<td class="DataTable_Content" align="left" title="${l.mobile }">${l.mobile }</td>
						<td class="DataTable_Content" align="left" title="${l.topTime}">${l.topTime}</td>
						<td class="DataTable_Content" align="left">
						<a href="javascript:;" title="查看" onclick="view('${l.id}')">
						 <span class="img_view"></span>
						</a>
						<a id="del_mfp" href="javascript:;" title="删除" onclick="doDelsById('${l.id}')">
						 <span class="img_delete"></span>
						</a>
							<c:if test="${l.isTop == 1}">
								<a  href="javascript:;" title="取消置顶" onclick="toTop('${l.id}','0')">
									<span class="img_jing"></span>
								</a>
							</c:if>
							<c:if test="${l.isTop == 0}">
								<a  href="javascript:;" title="置顶" onclick="toTop('${l.id}','1')">
									<span class="img_btn_up2"></span>
								</a>
							</c:if>
						</td>
				 	</tr>
				</c:forEach>
 	
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
