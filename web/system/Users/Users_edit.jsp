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
	<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	 <!--3.3框架必需end-->
	 <!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
	<!-- 地图信息样式，及引入js文件 -->
	<style type="text/css">
		#allmap{width:100%;}
		#l-map{ height:400px;width:100%;float:left;border-right:2px solid #bcbcbc;}
		#r-right{height:100%;width:25%;float:left;}
		#r-result{height:90%;width:100%;float:left;}
		</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	 <link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<!--缩略图样式-->
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
	<!-- 异步上传图片 -->
 <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left"> 
					<span>当前位置：编辑<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form action="business/Users.do?act=update" method="post"  target="frmright" onsubmit="return sub(this)">
			<html:text property="id" style="display:none"></html:text>  
			<html:text property="imel" style="display:none"></html:text> 
			<html:text property="imsi" style="display:none"></html:text> 
			<html:text property="token" style="display:none"></html:text> 
			<html:text property="createTime" style="display:none"></html:text> 
			<html:text property="growing" style="display:none"></html:text> 
			<html:text property="loginTime" style="display:none"></html:text> 
			<html:text property="code" style="display:none"></html:text> 
			<html:text property="userType" style="display:none"></html:text> 
			<html:text property="mobileState" style="display:none"></html:text> 
			<html:text property="icon" styleId="icon" style="width:250px;display:none" styleClass="validate[] iptClass"></html:text>     	
			
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							
							<%-- <tr>
								<td width="15%" align="right">
									会员头像：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<div style="float: left">
										<input type="button" value="上传并预览" onclick="fileClickpad()" />
									</div>
									<span class="star">* 图片尺寸：96 * 96</span>
									<div id="iconDiv" style="float: left;padding-right: 20px">
									  <c:if test="${UsersActionForm.icon != null && UsersActionForm.icon != '' }">
									   <a href="javascript:thumbImgsDivList('${UsersActionForm.icon }',0,'<%=path%>')" >
									  	<img src="<%=path%>/${UsersActionForm.icon }" style="width: 80px;"/>
									  </a>
								 	</c:if>
									</div>
								</td>
							</tr> --%>
							<tr>
								<td width="15%" align="right">
									用户名称：
								</td>
								<td width="35%" align="left">									 
									<html:text property="username" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									联系电话：
								</td>
								<td width="35%" align="left">									 
									<html:text property="mobile" style="width:250px" styleClass="validate[required,custom[mobilephone]] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									真实姓名：
								</td>
								<td width="35%" align="left">									 
									<html:text property="realname" style="width:250px" styleClass="validate[] iptClass"></html:text>     	
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									性别：
								</td>
								<td width="35%" align="left">									 
									<html:radio property="sex" value="0" >男</html:radio>     	
									<html:radio property="sex" value="1" >女</html:radio> 
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									支付宝账号：
								</td>
								<td width="35%" align="left">									 
									<html:text property="alipayId" style="width:250px" styleClass="validate[] iptClass"></html:text>     	
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									微信号：
								</td>
								<td width="35%" align="left">									 
									<html:text property="wx" style="width:250px" styleClass="validate[] iptClass"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									邮箱：
								</td>
								<td width="35%" align="left">									 
									<html:text property="email" style="width:250px" styleClass="validate[] iptClass" onblur="checkInput(this,'email','')"></html:text>     	
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									QQ：
								</td>
								<td width="35%" align="left">									 
									<html:text property="qq" style="width:250px" styleClass="validate[] iptClass" onblur="checkInput(this,'qq','')"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									状态 ：
								</td>
								<td width="35%" align="left">									 
									<html:radio property="isDisable" value="0" >正 常</html:radio>     	
									<html:radio property="isDisable" value="1" >禁 用</html:radio>
								</td>
								<td width="15%" align="right">
									IOS推送开关：
								</td>
								<td width="35%" align="left">									 
									<html:radio property="iosOff" value="NO" >推送</html:radio>     	
									<html:radio property="iosOff" value="OFF" >不推送</html:radio>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									四级是否激活 ：
								</td>
								<td width="35%" align="left">
									<html:radio property="fActivation" value="0" >否</html:radio>     	
									<html:radio property="fActivation" value="1" >是</html:radio>
								</td>
								<td width="15%" align="right">
									六级是否激活：
								</td>
								<td width="35%" align="left">									 
									<html:radio property="sActivation" value="0" >否</html:radio>     	
									<html:radio property="sActivation" value="1" >是</html:radio>
								</td>
							</tr>
							<tr>
								<td class="titleCn" width="15%" align="right">
									省/市/区县：
								</td>
								<td class="conCn" align="left" width="35%">
								<select name="province" id="select" class="default" onchange="getCity_Area('#select','#select1','city')" >
		               	 				<option value="">请选择省份</option>
		                 					${provinceList}
		               	 		</select>
		               	 		<select name="cityId" id="select1"  onchange="getCity_Area('#select1','#select2','area')" >
		               	 			<option value="">请选择</option>
		               	 			${cityList }
		               	 		</select>
		               			<select name="areaId" id="select2"  >
		               				<option value="">请选择</option>
		               				${citysList } 
		               			</select>
								</td>
								<td width="15%" align="right">
									邮编：
								</td>
								<td width="35%" align="left">									 
									<html:text property="zipcode" style="width:250px" styleClass="validate[] iptClass" onblur="checkInput(this,'email','')"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									详细地址：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<html:text property="address" style="width: 95%" styleClass="validate[] iptClass" maxlength="50"></html:text>     	
									<span class="star"></span>
								</td>
							</tr>
								<tr style="display: none">
								<td width="15%" align="right">
									经度：
								</td>
								<td width="35%" align="left">									 
									<html:text property="mapy" styleId="mapy" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									纬度：
								</td>
								<td width="35%" align="left">									 
									<html:text property="mapx" styleId="mapx" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
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
							  	<script type="text/javascript">
								var mapy = '${UsersActionForm.mapx}'==''||'${UsersActionForm.mapx}'=='0.0'?117.00772:'${UsersActionForm.mapx}';
								var mapx = '${UsersActionForm.mapy}'==''||'${UsersActionForm.mapy}'=='0.0'?36.672828:'${UsersActionForm.mapy}';
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
								</script>
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
							<input type="button" value=" 关闭 " onclick="back()"/> 
						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
  	</html:form>
		</div>
	<div style="display: none">
			<html:form action="/business/Users.do?act=updateImgPath" styleId="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="fileImage" styleId="fileIcon" onchange="ajaxFileIcon()"></html:file>
			</html:form>
		</div>
	<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
