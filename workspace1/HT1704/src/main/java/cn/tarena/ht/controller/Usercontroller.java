package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserService;


@Controller
@RequestMapping("/sysadmin/user")
public class Usercontroller extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<User> userList = userService.findAll();
		System.out.println(userList);
		model.addAttribute("userList", userList);
		return "/sysadmin/user/jUserList";
	}
	
	@RequestMapping("/start")
	public String start(@RequestParam(value="userId",defaultValue="0")String[] userIds){
		int state = 1;
		userService.updateState(userIds,state);
		return "redirect:/sysadmin/user/list";
	}
	@RequestMapping("/stop")
	public String stop(@RequestParam(value="userId",defaultValue="0")String[] userIds){
		int state = 0;
		userService.updateState(userIds,state);
		return "redirect:/sysadmin/user/list";
	}
	
	@RequestMapping("/toCreate")
	public String toCreate(Model model){
		
		List<UserInfo> userList = userService.findUserInfo();
		List<Dept> parentList = deptService.findParent();
		model.addAttribute("userList", userList);
		model.addAttribute("parentList", parentList);
		return "/sysadmin/user/jUserCreate";
	}
	@RequestMapping("/save")
	public String addUser(User user){
		userService.addUser(user);
		return "redirect:/sysadmin/user/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="userId",defaultValue="0")String[] userIds){
		userService.delete(userIds);
		return "redirect:/sysadmin/user/list";
	}
	@RequestMapping("/toView")
	public String toView(String userId,Model model){
		User user = userService.findOne(userId);
		model.addAttribute("user", user);
		return "/sysadmin/user/jUserView";
	}
	@RequestMapping("/toUpdate")
	public String toUpdate(String userId,Model model){
		User usermsg = userService.findOne(userId);
		List<UserInfo> userList = userService.findManager(userId);
		List<Dept> parentList = deptService.findParent();
		model.addAttribute("usermsg", usermsg);
		model.addAttribute("userList", userList);
		model.addAttribute("parentList", parentList);
		return "/sysadmin/user/jUserUpdate";
	}
	@RequestMapping("/update")
	public String upate(User user){
		userService.update(user);
		return "redirect:/sysadmin/user/list";
	}
	//转向到角色分配页面
	@RequestMapping("/role")
	public String toRole(String userId,Model model) throws JsonProcessingException{
		//根据userid信息查询角色信息
		List<String> roleIdList = roleService.findRoleIdByUserId(userId);
		
		List<Role> roleList = roleService.findAll();
		for (Role role : roleList) {
			if(roleIdList.contains(role.getRoleId())){
				//当前的id是用户拥有的角色信息
				role.setChecked(true);
			}
		}
		
		//将roleList转化为 Jason串
		ObjectMapper objectMapper = new ObjectMapper();
		String zTreeJson = objectMapper.writeValueAsString(roleList);
		System.out.println(zTreeJson);
		model.addAttribute("zTreeJson", zTreeJson);
		
		model.addAttribute("userId", userId);
		return "/sysadmin/user/jUserRole";
	}
	//saveUserRole
	@RequestMapping("/saveUserRole")
	public String saveUserRole(String userId,String[] roleIds){
		//将用户和角色信息入库
		userService.saveRoleUser(userId,roleIds);
		return "redirect:/sysadmin/user/list";
	}
}
