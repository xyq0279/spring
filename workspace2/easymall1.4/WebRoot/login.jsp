<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app}/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
		<script type="text/javascript">
			window.onload=function(){
				var str = document.getElementsByName("username")[0];
				var username = decodeURI(str.value);
				str.value = username;
			};
		</script>
	</head>
	<body>
		<h1>欢迎登陆EasyMall</h1>
		<form action="${app}/LoginServlet" method="POST">
			<table>
				<%-- <%
					/*取出cookie中记住的用户名，存入用户名输入框*/
					//获取所有的cookie组成的数组
					Cookie[] cs = request.getCookies();
					Cookie renameCoikie = null;
					//判断cs是否为空
					if(cs!=null){
						//遍历cookie数组
						for(Cookie c : cs){
							//找到名称为rename的cookie
							if("rename".equals(c.getName())){
								renameCoikie = c;
							}
						}
					}
					
					String username = null;
					if(renameCoikie != null){
						username = renameCoikie.getValue();
						username = URLDecoder.decode(username, "utf-8");
					}
				 %> --%>
				 <tr>
					<td class="tdx" colspan="2" style="color:red;text-align:center;">
						${ msg }
					</td>
				</tr>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="${ cookie.remname.value }"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true" 
						${ empty cookie.remname ? "":"checked='checked'" } 
						/>记住用户名                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
						<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center"
					${ empty cookie.user ? "":"checked='checked'" }>
						<input type="submit" value="登 陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>
