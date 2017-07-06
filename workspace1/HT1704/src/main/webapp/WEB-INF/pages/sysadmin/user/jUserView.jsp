<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户信息</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="back"><a href="#" onclick="window.history.go(-1);">后退</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   用户信息
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>用户名</td>
		<td>${user.username}</td>
		<td>密码</td>
		<td>${user.password}</td>
	</tr>
	<tr class="odd">
		<td>真实姓名</td>
		<td>${user.userInfo.name}</td>
		<td>身份证号</td>
		<td>${user.userInfo.cardNo}</td>
	</tr>
	<tr class="odd">
		<td>生日</td>
		<td><fmt:formatDate value="${user.userInfo.birthday}" pattern="yyyy-MM-dd"/></td>
		<td>性别</td>
		<td>${user.userInfo.gender}</td>
	</tr>
	<tr class="odd">
		<td>入职日期</td>
		<td><fmt:formatDate value="${user.userInfo.joinDate}" pattern="yyyy-MM-dd"/></td>
		<td>电话</td>
		<td>${user.userInfo.telephone}</td>
	</tr>
	<tr class="odd">
		<td>所属部门</td>
		<td>${user.dept.deptName}</td>
		<td>直属领导</td>
		<td>${user.userInfo.manager.name}</td>
	</tr>
	<tr class="odd">
		<td>薪酬</td>
		<td>${user.userInfo.salary}</td>
		<td>职务</td>
		<td>${user.userInfo.station}</td>
	</tr>
	
	<tr class="odd">
		<td>用户级别</td>
		<td>
			<c:if test="${user.userInfo.userLevel==1}">总经理</c:if>
			<c:if test="${user.userInfo.userLevel==2}">副总</c:if>
			<c:if test="${user.userInfo.userLevel==3}">部门经理</c:if>
			<c:if test="${user.userInfo.userLevel==4}">普通用户</c:if>
		</td>
		<td>状态</td>
		<td>${user.state}</td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3">${user.userInfo.remark}</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

