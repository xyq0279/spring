package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	public List<User> findAll();

	public void updateState(@Param("userIds")String[] userIds, @Param("state")int state);
	
	public void addUser(User user);

	public void deleteUser(String[] userIds);

	public User findOne(String userId);

	public void updateUser(User user);
	@Insert("insert into role_user_p(role_id,user_id) values(#{roleId},#{userId})")
	public void saveRoleUser(@Param("userId")String userId,@Param("roleId") String roleId);
	@Delete("delete from role_user_p where user_id = #{userId}")
	public void deleteRoleByUserId(String userId);

	public User findUserByUserName(String username);

	public List<String> findModileNameList(String userId);

}
