<%@ page language="java" pageEncoding="utf-8"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String myerr = (String)request.getAttribute("myerrors");
myerr = (null == myerr)?"":myerr; 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>星火在线超级后台入口<%=path %></title>
<link href="<%=path %>/login_user/skin_flat/style.css" rel="stylesheet" type="text/css" id="skin"/>
<script type="text/javascript" src="<%=path %>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/libs/js/login.js"></script>

<!--居中显示start-->
<script type="text/javascript" src="<%=path %>/libs/js/method/center-plugin.js"></script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/
.white{
	color:#ffffff;
}
.white a{
	color:#ffffff;
	text-decoration:underline;
}
.white a:hover{
	color:#ffffff;
	text-decoration:underline;
}
</style>
</head>
<body >
	<div class="login_main">
		<div class="login_top">
		</div>
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<form id="loginForm" action="<%=path %>/login.do?act=login" class="login_form" method="post">
					<div class="login_user"><input type="text" id="username" name="username"></div>
					<div class="login_pass"><input type="password" id="password"  name="password" ></div>
					<div class="clear"></div>
					<div class="login_button">
						<div class="login_button_left"><input type="submit" onclick="javascript:return login()"  value="" /></div>
						<div class="login_button_right"><input type="reset" value=""/><input type="hidden" name="state" value="4"/></div>
						<div class="clear"></div>
					</div>
					</form>
					<div class="login_info" style="display:none;">111</div>
					<div class="login_info2"><%=myerr%><!-- 测试用户名：guest，密码：123456 --></div>
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">
			Copyright © 2016 All Rights Reserved 山东星火国际传媒集团有限公司  京ICP证070017号 - 京ICP备08105196号<br/><br/>
			统一入口：<span class="white"><a href="<%=path %>/" ><%=path %>/</a>&nbsp;&nbsp;&nbsp;&nbsp;官方网站：<span class="white"><a href="http://www.ispark.com.cn" target="_blank">http://www.ispark.com.cn/</a></span>
			</div>
		</div>
	</div>
<script>
	$(function(){
		//居中
		 $('.login_main').center();
		 document.getElementById("username").focus();
		 $("#username").keydown(function(event){
		 	if(event.keyCode==13){
				login()
			}
		 })
		 $("#password").keydown(function(event){
		 	if(event.keyCode==13){
				login()
			}
		 })
		 
	})

	//登录
	function login() {
		var errorMsg = "";
		var loginName = document.getElementById("username");
		var password = document.getElementById("password");
		if(!loginName.value){
			errorMsg += "&nbsp;&nbsp;用户名不能为空!";
			$("username").focus();
			$(".login_info2").html(errorMsg);
			$(".login_info2").show();
			return false;
		}
		if(!password.value){
			errorMsg += "&nbsp;&nbsp;密码不能为空!";
			$("password").focus();
			$(".login_info2").html(errorMsg);
			$(".login_info2").show();
			return false;
		}

		if(errorMsg != ""){
			$(".login_info2").html(errorMsg);
			$(".login_info2").show();
			return false;
		}
		else{
			$(".login_info2").show();
			$(".login_info2").html("&nbsp;&nbsp;正在登录中...");
			//登录处理
			/* $.post("login.do?act=login",
				  {"username":loginName.value,"password":password.value},
				  function(result){
					  if(result == null){
						  $(".login_info").html("&nbsp;&nbsp;登陆失败！");
						  return false;
					  }
					  if(result.status=="true"||result.status==true){
					  	  $(".login_info").html("&nbsp;&nbsp;登录成功，正在转到主页...");
						  window.location="../system/layout_flat/main.jsp";  
					  }
					  else{
					  	 $(".login_info").html("&nbsp;&nbsp;"+result.message);
					  }
					  
				  },"json"); */
			//$("loginForm").submit;
			return true;
		}
	}
</script>
</body>
</html>

