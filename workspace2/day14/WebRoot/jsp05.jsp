<%@ page language="java" import="java.util.*" pageEncoding="utf-8" buffer="0kb"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<body>
	<% out.write("aaa<br/>"); %>
	<% response.getWriter().write("bbb<br/>"); %>
	<% out.write("ccc<br/>"); %>
</body>
</html>
