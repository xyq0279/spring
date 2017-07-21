package cn.tarena.ht.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/tologin")
	public String toLogin(){
		return "/sysadmin/login/login";
	}
	@RequestMapping("login")
	public String login(String userName,String password,Model model,HttpSession session){
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			return "/sysadmin/login/login";
		}
		//shiro的登录操作  获取用户对象
		Subject subject = SecurityUtils.getSubject();
		//将用户的数据封装为令牌
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		try {
			//通过用户实现登录、
			subject.login(token);
			User user = (User) subject.getPrincipal();
			//session.setAttribute("sessionUser", user);
			subject.getSession().setAttribute("sessionUser", user);
			return "redirect:/home.action";
		} catch (AuthenticationException e) {
			e.printStackTrace();//打印异常信息
			//
			model.addAttribute("errorInfo", "用户名或密码不正确 ");
			return "/sysadmin/login/login";
		}
		
		
	}
	
//	@RequestMapping("/login")
//	public String login(String username,String password,Model model,HttpSession session){
//		
//		userService.findUserByUP();
//		return "/home";
//	}
}
