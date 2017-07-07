package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.RoleService;


@Controller
@RequestMapping("/sysadmin/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		return "/sysadmin/role/jRoleList";
	}
	@RequestMapping("/delete")
	public String toDelete(@RequestParam("roleId")String[] roleIds){
		roleService.deleteRole(roleIds);
		return "redirect:/sysadmin/role/list";
	}
	@RequestMapping("/toCreate")
	public String toCreate(){
		return "/sysadmin/role/jRoleCreate";
	}
	@RequestMapping("/save")
	public String save(Role role){
		roleService.addRole(role);
		return "redirect:/sysadmin/role/list";
	}
	@RequestMapping("/toUpdate")
	public String toUpdate(String roleId,Model model){
		Role role = roleService.findOne(roleId);
		System.out.println(role);
		model.addAttribute("role", role);
		return "/sysadmin/role/jRoleUpdate";
	}
	@RequestMapping("/update")
	public String update(Role role){
		roleService.updateRole(role);
		return "redirect:/sysadmin/role/list";
	}
	@RequestMapping("/toView")
	public String toView(String roleId,Model model){
		Role role = roleService.findOne(roleId);
		System.out.println(role);
		model.addAttribute("role", role);
		return "/sysadmin/role/jRoleView";
	}
}
