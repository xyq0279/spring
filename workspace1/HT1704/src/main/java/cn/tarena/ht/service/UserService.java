package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserService {
	public List<User> findAll();

	public void updateState(String[] userIds, int state);

	public void addUser(User user);

	public List<UserInfo> findManager();
}
