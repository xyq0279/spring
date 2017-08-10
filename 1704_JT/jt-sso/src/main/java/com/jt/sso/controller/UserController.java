package com.jt.sso.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	
	private static final Logger log = Logger.getLogger(UserController.class);
	
	//检查数据
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object check(String callback,@PathVariable String param,@PathVariable int type){
		try {
			MappingJacksonValue mjv = new MappingJacksonValue(SysResult.oK(userService.check(param, type)));
			mjv.setJsonpFunction(callback);
			return mjv;	
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	//注册
	@RequestMapping("/toRegister")
	@ResponseBody
	public SysResult toRegister(User user){
		try {
			String username = userService.addUser(user);
			return SysResult.oK(username);
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	//登录
	@RequestMapping("/tologin")
	@ResponseBody
	public SysResult tologin(String username,String password){
		try {
			String ticket = userService.findUser(username,password);
			return SysResult.oK(ticket);
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	@RequestMapping("/query/{ticket}")
	@ResponseBody
	public Object qurey(String callback,@PathVariable String ticket){
		try {
			
			SysResult jsonData = SysResult.oK(redisService.get(ticket));
			MappingJacksonValue mjv = new MappingJacksonValue(jsonData);
			mjv.setJsonpFunction(callback);
			return mjv;
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
}
