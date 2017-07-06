package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	public List<User> findAll();

	public void updateState(@Param("userIds")String[] userIds, @Param("state")int state);
	
	public void addUser(User user);

	public void deleteUser(String[] userIds);

	public User findOne(String userId);

	public void updateUser(User user);

}
