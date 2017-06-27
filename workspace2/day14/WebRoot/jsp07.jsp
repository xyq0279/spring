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
	<h3>2.2.2.作为域对象, 或者作为入口操作其他三大域对象</h3>
	
	<% //pageContext.setAttribute("addr", "北京"); %>
	<% //pageContext.setAttribute("addr", "上海", PageContext.REQUEST_SCOPE); %>
	<% //pageContext.setAttribute("addr", "广州", PageContext.SESSION_SCOPE); %>
	<% //pageContext.setAttribute("addr", "深圳", PageContext.APPLICATION_SCOPE); %>
	
	
	<%--= pageContext.getAttribute("addr", PageContext.PAGE_SCOPE) %>
	<%= pageContext.getAttribute("addr", PageContext.REQUEST_SCOPE) %>
	<%= pageContext.getAttribute("addr", PageContext.SESSION_SCOPE) %>
	<%= pageContext.getAttribute("addr", PageContext.APPLICATION_SCOPE) --%>
	
	<hr/>
	
	<%= pageContext.findAttribute("addr1") %>
	
	
</body>
</html>
