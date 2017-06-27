<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${app}/css/orderList.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="/_head.jsp"%>
<c:forEach items="${list}" var="orderinfo">
		<dl class="Order_information">
			<dt>
				<h3>订单信息</h3>
			</dt>
			<dd>
				订单编号：${ orderinfo.order.id }<br />
				 下单时间：${ orderinfo.order.ordertime }<br /> 
				 订单金额：${ orderinfo.order.money }<br /> 
				 支付状态：
				 <c:if test="${orderinfo.order.paystate==0}">
						<font color="red">未支付</font>&nbsp;&nbsp;&nbsp;
						<a href="${app}/OrderDeleteServlet?id=${orderinfo.order.id }"><img src="${app}/img/orderList/sc.jpg" width="69" height="19"/></a> 
				 		<a href="${app}/pay.jsp?id=${orderinfo.order.id }&money=${orderinfo.order.money }"> <img src="${app}/img/orderList/zx.jpg" width="69" height="19"/></a><br /> 
				 </c:if>
				  <c:if test="${orderinfo.order.paystate==1}">
						<font color="blue">已支付</font>
				</c:if>
				 所属用户：${orderinfo.order.id }<br/> 
				 收货地址：${orderinfo.order.receiverinfo }<br/> 
				支付方式：在线支付
			</dd>
		</dl>
	
		<table width="1200" border="0" cellpadding="0"
			cellspacing="1" style="background:#d8d8d8;color:#333333">
			<tr>
				<th width="276" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
				<th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
				<th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
				<th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
				<th width="232" align="center" valign="middle" bgcolor="#f3f3f3">总价</th>
			</tr>
			<c:set var="money" value="0"></c:set>
		<c:forEach items="${ orderinfo.map }" var="entry">
			<tr>
				<td align="center" valign="middle" bgcolor="#FFFFFF">
					<img src="${app}/ImgurlServlet?imgurl=${entry.key.imgurl}" width="90" height="105"/>
				</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.key.name }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.key.price }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.value }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ entry.key.price*entry.value }</td>
			</tr>
			<c:set var="money" value="${ money+entry.key.price*entry.value }"></c:set>
		</c:forEach>
		</table>
		<div class="Order_price">总价：${money}元</div>
</c:forEach>
<%@include file="/_foot.jsp" %>
</body>
</html>
