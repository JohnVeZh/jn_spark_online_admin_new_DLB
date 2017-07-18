<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.TbCouponRecord.TbCouponRecord"%>
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
                        listForm.action="TbCouponRecord.do?act=realdelete"+idVal;
                        listForm.submit();
                    }
            )
        }
    }
    function doDelsById(idVal) {
        if(idVal!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        listForm.action="TbCouponRecord.do?act=realdeleteById&idVal="+idVal;
                        listForm.submit();
                    }
            )
        }
    }
    //添加
    function preAdd(){

        top.Dialog.open({URL:"<%=path%>/business/TbCouponRecord.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});

    }
    //修改
    function preUpdate(idVal) {
        if(idVal!=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponRecord.do?act=preUpdate&id="+idVal,ID:"a2",Width:1024,Height:768,Title:"编辑"});
        }
    }
    //详情
    function view(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/ProductOrder.do?act=view&id="+id,ID:"a1",Width:1024,Height:768,Title:"订单查看"});
        }
    }
    //清空查询数据
    function qing(){
        document.getElementById("title").value="";
        document.getElementById("sel").value="";
    }

</script>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/TbCouponRecord.do?act=list" method="post">
        <input type="text" name="couponId" value="${couponId }" style="display: none"/>
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <!-- <a href="javascript:;" onclick="preAdd('')" title="新增"> <span
                                class="img_add" ></span>
                        </a> -->
                        <a id="del_mfp"
                           href="javascript:;" onclick="doDels()" title="删除"> <span
                                class="img_delete"></span>
                        </a>
                        优惠码：<input type="text" name="nameStr" value="${nameStr }"/>
                        <input type="submit" value="查询" />&nbsp;&nbsp; <input
                            type="button" value="清空" onclick="qing()" /></td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field">
                </th>
                <th height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
                <th height="25"  align="center" class="DataTable_Field" title="优惠码">优惠码</th>
                <th height="25"  align="center" class="DataTable_Field" title="使用账户">会员名称</th>
                <th height="25"  align="center" class="DataTable_Field" title="优惠金额">优惠金额</th>
                <th height="25"  align="center" class="DataTable_Field" title="使用时间">创建时间</th>
                <th height="25"  align="center" class="DataTable_Field" title="订单号">订单号</th>
                <th height="25"  align="center" class="DataTable_Field" title="操作 ">操作</th>
            </tr>


            <%
                int sn=lc.getIndex();
                List list=lc.getList();
                TbCouponRecord po = null;
                for (int i = 0; i < list.size(); i++)
                {
                    po = (TbCouponRecord)list.get(i);
            %>
            <tr  >
                <td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
                <td align="center"><%=++sn%></td>
                <td class="DataTable_Content" align="left" >
                    <div class="textSlice" title="<%= po.getCouponCode() %>"><%= po.getCouponCode() %></div>
                </td>
                <td class="DataTable_Content" align="left" >
                    <div class="textSlice" title="<%= Tool.getValue("select username from users where id='"+po.getUserId()+"'") %>"><%= Tool.getValue("select username from users where id='"+po.getUserId()+"'") %></div>
                </td>
                <td class="DataTable_Content" align="left" >
                    <div class="textSlice" title="<%= po.getDiscountMoney() %>"><%= po.getDiscountMoney() %></div>
                </td>
                <td class="DataTable_Content" align="left" >
                    <div class="textSlice" title="<%= po.getUseTime() %>"><%= po.getUseTime() %></div>
                </td>
                <td class="DataTable_Content" align="left" >
                    <div class="textSlice" title="<%= Tool.getValue("select order_code from product_order where id='"+po.getOrderId()+"'") %>"><%= Tool.getValue("select order_code from product_order where id='"+po.getOrderId()+"'") %></div>
                </td>
                <td align="left">
                    <a id="del_mfp"
                       href="javascript:;" onclick="doDelsById('<%=po.getId()%>')" title="删除"> <span
                            class="img_delete"></span>
                    </a>
                    <a id="del_mfp"
                       href="javascript:;" onclick=" view('<%=po.getOrderId()%>')" title="订单详情"> <span
                            class="img_view"></span>
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
