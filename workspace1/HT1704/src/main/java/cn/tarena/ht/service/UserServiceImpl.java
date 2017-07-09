package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
	
	@Override
	public void updateState(String[] userIds, int state) {
		userMapper.updateState(userIds,state);
	}
	
	@Override
	public List<UserInfo> findUserInfo() {
		return userInfoMapper.findUserInfo();
	}
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String userId = UUID.randomUUID().toString();
		Date createDate = new Date();
		user.setUserId(userId);
		user.setCreateTime(createDate);
		userMapper.addUser(user);
		UserInfo info = user.getUserInfo();
		info.setUserInfoId(userId);
		info.setCreateTime(createDate);
		userInfoMapper.addUserInfo(info);
	}
	
	@Override
	public void delete(String[] userIds) {
		userInfoMapper.deleteUserInfo(userIds);
		roleMapper.deleteRoleByUserId(userIds);
 		userMapper.deleteUser(userIds);
	}
	
	@Override
	public User findOne(String userId) {
		
		return userMapper.findOne(userId);
	}
	
	@Override
	public void update(User user) {
		Date updateTime = new Date();
		user.setUpdateTime(updateTime);
		userMapper.updateUser(user);
		UserInfo info = user.getUserInfo();
		info.setUserInfoId(user.getUserId());
		info.setUpdateTime(updateTime);
		userInfoMapper.updateUserInfo(info);
	}
	
	@Override
	public List<UserInfo> findManager(String userId) {
		return userInfoMapper.findManager(userId);
	}
	
	@Override
	public void saveRoleUser(String userId, String[] roleIds) {
		//防止重复提交先删除
		userMapper.deleteRoleByUserId(userId);
		for (String roleId : roleIds) {
			userMapper.saveRoleUser(userId,roleId);
		}
		
	}

}
