<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>
		<!-- 引入jQuery类库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist.js"></script>
	</head>
	
	<body>
		<form action="${pageContext.request.contextPath}/RegistServlet" method="POST" >
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td class="tds" colspan="2" style="color:red;text-align:center;">
						${ msg }
					</td>
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username"
						value="${ param.username }"/>
						<span id="username_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"
						value="${ param.password }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2"
						value="${ param.password2 }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname"
						value="${ param.nickname }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" 
						value="${ param.email }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr"/>
						<img  src="${pageContext.request.contextPath}/VerifyCodeServlet" width="" height="" alt="" />
						<span id="valistr_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
				
			</table>
		</form>
	</body>
</html>
