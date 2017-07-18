<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
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
<script type="text/javascript" src="<%=path%>/libs/js/table/detailTable.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->

<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

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
			if(${msg =="200" }){
				top.Dialog.close();
			}
		})

        //网课后面的作业列表
        function homeworkList(videoId,ncId) {
            listForm.action="Homework.do?act=homeworkList&ncId="+ncId+"&videoId="+videoId;
            listForm.submit();
        }

		//添加
		function preAdd(videoId,ncId){
		    <%--top.Dialog.open({URL:"<%=path%>/business/Dlb/Homework.do?act=toAdd",ID:"a1",Width:1080,Height:768,Title:"添加网课"});--%>
            listForm.action="Homework.do?act=toAdd&ncId="+ncId+"&videoId="+videoId;
            listForm.submit();
		}

		//清空查询数据
		function qing(){
			document.getElementById("ncNamelike").value="";
		}

	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：网课列表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/Dlb/Homework.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left">
						网课名称：<input type="text" name="ncNamelike" id="ncNamelike" value="${ncNamelike }"/>&nbsp;
					</div>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
            <table  class="detailTable" mode="list" useCheckbox="true" trClick="true" selectRowButtonOnly="false" id="listTable">
					<tr >
                        <th height="15" width="1" align="center" class="DataTable_Field" ></th>
						<th height="15" width="1" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="15" width="10" align="center" class="DataTable_Field" title="封面主图 ">封面主图</th>
						<th height="15" width="30" class="DataTable_Field" title="网课名称 ">网课名称</th>
						<th height="15" width="5" align="center" class="DataTable_Field" title="课程类型 ">课程类型</th>
						<th height="15" width="5" align="center" class="DataTable_Field" title="学段">学段</th>
						<th height="15" width="5" align="center" class="DataTable_Field" title="作业类型 ">作业类型 </th>
						<th height="15" width="35" align="center" class="DataTable_Field" title="开课时间 ">开课时间</th>
					</tr>
		            <c:forEach items="${courseList}" var="c" varStatus="s">
                        <tr>
                            <td align="center"><span class="hand img_add2"  keepdefaultstyle="true" title="点击展开"/></td>
                            <td class="DataTable_Content" align="left">${s.index+1 }</td>
                            <td class="DataTable_Content" align="left">
                                <c:if test="${c.ncImg !=null && !c.ncImg.equals('')}">
                                    <c:choose>
                                        <c:when test="${c.ncImg.startsWith('http://')}">
                                            <div style="float: left;padding: 5px 5px 5px 5px">
                                                <a href="javascript:thumbImgsDiv('${c.ncImg}',0)" >
                                                    <img src="${c.ncImg}" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
                                                </a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div style="float: left;padding: 5px 5px 5px 5px">
                                                <a href="javascript:thumbImgsDiv('<%=path%>/${c.ncImg}',0)" >
                                                    <img src="<%=path%>/${c.ncImg}" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
                                                </a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </td>
                            <td class="DataTable_Content" align="left" title="${c.ncName}">${c.ncName}</td>
                            <td class="DataTable_Content" align="left">
                                <c:if test="${c.ncType == 0}">录播课</c:if>
                                <c:if test="${c.ncType == 1}">直播课</c:if>
                            </td>
                            <td class="DataTable_Content" align="left">
                                <c:if test="${c.ncLevel.equals('cet4')}">四级</c:if>
                                <c:if test="${c.ncLevel.equals('cet6')}">六级</c:if>
                            </td>
                            <td class="DataTable_Content" align="left">
                                <c:if test="${c.ncLevelType.equals('system_course')}">系统课</c:if>
                                <c:if test="${c.ncLevelType.equals('special_course')}">专项课</c:if>
                                <c:if test="${c.ncLevelType.equals('public_course')}">公开课</c:if>
                                <c:if test="${c.ncLevelType.equals('wei_course')}">微课</c:if>
                            </td>
                            <td class="DataTable_Content" align="left" title="">
                                <fmt:formatDate value="${c.ncLiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/> -
                                <fmt:formatDate value="${c.ncEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                        <tr style="display: none;">
                            <td></td>
                            <td colspan="6">
                                <table class="tableStyle" style="font-size: 35px;">
                                    <tbody>
                                    <tr>
                                        <th height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                                        <th height="25"  align="center" class="DataTable_Field" title="视频名称">视频名称</th>
                                        <th height="25"  align="center" class="DataTable_Field" title="开始时间">开始时间</th>
                                        <th height="25"  align="center" class="DataTable_Field" title="时长">时长</th>
                                        <th height="25"  align="center" class="DataTable_Field" title="授课老师">授课老师</th>
                                        <th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>
                                    </tr>
                                    <c:forEach items="${c.mList }" var="v" varStatus="n">
                                        <tr>
                                            <td class="DataTable_Content" align="left">${n.index+1 }</td>
                                            <td class="DataTable_Content" align="left" title="${v.ncvName}">${v.ncvName}</td>
                                            <td class="DataTable_Content" align="left" title="">
                                                <fmt:formatDate value="${v.ncvStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </td>
                                            <td class="DataTable_Content" align="left" title="${v.ncvDuration}">${v.ncvDuration}</td>
                                            <td class="DataTable_Content" align="left" title="${v.teacher}">${v.teacher}</td>
                                            <td class="DataTable_Content" align="left" >
                                                <a id="del_mfp" title="绑定作业"
                                                   href="javascript:;" onclick="homeworkList('${v.videoId}','${c.ncId}')" > <span
                                                        class="img_history" ></span>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
			</table>
			 <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
		</form>
	</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>
