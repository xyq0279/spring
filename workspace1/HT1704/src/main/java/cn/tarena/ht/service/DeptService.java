package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Dept;

public interface DeptService {
	
	public List<Dept> findAll();

	public void updateState(String[] deptIds, int state);

	public void deleteDept(String[] deptIds);

	public List<Dept> findParent();

	public void saveDept(Dept dept);

	public Dept findOne(String deptId);

	public void updateDept(Dept dept);
}