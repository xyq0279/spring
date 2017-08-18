package com.jt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public static final String JT_TICKET = "JT_TICKET";
	
	@RequestMapping("/login")
	public String index(){
		return "login";
	}
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult toRegister(User user) throws Exception{ 
		return userService.toRegister(user);
	}
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult toLogin(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		String ticket=userService.toLogin(user);
		//前台存入cookie
		CookieUtils.setCookie(request, response, JT_TICKET, ticket);
		return SysResult.oK(ticket);
	}
	@RequestMapping("/logout")
	public String logout(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		//前台删除cookie
		CookieUtils.deleteCookie(request, response, JT_TICKET);
		return "index";
	}
	
}
