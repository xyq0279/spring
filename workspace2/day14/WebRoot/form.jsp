<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<h1>GET提交</h1>
		<form action="http://localhost/day14/servlet/ServletDemo1" method="GET">
			用户名: <input type="text" name="username" />
			昵称: <input type="text" name="nickname" />
			<input type="submit" value="提交" />
		</form>
		<h1>POST提交</h1>
		<form action="http://localhost/day14/servlet/ServletDemo1" method="POST">
			用户名: <input type="text" name="username" />
			昵称: <input type="text" name="nickname" />
			爱好: <input type="checkbox" name="like" value="lanqiu" />篮球
				<input type="checkbox" name="like" value="zuqiu" />足球
				<input type="checkbox" name="like" value="taiqiu" />台球
			<input type="submit" value="提交" />
		</form>
	</body>
</html>