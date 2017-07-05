package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
	@Override
	public void updateState(String[] userIds, int state) {
		userMapper.updateState(userIds,state);
	}
	@Override
	public List<UserInfo> findManager() {
		return userInfoMapper.findManager();
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

}
