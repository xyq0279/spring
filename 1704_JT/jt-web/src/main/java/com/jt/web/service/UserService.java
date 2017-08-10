package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	private  HttpClientService clientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public SysResult toRegister(User user) throws Exception {
		String url= "http://sso.jt.com/user/toRegister";
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		if(StringUtils.isEmpty(user.getEmail())){
			map.put("email", "---"+user.getPhone());
		}
		
		String jsonData = clientService.doPost(url, map);
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		String username = jsonNode.get("data").asText();
		return SysResult.oK(username);
	}

	public String toLogin(User user) throws Exception {
		String url = "http://sso.jt.com/user/tologin";
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		String jsonData = clientService.doPost(url, map);
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		String ticket = jsonNode.get("data").asText();
		return ticket;
	}

}
