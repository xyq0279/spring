package controller;

//import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.User;


@Controller
public class UserController {
	
	//页面的转向
	@RequestMapping	("/toAddUser.action")
	public String toAddUser(){
		return "addUser";
	}
	
	@RequestMapping	("/addUser.action")
	public String addUser(User user,Model model){
		model.addAttribute("user", user);
		return "addUser";
	}
	
	@InitBinder
	public void InitBinder (ServletRequestDataBinder binder){
		binder.registerCustomEditor(
			java.util.Date.class, 
			new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
