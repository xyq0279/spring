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
	<h1>2.JSP九大隐式对象</h1>
	<h3>2.2.pageContext对象</h3>
	<h3>2.2.1.作为入口对象, 获取其他八大隐式对象</h3>
	<%
		pageContext.getRequest().setAttribute("", "");
		request.setAttribute("", "");
	 %>
	 ${ pageContext.request.contextPath }
	 
	
	
</body>
</html>
