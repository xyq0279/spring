package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;

public interface ModuleService {
	public List<Module> findAll();

	public List<Module> findParent();

	public void addModule(Module module);

	public void updateState(String[] moduleIds, int state);

	public void deleteModule(String[] moduleIds);

	public Module findOne(String moduleId);

	public void UpdateModule(Module module);
}
