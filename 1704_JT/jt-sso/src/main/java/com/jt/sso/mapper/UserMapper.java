package com.jt.sso.mapper;

import java.util.Map;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;

public interface UserMapper extends SysMapper<User>{
	/**
	 * 检查数据库中是否存在该用户
	 * @param map
	 * @return 
	 */
	int check(Map<String, String> map);
	
}
