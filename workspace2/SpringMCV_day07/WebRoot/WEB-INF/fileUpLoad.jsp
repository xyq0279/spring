<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=UTF-8">
		<title>添加用户</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/file.action" method="POST"
		enctype="multipart/form-data">
			
			<table align="center" border="1" width="45%">
				<tr>
					<td colspan="2" align="center"><h3>文件上传</h3></td>
				</tr>
				<tr>
					<td>文件上传</td>
					<td><input name="photo" type="file"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="提交"/>
					</td>
				</tr>
				${msg }
			</table>
		</form>
	</body>
</html>