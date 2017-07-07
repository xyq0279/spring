package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {
	
	public List<UserInfo> findUserInfo();
	
	public void addUserInfo(UserInfo info);

	public void deleteUserInfo(String[] userIds);

	public void updateUserInfo(UserInfo info);

	public List<UserInfo> findManager(String userId);
	
}
