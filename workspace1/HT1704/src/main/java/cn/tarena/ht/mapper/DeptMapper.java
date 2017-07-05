package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	
	public List<Dept> findAll();
	//通过注解封装为map
	public void updateState(@Param("deptIds")String[] deptIds, @Param("state")int state);
	//删除部门
	public void deleteDept(String[] deptIds);
	//查询部门信息
	public List<Dept> findParent();
	//新增部门
	public void saveDept(Dept dept);
	//查询单个部门信息
	public Dept findOne(String deptId);
	//更改部门信息
	public void updateDept(Dept dept);
	
}
