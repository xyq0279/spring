package com.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

@Service
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public Boolean check(String param,int type){
		Map<String,String> map = new HashMap<String,String>();
		map.put("param", param);
		if(1==type){
			map.put("type", "username");
		}else if(2==type){
			map.put("type", "phone");
		}else if(3==type){
			map.put("type", "email");
		}
		return userMapper.check(map)>0;
	}

	public String addUser(User user) {
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		super.saveSelective(user);
		
		return user.getUsername();
	}

	public String findUser(String username,String password) {
		User param = new User();
		param.setUsername(username);
		User user = super.queryByWhere(param);
		if(user!=null){
			password = DigestUtils.md5Hex(password);
			if(password.equals(user.getPassword())){
				try {
					
					String ticket=  DigestUtils.md5Hex("TICKET_"+username+user.getId()+System.currentTimeMillis());
					String jsonData = MAPPER.writeValueAsString(user);
					//存入redis中保存7天
					redisService.set(ticket, jsonData, 60*60*24*7);
					return ticket;
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	
}
