<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<body>
	<h1 style="color:red;text-align:center;">亲, 服务器内部出错了哦~~~~~~!!</h1>
	<br>错误信息: <%= exception.getMessage() %>
	
</body>
</html>
