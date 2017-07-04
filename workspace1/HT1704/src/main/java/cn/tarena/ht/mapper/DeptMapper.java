package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	
	public List<Dept> findAll();
	//通过注解封装为map
	public void updateState(@Param("deptIds")String[] deptIds, @Param("state")int state);
	
	public void deleteDept(String[] deptIds);
	//查询部门信息
	public List<Dept> findParent();
	
	public void saveDept(Dept dept);
	
	public Dept findOne(String deptId);
	
	public void updateDept(Dept dept);
	
}
