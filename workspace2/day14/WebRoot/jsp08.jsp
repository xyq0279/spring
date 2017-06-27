<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<body>
	<h1>pageContext</h1>
	<h3>实现请求转发</h3>
	<%-- pageContext.forward("/index.jsp"); --%>
	
	
	<% pageContext.include("/index.jsp"); %>
	
	<h3>实现请求包含</h3>
</body>
</html>
