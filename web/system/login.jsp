<%@ page language="java" pageEncoding="utf-8"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String myerr = (String)request.getAttribute("myerrors");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500" />
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/form-elements.css" />
<link rel="stylesheet" href="assets/css/style.css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png" />
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png" />
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png" />
</head>
<body>
	<!-- Top content -->
	<div class="top-content" style="font-family: '黑体'">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong><img src="assets/img/homelogo.png"></img></strong>
							<br />
						</h1>
						<div class="description" style="margin-top: 30px;">
							<p>
								<font style="filter: Alpha(Opacity=90);font-size: 20px;">sparkonline</font>
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left" style="font-family: '黑体'">
								<h3>员工登录</h3>
								<p>请输入您的账号和密码:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="login.do?act=login" method="post" class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">Username</label> <input
										type="text" name="username" placeholder="Username..."
										class="form-username form-control" id="form-username" style="font-size: 20px;">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label> <input
										type="password" name="password" placeholder="Password..."
										class="form-password form-control" id="form-password" style="font-size: 20px;">
								</div>
								<button type="submit" class="btn"><font style="font-family: '黑体';font-size: 18px;">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</font></button>
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 social-login">
						<h4>股份有限公司  2015 版权所有</h4>
						<!-- <div class="social-login-buttons">
							<a class="btn btn-link-1 btn-link-1-facebook" href="#"> <i
								class="fa fa-facebook"></i> Facebook </a> <a
								class="btn btn-link-1 btn-link-1-twitter" href="#"> <i
								class="fa fa-twitter"></i> Twitter </a> <a
								class="btn btn-link-1 btn-link-1-google-plus" href="#"> <i
								class="fa fa-google-plus"></i> Google Plus </a>
						</div> -->
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>
</html>
