<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri ="/struts-tags"%>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.PreferentialCode.PreferentialCode"%>
<%@ page import="com.business.TbCoupon.TbCoupon" %>
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
                        listForm.action="TbCoupon.do?act=realdelete"+idVal;
                        listForm.submit();
                    }
            )
        }else{
        top.Dialog.alert("请至少选中一条数据！");
        }
    }
    function doDelsById(id) {
        if(id!=""){
            top.Dialog.confirm("您确信要删除吗?",
                    function() {
                        listForm.action="TbCoupon.do?act=realdelete&id="+id;
                        listForm.submit();
                    }
            )
        }
    }

    //添加
    function preAdd(){
        top.Dialog.open({URL:"<%=path%>/business/TbCoupon.do?act=preAdd",ID:'a11',Width:1024,Height:420,Title:'新增'});
//		    listForm.action="TbCoupon.do?act=preAdd"
//			listForm.submit();
    }
    //修改
    function preUpdate(id) {
        if(id !=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCoupon.do?act=preUpdate&id="+id,ID:'a12',Width:1024,Height:420,Title:'修改'});
        }
    }
    //详情
    function view(id) {
        if(id !=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCoupon.do?act=view&id="+id,ID:'a13',Width:1024,Height:768,Title:'查看'});
        }
    }
    //商品绑定
    function priPro(id,ralationType) {
        if(ralationType > 1){
            top.Dialog.alert("全场类无需绑定！");
            return;
        }
        if(id !=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponProduct.do?act=list&pcId="+id,ID:'a14',Width:1024,Height:768,Title:'商品绑定'});
        }
    }
    //券码管理
    function pcGenerate(id) {
        if(id !=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponCode.do?act=list&couponId="+id,ID:'a15',Width:1024,Height:768,Title:'券码管理'});
        }
    }
    //使用记录
    function preUse(id) {
        if(id !=""){
            top.Dialog.open({URL:"<%=path%>/business/TbCouponRecord.do?act=list&couponId="+id,ID:'a15',Width:1024,Height:768,Title:'使用记录'});
        }
    }


    //清空查询数据
    function qing(){
        document.getElementById("title").value="";
        document.getElementById("sel").value="";
    }
    $(function(){
        top.Dialog.close();
    })

    function exportCoupon(){
        $("#netWorkOrBook").val("图书");
        $("#exportEX").click();
    }
    function exportExcel(){
        $("#name1").val($("#title").val());
        $("#CouponType1").val($("#CouponType").val());
        $("#CouponStatus1").val($("#CouponStatus").val());
        exportForm.action = "TbCoupon.do?act=exportExcel";
        exportForm.submit();
    }
</script>
<body>
<form name="exportForm" style="display: none;" scope="request" action="<%=path%>/business/TbCoupon.do?act=exportExcel" method="post">
    <input type="hidden" name="CouponType1"  id="CouponType1"  />
    <input type="hidden" name="CouponStatus1"  id="CouponStatus1"  />
    <input type="hidden" id="name1" name="name1"  />
    <input type="submit" id="exportEX" value="导出excel" onclick="exportExcel()"/>&nbsp;&nbsp;