</body>
<script language="javascript" type="text/javascript">
    function flush(){
     top.frmright.window.location.reload();
    }
	function back(){
		top.Dialog.close();
	}

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
	  	
	  	  	/**
	验证 
	@param inp 要验证的输入框
	@param cls  要加入的class
  */
   function checkInput(inp,cls,len){
   	if(inp.value.length!=0){
	   	if(len==''){
	   		inp.className= "validate[custom["+cls+"]] iptClass";
	   	}else{
	   		inp.className= "validate[length[0,"+len+"],custom["+cls+"]] iptClass";
	   	}
	  }else{
	  	inp.className= "validate[] iptClass";
	  }
	}
	
	function cityfun(id,id1){
			var $proid = $(id).val();
			$.post(
				  "business/Employee.do?act=findCity",{id:$proid},
				  function (msg){
					$(id1).show();			  	
				  	$(id1).empty();
				  	if(msg.citylist==null){
				  		alert("无数据");
				  	}
				  	$(id1).append(msg.citylist);
				  },
			  	 "json"
			);
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
	//查询城市区县
	function getCity_Area(id,id1,type){
		var Fid = $(id).val();
		$.post("business/UserAddress.do?act=province_city_area_List",{Fid:Fid,type:type},
			  function (data){
				$(id1).show();			  	
			  	$(id1).empty();
			  	$(id1).append("<option value=''>请选择</option>");
			  	$(id1).append(data.dataList);
			  	$(id1).setValue("","");
			  },
		  	 "json"
		);
	}
	
</script>
</html>
