package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	
	public List<Module> findAll();
	
	@Select("select * from module_p")
	public List<Module> findParent();
	
	public void addModule(Module module);
	
	public void updateState(@Param("moduleIds")String[] moduleIds,@Param("state") int state);
	
	public void deleteModule(String[] moduleIds);
	
	public Module findOne(String moduleId);
	
	public void UpdateModule(Module module);
	
	public List<String> findModuleIdByRoleId(String roleId);
	
	public void deleteRoleMouleByModuleId(String[] moduleIds);
}
