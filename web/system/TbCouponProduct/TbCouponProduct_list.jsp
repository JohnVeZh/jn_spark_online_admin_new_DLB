<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.PreferentialCodeProduct.PreferentialCodeProduct"%>
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
        var inps = document.getElementsByName('id');
        var idVal = "";
        for(var i = 0; i <inps.length; i++){
            if(inps[i].checked){
                if(idVal==""){
                    idVal = inps[i].value;
                }else{
                    idVal = idVal + "," + inps[i].value;
                }
            }
        }
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        $.post("<%=path%>/business/TbCouponProduct.do?act=realdelete",{idVal:idVal},function(data){
                            if(data.result){
                                window.location.reload();
                            }
                        });

                    }
            )
        }
    }
    function doDelsById(idVal) {
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        $.post("<%=path%>/business/TbCouponProduct.do?act=realdeleteById",{idVal:idVal},function(data){
                            if(data.result){
                                window.location.reload();
                            }
                        });
                    }
            )
        }
    }
    //添加
    function preAdd(){
        top.Dialog.open({URL:"<%=path%>/business/TbCouponProduct.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});
    }
    //修改
    function preUpdate(idVal) {
        if(idVal!=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponProduct.do?act=preUpdate&id="+idVal,ID:"a2",Width:1024,Height:768,Title:"编辑"});
        }
    }
    //详情
    function view() {
        var idVal = isSel();
        if(idVal!=""){
            listForm.action="PreferentialCodeProduct.do?act=view"+idVal;
            listForm.submit();
        }
    }
    //清空查询数据
    function qing(){
        document.getElementById("title").value="";
        document.getElementById("sel").value="";
    }

    //搭配图书
    function colloBook(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponProduct.do?act=p_list&pcId="+id,ID:'col1',Title:"配套图书",Width:1024,Height:768});
        }
    }
    //搭配网课
    function colloNetwork(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponProduct.do?act=net_list&pcId="+id,ID:'col1',Title:"配套图书",Width:1024,Height:768});
        }
    }

</script>
<body>
<div id="scrollContent">
    <%--<div class="position">--%>
        <%--<div class="center">--%>
            <%--<div class="left">--%>
                <%--<div class="right">--%>
                    <%--<span>当前位置：绑定商品<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <form name="listForm" id="productForm" scope="request" action="<%=path%>/business/TbCouponProduct.do?act=list" method="post">
        <div class="box2" panelTitle="功能面板" roller="false">
            <input type="hidden" id="pcId" name = "pcId" value="${pcId }"/>
            <table style="width:100%">
                <tr>
                    <td >
                        &nbsp;&nbsp;
                        <div style="float: left;"><select name="serchType" id="serchType" style="width:100px;line-height: 30px;">
                            <option value="all">全部</option>
                            <option value="2">图书</option>
                            <option value="1">网课</option>
                        </select>&nbsp;&nbsp;<input type="submit" value="查询" id="searchButton" />&nbsp;&nbsp;</div>
                        <div style="float: left;">
                            <a id="del_mfp"
                               href="javascript:;" onclick="doDels()" title="删除"> <span
                                    class="img_delete"></span>
                            </a>
                            <a href="javascript:;" onclick="colloBook('${pcId }')" title="搭配图书 "><span
                                    class="img_txt"></span>
                            </a>

                            <a href="javascript:;" onclick="colloNetwork('${pcId }')" title="搭配网课"> <span
                                    class="img_network"></span>
                            </a>
                        </div></td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field">
                </th>
                <th height="25"  width="50" align="center" class="DataTable_Field" title="序号">序号</th>
                <th height="25"  align="center" class="DataTable_Field" title="商品">商品</th>
                <th height="25" width="100" align="center" class="DataTable_Field" title="类型">类型</th>
                <th height="25" width="80" align="center" class="DataTable_Field" title="操作 ">操作</th>
            </tr>

            <c:forEach items="${lm }" var="l" varStatus="s">
                <tr  >
                    <td align="center"><input type="checkbox" name="id" id="id" value="${l.id }" onclick="event.cancelBubble=true;"></td>
                    <td align="center">${s.index+1 }</td>
                    <td class="DataTable_Content" align="left" >
                        <div class="textSlice" title="${l.pName }">${l.pName }</div>
                    </td>
                    <td class="DataTable_Content" align="left" >
                        <div class="textSlice" title="${l.typeName }">${l.typeName }</div>
                    </td>
                    <td align="left">
                        <a id="del_mfp"
                           href="javascript:;" onclick="doDelsById('${l.id }')" title="删除"> <span
                                class="img_delete"></span>
                        </a>
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
