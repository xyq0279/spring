package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserService {
	public List<User> findAll();

	public void updateState(String[] userIds, int state);

	public void addUser(User user);

	public List<UserInfo> findUserInfo();

	public void delete(String[] userIds);

	public User findOne(String userId);

	public void update(User user);

	public List<UserInfo> findManager(String userId);

	public void saveRoleUser(String userId, String[] roleIds);

	public User findUserByUserName(String username);

	public List<String> findModileNameList(String userId);
}
