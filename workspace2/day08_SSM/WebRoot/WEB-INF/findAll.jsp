<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>查询用户表</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
  </head>
  <body>
  	<table width="60%" align="center" border="1" bordercolor="red">
  		<caption>用户表</caption>>
  		<tr align="center">
  			<td>id</td>
  			<td>姓名</td>
  			<td>年龄</td>
  			<td>性别</td>
  		</tr align="center">
  		<c:forEach items="${userList}" var="user">
  		<tr>
  			<td>${user.id}</td>
  			<td>${user.name}</td>
  			<td>${user.age}</td>
  			<td>${user.sex}</td>
  		</tr>
  		</c:forEach>
  	</table>
  </body>
</html>
