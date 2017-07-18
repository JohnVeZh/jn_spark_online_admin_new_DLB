<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.ProductOrderRefund.ProductOrderRefund"%>
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
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript">
    $(function () {
        //添加
        $('#add').click(function (e) {
            top.Dialog.open({URL:"<%=path%>/business/AppVersions.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});
        });
        //详情
        function view(id) {
            if(id!=""){
                top.Dialog.open({URL:"<%=path%>/business/AppVersions.do?act=view&id="+id,ID:"a2",Width:1024,Height:768,Title:"详情"});
            }
        }
    });

        function updateIosVersion() {
           	$.ajax({
           		url:"<%=path%>/business/AppVersions.do?act=updateIosVersion",
           		type:"POST",
           		data:{iosVersion:$("#iosVersion").val()},
			dataType:"json",
			success:function (data) {
				if(data) {
					top.Dialog.alert("更新成功！");
				} else {
					top.Dialog.alert("更新失败！");
				}
			}
        });
       }
</script>


<%
    ListContainer lc = (ListContainer) request.getAttribute("lc");
%>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：Apk版本更新<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/AppVersions.do?act=list" method="post">
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                    	<%-- 
                        <a href="javascript:;" onclick="add()" id="add"> <span class="icon_add">新增</span></a>
                    	 --%>
                    	<input id="iosVersion" value="${iosVersion}" />
                        <input type="button" onclick="javascript:updateIosVersion();" value="更新iOS版本" />
                    </td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <%--<th width="3%" height="25" align="center" class="DataTable_Field">--%>
                <%--</th>--%>
                <th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                <th height="25"  align="center" class="DataTable_Field">APP类型</th>
                <th height="25"  align="center" class="DataTable_Field">应用名称</th>
                <th height="25"  align="center" class="DataTable_Field">版本号</th>
                <th height="25"  align="center" class="DataTable_Field">版本名称</th>
                <th height="25"  align="center" class="DataTable_Field">文件大小</th>
                <th height="25"  align="center" class="DataTable_Field">更新时间</th>
                <th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>
            </tr>

            <c:forEach items="${lm }" var="apk" varStatus="s">
            <tr >
                <%--<td align="center"><input type="checkbox" name="id" value="${apk.id }" onclick="event.cancelBubble=true;"></td>--%>
                <td align="center">${s.index+1 }</td>
                <td class="DataTable_Content" align="left">
                	<c:if test="${apk.type == 0}">Android</c:if>
                	<c:if test="${apk.type == 1}">iOS</c:if>
                </td>
                <td class="DataTable_Content" align="left" title="${apk.apkName }">${apk.apkName }</td>
                <td class="DataTable_Content" align="left" title="${apk.apkVersion }">${apk.apkVersion }</td>
                <td class="DataTable_Content" align="left" title="${apk.versionName }">${apk.versionName }</td>
                <td class="DataTable_Content" align="left" title="${apk.fileSize }">${apk.fileSize }</td>
                <td class="DataTable_Content" align="left" title="${apk.updateTime }">${apk.updateTime }</td>
                <td align="left">
                    <a href="javascript:;" onclick="view('${apk.id}')"><span class="icon_view">查看</span>
                    </a>
                    <a href="javascript:;"
                       onclick="preUpdate('${apk.id}')"> <span class="icon_edit">修改</span>
                    </a>
                    <%-- 
                    <a id="del_mfp"
                       href="javascript:;" onclick="doDelsById('${apk.id}')"> <span
                            class="icon_delete">删除</span>
                    </a>
                     --%>
                </td>
            </tr>
            </c:forEach>

            <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
                <div class="center">
                    <div class="right">
                        <%@include file="../../include/listpage.jsp"%>
                    </div>
                </div>
            </div>
            <div class="diverror">友情提示: </br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    </form>
</div>
<script type="text/javascript">
    function view(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/AppVersions.do?act=view&id="+id,ID:"a2",Width:1024,Height:768,Title:"详情"});
        }
    }
    function doDelsById(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要删除吗?",
                function() {
                    listForm.action="AppVersions.do?act=realdeleteById&id="+id;
                    listForm.submit();
                }
            )
        }
    }
    //修改
    function preUpdate(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/AppVersions.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
        }
    }
</script>
</body>
</html>
