<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommunityPostMessage.CommunityPostMessage"%>
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
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="<%=path%>/business/CommunityPostMessage.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="<%=path%>/business/CommunityPostMessage.do?act=realdeleteById&mesId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(postId ){       
		    listForm.action="<%=path%>/business/CommunityPostMessage.do?act=preAdd&postId="+postId;
			listForm.submit();
		}
		//修改
		function preUpdate(j) {
			var idVal = isSel();
			if(idVal!=""){
			  listForm.action="<%=path%>/business/CommunityPostMessage.do?act=preUpdate"+idVal;
			  listForm.submit();
			}
		}
		//详情
		function view(id) {
			if(id!=""){
			 top.Dialog.open({URL:"<%=path%>/business/CommunityPostMessage.do?act=view&id="+id,ID:id,Width:1024,Height:768,Title:"查看"});
			}
		}
		//查看回复
		function serchReply(id) {
			if(id!=""){
				top.Dialog.open({URL:"<%=path%>/business/CommunityPostMessage.do?act=list&replayCommentsId="+id,ID:id,Width:1024,Height:768,Title:"查看"});
			}
		}

		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		function back(){
		  history.back();
		}

        //置顶
        function toTop(id,enable){
            if(id!=""){
                listForm.action="<%=path%>/business/CommunityPostMessage.do?act=toTop&id="+id+"&enable="+enable;
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
					<span>当前位置：帖子评论管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/CommunityPostMessage.do?act=list" method="post">
			<input type="text" name="postId" value="${postId }" style="display: none"/>
			<input type="hidden" name="commentsId" value="${commentsId}"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
						<a id="del_mfp" title="删除"
						   href="javascript:;" onclick="doDels()"> <span
								class="img_delete">_</span>
						</a>
						活动：<input   type="text" name="title" value="${title }" />
						评论内容：<input   type="text" name="newsContent" value="${newsContent }"  />
						用户：<input type="text" id="username" name="username" value="${username }"/>
						手机号：<input type="text" id="mobile" name="mobile" value="${mobile }"/>
						评论时间：<input  class="date" type="text" name="starttime" value="${starttime }" id="starttime"  />-<input  class="date" type="text" name="endtime" value="${endtime }" id="endtime"  />
						<input type="submit" value="查询" />&nbsp;&nbsp;
						<input type="button" value="清空" onclick="qing()" />

				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="标题 ">标题</th>
						<th height="25"  align="center" class="DataTable_Field" title="评论内容 ">评论内容 </th>
						<th height="25"  align="center" class="DataTable_Field" title="用户">用户</th>
						<th height="25"  align="center" class="DataTable_Field" title="联系电话">联系电话</th>
						<th height="25"  align="center" class="DataTable_Field" title="点赞数量 ">点赞数量 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="评论时间 ">评论时间 </th>								 
                        <th height="25"  align="center" class="DataTable_Field" title="置顶时间">置顶时间 </th>
						<th height="25"  align="center" class="DataTable_Field" title="操作">操作 </th>	
					</tr>
				 
	  <c:forEach items="${lm }" var="l" varStatus="s">
		<tr  >
	    	<td align="center"><input type="checkbox" name="id" value="${l.id }" onclick="event.cancelBubble=true;"></td>
	        <td align="center">${s.index+1 }</td>
			<td class="DataTable_Content" align="left" title="${l.title}">${l.title}</td>
			<td class="DataTable_Content" align="left" title="${l.content}">${l.content}</td>
			<td class="DataTable_Content" align="left" title="${l.userName}">${l.userName}</td>
			<td class="DataTable_Content" align="left" title="${l.mobile}">${l.mobile}</td>
			<td class="DataTable_Content" align="left" title="${l.likeNum}">${l.likeNum}</td>								
			<td class="DataTable_Content" align="left" title="${l.createtime}">${l.createtime}</td>								
            <td class="DataTable_Content" align="left" title="${l.topTime}">${l.topTime}</td>
			<td class="DataTable_Content" align="left">
			 	<a href="javascript:;" title="查看"
							onclick="view('${l.id}')"> <span class="img_view"></span>
						</a> 
						<a id="del_mfp" title="删除"
							href="javascript:;" onclick="doDelsById('${l.id}')"> <span
								class="img_delete"></span>
						</a>
				<a  title="回复查看"  href="javascript:;" onclick="serchReply('${l.id}')">
					<span class="img_bubble"></span>
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
