<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.TbCouponCode.TbCouponCode"%>
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
                        listForm.action="TbCouponCode.do?act=realdelete";
                        listForm.submit();
                    }
            )
        }
    }
    function doDelsById(idVal) {
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        listForm.action="TbCouponCode.do?act=realdeleteById&idVal="+idVal;
                        listForm.submit();
                    }
            )
        }
    }
    //添加,生成券码
    function preAdd(couponId){
        if(couponId!=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponCode.do?act=preAdd&couponId="+couponId,ID:"a1",Width:1024,Height:260,Title:"生成券码"});
        }
    }
    //修改
    function preUpdate(j) {
        var idVal = isSel();
        if(idVal!=""){
            listForm.action="TbCouponCode.do?act=preUpdate"+idVal;
            listForm.submit();
        }
    }
    //详情
    function view() {
        var idVal = isSel();
        if(idVal!=""){
            listForm.action="TbCouponCode.do?act=view"+idVal;
            listForm.submit();
        }
    }
    //清空查询数据
    function qing(){
        document.getElementById("nameStr").value="";
    }

</script>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：优惠码管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/TbCouponCode.do?act=list" method="post">
        <input type="text" name="couponId" id="couponId" value="${couponId }" style="display: none"/>
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td><a href="javascript:;" onclick="preAdd('${couponId }')" title="添加"> <span
                            class="img_add"></span>
                    </a>
                        <a id="del_mfp"
                           href="javascript:;" onclick="doDels()" title="删除"> <span
                                class="img_delete"></span>
                        </a>
                        优惠码：<input type="text" name="nameStr" id="nameStr" value="${nameStr }"/>
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
                <th height="25"  align="center" class="DataTable_Field" title="优惠码 ">优惠码</th>
                <th height="25"  align="center" class="DataTable_Field" title="已使用次数">已使用次数</th>
                <th height="25"  align="center" class="DataTable_Field" title="生成时间">生成时间</th>
                <th>操作</th>
            </tr>


            <%
                int sn=lc.getIndex();
                List list=lc.getList();
                TbCouponCode po = null;
                for (int i = 0; i < list.size(); i++)
                {
                    po = (TbCouponCode)list.get(i);
            %>
            <tr  >
                <td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
                <td align="center"><%=++sn%></td>
                <td class="DataTable_Content" align="left" title="<%= po.getCode() %>"><%= po.getCode() %></td>
                <td class="DataTable_Content" align="left"><%= po.getUseNum() %></td>
                <td class="DataTable_Content" align="left"><%= po.getCreateTime() %></td>
                <td>
                    <a id="del_mfp"
                       href="javascript:;" onclick="doDelsById('<%=po.getId()%>')" title="删除"> <span
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
        <div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    </form>
</div>
</body>
</html>
