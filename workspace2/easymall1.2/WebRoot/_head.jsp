<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
	<div id="line1">
		<div id="content">
			<!-- 如果用户没有登录，提示登录或注册 -->
			<c:if test="${ (empty sessionScope)||(empty sessionScope.user) }">
				<a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
			</c:if>
			<c:if test="${ !((empty sessionScope)||(empty sessionScope.user)) }">
				欢迎${ sessionScope.user.username }回来&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/LogoutServlet">注销</a>
			</c:if>
			<%-- <% if(request.getSession(false)==null||request.getSession().getAttribute("user")==null){ %>
				<a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
			<% }else{ %>
			<!--  如果用户已经登录，提示欢迎xxx回来-->
				欢迎<%= request.getSession().getAttribute("user") %>回来&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/LogoutServlet">注销</a>
			<% } %> --%>
		</div>
	</div>
	<div id="line2">
		<img id="logo" src="${pageContext.request.contextPath}/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="#">我的订单</a>
			<a id="goto_cart" href="#">我的购物车</a>
		</span>
		<img id="erwm" src="${pageContext.request.contextPath}/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="#">首页</a></li>
				<li><a href="#">全部商品</a></li>
				<li><a href="#">手机数码</a></li>
				<li><a href="#">电脑平板</a></li>
				<li><a href="#">家用电器</a></li>
				<li><a href="#">汽车用品</a></li>
				<li><a href="#">食品饮料</a></li>
				<li><a href="#">图书杂志</a></li>
				<li><a href="#">服装服饰</a></li>
				<li><a href="#">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>