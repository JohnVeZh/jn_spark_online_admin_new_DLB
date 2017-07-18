<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Users.UsersActionForm"%>
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
	
	<!-- 异步上传图片 -->
 <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	
	<!-- 地图信息样式，及引入js文件 -->
	<style type="text/css">
		#allmap{width:100%;}
		#l-map{ height:400px;width:100%;float:left;border-right:2px solid #bcbcbc;}
		#r-right{height:100%;width:25%;float:left;}
		#r-result{height:90%;width:100%;float:left;}
		</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/Users.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							
							<!-- <tr style="">
								<td width="15%" align="right">
									会员头像：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" style="display: none" name="icon" id="icon"  class="validate[] iptClass" />
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickpad()" />
									</div>
									<span class="star">* 图片尺寸：96 * 96 </span>
									<div id="iconDiv" style="float: left;padding-right: 20px"></div>
								</td>
							</tr> -->
								<tr>
								<td width="15%" align="right">
									用户名称：
								</td>
								<td width="35%" align="left">
									<input type="text" name="username" class="validate[required] iptClass" />
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									联系电话：
								</td>
								<td width="35%" align="left">
									<input type="text" id="mobile" name="mobile" class="validate[required,custom[mobilephone]] iptClass" onblur="checkPhone()"/>
									<span class="star" id="mobileStar">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									真实姓名：
								</td>
								<td width="35%" align="left">
									<input type="text" name="realname" class="validate[] iptClass" />
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									性别：
								</td>
								<td width="35%" align="left">
									<input type="radio" class="radio" checked="checked" id="sex" name="sex" value="0" />
										<label for="radio-1">男</label>
										<input type="radio" class="radio"  id="sex" name="sex" value="1"/>
										<label for="radio-2">女</label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									支付宝账号：
								</td>
								<td width="35%" align="left">
									<input type="text" name="alipayId" class="validate[] iptClass" />
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									微信账号：
								</td>
								<td width="35%" align="left">
									<input type="text" name="wx" class="validate[] iptClass" />
									<span class="star"></span>
								</td>
							</tr>
							
							<tr>
								<td width="15%" align="right">
									邮箱：
								</td>
								<td width="35%" align="left">
									<input type="text" name="email" class="validate[] iptClass" onblur="checkInput(this,'email','')"/>
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									QQ：
								</td>
								<td width="35%" align="left">
									<input type="text" name="qq" class="validate[] iptClass" onblur="checkInput(this,'qq','')"/>
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td  class="titleCn" width="15%" align="right">
									省/市/区县：
								</td>
								<td class="conCn" width="35%" align="left">
									<select  name="provinceId" id="select" onchange="getCity_Area('#select','#select1','city')">
               	 						<option value="">请选择省份</option>
               							${provinceList}
               	 					</select>
               	 					<select name="cityId" id="select1"  onchange="getCity_Area('#select1','#select2','area')" >
               	 					  	<option value="">请选择</option>
               	 					</select>
               						<select name="areaId" id="select2"  onchange="addstr()" >
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
									纬度：
								</td>
								<td width="35%" align="left">
									<input type="text" id="mapx" name="mapx" class="validate[required] iptClass" value="36.672828"/>
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									经度：
								</td>
								<td width="35%" align="left">
									<input type="text" id="mapy" name="mapy" class="validate[required] iptClass" value="117.00772"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr style="height: 60px">
							<td class="titleTd" align="right">
								地图定位：<br />拖动红色标点，更换位置。<br />
							</td>
							<td class="contentTd" colspan="3">
								<div id="allmap">
								<div id="l-map"></div>
								<div id="r-result">
								城市名: <input id="cityName" type="text" style="width:100px; margin-right:10px;" />
									<input type="button" value="查询" onclick="theLocation()" />
								</div>
							  	</div>
								<%@include file="../../include/map.jsp" %>
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
							<input type="reset" value="关闭" onclick="back()" />
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
		
		<div style="display: none">
				<html:form action="/business/Users.do?act=updateImgPath" styleId="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="fileImage" styleId="fileIcon" onchange="ajaxFileIcon()"></html:file>
				</html:form>
			</div>
</body>
<script language="javascript" type="text/javascript">
 
    function flush(){
      top.frmright.window.location.reload();
    }
	function back(){
		top.Dialog.close();
	}
	
	

	/**
	验证 
	@param inp 要验证的输入框
	@param cls  要加入的class
  */
   function checkInput(inp,cls,len){
   	if(inp.value.length!=0){
	   	if(len==''){
	   		inp.className= "validate[required,custom["+cls+"]] iptClass";
	   	}else{
	   		inp.className= "validate[required,length[0,"+len+"],custom["+cls+"]] iptClass";
	   	}
	  }else{
	  	inp.className= "validate[] iptClass";
	  }
	}


	function fileClickpad(){
  		 	$("#fileIcon").click();
 		}
 		
	function ajaxFileIcon(){
	  	var path = $("#fileIcon").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	  
	 $('#formAction').ajaxSubmit(function(data){
	    if(data.result){
			$("#icon").val(data.imgPath); 
	    	$("#iconDiv").html("<img src='"+data.imgPath+"' style='width: 80px;'/>");
	    	$("#fileIcon").val("");
	    }
	    });
	}
	
	/*加载地图*/
		var mapx = '117.00772';
		var mapy = '36.672828';
		var zoom = 13;
		
		function map_callback(mapy, mapx, zoom){
			 document.getElementById('mapx').value=mapx ;
		 	 document.getElementById('mapy').value=mapy ;
		}
		function theLocation(){
			var city = document.getElementById("cityName").value;
			if(city != ""){
				map.centerAndZoom(city,11);      // 用城市名设置地图中心点
			}
		}
		
		//判断是否已经注册
	 function checkPhone(){
		 var mobile = $("#mobile").val();
		$.post("business/Users.do?act=checkPhone",{mobile:mobile},
			  function (data){
			   if(data.result=='false'){
			  	 $("#mobile").val("");
			      $("#mobileStar").text("该号码已注册！请另选号码。");
			   }else{
			    	$("#mobileStar").text("*");
			   }
			  },
		  	 "json"
		);
	}
	//查询城市区县
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
	}
  	
</script>


</html>
