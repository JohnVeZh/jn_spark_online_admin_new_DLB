<%@page import="com.easecom.system.model.SysConfig"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.system.web.SysConfigForm"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/"/>
	<link href="<%=path%>/css/product_add.css" rel="stylesheet" type="text/css"/>
	<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>

	<!-- 异步上传 -->
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<!--框架必需end-->

	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：手机参数<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
				<form name="listForm" scope="request" action="<%=path%>/business/News.do?act=list" method="post">
				<div id="imgList">
				<%
				List<SysConfig> lc = (List<SysConfig>)request.getAttribute("lc");
				SysConfig po = null;
				for (int i = 0; i < lc.size(); i++) 
				{
					po = (SysConfig)lc.get(i);
				%>
				
				<div   style="width: 25%;float: left;">
					<div align="center">
				   		<img id="img<%= po.getId() %>" src="<%=path %>/<%= po.getValue() %>" style="width: 280px"/>
						<input type="hidden" id="text<%= po.getId() %>"/>
					</div>
						<div align="center" style="padding: 10px 10px 10px 10px">
							<%= po.getName() %>
							<input type="hidden" id="typevalue<%= po.getId() %>" style="width: 230px" value="<%= po.getType() %>"/><br/>
							<c:if test="${type!='cusConfig' }">
								<input type="hidden" id="nametext<%= po.getId() %>" style="width: 230px" value="<%= po.getName() %>"/>
							</c:if>
							<c:if test="${type=='cusConfig' }">
								名称：<input type="text" id="nametext<%= po.getId() %>" style="width: 230px" value="<%= po.getName() %>"/>
							</c:if>
						</div>
					<div align="center" style="padding-top:10px ">
					  <input type="button" value="修改图片并预览" onclick="fileClick('<%= po.getId() %>')"/>
					  <input type="button" value="使用图片" onclick="update('<%= po.getId() %>')"/>
					</div>
				</div>
				<%
					}
				%>
			 </div>
			 </form>
			 <div style="display: none">
				<input type="text" id="disId"/>
				<html:form action="System/SysConfig.do?act=updateImgPath" styleId="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<html:file property="file" styleId="file" onchange="ajaxFile()"></html:file>
				</html:form>
				
				<html:form action="System/SysConfig.do?act=updateImgPath" styleId="addformAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
					<input type="text" name="alias" id="alias" value="${type }"/>
					<html:file property="file" styleId="addfile" onchange="addajaxFile()"></html:file>
				</html:form>
			</div>
			 <br/><br/><br/><br/>
	</div>
	
	</body>
	<script type="text/javascript">
	  function fileClick(id){
  		$("#disId").val(id);
  		$("#file").click();
 	}
	
	function ajaxFile(){
	  var id = $("#disId").val();
	  if(id==""){
	  	return;
	  }
	  	var path = $("#file").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	 $('#formAction').ajaxSubmit(function(data){
	    if(data.result){
			$("#text"+id).val(data.imgPath);    
	    	$("#img"+id).attr("src","../"+data.imgPath);
	    	$("#file").val("");
	    }
	    });
	}
	
	//添加图片
	function addfileClick(){
  		$("#addfile").click();
 	}
	
	function addajaxFile(){
	
	  	var path = $("#addfile").val();
	  	var alias = $("#alias").val();
	  	var extStart = path.lastIndexOf(".");
        var ext = path.substring(extStart, path.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            alert("图片限于bmp,png,gif,jpeg,jpg格式");
            return;
		}
	  
	 $('#addformAction').ajaxSubmit(function(data){
	    if(data.result){
			$("#textadd").val(data.imgPath);
				var str = '<div style="width: 25%;float: left;padding:10px 10px 10px 10px">'
					+'<div align="center">'
				   	  +'<img id="img'+data.count+'"  src="<%=path %>/'+data.imgPath+'" style="width: 280px"/>'
				   	    +'<input type="hidden" id="text'+data.count+'"/>'
				    + '</div>'
				   	  +'<div align="center" style="padding: 10px 10px 10px 10px">';
				     if(alias == 'CAROUSEL_PAD' || alias == 'CAROUSEL_PHONE'){
						  	str = str+'图片链接：<input type="text" id="nametext'+data.count+'" style="width: 230px"/>';
						}else{
							str = str+'图片链接：<input type="hidden" id="nametext'+data.count+'" style="width: 230px"/>';
						}
						str = str + '</div>'
					  +'<div align="center" style="padding-top:10px ">'
					  +'<input type="button" value="修改图片并预览" onclick="fileClick('+data.count+')"/>'
					  +'<input type="button" value="使用图片" onclick="addImg(\''+data.imgPath+'\',\''+data.count+'\')"/>'
					+'</div>'
				+'</div>';
			$("#imgList").append(str);
			
	    }
	    });
	}
	
	//修改图片路径
	function update(id){
		var path = $("#text"+id).val();
		if(id== ""){
		 	return;
		}
		if(path == ""){
			return;
		}
		var nametext = $("#nametext"+id).val();
		var typevalue = $("#typevalue"+id).val();
	   $.post("SysConfig.do?act=AjaxUpdateImgPath", {id:id,path:path,nametext:nametext,typevalue:typevalue},function(data){
                if(data.result){
                   window.location.reload(true);
				}else{
				 alert("修改失败,请稍后再试!");
				}
			}); 
		}
		
	//添加图片
	function addImg(path,count){
			var alias = $("#alias").val();
			if(path == ""){
				return;
			}
			var nametext = $("#nametext"+count).val();
			var typevalue = $("#typevalue"+id).val();
		   $.post("SysConfig.do?act=addImg", {path:path,alias:alias,nametext:nametext,typevalue:typevalue},function(data){
	                if(data.result){
	                   window.location.reload(true);
					}else{
					 alert("修改失败,请稍后再试!");
					}
				}); 
		}
	//删除
	function doDelById(id){
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		 $.post("SysConfig.do?act=del", {id:id},function(data){
	               		 if(data.result){
	                  		 window.location.reload(true);
							}else{
							 alert("删除失败,请稍后再试!");
							}
						});
					}
				)
			}
		}
	
	</script>
</html>
