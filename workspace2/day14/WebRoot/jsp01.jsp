<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<body>
	<h1>1.JSP语法</h1>
	<h3>1.1.模版元素</h3>
	day13....index.jsp........
	
	
	<h3>1.2.JSP表达式</h3>
	<% String str = "hello JSP~~~"; %>
	<%= "韩少云" %>	<br/>
	<%= str %>		<br/>
	<%= 100+123 %>	<br/>
	<%= request.getContextPath() %><br/>
	
	
	<h3>1.3.JSP脚本片段</h3>
	<% String name = "尼古拉斯赵四"; %>
	<%= name %>
	<br/>
	
	<% for(int i=0; i<5; i++){ %>
		Hello JSP~~~~~<br/>
	<% } %>
	
	<% String str2 = name+str; %>
	<%= str2 %>
	
	<h3>1.4.JSP声明</h3>
	<%! String str3 = "JSP声明~!"; %>
	<%! final String str4 = "hello~~~~"; %>
	<%! public void m1(){} %>
	<%! class c1{} %>
	<%! static{} %>
	
	<br/>
	测试:
		<% int a = 0; %>
		<%! int b = 0; %>
		<br/>
		<%= ++a %> <br/>
		<%= ++b %>
	
	
	<h3>1.5.JSP注释</h3>
	<!-- 这是html注释 -->
	<%-- 这是JSP注释 --%>
	<% //String str = "aaa"; %>
	
	<br/>
	<br/>
	<%-- out.write("aaa<br/>"); --%>
	<!-- <% out.write("bbb<br/>"); %> -->
	<% //out.write("ccc<br/>"); %>
	
	
	<%-- <% str3 = "哈哈哈哈~!"; %> --%>
	
	<%= str3 %>
	
	
	
	
	
	
	<br>
</body>
</html>
