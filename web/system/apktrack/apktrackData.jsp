<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript">


</script>

<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：APP流量统计</span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/apktrack.do?act=apktrackData" method="post">
        <div class="box2" paneltitle="功能面板" roller="false" boxtype="box2"><div class="box_topcenter" id="box_topcenter"><div class="box_topleft"><div class="box_topright"><div class="title"><span>功能面板</span></div><div class="boxSubTitle"></div><div class="status"><span class="ss">收缩</span></div><div class="clear"></div></div></div></div><div class="box_middlecenter"><div class="box_middleleft"><div class="box_middleright"><div class="boxContent" style="overflow: visible;">
            <table style="width:100%">
                <tbody><tr>
                    <td>
                        <div style="float: left">
                           	 开始日期：<input type="text" name="startTime" value="${startTime}" class="date"/>&nbsp;&nbsp;
                        </div>
                        <div style="float: left">
                           	 结束日期： <input type="text" name="endTime" value="${endTime}" class="date"/>&nbsp;&nbsp;
                        </div>

                        <div style="float: left">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="submit" value="查询" class="button" style="font-family: 宋体; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:void(0);" id="add"> <span class="icon_list">渠道管理</span></a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        </div>
        </div>
        </div>
        
        <table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
        	<colgroup>
        		<col style="width:40px;"/>
        		<col style="width:100px;"/>
        		<col style="width:80px;"/>
        		<col style="width:50px;"/>
        		<col style="width:50px;"/>
        		<col style="width:50px;"/>
        		<col style="width:50px;"/>
        		<col style="width:50px;"/>
        		<col style="width:50px;"/>
                <c:forEach items="${areaList}" var="area">
	        		<col style="width:50px;"/>
	        		<col style="width:50px;"/>
                </c:forEach>
        		<col style="width:50px;"/>
        		<col style="width:50px;"/>
        	</colgroup>
            <tr>
                <th rowspan="2" align="center" class="DataTable_Field" title="序号">序号</th>
                <th rowspan="2" align="center" class="DataTable_Field" title="渠道名称">渠道名称</th>
                <th rowspan="2" align="center" class="DataTable_Field" title="渠道代码">渠道代码</th>
                <th colspan="4" align="center" class="DataTable_Field" title="渠道引流">渠道引流</th>
                <th colspan="2" align="center" class="DataTable_Field" title="总计">总计</th>
                <c:forEach items="${areaList}" var="area">
                	<th colspan="2" align="center" class="DataTable_Field" title="${area}">${area}</th>
                </c:forEach>
                <th colspan="2" align="center" class="DataTable_Field" title="其他地区">其他地区</th>
            </tr>
            <tr>
            	<th align="center" class="DataTable_Field">安卓</th>
            	<th align="center" class="DataTable_Field">IOS</th>
            	<th align="center" class="DataTable_Field">PC</th>
            	<th align="center" class="DataTable_Field">其他</th>
            	<th align="center" class="DataTable_Field">PV</th>
            	<th align="center" class="DataTable_Field">UV</th>
                <c:forEach items="${areaList}" var="area">
	            	<th align="center" class="DataTable_Field">PV</th>
	            	<th align="center" class="DataTable_Field">UV</th>
                </c:forEach>
            	<th align="center" class="DataTable_Field">PV</th>
            	<th align="center" class="DataTable_Field">UV</th>
            </tr>

            <c:forEach items="${dataList}" var="channel" varStatus="s">
	            <tr>
	                <td align="center">${s.count}</td>
	                <td>${channel.name}</td>
	                <td>${channel.code}</td>
	                <td><c:out value="${channel.OS_1}" default="0"/></td>
	                <td><c:out value="${channel.OS_2}" default="0"/></td>
	                <td><c:out value="${channel.OS_3}" default="0"/></td>
	                <td><c:out value="${channel.OS_}" default="0"/></td>
	                <td><c:out value="${channel.PV}" default="0"/></td>
	                <td><c:out value="${channel.UV}" default="0"/></td>
	                <c:forEach items="${areaList}" var="area">
	                	<c:set var="key">PV_${area}</c:set>
		                <td><c:out value="${channel[key]}" default="0"/></td>
	                	<c:set var="key">UV_${area}</c:set>
		                <td><c:out value="${channel[key]}" default="0"/></td>
	                </c:forEach>
	                <td><c:out value="${channel.PV_}" default="0"/></td>
	                <td><c:out value="${channel.UV_}" default="0"/></td>
	            </tr>
            </c:forEach>
		</table>
		
        <div class="diverror">友情提示：</div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        // 渠道管理
        $('#add').click(function () {
            top.Dialog.open({URL:"<%=path%>/business/apktrack.do?act=channelList",ID:"channel_list",Width:1024,Height:768,Title:"渠道列表"});
        });
    });
</script>
</body>
</html>
