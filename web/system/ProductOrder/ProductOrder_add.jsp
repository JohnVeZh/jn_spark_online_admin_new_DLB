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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
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
				<div class="right">
					<span>当前位置：product_order_add<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/ProductOrder.do?act=add" method="post" id="listForm" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>product_order基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
								<tr>
								<td width="15%">
									用户是否删除：
								</td>
								<td width="35%">
									<input type="text" name="userDel" class="validate[required] iptClass" />
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									邮编：
								</td>
								<td width="35%">
									<input type="text" name="zipcode" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									创建时间：
								</td>
								<td width="35%">
									<input type="text" name="createtime" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:50</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									唯一标识：
								</td>
								<td width="35%">
									<input type="text" name="id" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									城市：
								</td>
								<td width="35%">
									<input type="text" name="cityId" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									收货人：
								</td>
								<td width="35%">
									<input type="text" name="consignee" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:50</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									订单状态：
								</td>
								<td width="35%">
									<input type="text" name="orderState" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:50</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									详细地址：
								</td>
								<td width="35%">
									<input type="text" name="address" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:500</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									商品兑换积分：
								</td>
								<td width="35%">
									<input type="text" name="exchangeintegral" class="validate[required] iptClass" />
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									收货人电话：
								</td>
								<td width="35%">
									<input type="text" name="telephone" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:50</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									管理员是否删除：
								</td>
								<td width="35%">
									<input type="text" name="adminDel" class="validate[required] iptClass" />
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									商品id：
								</td>
								<td width="35%">
									<input type="text" name="productId" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									省份：
								</td>
								<td width="35%">
									<input type="text" name="provinceId" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									原价：
								</td>
								<td width="35%">
									<input type="text" name="price" class="validate[required] iptClass" />
									<span class="star">* 类型：double 长度:22</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									区县：
								</td>
								<td width="35%">
									<input type="text" name="areaId" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									备注：
								</td>
								<td width="35%">
									<input type="text" name="remark" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:500</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									物流单号：
								</td>
								<td width="35%">
									<input type="text" name="logisticscode" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:50</span>
								</td>
							</tr>
								<tr>
								<td width="15%">
									用户id：
								</td>
								<td width="35%">
									<input type="text" name="userId" class="validate[required] iptClass" />
									<span class="star">* 类型：String 长度:32</span>
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
							<input type="submit" value=" 提 交 "/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 返 回 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
		</form>
		</div>
</body>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		//document.getElementById('listForm').action="business/News.do?act=list";
		//document.getElementById('listForm').submit();
		history.back();
	}
	
	</script>
	  $(function(){
	  		var msg='';//jstl的标签 $，{，msg}
	  		if(msg==200){
	  			top.Dialog.alert("添加成功。");
	  		}else if(msg==201){
	  			top.Dialog.alert("添加失败。");
	  		}else if(msg==202){
	  			top.Dialog.alert("添加失败!");
	  		}
	  	});


</script>


</html>