</form>
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
    <form name="listForm" scope="request" action="<%=path%>/business/TbCoupon.do?act=list" method="post">
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <div style="float: left">
                        标题：<input type="text" name="name" id="title" value="${name }"/>&nbsp;&nbsp;
                            </div>
                        <div style="float: left">
                        类型：
                            </div>
                        <div style="float: left">
                            <select name="CouponType" id="CouponType" >
                                <option value="all">全部</option>
                                    <option value="1" <c:if test="${CouponType == '1'}">selected="selected"</c:if> >折扣卷</option>
                                    <option value="2"  <c:if test="${CouponType == '2'}">selected="selected"</c:if>>现金券</option>
                        </select>&nbsp;&nbsp;
                            </div>
                            <div style="float: left">
                        状态：</div>
                        <div style="float: left">
                                <select name="CouponStatus" id="CouponStatus" >
                            <option value="all">全部</option>
                                <option value="0" <c:if test="${CouponStatus == '0'}">selected="selected"</c:if> >启用</option>
                                <option value="1" <c:if test="${CouponStatus == '1'}">selected="selected"</c:if>>停用</option>
                        </select>&nbsp;&nbsp;
                            </div>
                        <div style="float: left">
                        <input type="submit" value="查询" />&nbsp;&nbsp;
                            <input type="button" onclick="preAdd('')" title="新增" value="新增" />&nbsp;&nbsp;
                            <input type="button" onclick="doDels()" title="批量删除" value="批量删除" />&nbsp;&nbsp;
                        <input type="button" value="导出excel" onclick="exportCoupon()" />
                        </div>

                    </td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <th width="3%" height="25" align="center" class="DataTable_Field">
                </th>
                <th width="5%">序号</th>
                <th height="25"  align="center" class="DataTable_Field"  title="">标题</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 50px;" title="">类型</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 50px;" title="">状态</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 100px;" title="">开始时间</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 100px;" title="">结束时间</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 80px;" title="">可使用次数</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 50px;" title="">满额</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 50px;" title="">减额</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 50px;" title="">折扣率</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 80px;" title="">关联类型</th>
                <th height="25"  align="center" class="DataTable_Field" style="width: 160px;" title="">创建时间</th>
                <th style="width: 160px;">操作</th>
            </tr>


            <%
                int sn=lc.getIndex();
                List list=lc.getList();
                TbCoupon tc = null;
                for (int i = 0; i < list.size(); i++)
                {
                    tc = (TbCoupon)list.get(i);
            %>
            <tr  >
                <td align="center"><input type="checkbox" name="id" value="<%=tc.getId()%>" onclick="event.cancelBubble=true;"></td>
                <td align="center"><%=++sn%></td>
                <td class="DataTable_Content" align="left" title="<%= tc.getTitle() %>"><%= tc.getTitle() %></td>
                <td class="DataTable_Content" align="left"><% if(tc.getType()==1)out.print("折扣券");else out.print("现金券"); %></td>
                <td class="DataTable_Content" align="left"><% if(tc.getStatus()==0)out.print("启用");else out.print("停用"); %></td>
                <td class="DataTable_Content" align="left" title="<%= tc.getStartTime().substring(0,10) %>"><%= tc.getStartTime().substring(0,10) %></td>
                <td class="DataTable_Content" align="left" title="<%= tc.getEndTime().substring(0,10) %>"><%= tc.getEndTime().substring(0,10) %></td>
                <td class="DataTable_Content" align="left" ><% if(tc.getMaxUseNum() == -1) out.print("无限制"); else out.print(tc.getMaxUseNum()); %></td>
                <td class="DataTable_Content" align="left" ><% if(tc.getMinMoney()==null)out.print("");else out.print(tc.getMinMoney()); %></td>
                <td class="DataTable_Content" align="left" ><% if(tc.getDiscountMoney()==null)out.print("");else out.print(tc.getDiscountMoney()); %></td>
                <td class="DataTable_Content" align="left" ><% if(tc.getDiscountRate()==null)out.print("");else out.print(tc.getDiscountRate());%></td>
                <td class="DataTable_Content" align="left" ><% if(tc.getRelationType()==1) out.print("非全场");if(tc.getRelationType()==2) out.print("全场网课");if(tc.getRelationType()==3) out.print("全场图书");if(tc.getRelationType()==4) out.print("全场通用"); %></td>
                <td class="DataTable_Content" align="left" title="<%= tc.getCreateTime() %>"><%= tc.getCreateTime() %></td>
                <td>
                    <a href="javascript:;" title="修改"
                       onclick="preUpdate('<%=tc.getId()%>')"> <span class="img_edit"></span>
                    </a>
                    <a id="building" href="javascript:;" title="商品绑定"
                       onclick="priPro('<%=tc.getId()%>','<%=tc.getRelationType()%>')"> <span class="img_mark"></span>
                    </a>
                    <a href="javascript:;" title="券码管理"
                       onclick="pcGenerate('<%=tc.getId()%>')"> <span class="img_pc"></span>
                    </a>
                    <a href="javascript:;" title="使用记录"
                       onclick="preUse('<%=tc.getId()%>')"> <span class="img_list"></span>
                    </a>
                    <a id="del_mfp" title="删除"
                       href="javascript:;" onclick="doDelsById('<%=tc.getId()%>')"> <span
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
