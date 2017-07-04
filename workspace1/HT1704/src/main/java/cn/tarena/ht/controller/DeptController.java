package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	//展示全部部门
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Dept> deptList = deptService.findAll();
		
		System.out.println(deptList);
		
		model.addAttribute("deptList", deptList);
		
		return "/sysadmin/dept/jDeptList";
	}
	
	//修改状态 状态停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="deptId",defaultValue="0")String[] deptIds,Model model){
		//状态的修改 state=0
		int state = 0;
		deptService.updateState(deptIds,state);
		return "redirect:/sysadmin/dept/list";
	}
	//修改状态 状态启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="deptId",defaultValue="0")String[] deptIds,Model model){
		//状态的修改 state=0
		int state = 1;
		deptService.updateState(deptIds,state);
		return "redirect:/sysadmin/dept/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="deptId",defaultValue="0")String[] deptIds){
		
		deptService.deleteDept(deptIds);
		return "redirect:/sysadmin/dept/list";
	}
	//部门新增
	@RequestMapping("/toCreate")
	public String toCreate(Model model){
		
		List<Dept> parentList = deptService.findParent();
		model.addAttribute("parentList", parentList);
		return "/sysadmin/dept/jDeptCreate";
	}
	@RequestMapping("/save")
	public String save(Dept dept){
		
		deptService.saveDept(dept);
		
		return "redirect:/sysadmin/dept/list";
	}
	@RequestMapping("/toView")
	public String toView(String deptId,Model model){
		//根据部门id查询数据
		Dept dept = deptService.findOne(deptId);
		model.addAttribute("dept", dept);
		return "/sysadmin/dept/jDeptView";
	}
	@RequestMapping("/toUpdate")
	public String toUpdate(String deptId,Model model){
		//根据部门id查询数据
		Dept dept = deptService.findOne(deptId);
		List<Dept> parentList = deptService.findParent();
		model.addAttribute("dept", dept);
		model.addAttribute("parentList", parentList);
		return "/sysadmin/dept/jDeptUpdate";
	}
	@RequestMapping("/update")
	public String update(Dept dept){
		//根据部门id查询数据
		deptService.updateDept(dept);
		return "redirect:/sysadmin/dept/list";
	}
	
}
