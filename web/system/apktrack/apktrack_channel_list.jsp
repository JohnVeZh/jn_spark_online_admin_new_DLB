<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri ="/struts-tags"%>
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
	<script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
<!--3.3框架必需end-->

<script type="text/javascript">
    // 获取选中的数据
    function getSelectedIds() {
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
        if (j < 1) {
            top.Dialog.alert("请至少选中一条数据！");
            return "";
        }else{
            return idVal;
        }
    }

    // 批量删除
    function batchDelete() {
        var idVal = getSelectedIds();
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                 function() {
                   	$.ajax({
                   		url		: "<%=path%>/business/apktrack.do?act=channelDelete"+idVal,
                   		type	: "GET",
						dataType: "json",
						success: function (data) {
							if(data.result) {
					   			reload("apktrack.do?act=channelList");
							}
						},
						error: function (msg) {
							
						}
                   	});	// end ajax
                 }
            )
        }
    }
    
    // 删除
    function deleteById(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要删除吗?",
                 function() {
                   	$.ajax({
                   		url		: "<%=path%>/business/apktrack.do?act=channelDelete&id="+id,
                   		type	: "GET",
						dataType: "json",
						success: function (data) {
							if(data.result) {
					   			reload("apktrack.do?act=channelList");
							}
						},
						error: function (msg) {
							
						}
                   	});	// end ajax
                 }
            )
        }
    }

    // 新增
    function preAdd(){
        top.Dialog.open({URL:"<%=path%>/business/apktrack.do?act=channelPreAdd",ID:'channel_pre_add',Width:1024,Height:420,Title:'新增渠道'});
    }
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
    <form name="listForm" scope="request" action="<%=path%>/business/apktrack.do?act=channelList" method="post">
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <div style="float: left">
                            <input type="button" onclick="preAdd()" title="新增" value="新增" />&nbsp;&nbsp;
                            <input type="button" onclick="batchDelete()" title="批量删除" value="批量删除" />&nbsp;&nbsp;
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field"></th>
                <th style="width:40px">序号</th>
                <th height="25" align="center" class="DataTable_Field">渠道名称</th>
                <th height="25" align="center" class="DataTable_Field" style="width:180px">渠道代码</th>
                <th style="width: 120px;">操作</th>
            </tr>
			<c:forEach items="${channelList}" var="item" varStatus="stat">
				<tr>
					<td align="center"><input type="checkbox" name="id" value="${item.ID}" onclick="event.cancelBubble=true;"></td>
					<td align="center">${stat.count}</td>
	                <td class="DataTable_Content" align="left" title="${item.NAME}">${item.NAME}</td>
	                <td class="DataTable_Content" align="left" title="${item.VALUE}">${item.VALUE}</td>
	                <td>
	                    <a title="删除" href="javascript:void(0);" onclick="deleteById('${item.ID}')">
	                    	<span class="img_delete"></span>
	                    </a>
	                </td>
				</tr>
			</c:forEach>
        </table>
        <div class="diverror">友情提示:</br></div>
    </form>
</div>
</body>
</html>
