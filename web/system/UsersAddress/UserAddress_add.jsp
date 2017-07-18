<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--3.3框架必需start-->
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	 <!--3.3框架必需end-->
	 <!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：添加用户收货地址<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/UserAddress.do?act=add" method="post" id="listForm">
		<input type="text" style="display: none" name="userId" value="${userId }"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>用户地址基本信息</legend> 
						<table class="tableStyle"  align="center">
							<tr>
								<td width="15%" align="right" >
									收货人姓名：
								</td>
								<td width="35%" align="left">
									<input type="text" name="realname" class="validate[required] iptClass" />
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									电话：
								</td>
								<td width="35%" align="left">
									<input type="text" name="phone" class="validate[required,custom[mobilephone]] iptClass" />
									<span class="star">*</span>
								</td>
							</tr>
								
							<tr>
								<td  class="titleCn" width="15%" align="right">
									省/市/区县：
								</td>
								<td class="conCn" width="35%" align="left">
									<select  name="provinceId" id="select" class="default" onchange="getCity_Area('#select','#select1','city')">
               	 						<option value="">请选择省份</option>
               							${provinceList}
               	 					</select>
               	 					<select name="cityId" id="select1" class="default" onchange="getCity_Area('#select1','#select2','area')" >
               	 						<option value="">请选择</option>
               	 					</select>
               						<select name="areaId" id="select2" class="default" style="display: none;" onchange="addstr()" >
               							<option value="">请选择</option>
               						 </select>
               						<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									邮编：
								</td>
								<td width="35%" align="left">
									<input type="text" name="zipcode" class="validate[required,custom[zipcode]] iptClass" />
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									详细地址：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" style="width: 98%" name="detailAddress" class="validate[required] iptClass" />
									<span class="star">*</span>
								</td>
							</tr>
							<tr style="display: none">
							  <td width="15%" align="right">
									省市区全名：
								</td>
								<td width="35%" align="left">
									<input type="text" style="width: 98%"  name="districtCn" id="districtCn" class="validate[required] iptClass" />
									<span class="star">* </span>
								</td>
							</tr>
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="submit" value=" 提 交 " onclick="flush()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
		</form>
		</div>
</body>
<script language="javascript" type="text/javascript">
	 function flush(){
	 	
       top.frmright.window.location.reload();
    }
	function back(){
		top.Dialog.close();
	}


	function getCity_Area(id,id1,type){
		var Fid = $(id).val();
		$.post("business/UserAddress.do?act=province_city_area_List",{Fid:Fid,type:type},
			  function (data){
				$(id1).show();			  	
			  	$(id1).empty();
			  	$(id1).append("<option value=''>请选择</option>");
			  	$(id1).append(data.dataList);
			  	$(id1).setValue();
			  },
		  	 "json"
		);
		addstr();
	}	

function addstr(){
    var str= $("#select").find("option:selected").text();
    var str1= $("#select1").find("option:selected").text()
    var str2= $("#select2").find("option:selected").text();
   	$("#districtCn").val(str+str1+str2);
  
}
</script>


</html>
