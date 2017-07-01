<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=UTF-8">
		<title>添加用户</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/addUser.action" method="POST">
			
			<table align="center" border="1" width="45%">
				<tr>
					<td colspan="2" align="center"><h3>用户的新增</h3></td>
				</tr>
				<tr>
					<td>用户名:</td>
					<td><input name="userName" type="text"/></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input name="password" type="password"/></td>
				</tr>
				<tr>
					<td>年龄:</td>
					<td><input name="age" type="text"/></td>
				</tr>
				<tr>
					<td>宠物姓名:</td>
					<td><input name="dog.dogName" type="text"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="提交"/>
					</td>
				</tr>
			</table>
		</form>
		<!-- <h4>${userName}</h4>
		<h4>${password}</h4>
		<h4>${age}</h4> -->
		<h4>${user}</h4>
	</body>
</html>