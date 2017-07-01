package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.pojo.User;
import cn.tedu.service.UserService;


@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("toFindAll.action")
	public String findAll(Model model){
		
		List<User> userList = userService.findAll();
		
		model.addAttribute("userList", userList);
		
		return "findAll";
	}

}
