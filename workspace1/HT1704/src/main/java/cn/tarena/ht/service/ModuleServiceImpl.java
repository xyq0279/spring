package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;
@Service
public class ModuleServiceImpl implements ModuleService{
	@Autowired
	private ModuleMapper moduleMapper;
	@Override
	public List<Module> findAll() {
		return moduleMapper.findAll();
	}
	@Override
	public void updateState(String[] moduleIds, int state) {
		moduleMapper.updateState(moduleIds,state);
	}
	@Override
	public void deleteModule(String[] moduleIds) {
		moduleMapper.deleteRoleMouleByModuleId(moduleIds);
		moduleMapper.deleteModule(moduleIds);
	}
	@Override
	public List<Module> findParent() {
		return moduleMapper.findParent();
	}
	@Override
	public void addModule(Module module) {
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		moduleMapper.addModule(module);
	}
	@Override
	public Module findOne(String moduleId) {
		return moduleMapper.findOne(moduleId);
	}
	@Override
	public void UpdateModule(Module module) {
		module.setUpdateTime(new Date());
		moduleMapper.UpdateModule(module);
	}
	@Override
	public List<String> findModuleIdByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return moduleMapper.findModuleIdByRoleId(roleId);
	}

}
