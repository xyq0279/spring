package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;

public interface RoleService {
	public List<Role> findAll();

	public void deleteRole(String[] roleIds);

	public void addRole(Role role);

	public Role findOne(String roleId);

	public void updateRole(Role role);

	public List<String> findRoleIdByUserId(String userId);
}
