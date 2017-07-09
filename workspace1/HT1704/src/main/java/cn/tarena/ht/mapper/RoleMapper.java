package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	//查询全部角色信息
	@Select("select * from role_p order by order_no")
	public List<Role> findAll();

	public void deleteRole(String[] roleId);

	public void addRole(Role role);
	@Select("select * from role_p where role_id = #{roleId}")
	public Role findOne(String roleId);

	public void updateRole(Role role);
	
	@Select("select role_id from role_user_p where user_id = #{userId}")
	public List<String> findRoleIdByUserId(String userId);
	
	//删除用户之前删除对应的关联角色
	public void deleteRoleByUserId(String[] userIds);

	public void saveRoleModule(@Param("roleId")String roleId,@Param("moduleId") String moduleId);

	public void deleteRoleByRoleId(String roleId);

	public void deleteRoleUserByRoleId(String[] roleIds);

	public void deleteRoleModuleByRoleId(String[] roleIds);
}
