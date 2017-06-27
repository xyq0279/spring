<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>我的购物车</title>
<link href="${app}css/cart.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){

		$(".delProd").click(function(){
			if(confirm("亲，你真的忍心抛弃我吗？")){
				var pid = $(this).parents("ul").find(".buyNumInp").attr("id");
				window.location.href = "${app}/CartDeleteServlet?pid="+pid;
				
			}
		});
		$(".delNum").click(function(){
			var $inpNum = $(this).next("input");
			var oldNum = $(this).nextAll("input[type='hidden']").val();
			var pid = $inpNum.attr("id");
			var newNum = oldNum - 1;
			if(newNum <= 0){
				window.location.href = "${app}/CartDeleteServlet?pid="+pid;
			}else{
				window.location.href="${app}/CartEditServlet?pid="+pid+"&newNum="+newNum;
			}
		});
		$(".addNum").click(function(){
			var $inpNum = $(this).prev();
			var oldNum = $(this).next("input[type='hidden']").val();
			var pid = $inpNum.attr("id");
			var newNum = parseInt(oldNum) + 1;
			window.location.href="${app}/CartEditServlet?pid="+pid+"&newNum="+newNum;
		});
		$(".buyNumInp").blur(function(){
			var newNum = $(this).val();
			var pid = $(this).attr("id");
			var oldNum = $(this).nextAll("input[type='hidden']").val();
			var reg = /^[1-9][0-9]*$/;
			if(!reg.test(newNum)){
				alert("请输入正整数");
				$(this).val(oldNum);
			}else{
				window.location.href="${app}/CartEditServlet?pid="+pid+"&newNum="+newNum;
			}
		});
	});	
</script>
</head>
<body>
<%@include file="_head.jsp" %>
	<div id="wrap">
		
		${msg}
		<!-- 标题信息 -->
		<ul id="title">
			<li><input name="allC" type="checkbox" value="" onclick="" /> <span
				id="title_checkall_text">全选</span>
			</li>
			<li class="li_prod">商品</li>
			<li>单价（元）</li>
			<li>数量</li>
			<li>小计（元）</li>
			<li>操作</li>
		</ul>

		<!-- 购物信息 -->
		<c:set var="money" value="0" ></c:set>
		<c:forEach items="${ cart }" var="entry">
		<ul class="prods">
				<li><input type="checkbox" class="allC" name="allC" />
				</li>
				<li class="li_prod">
					<img src="${app}/ImgurlServlet?imgurl=${entry.key.imgurl}" width="90" height="90" class="prodimg" />
					<span class="prodname">${ entry.key.name }</span>
				</li>
				<li class="li_price">${ entry.key.price }</li>
				<li>
					<a href="javascript:void(0)" class="delNum">-</a> 
					<input id="${ entry.key.id }" class="buyNumInp" type="text" value="${ entry.value }"/>
					<a href="javascript:void(0)" class="addNum">+</a>
					<input type="hidden" id="hid_${ entry.key.id }" value="${ entry.value }" />
				</li>
				<li class="sum_price">${ entry.key.price*entry.value }</li>
				<c:set var="money" value="${ money+entry.key.price*entry.value }"></c:set>
				<li><a id="" class="delProd" href="javascript:void(0)">删除</a></li>
		</ul>
		</c:forEach>
		<!-- 总计条 -->
		<div id="total">
			<div id="total_1">
				<input type="checkbox" class="allC" name="allC" /> <span>全选</span>
				<a id="del_a" href="javascript:void(0)">删除选中的商品</a>
				<div id="div_sum">
					<span id="span_1">总价：</span> <span>￥</span> <span id="span_2"
						class="total_sum_price">${ money }</span>
				</div>
			</div>
			<div id="total_2">
				<a id="goto_order" href="${app}/addOrder.jsp" target="_blank">去结算</a>
			</div>
		</div>
	</div>
<%@include file="_foot.jsp" %>
</body>
</html>