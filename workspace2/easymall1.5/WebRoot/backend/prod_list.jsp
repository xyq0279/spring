<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>商品管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{
		font-family: "微软雅黑";
		background-color: #EDEDED;
	}
	h2{
		text-align: center;
	}
	table{ 
		margin: 0 auto; 
		/* width: 96%; */
		text-align: center;
		border-collapse:collapse;
	}
	td, th{ padding: 7px;}
	th{
		background-color: #DCDCDC;
	}
	th.ths{
		width: 100px;
	} 
	input.pnum{
		width:80px;
		height:25px;
		font-size: 18px;
		text-align:center;
	}
	
</style>

<!--引入jquery的js库-->
<script src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){
	//给所有的库存数量输入框添加一个失去焦点事件
		$(".pnum").blur(function(){
			//获得当前输入框的dom对象
			var _self = this;
			//获取库存数量
			var pnum = $(this).val();
			var pid = $(this).attr("id");
			//获取隐藏域的库存数量备份值
			var $oldpnum = $(this).next();
			var oldpnum = $oldpnum.val();
			
			if(oldpnum!=pnum){
				var reg = /^0$|^[1-9][0-9]*$/;
				if(reg.test(pnum)){
					//动态修改数据库中的库存数量
					$.post("${app}/AjaxCheckBackPnumServlet",
					{"pnum":pnum,"pid":pid},
					function(result){
					//console.log(this);
						if(result=="true"){
							alert("修改成功!");
							//更新备份
							$oldpnum.val(pnum);
						}else{
							alert("修改失败!");
							//恢复当前输入框修改前数值
							$(_self).val(oldpnum);
						}
					});
					
				}else{
					alert("格式错误，请输入正确数值");
					$(this).val(oldpnum);
				}
			}
		});
		//为删除商品，超链接绑定点击事件
		$(".del").click(function(){
			// window .confirm 确认对话框
			if(confirm("您确定要删除该商品吗？")){
				//获取商品ID
				//var pid = $(this).parent().prev().prev().attr("id").val();
				var $del = $(this).parents("tr");
				var pid = $(this).parents("tr").find(".pnum").attr("id");
				alert(pid);
				//利用ajax异步删除商品
				$.ajax({
					"url":"${app}/delProdServlet",
					"data":{"pid":pid},
					"type":"POST",
					"success":function(result){
						if(result=="true"){
							$del.remove();
							alert("删除成功");
						}else{
							alert("删除失败");
						}
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<h2>商品管理</h2>
	<table border="1">
		<tr>
			<th>商品图片</th>
			<th width="200px">商品ID</th>
			<th class="ths">商品名称</th>
			<th class="ths">商品种类</th>
			<th class="ths">商品单价</th>
			<th class="ths">库存数量</th>
			<th>描述信息</th>
			<th width="50px">操 作</th>
		</tr>

		<!-- 模版数据 -->
		<c:forEach items="${list}" var="prod">
			<tr>
				<td><img width="120px" height="120px" src="${app}/ImgurlServlet?imgurl=${prod.imgurl}"
					alt=""></td>
				<td>${ prod.id }</td>
				<td>${ prod.name }</td>
				<td>${ prod.category }</td>
				<td>${ prod.price }</td>
				<td><input type="text" id="${ prod.id }" class="pnum" value="${ prod.pnum }" />
				<input type="hidden" value="${ prod.pnum }"/>
				</td>
				<td>${ prod.description }</td>
				<td><a class="del" href="javascript:void(0)">删 除</a>
				</td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>