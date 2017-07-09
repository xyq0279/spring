package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleMapper.findAll();
	}

	@Override
	public void deleteRole(String[] roleIds) {
		roleMapper.deleteRoleUserByRoleId(roleIds);
		roleMapper.deleteRoleModuleByRoleId(roleIds);
		roleMapper.deleteRole(roleIds);
	}

	@Override
	public void addRole(Role role) {
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		roleMapper.addRole(role);
	}

	@Override
	public Role findOne(String roleId) {
		// TODO Auto-generated method stub
		return roleMapper.findOne(roleId);
	}

	@Override
	public void updateRole(Role role) {
		role.setUpdateTime(new Date());
		roleMapper.updateRole(role);
	}

	@Override
	public List<String> findRoleIdByUserId(String userId) {
		return roleMapper.findRoleIdByUserId(userId);
	}

	@Override
	public void saveRoleModule(String roleId, String[] moduleIds) {
		roleMapper.deleteRoleByRoleId(roleId);
		for (String moduleId : moduleIds) {
			roleMapper.saveRoleModule(roleId,moduleId);
		}
	}

}
