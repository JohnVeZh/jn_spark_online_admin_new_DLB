<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.NetworkCourseComment.NetworkCourseComment"%>
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
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		$(function(){
			if(${msg =="200" }){
				top.Dialog.close();
			}
		})
	
		//删除
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
					listForm.action="<%=path%>/business/NetworkCourseComment.do?act=deleteById&id="+id;
						listForm.submit();
					}
				)
			}
		}

		// 点赞
        function doVoteUpById(id, voteUp) {
            if(id != ""){
                top.Dialog.open({URL:"<%=path%>/business/NetworkCourseComment.do?act=preVoteUpEdit&id="+id+"&voteUp="+voteUp,ID:"a1",Width:450,Height:200,Title:"修改点赞数"});
            }
        }
		
		//清空查询数据
		function clear(){
			document.getElementById("ncNameQuery").value="";
			document.getElementById("startTime").value="";
			document.getElementById("endTime").value="";
		}
		
		//修改网课状态
		function ncState(id,isHide) {
			if(id!=""){
		  		top.Dialog.open({URL:"<%=path%>/business/NetworkCourseComment.do?act=updateCommentState&id="+id+"&isHide="+isHide,ID:"a1",Width:450,Height:200,Title:"修改评论状态"});
			}
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：网课评论管理</span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourseComment.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">

				<tr>
					<td>

						<div style="float: left">
							网课名称：<input type="text" name="ncNameQuery" id="ncNameQuery" value="${ncNameQuery }"/>&nbsp;
						</div>
						<div style="float: left">
							起始时间：<input type="text" name="startTime" id="startTime" value="${startTime }"/>&nbsp;<input type="text" name="endTime" id="endTime" value="${endTime }"/>
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="查询" />&nbsp;&nbsp;
						<input type="button" value="清空" onclick="clear()" /></td>
					</tr>

				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" width="3%" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" width="25%" align="center" class="DataTable_Field" title="评论内容">评论内容</th>
						<th height="25" width="300px" class="DataTable_Field" title="网课名称">网课名称</th>
						<th height="25" width="50px" align="center" class="DataTable_Field" title="点赞数">点赞数</th>
						<th height="25" width="250px" class="DataTable_Field" title="评论用户">评论用户</th>
						<th height="25" width="150px" align="center" class="DataTable_Field" title="评论时间">评论时间</th>
						<th height="25" width="80px" align="center" class="DataTable_Field" title="操作">操作</th>
					</tr>

					<c:forEach items="${commentList }" var="cList" varStatus="s">
						<tr >
							<td align="center"><input type="checkbox" name="id" value="${cList.id}" onclick="event.cancelBubble=true;"></td>
							<td class="DataTable_Content" align="center">${s.index+1 }</td>
							<td class="DataTable_Content" align="left"  title="${cList.content}">${cList.content}</td>
							<td class="DataTable_Content" align="left" title="${cList.ncName }">${cList.ncName}</td>
							<td class="DataTable_Content" align="center" title="${cList.voteUp }">${cList.voteUp}</td>
                            <td class="DataTable_Content" align="left" title="${cList.mobile }">${cList.mobile }</td>
							<td class="DataTable_Content" align="center" title="${cList.createTime }">${cList.createTime }</td>
							<td  align="center" >
                                <a id="voteUpComment" title="修改点赞" href="javascript:;" onclick="doVoteUpById('${cList.id}', '${cList.voteUp}')"> <span class="img_finger"></span></a>
                                &nbsp;&nbsp;
								<a id="delComment" title="删除评论" href="javascript:;" onclick="doDelsById('${cList.id }')"> <span class="img_delete"></span></a>
							</td>
						</tr>
						<input type="text" name="commentId" id="commentId" value="${cList.id }" style="display:none"/>
					</c:forEach>

			</table>
			 <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
				<div class="center">
					<div class="right">
						<%@include file="../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
		</form>
	</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>
