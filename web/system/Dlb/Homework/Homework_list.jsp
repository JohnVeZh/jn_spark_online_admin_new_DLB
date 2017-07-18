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

    //解绑网课
    function deleteById(id){
        listForm.action="<%=path%>/business/Dlb/Homework.do?act=delete&id="+id;
        listForm.submit();
    }

    //网课下面的作业列表
    function homeworkList(videoId,ncId) {
        listForm.action="Homework.do?act=homeworkList&ncId="+ncId+"&videoId="+videoId;
        listForm.submit();
    }

    //添加作业
    function preAdd(videoId,ncId){
        <%--top.Dialog.open({URL:"<%=path%>/business/Dlb/Homework.do?act=toAdd",ID:"a1",Width:1080,Height:768,Title:"添加网课"});--%>
        listForm.action="Homework.do?act=toAdd&ncId="+ncId+"&videoId="+videoId;
        listForm.submit();
    }

    //修改作业
    function preUpdate(id) {
        listForm.action="Homework.do?act=preUpdate&id="+id;
        listForm.submit();
    }

    //清空查询数据
    function qing(){
        document.getElementById("ncNamelike").value="";
    }

    //选择听力作业题目
    function preSub(execriseId,questionType){
        <%--top.Dialog.open({URL:"<%=path%>/business/Dlb/HomeworkQuestion.do?act=listenList&execriseId="+execriseId,Title:"绑定窗口",Width:1024,Height:768});--%>
        listForm.action="<%=path%>/business/Dlb/HomeworkQuestion.do?act=listenList&execriseId="+execriseId+"&questionType="+questionType;
        listForm.submit();
    }

</script>
	<body>
        <div id="scrollContent">
            <div class="position">
                <div class="center">
                    <div class="left">
                        <div class="right">
                        <span>当前位置：网课列表>>作业列表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                        </div>
                    </div>
                </div>
            </div>
            <form name="listForm" scope="request" action="<%=path%>/business/Dlb/Homework.do?act=homeworkList" method="post" >
                <input type="hidden" name="videoId" value="${courseCatalogId}"/>
                <input type="hidden" name="ncId" value="${courseId}"/>
                <div class="box2" panelTitle="功能面板" roller="false">
                    <table style="width:100%">
                    <tr>
                        <td>
                        <div style="float: left">
                            <a href="javascript:;" onclick="preAdd('${courseCatalogId}','${courseId}')" title="新增"> <span
                                    class="img_add"></span>
                            </a>
                        </div>
                        <div style="float: left">
                            作业名称：<input type="text" name="titleLike" id="titleLike" value="${titleLike }"/>&nbsp;
                        </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input
                            type="button" value="清空" onclick="qing()" />
                        </td>
                    </tr>
                    </table>
                </div>
                <table  class="detailTable" mode="list" useCheckbox="true" trClick="false" selectRowButtonOnly="false" id="listTable">
                    <tr >
                        <th height="15" width="1" align="center" class="DataTable_Field" title="序号 ">序号</th>
                        <th height="15" width="20" align="center" class="DataTable_Field" title="网课名称">网课名称</th>
                        <th height="15" width="30" align="center" class="DataTable_Field" title="视频名称">视频名称</th>
                        <th height="15" width="30" align="center" class="DataTable_Field" title="作业名字">作业名字</th>
                        <th height="15" width="3" align="center" class="DataTable_Field" title="学段">学段</th>
                        <th height="15" width="3" align="center" class="DataTable_Field" title="作业类型 ">作业类型</th>
                        <th height="15" width="1" align="center" class="DataTable_Field" title="排序">排序</th>
                        <th height="15" width="15" align="center" class="DataTable_Field" title="展示时间 ">展示时间</th>
                        <th height="15" width="5" align="center" class="DataTable_Field" title="操作 ">操作</th>
                    </tr>
                    <c:forEach items="${homeworkList}" var="h" varStatus="s">
                        <tr>
                            <td class="DataTable_Content" align="left">${s.index+1 }</td>
                            <td class="DataTable_Content" align="left" title="${h.title}">${h.title}</td>
                            <td class="DataTable_Content" align="left" title="${ncName}">${ncName}</td>
                            <td class="DataTable_Content" align="left" title="${ncvName}">${ncvName}</td>
                            <td class="DataTable_Content" align="left">
                                <c:if test="${'01'.equals(h.section)}">四级</c:if>
                                <c:if test="${'02'.equals(h.section)}">六级</c:if>
                            </td>
                            <td class="DataTable_Content" align="left">
                                <c:if test="${h.questionType == 1}">听力</c:if>
                                <c:if test="${h.questionType == 2}">阅读</c:if>
                                <c:if test="${h.questionType == 3}">翻译</c:if>
                                <c:if test="${h.questionType == 4}">写作</c:if>
                            </td>
                            <td class="DataTable_Content" align="left" title="${h.sort}">${h.sort}</td>
                            <td class="DataTable_Content" align="left" title="">${h.displayDate}</td>
                            <td class="DataTable_Content" align="left" title="">
                                <a href="javascript:;" onclick="preSub('${h.id}','${h.questionType}')" title="绑定题目"> <span
                                        class="img_history"></span>
                                </a>
                                <a href="javascript:;" title="修改"
                                   onclick="preUpdate('${h.id}')"> <span class="img_edit"></span>
                                </a>
                                <a id="del_mfp" title="解绑作业"
                                   href="javascript:;" onclick="deleteById('${h.id}')"> <span
                                        class="img_delete"></span>
                                </a>
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
	</body>
</html>
