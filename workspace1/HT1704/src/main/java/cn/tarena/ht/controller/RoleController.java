package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;


@Controller
@RequestMapping("/sysadmin/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService  moduleService;
	
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
		model.addAttribute("role", role);
		return "/sysadmin/role/jRoleView";
	}
	@RequestMapping("/module")
	public String module(String roleId,Model model) throws JsonProcessingException{
		//根据roleId查询模块信息
		List<String> moduleIdList = moduleService.findModuleIdByRoleId(roleId);
		List<Module> moduleList = moduleService.findParent();
		for (Module module : moduleList) {
			if(moduleIdList.contains(module.getModuleId())){
				module.setChecked(true);
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String zTreeJson = objectMapper.writeValueAsString(moduleList);
		model.addAttribute("zTreeJson", zTreeJson);
		model.addAttribute("roleId", roleId);
		return "/sysadmin/role/jRoleModule";
		
	}
	@RequestMapping("/saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		
		roleService.saveRoleModule(roleId,moduleIds);

		return "redirect:/sysadmin/role/list";
	}
}
