package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/tologin")
	public String toLogin(){
		return "/sysadmin/login/login";
	}
}
