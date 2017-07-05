package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {
	
	public List<UserInfo> findManager();
	
	public void addUserInfo(UserInfo info);
	
}
