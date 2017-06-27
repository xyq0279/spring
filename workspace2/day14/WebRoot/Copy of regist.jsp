<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/regist.css"/>
		<!-- 引入jQuery类库 -->
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.4.2.js"></script>
		<script type="text/javascript" >
			var formObj = {
				"checkForm":function(){
					var flag = true;
					flag = this.checkNull("username", "用户名不能为空") && flag;
					flag = this.checkNull("password", "密码不能为空")&&flag;
					flag = this.checkNull("password2", "确认密码不能为空")&&flag;
					flag = this.checkNull("nickname", "昵称不能为空")&&flag;
					flag = this.checkNull("email", "邮箱不能为空")&&flag;
					flag = this.checkNull("valistr", "验证码不能为空")&&flag;
					flag = this.checkEmail("email", "邮箱格式不正确")&&flag;
					flag= this.checkPassword("password", "密码不一致")&&flag;
					return flag;
				},
				"checkEmail":function(name,msg){
					var $email = $("input[name='"+name+"']");
					var val = $email.val();
					var str = /^\w+@\w+(\.\w+)+$/;
					
					if($.trim(val)!=""){
						this.setMsg(name,"");
						if(!str.test(val)){
							this.setMsg(name, msg);
							return false;
						}
					}
					return true;
				},
				"checkPassword":function(name,msg){
					var pw1 = $("input[name='"+name+"']").val();
					var pw2 = $("input[name='"+name+"2']").val();
					
					if($.trim(pw1)!=""&&$.trim(pw2)!=""){
						this.setMsg(name,"");
						this.setMsg(name+"2","");
						if(pw1!=pw2){
							this.setMsg(name+"2", msg);
							return false;
						}
						
					}
					return true;
				},
				"checkNull":function(name,msg){
					var $inp = $("input[name='"+name+"']");
					var val = $inp.val();
					this.setMsg(name,"");
					if($.trim(val) == ""){
						this.setMsg(name,msg);
						return false;
					}
					return true;
				},
				"setMsg":function(name,msg){
					$("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
				}
			};
			//文档就绪事件
			$(function(){
				$("input[name='username']").blur(function(){
				
					var username = $("input[name='username']").val();
					if($.trim(username)==""){
						formObj.checkNull("username", "用户名不能为空");
						return;
					}
					<%-- $("#username_msg").load("<%= request.getContextPath() %>/AjaxServlet",{"username":username}); --%>
					$.post("<%=request.getContextPath()%>/AjaxServlet",{"username":username},
					function(result){
						$("#username_msg").html(result).css("color","red");
					}); 
				});
				$("input[name='password']").blur(function(){
					formObj.checkNull("password", "密码不能为空");
					formObj.checkPassword("password", "密码不一致");
				});
				$("input[name='password2']").blur(function(){
					formObj.checkNull("password2", "确认密码不能为空");
					formObj.checkPassword("password", "密码不一致");
				});
				$("input[name='nickname']").blur(function(){
					formObj.checkNull("nickname", "昵称不能为空");
				});
				$("input[name='email']").blur(function(){
					formObj.checkNull("email", "邮箱不能为空");
					formObj.checkEmail("email", "邮箱格式不正确");
				});
				$("input[name='valistr']").blur(function(){
					var valistr = $("input[name='valistr']").val();
					if($.trim(valistr)==""){
						formObj.checkNull("valistr", "验证码不能为空");
						return;
					}
					$.post("<%=request.getContextPath()%>/AjaxCheckCodeServlet",{"valistr":valistr},
					function(result){
						$("#valistr_msg").html(result).css("color","red");
					});
					
				});	
			});
			function changeImage(thisObj){
				thisObj.src="<%= request.getContextPath() %>/VerifyCodeServlet?time="+new Date().getTime();
			};
		</script>
	</head>
	<body>
		<form action="<%= request.getContextPath() %>/RegistServlet" method="POST" 
		onsubmit="return formObj.checkForm();">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td class="tds" colspan="2" style="color:red;text-align:center;">
						<%= request.getAttribute("msg")== null ? "" : request.getAttribute("msg") %>
					</td>
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username"
						value="<%= request.getParameter("username") == null ? "" : request.getParameter("username") %>"/>
						<span id="username_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"
						value="<%= request.getParameter("password") == null ? "" : request.getParameter("password") %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2"
						value="<%= request.getParameter("password2") == null ? "" : request.getParameter("password2") %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname"
						value="<%= request.getParameter("nickname") == null ? "" : request.getParameter("nickname") %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" 
						value="<%= request.getParameter("email") == null ? "" : request.getParameter("email") %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr"/>
						<img onclick="changeImage(this)" src="<%= request.getContextPath() %>/VerifyCodeServlet" width="" height="" alt="" />
						<span id="valistr_msg"></span>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
				
			</table>
		</form>
	</body>
</html>
