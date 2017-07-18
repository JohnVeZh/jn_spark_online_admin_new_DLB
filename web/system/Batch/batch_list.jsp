<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@ page import="com.easecom.system.model.SysConfig" %>
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
                        listForm.action="Batch.do?act=realdelete"+idVal;
                        listForm.submit();
                    }
            )
        }
    }
    function doDelsById(idVal,name) {
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        listForm.action="Batch.do?act=realdeleteById&id="+idVal+"&name="+name;
                        listForm.submit();
                    }
            )
        }
    }
    //添加,生成券码
    function preAdd(){
            top.Dialog.open({URL:"/system/Batch/batch_add.jsp",ID:"a1",Width:330,Height:260,Title:"添加批次"});
    }
    //修改
    function update(id,name) {
        top.Dialog.open({URL:"/system/Batch/batch_edit.jsp?id="+id+"&name="+encodeURIComponent(encodeURIComponent(name))  ,ID:"a1",Width:330,Height:260,Title:"修改批次"});
    }
    //详情
    function view() {
        var idVal = isSel();
        if(idVal!=""){
            listForm.action="Batch.do?act=view"+idVal;
            listForm.submit();
        }
    }
    //清空查询数据
    function qing(){
        document.getElementById("nameStr").value="";
    }
    //验证该批次是否已经存在
    function checkIsUsed(id,name,deleteOrUpdate) {
        $("#scrollContent").mask("验证该批次是否可修改..");
        $.ajax({
            type: 'POST',
            url: '/business/Batch.do?act=isUsed',
            data: {'name':name,'id':id},
            dataType:"json",
            success: function (data) {
                $("#scrollContent").unmask();
                var data = eval(data);
            if(data.RES == 1){
                var tip = "该批次图书中已使用，不能进行修改删除操作！";
                top.Dialog.alert(tip);
                return;
            }else  if(data.RES == 2){
                var tip = "该批次四级激活码中已使用，不能进行修改删除操作！";
                top.Dialog.alert(tip);
                return;
            }else  if(data.RES == 3){
                var tip = "该批次六级激活码中已使用，不能进行修改删除操作！";
                top.Dialog.alert(tip);
                return;
            }else if(data.RES < 0){
                var tip = "程序异常，请稍后再试！";
                top.Dialog.alert(tip);
                return;
            }
            if(deleteOrUpdate == 'delete'){
                doDelsById(id,name);
            }else{
                update(id,name);
            }
            },
            error:function () {
                $("#scrollContent").unmask();
                top.Dialog.alert("网络异常，请稍后再试！");
            }

        });
    }
</script>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：批次管理 </span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/Batch.do?act=list" method="post">
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td><a href="javascript:;" onclick="preAdd()" title="添加"> <span
                            class="img_add"></span>
                    </a>
                        <a title="删除"
                           href="javascript:;" onclick="doDels()"> <span
                                class="img_delete"></span>
                        </a>
                        <input type="text" name="searchName" id="searchName"  />
                        <input type="submit" value="查询" />&nbsp;&nbsp; <input
                                type="button" value="清空" onclick="qing()" /></td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field">
                </th>
                <th width="5%" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                <th height="25"  align="center" class="DataTable_Field" title="批次名称 ">批次名称</th>
                <th>操作</th>
            </tr>


            <%
                int sn=lc.getIndex();
                List list=lc.getList();
                SysConfig sc = null;
                for (int i = 0; i < list.size(); i++)
                {
                    sc = (SysConfig)list.get(i);
            %>
            <tr  >
                <td align="center"><input type="checkbox" name="id" value="<%=sc.getId()%>" onclick="event.cancelBubble=true;"></td>
                <td align="center"><%=++sn%></td>
                <td class="DataTable_Content" align="left" title="<%= sc.getName()%>"><%= sc.getName() %></td>
                <td>
                    <a id="edit_mfp"
                       href="javascript:;" onclick="checkIsUsed('<%=sc.getId()%>','<%= sc.getName()%>','update')" title="修改"> <span
                            class="img_edit"></span>
                    </a>
                    <a id="del_mfp"
                       href="javascript:;" onclick="checkIsUsed('<%=sc.getId()%>','<%= sc.getName()%>','delete')" title="删除"> <span
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
        <div class="diverror">友情提示:</div>
    </form>
</div>
</body>
</html>
