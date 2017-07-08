package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;



@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController extends BaseController{
	
	@Autowired
	private ModuleService moduleService;
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Module> moduleList = moduleService.findAll();
		System.out.println(moduleList);
		model.addAttribute("moduleList", moduleList);
		return "/sysadmin/module/jModuleList";
	}
	@RequestMapping("/start")
	public String start(@RequestParam(value="moduleId",defaultValue="0")String[] moduleIds){
		int state = 1;
		moduleService.updateState(moduleIds,state);
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/stop")
	public String stop(@RequestParam(value="moduleId",defaultValue="0")String[] moduleIds){
		int state = 0;
		moduleService.updateState(moduleIds,state);
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="moduleId",defaultValue="0")String[] moduleIds){
		moduleService.deleteModule(moduleIds);
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/toCreate")
	public String toCreate(Model model){
		List<Module> pList = moduleService.findParent();
		model.addAttribute("pList", pList);
		return "/sysadmin/module/jModuleCreate";
	}
	@RequestMapping("/save")
	public String save(Module module){
		moduleService.addModule(module);
		return "redirect:/sysadmin/module/list";
	}
	@RequestMapping("/toUpdate")
	public String toUpdate(String moduleId,Model model){
		Module module = moduleService.findOne(moduleId);
		List<Module> pList = moduleService.findParent();
		model.addAttribute("pList", pList);
		model.addAttribute("module", module);
		return "/sysadmin/module/jModuleUpdate";
	}
	@RequestMapping("/update")
	public String update(Module module){
		moduleService.UpdateModule(module);
		return "redirect:/sysadmin/module/list";
	}
}
