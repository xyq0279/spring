package cn.tarena.ht.controller;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.UserService;


@Controller
@RequestMapping("/sysadmin/user")
public class Usercontroller {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<User> userList = userService.findAll();
		System.out.println(userList);
		model.addAttribute("userList", userList);
		return "/sysadmin/user/jUserList";
	}
	
	@RequestMapping("/start")
	public String start(@RequestParam("userId")String[] userIds){
		int state = 1;
		userService.updateState(userIds,state);
		return "redirect:/sysadmin/user/list";
	}
	@RequestMapping("/stop")
	public String stop(@RequestParam("userId")String[] userIds){
		int state = 0;
		userService.updateState(userIds,state);
		return "redirect:/sysadmin/user/list";
	}
	
	@RequestMapping("/toCreate")
	public String toCreate(Model model){
		
		List<UserInfo> userList = userService.findManager();
		List<Dept> parentList = deptService.findParent();
		model.addAttribute("userList", userList);
		model.addAttribute("parentList", parentList);
		return "/sysadmin/user/jUserCreate";
	}
	
	public String addUser(User user){
			userService.addUser(user);
		return "redirect:/sysadmin/user/list";
	}
}
