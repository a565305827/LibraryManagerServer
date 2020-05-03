<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="UTF-8"/>

	<title>图书后台管理系统</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/style.css" type="text/css" />

	</head>

	<body>
		<div id="container">

			<!--
			服务器跳转时，路径相对的是服务器WebContent
			浏览器请求跳转时， 路径相对的当前的jsp
			
			 在JSP  凡是有  action  src  href 都要去写绝对路径  加上工程名称的路径
		    -->
			<form class="welcome"  action="${pageContext.request.contextPath }/LoginServlet">
				<div class="login">图书后台管理系统
				<span style="color:red">${err }</span>
				</div>
				<input id="utype" type="hidden" name="utype" value="4"/>
				<input id="type" type="hidden" name="type" value="2"/>
				<div class="username-text">用户名:</div>
				<div class="password-text">密码:</div>
				<div class="username-field">
					<input class="no_border" type="text" name="username" value="" />
				</div>
				<div class="password-field">
					<input class="no_border" type="password" name="password" value="" />
				</div>
				<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">记住用户名</label>

				<div class="forgot-usr-pwd"></div>
				<input id="admin-submit_login" type="submit" value="GO"/>
			</form>
		</div>

	</body>
</html>
